/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itse322.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mahmoud
 */
public class DbConnection {
    private static Connection connection = null;
    
    public static Connection dbConnect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training_center", "root", "");
            return connection;

        } catch (ClassNotFoundException | SQLException ex) {
            Message.viewMessage("Couldn't connect to database");
            return connection;
        }
    }
    
    public static void connection_close() throws SQLException{
        connection.close();
    }
    
}
