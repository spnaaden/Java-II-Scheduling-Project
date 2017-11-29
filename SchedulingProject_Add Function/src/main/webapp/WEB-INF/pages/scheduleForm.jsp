<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix="mvc" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scheduling Form</title>
</head>
<body>
	<h2>Your Personal Schedule</h2>
	
	<h3>Use the Form Below to Create a New Event!</h3>
	
	<mvc:form modelAttribute="event" action="add.mvc">
	<table>
	   <tr>
	        <td><mvc:label path="eventName">Event Name:</mvc:label></td>
	        <td><mvc:input path="eventName" /></td>
	    </tr>
	    <tr>
	        <td><mvc:label path="eventDescription">Event Description:</mvc:label></td>
	        <td><mvc:textarea path="eventDescription" /></td>
	    </tr>
	    <tr>
	        <td><mvc:label path="eventDate">Event Date:</mvc:label></td>
	        <td><mvc:input path="eventDate" /></td>
	    </tr>
   	    <tr>
	        <td><mvc:label path="eventStartTime">Event Starting Time:</mvc:label></td>
	        <td><mvc:input path="eventStartTime" /></td>
	    </tr>
	    <tr>
	        <td><mvc:label path="eventEndTime">Event End Time:</mvc:label></td>
	        <td><mvc:input path="eventEndTime" /></td>
	    </tr>
        <tr>
	        <td><mvc:label path="eventAddress">Event Address:</mvc:label></td>
	        <td><mvc:input path="eventAddress" /></td>
	    </tr>
        <tr>
	        <td colspan="2">
                <input type="submit" value="Add an Event" />
	        </td>
	    </tr>
	</table>  
</mvc:form>		
</body>
</html>