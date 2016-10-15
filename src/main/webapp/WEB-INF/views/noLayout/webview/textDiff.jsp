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
	<h2>파일 비교</h2>
    <form method="post">
   
    <div id="files">
        <span>first</span>
        <input type="file" name="file" multiple /><br />
        <span>second</span>
        <input type="file" name="file" multiple /><br />
    </div>
    <div>
        <button type="submit" class="btn btn-primary">
           	비교
        </button>
       
    </div>
</form>
	<span>결과:</span>
    <div>
    	<c:if test="${showDiff!=null}">
                        	<c:set var="$del" value="$del" />
                        	<c:set var="$add" value="$add" />
                        	<c:set var="$cha" value="$cha" />
                        		<c:forEach var="diff" items="${showDiff}">
                        		<c:choose>
                        			<c:when test="${diff.startsWith($del)}">
                            		<p style="color:red">${diff.replace($del,"")}</p>
                            		</c:when>
                            		<c:when test="${diff.startsWith($add)}">
                            		<p style="color:blue">${diff.replace($add,"")}</p>
                            		</c:when>
                            		<c:otherwise>
                            		<p>${diff}</p>
                            		</c:otherwise>
      							</c:choose>
                        		</c:forEach>
                        	</c:if>
    </div>

</div>
