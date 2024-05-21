package com.three.alcoholshoppingmall.project.shoppingbasket;


import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.exception.ErrorCode;
import com.three.alcoholshoppingmall.project.stock.StockDTO;
import com.three.alcoholshoppingmall.project.stock.StockNumber;
import com.three.alcoholshoppingmall.project.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shoppingbasket")
@Tag(name = "Shoppingbasket", description = "장바구니 입니다.")
@SecurityRequirement(name = "bearerAuth")
public class ShoppingbasketController {

    private final ShoppingbasketService shoppingbasketService;
    private final DetailbasketRepository detailbasketRepository;

    @GetMapping("")
    @Operation(summary = "장바구니 조회",
            description = "현재 장바구니에 넣은 물건을 조회하는 기능입니다. <br>" +
                    "입력 하실 값은 없습니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Shopping>> Shoppingbasketlist() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();


        List<Shopping> list = shoppingbasketService.Shoppinglist(user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("")
    @Operation(summary = "장바구니 넣기",
            description = "해당 재품을 회원의 장바구니에 추가하는 기능 입니다. <br>" +
                    "stock에 입력이 필요 합니다. <br>" +
                    "stock은  매장의 코드와 술의 코드가 합쳐진것으로 1~150까지 있습니다. <br>" +
                    "amount 물건의 수량의 입력이 필요 합니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Shopping>> Shopping(@RequestBody DetailbasketDTO detailbasketDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();


        List<Shopping> list = shoppingbasketService.Shopping(user, detailbasketDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping("")
    @Operation(summary = "장바구니 빼기",
            description = "해당 재품을 회원의 장바구니에서 뺴는 기능입니다. <br>" +
                    "stock에 입력이 필요 합니다.<br>" +
                    "stock은  매장의 코드와 술의 코드가 합쳐진것으로 1~150까지 있습니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Shopping>> DeleteShopping(@RequestBody DetailbasketDTO detailbasketDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<Shopping> list = shoppingbasketService.Delete(user, detailbasketDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @DeleteMapping("/all")
    @Operation(summary = "장바구니 물건 전부 빼기",
            description = "해당 재품을 회원의 장바구니에서 물건을 전부 뺴는 기능입니다. <br>" +
                    "입력 값은 없습니다.")
    @SecurityRequirement(name = "bearerAuth")
    @Transactional
    public ResponseEntity<String> DeleteShopping() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Optional<Integer> baskets = detailbasketRepository.basketcheck(user.getEmail());

        if(baskets.isPresent() && baskets.get() != 0){
            detailbasketRepository.deldeteall(user.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body("장바구니의 물건을 전부 삭제 하였습니다.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body("장바구니가 이미 비어 있습니다.");
        }
    }

    @PutMapping("")
    @Operation(summary = "장바구니 수정",
            description = "회원의 장바구니에서 선택한 제품의 수량을 수정하는 기능입니다." +
                    "stock에 입력이 필요 합니다." +
                    "stock은  매장의 코드와 술의 코드가 합쳐진것으로 1~150까지 있습니다." +
                    "amount 물건의 수량의 입력이 필요 합니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<Shopping>> PutShopping(@RequestBody DetailbasketDTO detailbasketDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        List<Shopping> list = shoppingbasketService.Put(user, detailbasketDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/stock")
    @Operation(summary = "스텍 넘버 확인",
            description = "해당 술과 매장의 코드를 보내면 스텍를 반환하여 줍니다. <br>" +
            "술의 고유의 코드와 매장 고유의 코드를 입력 받으면 장바구니에 사용되는 스텍을 반환하여 줍니다. <br>" +
            "alcohol은 술의 고유 코드는 1~50까지 있습니다. <br>" +
            "market은 매장의 고유 코드로 1~5까지 있습니다.")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<StockNumber>> StockCheck(@RequestBody StockDTO stockDTO) {


        List<StockNumber> list = shoppingbasketService.Stock(stockDTO);

        return ResponseEntity.status(HttpStatus.OK).body(list);



    }





}
