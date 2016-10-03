<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<link href='/Coop/res/css/profile.css' rel='stylesheet' type='text/css'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Coop</title>
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
		 
		$('#chartTab').click(function(){
			$.ajax({
	 			url : '/Coop/home/chart.do',
	 			method :'GET',
	 			contentType : 'application/String;charset=UTF-8',
	 			data : {"id" : $("#user_id").val() },
	 			success : function(data){
	 				chartShow(data);
	 			
	 				
	 			},
	 			error : function(data){
	 				console.log(data);
	 				alert("실패");
	 			}
	 		})
			
		});
		/*
		function chartShow(chartData){
			var chart = new CanvasJS.Chart("chartContainer",
					{
						title:{
							text: "Project Contribute"
						},
				                animationEnabled: true,
						legend:{
							verticalAlign: "bottom",
							horizontalAlign: "center"
						},
						data: [
						{        
							indexLabelFontSize: 20,
							indexLabelFontFamily: "Monospace",       
							indexLabelFontColor: "darkgrey", 
							indexLabelLineColor: "darkgrey",        
							indexLabelPlacement: "outside",
							type: "pie",       
							showInLegend: true,
							toolTipContent: "{y} - <strong>#percent%</strong>",
							dataPoints: chartData
								
							
						}
						]
					});
					chart.render();
					*/
					function chartShow(data) {
						var chart = new CanvasJS.Chart("chartContainer",
						{
							animationEnabled: true,
							title:{
								text: "Project Contribute"
							},
							animationEnabled: true,
							data: [
							{
								type: "column", //change type to bar, line, area, pie, etc
								dataPoints: data
							}
							]
							});

						chart.render();
			
		};
		
		
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
      <div><div class="profile-usertitle"  style="margin-right:260px;">
					<div class="profile-usertitle-name">
						${user.name }
					</div>
			
				</div></div>
      <hr/>
    
      <div>
      	
				<!-- END SIDEBAR USER TITLE -->
				<!-- SIDEBAR BUTTONS -->
				
				<!-- END SIDEBAR BUTTONS -->
				<!-- SIDEBAR MENU -->
				<div class="profile-usermenu">
					<ul class="nav">
						<li class="active">
							<a href="#">
							<i class="glyphicon glyphicon-home"></i>
							MyProject </a>
						</li>
						<li>
							<a href="#">
							<i class="glyphicon glyphicon-user"></i>
							Account Settings </a>
						</li>
						<li>
							<a href="#" target="_blank">
							<i class="glyphicon glyphicon-ok"></i>
							Tasks </a>
						</li>
						<li>
							<a href="#">
							<i class="glyphicon glyphicon-flag"></i>
							Message </a>
						</li>
					</ul>
				</div>
				<!-- END MENU -->
      </div>
      
      </div>
    </div>
    <!-- edit form column -->
    <div class="col-md-8 col-sm-6 col-xs-12 personal-info">
    	<ul class="nav nav-tabs">

  	  	 <li class="active"><a data-toggle="tab" href="#Overview"><i class="fa fa-eye" aria-hidden="true"></i> Overview</a></li>
  	  	 <li id="proTab"><a data-toggle="tab" href="#Project"><i class="fa fa-users" aria-hidden="true"></i> Project</a></li>
         <li id="chartTab"><a data-toggle="tab" href="#Public"><i class="fa fa-line-chart" aria-hidden="true"></i> Public</a></li>
		</ul>

<div class="tab-content">
  <div id="Overview" class="tab-pane fade in active">
    
    <h3>Overview</h3>
    <c:if test="${noticeList.size()>0}">
    <ul class="mylist">
    <c:forEach var="notice" items="${noticeList}">
     <span><img id="notice_img" src="/Coop/res/images/paper_plane.png" class="avatar img-circle" alt="avatar" style="height:30px; width:30px;"/></span>&nbsp&nbsp<li id="pList" data-url="/Coop/"><span><strong>${notice.name}</strong></span>&nbsp&nbsp 새로운 게시글
   		  </li>
    	  
   		
	      
   		 
   		 <hr/>        
    </c:forEach>
    </ul>
    </c:if>
  </div>
  <div id="Project" class="tab-pane fade">
   <div class="filter-bar">
    <form accept-charset="UTF-8" action="/Coop/project/search.do" class="d-inline" method="post" role="search">
    <!--<input type="hidden" name="user" value="<sec:authentication property="user.name" />">-->
    <input type="text" id="your-repos-filter" name="search" style="width:200px;height:30px;" class="form-control js-filterable-field" placeholder="Find a repository&hellip;" aria-label="Filter your repositories by name" >
    <input type="submit" style="margin-bottom:10px;" value="Search" class="btn">
	<input type="button" id="new_pro" style="margin-bottom:10px;" value="New" class="btn pull-right btn-success">
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
    <div id="chartContainer" style="height: 300px; width: 100%;"></div>
  </div>
  </div>
  
</div> 
    </div>
 


</sec:authorize>
</body>
</html>
