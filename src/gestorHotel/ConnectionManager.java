/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestorHotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class ConnectionManager {
    private static final String DRIVER ="com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASS ="";
    private static final String URL ="jdbc:mysql://localhost:3306/hoteldb";
    
    private ConnectionManager(){
        
    }
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro!" + ex);
        }
    }
    
    public static void closeConnection(Connection con){
        if (con!= null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }
    
    public static void closeConnection(Connection con, PreparedStatement ps){
        closeConnection(con);
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement ps,ResultSet rs){
        closeConnection(con, ps);
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
