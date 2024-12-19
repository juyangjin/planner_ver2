package org.example.plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.plan.dto.CommentResponseDto;
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

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final PlanRepository planRepository;

    /*
    댓글에 게시글과, 유저를 연결하기 위해 CommentRespository에 해당 값들을 가져오게 하였다.
    댓글에 대한 응답에 헷갈리지 않게 코멘트 id와 제목, 코멘트, 작성 시간 등이 제대로 나오게 구현하였다.
     */
    public CommentResponseDto save(Long id, String username, String saveComment) {
        Member findMember = memberRepository.findMemberByUsernameOrElseThrow(username);
        Plan findPlan = planRepository.findByIdOrElseThrow(id);

        Comment comment = new Comment(username,saveComment);
        comment.setMember(findMember);
        comment.setPlan(findPlan);

        commentRepository.save(comment);

        return new CommentResponseDto(comment.getId(),comment.getUsername(),findPlan.getTitle(),comment.getComment(),comment.getCreatedAt(),comment.getModifiedAt());
    }


    public List<CommentResponseDto> findAll(){
        return commentRepository.findAll()
                .stream()
                .map(CommentResponseDto::toDto)
                .toList();
    }

    public CommentResponseDto findById(Long id) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        return  new CommentResponseDto(findComment.getId(),findComment.getUsername(),findComment.getPlan().getTitle() ,findComment.getComment(),findComment.getCreatedAt(),findComment.getModifiedAt());

    }

    @Transactional
    public CommentResponseDto updateComment(Long id, String comment) {

        Comment findComment = commentRepository.findByIdOrElseThrow(id);
        Member member = findComment.getMember();

        findComment.setComment(comment);

        return new CommentResponseDto(findComment.getId(), findComment.getUsername(),findComment.getPlan().getTitle(),findComment.getComment(),findComment.getCreatedAt(), LocalDateTime.now());
    }

    public void delete(Long id) {
        Comment findComment = commentRepository.findByIdOrElseThrow(id);

        commentRepository.delete(findComment);
    }
}
