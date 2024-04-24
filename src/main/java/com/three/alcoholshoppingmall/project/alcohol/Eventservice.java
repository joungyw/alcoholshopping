package com.three.alcoholshoppingmall.project.alcohol;





import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Eventservice {

    private final AlcoholRepository alcoholRepository;

    //가장 많이 팔린 술 8개
    public List<Alcohol> Most() {
        List<Alcohol> list = alcoholRepository.mostsold();

        return list;
    }

    public List<Alcohol> Mostcategory(String maincategory) {

            List<Alcohol> list = alcoholRepository.most(maincategory);

            return list;
        }

    public List<Alcohol> Product() {
        List<Alcohol> list =  alcoholRepository.newproduct();

        return list;
    }


}
