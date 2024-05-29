package com.three.alcoholshoppingmall.project.user;

import com.three.alcoholshoppingmall.project.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.three.alcoholshoppingmall.project.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

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

    public User userInfo(String email) {
        User dbUser = userRepository.findByEmail(email);
        if (dbUser == null) {
            throw new BizException(NOTFOUNDUSER);
        } else {
            User user = User.builder()
                    .email(dbUser.getEmail())
                    .nickname(dbUser.getNickname())
                    .phone(dbUser.getPhone())
                    .address(dbUser.getAddress())
                    .address2(dbUser.getAddress2())
                    .gender(dbUser.getGender())
                    .birthdate(dbUser.getBirthdate())
                    .build();
            return user;
        }
    }

    public String updatePw(String email, PwUpdate pwUpdate) {
        System.out.println(pwUpdate);
        User dbUser = userRepository.findByEmail(email);
        System.out.println(dbUser);
        if (dbUser == null) {
            throw new BizException(NOTFOUNDUSER);
        } else if (!encoder.matches(pwUpdate.getPassword(), dbUser.getPassword())) {
            throw new BizException(DIFFERPASSWORD);
        } else if (!pwUpdate.getNewPassword().equals(pwUpdate.getPasswordch())) {
            throw new BizException(CHECKPASSWORD);
        } else if (pwUpdate.getPassword().equals(pwUpdate.getNewPassword())) {
            throw new BizException(SAMEPASSWORD);
        }
        else {
            dbUser.setPassword(encoder.encode(pwUpdate.getNewPassword()));
            userRepository.save(dbUser);

        }

        return "비밀번호 수정이 완료되었습니다.";
    }
}