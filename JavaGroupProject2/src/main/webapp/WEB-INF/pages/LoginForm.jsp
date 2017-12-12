<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://www.springframework.org/tags/form" prefix="mvc" %>
     <%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

        <title>Scheduling App Login Page</title>
<link href="Styles/Styler.css" rel="stylesheet" type="text/css"/>
        <style>
            

            h1 {
                margin-top: 40px;
                margin-bottom: 50px;
                text-align: center;
            }

            input {
                transform:scale(1,1.5);
            }

            .inputField {
                float: left;
                width: 49%;
                padding:.5%;
                min-width:237.972px;
            }

            .inputField input{
                width:90%;
            }
            .inputsDiv{
                
                margin-bottom:20px;
                width:100%;
                overflow:auto;
                overflow-x:hidden;
                overflow-y:hidden;
            }

            #loginButton {
                display: block;
                margin: auto;
                transform:scale(1.5,1.5);

            }

            p {
                text-align: center;
                margin-top: 30px;
                color: #6699cc;
            }

            a {
                color: #4080bf;
            }

                a:hover {
                    color: #9fbedf;
                }

                a:visited {
                    color: #4080bf;
                }

            #loginForm {
                width: 50%;
                margin: 0 auto;
            }
        </style>

    </head>

    <body>

        <h1>Scheduling App Login Page</h1>
        <mvc:form modelAttribute="user" action="login.mvc" id="loginForm">
            <div class="inputsDiv">
            <div id="usernameDiv" class="inputField">
                <span style="padding:8px;">Username:</span><br>
                <mvc:input path="userName" id="userNametBox" />
            </div>

            <div id="passwordDiv" class="inputField">
                <span style="padding:8px;">Password:</span> <br>
                <mvc:input path="password" id="passwordtBox" />
            </div>
                </div>

            <input id="loginButton" type="submit" value="Login">
            <span style="color:red;">${err.errMsg}</span>
            <p><em>Don't have an account? <a href="signUp.mvc">Sign up here!</a></em></p>
            
        </mvc:form>


    </body>

</html>