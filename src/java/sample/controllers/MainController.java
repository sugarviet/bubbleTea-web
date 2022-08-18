/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VietDang
 */
public class MainController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    //Login    
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    
    //Signup
    private static final String MOVE_TO_SIGN_UP = "MoveToSignUp";
    private static final String SIGN_UP_PAGE = "signUpPage.jsp";
    
    //Logout  
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogOutController";
    
    //Create    
    private static final String CREATE = "Create";
    private static final String CREATE_CONTROLLER = "CreateController";
    private static final String CREATE_PRODUCT = "CreateProduct";
    private static final String CREATE_PRODUCT_CONTROLLER = "CreateProductController";
    
    
    //Search    
    private static final String SEARCH_USER = "SearchUser";
    private static final String SEARCH_USER_CONTROLLER = "SearchUserController";
    private static final String SEARCH_PRODUCT = "SearchProduct";
    private static final String SEARCH_PRODUCT_CONTROLLER = "SearchProductController";
    private static final String SEARCH_ORDER = "SearchOrder";
    private static final String SEARCH_ORDER_CONTROLLER = "SearchOrderController";
    
    
    //Update
    private static final String UPDATE_USER = "UpdateUser";
    private static final String UPDATE_USER_CONTROLLER = "UpdateUserController";
    private static final String UPDATE_PRODUCT = "UpdateProduct"; 
    private static final String UPDATE_PRODUCT_CONTROLLER = "UpdateProductController";
    private static final String UPDATE_ORDER = "UpdateOrder";
    private static final String UPDATE_ORDER_CONTROLLER = "UpdateOrderController";
    private static final String UPDATE_ORDER_DETAIL = "UpdateOrderDetail";
    private static final String UPDATE_ORDER_DETAIL_CONTROLLER = "UpdateOrderDetailController";
    
    
    //Delete
    private static final String DELETE_USER = "DeleteUser";
    private static final String DELETE_USER_CONTROLLER = "DeleteUserController";
    private static final String DELETE_PRODUCT = "DeleteProduct";
    private static final String DELETE_PRODUCT_CONTROLLER = "DeleteProductController";
    private static final String DELETE_ORDER = "DeleteOrder";
    private static final String DELETE_ORDER_CONTROLLER = "DeleteOrderController";
    private static final String DELETE_ORDER_DETAIL = "DeleteOrderDetail";
    private static final String DELETE_ORDER_DETAIL_CONTROLLER = "DeleteOrderDetailController";
    
    //Cart
    private static final String ADD_TO_CART = "Add to cart";
    private static final String PRODUCT_DETAIL_CONTROLLER = "ProductDetailController";
    private static final String VIEW_CART = "ViewCart";
    private static final String VIEW_CART_PAGE = "viewCart.jsp";    
    private static final String CREATE_IN_CART = "CreateInCart";
    private static final String CREATE_IN_CART_CONTROLLER = "CreateInCartController";
    private static final String REMOVE_IN_CART = "DeleteCartItem";
    private static final String REMOVE_IN_CART_CONTROLLER = "RemoveInCartController";
    private static final String CHECK_OUT_CART = "Checkout";
    private static final String CHECK_OUT_CART_CONTROLLER = "CheckOutCartController";
    private static final String MODIFY_CART = "Modify";
    private static final String MODIFY_CART_CONTROLLER = "ModifyCartController";
    
    //Email
    private static final String MAKE_A_PAYMENT = "Make a payment";
    private static final String MAKE_A_PAYMENT_CONTROLLER = "MakeAPaymentController";
    
    private static final String BACK_TO_SHOPPING = "BackToShopping";
    private static final String BACK_TO_SHOPPING_PAGE = "userPage.jsp";
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if(action.equals(LOGIN)){
                url = LOGIN_CONTROLLER;
            }else if(action.equals(CREATE)){
                url = CREATE_CONTROLLER;
            }else if(action.equals(SEARCH_USER)){
                url = SEARCH_USER_CONTROLLER;
            }else if(action.equals(SEARCH_PRODUCT)){
                url = SEARCH_PRODUCT_CONTROLLER;
            }else if(action.equals(UPDATE_USER)){
                url = UPDATE_USER_CONTROLLER;
            }else if(action.equals(DELETE_USER)){
                url = DELETE_USER_CONTROLLER;
            }else if(action.equals(LOGOUT)){
                url = LOGOUT_CONTROLLER;
            }else if(action.equals(CREATE_PRODUCT)){
                url = CREATE_PRODUCT_CONTROLLER;
            }else if(action.equals(DELETE_PRODUCT)){
                url = DELETE_PRODUCT_CONTROLLER;
            }else if(action.equals(UPDATE_PRODUCT)){
                url = UPDATE_PRODUCT_CONTROLLER;
            }else if(action.equals(ADD_TO_CART)){
                url = PRODUCT_DETAIL_CONTROLLER;
            }else if(action.equals(VIEW_CART)){
                url = VIEW_CART_PAGE;
            }else if(action.equals(CREATE_IN_CART)){
                url = CREATE_IN_CART_CONTROLLER;
            }else if(action.equals(REMOVE_IN_CART)){
                url = REMOVE_IN_CART_CONTROLLER;
            }else if(action.equals(CHECK_OUT_CART)){
                url = CHECK_OUT_CART_CONTROLLER;
            }else if(action.equals(MODIFY_CART)){
                url = MODIFY_CART_CONTROLLER;
            }else if(action.equals(BACK_TO_SHOPPING)){
                url = BACK_TO_SHOPPING_PAGE;
            }else if(action.equals(MOVE_TO_SIGN_UP)){
                url = SIGN_UP_PAGE;
            }else if(action.equals(UPDATE_ORDER)){
                url = UPDATE_ORDER_CONTROLLER;
            }else if(action.equals(UPDATE_ORDER_DETAIL)){
                url = UPDATE_ORDER_DETAIL_CONTROLLER;
            }else if(action.equals(DELETE_ORDER)){
                url = DELETE_ORDER_CONTROLLER;
            }else if(action.equals(DELETE_ORDER_DETAIL)){
                url = DELETE_ORDER_DETAIL_CONTROLLER;
            }else if(action.equals(MAKE_A_PAYMENT)){
                url = MAKE_A_PAYMENT_CONTROLLER;
            }else if(action.equals(SEARCH_ORDER)){
                url = SEARCH_ORDER_CONTROLLER;
            }
            
            
        } catch (Exception e) {
            log("Error at MainController: "+e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
