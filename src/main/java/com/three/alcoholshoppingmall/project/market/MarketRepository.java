package com.three.alcoholshoppingmall.project.market;

import com.three.alcoholshoppingmall.project.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarketRepository  extends JpaRepository<Market, Long> {


}
