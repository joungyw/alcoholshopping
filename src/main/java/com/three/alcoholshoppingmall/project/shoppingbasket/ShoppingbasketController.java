package com.three.alcoholshoppingmall.project.shoppingbasket;


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
@RequestMapping("/shoppingbasket")
@Tag(name = "Shoppingbasket", description = "장바구니 입니다.")
public class ShoppingbasketController {

    private final ShoppingbasketService shoppingbasketService;


    @GetMapping("")
    @Operation(summary = "장바구니 조회",
            description = "현재 장바구니에 넣은 물건을 조회하는 기능입니다." +
                    "email에 입력이 필요합니다.")
    public ResponseEntity<List<Shopping>> Shoppingbasketlist(@RequestBody ShoppingbasketDTO shoppingbasketDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        shoppingbasketDTO.setUser(user);

        List<Shopping> list = shoppingbasketService.Shoppinglist(shoppingbasketDTO.getUser().getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("")
    @Operation(summary = "장바구니 넣기",
            description = "해당 재품을 회원의 장바구니에 추가하는 기능 입니다. " +
                    "email과 name에는 술의 이름 " +
                    "marketname 은 판매처 명" +
                    "amount 물건의 수량의 입력이 필요 합니다.")
    public ResponseEntity<List<Shopping>> Shopping(@RequestBody ShoppingbasketDTO shoppingbasketDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        shoppingbasketDTO.setUser(user);

        List<Shopping> list = shoppingbasketService.Shopping(shoppingbasketDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping("")
    @Operation(summary = "장바구니 빼기",
            description = "해당 재품을 회원의 장바구니에서 뺴는 기능입니다. " +
                    "email과 name에는 술의 이름 " +
                    "marketname 은 판매처 명의 입력이 필요 합니다.")
    public ResponseEntity<List<Shopping>> DeleteShopping(@RequestBody ShoppingbasketDTO shoppingbasketDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        shoppingbasketDTO.setUser(user);

        List<Shopping> list = shoppingbasketService.Delete(shoppingbasketDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PutMapping("")
    @Operation(summary = "장바구니 수정",
            description = "회원의 장바구니에서 선택한 제품의 수량을 수정하는 기능입니다." +
                    "email과 name에는 술의 이름 " +
                    "marketname 은 판매처 명" +
                    "amount 물건의 수량의 입력이 필요 합니다.")
    public ResponseEntity<List<Shopping>> PutShopping(@RequestBody ShoppingbasketDTO shoppingbasketDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        shoppingbasketDTO.setUser(user);

        List<Shopping> list = shoppingbasketService.Put(shoppingbasketDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
