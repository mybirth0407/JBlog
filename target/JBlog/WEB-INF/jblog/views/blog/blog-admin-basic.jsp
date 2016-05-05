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
      <form action="" method="post">
        <table class="admin-config">
          <tr>
            <td class="t">블로그 제목</td>
            <td><input type="text" size="40" name="title"></td>
          </tr>
          <tr>
            <td class="t">로고이미지</td>
            <td><img
              src="${
              pageContext.request.contextPath}/assets/images/spring-logo.jpg">
            </td>
          </tr>
          <tr>
            <td class="t">&nbsp;</td>
            <td><input type="file" name="logo-file"></td>
          </tr>
          <tr>
            <td class="t">&nbsp;</td>
            <td class="s"><input type="submit" value="기본설정 변경"></td>
          </tr>
        </table>
      </form>
    </div>
  </div>
  <c:import url="/WEB-INF/jblog/views/include/footer.jsp"/>
</div>
</body>
</html>