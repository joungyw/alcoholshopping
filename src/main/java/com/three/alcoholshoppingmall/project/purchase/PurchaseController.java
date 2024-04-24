package com.three.alcoholshoppingmall.project.purchase;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")

@Tag(name = "purchase", description = "구매 페이지 입니다.")
public class PurchaseController {


    private final PurchaseServicce purchaseServicce;



    @PostMapping("/pickup")
    @Operation(summary = "구매내역 중 픽업 보기")
    public ResponseEntity<List<Purchase>> PICKUP(@Valid @RequestBody PurchaseDTO purchaseDTO) {

        List<Purchase> list = purchaseServicce.PICKUPlist(purchaseDTO.getEmail(), purchaseDTO.getOrdertype());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/delivery")
    @Operation(summary = "구매내역 중 배달 보기")
    public ResponseEntity<List<Purchase>> DELIVERY(@Valid @RequestBody PurchaseDTO purchaseDTO) {
        List<Purchase> list = purchaseServicce.DELIVERYlist(purchaseDTO.getEmail(), purchaseDTO.getOrdertype());

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/pickuplimt")
    @Operation(summary = "구매내역중 픽업 최근 5개만")

    public ResponseEntity<List<Purchase>> PICKUPLIMT(@RequestBody PurchaseDTO purchaseDTO) {
        List<Purchase> list = purchaseServicce.PICKUPlimt(purchaseDTO.getEmail(), purchaseDTO.getOrdertype());

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/deliverylimt")
    @Operation(summary = "구매내역중 배달 최근 5개만")

    public ResponseEntity<List<Purchase>> DELIVERYLIMT(@RequestBody PurchaseDTO purchaseDTO) {
        List<Purchase> list = purchaseServicce.DELIVERYLIMTlimt(purchaseDTO.getEmail(), purchaseDTO.getOrdertype());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}

