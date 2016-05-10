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
        href="${
        pageContext.request.contextPath}/assets/css/jblog.css">
  <link rel="stylesheet"
        href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script type="text/javascript"
          src="${
          pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js">
  </script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <script type="text/javascript">

    i = 0;
    var getCategoryList = function() {
      i = 0;
      $.ajax({
        url: "${
        pageContext.request.contextPath}/${
        authUser.id}/category-list",
        type: "get",
        dataType: "json",
        data: "",

        success: function(response) {
          if (response.result != "success") {
            return;
          }
          else if (response.data.length == 0) {
            return;
          }

          var listLength = response.data.length;
          $.each(response.data, function(index, vo) {
            $("#category-list").append(renderHTML(vo));
            listLength--;
          });
        },

        error: function(xhr, status, error) {
          console.error(status + ": " + error);
        }
      });
    };


    var renderHTML = function(vo) {
      var html =
        "<tr class='category-list'>" +
        "<td>" + (i = i + 1) + "</td>" +
        "<td>" + vo.categoryName + "</td>" +
        "<td>" + vo.posting + "</td>" +
        "<td>" + vo.description + "</td>" +
        "<td><a href='#' id='del' data-no='" + vo.no + "'>" +
        "<img src='${
        pageContext.request.contextPath}/assets/images/delete.jpg'>" +
        "</td>" + "</tr>";
      return html;
    };

    $(function() {
      $(document).on("click", "#del", function() {
        var categoryNo = $(this).attr("data-no");
        console.log(categoryNo);
        $.ajax({
          url: "${
          pageContext.request.contextPath}/category-delete",
          type: "get",
          dataType: "json",
          data: "category_no=" + categoryNo,

          success: function(response) {
            if (response.result != "success") {
              console.log("1");
              return;
            }

            $(".category-list").remove();
            getCategoryList();
          },

          error: function(xhr, status, error) {
              console.log("2");
            console.error(status + ":" + error);
          }
        });
      });

      $("#insert-category").on("click", function() {
        var categoryName = $("#category-name").val();
        var categoryDescription = $("#category-desc").val();

        if (categoryName == "") {
          return;
        }
        else if (categoryDescription == "") {
          return;
        }

        $.ajax({
          url: "${
          pageContext.request.contextPath}/${
          authUser.id}/category-insert",
          type: "post",
          dataType: "json",
          data: "categoryName=" + categoryName +
          "&description=" + categoryDescription,

          success: function(response) {
            if (response.result != "success") {
              return;
            }
            else if (response.data == null) {
              return;
            }

            $(".category-list").remove();
            getCategoryList();
          },
          error: function(jqXHR, status, error) {
            console.error(status + ":" + error);
          }
        });
      });
      getCategoryList();
    });

  </script>
</head>
<body>
<div id="container">
  <c:import url="/WEB-INF/jblog/views/include/blog-header.jsp"/>
  <div id="wrapper">
    <div id="content" class="full-screen">
      <c:import url="/WEB-INF/jblog/views/include/blog-admin-header.jsp"/>
      <table id="category-list" class="admin-cat">
        <tr>
          <th>번호</th>
          <th>카테고리명</th>
          <th>포스트 수</th>
          <th>설명</th>
          <th>삭제</th>
        </tr>
        <%--ajax 구현시 더 이상 필요하지 않음 --%>
        <%--<c:set var="i" value="0"/>--%>
        <%--<c:forEach items="${categoryList}" var="categoryVo">--%>
          <%--<tr id="tr-${categoryVo.no}">--%>
            <%--<td>${i = i + 1}</td>--%>
            <%--<td>${categoryVo.categoryName}</td>--%>
            <%--<td>${categoryVo.posting}</td>--%>
            <%--<td>${categoryVo.description}</td>--%>
            <%--<td>--%>
              <%--<a href="${--%>
              <%--pageContext.request.contextPath}/category-delete/${--%>
              <%--categoryVo.no}">--%>
                <%--<img src="${--%>
                <%--pageContext.request.contextPath}/assets/images/delete.jpg"/>--%>
              <%--</a>--%>
            <%--</td>--%>
          <%--</tr>--%>
        <%--</c:forEach>--%>
      </table>
      <h4 class="n-c">새로운 카테고리 추가</h4>
      <%--ajax 구현시 더 이상 필요하지 않음 --%>
      <%--<form id="category-insert" method="post"--%>
            <%--action="${--%>
            <%--pageContext.request.contextPath}/${--%>
            <%--authUser.id}/category-insert">--%>
        <table id="admin-cat-add">
          <tr>
            <td class="t">카테고리명</td>
            <td><input type="text"
                       name="categoryName"
                       id="category-name"/></td>
          </tr>
          <tr>
            <td class="t">설명</td>
            <td><input type="text"
                       name="description"
                       id="category-desc"/></td>
          </tr>
          <tr>
            <td class="s">&nbsp;</td>
            <td><input type="submit"
                       value="카테고리 추가"
                       id="insert-category"/></td>
          </tr>
        </table>
      </form>
    </div>
  </div>
  <c:import url="/WEB-INF/jblog/views/include/footer.jsp"/>
</div>
</body>
</html>