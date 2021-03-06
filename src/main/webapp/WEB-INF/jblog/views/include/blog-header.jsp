<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="header">
  <h1><a href="${
  pageContext.request.contextPath}/${
  blogVo.userID}/blog-main">${
    blogVo.blogName}</a></h1>
  <ul>
    <c:choose>
      <c:when test="${empty authUser}">
        <li><a href="${
          pageContext.request.contextPath}/loginform">로그인</a></li>
        <li><a href="${
          pageContext.request.contextPath}/joinform">회원가입</a></li>
      </c:when>
      <c:otherwise>
        <li><a href="${
          pageContext.request.contextPath}/logout">로그아웃</a>
        </li>
        <c:if test="${authUser.id eq blogVo.userID}">
          <li><a href="${
          pageContext.request.contextPath}/${
          authUser.id}/blog-admin-basic">블로그 관리</a></li>
        </c:if>
      </c:otherwise>
    </c:choose>
  </ul>
</div>