/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itse322.project;

import itse322.project.Frames.LoginPage;
import org.apache.log4j.Logger;

/**
 *
 * @author Mahmoud
 */
public class ITSE322Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Logger log = Logger.getLogger(ITSE322Project.class);
        LoginPage login = new LoginPage();
        login.setVisible(true);
    }
    
}
