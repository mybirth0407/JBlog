<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>JBlog</title>
  <link rel="stylesheet"
        href="${
        pageContext.request.contextPath}/assets/css/jblog.css"/>
  <script type="text/javascript"
          src="${
          pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js">
  </script>
  <script type="text/javascript">
    $(function() {
      $("#join-form").submit(function() {
        /* 이름 유효성 검사 */
        if ($("#name").val() == "") {
          alert("이름은 필수 요소다!");
          $("#name").focus();
          return false;
        }

        /* 아이디 체크 */
        if ($("#blog-id").val() == "") {
          alert("아이디는 필수 요소다!");
          $("#blog-id").focus();
          return false;
        }

        /* 아이디 중복 체크 */
        if ($("#img-checkID").is(":visible") == false) {
          alert("아이디는 중복 체크를 하렴");
          return false;
        }

        /* 비밀번호 유효성 체크 */
        if ($("#passwd").val() == "") {
          alert("비밀번호는 필수 요소다!");
          return false;
        }

        /* 약관 체크, 제이쿼리 isChecked */
        if ($("#agree-prov").is(":checked") == false) {
          alert("약관 동의해라");
          return false;
        }

        return true;
      });

      $("#blog-id").change(function() {
        $("#btn-checkID").show();
        $("#img-checkID").hide();
      });

      $("#btn-checkID").click(function() {
        var id = $("#blog-id").val();
        if (id == "") {
          return;
        }
        console.log(id);
        $.ajax({
          url: "${pageContext.request.contextPath}/checkID?" +
            "blog-id=" + id,
          type: "get", // 통신방식 get/post 둘중 하나
          dataType: "json", // 수신 데이터 타입
          data: "", //post방식인 경우 서버에 전달할 파라미터 데이터
          // ex) a=checkemail&email=tyranosaurus@nate.com
          // contentType:"application/json"
          // 보내는 데이터가 JSON형식인 경우,
          // 반드시 post방식인 경우로 보내야함
          // ex)data 부분 : "{"a":"checkemail", email:afsdf@naver.com"}"
          // 성공시 callback
          success: function(response) {
            console.log(response);
            if (response.result != "success") {
              return;
            }

            if (response.data == false) {
              alert("이미 존재하는 아이디다!");
              $("#blog-id").val("").focus();
              return;
            }

            $("#btn-checkID").hide();
            $("#img-checkID").show();
          },
          // 실패시 callback
          error: function(jqXHR, status, error) {
            console.error(status + ":" + error);
          }
        });
      });
    });
  </script>
</head>
<body>
<div class="center-content">
  <c:import url="/WEB-INF/jblog/views/include/main-header.jsp"/>
  <form class="join-form" id="join-form" method="post"
        action="${pageContext.request.contextPath}/join">
    <label class="block-label" for="name">이름</label>
    <input id="name" name="name" type="text"
           value="" placeholder="이름 써라"/>

    <spring:hasBindErrors name="userVo">
      <c:if test="${errors.hasFieldErrors('name')}">
        <br>
        <strong style="color:cadetblue">
          <c:set var="errorName"
                 value="${errors.getFieldError('name').codes[0]}"/>
          <spring:message
            code="${errorName}"
            text="${errors.getFieldError('name').defaultMessage}"/>
        </strong>
      </c:if>
    </spring:hasBindErrors>

    <label class="block-label" for="blog-id">아이디</label>
    <input id="blog-id" name="id" type="text" placeholder="아이디 써라"/>
    <input id="btn-checkID" type="button" value="id 중복체크"/>
    <img id="img-checkID" style="display: none;"
         src="${pageContext.request.contextPath}/assets/images/check.png"/>

    <spring:hasBindErrors name="userVo">
      <c:if test="${errors.hasFieldErrors('id')}">
        <br>
        <strong style="color:darkolivegreen">
          <c:set var="errorID"
                 value="${errors.getFieldError('id').codes[0]}"/>
          <spring:message
            code="${errorID}"
            text="${errors.getFieldError('id').defaultMessage}"/>
        </strong>
      </c:if>
    </spring:hasBindErrors>

    <label class="block-label" for="passwd">비밀번호</label>
    <input id="passwd" name="passwd" type="password"
           placeholder="비밀번호 써라"/>

    <spring:hasBindErrors name="userVo">
      <c:if test="${errors.hasFieldErrors('passwd')}">
        <br>
        <strong style="color:blueviolet">
          <c:set var="errorPasswd"
                 value="${errors.getFieldError('passwd').codes[0]}"/>
          <spring:message
            code="${errorPasswd}"
            text="${errors.getFieldError('passwd').defaultMessage}"/>
        </strong>
      </c:if>
    </spring:hasBindErrors>

    <fieldset>
      <legend>약관동의</legend>
      <input id="agree-prov" type="checkbox" name="agreeProv" value="y"/>
      <label class="l-float">서비스 약관에 동의합니다.</label>
    </fieldset>
    <input type="submit" value="가입하기"/>
  </form>
</div>
</body>
</html>
