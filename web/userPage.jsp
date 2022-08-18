<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    />
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="./css/userStyle.css" />
    <link
      rel="shortcut icon"
      href="https://upload.wikimedia.org/wikipedia/commons/thumb/c/cb/V_%28logo_2010%29.svg/1024px-V_%28logo_2010%29.svg.png"
      type="image/x-icon"
    />

    <title>The V's</title>
  </head>
  
  <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.getRoleID() != 'US'}">
            <c:redirect url="loginPage.jsp"></c:redirect>
        </c:if>
  <body>
    <!-- HEADER START -->
    <div class="header-nav">
      <div class="header-img">
        <img
          src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/cb/V_%28logo_2010%29.svg/1024px-V_%28logo_2010%29.svg.png"
          alt="V-Logo"
        />
      </div>
      <div class="header-text">
        <h1>Every Day Drink</h1>
      </div>
      <div class="header-navbar">
        <ul class="header-list">
          <li class="header-item">
            <a href="#">HOME</a>
          </li>
          <li class="header-item">
            <a href="#product">PRODUCT</a>
          </li>
          <li class="header-item">
            <a href="#" class="connect">CONNECT</a>
          </li>
          <li class="header-item">
            <a href="#about">ABOUT US</a>
          </li>
          <li class="header-item">
            <form class="d-flex">
                <button class="btn btn-view-cart" type="submit" name="action" value="ViewCart">
                <i class="bi-cart-fill me-1 fa-solid fa-cart-arrow-down"></i>
                Cart
                <span class="badge bg-dark text-white ms-1 rounded-pill"
                      >${sessionScope.CART_SIZE}</span
                >
              </button>
            </form>
          </li>
          
          <li class="header-item">
              <form action="MainController">
              
              <button class="btn btn-logout" type="submit" name="action" value="Logout">Logout <i class="fa-solid fa-arrow-right-from-bracket"></i></button>
              </form>
          </li>
          
        </ul>
      </div>
    </div>
    <!-- END OF HEADER -->

    <!-- Home -->
    <div class="home" id="">
      <div class="hero-text">
        <blockquote
          cite="https://www.goodreads.com/author/quotes/3253.Pablo_Picasso"
        >
          <span>“</span>It's our joy to serve you<span>”</span>
          <h5>-Viet Dang-</h5>
        </blockquote>
      </div>
    </div>
    <!-- BODY START -->

    <div class="container product" id="product">
      <div class="row">
        <h1>Products</h1>
        
        <c:forEach items="${sessionScope.PRODUCT_LIST}" var="o">
            <div class="col-md-3">
                <form action="MainController">
          <div class="product-img">
            <img src="${o.image}" alt="" />
          </div>
          <div class="product-info">
            <div class="main-info">
              <h4>${o.name}</h4>
              <p>
                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Et
                aspernatur neque voluptatem non pariatur dolorem excepturi totam
                debitis culpa incidunt. Voluptates fuga distinctio ipsum qui,
                quia nisi nulla est nam!
              </p>
            </div>    
          </div>
              <input class="boxer" type="submit" name="action" value="Add to cart"/>
              <input type="hidden" name="productID" value="${o.productID}"/>
              </form>
        </div>
        </c:forEach>
        
        
 
      </div>
    </div>
    <!-- About us -->
    <div class="about-us" id="about">
      <h1>About us</h1>
      <div class="row">
        <div class="about-us-picture col-md-4">
          <div class="abt-img">
            <img
              src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/cb/V_%28logo_2010%29.svg/1024px-V_%28logo_2010%29.svg.png"
              alt=""
            />
          </div>
        </div>
        <div class="about-us-text col-md-8">
          <div class="abt-text">
            <p>
              - Lorem ipsum dolor sit amet consectetur adipisicing elit. Aut sit
              min minus rem, repellat eligendi aut omnis nobis quis maiores
              commodi. Cum, obcaecati vero.
            </p>
          </div>
          <div class="abt-text">
            <p>
              - Lorem ipsum dolor sit amet consectetur adipisicing elit. Aut sit
              minus cum, impedit a eveniet dolorem ipsum error, itaque quo
              recusandae excepturi fuga ex consequatur veniam officiis, suscipit
              alias facilis aliquid corporis pariatur nesciunt minus rem,
              repellat eligendi aut omnis nobis quis maiores commodi. Cum,
              obcaecati vero.
            </p>
          </div>
          <div class="abt-text">
            <p>
              - Lorem ipsum dolor sit amet consectetur adipisicing elit. Aut sit
              minus cum, impedit a eveniet dolorem ipsum error, itaque quo
              recusandae excepturi fuga ex consequatur veniam officiis,
              voluptates eligendi mollitia! Lorem ipsum dolor sit amet cons
              adipisicing elit. Hic nam, suscipit alias facilis aliquid corporis
              pariatur nesciunt minus rem, repellat eligendi aut omnis nobis
              quis maiores commodi. Cum, obcaecati vero.
            </p>
          </div>
        </div>
      </div>
    </div>

    <!-- BODY END -->
    <footer class="footer">
      <div class="footer-contain">
        <div class="footer-text">
          <p class="hehe">Email : V-Shop@gmail.com</p>
          <p>Address: 217/9 Bui Dinh Tuy Binh Thanh District, HCMC</p>
          <h5>&copy; Copyright 2022. Viet Dang</h5>
        </div>
      </div>
    </footer>
  </body>
</html>

