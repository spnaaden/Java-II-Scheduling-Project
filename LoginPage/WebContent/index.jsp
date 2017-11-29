<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<title>Scheduling App Login Page</title>
	
	<style>
	
		body{
			background-color: AliceBlue;
		}
		
		h1{
			margin-top: 40px;
			margin-bottom: 50px;
			text-align: center;
		}
		
		div{
			margin: auto;
		}
		
		input{
			margin-top: 20px;
			transform: scale(1.5, 1.5);
		}
	
		span{
			margin-left: -30px;
			font-size: 20px;
		}
		
		#usernameDiv{
			float: left;
			margin-left: 540px;
			margin-right: 90px;
		}
		
		#loginButton{
			display: block;
			margin: auto;
			margin-top: 40px;
		}
		
		p{
			text-align: center;
			margin-top: 30px;
			color: #6699cc;
		}
		
		a{
			color: #4080bf;
		}
		
		a:hover{
			color: #9fbedf;
		}
		
		a:visited{
			color: #4080bf;
		}
		
		
	</style>
	
</head>

<body>

	<h1>Scheduling App Login Page</h1>
	<form>
		<div id="usernameDiv">
			<span>Username:</span><br>
			<input type="text" id="usernameText" required>
		</div>
		
		<div id="passwordDiv">
			<span>Password:</span> <br>
			<input type="password" id="passwordText" required>
		</div>
		
		
		 <input id="loginButton" type="submit" value="Login">
		 
		 <p><em>Don't have an account? <a href="#">Sign up here!</a></em></p>
	</form>


</body>

</html>