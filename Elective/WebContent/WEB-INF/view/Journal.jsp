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

<jsp:include page="LecturerProfile.jsp"></jsp:include>

<h3><fmt:message key="journal_jsp.students"/></h3>
<div class="mainTable">

<h3><fmt:message key="journal_jsp.journal_of"/> ${journal.nameCourse}</h3>

<c:if test="${errorInput != null}">
	<h5>${errorInput}</h5>
</c:if>

	<table class="table">	
		<tr>
			<th><fmt:message key="profile.surname"/></th>
			<th><fmt:message key="profile.first_name"/></th>
			<th><fmt:message key="profile.last_name"/></th>
			<th><fmt:message key="profile.email"/></th>
			<th><fmt:message key="view_courses_that_over_jsp.evaluation"/></th>
		</tr>
		
		<c:forEach var="rawJournal" items="${journal.rowsJournal}">
		<tr>
			<td>${rawJournal.customer.customerInfo.surname}</td>
			<td>${rawJournal.customer.customerInfo.firstName}</td>
			<td>${rawJournal.customer.customerInfo.lastName}</td>
			<td>${rawJournal.customer.customerInfo.email}</td>
			<td>
				<form action="Estimate" method="POST">
					<input type="number" name="idStudent" value="${rawJournal.customer.id}" hidden="true"></input>
					<input type="number" name="evaluation" value="${rawJournal.evaluation}" min="0" max="100" style="width:30%"></input>
					<input class="button" type="submit" value="OK">
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>

</body>
</html>