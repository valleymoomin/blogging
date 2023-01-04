package com.spring.blogging.service;

import com.spring.blogging.config.auth.PrincipalDetail;
import com.spring.blogging.model.Board;
import com.spring.blogging.model.RoleType;
import com.spring.blogging.model.User;
import com.spring.blogging.repository.BoardRepository;
import com.spring.blogging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌. IoC를 해준다.
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    @Transactional
    public void 글쓰기(Board board, User user) { //title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);

    }

   /* @Transactional(readOnly = true) // select 할때 트랜잭션 시작, 서비스종료시에 트랜잭션 종료(정합성 유지)
    public User 로그인(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/


    @Transactional(readOnly = true)// readonly true: select 할때 트랜잭션 시작, 서비스종료시에 트랜잭션 종료(정합성 유지)
    public Page<Board> 글목록(Pageable pageable) {
        return  boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(int id) {
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
                });
    }

    @Transactional
    public void 글삭제하기(int id, PrincipalDetail principal) {
//        System.out.println("글삭제하기: "+id);
//        boardRepository.deleteById(id);

        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 찾기 실패 : 해당 글이 존재하지 않습니다.");
        });

        if (board.getUser().getId() != principal.getUser().getId()) {
            throw new IllegalStateException("글 삭제 실패 : 해당 글을 삭제할 권한이 없습니다.");
        }
        boardRepository.delete(board);
    }

    @Transactional
    public void 글수정하기(int id, Board requestBoard) {
    //글 수정하려면 영속화가 먼저 필요하다.
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
        }); //영속화 완료 DB table data랑 동기화가 되었다.

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        //해당 함수 종료시에 (Servbice가 종료될 때) 트랜잭션이 종료됩니다. 이때 더티체킹 -> 자동 업데이트가 됨 db쪽으로 flush
        //영속화 되어있는게 내용이달라짐 더티체킹
    }


}
