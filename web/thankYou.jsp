<%-- 
    Document   : thankYou
    Created on : Jun 27, 2022, 6:59:55 AM
    Author     : VietDang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"
    />
    <title>Thank you for shopping</title>
  </head>
  <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.getRoleID() != 'US'}">
        <c:redirect url="loginPage.jsp"></c:redirect>
    </c:if>
  <body>
    <div class="jumbotron text-center">
      <h1 class="display-3">Thank You!</h1>
      <p class="lead">
        <strong>Please check your email</strong> for further instructions on how
        to complete your order setup.
      </p>
      <hr />
      <p>Having trouble? <a href="">Contact us</a></p>
      <p class="lead">
        <a class="btn btn-primary btn-sm" href="userPage.jsp" role="button"
          >Continue to homepage</a
        >
      </p>
    </div>
  </body>
</html>

