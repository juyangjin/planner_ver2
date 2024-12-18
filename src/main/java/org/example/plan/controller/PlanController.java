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

    //일정을 생성하기 위해 PostMapping 사용
    @PostMapping
    public ResponseEntity<PlanResponseDto> create(@RequestBody PlanRequestDto requestDt0) {

        PlanResponseDto planResponseDto =
                planService.save(
                        requestDt0.getUsername(),
                        requestDt0.getTitle(),
                        requestDt0.getContents()
                );

        return new ResponseEntity<>(planResponseDto, HttpStatus.OK);
    }

    //일정을 불러오기 위해 GetMapping 사용, planService.findAll 로 값을 받아서 List형식으로 저장하는 이유는 전체 조회를 하기 위함이다.
    @GetMapping
    public ResponseEntity<List<PlanResponseDto>> findAll() {
        List<PlanResponseDto> planResponseDtoList = planService.findAll();

        return new ResponseEntity<>(planResponseDtoList, HttpStatus.OK);
    }

    //전체 조회와 동일하지만 한 개만 불러와도 되므로 id를 기준으로 불러올 수 있는 planService.findById를 사용
    @GetMapping("/{id}")
    public ResponseEntity<PlanResponseDto> findById(@PathVariable Long id){
        PlanResponseDto planResponseDto = planService.findById(id);

        return new ResponseEntity<>(planResponseDto, HttpStatus.OK);
    }

    //값을 수정하기 위해 PutMapping 사용, id를 이용해 인덱스를 찾으면서 동시에 새 값도 입력받을 수 있게 planService.updatePlan를 사용
    @PutMapping("/{id}")
    public ResponseEntity<PlanResponseDto> updatePlan(
            @PathVariable Long id,
            @RequestBody PlanRequestDto requestDto
            ) {
        PlanResponseDto responseDto =
                planService.updatePlan(
                        id,
                        requestDto.getTitle(),
                        requestDto.getContents(),
                        requestDto.getPassword()
                );

        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        planService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
