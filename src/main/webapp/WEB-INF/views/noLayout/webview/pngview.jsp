<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<div class="container">

	<c:forEach var="png" items="${ pngs }">
       <img id="user_img" src="/Coop/res/FileSave/${file.fileName}/${png.src}" class="avatar img-circle" alt="avatar" style="height:300px; width:300px;"/>
    </c:forEach>
    
</div>
