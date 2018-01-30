/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itse322.project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Mahmoud
 */
public class TimeManager {
    public static String getCurrentTime() {
        //Current Milliseconds
        long currentMilliSeconds = System.currentTimeMillis();

        //The Format Of Timestamp
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 

        //Creating a new Date from milliseconds
        Date currentDate = new Date(currentMilliSeconds);

        //Getting the timestamp in variable
        String time = dateFormat.format( currentDate );
        return time;
    }
}
