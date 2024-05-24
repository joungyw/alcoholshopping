package com.three.alcoholshoppingmall.project.purchase;


import com.three.alcoholshoppingmall.project.alcohol.AlcoholRepository;
import com.three.alcoholshoppingmall.project.alcohol.AlcoholService;
import com.three.alcoholshoppingmall.project.alcohol.Alcoholmain;
import com.three.alcoholshoppingmall.project.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
@Tag(name = "purchase", description = "구매 페이지 입니다.")
@SecurityRequirement(name = "bearerAuth")
public class PurchaseController {


    private final PurchaseService purchaseServicce;
    private final AlcoholService alcoholService;


    @GetMapping("/pickup")
    @Operation(summary = "구매내역 중 픽업 보기",
            description = "회원의 구매 내역중 픽업으로 수령을 신청한 제품을 모두 보여줍니다." +
                    "입력 하실 값은 없습니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Purchaseshow>> PICKUP() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Purchaseshow> list = purchaseServicce.PICKUPlist(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/delivery")
    @Operation(summary = "구매내역 중 배달 보기",
            description = "회원의 구매 내역중 배달로 수령을 신청한 제품을 모두 보여줍니다." +
                    "입력 하실 값은 없습니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Purchaseshow>> DELIVERY() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Purchaseshow> list = purchaseServicce.DELIVERYlist(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/pickuplimt")
    @Operation(summary = "구매내역중 픽업 최근 5개만",
            description = "회원의 구매 내역중 가장 최근에 픽업으로 수령을 신청한 제품을 5개를 보여줍니다." +
                    "입력 하실 값은 없습니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Purchaseshow>> PICKUPLIMT() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Purchaseshow> list = purchaseServicce.PICKUPlimt(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/deliverylimt")
    @Operation(summary = "구매내역중 배달 최근 5개만",
            description = "회원의 구매 내역중 가장 최근에 배달로 수령을 신청한 제품을 5개를 보여줍니다." +
                    "입력 하실 값은 없습니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Purchaseshow>> DELIVERYLIMT() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Purchaseshow> list = purchaseServicce.DELIVERYLIMTlimt(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/toss")
    @Operation(summary = "토스페이 결제",
            description = "토스페이 결제입니다. " +
                    "필요한 정보 = 총가격,상품 이름,결제 결과 Callback URL,인증 완료 후 연결할 URL,결제 중단 시 사용자를 이동시킬 가맹점 페이지")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> tosspay(@Valid @RequestBody TossInfo tossInfo) {

        String checkoutPage = purchaseServicce.tosspay(tossInfo);

        return ResponseEntity.status(HttpStatus.OK).body(checkoutPage);
    }

    @GetMapping("/algorithm")
    @Operation(summary = "내 알고리즘",
            description = "회원의 구매정보를 토대로 술을 추천 합니다. 구매 정보가 없을 경우 많이 팔린 술 3개를 추천 합니다." +
                    "입력 하실 값은 없습니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Alcoholmain>> MemberAlgorithm() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Alcoholmain> list = alcoholService.Algorithm(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}

