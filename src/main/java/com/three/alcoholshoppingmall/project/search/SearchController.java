package com.three.alcoholshoppingmall.project.search;

import com.three.alcoholshoppingmall.project.alcohol.Alcohol;
import com.three.alcoholshoppingmall.project.alcohol.AlcoholRepository;
import com.three.alcoholshoppingmall.project.alcohol.MainListDto;
import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.three.alcoholshoppingmall.project.exception.ErrorCode.*;

@RestController
@RequestMapping("/search")
@Tag(name = "SearchController", description = "주류 검색")
@RequiredArgsConstructor
@ToString

public class SearchController {

    private final AlcoholRepository alcoholRepository;
    private final SearchService searchService;


    @DeleteMapping("/delete")
    @Operation(summary = "회원의 검색 기록 삭제", description = "회원의 검색 기록을 삭제하는 기능입니다.<br>" +
            "해당 검색id를 입력하면 해당 검색 기록을 삭제합니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Search>> searchDelete(@RequestBody SearchDeleteDto searchDeleteDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Search> list = searchService.searchDelete(user.getEmail(), searchDeleteDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

@PostMapping("/maincategory")
@Operation(summary = "maincategory로 주류 검색",
        description = "maincategory에 해당하는 주류를 검색합니다. <br>" +
                "maincategory에는 와인, 위스키, 브랜디, 리큐르가 있습니다, 이 중 하나를 입력해주세요. <br>" +
                "피그마에서 maincategory 선택 시 subcategory 부분 All에 사용할 기능입니다.")
public ResponseEntity<List<MainListDto>> selectByMainCategory(@RequestBody SearchMainCategoryDto searchMainCategoryDto) {
    List<Alcohol> alcoholList = alcoholRepository.findByMaincategory(searchMainCategoryDto.getMaincategory());
    if (alcoholList.isEmpty()) {
        throw new BizException(NULLMAINCATEGORY);
    }
    List<MainListDto> list = new ArrayList<>();
    for (Alcohol alcohol : alcoholList) {
        MainListDto mainListDto = MainListDto.builder()
                .code(alcohol.getCode())
                .name(alcohol.getName())
                .price(alcohol.getPrice())
                .ratingaverage(alcoholRepository.Rating(alcohol.getName()))
                .picture(alcohol.getPicture())
                .build();
        list.add(mainListDto);
    }
    return ResponseEntity.status(HttpStatus.OK).body(list);
}


    @PostMapping("/subcategory")
    @Operation(summary = "subcategory로 주류 검색",
            description = "subcategory에 해당하는 주류를 검색합니다. <br>" +
                    "와인의 subcategory에는 레드 와인, 화이트 와인, 스파클링 와인, 로제 와인 <br>" +
                    "위스키의 subcategory에는 싱글몰트, 블렌디드, 버번 <br>" +
                    "브랜디의 subcategory에는 꼬냑, 깔바도스, 아르마냑 <br>" +
                    "리큐르의 subcategory에는 리큐르를 입력해주세요. <br>" +
                    "와인의 subcategory 입력시에는 띄어쓰기를 유의해주세요. <br>" +
                    "피그마에서 대분류 선택 시 All을 제외한 각각의 subcategory에 사용할 기능입니다.")
    public ResponseEntity<List<Alcohol>> selectBySubCategory(@RequestBody SearchSubCategroyDto searchSubCategroyDto) {
        List<Alcohol> list = alcoholRepository.findBySubcategory(searchSubCategroyDto.getSubcategory());
        if (list.isEmpty()) {
            throw new BizException(NULLSUBCATEGORY);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}

