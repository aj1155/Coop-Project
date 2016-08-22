<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script>
$(function(){
	$('#new_file').click(function(){
		location.href = "/Coop/file/"+${project.id}+"/"+$('#user_id').val()+"/create.do"
	});
	$("li[data-url]").click(function() {
        location.href = $(this).attr("data-url");
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
<sec:authorize access="authenticated">


<h5>${project.owner } / ${project.name }</h5>
<hr/>

<input type="hidden" id="user_id" value="<sec:authentication property="user.id" />">
<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#work"><i class="fa fa-file-word-o" aria-hidden="true"></i> Work</a></li>
  <li><a data-toggle="tab" href="#problem"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i> Problem</a></li>
  <li><a data-toggle="tab" href="#request"><i class="fa fa-refresh" aria-hidden="true"></i> Request</a></li>
  <li><a data-toggle="tab" href="#setting"><i class="fa fa-wrench" aria-hidden="true"></i> Settings</a></li>
  <li><a data-toggle="tab" href="#Public"><i class="fa fa-line-chart" aria-hidden="true"></i> Public</a></li>
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
   		 <span><i class="fa fa-file" aria-hidden="true"></i></span>&nbsp&nbsp<li id="pList" data-url="/Coop/file/${file.id}/${project.id}/detail.do"><h5> ${file.fileName}</h5></li> 
   		 <hr/>        
    </c:forEach>
    </ul>
  </div>
  <div id="problem" class="tab-pane fade">
  
	
    </div>
    <hr/>
   <div id="request" class="tab-pane fade">
    <h3>Request activity</h3>
    <p>Some content in menu 2.</p>
  </div>
   <div id="setting" class="tab-pane fade">
    <h3>Setting activity</h3>
    <p>Some content in menu 2.</p>
  </div>
  <div id="Public" class="tab-pane fade">
    <h3>Public activity</h3>
    <p>Some content in menu 2.</p>
  </div>
  </div>
  
 
</sec:authorize>
  
