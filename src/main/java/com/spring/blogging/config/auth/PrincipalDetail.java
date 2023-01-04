package com.spring.blogging.config.auth;

import com.spring.blogging.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
@Getter
public class PrincipalDetail implements UserDetails {
    private User user; //콤포지션(객체를 품고있는)

    public PrincipalDetail(User user){
       this.user = user;
    }



    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정이 만료되지 않았는지 리턴한다.(true:만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있지않았는지 리턴한다.(true:잠기지않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료되지 않았는지 리턴한다.(true:만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화(사용가능)인지 리턴한다.(true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }


    //계정이 갖고있는 권한 목록을 리턴한다.(권한이 여러개있을 수 있어서 루프를 돌아야하는데 우리는 한개만) for문
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();
        //arraylist 부모는 colelction 이다.
        //부모객체로 자식객체를 가르킬수있다.?OO
        //자식객체로 부모객체를 가르킬수있다.?XX 다형성 :참조변수가 사용할수 있는 맴버의 갯수 <= 인스턴스 맴버갯수

        /** 자바는 메서드 넣을수없음
        collectors.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_"+user.getRole(); //ROLE_USER리턴  prefix가 꼭필요하다.
            }
        });
         */
        //자바는 메서드 넣을수없음 object를 다룰순있지만
            //1.8 람다식표현
        collectors.add(() -> {
            return "ROLE_" + user.getRole(); //ROLE_USER리턴  prefix가 꼭필요하다.
        });

        return collectors;
    }
}
