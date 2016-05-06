<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1 class="logo" onclick="location.href='${
  pageContext.request.contextPath}/main'">JBlog</h1>
<ul class="menu">
  <c:choose>
  <c:when test="${empty authUser}">
    <li><a href="${
    pageContext.request.contextPath}/loginform">로그인</a></li>
    <li><a href="${
    pageContext.request.contextPath}/joinform">회원가입</a></li>
  </c:when>
  <c:otherwise>
  <li><a href="${
    pageContext.request.contextPath}/logout">로그아웃</a></li>
  <li><a href="${
    pageContext.request.contextPath}/${
    authUser.id}/blog-main?category-no&post-no">내블로그
    </c:otherwise>
    </c:choose>
  </a></li>
</ul>


