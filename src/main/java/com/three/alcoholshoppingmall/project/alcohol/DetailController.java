package com.three.alcoholshoppingmall.project.alcohol;

import com.three.alcoholshoppingmall.project.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/detail")
@Tag(name = "detail", description = "상세 정보를 보여주는 페이지로 토큰이 필요 합니다.")
public class DetailController {

    private final AlcoholService alcoholService;

    @PostMapping("")
    @Operation(summary = "상세 페이지 정보",
            description = "선택한 술의 정보와 가격 평균 평점 리뷰 갯수가 표시됩니다." +
                    "name에 술의 이름을 입력하시면 됩니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<DetailInformation>> Detail(@RequestBody AlcoholDto alcoholDto) {

            List<DetailInformation> list = alcoholService.DetailPage(alcoholDto);

            return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/algorithm")
    @Operation(summary = "내 알고리즘",
            description = "회원의 구매정보를 토대로 술을 추천 합니다. 구매 정보가 없을 경우 많이 팔린 술 8개를 추천 합니다." +
                    "입력 하실 값은 없습니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Alcohol>> MemberAlgorithm() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            User user = (User)authentication.getPrincipal();

            List<Alcohol> list =  alcoholService.Algorithm(user.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
