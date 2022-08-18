<%-- 
    Document   : lastUserInfo
    Created on : Jun 24, 2022, 3:23:07 PM
    Author     : VietDang
--%>

<%@page import="sample.entity.Product"%>
<%@page import="sample.shopping.Cart"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css"
            />
        <link rel="stylesheet" href="./css/finalCss.css" />

        <title>Document</title>
    </head>
    
    <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.getRoleID() != 'US'}">
        <c:redirect url="loginPage.jsp"></c:redirect>
    </c:if>
    <body>
        <%
            Cart cart = (Cart) session.getAttribute("CART");
            double total = 0;
            if (cart != null && cart.getCart().size() > 0) {

        %>
        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-11">
                    <div class="card card0 rounded-0">
                        <div class="row">
                            <div class="col-md-5 d-md-block d-none p-0 box">
                                <div class="card rounded-0 border-0 card1" id="bill">
                                    <h3 id="heading1">Payment Summary</h3>
                                    
                                    <%      
                                            for (Product product : cart.getCart().values()) {
                                            total += product.getPrice() * product.getQuantity();
                                    %>
                                    <div class="row">
                                        <div class="col-lg-7 col-8 mt-4 line pl-4">
                                            <h2 class="bill-head"><%= product.getName() %></h2>
                                            <small class="bill-date">Quantity: <%= product.getQuantity() %></small>
                                        </div>
                                        <div class="col-lg-5 col-4 mt-4">
                                            <h2 class="bill-head px-xl-5 px-lg-4"><%= product.getPrice() %></h2>
                                        </div>
                                    </div>
                                    <%
                                        }
                                    %>
                                    <div class="row">
                                        <div class="col-md-12 red-bg">
                                            <p class="bill-date" id="total-label">Total Price</p>
                                            <h2 class="bill-head" id="total"><%= total %>$</h2>
                                            <small class="bill-date" id="total-label"
                                                   >Price includes all taxes</small
                                            >
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-7 col-sm-12 p-0 box">
                                <div class="card rounded-0 border-0 card2" id="paypage">
                                    <div class="form-card">
                                        <form action="MainController">
                                        
                                        <h2 id="heading2" class="text-danger">Payment Method</h2>
                                        <div class="radio-group">
                                            <div class="radio" data-value="credit">
                                                <img
                                                    src="https://i.imgur.com/28akQFX.jpg"
                                                    width="200px"
                                                    height="60px"
                                                    />
                                            </div>
                                            <div class="radio" data-value="paypal">
                                                <img
                                                    src="https://i.imgur.com/5QFsx7K.jpg"
                                                    width="200px"
                                                    height="60px"
                                                    />
                                            </div>
                                            <br />
                                        </div>
                                        <label class="pay">Email</label>
                                        <input
                                            type="text"
                                            name="email"
                                            placeholder="danghoangviet@fpt.edu.vn"
                                            />
                                        <div class="row">
                                            <div class="col-8 col-md-6">
                                                <label class="pay">Address</label>
                                                <input
                                                    type="text"
                                                    name="address"
                                                    id="cr_no"
                                                    placeholder="FPT University district 9"
                                                    minlength="19"
                                                    maxlength="100"
                                                    />
                                            </div>
                                            <div class="col-4 col-md-6">
                                                <label class="pay">Phone</label>
                                                <input
                                                    type="number"
                                                    name="phone"
                                                    placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;"
                                                    class="placeicon"
                                                    minlength="10"
                                                    maxlength="10"
                                                    required=""
                                                    />
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <label class="pay">Note</label>
                                            </div>
                                            <div class="col-md-12">
                                                <input
                                                    type="text"
                                                    name="note"
                                                    id="exp"
                                                    placeholder="*There is anything else you want to tell us about your order?"
                                                    
                                                    />
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <input
                                                    type="submit"
                                                    name="action"
                                                    value="Make a payment"
                                                    
                                                    class="btn btn-info placeicon"
                                                    />
                                                <input type="hidden" name="total" value="<%= total %>"/>
                                            </div>
                                        </div>
                                        </form>
                                            
                                            
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="./js/finalJS.js"></script>
    </body>
    <%      }
    %>
</html>


