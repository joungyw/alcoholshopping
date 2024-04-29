package com.three.alcoholshoppingmall.project.purchase;


import com.three.alcoholshoppingmall.project.user.User;
import io.swagger.v3.oas.annotations.Operation;
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
public class PurchaseController {


    private final PurchaseServicce purchaseServicce;


    @GetMapping("/pickup")
    @Operation(summary = "구매내역 중 픽업 보기",
            description = "회원의 구매 내역중 픽업으로 수령을 신청한 제품을 모두 보여줍니다." +
                    "email의 입력이 필요합니다.")
    public ResponseEntity<List<Purchaseshow>> PICKUP(PurchaseDTO purchaseDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        purchaseDTO.setUser(user);
        List<Purchaseshow> list = purchaseServicce.PICKUPlist(purchaseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/delivery")
    @Operation(summary = "구매내역 중 배달 보기",
            description = "회원의 구매 내역중 배달로 수령을 신청한 제품을 모두 보여줍니다." +
                    "email의 입력이 필요합니다.")
    public ResponseEntity<List<Purchaseshow>> DELIVERY(PurchaseDTO purchaseDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        purchaseDTO.setUser(user);

        List<Purchaseshow> list = purchaseServicce.DELIVERYlist(purchaseDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/pickuplimt")
    @Operation(summary = "구매내역중 픽업 최근 5개만",
            description = "회원의 구매 내역중 가장 최근에 픽업으로 수령을 신청한 제품을 5개를 보여줍니다." +
                    "email의 입력이 필요합니다.")

    public ResponseEntity<List<Purchaseshow>> PICKUPLIMT(PurchaseDTO purchaseDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        purchaseDTO.setUser(user);

        List<Purchaseshow> list = purchaseServicce.PICKUPlimt(purchaseDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/deliverylimt")
    @Operation(summary = "구매내역중 배달 최근 5개만",
            description = "회원의 구매 내역중 가장 최근에 배달로 수령을 신청한 제품을 5개를 보여줍니다." +
                    "email의 입력이 필요합니다.")

    public ResponseEntity<List<Purchaseshow>> DELIVERYLIMT(PurchaseDTO purchaseDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        purchaseDTO.setUser(user);

        List<Purchaseshow> list = purchaseServicce.DELIVERYLIMTlimt(purchaseDTO);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}

