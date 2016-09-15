<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link href='https://fonts.googleapis.com/css?family=Lato:300,400,700' rel='stylesheet' type='text/css'>
<script>
$(function(){
	$('#new_file').click(function(){
		location.href = "/Coop/file/"+${project.id}+"/"+$('#user_id').val()+"/create.do"
	});
	$("li[data-url]").click(function() {
        location.href = $(this).attr("data-url");
 	});
	$("input[data-url]").click(function() {
        location.href = $(this).attr("data-url");
 	});
	
	$('#chartTab').click(function(){
		$.ajax({
 			url : '/Coop/project/chart.do',
 			method :'GET',
 			contentType : 'application/String;charset=UTF-8',
 			data : {"id" : $("#project_id").val() },
 			success : function(data){
 				chartShow(data);
 			
 				
 			},
 			error : function(data){
 				console.log(data);
 				alert("실패");
 			}
 		})
		
	});
	function chartShow(chartData){
		var chart = new CanvasJS.Chart("chartContainer",
				{
					title:{
						text: "Member Contribute"
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
h1{
    margin-bottom: 40px;
}
label {
    color: #333;
}
.btn-send {
    font-weight: 300;
    text-transform: uppercase;
    letter-spacing: 0.1em;
    margin-bottom: 20px;
}
</style>
<sec:authorize access="authenticated">


<h5>${project.owner } / ${project.name }</h5>

<hr/>
<c:set var="userId"><sec:authentication property="user.id" /></c:set>
<input type="hidden" id="user_id" value="<sec:authentication property="user.id" />">
<input type="hidden" id="project_id" value="${project.id}">
<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#work"><i class="fa fa-file-word-o" aria-hidden="true"></i> Work</a></li>
  <li><a data-toggle="tab" href="#problem"><i class="fa fa-users" aria-hidden="true"></i> Member</a></li>
  <li><a data-toggle="tab" href="#request"><i class="fa fa-refresh" aria-hidden="true"></i> Request</a></li>
  <c:if test="${project.owner==userId}">
  <li><a data-toggle="tab" href="#setting"><i class="fa fa-wrench" aria-hidden="true"></i> Settings</a></li>
  </c:if>
  <li id="chartTab"><a data-toggle="tab" href="#Public"><i class="fa fa-line-chart" aria-hidden="true"></i> Public</a></li>
  <c:if test="${project.owner==userId}">
  <li><a data-toggle="tab" href="#invite"><i class="fa fa-american-sign-language-interpreting" aria-hidden="true"></i> invite</a></li>
  </c:if>
</ul>

<div class="tab-content">
  <div id="work" class="tab-pane fade in active">
  	 <div class="filter-bar">
    <form accept-charset="UTF-8" action="/Coop/file/search.do" class="d-inline" method="POST" role="search">
    <!--<input type="hidden" name="user" value="<sec:authentication property="user.name" />">-->
    <input type="text" id="your-repos-filter" name="search" style="width:320px;height:30px;" class="form-control js-filterable-field" placeholder="Find a repository&hellip;" aria-label="Filter your repositories by name" >
    <input type="submit" style="margin-bottom:10px;" value="Search" class="btn">
	<input type="button" id="new_file" style="margin-bottom:10px;" value="Upload File" class="btn pull-right btn-success">
	</form>
	
    </div>
    
    <h3>Work</h3>
    <ul class="mylist">
    <c:forEach var="file" items="${ fileList }">
   		 <span><i class="fa fa-file" aria-hidden="true"></i></span>&nbsp&nbsp<li id="pList" data-url="/Coop/file/${file.id}/${project.id}/detail.do"><h5> ${file.fileName}</h5>
   		 </li>
   		 <c:if test="${file.userId==userId}">
   		 <div class="pull-right action-buttons">
   			  <input id="inv" type="button" class="btn btn-info" value="Delete" data-url="/Coop/file/${project.id}/${file.id}/delete.do"/>
         </div> 
         </c:if> 
   		 <hr/>        
    </c:forEach>
    </ul>
  </div>
  <div id="problem" class="tab-pane fade">
  <ul class="mylist">
  		<c:forEach var="user" items="${pro_user}">
    	  <c:if test="${user.img!=null}">
   			 <span><img id="user_img" src="/Coop/res/images/${user.id}.jpg" class="avatar img-circle" alt="avatar" style="height:50px; width:50px;"/></span>&nbsp&nbsp<li id="pList" data-url="/Coop/"><h5> ${user.name}</h5></li>
   			  <div class="pull-right action-buttons">
   			  		<input id="inv" type="button" class="btn btn-info" value="Message" data-url="/Coop/project/${user.id}/${project.id}/invite.do"/>
              </div> 
	      </c:if>
   		  <c:if test="${user.img==null }">
   		  	 <span><img id="user_img" src="/Coop/res/images/null.jpg" class="avatar img-circle" alt="avatar" style="height:50px; width:50px;"/></span>&nbsp&nbsp<li id="pList" data-url="/Coop/"><h5> ${user.name}</h5></li>
   		  	 <div class="pull-right action-buttons">
                    <input id="inv" type="button" class="btn btn-info" value="Message" data-url="/Coop/project/${user.id}/${project.id}/invite.do"/>       
             </div>  
   		  </c:if>
   		 <hr/>        
    </c:forEach>
  </ul>
  </div>
   
   <div id="request" class="tab-pane fade">
    <h3>Request activity</h3>
    <p>Some content in menu 2.</p>
  </div>
   <div id="setting" class="tab-pane fade"  style="font-family: 'Lato', sans-serif;" >
    	  

           

                <div class="col-lg-8 col-lg-offset-2">

                    <h1>Contact form Tutorial from <a href="http://localhost:6472/Coop/home/index.do">Coop</a></h1>

                    <p class="lead">Modify project detail and set up next Description...</p>


                    <form id="contact-form" method="post" action="/Coop/project/edit.do" role="form">
						<input type="hidden" id="id" name="id" value="${project.id}">
                        <div class="messages"></div>

                        <div class="controls">

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="form_name">ProjectName *</label>
                                        <input id="form_name" type="text" name="name" value="${project.name }"class="form-control" placeholder="Please enter your ProjectName *" required="required" data-error="Firstname is required.">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="form_lastname">Owner *</label>
                                        <input id="form_lastname" type="text" name="owner" value="${project.owner }" class="form-control" placeholder="Please enter Owner *" required="required" data-error="Lastname is required.">
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label for="form_message">Description *</label>
                                        <input id="form_message" name="des" value="${project.des }" class="form-control" placeholder="Message for me *" rows="4" required="required" data-error="Please,leave us a message."></input>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <input type="submit" class="btn btn-success btn-send" value="Update">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <p class="text-muted"><strong>*</strong> These fields are required. Contact form template by <a href="http://bootstrapious.com" target="_blank">Coop</a>.</p>
                                </div>
                            </div>
                        </div>

                    </form>

                </div><!-- /.8 -->

 

     
   
  </div>
  <div id="Public" class="tab-pane fade">
    <h3>Public activity</h3>
    <div id="chartContainer" style="height: 300px; width: 100%;"></div>
  </div>
  <c:if test="${project.owner==userId}">
   <div id="invite" class="tab-pane fade">
    <h3>invite users</h3>
    <ul class="mylist">
    <c:forEach var="user" items="${userList}">
    	  <c:if test="${user.img!=null}">
   			 <span><img id="user_img" src="/Coop/res/images/${user.id}.jpg" class="avatar img-circle" alt="avatar" style="height:50px; width:50px;"/></span>&nbsp&nbsp<li id="pList" data-url="/Coop/"><h5> ${user.name}</h5></li>
   			  <div class="pull-right action-buttons">
   			  		<input id="inv" type="button" class="btn btn-info" value="Invite" data-url="/Coop/project/${user.id}/${project.id}/invite.do"/>
              </div> 
	      </c:if>
   		  <c:if test="${user.img==null }">
   		  	 <span><img id="user_img" src="/Coop/res/images/null.jpg" class="avatar img-circle" alt="avatar" style="height:50px; width:50px;"/></span>&nbsp&nbsp<li id="pList" data-url="/Coop/"><h5> ${user.name}</h5></li>
   		  	 <div class="pull-right action-buttons">
                    <input id="inv" type="button" class="btn btn-info" value="Invite" data-url="/Coop/project/${user.id}/${project.id}/invite.do"/>       
             </div>  
   		  </c:if>
   		 <hr/>        
    </c:forEach>
    </ul>
  </div>
  </c:if>
  </div>
  
  
 
</sec:authorize>
  
