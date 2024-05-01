package com.three.alcoholshoppingmall.project.main;


import com.three.alcoholshoppingmall.project.alcohol.AlcoholDto;
import com.three.alcoholshoppingmall.project.alcohol.Alcoholmain;
import com.three.alcoholshoppingmall.project.alcohol.Eventservice;
import com.three.alcoholshoppingmall.project.alcohol.MainListDto;
import com.three.alcoholshoppingmall.project.market.MarketService;
import com.three.alcoholshoppingmall.project.search.MemberSearchDto;
import com.three.alcoholshoppingmall.project.search.NoneMemberSearchDto;
import com.three.alcoholshoppingmall.project.search.Search;
import com.three.alcoholshoppingmall.project.search.SearchService;
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
@RequestMapping("main")
@Tag(name = "main", description = "메인페이지 입니다.")
public class MainConstructor {

    private final Eventservice eventservice;
    private final MarketService marketService;
    private final SearchService searchService;


    @GetMapping("/most")
    @Operation(summary = "주간 많이 팔린 제품",
            description = "주간 많이 팔린 술 8개를 보여 줍니다." +
                    "입력 값은 없습니다.")
    public ResponseEntity<List<Alcoholmain>> Mostsold() {

        List<Alcoholmain> list = eventservice.Most();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/rand")
    @Operation(summary = "랜덤 3개",
            description = "랜덤으로 3개의 제품을 보여줍니다. ")
    public ResponseEntity<List<Alcoholmain>> RAND() {

        List<Alcoholmain> list = eventservice.Rand();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/newproduct")
    @Operation(summary = "신 제품",
            description = "가장 최근에 나온 제품을 보여 줍니다." +
                    "입력 값은 없습니다.")
    public ResponseEntity<List<Alcoholmain>> NewProduct() {

        List<Alcoholmain> list = eventservice.Product();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/delivery")
    @Operation(summary = "배달 배송을 하는 매장의 판매 물품",
            description = "입력 값은 따로 없습니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Alcoholmain>> DeliveryMarket() {

        String type = "Delivery";
        List<Alcoholmain> list = marketService.DeliveryPickup(type);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }

    @GetMapping("/pickup")
    @Operation(summary = "픽업 하는 매장의 판매 물품",
            description = "입력 값은 따로 없습니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Alcoholmain>> PickupMarket() {

        String type = "pickup";
        List<Alcoholmain> list = marketService.DeliveryPickup(type);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }

    @PostMapping("/contents") // 회원 검색
    @Operation(summary = "회원일 때 검색, name으로 주류를 검색",
            description = "회원이 검색하는 기능입니다. <br>" +
                    "searchcontents에 name으로 주류를 검색하는 기능입니다. <br>" +
                    "검색을 완료하지 않아도 내용이 나오게 만들었습니다. <br>" +
                    "피그마에서 검색을 하면 검색 기록과 회원의 이메일이 db에 저장됩니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<MainListDto>> memberSearch(@RequestBody MemberSearchDto memberSearchDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email;
        User user = (User) authentication.getPrincipal();
        email = user.getEmail();
        List<MainListDto> list = searchService.memberSearch(memberSearchDto.getSearchcontents(), email);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/anony/contents")
    @Operation(summary = "비회원일 때 검색, name으로 주류를 검색",
            description = "비회원이 검색하는 기능입니다.<br>" +
                    "searchcontents에 name으로 주류를 검색하는 기능입니다.<br>" +
                    "검색을 완료하지 않아도 내용이 나오게 만들었습니다.<br>" +
                    "비회원 이메일은 anony@anony.anony입니다.<br>" +
                    "이메일과 searchcontents에 입력값이 필요합니다." +
                    "피그마에서 검색을 하면 검색 기록과 비회원의 이메일이 db에 저장됩니다.")
    public ResponseEntity<List<MainListDto>> NonmemberSearch(@RequestBody NoneMemberSearchDto noneMemberSearchDto) {
        String email = "anony@anony.anony";
        List<MainListDto> list = searchService.memberSearch(noneMemberSearchDto.getSearchcontents(), email);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


    @GetMapping("/recent")
    @Operation(summary = "최근 검색기록",
            description = "최근 검색 기록을 5개를 출력하게 만들었습니다. <br>" +
                    "피그마에서 검색 창에 검색 시 최근 검색 기록 5개를 뜨게 하는 기능입니다. <br>" +
                    "입력 값은 필요 없습니다.<br>" +
                    "검색을 하면서 db에 저장되었던 내용을 내림차순으로 5개를 출력하게 하는 기능입니다. <br>")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Search>> recent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String email = user.getEmail();
        List<Search> list = searchService.recentSearch(email);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/main")
    @Operation(summary = "메인카테고리에 입력 받으면 그에 맞게 분류해서 보여 줍니다.",
            description = "maincategory에 와인 위스키 리큐르 브랜디의 중 하나를 입력하시면 됩니다.")
    public ResponseEntity<List<Alcoholmain>> AlcoholMaincategory(@RequestBody AlcoholDto alcoholDto) {

        List<Alcoholmain> list = marketService.Maincategory(alcoholDto.getMaincategory());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }
    @PostMapping("/sub")
    @Operation(summary = "서브카테고리에 입력 받으면 그에 맞게 분류해서 보여 줍니다.",
            description = "Subcategory에 값을 입력하시면 됩니다.")
    public ResponseEntity<List<Alcoholmain>> AlcoholSubcategory(@RequestBody AlcoholDto alcoholDto) {

        List<Alcoholmain> list = marketService.Subcategory(alcoholDto.getSubcategory());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(list);
    }




}



