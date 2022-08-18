
<%@page import="sample.entity.Product"%>
<%@page import="sample.shopping.Cart"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <!--  This file has been downloaded from bootdey.com @bootdey on twitter -->
        <!--  All snippets are MIT license http://bootdey.com/license -->
        <title>Shopping Cart</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
     <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.getRoleID() != 'US'}">
        <c:redirect url="loginPage.jsp"></c:redirect>
    </c:if>
    <body>
       

        <div class="container px-3 my-5 clearfix">
            <!-- Shopping cart table -->
            <div class="card">
                <div class="card-header">
                    <h2>Shopping Cart</h2>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <%
                            Cart cart = (Cart) session.getAttribute("CART");
                            double total = 0;
                            if (cart != null && cart.getCart().size() > 0) {

                        %>
                        <table class="table table-bordered m-0">
                            <thead>
                                <tr>
                                    <!-- Set columns width -->
                                    <th class="text-center py-3 px-4" style="min-width: 400px">
                                        Product Name &amp; Details
                                    </th>
                                    <th class="text-right py-3 px-4" style="width: 100px">
                                        Price
                                    </th>
                                    <th class="text-center py-3 px-4" style="width: 120px">
                                        Quantity
                                    </th>
                                    <th class="text-right py-3 px-4" style="width: 100px">
                                        Total
                                    </th>

                                    <th>
                                        Modify    
                                    </th>

                                    <th
                                        class="text-center align-middle py-3 px-0"
                                        style="width: 40px"
                                        >
                                        <a
                                            href="#"
                                            class="shop-tooltip float-none text-light"
                                            title=""
                                            data-original-title="Clear cart"
                                            ><i class="ino ion-md-trash"></i
                                            ></a>
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%      for (Product product : cart.getCart().values()) {
                                        total += product.getPrice() * product.getQuantity();
                                %>
                            <form action="MainController">

                                <tr>
                                    <td class="p-4">
                                        <div class="media align-items-center">
                                            <img
                                                src="<%= product.getImage()%>"
                                                class="d-block ui-w-40 ui-bordered mr-4"
                                                alt=""
                                                />
                                            <div class="media-body">
                                                <a href="#" class="d-block text-dark"><%= product.getName()%></a>
                                                <!--                        <small>
                                                                          <span class="text-muted">Color:</span>
                                                                          <span
                                                                            class="ui-product-color ui-product-color-sm align-text-bottom"
                                                                            style="background: #e81e2c"
                                                                          ></span>
                                                                          &nbsp; <span class="text-muted">Size: </span> EU 37
                                                                          &nbsp;
                                                                          <span class="text-muted">Ships from: </span> China
                                                                        </small>-->
                                            </div>
                                        </div>
                                    </td>
                                    <td class="text-right font-weight-semibold align-middle p-4">
                                        <%= product.getPrice()%>
                                    </td>
                                    <td class="align-middle p-4">
                                        <input
                                            type="number"
                                            class="form-control text-center"
                                            name="quantity" value="<%= product.getQuantity()%>" required=""
                                            />
                                    </td>
                                    <td class="text-right font-weight-semibold align-middle p-4">
                                        $<%= product.getQuantity() * product.getPrice()%>
                                    </td>

                                    <td>
                                        <input type="submit" name="action" value="Modify" class="btn btn-lg btn-primary mt-2"/>
                                        <input type="hidden" name="productID" value="<%= product.getProductID()%>"/>
                                        <input type="hidden" name="quantity" value="<%= product.getQuantity()%>"/>
                                        <input type="hidden" name="total" value="<%= total %>"/>
                                    </td>

                                    <td class="text-center align-middle px-0">
                                        <a
                                            href="MainController?action=DeleteCartItem&productID=<%= product.getProductID()%>"
                                            class="shop-tooltip close float-none text-danger"
                                            title=""
                                            data-original-title="Remove"
                                            >×</a
                                        >
                                    </td>

                                </tr>    
                            </form>
                            <%
                                }
                            %>
                            </tbody>
                        </table>
                        <%
                            }
                        %>
                    </div>
                    <!-- / Shopping cart table -->

                    <div
                        class="d-flex flex-wrap justify-content-between align-items-center pb-4"
                        >
                        <div class="mt-4">
                            <label class="text-muted font-weight-normal">Promocode</label>
                            <input type="text" placeholder="ABC" class="form-control" />
                        </div>
                        <div class="d-flex">
                            <div class="text-right mt-4 mr-5">
                                <label class="text-muted font-weight-normal m-0"
                                       >Discount</label
                                >
                                <div class="text-large"><strong>$0</strong></div>
                            </div>
                            <div class="text-right mt-4">
                                <label class="text-muted font-weight-normal m-0"
                                       >Total price</label
                                >
                                <div class="text-large"><strong><%= total%></strong></div>
                            </div>
                        </div>
                    </div>

                    <form action="MainController">
                        <div class="float-right">
                            <button
                                type="submit"
                                class="btn btn-lg btn-default md-btn-flat mt-2 mr-3" name="action" value="BackToShopping"
                                >
                                Back to shopping
                            </button>
                            <button type="submit" class="btn btn-lg btn-primary mt-2" name="action" value="Checkout">
                                Checkout
                            </button>
                            <input type="hidden" name="total" value="<%= total %>"/>
                            <% 
                                String outOfProductMessage = (String)request.getAttribute("QTY_MESSAGE");
                                String successMsg = (String)request.getAttribute("ADD_SUCCESS");
                                if(outOfProductMessage == null){
                                    outOfProductMessage = "";
                                }
                                if(successMsg == null){
                                    successMsg = "";
                                }
                                
                             %>
                             <%= outOfProductMessage %>
                             <%= successMsg %>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <style type="text/css">
            body {
                margin-top: 20px;
                background: #eee;
            }
            .ui-w-40 {
                width: 40px !important;
                height: auto;
            }

            .card {
                box-shadow: 0 1px 15px 1px rgba(52, 40, 104, 0.08);
            }

            .ui-product-color {
                display: inline-block;
                overflow: hidden;
                margin: 0.144em;
                width: 0.875rem;
                height: 0.875rem;
                border-radius: 10rem;
                -webkit-box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.15) inset;
                box-shadow: 0 0 0 1px rgba(0, 0, 0, 0.15) inset;
                vertical-align: middle;
            }
        </style>

        <script type="text/javascript"></script>
    </body>
</html>


