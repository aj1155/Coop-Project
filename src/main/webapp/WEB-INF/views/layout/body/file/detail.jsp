<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/Coop/res/css/detail.css" type='text/css'>
<script>
$(function(){
	$('#down').click(function(){
		location.href = $(this).attr("data-url");
	});
	$('#new_file').click(function(){
		location.href = "/Coop/file/"+${project.id}+"/"+$('#user_id').val()+"/"+$('#file_id').val()+"/create2.do"
	});
	$('#btn').click(function(){
		location.href = $(this).attr("data-url");
	});
});
</script>


<sec:authorize access="authenticated">

<input type="hidden" id="user_id" value="<sec:authentication property="user.id" />">
<input type="hidden" id="file_id" value="${file.id }">
<c:set var="userId"><sec:authentication property="user.id" /></c:set>
<h5><sec:authentication property="user.id" /> / ${project.name }</h5>
<hr/>
<div class="filter-bar">
    <form accept-charset="UTF-8" action="/Coop/file/search.do" class="d-inline" method="POST" role="search">
    <!--<input type="hidden" name="user" value="<sec:authentication property="user.name" />">-->
    <input type="text" id="your-repos-filter" name="search" style="width:320px;height:30px;" class="form-control js-filterable-field" placeholder="Find a repository&hellip;" aria-label="Filter your repositories by name" >
    <input type="submit" style="margin-bottom:10px;" value="Search" class="btn">
	<input type="button" id="new_file" style="margin-bottom:10px;" value="Upload File" class="btn pull-right btn-success">
	<input type="button" id="btn" style="margin-bottom:10px; margin-right:10px;" value="FileHistory" class="btn pull-right btn-success" data-url="/Coop/file/${file.id }/history.do" />
	</form>
	
    </div>
<div class="container">
				<div class="row">
				<div class="col-md-12 col-sm-12">
                    <div class="panel panel-success">
                        <div id="down" class="panel-heading" style="cursor:pointer;" data-url="/Coop/file/${file.id}/download.do">
                           <span><i class="fa fa-file" aria-hidden="true"></i></span>&nbsp${file.fileName}
                        </div>
                        <div class="panel-body">
                            <p>${file.des}</p>
                        </div>
                        <div class="panel-footer">
                            <img id="user_img" src="/Coop/res/images/${file.userId}.jpg" class="avatar img-circle" alt="avatar" style="height:40px; width:40px;"/><span style="font-weight:bold"> ${file.userId }</span>
                        	
                        </div>
                    </div>
                </div>
               	
                </div>
                  <div class="row">
                <div class="col-md-12 col-sm-12">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            Diff file before
                        </div>
                        <div class="panel-body">
                            <p>Change File Contents...</p>
                            <c:forEach var="png" items="${ pngs }">
                            	<img id="user_img" src="/Coop/res/FileSave/${file.fileName}/${png.src}" class="avatar" alt="avatar" style="height:500px; width:500px;"/>
                        	</c:forEach>
                        </div>
                        <div class="panel-footer">
     <ul class="media-list comments">
     <c:forEach var="comment" items="${ commentList }">
   	  <li class="media">
        <a class="pull-left" href="#">
        <img class="media-object img-circle img-thumbnail" src="/Coop/res/images/${comment.userId }.jpg" width="64" alt="Generic placeholder image">
        </a>
        <div class="media-body">
          <h5 class="media-heading pull-left">${comment.userName }</h5>
          <div class="comment-info pull-left">
            <div class="btn btn-danger btn-xs" data-toggle="tooltip" data-placement="top" title="Sent from ***0.0.1"><i class="fa fa-user"></i></div>
            <div class="btn btn-primary btn-xs"><a class="fa fa-envelope white" href="mailto:loneswan@loneswan.net"></a></div>
            <c:if test="${comment.userId==userId}">
            <div class="btn btn-xs"><a class="fa fa-trash-o" href="/Coop/file/${comment.id}/${project.id}/${file.id}/commentDelete.do"></a></div>
            </c:if>
            <div class="btn btn-default btn-xs"><i class="fa fa-clock-o"></i> Posted 3 days ago</div>
          </div>
          <br class="clearfix">
          <p class="well">${comment.content }</p>
        </div>
      </li>     
     </c:forEach>
      
     
   
    </ul>
                        </div>
                    </div>
                </div>
                </div>
                
                
                 <div class="row">
    
   				 <div class="col-md-6">
    						<div class="widget-area no-padding blank">
								<div class="status-upload">
									<form action="/Coop/file/${project.id}/${file.id}/comment.do"  method="POST">
										<textarea name="text" placeholder="Add your comments..." ></textarea>
										<ul>
											<li><a title="" data-toggle="tooltip" data-placement="bottom" data-original-title="Audio"><i class="fa fa-music"></i></a></li>
											<li><a title="" data-toggle="tooltip" data-placement="bottom" data-original-title="Video"><i class="fa fa-video-camera"></i></a></li>
											<li><a title="" data-toggle="tooltip" data-placement="bottom" data-original-title="Sound Record"><i class="fa fa-microphone"></i></a></li>
											<li><a title="" data-toggle="tooltip" data-placement="bottom" data-original-title="Picture"><i class="fa fa-picture-o"></i></a></li>
										</ul>
										<button type="submit" class="btn btn-success green"><i class="fa fa-share"></i> Add</button>
									</form>
								</div><!-- Status Upload  -->
							</div><!-- Widget Area -->
						</div>
        
    			</div>


</div>
</sec:authorize>