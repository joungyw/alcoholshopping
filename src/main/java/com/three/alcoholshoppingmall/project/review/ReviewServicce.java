package com.three.alcoholshoppingmall.project.review;



import com.three.alcoholshoppingmall.project.alcohol.Alcohol;
import com.three.alcoholshoppingmall.project.alcohol.AlcoholRepository;
import com.three.alcoholshoppingmall.project.user.User;
import com.three.alcoholshoppingmall.project.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class ReviewServicce {

    private final ReviewRepository reviewRepository;
    public final AlcoholRepository alcoholRepository;
    public final UserRepository userRepository;


    public List<Review> Reviewlist(String email) {
        List<Review> list = reviewRepository.findByUser_Email(email);
        return list;
    }

    public List<Review> Review(ReviewDTO reviewDTO) {
        Alcohol alcoholcheck = alcoholRepository.findByCode(reviewDTO.getAlcohol());
        Optional<Review> check = reviewRepository.findByUser_EmailAndAlcohol_Code(reviewDTO.getUser().getEmail(), reviewDTO.getAlcohol());

        List<Review> list = new ArrayList<>();
        Review review;

        if (check.isPresent()) {
            Review existingReview = check.get();
            existingReview.setWriting(reviewDTO.getWriting());
            existingReview.setGrade(reviewDTO.getGrade());
            existingReview.setPicture(reviewDTO.getPicture());
            Review updatedReview = reviewRepository.save(existingReview);
            list.add(updatedReview);
        } else {
            User usercheck = userRepository.findByEmail(reviewDTO.getUser().getEmail());
            review = Review.builder()
                    .user(usercheck)
                    .alcohol(alcoholcheck)
                    .writing(reviewDTO.getWriting())
                    .grade(reviewDTO.getGrade())
                    .picture(reviewDTO.getPicture())
                    .build();
            Review reviewsave = reviewRepository.save(review);
            list.add(reviewsave);
        }
        return list;
    }


    @Transactional
    public List<Review> ReviewDelete(ReviewDTO reviewDTO) {
        Optional<Review> check = reviewRepository.findByUser_EmailAndAlcohol_Code(reviewDTO.getUser().getEmail(), reviewDTO.getAlcohol());
        if (check.isPresent()) {
            reviewRepository.deleteByUser_EmailAndAlcohol_Code(reviewDTO.getUser().getEmail(), reviewDTO.getAlcohol());
        } else {
            throw new NoSuchElementException("해당 리뷰는 존재하지 않습니다.");
        }
        return null;
    }


}




