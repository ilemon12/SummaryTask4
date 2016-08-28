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

<jsp:include page="AdminProfile.jsp"></jsp:include>

<h3><fmt:message key="admin_view_lecturers_jsp.lecturers"/></h3>
<div class="mainTable">
	<table class="table">	
		<tr>
			<th><fmt:message key="profile.surname"/></th>
			<th><fmt:message key="profile.first_name"/></th>
			<th><fmt:message key="profile.last_name"/></th>
			<th><fmt:message key="profile.email"/></th>
			<th><fmt:message key="courses_view_jsp.courses"/></th>
			<th><fmt:message key="admin_view_lecturers_jsp.add_course"/></th>
		</tr>
		
		<c:forEach var="lecturer" items="${lecturers}">
		<tr>
			<td>${lecturer.customerInfo.surname}</td>
			<td>${lecturer.customerInfo.firstName}</td>
			<td>${lecturer.customerInfo.lastName}</td>
			<td>${lecturer.customerInfo.email}</td>
			<td>
				<form action="CoursesOfLecturer">
					<input type="number" name="idLecturer" value="${lecturer.id}" hidden="true"></input>
					<input class="button" type="submit" value=<fmt:message key="courses_view_jsp.courses"/>>
				</form>
			</td>
			<td>
				<form action="SetLecturerOnCourseView">
					<input type="number" name="idLecturer" value="${lecturer.id}" hidden="true"></input>
					<input class="button" type="submit" value=<fmt:message key="admin_view_lecturers_jsp.add_course"/>>
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>

<jsp:include page="SelectActionForLecturerView.jsp"></jsp:include>	

</body>
</html>