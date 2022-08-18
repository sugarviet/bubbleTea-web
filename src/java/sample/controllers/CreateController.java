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
import sample.dao.DAO;
import sample.entity.ErrorMsg;
import sample.entity.User;

/**
 *
 * @author VietDang
 */
public class CreateController extends HttpServlet {
    private static final String ERROR = "signUpPage.jsp";
    private static final String SUCCESS = "loginPage.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ErrorMsg error = new ErrorMsg();
        try {
            boolean checkValidate = true;
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            DAO dao = new DAO();
            if(userID.length() > 10 || userID.length() < 3){
                error.setUserIDError("Length must be in [3,10]");
                checkValidate = false;
            }
            boolean checkDuplicate = dao.checkDuplicate(userID);
            if(checkDuplicate == false){
                error.setUserIDError("Duplicate userID");
                checkValidate = false;
            }
            if(fullName.length() > 50 || fullName.length() < 2){
                error.setFullNameError("Length must be in [2,50]");
                checkValidate = false;
            }
             if(!confirm.equals(password)){
                error.setConfirmError("2 passwords are not the same");
                checkValidate = false;
            }
             if(checkValidate){
                 User user = new User(userID, fullName, roleID, password);
                 boolean checkCreate = dao.createUser(user);
                 if(checkCreate){
                     url = SUCCESS;
                 }
             }else{
                 request.setAttribute("USER_ERROR", error);
             }
        } catch (Exception e) {
            log("Error at CreateController: "+e.toString());
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
