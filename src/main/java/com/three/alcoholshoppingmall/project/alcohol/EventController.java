package com.three.alcoholshoppingmall.project.alcohol;

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
@RequestMapping("/event")
@Tag(name = "event", description = "주간 판매정보를 토대로 인기상품을 보여 줍니다.")
public class EventController {

    private final Eventservice eventservice;

    @PostMapping("/most")
    @Operation(summary = "주간 많이 팔린 제품")
    public ResponseEntity<List<Alcohol>> Mostsold(){

        List<Alcohol> list = eventservice.Most();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @PostMapping("/mostcategory")
    @Operation(summary = "주간 많이 팔린 술 카테고리별")
    public ResponseEntity<List<Alcohol>> MostCategory(@RequestBody AlcoholDto alcoholDto){

        List<Alcohol> list = eventservice.Mostcategory(alcoholDto.getMaincategory());

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @PostMapping("/newproduct")
    @Operation(summary = "신 제품")
    public ResponseEntity<List<Alcohol>> NewProduct(){

        List<Alcohol> list = eventservice.Product();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }


}
