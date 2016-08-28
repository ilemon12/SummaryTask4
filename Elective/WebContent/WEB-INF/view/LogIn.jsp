<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/style.css" />
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
</head>
<body>

<c:import url="NavigationBar.jsp"/>

<h3><fmt:message key="login_jsp.login"/></h3>

<c:if test="${errorInput != null}">
	<h3>Error input:</h3>
	<c:forEach var="errorField" items="${errorInput}">
		<h5>${errorField}</h5>
	</c:forEach>
</c:if>

<div class = "inputFields">
 	<form class="input" action="LogIn" method = "POST">
 		
		<label ><fmt:message key="register_jsp.email"/></label>
  		<input class="form-control" type="email" name="email" required><br>
		
		<label ><fmt:message key="register_jsp.password"/></label>
  		<input class="form-control" type="password" name="password" min="6" max="10" required><br>
		
 	    <input class="btn btn-default" type="submit" value="OK">
 	</form>
</div> 
</body>
</html>