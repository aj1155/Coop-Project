<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이것은 제목</title>
<script>
	$(function(){
		$("#new_pro").click(function(){
			location.href = "/Coop/project/"+$("#user_id").val()+"/create.do";
		});
		 $("li[data-url]").click(function() {
		        location.href = $(this).attr("data-url");
		 });
		 $("img").click(function() {
		        location.href = "/Coop/user/"+$("#user_id").val()+"/profile.do";
		 });
		 
		
		
	});
</script>
<style>
img:hover{
	cursor:pointer;
}
ul.mylist li, ol.mylist li {
	cursor:pointer;
	list-style: none;   
    margin-bottom: 5px; 
    font-size: 12px;
    display:inline-block;
}
</style>
</head>
<body>
<sec:authorize access="authenticated">
  <div class="row">
    <!-- left column -->
    <div class="col-md-4 col-sm-6 col-xs-12">
      <div class="column one-fourth">
      <c:if test="${user.img!=null}">
   			 <img id="user_img" src="/Coop/res/images/${user.id}.jpg" class="avatar img-circle" alt="avatar" style="height:230px; width:230px;"/>
	  </c:if>
      <c:if test="${user.img==null}">
      		 <img id="user_img" src="/Coop/res/images/null.jpg" class="avatar img-circle" alt="avatar" style="height:230px; width:230px;"/>
      </c:if>
      <input id="user_id" type="hidden" value="<sec:authentication property="user.id" />">
      <div><h1><sec:authentication property="user.id" /></h1></div>
      <hr/>
      <div><h3><sec:authentication property="user.name" /></h3></div>
      <div></div>
      
      </div>
    </div>
    <!-- edit form column -->
    <div class="col-md-8 col-sm-6 col-xs-12 personal-info">
    	<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#Overview"><i class="fa fa-eye" aria-hidden="true"></i> Overview</a></li>
  <li><a data-toggle="tab" href="#Project"><i class="fa fa-users" aria-hidden="true"></i> Project</a></li>
  <li><a data-toggle="tab" href="#Public"><i class="fa fa-line-chart" aria-hidden="true"></i> Public</a></li>
</ul>

<div class="tab-content">
  <div id="Overview" class="tab-pane fade in active">
    
    <h3>Overview</h3>
    <p>My Recently Actvity</p>
  </div>
  <div id="Project" class="tab-pane fade">
   <div class="filter-bar">
    <form accept-charset="UTF-8" action="/search" class="d-inline" method="get" role="search">
    <!--<input type="hidden" name="user" value="<sec:authentication property="user.name" />">-->
    <input type="text" id="your-repos-filter" name="q" style="width:200px;height:30px;" class="form-control js-filterable-field" placeholder="Find a repository&hellip;" aria-label="Filter your repositories by name" >
    <input type="submit" style="margin-bottom:10px;" value="Search" class="btn">
	<input type="button" id="new_pro" style="margin-bottom:10px;" value="New" class="btn pull-right">
	</form>
	
    </div>
    <hr/>
    <ul class="mylist">
    <c:forEach var="project" items="${ ProjectList }">
   		 <span><i class="fa fa-users" aria-hidden="true"></i></span>&nbsp&nbsp<li id="pList" data-url="/Coop/project/${project.id}/proInfo.do"><h5> ${project.name}</h5></li> 
   		 <hr/>        
    </c:forEach>
    </ul>
  </div>
  <div id="Public" class="tab-pane fade">
    <h3>Public activity</h3>
    <p>Some content in menu 2.</p>
  </div>
  </div>
  
</div> 
    </div>
 


</sec:authorize>
</body>
</html>
