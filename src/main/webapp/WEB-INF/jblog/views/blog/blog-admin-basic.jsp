<%@ page import="jblog.config.Config" %>
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
        href="${
        pageContext.request.contextPath}/assets/css/jblog.css">
  <script type="text/javascript"
          src="${
          pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js">
  </script>
  <script type="text/javascript">
    function previewImg(input) {
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(event) {
          $("#logo-img").attr("src", event.target.result);
        }
        reader.readAsDataURL(input.files[0]);
      }
    }
    function defaultIMG(input) {
      if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function() {
          $("#logo-img").attr("src",
            "http://www.rwn.co.kr/news/photo/201405/23271_4367_3147.jpg");
        }
        reader.readAsDataURL(input.files[0]);
      }
    }

  </script>
</head>
<body>
<div id="container">
  <c:import url="/WEB-INF/jblog/views/include/blog-header.jsp"/>
  <div id="wrapper">
    <div id="content" class="full-screen">
      <c:import url="/WEB-INF/jblog/views/include/blog-admin-header.jsp"/>
      <form id="upload-form"
            action="${
            pageContext.request.contextPath}/${authUser.id}/changeSettings"
            enctype="multipart/form-data"
            method="post">
        <table class="admin-config">
          <tr>
            <td class="t">블로그 제목</td>
            <td><input type="text" size="40" name="blog-name"
                       placeholder="${blogVo.blogName}"/></td>
          </tr>
          <tr>
            <td class="t">로고이미지</td>
            <td><img id="logo-img" src="${blogVo.img}"/>
            </td>
          </tr>
          <tr>
            <td class="t">&nbsp;</td>
            <td>
              <%--<input type="checkbox" name="default-image" value="true"/>--%>
              <a href="${
              pageContext.request.contextPath}/${
              authUser.id}/changeDefault">초기 설정으로 변경
                <input type="hidden"
                       name="logo-file"
                       onchange="defaultIMG(this);"/>
              </a>
              <input type="file"
                     name="logo-file"
                     onchange="previewImg(this);"/>
            </td>
          </tr>
          <tr>
            <td class="t">&nbsp;</td>
            <td class="s"><input type="submit" value="설정 변경"/></td>
          </tr>
        </table>
      </form>
    </div>
  </div>
  <c:import url="/WEB-INF/jblog/views/include/footer.jsp"/>
</div>
</body>
</html>