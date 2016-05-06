<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JBlog</title>
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
<div id="container">
  <c:import url="/WEB-INF/jblog/views/include/blog-header.jsp"/>
  <div id="wrapper">
    <div id="content" class="full-screen">
      <c:import url="/WEB-INF/jblog/views/include/blog-admin-header.jsp"/>
      <div class="center-content">
        <p class="welcome-message">
          <span> 감사합니다. 글이 성공적으로 작성되었습니다.</span>
          <a href="${
          pageContext.request.contextPath}">글 보러 가기</a>
      </div>
      </p>
    </div>
  </div>
  <c:import url="/WEB-INF/jblog/views/include/footer.jsp"/>
</div>
</body>
</html>