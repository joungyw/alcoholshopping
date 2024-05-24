package com.three.alcoholshoppingmall.project.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(value = "select m from User m where m.email = :email and m.withdrawStatus='N'")
    User findMyCustom(String email);

    User findByPhone(String phone);

    User findByEmailAndPassword(String email, PwUpdate pwUpdate);
}