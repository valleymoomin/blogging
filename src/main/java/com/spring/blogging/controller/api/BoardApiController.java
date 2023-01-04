package com.spring.blogging.controller.api;

import com.spring.blogging.config.auth.PrincipalDetail;
import com.spring.blogging.dto.ResponseDto;
import com.spring.blogging.model.Board;
import com.spring.blogging.model.User;
import com.spring.blogging.service.BoardService;
import com.spring.blogging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

/*    @Autowired
    private HttpSession session;*/




    @Autowired
    BoardService boardService;



    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board
                        , @AuthenticationPrincipal PrincipalDetail principal) { //username, password, email




        //실제로 DB에 insert를 하고 아래에서return되면되요.

        boardService.글쓰기(board, principal.getUser());

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        //자바오브젝트를 JSON으로 변환해서 리턴 (Jackson lib)
    }

    //글 삭제
    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글삭제하기(id, principal);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //글 수정
    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.글수정하기(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
