let index= {
    init: function () {
        $("#btn-save").on("click", ()=>{ // function(){}, () =>{} this를 바인딩하기 위해서!
            this.save();
        });
/*        $("#btn-login").on("click", ()=>{ // function(){}, () =>{} this를 바인딩하기 위해서!
            this.login();
        });*/
    },
    save: function(){
        // alert('user의 save함수 호출됨');
        let data ={
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()

        };

        // console.log(data); //자바스크립트 오브젝트
        // console.log(JSON.stringify(data)); //JSON문자열
        //ajax요청
        //ajax호출시 default가 비동기 호출
        //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
        //ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바?오브젝트로 변환해주네요.
        $.ajax({
            //회원 가입 수행 요청 (100초 가정)
            type: "POST",
            url:"/auth/joinProc", //js object -> json
            data:JSON.stringify(data), //json문자열 , http body데이터
            contentType:"application/json; charset=utf-8", //body데이터가 어떤타입인지(MIME)
            dataType:"json" //요청을 서버로 해서 응답이 왔을때 기본적으로 모든것이 문자열 (생긴게json이라면)
                            //-> javascript오브젝트로 변경
        }).done(function(resp){
            alert("회원가입이 완료되었습니다.");
            console.log(resp);
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });



    }

/*    login: function(){
        // alert('user의 save함수 호출됨');
        let data ={
            username:$("#username").val(),
            password:$("#password").val()


        };

        $.ajax({
            //회원 가입 수행 요청 (100초 가정)
            type: "POST",
            url:"/api/user/login", //js object -> json
            data:JSON.stringify(data), //json문자열 , http body데이터
            contentType:"application/json; charset=utf-8", //body데이터가 어떤타입인지(MIME)
            dataType:"json" //요청을 서버로 해서 응답이 왔을때 기본적으로 모든것이 문자열 (생긴게json이라면)
                            //-> javascript오브젝트로 변경
        }).done(function(resp){
            alert("로그인이 완료되었습니다.");
            console.log(resp);
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });



    }*/
}

index.init();