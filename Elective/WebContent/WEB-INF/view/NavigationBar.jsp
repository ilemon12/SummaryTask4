<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/jspf/taglib.jspf" %>
  
<!DOCTYPE html PUBLIC>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/style.css" />
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
</head>
<body>

<nav class="navbar navbar-default">
 	<div class="container-fluid">
		<ul class="nav navbar-nav">
  			<li><a href="CoursesView"><fmt:message key="navigation_bar_jsp.link.home"/></a></li>
  		
  			<c:choose>
  				<c:when test="${customer == null}">
  					<li><a href="LogIn"><fmt:message key="navigation_bar_jsp.link.login"/></a></li>
  					<li><a href="Register"><fmt:message key="navigation_bar_jsp.link.register"/></a></li>
  				</c:when>
  				<c:otherwise>	
  					<li><a href="LogOut"><fmt:message key="navigation_bar_jsp.link.logout"/></a></li>
  					<li><a href="Profile">${customer.customerInfo.firstName}</a>
  				</c:otherwise>	
  			</c:choose>	
  			
  			<li><a href="ru">Ru</a>
  			<li><a href="en">En</a>
		</ul> 
 	</div>
</nav>	

</body>
</html>