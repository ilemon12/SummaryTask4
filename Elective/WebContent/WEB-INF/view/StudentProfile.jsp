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
<jsp:include page="NavigationBar.jsp" />	
	
<form class="form-group" action="ViewSelectedStudentAction">
		<h3><fmt:message key="profile.profile"/></h3>
		<fmt:message key="profile.surname"/>   : ${customer.customerInfo.surname}<br>  
		<fmt:message key="profile.first_name"/>: ${customer.customerInfo.firstName}<br>  
		<fmt:message key="profile.last_name"/>:	 ${customer.customerInfo.lastName}<br>
		<fmt:message key="profile.email"/>:	     ${customer.customerInfo.email}<br>
		<fmt:message key="profile.role"/>:		 ${customer.customerInfo.role}<br>		
		
	<label ><fmt:message key="select"/></label>
    <select class="form-control" name="nameOfAction">
     	<option value="dontStart"><fmt:message key="studet_profile_jsp.registered_courses"/></option>
      	<option value="inProgress"><fmt:message key="student_profile_jsp.in_progress"/></option>
      	<option value="isOver"><fmt:message key="student_profile_jsp.courses_over"/></option>
    </select>
 	    
 	<input class="button" type="submit" value="OK">
</form>

	
</body>
</html>