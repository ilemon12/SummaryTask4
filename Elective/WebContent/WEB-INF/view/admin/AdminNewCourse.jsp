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
	
<jsp:include page="AdminProfile.jsp" />

<div class="mainTable">
	
	<h3><fmt:message key="courses_view_jsp.courses"/></h3>
	
<c:if test="${errorInput != null}">
	<h3>Error input:</h3>
	<c:forEach var="errorField" items="${errorInput}">
		<h5>${errorField}</h5>
	</c:forEach>
</c:if>
	
	<div class = "inputFields">
 		<form class="input" action="createCourse" method = "POST">
 		
			<label><fmt:message key="admin_new_course_jsp.name"/></label>
  			<input class="form-control" type="text" name="nameOfCourse" required><br>
		
			<label><fmt:message key="admin_new_course_jsp.date_start"/></label><br>
  			<input class="form-control" type="text" value="" name="dateOfBegin" required><br>
  			
  			<label><fmt:message key="admin_new_course_jsp.date_end"/></label>
  			<input class="form-control" type="text" value="" name="dateOfEnd" required><br>
			
			<label><fmt:message key="admin_new_course_jsp.number_participants"/></label>
			<input class="form-control" type="number" name="numberOfParticipants" min="1" required><br>
			
			<label for="nameOfTopic"><fmt:message key="admin_new_course_jsp.topic_of_course"/></label>
    		<select class="form-control" id="nameOfTopic" name="nameOfTopic">
    		<c:forEach var="topic" items = "${topics}">
      			<option value="${topic.id}">${topic.name}</option>
      		</c:forEach>	
    		</select>
			
 	    	<input class="btn btn-default" type="submit" value="OK">
 		</form>
	</div> 
</div>	

<jsp:include page="SelectActionForCoursesView.jsp"/>

</body>
</html>