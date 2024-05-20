package com.three.alcoholshoppingmall.project.login;


import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.exception.ErrorCode;
import com.three.alcoholshoppingmall.project.user.User;
import com.three.alcoholshoppingmall.project.user.UserDto;
import com.three.alcoholshoppingmall.project.user.UserSub;
import com.three.alcoholshoppingmall.project.user.WithdrawStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final BCryptPasswordEncoder encoder;

    public void createUser(UserDto userDto) {

        if (userDto.getEmail() == null) {
            throw new BizException(ErrorCode.NULLEMAIL);
        }

        User emailUser = loginRepository.findByEmail(userDto.getEmail());
        User phoneUser = loginRepository.findByPhone(userDto.getPhone());

        if (emailUser != null && userDto.getEmail().equals(emailUser.getEmail())) {
            throw new BizException(ErrorCode.DUPLEMAIL);
        } else if (userDto.getNickname() == null) {
            throw new BizException(ErrorCode.NULLNICKNAME);
        } else if (userDto.getAddress() == null) {
            throw new BizException(ErrorCode.NULLADDRESS);
        } else if (userDto.getAddress2() == null) {
            throw new BizException(ErrorCode.NULLLASTADDRESS);
        } else if (userDto.getPassword() == null) {
            throw new BizException(ErrorCode.NULLNICKNAME);
        } else if (!userDto.getPassword().equals(userDto.getPasswordch())) {
            throw new BizException(ErrorCode.CHECKPASSWORD);
        } else if (phoneUser != null && userDto.getPhone().equals(phoneUser.getPhone())) {
            throw new BizException(ErrorCode.DUPLPHONE);
        } else if (userDto.getPhone() == null) {
            throw new BizException(ErrorCode.NULLPHONE);
        }

        loginRepository.save(User.builder()
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .address(userDto.getAddress())
                .address2(userDto.getAddress2())
                .password(encoder.encode(userDto.getPassword()))
                .phone(userDto.getPhone())
                .birthdate(userDto.getBirthdate())
                .gender(userDto.getGender())
                .withdrawStatus(WithdrawStatus.N)
                .build());
    }

    public List<UserSub> SUB(String email) {

        User user = loginRepository.findByEmail(email);

        List<UserSub> list = new ArrayList<>();
        UserSub userSub = UserSub
                .builder()
                .nickname(user.getNickname())
                .address(user.getAddress())
                .address2(user.getAddress2())
                .build();

        list.add(userSub);

        return list;
    }
}
