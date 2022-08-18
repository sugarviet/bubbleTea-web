
<%@page import="sample.entity.ErrorMsg"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="./css/main.css" />
    <title>Sign-up</title>
  </head>
  <body>
      <%
       ErrorMsg userError = (ErrorMsg)request.getAttribute("USER_ERROR");
            if(userError == null){
                userError = new ErrorMsg();
            }   
       %>
    <h1>Create new user</h1>
    <div class="cover-img">
      <form action="MainController" class="container" method="POST">
        <label for="userID"><b>UserID</b></label>
        <input type="text" placeholder="Enter UserID" name="userID" value="<%= userError.getUserIDError() %>"/>

        <label for="fullName"><b>FullName</b></label>
        <input type="text" placeholder="Enter fullName" name="fullName" value="<%= userError.getFullNameError()%>"/>

        <input type="hidden" name="roleID" value="US" />

        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" />

        <label for="confirm"><b>Confirm</b></label>
        <input type="password" placeholder="Enter confirm" name="confirm" value="<%= userError.getConfirmError() %>"/>

        <input type="submit" class="btn" name="action" value="Create" />
        <input type="reset" class="btn rs-btn" value="Reset" />
      </form>
    </div>
  </body>
</html>
