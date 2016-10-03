<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>
$(function(){
	$("tr[data-url]").click(function() {
		location.href =  $(this).attr("data-url");
		});
	
})


</script>

<div class="container">
		<div class="row">
		
        
        <div class="col-md-12">
        <h4>Member History</h4>
        <div class="table-responsive">

                
              <table id="mytable" class="table table-bordred table-striped">
                   
                   <thead>
                   
                   <th><input type="checkbox" id="checkall" /></th>
                   <th>멤버</th>
                    <th>파일번호</th>
                     <th>변경내용</th>
                     <th>명세서</th>
                     <th>작업날짜</th>
                      <th>Edit</th>
                      
                       <th>Delete</th>
                   </thead>
    <tbody>
    					
                        <c:if test="${fileList.size()>0}">
                            <c:forEach var="file" items="${ fileList }">
                            	<tr  data-url="/Coop/file/downloadInner.do?fileId=${file.refFile}">
							    <td><input type="checkbox" class="checkthis" /></td>
							    <td>${userName}</td>
							    <td>${file.refFile}</td>
							    <td>${file.fileName}업로드</td>
							    <td>${file.des }</td>
							    <td>${file.fileTime }</td>
							    <td><button  class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-pencil"></span></button></td>
								<td><button class="btn btn-danger btn-xs" ><span class="glyphicon glyphicon-trash"></span></button></td>
							    </tr>
                        	</c:forEach>
                        	</c:if>
                        	<c:if test="${diffList!=null}">
                        	
                        		<c:forEach var="diff" items="${diffList}">
                        				<tr id="btn2" data-url="/Coop/file/show.do?fileId=${diff.fileId}">
									    <td><input type="checkbox" class="checkthis" /></td>
									    <td>${userName}</td>
									    <td>${diff.fileId}</td>
									    <td>파일 수정 및 업로드</td>
									    <td>${diff.des }</td>
									    <td>${diff.create_time }</td>
									    <td><button  class="btn btn-primary btn-xs"><span class="glyphicon glyphicon-pencil"></span></button></td>
									    <td><button class="btn btn-danger btn-xs" ><span class="glyphicon glyphicon-trash"></span></button></td>
									    </tr>
                        		</c:forEach>
                        	</c:if>
   

    
    </tbody>
        
</table>

	</div>
</div>
  </div>
</div>