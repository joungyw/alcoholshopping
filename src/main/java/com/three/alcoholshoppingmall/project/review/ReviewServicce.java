package com.three.alcoholshoppingmall.project.review;



import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class ReviewServicce {

    private final ReviewRepository reviewRepository;


    public List<Review> Reviewlist(String email) {
        List<Review> list = reviewRepository.findByEmail(email);
        return list;
    }

    public List<Review> Review(ReviewDTO reviewDTO) {
        Optional<Review> check = reviewRepository.findByEmailAndName(reviewDTO.getEmail(), reviewDTO.getName());

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
            review = Review.builder()
                    .email(reviewDTO.getEmail())
                    .name(reviewDTO.getName())
                    .writing(reviewDTO.getWriting())
                    .grade(reviewDTO.getGrade())
                    .marketname(reviewDTO.getMarketname())
                    .picture(reviewDTO.getPicture())
                    .build();
            Review reviewsave = reviewRepository.save(review);
            list.add(reviewsave);
        }
        return list;
    }


    @Transactional
    public List<Review> ReviewDelete(ReviewDTO reviewDTO) {
        Optional<Review> check = reviewRepository.findByEmailAndName(reviewDTO.getEmail(), reviewDTO.getName());
        if (check.isPresent()) {
            reviewRepository.deleteByEmailAndName(reviewDTO.getEmail(), reviewDTO.getName());
        } else {
            throw new NoSuchElementException("해당 리뷰는 존재하지 않습니다.");
        }
        return null;
    }

    }




