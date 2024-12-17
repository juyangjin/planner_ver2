package org.example.plan.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.plan.dto.PlanResponseDto;
import org.example.plan.entity.Plan;
import org.example.plan.repository.PlanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;

    public PlanResponseDto save(String username, String title, String contents){

        Plan plan = new Plan(title, contents);

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
    public PlanResponseDto updatePlan(Long id,  String title, String contents) {
        Plan findPlan = planRepository.findByIdOrElseThrow(id);
        planRepository.save(findPlan);

        return new PlanResponseDto(id, title ,contents ,findPlan.getCreatedAt(), LocalDateTime.now());
    }

    public void delete(Long id) {
        Plan findPlan = planRepository.findByIdOrElseThrow(id);

        planRepository.delete(findPlan);
    }
}
