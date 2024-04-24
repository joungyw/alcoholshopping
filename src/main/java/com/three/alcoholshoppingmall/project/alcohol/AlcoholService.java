package com.three.alcoholshoppingmall.project.alcohol;



import com.three.alcoholshoppingmall.project.favorites.Favorites;
import com.three.alcoholshoppingmall.project.favorites.FavoritesDTO;
import com.three.alcoholshoppingmall.project.favorites.FavoritesRepository;
import com.three.alcoholshoppingmall.project.purchase.Purchase;
import com.three.alcoholshoppingmall.project.purchase.PurchaseRepository;
import com.three.alcoholshoppingmall.project.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AlcoholService {

    private final AlcoholRepository alcoholRepository;
    private final ReviewRepository reviewRepository;
    private final AlgorithmRepository algorithmRepository;
    private final PurchaseRepository purchaseRepository;

    public List<Information> Page() {
        List<Double> rating = alcoholRepository.Ratingaverage();
        List<Integer> allamount = alcoholRepository.Allamount();
        List<Integer> minprice = alcoholRepository.Minprice();
        List<Alcohol> alcohols = alcoholRepository.findAll();
        List<Information> list = new ArrayList<>();

        for (int i = 0; i < alcohols.size(); i++) {
            Alcohol alcohol = alcohols.get(i);
            Double grade = (i < rating.size()) ? rating.get(i) : null;
            Integer price = (i < minprice.size()) ? minprice.get(i) : null;
            Integer amount = (i < allamount.size()) ? allamount.get(i) : null;

            Information info = Information.builder()
                    .id(alcohol.getId())
                    .name(alcohol.getName())
                    .maincategory(alcohol.getMaincategory())
                    .subcategory(alcohol.getSubcategory())
                    .content(alcohol.getContent())
                    .aroma(alcohol.getAroma())
                    .taste(alcohol.getTaste())
                    .finish(alcohol.getFinish())
                    .nation(alcohol.getNation())
                    .picture(alcohol.getPicture())
                    .grade(grade)
                    .price(price)
                    .amount(amount)
                    .build();

            list.add(info);
        }
        return list;
    }



    public List<DetailInformation> DetailPage(String name) {

        Double ratings = alcoholRepository.Rating(name);
        int prices = alcoholRepository.Price(name);
        List<Alcohol> alcohols = alcoholRepository.findByName(name);
        List<DetailInformation> list = new ArrayList<>();

        for (Alcohol alcohol : alcohols) {
            int reviewCount = reviewRepository.Reviewcacount(alcohol.getName());

            DetailInformation info = DetailInformation.builder()
                    .id(alcohol.getId())
                    .name(alcohol.getName())
                    .maincategory(alcohol.getMaincategory())
                    .subcategory(alcohol.getSubcategory())
                    .content(alcohol.getContent())
                    .aroma(alcohol.getAroma())
                    .taste(alcohol.getTaste())
                    .finish(alcohol.getFinish())
                    .nation(alcohol.getNation())
                    .picture(alcohol.getPicture())
                    .price(prices)
                    .ratingaverage(ratings)
                    .reviewcacount(reviewCount)
                    .build();

            list.add(info);
        }
        return list;
    }

    public List<DetailInformation> SortType(String tag) {
        List<Alcohol> alcohols;
        List<Double> ratings;
        List<Integer> prices;
        List<Integer> reviewCount;

        if (tag.equals("인기")) {
            alcohols = alcoholRepository.pop();
            ratings = alcoholRepository.popratings();
            prices = alcoholRepository.popprices();
            reviewCount = alcoholRepository.popreviewCount();
        } else if (tag.equals("최고")) {
            alcohols = alcoholRepository.max();
            ratings = alcoholRepository.maxratings();
            prices = alcoholRepository.maxprices();
            reviewCount = alcoholRepository.maxreviewCount();
        } else {
            alcohols = alcoholRepository.min();
            ratings = alcoholRepository.minratings();
            prices = alcoholRepository.minprices();
            reviewCount = alcoholRepository.minreviewCount();
        }

        List<DetailInformation> list = new ArrayList<>();

        for (int i = 0; i < alcohols.size(); i++) {
            Alcohol alcohol = alcohols.get(i);
            Double grade = (i < ratings.size()) ? ratings.get(i) : null;
            Integer price = (i < prices.size()) ? prices.get(i) : null;
            Integer count = (i < reviewCount.size()) ? reviewCount.get(i) : null;

            DetailInformation info = DetailInformation.builder()
                    .id(alcohol.getId())
                    .name(alcohol.getName())
                    .maincategory(alcohol.getMaincategory())
                    .subcategory(alcohol.getSubcategory())
                    .content(alcohol.getContent())
                    .aroma(alcohol.getAroma())
                    .taste(alcohol.getTaste())
                    .finish(alcohol.getFinish())
                    .nation(alcohol.getNation())
                    .picture(alcohol.getPicture())
                    .price(price)
                    .ratingaverage(grade)
                    .reviewcacount(count)
                    .build();


            list.add(info);
        }

        return list;
    }

    public List<Alcohol> Algorithm(String email) {

        Optional<Purchase> check = purchaseRepository.findByEmail(email);

        if (check.isPresent()) {

            String aroma = algorithmRepository.Aroma(email);
            String taste = algorithmRepository.Taste(email);
            String finish = algorithmRepository.Finish(email);

            List<Alcohol> list = algorithmRepository.personalalgorithm( aroma, taste, finish);


            return list;
        } else {
            List<Alcohol> list = alcoholRepository.mostsold();
            return list;
        }
    }
}