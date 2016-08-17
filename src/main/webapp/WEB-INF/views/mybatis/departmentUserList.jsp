<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>학과별 사용자 목록</h2>
<hr />

<c:forEach var="department" items="${ departments }">
    <h3>${ department.departmentName } <small>${ department.users.size() }명</small></h3>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>id</th>
                <th>loingId</th>
                <th>name</th>
                <th>email</th>
                <th>userType</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${ department.users }">
                <tr>
                    <td>${ user.id }</td>
                    <td>${ user.loginId }</td>
                    <td>${ user.name }</td>
                    <td>${ user.email }</td>
                    <td>${ user.userType }</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <hr />
</c:forEach>
