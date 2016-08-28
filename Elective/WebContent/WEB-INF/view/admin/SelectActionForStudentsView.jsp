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


<form class="form-group" action="ViewSelectedStudents">	
		
	<label ><fmt:message key="select_action_for_students_view_jsp.action"/></label>
    <select class="form-control" name="nameOfCoursesAction">
      	<option value="addStudentToBlock"><fmt:message key="select_action_for_students_view_jsp.block_students"/></option>
    </select>
 	    
 	<input class="button" type="submit" value="OK">
</form>

</body>
</html>