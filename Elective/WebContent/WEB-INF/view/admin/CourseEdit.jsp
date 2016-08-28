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
 	
 	<c:if test="${errorInput != null}">
		<h3>Error input:</h3>
		<h5>${errorInput}</h5>
	</c:if>
 	
 		<c:forEach var="course" items = "${courses}">
 			
 				<div class = "panel panel-info">
 					<div class="panel-heading">${course.name}</div> 
 					<div class="panel-body">
 	  					<div class="course">
 	  						
 	  							<dl>
 	  								<dt><fmt:message key="courses_view_jsp.name"/></dt>
 	  								<dd>
 	  									<form action="CourseEdit" method="POST">
 	  										<input type="text" name="nameOfField" value="name" hidden="true" ></input>
 	  										<input type="number" name="idCourse" value="${course.id}" hidden="true" ></input>
    										<input class="form-control" type="text" name="newName" value="${course.name}"/>	
    										<input class="btn btn-default" type="submit" value=<fmt:message key="course_edit_jsp.edit"/>><br></br>
    									</form>
    								</dd>
 	  								
   					   				<dt><fmt:message key="courses_view_jsp.date_start"/></dt>
    								<dd>
    									<form action="CourseEdit" method="POST">
    										<input type="text" name="endDate" value="${course.dateOfCourse.endDate}" hidden="true" ></input>
    										<input type="text" name="nameOfField" value="startDate" hidden="true" ></input>
    										<input type="number" name="idCourse" value="${course.id}" hidden="true" ></input>
    										<input class="form-control" type="text" name="newStartDate" value="${course.dateOfCourse.startDate}"/>
    										<input class="btn btn-default" type="submit" value=<fmt:message key="course_edit_jsp.edit"/>><br></br>	
    									</form>	
    								</dd>
    								
    								<dt><fmt:message key="courses_view_jsp.date_end"/></dt>
    								<dd>
    									<form action="CourseEdit" method="POST">
    										<input type="text" name="startDate" value="${course.dateOfCourse.startDate}" hidden="true" ></input>
    										<input type="text" name="nameOfField" value="endDate" hidden="true" ></input>
    										<input type="number" name="idCourse" value="${course.id}" hidden="true" ></input>
    										<input class="form-control" type="text" name="newEndDate" value="${course.dateOfCourse.endDate}"/>
    										<input class="btn btn-default" type="submit" value=<fmt:message key="course_edit_jsp.edit"/>><br></br>
    									</form>	
    								</dd>
    								
  									<dt><fmt:message key="courses_view_jsp.duration"/></dt>
 									<dd>${course.dateOfCourse.daysDuration}<br></br></dd>
 									
 									<dt><fmt:message key="courses_view_jsp.number_participants"/></dt>
 									<dd>
 										<form action="CourseEdit" method="POST">
 											<input type="text" name="nameOfField" value="numberParticipants" hidden="true" ></input>
 											<input type="number" name="idCourse" value="${course.id}" hidden="true" ></input>
 											<input class="form-control" type="number" name="newNumberOfParticipants" min="1" value="${course.numberParticipants.numberParticipants}"/>
 											<input class="btn btn-default" type="submit" value=<fmt:message key="course_edit_jsp.edit"/>><br></br>
 										</form>	
 									</dd>
 									
 									<dt><fmt:message key="courses_view_jsp.number_registered"/></dt>
 									<dd>${course.numberParticipants.numberRegistered}<br></br></dd>	
 									
 									<dt><fmt:message key="courses_view_jsp.topic"/>: ${course.topic.name}<dt>
    								<dd>
    									<form action="CourseEdit" method="POST">
    										<select class="form-control" name="idOfNewTopic">
    											<c:forEach var="topic" items = "${topics}">
      												<option value="${topic.id}">${topic.name}</option>
      											</c:forEach>	
    										</select>
    										<input type="text" name="nameOfField" value="topic" hidden="true" ></input>
    										<input type="number" name="idCourse" value="${course.id}" hidden="true" ></input>
    										<input class="btn btn-default" type="submit" value=<fmt:message key="course_edit_jsp.edit"/>><br></br>
    									</form>	
    								</dd>
 								</dl>
 								
 							<form action="CourseDelete" method="POST">
 								<input type="number" name="idCourse" value="${course.id}" hidden="true" ></input>
 								<input class="btn btn-default" type="submit" value=<fmt:message key="course_edit_jsp.delete"/>>
 							</form>	
 						</div>
 					</div>
		   		</div> 	
 		</c:forEach>
</div>

<jsp:include page="SelectActionForCoursesView.jsp"></jsp:include>

</body>
</html>