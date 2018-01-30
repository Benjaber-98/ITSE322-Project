/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itse322.project;

import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Mahmoud
 */
public class Validator {
    
    public boolean isValid(Object[] fields) {
        JTextField firstName = (JTextField)fields[0];
        JTextField lastName = (JTextField)fields[1];
        JTextField phoneNumber = (JTextField)fields[2];
        
        if(firstName.getText().equals("") || lastName.getText().equals("") || !isNum(phoneNumber.getText()) ) {
            viewMessage();
            return false;
        }
        
        return true;
    }
    
    public boolean isValid(Object[] fields, JTextField hours) { 
        JTextField courseName = (JTextField ) fields[0];
        JDateChooser startDate = (JDateChooser) fields[1];
        JDateChooser endDate = (JDateChooser) fields[2];
        JTextField price = (JTextField) fields[3];
        if( courseName.getText().equals("") || startDate.getDate() == null || endDate.getDate() == null
                || !isNum(hours.getText()) || !isNum(price.getText())
        )
        {
            viewMessage();
            return false;
        }

        return true;
    }
    
    private boolean isNum(String num) {
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher m = pattern.matcher(num);
        if(m.find()) {
            return false;
        }
        return true;
    }
    
    private void viewMessage(){
        Message.showWarningMessage("All Fields Are Required And Should Be Valid");
    }
}
