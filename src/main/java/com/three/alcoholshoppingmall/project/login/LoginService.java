package com.three.alcoholshoppingmall.project.login;


import com.three.alcoholshoppingmall.project.exception.BizException;
import com.three.alcoholshoppingmall.project.exception.ErrorCode;
import com.three.alcoholshoppingmall.project.shoppingbasket.Shoppingbasket;
import com.three.alcoholshoppingmall.project.shoppingbasket.ShoppingbasketRepository;
import com.three.alcoholshoppingmall.project.user.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import static com.three.alcoholshoppingmall.project.exception.ErrorCode.CHECKPASSWORD;
import static com.three.alcoholshoppingmall.project.exception.ErrorCode.NOTFOUNDUSER;


@Service
@RequiredArgsConstructor
public class LoginService {

    @Value("${spring.mail.username}")
    private String senderEmail;

    private final LoginRepository loginRepository;
    private final BCryptPasswordEncoder encoder;
    private final ShoppingbasketRepository shoppingbasketRepository;
    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;

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
        User user = User.builder()
                .email(userDto.getEmail())
                .nickname(userDto.getNickname())
                .address(userDto.getAddress())
                .address2(userDto.getAddress2())
                .password(encoder.encode(userDto.getPassword()))
                .phone(userDto.getPhone())
                .birthdate(userDto.getBirthdate())
                .gender(userDto.getGender())
                .withdrawStatus(WithdrawStatus.N)
                .build();


        loginRepository.save(user);
        Shoppingbasket shoppingbasket = Shoppingbasket.builder()
                .user(user)
                .build();
        shoppingbasketRepository.save(shoppingbasket);
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

    public String sendAuthNum(String email) {

        if (email == null || email == "") {
            throw new BizException(ErrorCode.NOTINPUTEMAIL);
        }

        MimeMessage message = javaMailSender.createMimeMessage();

        int num = (int) (Math.random() * 10000);

        try {
            message.setFrom(senderEmail);   // 보내는 이메일
            message.setRecipients(MimeMessage.RecipientType.TO, email); // 보낼 이메일 설정
            message.setSubject("[AlcoholFree] 회원가입을 위한 이메일 인증");  // 제목 설정
            String body = "";
            body += "<h1>" + "안녕하세요." + "</h1>";
            body += "<h1>" + "AlcoholFree 입니다." + "</h1>";
            body += "<h3>" + "회원가입을 위한 요청하신 인증 번호입니다." + "</h3><br>";
            body += "<h2>" + "아래 코드를 회원가입 창으로 돌아가 입력해주세요." + "</h2>";

            body += "<div align='center' style='border:1px solid black; font-family:verdana;'>";
            body += "<h2>" + "회원가입 인증 코드입니다." + "</h2>";
            body += "<h1 style='color:blue'>" + num + "</h1>";
            body += "</div><br>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body, "UTF-8", "html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);

        return num + "";
    }

    public String findPw(String email) {
        if (email == null || email == "") {
            throw new BizException(ErrorCode.NOTINPUTEMAIL); // 유효성 검사
        }

        MimeMessage message = javaMailSender.createMimeMessage();
        // 임시비번 생성
        char[] charSet = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        StringBuilder tempPw = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int idx = (int) (charSet.length * Math.random());
            tempPw.append(charSet[idx]);
        }
        try {
            message.setFrom(senderEmail);   // 보내는 이메일
            message.setRecipients(MimeMessage.RecipientType.TO, email); // 보낼 이메일 설정
            message.setSubject("[AlcoholFree] 비밀번호 찾기를 위한 이메일 인증");  // 제목 설정
            String body = "";
            body += "<h1>" + "안녕하세요." + "</h1>";
            body += "<h1>" + "AlcoholFree 입니다." + "</h1>";
            body += "<h3>" + "비밀번호 찾기를 위한 요청하신 임시 비밀번호입니다." + "</h3><br>";
            body += "<h2>" + "아래 코드를 로그인 창으로 돌아가 임시 비밀번호를 입력해 로그인주세요. 로그인 후 비밀번호 수정은 꼭 해주세요" + "</h2>";

            body += "<div align='center' style='border:1px solid black; font-family:verdana;'>";
            body += "<h2>" + "비밀번호 찾기 임시 비밀번호입니다." + "</h2>";
            body += "<h1 style='color:blue'>" + tempPw + "</h1>";
            body += "</div><br>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body, "UTF-8", "html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);

        return tempPw + "";

    }

//    public String pwChange(ChangePw changePw) {
//        String tempPw
//        if (!changePw.getNewPassword().equals(changePw.getPasswordch())) {
//            throw new BizException(CHECKPASSWORD);
//        } else {
//            changePw.setNewPassword(encoder.encode(changePw.getNewPassword()));
//            userRepository.save();
//
//        }
//        return "비밀번호 변경이 완료되었습니다.";
//    }
}