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
public class CreateInCartController extends HttpServlet {

    private static final String ERROR = "productDetailPage.jsp";
    private static final String SUCCESS = "productDetailPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            DAO dao = new DAO();
            HttpSession session = request.getSession();
            if (session != null) {
                if(quantity < 0){
                    request.setAttribute("NUMBER_NOT_TRUE", "The quantity must be > 0");
                    return;
                    
                }
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart == null) {
                    cart = new Cart();
                }
                Product product = dao.getProductByID(productID);
                product.setQuantity(quantity);
                cart.add(product);
                session.setAttribute("CART", cart);
                url = SUCCESS;
                request.setAttribute("ADD_TO_CART_MESSAGE", "You just added successfully "+quantity+"-"+product.getName());
                session.setAttribute("CART_SIZE", cart.getCart().size());
            }
        } catch (Exception e) {
        } finally {
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
