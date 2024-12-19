package org.example.plan.controller;

import lombok.RequiredArgsConstructor;
import org.example.plan.dto.CommentRequestDto;
import org.example.plan.dto.CommentResponseDto;
import org.example.plan.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /*
    댓글 생성 시 게시글이 무엇인지 판별하기 위해 URL로 게시글을 불러오고, 입력하는 유저네임으로 사용자를 불러오게 하였다.
     */
    @PostMapping("/{id}")
    public ResponseEntity<CommentResponseDto> createcomment(
            @PathVariable Long id,
            @RequestBody CommentRequestDto requestDto
    ) {

        CommentResponseDto commentResponseDto =
                commentService.save(
                        id,
                        requestDto.getUsername(),
                        requestDto.getComment()
                );
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    /*
    전체 댓글을 한 번에 볼 수 있도록 코멘트 서비스 값을 List 형태로 가져와서 보여주게 했다.
     */
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAll() {
        List<CommentResponseDto> commentResponseDtoList = commentService.findAll();

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    /*
    한 개의 댓글만 볼 수 있도록 댓글 고유 id로 댓글을 조회할 수 있게 하였다.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> findById(@PathVariable Long id){
        CommentResponseDto commentResponseDto = commentService.findById(id);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    /*
    댓글 수정을 위해 수정할 댓글 아이디로 저장된 값을 불러오고, 코멘트 서비스에서 불러온 새로운 값을 그 위치에 저장할 수 있게 하였다.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long id,
            @RequestBody CommentRequestDto requestDto
            ) {
        CommentResponseDto responseDto =
                commentService.updateComment(
                        id,
                        requestDto.getComment()
                );

        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    /*
    댓글 삭제는 부가적인 비밀번호 구현부는 시간관계상 구현하지 못했지만 동작은 가능케하였다.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
