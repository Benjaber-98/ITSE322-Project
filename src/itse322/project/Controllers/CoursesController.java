/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itse322.project.Controllers;

import itse322.project.DbConnection;
import itse322.project.Message;
import itse322.project.Models.Course;
import itse322.project.Models.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/**
 *
 * @author Mahmoud
 */
public class CoursesController {
    
    public HashSet<String> getCoursesNames() {
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        try {
            c = DbConnection.dbConnect();
            s = c.createStatement();
            String query = "SELECT distinct course_name FROM courses;";
            rs = s.executeQuery(query);
            HashSet<String> courses = new HashSet<>();
            while(rs.next()) {
                courses.add(rs.getString("course_name"));
                
            }
            return courses;
        } catch (SQLException ex) {
            Message.showWarningMessage(ex.toString());
        } finally {
            try {
                rs.close();
                s.close();
                c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
            }
            
        }
        
        return null;
    }
    
    public HashSet<Course> getCourseTimes(String courseName) {
        
        Connection c = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            c = DbConnection.dbConnect();
            String query = "SELECT c.cid, start_date, end_date FROM courses c INNER JOIN teacher_teaches_course tc"
                    + " ON tc.cid = c.cid WHERE c.course_name = ?;";
            s = c.prepareStatement(query);
            s.setString(1, courseName);
            rs = s.executeQuery();
            HashSet<Course> courses = new HashSet<>();
            while(rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("c.cid"));
                course.setStartDate(rs.getDate("start_date").toString());
                course.setEndDate(rs.getDate("end_date").toString());
                courses.add(course);
            }
            return courses;
        } catch (SQLException ex) {
            Message.showWarningMessage(ex.toString());
        } finally {
            try {
                rs.close();
                s.close();
                c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
            }
            
        }
        
        return null;
    }
    
    
    
}
