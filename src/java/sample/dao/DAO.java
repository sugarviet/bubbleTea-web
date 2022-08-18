/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import javax.servlet.http.HttpSession;
import sample.entity.Order;
import sample.entity.OrderDetail;
import sample.entity.Product;
import sample.entity.User;
import sample.shopping.Cart;
import sample.utils.DBUtils;

/**
 *
 * @author VietDang
 */
public class DAO {

    //Login    
    private static final String LOGIN = "SELECT fullName, roleID FROM tblUsers WHERE userID=? AND password=?"; 
    
    //User
    private static final String CREATE_USER = "INSERT INTO tblUsers(userID, fullName, roleID, password) VALUES (?, ?, ?, ?)";
    private static final String SEARCH_USER = "SELECT userID, fullName, roleID FROM tblUsers WHERE fullName LIKE ?";
    private static final String UPDATE_USER = "UPDATE tblUsers SET fullName=?, roleID=? WHERE userID=?";
    private static final String DELETE_USER = "DELETE FROM tblUsers WHERE userID=?";
    
    //CheckDuplicate    
    private static final String DUPLICATE = "SELECT userID FROM tblUsers WHERE userID=?";
    
    //Product    
    private static final String GET_ALL_PRODUCT = "SELECT productID, [name], [image], price, quantity, categoryID FROM tblProduct";
    
    private static final String SEARCH_PRODUCT = "SELECT productID, [name], [image], price, quantity, categoryID FROM tblProduct WHERE [name] LIKE ?";
    
    private static final String CREATE_PRODUCT = "INSERT INTO tblProduct(productID, [name], [image], price, quantity, categoryID) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE tblProduct SET [name]=?, [image]=?, price=?, quantity=? WHERE productID=?";
    private static final String DELETE_PRODUCT = "DELETE FROM tblProduct WHERE productID=?";
    private static final String GET_PRODUCT_BY_ID = "SELECT productID, [name], [image], price, quantity, categoryID FROM tblProduct WHERE productID=?";
    private static final String GET_PRODUCT_QUANTITY = "SELECT quantity FROM tblProduct WHERE productID=?";
    
    
    //Order    
    private static final String DELETE_ORDER = "DELETE FROM tblOrder WHERE orderID=?";
    private static final String UPDATE_ORDER = "UPDATE tblOrder SET [address], [date]=?, total=? WHERE orderID=?";
    private static final String SEARCH_ORDER = "SELECT orderID, userID, [address], [date], total FROM tblOrder WHERE userID LIKE ?";
    private static final String GET_ALL_ORDER = "SELECT orderID, userID, [address], [date], total FROM tblOrder ORDER BY orderID DESC";
    
    //Order Detail    
    private static final String UPDATE_ORDER_DETAIL = "UPDATE tblOrderDetail SET orderDetailID=?, orderID=?, productID=?, price=?, quantity=? WHERE orderDetailID=?";
    private static final String DELETE_ORDER_DETAIL = "DELETE FROM tblOrderDetail WHERE orderDetailID=?";
    private static final String GET_ALL_ORDER_DETAIL = "SELECT orderDetailID, orderID, productID, price, quantity FROM tblOrderDetail ORDER BY orderDetailID DESC";
    
    

    
    
