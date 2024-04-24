package com.three.alcoholshoppingmall.project.alcohol;



import com.three.alcoholshoppingmall.project.purchase.PurchaseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/main")
@Tag(name = "main", description = "메인 페이지 입니다.")
public class AlcoholController {

    private final AlcoholService alcoholService;

    @PostMapping("/mainpage")
    @Operation(summary = "메인 페이지 정보")
    public ResponseEntity<List<Information>> MainPage(){

        List<Information> list = alcoholService.Page();

        return  ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/detail")
    @Operation(summary = "상세 페이지 정보")
    public ResponseEntity<List<DetailInformation>> Detail(@RequestBody AlcoholDto alcoholDto){

        List<DetailInformation> list = alcoholService.DetailPage(alcoholDto.getName());

        return  ResponseEntity.status(HttpStatus.OK).body(list);
    }


    @PostMapping("/pop")
    @Operation(summary = "인기 정렬")
    public ResponseEntity<List<DetailInformation>> Pop(){
        String Type = "인기";
        List<DetailInformation> list = alcoholService.SortType(Type);

        return  ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @PostMapping("/max")
    @Operation(summary = "가격 높은 순 정렬")
    public ResponseEntity<List<DetailInformation>> Max(){
        String Type = "최고";
        List<DetailInformation> list = alcoholService.SortType(Type);


        return  ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/min")
    @Operation(summary = "가격 낮은 정렬")
    public ResponseEntity<List<DetailInformation>> Min(){
        String Type = "최소";
        List<DetailInformation> list = alcoholService.SortType(Type);

        return  ResponseEntity.status(HttpStatus.OK).body(list);
    }
    

    @PostMapping("/algorithm")
    @Operation(summary = "내 알고리즘")
    public ResponseEntity<List<Alcohol>> MemberAlgorithm(@RequestBody PurchaseDTO purchaseDTO){

        List<Alcohol> list = alcoholService.Algorithm(purchaseDTO.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }



}
