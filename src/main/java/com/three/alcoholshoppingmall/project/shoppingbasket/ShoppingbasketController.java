package com.three.alcoholshoppingmall.project.shoppingbasket;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shoppingbasket")
@Tag(name = "Shoppingbasket", description = "장바구니 입니다.")
public class ShoppingbasketController {

    private final ShoppingbasketService shoppingbasketService;


    @PostMapping("/list")
    @Operation(summary = "장바구니 조회")
    public ResponseEntity<List<Shoppingbasket>> Shoppingbasketlist(@RequestBody ShoppingbasketDTO shoppingbasketDTO){

        List<Shoppingbasket> list = shoppingbasketService.Shoppinglist(shoppingbasketDTO.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("")
    @Operation(summary = "장바구니 넣기")
    public ResponseEntity<List<Shoppingbasket>> Shopping(@RequestBody ShoppingbasketDTO shoppingbasketDTO){

        List<Shoppingbasket> list = shoppingbasketService.Shopping(shoppingbasketDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping("")
    @Operation(summary = "장바구니 빼기")
    public ResponseEntity<List<Shoppingbasket>> DeleteShopping(@RequestBody ShoppingbasketDTO shoppingbasketDTO){

        List<Shoppingbasket> list = shoppingbasketService.Delete(shoppingbasketDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PutMapping("")
    @Operation(summary = "장바구니 수정")
    public ResponseEntity<List<Shoppingbasket>> PutShopping(@RequestBody ShoppingbasketDTO shoppingbasketDTO){

        List<Shoppingbasket> list = shoppingbasketService.Put(shoppingbasketDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
