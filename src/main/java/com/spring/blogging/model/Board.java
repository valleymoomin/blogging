package com.spring.blogging.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨. 용량이 커짐


    private int count; //조회수

//    private int userId;
    @ManyToOne(fetch = FetchType.EAGER) //Many = board, One= user  한명의 유저는 여러개의 게시글을 쓸수있다.
    @JoinColumn(name="userId")
    private User user;//DB는 오브젝트를 저장할 수 없다. FK,자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //하나의 게시글은 여러개의 답변을 가질 수 있다.
    //mappedBy 연관관계의 주인이 아니다. (난FK가 아니에요) DB에 컬럼을 만들지 마세요.
//    @JoinColumn(name="replyId")
    private List<Reply> reply;       //select 하기위해서 있는거임. DB에 들어가있는 값이 아니다.

    @CreationTimestamp
    private Timestamp createDate;
}
