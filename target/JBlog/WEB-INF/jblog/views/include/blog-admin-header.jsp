<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul class="admin-menu">
  <li class="selected"><a href="${
  pageContext.request.contextPath}/${
  authUser.id}/blog-admin-basic">기본설정</a></li>
  <li><a href="${
  pageContext.request.contextPath}/${
  authUser.id}/blog-admin-category">카테고리</a></li>
  <li><a href="${
  pageContext.request.contextPath}/${
  authUser.id}/blog-admin-write">글 작성</a></li>
</ul>