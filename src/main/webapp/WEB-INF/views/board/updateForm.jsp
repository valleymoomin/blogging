<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>


<div class="container">
    <%--    <form action="/api/user/login">--%>
    <%--    <form>--%>
    <form>
        <input type="hidden" id="id" value="${board.id}"/>
        <div class="form-group">
            <label for="title">글제목 :</label>
            <input value="${board.title}" type="text"  class="form-control" placeholder="Enter title" id="title">
        </div>


        <div class="form-group">
            <label for="content">글내용 :</label>
            <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
        </div>



    </form>
    <%--    버튼을 form밖으로 빼는 이유 user.js쓰기위해서?//     form로그인 쓰지않는다.--%>
        <button id="btn-update" class="btn btn-primary">글수정</button>
</div>
<script>
    $('.summernote').summernote({
        tabsize: 2,
        height: 300
    });
</script>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


