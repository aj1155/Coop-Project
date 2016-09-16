<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="http://snipplicious.com/css/bootstrap-3.1.1.min.css">

<link rel="stylesheet" type="text/css" href="http://snipplicious.com/css/font-awesome-4.0.3.min.css">
<style>
.stylish-panel {
    padding: 20px 0;
    text-align: center;
}
.stylish-panel > div > div{
    padding: 10px;
    border: 1px solid transparent;
    border-radius: 4px;
    transition: 0.2s;
}
.stylish-panel > div:hover > div{
    margin-top: -10px;
    border: 1px solid rgb(200, 200, 200);
    box-shadow: rgba(0, 0, 0, 0.1) 0px 5px 5px 2px;
    background: rgba(200, 200, 200, 0.1);
    transition: 0.5s;
}

.stylish-panel > div:hover img {
    border-radius: 50%;
    -webkit-transform: rotate(360deg);
    -moz-transform: rotate(360deg);
    -o-transform: rotate(360deg);
    -ms-transform: rotate(360deg);
    transform: rotate(360deg);
}
</style>
<script src="http://snipplicious.com/js/jquery.js"></script>
<script src="http://snipplicious.com/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <div class="page-header">
    <h1 class="text-center">FileHistory</h1>
  </div>
  <p class="lead text-center">FileList</p>
  <div class="container">
    <div class="row stylish-panel">
    <c:forEach var="file" items="${fileList}">
      <div class="col-md-4">
        <div>
          <img src="/Coop/res/images/file.png" alt="Texto Alternativo" class="img-circle img-thumbnail">
          <h2>${file.fileName }</h2>
          <p>${file.des }
          </p>
          <a href="/Coop/file/${file.id}/downloadInner.do" class="btn btn-primary" title="See more">Download</a>
        </div>
      </div>
     </c:forEach>
    </div>
  </div>
</div>
<!-- /container -->
</body>
</html>