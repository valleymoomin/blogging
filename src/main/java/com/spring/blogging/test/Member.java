package com.spring.blogging.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {
    private Integer id;
    private String username;
    private String password;
    private String email;

    //private으로 만드는 이유:
    //변수의 상태는 메서드를 통해서 변경해야 한다. 객체지향적
    //getter, setter

    @Builder
    public Member(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
