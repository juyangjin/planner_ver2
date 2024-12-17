package org.example.plan.controller;

import lombok.RequiredArgsConstructor;
import org.example.plan.dto.PlanRequestDto;
import org.example.plan.dto.PlanResponseDto;
import org.example.plan.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping
    public ResponseEntity<PlanResponseDto> create(@RequestBody PlanRequestDto requestDto) {

        PlanResponseDto planResponseDto =
                planService.save(
                        requestDto.getUsername(),
                        requestDto.getTitle(),
                        requestDto.getContents()
                );

        return new ResponseEntity<>(planResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PlanResponseDto>> findAll() {
        List<PlanResponseDto> planResponseDtoList = planService.findAll();

        return new ResponseEntity<>(planResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDto> findById(@PathVariable Long id){
        PlanResponseDto planResponseDto = planService.findById(id);

        return new ResponseEntity<>(planResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanResponseDto> updatePlan(
            @PathVariable Long id,
            @RequestBody PlanRequestDto requestDto
            ) {
        PlanResponseDto responseDto =
                planService.updatePlan(
                        id,
                        requestDto.getUsername(),
                        requestDto.getTitle(),
                        requestDto.getContents());

        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

}
