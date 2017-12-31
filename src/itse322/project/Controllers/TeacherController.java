/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itse322.project.Controllers;

import itse322.project.DbConnection;
import itse322.project.Models.Course;
import itse322.project.Models.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mahmoud
 */
public class TeacherController {
    
    public HashSet<Teacher> getAllTeachers(){
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        HashSet<Teacher> teachers = new HashSet<>();
        try {
            c = DbConnection.dbConnect();
            s = c.createStatement();
            String query = "SELECT * FROM teachers;";
            rs = s.executeQuery(query);
            
            while(rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("tid"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
                teacher.setAge(rs.getInt("age"));
                teacher.setPhoneNumber(rs.getString("phone_number"));
                teachers.add(teacher);
            }
        } catch (SQLException ex) {
            
        } finally {
            try {
                if(rs != null)
                    rs.close();
                if(s != null)
                    s.close();
                if(c != null)
                    c.close();
            } catch (SQLException ex) {
                
            }
        }
        
        
        
        return teachers;
    }
    
    public static Teacher getTeacherById(int id) throws SQLException {
        Connection c = DbConnection.dbConnect();
        String sql = "SELECT * FROM courses c "
                + "INNER JOIN teacher_teaches_course tc ON c.cid = tc.cid "
                + "INNER JOIN teachers t ON t.tid = tc.tid "
                + "WHERE t.tid = ?;";
        PreparedStatement s = c.prepareStatement(sql);
        s.setInt(1, id);
        ResultSet rs = s.executeQuery();
        Teacher teacher = new Teacher();
        if(rs.next()) {
            teacher.setId(rs.getInt("s.sid"));
            teacher.setFirstName(rs.getString("s.first_name"));
            teacher.setLastName(rs.getString("s.last_name"));
            teacher.setAge(rs.getInt("s.age"));
            teacher.setPhoneNumber(rs.getString("s.phone_number"));
        }
        if(rs.first()) {
            Course course = new Course();
            course.setId(rs.getInt("c.cid"));
            course.setCourseName(rs.getString("c.course_name"));
            course.setHours(rs.getInt("c.hours"));
            course.setStartDate(rs.getDate("sc.start_date").toString());
            course.setEndDate(rs.getDate("sc.end_date").toString());
            
            teacher.addCourse(course);
        }
        while(rs.next()) {
            Course course = new Course();
            course.setId(rs.getInt("c.cid"));
            course.setCourseName(rs.getString("c.course_name"));
            course.setHours(rs.getInt("c.hours"));
            course.setStartDate(rs.getDate("sc.start_date").toString());
            course.setEndDate(rs.getDate("sc.end_date").toString());
            
            teacher.addCourse(course);
        }
        rs.close();
        s.close();
        c.close();
        return teacher;
        
    }
    
}
