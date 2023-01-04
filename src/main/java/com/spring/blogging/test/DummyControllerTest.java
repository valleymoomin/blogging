package com.spring.blogging.test;

import com.spring.blogging.model.RoleType;
import com.spring.blogging.model.User;
import com.spring.blogging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

//html 파일이 아니라 data를 리턴해주는 restController
@RestController
public class DummyControllerTest {

    @Autowired //의존성 주입(DI)
    private UserRepository userRepository;

    //select
    //{id}주소로 파라미터 전달 받을 수 있음.
    //http://localhost:3000/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
//        Optional<User> byId = userRepository.findById(id);
        // user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될것아냐?
        // 그럼 return null이 리턴이 되잖아.. 그럼 프로그램에 문제가 있지 않겠니?
        // Optional로 너의 User객체를 감싸서 가져올테니 null인지아닌지 판단해서 return해

        /*User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
            @Override
            public User get() {
                return new User();
            } //인터페이스를 new하려면 익명클래스를 만들어야한다.
        });
        */
        //        User user1 = userRepository.findById(id).get();


        //람다식
        User user1 = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 사용자는 없습니다.");
        });

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다.id:" + id);
            }

        });
        //user객체 = 자바 오브젝트
        //요청: 웹 브라우저
        //변환 (웹 브라우저가 이해할 수 있는 데이터) -> json (Gson라이브러리)
        //스프링 부트 = MessageConverter라는 애가 응답시에 자동 작동
        // 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson라이브러리를 호출해서
        //user오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
        return user;



    }


    //===================================================

    //http://localhost:3000/dummy/user
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    //한 페이지당 2건에 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)
                                       Pageable pageable) {

        Page<User> pagingUser = userRepository.findAll(pageable);


        List<User> users = pagingUser.getContent();

        return pagingUser;
    }

    //==========================================================================

    //save함수는 id를 전달하지 않으면 insert해주고
    //save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
    //save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요.
    //email,password 수정한다. id,username수정안하고
    @Transactional //함수 종료시에 자동 commit됨.
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User requestUser) {
        //json데이터 요청 -> JavaObject(MessageConverter의 Jackson라이브러리가 변환해서 받아줘요.)
        //그때 필요한것이 @RequestBody임.
        System.out.println("id = " + id);
        System.out.println("password = " +requestUser.getPassword());
        System.out.println("email = " + requestUser.getEmail());

   /*     requestUser.setId(id);
        requestUser.setUsername("ssar");
        userRepository.save(requestUser);
        //save 는 insert할때 쓰는것인데, id가있다면 update 쳐준다.
        // but 다른값들이 null이 되버림
        //update시에는 save를 안쓰고.
*/
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        //실제 DB에서 받은 유저
        //자바는 파라미터에 함수를 넣을수없다.
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        userRepository.save(user);    //@Transactional를 걸면 save하지않아도 update가 된다.
        //더티 체킹

        return  user;
    }

    //======================================================================


    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제 되었습니다. id :" +id;
    }







    //==============================================================================




        //http://localhost:3000/dummy/join(요청)
        //http의 body에 username,password,email데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(@RequestParam(value = "username", required = false) String username, String password, String email) { //key=value (약속된 규칙)
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("email = " + email);
        return "회언가입이 완료되었습니다.";
    }
    //key=value null로 값이 받아지는데;;? setter도 있고 그런데 왜 null로 값이받아지나


    @PostMapping("/dummy/join2")
    public String join2(User user) {
        System.out.println("user.getId() = " + user.getId());
        System.out.println("user.getUsername() = " + user.getUsername());
        System.out.println("user.getPassword() = " + user.getPassword());
        System.out.println("user.getEmail() = " + user.getEmail());
        System.out.println("user.getRole() = " + user.getRole());
        System.out.println("user.getCreateDate() = " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
        
    }
}
