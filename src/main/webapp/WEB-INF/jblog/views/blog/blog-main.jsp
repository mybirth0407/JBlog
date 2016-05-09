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
        <h1>최근 포스트</h1>
        <h4>${postVo.title}</h4>
        <p>${postVo.content}<p>
      </div>
      <ul class="blog-list">
        <c:forEach items="${postList}" var="postVo">
          <li>
            <c:set value="${postVo.categoryNo}" var="categoryNo"/>
            <a href="${
            pageContext.request.contextPath}/${
            blogVo.userID}/blog-main?category-no=${
            categoryNo}&post-no=${postVo.no}">${
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
          blogVo.userID}/blog-main?category-no=${categoryVo.no}">${
          categoryVo.categoryName}</a>
        </li>
      </c:forEach>
    </ul>
  </div>
  <c:import url="/WEB-INF/jblog/views/include/footer.jsp"/>
</div>
</body>
</html>
