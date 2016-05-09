<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <title>JBlog</title>
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
<div id="container">
  <c:import url="/WEB-INF/jblog/views/include/blog-header.jsp"/>
  <div id="wrapper">
    <div id="content">
      <div class="blog-content">
        <c:choose>
        <c:when test="${
        param.category_no eq '' || empty param.category_no}">
        <strong><h1>최근 포스트</h1></strong>
        <h2>${postVo.title}</h2>
        <p>${postVo.content}<p>
        </c:when>
        <c:otherwise>
        <h1>${postVo.title}</h1>
        <p>${postVo.content}<p>
        </c:otherwise>
        </c:choose>
        <c:if test="${authUser.id eq blogVo.userID}">
        <strong><a href="${
        pageContext.request.contextPath}/${
        authUser.id}/delete?category_no=${
        postVo.categoryNo}&post_no=${
        postVo.no}">글 삭제</a></strong>
        </c:if>
      </div>
      <ul class="blog-list">
        <c:forEach items="${postList}" var="postVo">
          <li>
            <c:set value="${postVo.categoryNo}" var="categoryNo"/>
            <a href="${
            pageContext.request.contextPath}/${
            blogVo.userID}/blog-main?category_no=${
            categoryNo}&post_no=${postVo.no}">${
              postVo.title}</a> <span>${
            postVo.regDate}</span>
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>

  <div id="extra">
    <div class="blog-logo">
      <img
        src="${blogVo.img}">
    </div>
  </div>

  <div id="navigation">
    <h2>카테고리</h2>
    <ul>
      <c:forEach items="${categoryList}" var="categoryVo">
        <li>
          <a href="${
          pageContext.request.contextPath}/${
          blogVo.userID}/blog-main?category_no=${categoryVo.no}">${
            categoryVo.categoryName}</a>
        </li>
      </c:forEach>
    </ul>
  </div>
  <c:import url="/WEB-INF/jblog/views/include/footer.jsp"/>
</div>
</body>
</html>
