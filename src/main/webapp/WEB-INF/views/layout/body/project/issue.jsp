<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link href='http://fonts.googleapis.com/css?family=Roboto:100' rel='stylesheet' type='text/css'>

<style>

/* Font ROBOTO */
.roboto{
    font-family: 'Roboto', sans-serif !important; 
}

/* custom background for panel  */
.container{ 
    padding-top: 50px !important;
    background-color: #f5f5f5 !important;  
}

/* custom background header panel */
.custom-header-panel{
    background-color: #004b8e !important;
    border-color: #004b8e !important;
    color: white;
}

.no-margin-form-group {
    margin: 0 !important;
}
.requerido {
    color: red;
}
.btn-orange-md {
    background: #FF791F !important;
    border-bottom: 3px solid #ae4d13 !important;
    color: white;
}

.btn-orange-md:hover {
    background: #d86016 !important;
    color: white !important;
}
</style>

<div class="container">
<div class="row">
    <div class="col-md-12">

        <form id="candidatedata" class="form-horizontal" method="POST" role="form">

            <input type="hidden" name="idUser" value="2904">
            <input type="hidden" name="userKey" value="d8babe719ffafafd59bc9d802fb3fdb1">

            <div class="row">
                <div class="col-md-offset-2 col-md-8">
                    <div class="panel">
                        <div class="panel-heading custom-header-panel">
                            <h3 class="panel-title roboto">Profile info</h3>
                        </div>
                        <div class="panel-body">

                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="name">Isseu Name <span class="requerido"> *</span></label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="name" id="name" value="" oninvalid="this.setCustomValidity('Campo requerido')" oninput="setCustomValidity('')" required="" maxlength="70">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="country" class="col-sm-4 control-label">Label<span class="requerido"> *</span></label>
                                <div class="col-sm-8">
                                    <select id="country" name="label" class="form-control" required="">
                                        <option value="" disabled="" selected="">필수항목</option>
                                                                                    <option value="도움요청">도움요청</option>
                                                                                    <option value="오류">오류</option>
                                                                                    <option value="정보공유">정보공유</option>
                                                                                    <option value="진행사항">진행사항</option>
                                                                                    <option value="질문사항">질문사항</option>
                                                                                 
                                                                                
                                        
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-4 control-label" for="rut">Des<span class="requerido"> *</span></label>
                                <div class="col-sm-8">
                                    <textarea style="width:480px;" name="des">
					                
									</textarea>
                                </div>
                            </div>
                            
                            <!--Fin datos personales-->
                            <div class="form-group text-center">
                                <button type="submit" id="submit_btn" class="btn btn-orange-md roboto">Make Issue</button>
                            </div>
                        </div>
                    </div>
                </div>
            
            </div>
        </form> <!-- Fint form -->  
    </div>
</div>
<!-- Tab panes -->
</div>
</div>