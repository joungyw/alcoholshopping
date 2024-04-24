package com.three.alcoholshoppingmall.project.review;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    //해당 술의 리뷰 갯수
    @Query(value = "SELECT COUNT(*) FROM review WHERE NAME = :name", nativeQuery = true)
    int Reviewcacount(String name);

    //해당 이메일의 리뷰 보기
    List<Review> findByEmail(String email);

    Optional<Review> findByEmailAndName(String email, String name);

    List<Review> deleteByEmailAndName(String email, String name);
}
