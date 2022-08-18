
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link
    href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
    rel="stylesheet"
    id="bootstrap-css"
    />
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <title>Product Detail</title>
        <link rel="stylesheet" href="./productDetail.css" />
        <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
    />
    <link
        <link
            href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
            rel="stylesheet"
            />
        <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
            rel="stylesheet"
            />
    </head>
    <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.getRoleID() != 'US'}">
        <c:redirect url="loginPage.jsp"></c:redirect>
    </c:if>

    <body>
        <div class="container">
            <div class="card">
                <div class="container-fliud">
                    <div class="wrapper row">
                        <div class="preview col-md-6">
                            <div class="preview-pic tab-content">
                                <div class="tab-pane active" id="pic-1">
                                    <img src="${sessionScope.PRODUCT_DETAIL.image}" />
                                </div>
                                <div class="tab-pane" id="pic-2">
                                    <img src="http://placekitten.com/400/252" />
                                </div>
                                <div class="tab-pane" id="pic-3">
                                    <img src="http://placekitten.com/400/252" />
                                </div>
                                <div class="tab-pane" id="pic-4">
                                    <img src="http://placekitten.com/400/252" />
                                </div>
                                <div class="tab-pane" id="pic-5">
                                    <img src="http://placekitten.com/400/252" />
                                </div>
                            </div>
                            <!-- <ul class="preview-thumbnail nav nav-tabs">
                              <li class="active">
                                <a data-target="#pic-1" data-toggle="tab"
                                  ><img src="http://placekitten.com/200/126"
                                /></a>
                              </li>
                              <li>
                                <a data-target="#pic-2" data-toggle="tab"
                                  ><img src="http://placekitten.com/200/126"
                                /></a>
                              </li>
                              <li>
                                <a data-target="#pic-3" data-toggle="tab"
                                  ><img src="http://placekitten.com/200/126"
                                /></a>
                              </li>
                              <li>
                                <a data-target="#pic-4" data-toggle="tab"
                                  ><img src="http://placekitten.com/200/126"
                                /></a>
                              </li>
                              <li>
                                <a data-target="#pic-5" data-toggle="tab"><img src="" /></a>
                              </li>
                            </ul> -->
                        </div>
                        <div class="details col-md-6">
                            <h3 class="product-title">${sessionScope.PRODUCT_DETAIL.name}</h3>
                            <div class="rating">
                                <div class="stars">
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star checked"></span>
                                    <span class="fa fa-star"></span>
                                    <span class="fa fa-star"></span>
                                </div>
                                <span class="review-no">41 reviews</span>
                            </div>
                            <p class="product-description">
                                Suspendisse quos? Tempus cras iure temporibus? Eu laudantium
                                cubilia sem sem! Repudiandae et! Massa senectus enim minim
                                sociosqu delectus posuere.
                            </p>
                            <h4 class="price">current price: <span>$${sessionScope.PRODUCT_DETAIL.price}</span></h4>
                            <p class="vote">
                                <strong>91%</strong> of buyers enjoyed this product!
                                <strong>(87 votes)</strong>
                            </p>
                            <!-- <h5 class="sizes">
                              sizes:
                              <span class="size" data-toggle="tooltip" title="small">s</span>
                              <span class="size" data-toggle="tooltip" title="medium">m</span>
                              <span class="size" data-toggle="tooltip" title="large">l</span>
                              <span class="size" data-toggle="tooltip" title="xtra large"
                                >xl</span
                              >
                            </h5> -->
                            <!-- <h5 class="colors">
                              colors:
                              <span
                                class="color orange not-available"
                                data-toggle="tooltip"
                                title="Not In store"
                              ></span>
                              <span class="color green"></span>
                              <span class="color blue"></span>
                            </h5> -->
                            
                            <form action="MainController">
                                <div class="action">
                                    <button class="add-to-cart btn btn-default" type="submit" name="action" value="CreateInCart">
                                        add to cart
                                    </button>
                                    <input type="hidden" name="productID" value="${sessionScope.PRODUCT_DETAIL.productID}"/>


                                    <input class="numb-pro" type="number" placeholder="Enter a number of product" name="quantity">

                                    <button class="btn btn-outline-dark" type="submit" name="action" value="ViewCart">
                                        <i class="bi-cart-fill me-1 fa-solid fa-cart-arrow-down"></i>
                                        Cart
                                        <span class="badge bg-dark text-white ms-1 rounded-pill"
                                              >${sessionScope.CART_SIZE}</span
                                        >
                                    </button>
                                </div>
                            </form>
                            <a href="userPage.jsp">Add more</a>
                        </div>
                        
                    </div>
                </div>
            </div>
                                    <%
                            String message = (String) request.getAttribute("ADD_TO_CART_MESSAGE");
                            String notANumber = (String)request.getAttribute("NUMBER_NOT_TRUE");
                            if (message == null) {
                                message = "";
                            }
                            if (notANumber == null) {
                                notANumber = "";
                            }
                        %>
                        <%= message%>
                        <%= notANumber %>
        </div>
                                    
    </body>
</html>

