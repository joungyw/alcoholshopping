package com.three.alcoholshoppingmall.project.user;

import com.three.alcoholshoppingmall.project.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.three.alcoholshoppingmall.project.exception.ErrorCode.NOTFOUNDUSER;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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

    public User updateUser(String email, UserUpdate userUpdate) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (optionalUser.isPresent()) {
            User dbUser = optionalUser.get();
            dbUser.setNickname(userUpdate.getNickname());
            dbUser.setPhone(userUpdate.getPhone());
            dbUser.setAddress(userUpdate.getAddress());
            dbUser.setAddress2(userUpdate.getAddress2());
            return userRepository.save(dbUser);

        } else {
            throw new BizException(NOTFOUNDUSER);
        }
    }
}
