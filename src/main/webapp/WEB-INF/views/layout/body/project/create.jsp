<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<sec:authorize access="authenticated">
  <div class="container">
   <div class="subhead">
    <h2 class="subhead-heading">Create a new repository</h2>
    <p class="subhead-description" style="font-size:10pt; color:#888;">
     A repository contains all the files for your project, including the revision history
    </p>
    <hr/>
   </div>
   
  
     <div class="subbody" >
      <form class="form-horizontal" method="POST">
  <fieldset>
    <div class="control-group">
      <!-- Username -->
      <label class="control-label"  for="owner">owner</label>
      <div class="controls">
        <input type="text" id="owner" name="owner" placeholder="" class="input-xlarge" value="<sec:authentication property="user.id" />" style="height:25px;">
        <p class="help-block">Owner can contain any letters or numbers, without spaces</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- E-mail -->
      <label class="control-label" for="name">name</label>
      <div class="controls">
        <input type="text" id="name" name="name" placeholder="" class="input-xlarge" value="" style="height:25px;">
        <p class="help-block">Please provide your ProjectName</p>
      </div>
    </div>
 
    <div class="control-group">
      <!-- Password-->
      <label class="control-label" for="des">Discription</label>
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
</form>
           
     </div> 
  
</div>
</sec:authorize>