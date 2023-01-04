package com.spring.blogging.repository;

import com.spring.blogging.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


//DAO
//자동으로 bean등록이 된다.
//@Repository 생략가능하다.
public interface UserRepository extends JpaRepository<User,Integer> {
    //로그인을 위한 함수
    //=1.=JPA Naming 전략 (Naming쿼리)
    //select * from user where username=?1 and password =?2;
//    User findByUsernameAndPassword(String username, String password);

/*    =2.=
    @Query(value="select * from user where username=?1 and password=?2", nativeQuery = true)
    User login(String username, String password);
*/


    //네이밍쿼리
    //select * from user where name =1?    findBy ***
    Optional<User> findByUsername(String username);
}
