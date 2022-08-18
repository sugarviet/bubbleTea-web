/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.DAO;
import sample.entity.Product;
import sample.entity.User;
import sample.entity.UserGoogle;
import sample.utils.GoogleUtils;

/**
 *
 * @author VietDang
 */
public class LoginGoogleHandler extends HttpServlet {
    private final static String ERROR = "error.jsp";
    private final static String SUCCESS = "userPage.jsp";

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
       
        String code = request.getParameter("code");
        String accessToken =  GoogleUtils.getToken(code);
        UserGoogle userInfo =  GoogleUtils.getUserInfo(accessToken);
        
        System.out.println(userInfo);
        
       DAO dao = new DAO();
        HttpSession session = request.getSession();
        boolean checkDuplicate = dao.checkDuplicate(userInfo.getId());
        List<Product> productList = dao.getAllProduct();
        session.setAttribute("PRODUCT_LIST", productList);
     
        if(checkDuplicate){
            User user = new User(userInfo.getId(), userInfo.getName(), userInfo.getRoleID(), userInfo.getPassword());
            boolean check = dao.createUser(user);
            if(check){
                session.setAttribute("LOGIN_USER", user);
                url = SUCCESS;
                return;
            }
            
        }else{
            User user = dao.checkLogin(userInfo.getId(), userInfo.getPassword());
            session.setAttribute("LOGIN_USER", user);
            url = SUCCESS;
            
        }
        
        } catch (Exception e) {
            log("Error at LoginGoogleHandler: "+e.toString());
        }finally{
            request.getRequestDispatcher(SUCCESS).forward(request, response);
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