    public User checkLogin(String userID, String password) throws SQLException {
        User user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    user = new User(userID, fullName, roleID, "***");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    //Get All Product
    public List<Product> getAllProduct() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();

        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GET_ALL_PRODUCT);
            rs = ptm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString("productID"), rs.getString("name"), rs.getString("image"), rs.getDouble("price"), rs.getInt("quantity"), rs.getString("categoryID")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return list;
    }

    //Create Product    
    public boolean createProduct(Product product) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_PRODUCT);
                ptm.setString(1, product.getProductID());
                ptm.setString(2, product.getName());
                ptm.setString(3, product.getImage());
                ptm.setDouble(4, product.getPrice());
                ptm.setInt(5, product.getQuantity());
                ptm.setString(6, product.getCategoryID());
                result = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    //Delete Product 
    public boolean deleteProduct(String productID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_PRODUCT);
                ptm.setString(1, productID);
                result = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    //Check Duplicate
    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = true;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DUPLICATE);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    //Create User
    public boolean createUser(User user) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_USER);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getRoleID());
                ptm.setString(4, user.getPassword());
                result = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    //Get user list
    public List<User> getListUser(String searchUser) throws SQLException {
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_USER);
                ptm.setString(1, "%" + searchUser + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String password = "***";
                    list.add(new User(userID, fullName, roleID, password));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return list;
    }

    //Get search product
    public List<Product> getSearchProduct(String searchProduct) throws SQLException {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_PRODUCT);
                ptm.setString(1, "%" + searchProduct + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    String image = rs.getString("image");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String categoryID = rs.getString("categoryID");
                    list.add(new Product(productID, name, image, price, quantity, categoryID));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return list;
    }
    
    //Get search order
    public List<Order> getSearchOrder(String searchOrder) throws SQLException{
        List<Order> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                ptm = conn.prepareStatement(SEARCH_ORDER);
                 ptm.setString(1, "%" + searchOrder + "%");
                rs = ptm.executeQuery();
                while(rs.next()){
                    int orderID = rs.getInt("orderID");
                    String userID = rs.getString("userID");
                    String address = rs.getString("address");
                    String date = rs.getString("date");
                    double total = rs.getDouble("total");
                    list.add(new Order(orderID, userID, address, date, total));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(rs != null){
                rs.close();
            }
            if(ptm != null){
                ptm.close();
            }
            if(conn != null){
                conn.close();
            }
        }
        return list;   
    }

    //Get product quantity
    public int getProductQuantity(String id) throws SQLException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(GET_PRODUCT_QUANTITY);
                ptm.setString(1, id);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return quantity;
    }

    //Get product by id
    public Product getProductByID(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_BY_ID);
                ptm.setString(1, id);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    return new Product(rs.getString("productID"), rs.getString("name"), rs.getString("image"), rs.getDouble("price"), rs.getInt("quantity"), rs.getString("categoryID"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

    //Update product
    public boolean updateProduct(Product product) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                //UPDATE tblProduct SET [name]=?, [image]=?, price=?, quantity=?, categoryID=? WHERE productID=?
                ptm = conn.prepareStatement(UPDATE_PRODUCT);
                ptm.setString(1, product.getName());
                ptm.setString(2, product.getImage());
                ptm.setDouble(3, product.getPrice());
                ptm.setInt(4, product.getQuantity());
                ptm.setString(5, product.getProductID());
                result = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    //Update user
    public boolean updateUser(User user) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_USER);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getRoleID());
                ptm.setString(3, user.getUserID());
                result = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    //Update order    
    public boolean updateOrder(Order order) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            //"UPDATE tblOrder SET [date]=?, total=? WHERE orderID=?";
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_ORDER);
                
                ptm.setString(1, order.getAddress());
                ptm.setString(2, order.getDate());
                ptm.setDouble(3, order.getTotal());
                ptm.setInt(4, order.getOrderID());
                result = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    //Update order detail
    public boolean updateOrderDetail(OrderDetail orderDetail) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_ORDER_DETAIL);
                //"UPDATE tblOrderDetail SET orderDetailID=?, orderID=?, productID=?, price=?, quantity=? WHERE orderDetailID=?";
               ptm.setInt(1, orderDetail.getOrderDetailID());
               ptm.setInt(2, orderDetail.getOrderID());
               ptm.setString(3, orderDetail.getProductID());
               ptm.setDouble(4, orderDetail.getPrice());
               ptm.setInt(5, orderDetail.getQuantity());
               ptm.setInt(6, orderDetail.getOrderDetailID());
                result = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    //Delete user
    public boolean deleteUser(String userID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_USER);
                ptm.setString(1, userID);
                result = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    //Delete order
    public boolean deleteOrder(int orderID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_ORDER);
                ptm.setInt(1, orderID);
                result = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    //Delete order detail
    public boolean deleteOrderDetail(int orderDetailID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_ORDER_DETAIL);
                ptm.setInt(1, orderDetailID);
                result = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    //Add order
    public void addOrder(User user, Cart cart, String address, double total) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                LocalDate curDate = LocalDate.now();
                String date = curDate.toString();
                String ADD_TO_ORDER = "INSERT INTO tblOrder(userID, [address], [date], total) VALUES(?, ?, ?, ?)";
                ptm = conn.prepareStatement(ADD_TO_ORDER);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, address);
                ptm.setString(3, date);
                ptm.setDouble(4, total);
                ptm.executeUpdate();

                //GET ORDER'ID just ADDED
                String GET_ORDER_ID = "SELECT TOP 1 orderID FROM tblOrder ORDER BY orderID DESC";
                ptm = conn.prepareStatement(GET_ORDER_ID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    for (Product product : cart.getCart().values()) {
                        String ADD_TO_ORDER_DETAIL = "INSERT INTO tblOrderDetail(orderID, productID, price, quantity) VALUES(?, ?, ?, ?)";
                        ptm = conn.prepareStatement(ADD_TO_ORDER_DETAIL);
                        ptm.setInt(1, orderID);
                        ptm.setString(2, product.getProductID());
                        ptm.setDouble(3, product.getPrice());
                        ptm.setInt(4, product.getQuantity());
                        ptm.executeUpdate();
                    }
                }
                //UPDATE PRODUCT IN STOCK QUANTITY
                String UPDATE_PRODUCT_QUANITY = "UPDATE tblProduct SET quantity = quantity - ? WHERE productID = ?";
                ptm = conn.prepareStatement(UPDATE_PRODUCT_QUANITY);
                for (Product product : cart.getCart().values()) {
                    ptm.setDouble(1, product.getQuantity());
                    ptm.setString(2, product.getProductID());
                    ptm.executeUpdate();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }
    
    //Get all user
    public List<User> getAllUser() throws SQLException{
        List<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                String GET_ALL_USER = "SELECT userID, fullName, roleID, password FROM tblUsers";
                ptm = conn.prepareStatement(GET_ALL_USER);
                rs = ptm.executeQuery();
                while(rs.next()){
                    list.add(new User(rs.getString("userID"), rs.getString("fullName"), rs.getString("roleID"), rs.getString("password")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    //Get order detail list
    public List<OrderDetail> getListOrderDetail() throws SQLException {
        List<OrderDetail> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            
            conn = DBUtils.getConnection();
            if(conn != null){
                ptm = conn.prepareStatement(GET_ALL_ORDER_DETAIL);
                rs = ptm.executeQuery();
                while(rs.next()){
                    list.add(new OrderDetail(rs.getInt("orderDetailID"), rs.getInt("orderID"), rs.getString("productID"), rs.getDouble("price"), rs.getInt("quantity")));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    //Get all order
    public List<Order> getAllOrder() throws SQLException{
        List<Order> list = new ArrayList<>();
         Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                
                ptm = conn.prepareStatement(GET_ALL_ORDER);
                rs = ptm.executeQuery();
                while(rs.next()){
                    list.add(new Order(rs.getInt("orderID"), rs.getString("userID"),rs.getString("address") ,rs.getString("date"), rs.getDouble("total")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
             if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
        return list;
    }

    public static void main(String[] args) throws SQLException {
        DAO dao = new DAO();
        List<Order> list = dao.getSearchOrder("a");
        for (Order o : list) {
            System.out.println(o);
        }
       
       

    }
}
