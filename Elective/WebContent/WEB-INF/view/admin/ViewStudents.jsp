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

<h3><fmt:message key="journal_jsp.students"/></h3>
<div class="mainTable">

<c:if test="${errorInput != null}">
	<h5>${errorInput}</h5>
</c:if>

	<table class="table">	
		<tr>
			<th><fmt:message key="profile.surname"/></th>
			<th><fmt:message key="profile.first_name"/></th>
			<th><fmt:message key="profile.last_name"/></th>
			<th><fmt:message key="profile.email"/></th>
			<th><fmt:message key="view_students_jsp.block"/></th>
			<th><fmt:message key="view_students_jsp.unblock"/></th>
		</tr>
		
		<c:forEach var="student" items="${students}">
		<tr>
			<td>${student.customerInfo.surname}</td>
			<td>${student.customerInfo.firstName}</td>
			<td>${student.customerInfo.lastName}</td>
			<td>${student.customerInfo.email}</td>
			<td>
				<form action="AddBlockStudent" method="POST">
					<input type="number" name="idStudent" value="${student.id}" hidden="true"></input>
					<input class="button" type="submit" value=<fmt:message key="view_students_jsp.block"/>>
				</form>
			</td>
			<td>
				<form action="DeletBlockStudent" method="POST">
					<input type="number" name="idStudent" value="${student.id}" hidden="true"></input>
					<input class="button" type="submit" value=<fmt:message key="view_students_jsp.unblock"/>>
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
</div>

</body>
</html>