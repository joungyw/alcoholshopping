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
@RequestMapping("/alcohol")
@Tag(name = "alcohol", description = "알콜 정보 출력 페이지 입니다.")
public class AlcoholController {

    private final AlcoholService alcoholService;
    private final Eventservice eventservice;

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


}
