package com.spring.blogging.controller;

import com.spring.blogging.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    /*
//@AuthenticationPrincipal PrincipalDetail principal
    @GetMapping({"", "/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal) {

        //컨트롤러에서 세션을 어떻게 찾는지?

        System.out.println("로그인 사용자 아이디:" + principal.getUsername());

        return "index"; // /WEB-INF/views/index.jsp
    }
*/ //컨트롤러에서 세션을 어떻게 찾는지?
    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable) {
        model.addAttribute("boards", boardService.글목록(pageable));
        return "index";// /WEB-INF/views/index.jsp
    } //viewResolver 작동~!! restcontroller아님
    //model request정보 model에 데이터를 담으면 view까지 데이터(컬렉션)를 끌고 이동


    //USER권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }


    //글 상세보기
    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/detail";
    }

    //글 수정
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/updateForm";
    }

}
