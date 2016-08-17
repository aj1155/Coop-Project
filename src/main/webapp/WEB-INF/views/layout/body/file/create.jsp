<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
function readURL(input){
	 if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	            $('#user_img').attr('src', e.target.result);
	        }
	        reader.readAsDataURL(input.files[0]);
	    }
}
</script>
<style>
.upload { 
    margin-bottom:20px;
    position: relative;
    cursor: pointer !important;
    background: #6ccaa5;
    padding: 10px 20px;
    border: 2px solid transparent;
    color: #fff;
    border-radius:2px;
}

.upload:hover {background: #fff;
padding: 10px 20px;
border: 2px solid #6ccaa5;
color: #6ccaa5;}
   
#input-file {
    cursor: pointer;
    display: block;
    position:absolute !important;
    top:0 !important;
    left: 0 !important;
    /* start of transparency styles */
    opacity:0;
    -moz-opacity:0;
    filter:alpha(opacity:0);
    /* end of transparency styles */
    background: red;
    z-index:2; /* bring the real upload interactivity up front */
    width:100%;
    height: 100%;
}
.upload:hover #input-file{
    display: block;
}
</style>
<div class="container">
<div class="subhead">
    <h2 class="subhead-heading">Work</h2>
    <span class="subhead-description" style="font-size:10pt; color:#888;">
      	Setting your WorkPlace..
    </span>
    <hr/>
    
    <div id="legend">
    	
      <legend>File Upload</legend>
      
    </div>
   </div>
   <form class="form-horizontal" method="POST" enctype="multipart/form-data">
  <div class="col-md-3">
        <div class="text-center">
    
   	  <img id="user_img" src="/Coop/res/images/fileDefault.jpg" class="avatar img-circle" alt="avatar" style="height:200px; width:200px;"/>
          <!--<img src="//placehold.it/100" class="avatar img-circle" alt="avatar">-->
          <h6>Upload a Project File...</h6>
          
          <span class="upload">
          	Upload Your File
			<input type="file" id="input-file" name="file" onchange="readURL(this);"/>
		  </span>
        </div>
  </div>
 <div class="col-md-9 personal-info">
	
  
  	<div class="subbody" >
		
  <fieldset>
    
    <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="owner">UserId</label>
      <div class="controls">
        <input type="text" id="owner" name="owner" placeholder="" class="input-xlarge" value="${userId}" style="height:25px;">
        <p class="help-block">Owner can contain any letters or numbers, without spaces</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- E-mail -->
      <label class="control-label" for="name">ProjectName</label>
      <div class="controls">
        <input type="text" id="name" name="name" placeholder="" class="input-xlarge" value="${project.name}" style="height:25px;">
        <p class="help-block">Project</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- Password-->
      <label class="control-label" for="des">Description</label>
      <div class="controls">
        <input type="text" id="des" name="des" placeholder="" class="input-xlarge" value="">
        <p class="help-block">if you want to remain notice just write down here</p>
      </div>
    </div>
     <div class="control-group">
      <!-- Button -->
      <div class="controls">
        <button class="btn btn-success">Save</button>
      </div>
    </div>
   
 
   
  </fieldset>

		  	
  	</div> 
  	</div>
  	</form>
</div>