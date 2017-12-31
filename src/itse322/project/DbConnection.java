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
import org.apache.log4j.Logger;

/**
 *
 * @author Mahmoud
 */
public class DbConnection {
    private static Logger log = Logger.getLogger(DbConnection.class);
    private static Connection connection = null;
    
    public static Connection dbConnect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training_center?useSSL=false", "root", "");

        } catch (ClassNotFoundException | SQLException ex) {
            Message.showWarningMessage("Couldn't connect to database");
            log.error("\n--------Couldn't Connect To Database Error------\n",ex);
        }
        return connection;
    }
    
    
}
