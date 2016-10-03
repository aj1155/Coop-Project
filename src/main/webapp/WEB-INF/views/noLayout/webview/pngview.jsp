<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<div class="container">

	<c:if test="${pngs.size()>0}">
                            <c:forEach var="png" items="${ pngs }">
                            	<img id="user_img" src="/Coop/res/FileSave/${file.fileName}/${png.src}" class="avatar" alt="avatar" style="height:500px; width:500px;"/>
                        	</c:forEach>
                        	</c:if>
                        	<c:if test="${showDiff!=null}">
                        	<c:set var="$del" value="$del" />
                        	<c:set var="$add" value="$add" />
                        	<c:set var="$cha" value="$cha" />
                        		<c:forEach var="diff" items="${showDiff}">
                        		<c:choose>
                        			<c:when test="${diff.startsWith($del)}">
                            		<p style="color:red">${diff.replace($del,"")}</p>
                            		</c:when>
                            		<c:when test="${diff.startsWith($add)}">
                            		<p style="color:blue">${diff.replace($add,"")}</p>
                            		</c:when>
                            		<c:otherwise>
                            		<p>${diff}</p>
                            		</c:otherwise>
      							</c:choose>
                        		</c:forEach>
                        	</c:if>
    
</div>
