
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="./css/main.css" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    />
    <title>Login Page</title>
  </head>
  <body>
    <div class="bg-img">
      <form id="form" action="MainController" class="container" method="POST">
        <h1>Sign In</h1>

        <label for="userID"><b>UserID</b></label>
        <input type="text" placeholder="Enter UserID" name="userID" />

        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" />
        
        <!-- Recapcha -->
        <div class="g-recaptcha" data-sitekey="6LfyCpIgAAAAAH5vjuyvkqjM4AMYtfdk812233tR"></div>
        <div id="error"></div>
        <!-- Recapcha -->

        <!--<button type="submit" class="btn btn-login" name="action" value="Login">-->
        
        <input value="Login" name="action" hidden=""/>
        <button type="submit" class="btn btn-login" value="Submit"/>
          Login
        </button>
        
        <hr>
        <a
          class="google-login"
          href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8084/Assignment/LoginGoogleHandler&response_type=code
           &client_id=1060595306957-eu1mhjulv0sek2cnbl121a37f2lv3sli.apps.googleusercontent.com&approval_prompt=force"
          >Login With Google <i class="fa-brands fa-google"></i>
        </a>
        </br>
        <hr>
        
        
            <h8>---Create account---</h8>
<!--        <button type="submit" class="btn" name="action" value="MoveToSignUp">
            Sign up
        </button>-->
            
            <a href="signUpPage.jsp" class="sign-up">Sign Up</a>
        </form>
        
     
    </div>
      
      
      <script src="https://www.google.com/recaptcha/api.js" async defer></script>
      
      <script>
          window.onload = function(){
              let isValid = false;
              const form = document.getElementById("form");
              const error = document.getElementById("error");
              
              form.addEventListener("submit", function(e){
                  e.preventDefault();
                  const response = grecaptcha.getResponse();
                  console.log(response);
                  if(response){
                      form.submit();
                  }else{
                      error.innerHTML = "Please check";
                  }
              });
          }
      </script>
  </body>
</html>
