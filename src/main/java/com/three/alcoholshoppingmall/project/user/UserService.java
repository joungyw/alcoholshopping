package com.three.alcoholshoppingmall.project.user;

import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.login.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.three.alcoholshoppingmall.project.exception.ErrorCode.DUPLPHONE;
import static com.three.alcoholshoppingmall.project.exception.ErrorCode.NOTFOUNDUSER;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public User withdrawUser(String email) { // 회원 정보 탈퇴 코드
        User dbUser = userRepository.findMyCustom(email);

        if (dbUser != null) {
            dbUser.setWithdrawStatus(WithdrawStatus.Y);
            userRepository.save(dbUser);
            return dbUser;
        } else {
            throw new BizException(NOTFOUNDUSER); // 예외처리
        }
    }

    public String updateUser(String email, UserUpdate userUpdate) {
        User dbUser = userRepository.findByEmail(email);
        User phoneUser = userRepository.findByPhone(userUpdate.getPhone());


        if (dbUser == null) {
            throw new BizException(NOTFOUNDUSER);
        }
        if (phoneUser != null && dbUser.getEmail() == phoneUser.getEmail()) {
            dbUser.setNickname(userUpdate.getNickname());
            dbUser.setAddress(userUpdate.getAddress());
            dbUser.setAddress2(userUpdate.getAddress2());
            userRepository.save(dbUser);
        } else if (phoneUser != null && userUpdate.getPhone().equals(phoneUser.getPhone())) {
            throw new BizException(DUPLPHONE);
        }
         else {
            dbUser.setNickname(userUpdate.getNickname());
            dbUser.setPhone(userUpdate.getPhone());
            dbUser.setAddress(userUpdate.getAddress());
            dbUser.setAddress2(userUpdate.getAddress2());
            userRepository.save(dbUser);
        }
        System.out.println(dbUser);
        return "회원정보가 수정되었습니다";
    }
}

