<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link rel="stylesheet" href="/Coop/res/css/inviteInfo.css" type='text/css'>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>

$(function(){
	$("a#accept").click(function() {
		alert("Welcome");
        location.href = "/Coop/invite/"+$('#pid').val()+"/"+$(this).text()+"/result.do";
 	});
	$("a#refuse").click(function() {
		alert("Thank you");
        location.href = "/Coop/layout/main/home.do";
 	});
});

</script>
<header>
<input id="pid" type="hidden" value="${project.id }"/>
        <div class="container">
            <div class="intro-text">
                <div class="intro-lead-in">Welcome To Our ${project.name}!</div>
                <div class="intro-heading">It's Nice To Meet You</div>
                <div class="intro-heading">${user.name } is wating for you</div>
                <a href="#services" id="accept" class="page-scroll btn btn-xl">ACCEPT</a>
                <a href="#services" id="refuse" class="page-scroll btn btn-xl">REFUSE</a>
            </div>
        </div>
</header>