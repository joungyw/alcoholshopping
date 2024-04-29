package com.three.alcoholshoppingmall.project.alcohol;

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
@RequestMapping("/alcohol")
@Tag(name = "alcohol", description = "알콜 정보 출력 페이지 입니다.")
public class AlcoholController {

    private final AlcoholService alcoholService;
    private final Eventservice eventservice;

//    @GetMapping("/mainpage")
//    @Operation(summary = "메인 페이지 정보",
//            description = "모든 술의 정보와 가격 평균 평점 리뷰 갯수가 표시됩니다." +
//                    "따로 입력 값은  필요 없습니다"
//    )
//    public ResponseEntity<List<Information>> MainPage() {
//
//        List<Information> list = alcoholService.Page();
//
//        return ResponseEntity.status(HttpStatus.OK).body(list);
//    }



    @GetMapping("/pop")
    @Operation(summary = "인기 정렬",
            description = "많이 팔린 순으로 정렬 합니다." +
                    "입력 값은 없습니다.")
    public ResponseEntity<List<DetailInformation>> Pop() {
        String Type = "인기";
        List<DetailInformation> list = alcoholService.SortType(Type);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/max")
    @Operation(summary = "가격 높은 순 정렬",
            description = "가격이 높은 순으로 정렬 합니다." +
                    "입력 값은 없습니다.")
    public ResponseEntity<List<DetailInformation>> Max() {
        String Type = "최고";
        List<DetailInformation> list = alcoholService.SortType(Type);


        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/min")
    @Operation(summary = "가격 낮은 정렬",
            description = "가격이 낮은 순으로 정렬 합니다." +
                    "입력 값은 없습니다.")
    public ResponseEntity<List<DetailInformation>> Min() {
        String Type = "최소";
        List<DetailInformation> list = alcoholService.SortType(Type);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/most")
    @Operation(summary = "주간 많이 팔린 제품",
            description = "주간 많이 팔린 술 8개를 보여 줍니다." +
                    "입력 값은 없습니다.")
    public ResponseEntity<List<Alcohol>> Mostsold(){

        List<Alcohol> list = eventservice.Most();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @PostMapping("/mostcategory")
    @Operation(summary = "주간 많이 팔린 술 카테고리별",
            description = "각 카테고리별로  주간 많이 팔린 술 8개를 각 카테고리별로 보여 줍니다." +
                    "maincategory 에 입력이 필요 합니다. 위스키 ,와인 ,리큐르 ,브렌디 ")

    public ResponseEntity<List<Alcohol>> MostCategory(@RequestBody AlcoholDto alcoholDto){


        List<Alcohol> list = eventservice.Mostcategory(alcoholDto.getMaincategory());

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/newproduct")
    @Operation(summary = "신 제품",
            description = "가장 최근에 나온 제품을 보여 줍니다." +
                    "입력 값은 없습니다.")
    public ResponseEntity<List<Alcohol>> NewProduct(){

        List<Alcohol> list = eventservice.Product();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }



}
