/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author anh
 */

public class DAO {
    protected static Connection con;
    
    public DAO(){
        String url = "jdbc:mysql://localhost:3306/banh?autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "mize0305";
        if(con == null){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, username,password);
            } catch(ClassNotFoundException | SQLException ex){
            }
        } 
    }
}
