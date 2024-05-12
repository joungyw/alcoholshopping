package com.three.alcoholshoppingmall.project.review;


import com.three.alcoholshoppingmall.project.alcohol.Alcohol;
import com.three.alcoholshoppingmall.project.alcohol.AlcoholRepository;
import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.exception.ErrorCode;
import com.three.alcoholshoppingmall.project.favorites.Favoritesalcohol;
import com.three.alcoholshoppingmall.project.market.Market;
import com.three.alcoholshoppingmall.project.market.MarketRepository;
import com.three.alcoholshoppingmall.project.purchase.Purchase;
import com.three.alcoholshoppingmall.project.purchase.PurchaseRepository;
import com.three.alcoholshoppingmall.project.stock.StockRepository;
import com.three.alcoholshoppingmall.project.user.User;
import com.three.alcoholshoppingmall.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AlcoholRepository alcoholRepository;
    private final UserRepository userRepository;
    private final MarketRepository marketRepository;
    private final PurchaseRepository purchaseRepository;

    public List<Reviewshow> Reviewlist(ReviewDTO reviewDTO) {
        List<String> alcoholnames = reviewRepository.names(reviewDTO.getUser().getEmail());

        List<Review> reviews = reviewRepository.findByUser_Email(reviewDTO.getUser().getEmail());

        List<Reviewshow> list = new ArrayList<>();
        for (int i = 0; i < Math.min(alcoholnames.size(), reviews.size()); i++) {
            String name = alcoholnames.get(i);
            Review review = reviews.get(i);

            Reviewshow reviewshow = Reviewshow
                    .builder()
                    .name(name)
                    .grade(review.getGrade())
                    .writing(review.getWriting())
                    .picture(review.getPicture())
                    .build();

            list.add(reviewshow);
        }
        return list;
    }


    public List<Reviewshow> Review(ReviewDTO reviewDTO) {
        Alcohol alcoholcheck = alcoholRepository.findByCode(reviewDTO.getCode());
        Optional<Review> check = reviewRepository.findByUser_EmailAndAlcohol_Code(reviewDTO.getUser().getEmail(), reviewDTO.getCode());

        List<Reviewshow> list = new ArrayList<>();
        Review review;

        if (check.isPresent()) {
            Review existingReview = check.get();
            existingReview.setWriting(reviewDTO.getWriting());
            existingReview.setGrade(reviewDTO.getGrade());
            existingReview.setPicture(reviewDTO.getPicture());
            reviewRepository.save(existingReview);


            String alcoholname = alcoholRepository.name(reviewDTO.getCode());
            Reviewshow reviewshow = Reviewshow
                    .builder()
                    .name(alcoholname)
                    .writing(reviewDTO.getWriting())
                    .grade(reviewDTO.getGrade())
                    .picture(reviewDTO.getPicture())
                    .build();

            list.add(reviewshow);
        } else {
            User usercheck = userRepository.findByEmail(reviewDTO.getUser().getEmail());
            review = Review.builder()
                    .user(usercheck)
                    .alcohol(alcoholcheck)
                    .writing(reviewDTO.getWriting())
                    .grade(reviewDTO.getGrade())
                    .picture(reviewDTO.getPicture())
                    .build();
            reviewRepository.save(review);

            String alcoholname = alcoholRepository.name(reviewDTO.getCode());
            Reviewshow reviewshow = Reviewshow
                    .builder()
                    .name(alcoholname)
                    .writing(reviewDTO.getWriting())
                    .grade(reviewDTO.getGrade())
                    .picture(reviewDTO.getPicture())
                    .build();

            list.add(reviewshow);

        }
        return list;
    }


    @Transactional
    public List<Review> ReviewDelete(ReviewDTO reviewDTO) {
        Optional<Review> check = reviewRepository.findByUser_EmailAndAlcohol_Code(reviewDTO.getUser().getEmail(), reviewDTO.getCode());
        if (check.isPresent()) {
            reviewRepository.deleteByUser_EmailAndAlcohol_Code(reviewDTO.getUser().getEmail(), reviewDTO.getCode());
        } else {
            throw new BizException(ErrorCode.NOTFOUNDREVIEW);
        }
        return null;
    }


    public List<ReviewCheck> Cherklist(String email) {
        List<ReviewCheck> list = new ArrayList<>();

        List<Alcohol> alcohol = alcoholRepository.alcoholreview(email);
        List<Market> market = marketRepository.marketreview(email);
        List<Purchase> purchase = purchaseRepository.purchasereview(email);


        for (int i = 0; i < alcohol.size(); i++) {
            Alcohol alcohols = alcohol.get(i);
            Market markets = market.get(i);
            Purchase purchases = purchase.get(i);

            ReviewCheck reviewCheck = ReviewCheck
                    .builder()
                    .code(alcohols.getCode())
                    .name(alcohols.getName())
                    .marketname(markets.getMarketname())
                    .delivery(markets.getDelivery())
                    .purchaseday(purchases.getPurchaseday())
                    .build();
            list.add(reviewCheck);
        }
        return list;


    }

    public List<Reviewshow> AlcoholReview(Long code) {
        Alcohol alcohol = alcoholRepository.findByCode(code);
        List<Review> reviews = reviewRepository.findByAlcohol_Code(code);
        List<Reviewshow> list = new ArrayList<>();

        for (Review review : reviews) {
            Reviewshow reviewshow = Reviewshow.builder()
                    .name(alcohol.getName())
                    .writing(review.getWriting())
                    .picture(review.getPicture())
                    .grade(review.getGrade())
                    .build();
            list.add(reviewshow);
        }

        return list;
    }

}



