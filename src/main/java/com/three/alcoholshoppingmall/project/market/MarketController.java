package com.three.alcoholshoppingmall.project.market;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("market")
@RequiredArgsConstructor
@Tag(name = "MarketController",description = "마켓 정보")
public class MarketController {

    private final MarketRepository marketRepository;

    @GetMapping("address")
    @Operation(summary = "전체 마켓 목록")
    public ResponseEntity<List<Market>> marketAddress(){
        List<Market> market = marketRepository.findAll();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(market);
    }
}
