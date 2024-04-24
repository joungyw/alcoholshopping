package com.three.alcoholshoppingmall.project.market;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("map")
@RequiredArgsConstructor
public class MarketController {

    private final MarketRepository marketRepository;

    @GetMapping("address")
    public ResponseEntity<List<Market>> marketAddress(){
        List<Market> market = marketRepository.findAll();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(market);
    }
}
