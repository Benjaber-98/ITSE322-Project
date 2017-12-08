/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itse322.project;

import javax.swing.JOptionPane;

/**
 *
 * @author Mahmoud
 */
public class Message {
    public static void viewMessage(String m) {
        JOptionPane.showMessageDialog(null, 
                        m, 
                        "Warning", 
                        JOptionPane.WARNING_MESSAGE);
    }
}
