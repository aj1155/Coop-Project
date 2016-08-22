<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
$(function(){
	$('#down').click(function(){
		location.href = $(this).attr("data-url");
	});
});
</script>
<style>
body{ background: #fafafa;}
.widget-area.blank {
background: none repeat scroll 0 0 rgba(0, 0, 0, 0);
-webkit-box-shadow: none;
-moz-box-shadow: none;
-ms-box-shadow: none;
-o-box-shadow: none;
box-shadow: none;
}
body .no-padding {
padding: 0;
}
.widget-area {
background-color: #fff;
-webkit-border-radius: 4px;
-moz-border-radius: 4px;
-ms-border-radius: 4px;
-o-border-radius: 4px;
border-radius: 4px;
-webkit-box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
-moz-box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
-ms-box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
-o-box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
box-shadow: 0 0 16px rgba(0, 0, 0, 0.05);
float: left;
margin-top: 30px;
padding: 25px 30px;
position: relative;
width: 100%;
}
.status-upload {
background: none repeat scroll 0 0 #f5f5f5;
-webkit-border-radius: 4px;
-moz-border-radius: 4px;
-ms-border-radius: 4px;
-o-border-radius: 4px;
border-radius: 4px;
float: left;
width: 100%;
}
.status-upload form {
float: left;
width: 100%;
}
.status-upload form textarea {
background: none repeat scroll 0 0 #fff;
border: medium none;
-webkit-border-radius: 4px 4px 0 0;
-moz-border-radius: 4px 4px 0 0;
-ms-border-radius: 4px 4px 0 0;
-o-border-radius: 4px 4px 0 0;
border-radius: 4px 4px 0 0;
color: #777777;
float: left;
font-family: Lato;
font-size: 14px;
height: 142px;
letter-spacing: 0.3px;
padding: 20px;
width: 100%;
resize:vertical;
outline:none;
border: 1px solid #F2F2F2;
}

.status-upload ul {
float: left;
list-style: none outside none;
margin: 0;
padding: 0 0 0 15px;
width: auto;
}
.status-upload ul > li {
float: left;
}
.status-upload ul > li > a {
-webkit-border-radius: 4px;
-moz-border-radius: 4px;
-ms-border-radius: 4px;
-o-border-radius: 4px;
border-radius: 4px;
color: #777777;
float: left;
font-size: 14px;
height: 30px;
line-height: 30px;
margin: 10px 0 10px 10px;
text-align: center;
-webkit-transition: all 0.4s ease 0s;
-moz-transition: all 0.4s ease 0s;
-ms-transition: all 0.4s ease 0s;
-o-transition: all 0.4s ease 0s;
transition: all 0.4s ease 0s;
width: 30px;
cursor: pointer;
}
.status-upload ul > li > a:hover {
background: none repeat scroll 0 0 #606060;
color: #fff;
}
.status-upload form button {
border: medium none;
-webkit-border-radius: 4px;
-moz-border-radius: 4px;
-ms-border-radius: 4px;
-o-border-radius: 4px;
border-radius: 4px;
color: #fff;
float: right;
font-family: Lato;
font-size: 14px;
letter-spacing: 0.3px;
margin-right: 9px;
margin-top: 9px;
padding: 6px 15px;
}
.dropdown > a > span.green:before {
border-left-color: #2dcb73;
}
.status-upload form button > i {
margin-right: 7px;
}
.comments .media-heading {
    margin-top: 25px;
}

.comments .comment-info {
    margin-left: 6px;
    margin-top: 21px;
}

.comments .comment-info .btn {
    font-size: 0.8em;
}

.comments .comment-info .fa {
    line-height: 10px;
}

.comments .media-body p {
    position: relative;
    background: #F7F7F7;
    padding: 15px;
    margin-top: 50px;
}

.comments .media-body p::before {
    background-color: #F7F7F7;
    box-shadow: -2px 2px 2px 0 rgba( 178, 178, 178, .4 );
    content: "\00a0";
    display: block;
    height: 30px;
    left: 20px;
    position: absolute;
    top: -13px;
    transform: rotate( 135deg );
    width:  30px;
}

</style>

<sec:authorize access="authenticated">


<h5><sec:authentication property="user.id" /> / ${project.name }</h5>
<hr/>
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