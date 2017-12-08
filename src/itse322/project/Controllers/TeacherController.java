/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itse322.project.Controllers;

import itse322.project.DbConnection;
import itse322.project.Model.Course;
import itse322.project.Model.Student;
import itse322.project.Model.Teacher;
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
public class TeacherController {
    
    public static HashSet<Teacher> getAllTeachers() throws SQLException{
        Connection c = DbConnection.dbConnect();
        Statement s = c.createStatement();
        String query = "SELECT * FROM teachers;";
        ResultSet rs = s.executeQuery(query);
        HashSet<Teacher> teachers = new HashSet<>();
        while(rs.next()) {
            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt("tid"));
            teacher.setFirstName(rs.getString("first_name"));
            teacher.setLastName(rs.getString("last_name"));
            teacher.setAge(rs.getInt("age"));
            teacher.setPhoneNumber(rs.getString("phone_number"));
            teachers.add(teacher);
        }
        
        rs.close();
        s.close();
        c.close();
        
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
