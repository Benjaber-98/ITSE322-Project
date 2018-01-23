/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itse322.project;

/**
 *
 * @author Mahmoud
 */
public class LoggedUser {
    private static String username;
    public static String getUsername(){
        return LoggedUser.username;
    }
    public static void setUsername(String username) {
        LoggedUser.username = username;
    }
}
