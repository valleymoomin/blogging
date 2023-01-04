let index= {
    init: function () {
        $("#btn-save").on("click", ()=>{ // function(){}, () =>{} this를 바인딩하기 위해서!
            this.save();
        });
/*        $("#btn-login").on("click", ()=>{ // function(){}, () =>{} this를 바인딩하기 위해서!
            this.login();
        });*/

        $("#btn-delete").on("click", ()=>{ // function(){}, () =>{} this를 바인딩하기 위해서!
            this.deleteById();
        });
        $("#btn-update").on("click", ()=>{ // function(){}, () =>{} this를 바인딩하기 위해서!
            this.update();
        });
    },
    save: function(){
        // alert('user의 save함수 호출됨');
        let data ={
            title:$("#title").val(),
            content:$("#content").val()

        };


        $.ajax({
            //회원 가입 수행 요청 (100초 가정)
            type: "POST",
            url:"/api/board", //js object -> json
            data:JSON.stringify(data), //json문자열 , http body데이터
            contentType:"application/json; charset=utf-8", //body데이터가 어떤타입인지(MIME)
            dataType:"json" //요청을 서버로 해서 응답이 왔을때 기본적으로 모든것이 문자열 (생긴게json이라면)
                            //-> javascript오브젝트로 변경
        }).done(function(resp){
            alert("글쓰기가 완료되었습니다.");
            console.log(resp);
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });



    },
    deleteById: function(){
        let id = $("#id").text() //${("#id").val()   //text값을 뽑아야되는데 value값을 뽑았다.

        $.ajax({
            //회원 가입 수행 요청 (100초 가정)
            type: "DELETE",
            url:"/api/board/"+id, //js object -> json
            dataType:"json" //요청을 서버로 해서 응답이 왔을때 기본적으로 모든것이 문자열 (생긴게json이라면)
                            //-> javascript오브젝트로 변경
        }).done(function(resp){
            alert("삭제가 완료되었습니다.");
            console.log(resp);
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });



    },
    update: function(){
        let id=$("#id").val();

        let data ={
            title:$("#title").val(),
            content:$("#content").val()

        };


        $.ajax({
            //회원 가입 수행 요청 (100초 가정)
            type: "PUT",
            url:"/api/board/"+id, //js object -> json
            data:JSON.stringify(data), //json문자열 , http body데이터
            contentType:"application/json; charset=utf-8", //body데이터가 어떤타입인지(MIME)
            dataType:"json" //요청을 서버로 해서 응답이 왔을때 기본적으로 모든것이 문자열 (생긴게json이라면)
                            //-> javascript오브젝트로 변경
        }).done(function(resp){
            alert("글수정이 완료되었습니다.");
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