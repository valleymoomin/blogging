package com.spring.blogging.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert //null값 default값으로 대체시키기 위해서. -> insert시에 null인 필드를 제외시켜준다.
//ORM -> JAVA object -> table로 바꾸어준다.
@Entity //User클래스가 MySql에 테이블이 생성된다.
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //시퀀스(오라클), auto_increment(mysql)


    @Column(nullable=false,length=30, unique = true)
    private String username;

    @Column(nullable = false, length =100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("'user")    ~@Dynamic insert
    //DB는 RoleType이라는게 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role; //Enum을 쓰는게 좋다. //ADMIN

    @CreationTimestamp
    private Timestamp createDate;
}
