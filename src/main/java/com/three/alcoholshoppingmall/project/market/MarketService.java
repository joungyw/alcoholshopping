package com.three.alcoholshoppingmall.project.market;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {

    private final MarketRepository marketRepository;

    public List<Market> Marketlist(Long code) {

        List<Market> list = marketRepository.marketlist(code);


        return list;
    }
}
