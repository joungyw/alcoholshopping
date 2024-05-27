package com.three.alcoholshoppingmall.project.user;

import com.three.alcoholshoppingmall.project.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.three.alcoholshoppingmall.project.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

//    public User withdrawUser(String email) { // 회원 정보 탈퇴 코드
//        User dbUser = userRepository.findMyCustom(email);
//
//        if (dbUser != null) {
//            dbUser.setWithdrawStatus(WithdrawStatus.Y);
//            userRepository.save(dbUser);
//            return dbUser;
//        } else {
//            throw new BizException(NOTFOUNDUSER); // 예외처리
//        }
//    }

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
        } else {
            dbUser.setNickname(userUpdate.getNickname());
            dbUser.setPhone(userUpdate.getPhone());
            dbUser.setAddress(userUpdate.getAddress());
            dbUser.setAddress2(userUpdate.getAddress2());
            userRepository.save(dbUser);
        }
        return "회원정보가 수정되었습니다";
    }

    public List<User> userInfo(String email) {
        User dbUser = userRepository.findByEmail(email);
        if (dbUser == null) {
            throw new BizException(NOTFOUNDUSER);
        } else {
            List<User> list = new ArrayList<>();
            User user = User.builder()
                    .email(dbUser.getEmail())
                    .nickname(dbUser.getNickname())
                    .phone(dbUser.getPhone())
                    .address(dbUser.getAddress())
                    .address2(dbUser.getAddress2())
                    .build();
            list.add(user);

            return list;
        }
    }
}

//    public String updatePw(String email, PwUpdate pwUpdate) {
//        User dbUser = userRepository.findByEmailAndPassword(email, pwUpdate);
//        if (dbUser == null) {
//            throw new BizException(NOTFOUNDUSER);
//        } else if (dbUser.getPassword() == pwUpdate.getNewPassword()) {
//            throw new BizException(SAMEPASSWORD);
//        } else if (pwUpdate.getNewPassword() != pwUpdate.getPasswordch()) {
//            throw new BizException(CHECKPASSWORD);
//        } else {
//            dbUser.setPassword(encoder.encode(pwUpdate.getPassword()));
//            pwUpdate.setNewPassword(encoder.encode(pwUpdate.getNewPassword()));
//            pwUpdate.setPasswordch(encoder.encode(pwUpdate.getPasswordch()));
//            userRepository.save(dbUser);
//        }
//        return "비밀번호 변경을 완료했습니다.";
//    }


