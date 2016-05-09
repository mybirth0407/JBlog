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
        param.category_no eq '' ||
        empty param.category_no}">
        <h1 style="color: red">최근 포스트</h1>
        <c:if test="${empty postVo}">
          <h1>최근 포스팅을 하지 않았습니다.</h1>
        </c:if>
        <h2>${postVo.title}</h2>
        <p>${postVo.content}<p>
        </c:when>
        <c:otherwise>
        <h2>${postVo.title}</h2>
        <p>${postVo.content}<p>
        </c:otherwise>
        </c:choose>
        <c:if test="${
        authUser.id eq blogVo.userID &&
        not empty postVo}">
        <strong><a href="${
        pageContext.request.contextPath}/${
        authUser.id}/delete?category_no=${
        postVo.categoryNo}&post_no=${
        postVo.no}">글 삭제</a></strong>
        </c:if>
      </div>
      <ul class="blog-list">
        <c:choose>
          <c:when test="${postList.size() eq 0}">
            <h1 style="color: red">최근 포스트</h1>
            <h1>최근 포스팅을 하지 않았습니다.</h1>
          </c:when>
          <c:otherwise>
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
          </c:otherwise>
        </c:choose>

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
