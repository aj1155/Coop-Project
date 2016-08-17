<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<sec:authorize access="authenticated">


<h5>${project.owner } / ${project.name }</h5>
<hr/>


<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#work"><i class="fa fa-file-word-o" aria-hidden="true"></i> Work</a></li>
  <li><a data-toggle="tab" href="#problem"><i class="fa fa-exclamation-triangle" aria-hidden="true"></i> Problem</a></li>
  <li><a data-toggle="tab" href="#request"><i class="fa fa-refresh" aria-hidden="true"></i> Request</a></li>
  <li><a data-toggle="tab" href="#setting"><i class="fa fa-wrench" aria-hidden="true"></i> Settings</a></li>
</ul>

<div class="tab-content">
  <div id="work" class="tab-pane fade in active">
    
    <h3>Work</h3>
    <p>Some content.</p>
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
  </div>
 
</sec:authorize>
  
