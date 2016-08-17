<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<h2>myBatis Cache Test</h2>
<hr />

<hr />
<h3>학과목록</h3>
<table class="table table-bordered" style="width:500px;">
    <c:forEach var="department" items="${ departments }">
        <tr>
            <td>${ department.id }</td>
            <td>${ department.departmentName }</td>
            <td><fmt:formatDate pattern="HH:mm:ss" value="${ department.time }" /></td>
        </tr>
    </c:forEach>
</table>
<a class="btn" href="/bbs1/mybatis/cacheTest.do">새로고침</a>

<hr />
<h3>학과명 수정</h3>
<form:form modelAttribute="department" method="post">
    <form:hidden path="id" />
    <form:input path="departmentName" /> <br />
    <button type="submit" class="btn btn-default">저장</button>
</form:form>

<hr/>
<h3>사용자목록</h3>
<table class="table table-bordered" style="width:500px;">
	<c:forEach var="user" items="${users }">
		<tr>
			<td>${user.id }</td>
			<td>${user.loginId }</td>
			<td>${user.name }</td>
			<td>${user.departmentName }</td>
			<td><fmt:formatDate pattern="HH:mm:ss" value="${ user.time }" /></td>
		</tr>
	</c:forEach>
</table>

			
