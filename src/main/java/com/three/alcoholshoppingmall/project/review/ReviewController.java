package com.three.alcoholshoppingmall.project.review;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")

@Tag(name = "reviewpicture", description = "마이 페이지 입니다.")
public class ReviewController {

    private final ReviewServicce reviewServicce;

    @PostMapping("/list")
    @Operation(summary = "내 리뷰 목록")
    public ResponseEntity<List<Review>> ReviewList(@RequestBody ReviewDTO reviewDTO){
        List<Review> list = reviewServicce.Reviewlist(reviewDTO.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("")
    @Operation(summary = "내 리뷰 등록 이미 있는경우 수정")
    public ResponseEntity<List<Review>> Review(@RequestBody ReviewDTO reviewDTO) {
        List<Review> list = reviewServicce.Review(reviewDTO);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping("")
    @Operation(summary = "내 리뷰 삭제")
    public ResponseEntity<List<Review>> ReviewDelete(@RequestBody ReviewDTO reviewDTO) {
        List<Review> list = reviewServicce.ReviewDelete(reviewDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
