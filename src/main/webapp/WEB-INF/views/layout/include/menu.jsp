<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<div class="navbar navbar-static-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="/Coop/layout/main/home.do"><img src="/Coop/res/images/logo.png"></a>
            
                <ul class="nav">
                    <li><a href="">MyTeam</a>
                    <li><a href="">Me</a>
                </ul>
                
                <ul class="nav pull-right">
                    <sec:authorize access="not authenticated">
                        <li><a href="">join</a></li>
                    </sec:authorize>
                    <sec:authorize access="authenticated">
                       	<li class="dropdown">
                		<a href="#" class="badge1 dropdown-toggle" data-toggle="dropdown" data-badge="${inviteList[0].rowCount }">
                			Request<b class="caret"></b></a>
                		<ul class="dropdown-menu">
                			<li><a href="">MyProfile</a>
                			<li><a href="">Repository</a></li>
                			<li><a href="">Help</a></li>
                		</ul>
                	</li>
                        <li><a href="/Coop/home/logout.do">logout</a></li>
                    </sec:authorize>
                </ul>
            
        </div>
    </div>
</div>
