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


<form class="form-group" action="ViewSelectedLecturers">	
		
	<label ><fmt:message key="select_action_for_lecturer_view_jsp.action"/> </label>
    <select class="form-control" name="nameOfLectuerAction">
      	<option value="registerLecturer"><fmt:message key="select_action_for_lecturer_view_jsp.register_lecturer"/></option>
      	<option value="viewLecturers"><fmt:message key="select_action_for_lecturer_view_jsp.view_lecturer"/></option>
    </select>
 	    
 	<input class="button" type="submit" value="OK">
</form>

</body>
</html>