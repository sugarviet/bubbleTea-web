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
import javax.servlet.http.HttpSession;
import sample.dao.DAO;
import sample.entity.Product;
import sample.shopping.Cart;

/**
 *
 * @author VietDang
 */
public class CheckOutCartController extends HttpServlet {

     private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "lastUserInfo.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            double total = Double.parseDouble(request.getParameter("total"));
            HttpSession session = request.getSession();
            DAO dao = new DAO();
           if (session != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) {
                    for (Product product : cart.getCart().values()) {
                        int stockQty = dao.getProductQuantity(product.getProductID());
                        if(product.getQuantity() > stockQty){
                            request.setAttribute("QTY_MESSAGE", "The product "+product.getName()+" is not enough!!, only "+stockQty+" left");
                            return;
                        }
                    }
//                    User user = (User) session.getAttribute("LOGIN_USER");
//                    dao.addOrder(user, cart, total);
//                    request.setAttribute("ADD_SUCCESS", "YAYYYYYYYYY");
                    
                    url = SUCCESS;
                }
           }
        } catch (Exception e) {
            log("Error at CheckOutCartController: "+e.toString());
        } finally{
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
