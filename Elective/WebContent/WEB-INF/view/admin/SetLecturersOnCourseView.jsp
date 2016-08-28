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
	
<div class="mainTable">	
 	<h1> <fmt:message key="courses_view_jsp.courses"/> </h1>	
 		<c:forEach var="course" items = "${courses}">
 			
 				<div class = "panel panel-info">
 					<div class="panel-heading">${course.name}</div> 
 					<div class="panel-body">
 	  					<div class="course">
 	  						<dl>
   					   			<dt><fmt:message key="courses_view_jsp.date_start"/></dt>
    							<dd>${course.dateOfCourse.startDate}<br></br></dd>
    							
    							<dt><fmt:message key="courses_view_jsp.date_end"/></dt>
    							<dd>${course.dateOfCourse.endDate} <br></br></dd>
    							
  								<dt><fmt:message key="courses_view_jsp.duration"/></dt>
 								<dd>${course.dateOfCourse.daysDuration}<br></br></dd> 
 								
 								<dt><fmt:message key="courses_view_jsp.number_participants"/></dt>
 								<dd>${course.numberParticipants.numberParticipants}<br></br></dd>
 								
 								<dt><fmt:message key="courses_view_jsp.number_registered"/></dt>
 								<dd>${course.numberParticipants.numberRegistered}<br></br></dd>
 								
 								<dt><fmt:message key="courses_view_jsp.topic"/></dt>
 								<dd>${course.topic.name}</dd>
 							</dl>	
 							<form action="SetLecturerOnCourse" method="POST">
 								<input type="number" name="idCourse" value="${course.id}" hidden="true" ></input><br>
 								<input type="number" name="idLecturer" value="${idLecturer}" hidden="true"></input>
 								<input class="btn btn-default" type="submit" value=<fmt:message key="set_lecturer_on_course_jsp.set_lecturer"/>>
 							</form>	
 						</div>
 					</div>
		   		</div> 	
 		</c:forEach>
</div>

<jsp:include page="SelectActionForLecturerView.jsp"></jsp:include>

</body>
</html>