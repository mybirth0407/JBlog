<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<% pageContext.setAttribute("newLine", "\r\n"); %>

<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JBlog</title>
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/assets/css/jblog.css">
  <link rel="stylesheet"
        href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script type="text/javascript"
          src="/assets/js/jquery/jquery-1.9.0.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <script type="text/javascript">

    var renderHtml = function(categoryVo) {
      /* 대신에 js template 라이브러리 사용 */
      var html =
        "<li id='li-" + guestBookVo.no + "'><table><tr>" +
        "<td>" + guestBookVo.name + "</td>" +
        "<td>" + guestBookVo.regDate + "</td>" +
        "<td><a href='#' class='a-del' data-no= '" + guestBookVo.no +
        "'>삭제</a></td>" +
        "</tr><tr>" +
        "<td colspan='3'>" +
        categoryVo.description.replace(/\r\n/g, "<br>") +
        "</td></tr></table></li>";
//      $("#gb-list").append(html);
      return html;
    }

    $(function() {
      $("#category-insert").submit(function(event) {
        var name = $("#category-name").val();
        var desc = $("#category-desc").val();

        this.reset();
        event.preventDefault();

        $.ajax({
          url: "${pageContext.request.contextPath}/category/category-insert",
          type: "post",
          data: "category=" + name +
            "&desc=" + desc,
          dataType: "json",

          success: function(response) {
            console.log(response.data);
            $("#admin-cat-add").prepend(renderHtml(response.data));
          },

          error: function(xhr, status, error) {
            console.error(status + ":" + error);
          }

        })

      });
    });
  </script>
</head>
<body>
<div id="container">
  <c:import url="/WEB-INF/jblog/views/include/blog-header.jsp"/>
  <div id="wrapper">
    <div id="content" class="full-screen">
      <c:import url="/WEB-INF/jblog/views/include/blog-admin-header.jsp"/>
      <table class="admin-cat">
        <tr>
          <th>번호</th>
          <th>카테고리명</th>
          <th>포스트 수</th>
          <th>설명</th>
          <th>삭제</th>
        </tr>
        <c:set var="i" value="0"/>
        <c:forEach items="${categoryList}" var="categoryVo">
          <tr>
            <td>${i = i + 1}</td>
            <td>${categoryVo.categoryName}</td>
            <td>${categoryVo.posting}</td>
            <td>${categoryVo.description}</td>
            <td><img
              src="${
              pageContext.request.contextPath}/assets/images/delete.jpg">
            </td>
          </tr>
        </c:forEach>
      </table>

      <h4 class="n-c">새로운 카테고리 추가</h4>
      <form id="category-insert">
        <table id="admin-cat-add">
          <tr>
            <td class="t">카테고리명</td>
            <td><input type="text"
                       name="category-name"
                       id="category-name"></td>
          </tr>
          <tr>
            <td class="t">설명</td>
            <td><input type="text"
                       name="category-desc"
                       id="category-desc"></td>
          </tr>
          <tr>
            <td class="s">&nbsp;</td>
            <td><input type="submit" value="카테고리 추가"></td>
          </tr>
        </table>
      </form>
    </div>
  </div>
  <c:import url="/WEB-INF/jblog/views/include/footer.jsp"/>
</div>
</body>
</html>