<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jspf/taglib.jspf" %>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/style.css" />
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
</head>
<body>

<jsp:include page="AdminProfile.jsp"/>

<h3><fmt:message key="register_lecturer_jsp.register_lecturer"/></h3>

<c:if test="${errorInput != null}">
	<h3>Error input:</h3>
	<c:forEach var="errorField" items="${errorInput}">
		<h5>${errorField}</h5>
	</c:forEach>
</c:if>

<div class="mainTable">
	<div class = "inputFields">
 		<form class="input" action="RegisterLecturer" method = "POST">
 			<label ><fmt:message key="register_jsp.surname"/></label>
 			<input class="form-control" type="text" name="surname" required><br>
 		
  			<label ><fmt:message key="register_jsp.first_name"/></label>
  			<input class="form-control" type="text" name="firstname" required><br>

	  		<label ><fmt:message key="resiter_jsp.last_name"/></label>
  			<input class="form-control" type="text" name="lastname" required><br><br>
		
			<label ><fmt:message key="register_jsp.email"/></label>
  			<input class="form-control" type="email" name="email" required><br>
		
			<label ><fmt:message key="register_jsp.password"/></label>
  			<input class="form-control" type="password" name="password" min="6" max="10" required><br>
		
 	   		 <input class="btn btn-default" type="submit" value="OK">
 		</form>
	</div> 
</div>

<jsp:include page="SelectActionForLecturerView.jsp"></jsp:include>	
</body>
</html>