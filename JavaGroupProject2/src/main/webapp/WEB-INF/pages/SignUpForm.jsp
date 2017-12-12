<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://www.springframework.org/tags/form" prefix="mvc" %>
   <%@ page isELIgnored="false" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Account Creation</title>
<link href="Styles/Styler.css" rel="stylesheet" type="text/css"/>
    <style>

        .container{
            width:40%;
            min-width:600px;
            height:auto;
            margin: 0 auto;
            border-radius:20px;
            border-width:5px;
            border-style:dashed;
            border-color:black;
            box-shadow: 0px 0px 26px 7px rgba(179,172,179,1);
            overflow:auto;
            background-color:white;
            overflow-x:hidden;
            transition: .5s height;
        }

        .half{
            width: 48%;
            float:left;
            padding:1%;
        }
        .half label{
            display:inline-block;
            width:80%;
            text-align:right;
        }

        h1{
            text-align:center;
            font-size:3em;

        }

        #errField{
            width:100%;
            text-align:center;
            color:red;
            
        }

        #subButton{
            display:block;
            margin: 0 auto;
        }
        
        
        
    </style>

    
</head>
<body>
   <h1>Sign Up</h1>
     <mvc:form modelAttribute="user" action="signUp.mvc" id="InputForm">
    <div class="container">
        <div class="half">
            <p>
                <label for="userNameTBox">Username:</label>
            </p>

            <p>
                <label for="passCheck1">Password:</label>
            </p>

            <p>
                <label for="passCheck2">Re-enter Password:</label>
            </p>

            <p>
                <label for="fNameTBox">First Name:</label>
            </p>

            <p>
                <label for="lNameTBox">Last Name:</label>
            </p>
        </div>
        <div class="half">
            <p>
                <mvc:input path="userName" id="userNameTBox" />
            </p>

            <p>
                <mvc:input path="password" id="passCheck1" />
            </p>

            <p>
                <input type="password" id="passCheck2" />
            </p>

            <p>
                <mvc:input path="fName"  id="fNameTBox" />
            </p>

            <p>
                <mvc:input path="lName" id="lNameTBox" />
            </p>
        </div>

        <br /><br />
        <p>
        <input id="subButton" type="submit" value="Submit"/>
        </p>

        <p id="errField">${error.getErrMsg() }</p>
    </div>
</mvc:form>
    <script>
        var passCheck1Elem = document.getElementById("passCheck1"),
            passCheck2Elem = document.getElementById("passCheck2"),
            errorElem = document.getElementById("errField");

        var validMatch = false; // A boolean only to be used to not attempt a database event if the password creation boxes do not match;

        passCheck1Elem.addEventListener("change", passChange);
        passCheck2Elem.addEventListener("change", passChange);
        function passChange() {
            var p1 = passCheck1Elem.value,
                p2 = passCheck2Elem.value;

            if (p1 != p2) {
                errorElem.innerHTML = "Passwords do not match.";
                validMatch = false;
                document.getElementById("subButton").type = "button";
                
            }
            else
            {
                validMatch = true;
     			errorElem.innerHTML = "${error.getErrMsg()}";
                document.getElementById("subButton").type = "submit";
            }
            
            
        }
    </script>
</body>
</html>