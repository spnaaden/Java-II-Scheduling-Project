<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://www.springframework.org/tags/form" prefix="mvc" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scheduling Page</title>
<link href="../Styles/Styler.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="wrapper">
<img class="headerImage" src="Images/headerlogo-full.jpg" alt="HeaderImage" />
<table width="90%" border="1">
	<tr>
		<th>Date</th>
		<th>Start Time</th>
		<th>End Time</th>
		<th>Event</th>
		<th>Description</th>
		<th>Address</th>
		<th>Edit/Delete</th>
	</tr>

<c:forEach items="${events}" var="event">
	<tr id = ${event.getEvent_ID() }> <!-- We can tell which event id we are looking at -->
		<td>${event.getEventDate().toString() }</td>
		<td>${event.getStartTime().toString()}</td>
		<td>${event.getEndTime().toString() }</td>
		<td>${event.getEventDescription() }</td>
		<td>${event.getEventName() }</td>
		<td>${event.getEventAddress() }</td>
		<td><input type="button" name="edit" value="Edit"> <input type="button" name="delete" value="Delete"></td>
	</tr>
</c:forEach>
</table> 
<h5 class="transparent">Property of SuperScheduler Studios�</h5>
</div>
</body>
<script>
var counter = 0;
</script>
</html>