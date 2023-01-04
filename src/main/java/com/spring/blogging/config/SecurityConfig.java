package com.spring.blogging.config;

import com.spring.blogging.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//빈 등록 : 스프링컨테이너에서 객체를 관리할 수 있게 하는것.

@Configuration //빈등록(IoC관리)
@EnableWebSecurity //필터를 거는것, 시큐리티 필터추가 = 스프링 시큐리티가 활성화가 되어있는데, 어떤 설정을 해당 파일에서 하겠다.
//시큐리티 필터가 등록이 된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean //IoC가 되요!! 리턴하는 값을 스프링이 관리한다.
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PrincipalDetailService principalDetailService;




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //csrf토큰 비활성화 (테스트시 걸어두는게 좋음)
                .authorizeRequests() //request가 들어오면
                    .antMatchers("/", "/auth/**","/js/**","/css/**","/image/**", "/dummy/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated() //다른 모든요청은 인증이되어야해
                .and() //접근제한이 걸렸을때 로그인창이 뜨게?
                   .formLogin()
                  .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/"); //스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인해준다.
                    //정상요청완료가 되면 / 로 이동
    }
    //join form태그값이 아니라  id값으로 ajax요청 javascript
    // csrf토큰이 없기때문에 시큐리티가 막아버린다. 요청시 csrf토근이 없으면 막아버린다.
    //테스트가 끝나면 csrf날리면서 request요청을 하면 된다.

    //로그인할때 사용자가 요청한 username password를 가로채서 로그인
    //내가 만들어야 될 class userdetails를 가지고 있는 userobject (userdetails타입이어야한다.)


    /**
     *     //시큐리티가 대신 로그인 해주는데 password를 가로채기하는데
     *     //해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
     *     //같은 해쉬로 암호화해서 DB에있는 해쉬랑 비교할 수 있다.     *
     */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
        //null자리에있는 object에게 알려줘야한다.
        //실제 로그인 짖ㄴ
        //패스워드 비교
    }
}
