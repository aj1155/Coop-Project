<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>dynamicSQL</h2>
<hr/>

<table class="table table-bordered">
	<thead>
		<tr>
			<th>id</th>
			<th>loginId</th>
			<th>name</th>
			<th>email</th>
			<th>userType</th>
			<th>department</th>
		</tr>
	</thead>
	<tbody>
		<tr><td colspan="6">list1</td></tr>
		<c:forEach var="user" items="${list1 }">
			<tr>
				<td>${user.id }</td>
				<td>${user.loginId }</td>
				<td>${user.name }</td>
				<td>${user.email }</td>
				<td>${user.userType }</td>
				<td>${user.departmentName }</td>
			</tr>
		</c:forEach>
		<tr><td colspan="6">list2</td></tr>
		<c:forEach var="user" items="${list2}">
			<tr>
				<td>${user.id }</td>
				<td>${user.loginId }</td>
				<td>${user.email }</td>
				<td>${user.userType }</td>
				<td>${user.departmentName}</td>
			</tr>
		</c:forEach>
		<tr><td colspan="6">list3</td></tr>
		<c:forEach var="user" items="${list3 }">
			<tr>
				<td>${user.id }</td>
				<td>${user.loginId }</td>
				<td>${user.name }</td>
				<td>${user.email }</td>
				<td>${user.userType }</td>
				<td>${user.departmentName }</td>
			</tr>
		</c:forEach>
		        <tr><td colspan="6">list4</td></tr>
        <c:forEach var="user" items="${ list4 }">
            <tr>
                <td>${ user.id }</td>
                <td>${ user.loginId }</td>
                <td>${ user.name }</td>
                <td>${ user.email }</td>
                <td>${ user.userType }</td>
                <td>${ user.departmentName }</td>
            </tr>
        </c:forEach>
		
	</tbody>
</table>