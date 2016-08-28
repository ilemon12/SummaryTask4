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

<jsp:include page="StudentProfile.jsp"/>
	
<div class="mainTable">	
 	<h1> <fmt:message key="courses_view_jsp.courses"/> </h1>	
 	<c:if test="${errorInput != null}">
		<h5>${errorInput}</h5>
	</c:if>
 	
 		<c:forEach var="evaluationStudent" items = "${courses}">
 			
 				<div class = "panel panel-info">
 					<div class="panel-heading">${evaluationStudent.course.name}</div> 
 					<div class="panel-body">
 	  					<div class="course">
 	  						<dl>
   					   			<dt><fmt:message key="courses_view_jsp.date_start"/></dt>
    							<dd>${evaluationStudent.course.dateOfCourse.startDate}<br></br></dd>
    							
    							<dt><fmt:message key="courses_view_jsp.date_end"/></dt>
    							<dd>${evaluationStudent.course.dateOfCourse.endDate} <br></br></dd>
    							
  								<dt><fmt:message key="courses_view_jsp.duration"/></dt>
 								<dd>${evaluationStudent.course.dateOfCourse.daysDuration}<br></br></dd> 
 								
 								<dt><fmt:message key="courses_view_jsp.number_participants"/></dt>
 								<dd>${evaluationStudent.course.numberParticipants.numberParticipants}<br></br></dd>
 								
 								<dt><fmt:message key="courses_view_jsp.number_registered"/></dt>
 								<dd>${evaluationStudent.course.numberParticipants.numberRegistered}<br></br></dd>
 								
 								<dt><fmt:message key="courses_view_jsp.topic"/></dt>
 								<dd>${evaluationStudent.course.topic.name}<br></br></dd>
 								
 								<dt><fmt:message key="view_courses_that_over_jsp.evaluation"/></dt>
 								<dd>
 									<div class="progress">
 										<div class="progress-bar" role="progressbar" 
 										aria-valuenow="${evaluationStudent.evaluation}" aria-valuemin="0" aria-valuemax="100"
 										style="width:${evaluationStudent.evaluation}%">
 										</div>
 										${evaluationStudent.evaluation}
 									</div>
 								</dd>
 							</dl>		
 						</div>
 					</div>
		   		</div> 	
 		</c:forEach>
</div>

</body>
</html>