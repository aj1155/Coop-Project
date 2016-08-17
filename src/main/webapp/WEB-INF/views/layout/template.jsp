<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <tiles:insertAttribute name="head" />
    <style>
    	
    </style>
</head>
<body>

<div>
    <tiles:insertAttribute name="menu" />
    <div id="laybody" class="container" style="margin-top:15px;">
        <tiles:insertAttribute name="content" />
		
		<c:if test="${ not empty errorMsg }">
            <div class="alert alert-error">${ errorMsg }</div>
        </c:if>
        <c:if test="${ not empty successMsg }">
            <div class="alert alert-success">${ successMsg }</div>
        </c:if>
		
        <tiles:insertAttribute name="footer" />
     </div>
</div>
</body>
</html>
