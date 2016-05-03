<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JBlog</title>
  <Link rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
<div class="center-content">
  <h1 class="logo">JBlog</h1>
  <c:import url="/WEB-INF/jblog/views/include/header.jsp"/>
  <form class="join-form" id="join-form" method="post"
        action="/jblog/user/join">
    <label class="block-label" for="name">이름</label>
    <input id="name" name="name" type="text"
           value="" placeholder="이름 써라">

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
    <input id="blog-id" name="id" type="text" placeholder="아이디 써라">
    <input id="btn-checkemail" type="button" value="id 중복체크">
    <img id="img-checkemail" style="display: none;"
         src="${pageContext.request.contextPath}/assets/images/check.png">

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
           placeholder="비밀번호 써라">

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
      <input id="agree-prov" type="checkbox" name="agreeProv" value="y">
      <label class="l-float">서비스 약관에 동의합니다.</label>
    </fieldset>

    <input type="submit" value="가입하기">
  </form>
</div>
</body>
</html>
