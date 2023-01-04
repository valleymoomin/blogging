package com.spring.blogging.test;

import org.springframework.web.bind.annotation.*;


//사용자가 요청 -> 응답(HTML파일) @Controller
//사용자가 요청 -> 응답(Data)   @RestController

@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest :";

    @GetMapping("/http/lombok")
    public String lombokTest() {
//        Member m = new Member(1, "ssar", "1234", "email");

        Member m = Member.builder().username("ssar").password("1234").email("email").build();
        System.out.println(TAG + "getter:" + m.getUsername());
        m.setUsername("cos");
        System.out.println(TAG + "setter: " + m.getUsername());

        return "lombok test 완료";

    }


    //인터넷 브라우저 요청은 get요청밖에 할 수 없다. ?id=1&username=ssar 쿼리스트링을 통해데이터보낼수있음.
    //http://localhost:3000/http/get (select)
    @GetMapping("/http/get")
    public String getTest(@RequestParam int id, @RequestParam String username) {

        return "get요청 "+id+ ","+username;
    }




    @GetMapping("/http/get2")
    public String getTest2(Member m) {

        return "get요청"+m.getId()+ ","+m.getUsername();
    }

    //insert
    @PostMapping("/http/post") //text/plain, application/json
    public String postTest(@RequestBody Member m) { //messageConverter(스프링부트)

        return "post요청 "+m.getId()+ ","+m.getUsername();
    }

    //update
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put요청"+m.getId()+ ","+m.getUsername();
    }

    //delete
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete요청";
    }
}
