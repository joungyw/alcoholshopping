package com.three.alcoholshoppingmall.project.review;


import com.three.alcoholshoppingmall.project.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")

@Tag(name = "reviewpicture", description = "마이 페이지 입니다.")
public class ReviewController {

    private final ReviewServicce reviewServicce;

    @GetMapping("/list")
    @Operation(summary = "내 리뷰 목록",
            description = "해당 회원이 작성한 리뷰들을 보여 주는 기능입니다." +
                    "email의 입력이 필요 합니다.")
    public ResponseEntity<List<Review>> ReviewList(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();

        List<Review> list = reviewServicce.Reviewlist(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("")
    @Operation(summary = "내 리뷰 등록 이미 있는경우 수정",
            description = "해당 술에 리뷰를 남기는 기능으로 이미 해당 술에 리뷰를 남긴경우 글과 평점 사진을 수정 하는 기능입니다." +
                    "email과 name에 술의 이름, marketname에 판매처의 이름 " +
                    "writing 는 리뷰 글, grade는 평점으로 0~10까지만 입력이 가능합니다." +
                    "picture는 사진으로 사진의 경로가 저장 되며 null로 보내는것도 가능 합니다.")
    public ResponseEntity<List<Review>> Review(@RequestBody ReviewDTO reviewDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        reviewDTO.setEmail(user.getEmail());

        List<Review> list = reviewServicce.Review(reviewDTO);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping("")
    @Operation(summary = "내 리뷰 삭제",
            description = "해당 술에 리뷰를 지우는 기능 입니다." +
    "email과 name에 술의 이름이 필요 합니다.")
    public ResponseEntity<List<Review>> ReviewDelete(@RequestBody ReviewDTO reviewDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        reviewDTO.setEmail(user.getEmail());

        List<Review> list = reviewServicce.ReviewDelete(reviewDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
