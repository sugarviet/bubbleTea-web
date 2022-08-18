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
import sample.email.Account;
import sample.email.Email;
import sample.entity.Order;
import sample.entity.User;
import sample.shopping.Cart;

/**
 *
 * @author VietDang
 */
public class MakeAPaymentController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "thankYou.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       String url = ERROR;
        try {
            
            String email = request.getParameter("email");
            int phone = Integer.parseInt(request.getParameter("phone"));
            String address = request.getParameter("address");   
            String note = request.getParameter("note");
            double total = Double.parseDouble(request.getParameter("total"));
            
            
             HttpSession session = request.getSession();
             User user = (User) session.getAttribute("LOGIN_USER");
             Cart cart = (Cart) session.getAttribute("CART");
             
            String subject = "Your order has been processing.";
            String message = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "    <h3 style=\"color: blue;\">Your order has been processing.</h3>\n"
                + "    <div>Full Name :"+user.getFullName()+"</div>\n"
                + "    <div>Phone : "+phone+"</div>\n"
                + "    <div>address :"+ address +"</div>\n" 
                + "    <div>Your note :"+ note +"</div>\n" 
                + "    <div>Your total bill:"+ total +"</div>\n"
                + "    <h3 style=\"color: blue;\">Thank you very much!</h3>\n"
                + "\n"
                + "</body>\n"
                + "\n"
                + "</html>";
           
        Email.send(email, subject, message, Account.EMAIL, Account.PASSWORD);
        DAO dao = new DAO();
        dao.addOrder(user, cart, address, total);
        List<Order> orderList = dao.getAllOrder();
        session.setAttribute("ORDER_LIST", orderList);
        url = SUCCESS;
            
        } catch (Exception e) {
            log("Error at MakeAPaymentController: "+e.toString());
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
