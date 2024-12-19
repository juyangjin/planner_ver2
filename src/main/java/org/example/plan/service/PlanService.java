package org.example.plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.plan.config.PasswordEncoder;
import org.example.plan.dto.PlanResponseDto;
import org.example.plan.entity.Comment;
import org.example.plan.entity.Member;
import org.example.plan.entity.Plan;
import org.example.plan.repository.CommentRepository;
import org.example.plan.repository.MemberRepository;
import org.example.plan.repository.PlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.regex.Pattern.matches;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final MemberRepository memberRepository;
    private final PlanRepository planRepository;
    private final PasswordEncoder passwordEncoder;
    private final CommentRepository commentRepository;

    public PlanResponseDto save(String username, String title, String contents){
        Member findMember = memberRepository.findMemberByUsernameOrElseThrow(username);

        Plan plan = new Plan(title, contents);
        plan.setMember(findMember);

        planRepository.save(plan);

        return new PlanResponseDto(plan.getId(),plan.getTitle(),plan.getContents(),plan.getCreatedAt(),plan.getCreatedAt());
    }

    public List<PlanResponseDto> findAll(){
        return planRepository.findAll()
                .stream()
                .map(PlanResponseDto::toDto)
                .toList();
    }

    public PlanResponseDto findById(Long id){
        Plan findPlan = planRepository.findByIdOrElseThrow(id);

        return new PlanResponseDto(findPlan.getId(), findPlan.getTitle(), findPlan.getContents(),findPlan.getCreatedAt(),findPlan.getModifiedAt());
    }

    @Transactional
    public PlanResponseDto updatePlan(Long id,  String title, String contents, String password) {

        Plan findPlan = planRepository.findByIdOrElseThrow(id);
        Member member = findPlan.getMember();

        if(!passwordEncoder.matches(password,member.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST," 비밀번호 오답");
        }

        findPlan.setTitle(title);
        findPlan.setContents(contents);

        return new PlanResponseDto(findPlan.getId(), findPlan.getTitle(), findPlan.getContents(),findPlan.getCreatedAt(), LocalDateTime.now());
    }

    public void delete(Long id) {
        Plan findPlan = planRepository.findByIdOrElseThrow(id);
        Comment findComment =  commentRepository.findByIdOrElseThrow(id);

        if (!findComment.getComment().isEmpty()) {
            throw new IllegalStateException("Plan에 연결된 댓글이 존재합니다. 삭제할 수 없습니다.");
        }

        planRepository.delete(findPlan);
    }
}
