package com.spring.blogging.controller.api;

import com.spring.blogging.dto.ResponseDto;
import com.spring.blogging.model.RoleType;
import com.spring.blogging.model.User;
import com.spring.blogging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

/*    @Autowired
    private HttpSession session;*/





    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) { //username, password, email
        System.out.println("UserApiController:save호출됨");




        //실제로 DB에 insert를 하고 아래에서return되면되요.

        userService.회원가입(user);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        //자바오브젝트를 JSON으로 변환해서 리턴 (Jackson lib)
    }

/*   전통적인 방식 // 다음시간에 스프링 시큐리티를 이용해서 로그인!!
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
        System.out.println("UserApiController: login호출됨");
        User principal = userService.로그인(user); //principal(접근주체)


        if (principal != null) {
            session.setAttribute("principal", principal);
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }*/
}
