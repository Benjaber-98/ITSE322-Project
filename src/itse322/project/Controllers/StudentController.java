/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itse322.project.Controllers;

import itse322.project.DbConnection;
import itse322.project.Model.Course;
import itse322.project.Model.Student;
import java.sql.*;
import java.util.HashSet;

/**
 *
 * @author Mahmoud
 */
public class StudentController {
    
    public static HashSet<Student> getAllStudents() {
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        try {
            c = DbConnection.dbConnect();
            s = c.createStatement();
            String query = "SELECT * FROM Students;";
            rs = s.executeQuery(query);
            HashSet<Student> students = new HashSet<>();
            while(rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("sid"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setAge(rs.getInt("age"));
                student.setPhoneNumber(rs.getString("phone_number"));
                students.add(student);
                
            }
            return students;
        } catch (SQLException ex) {
            DbConnection.viewMessage(ex.toString());
        } finally {
            try {
                rs.close();
                s.close();
                c.close();
            } catch(SQLException ex) {
                DbConnection.viewMessage(ex.toString());
            }
            
        }
        
        return null;
    }
    
    public static Student getStudentById(int id) throws SQLException {
        Connection c = null; 
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            c = DbConnection.dbConnect();
            String sql = "SELECT * FROM courses c "
                    + "INNER JOIN student_takes_course sc ON c.cid = sc.cid "
                    + "INNER JOIN students s ON s.sid = sc.sid "
                    + "WHERE s.sid = ?;";
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            rs = s.executeQuery();
            Student student = new Student();
            if(rs.next()) {
                student.setId(rs.getInt("s.sid"));
                student.setFirstName(rs.getString("s.first_name"));
                student.setLastName(rs.getString("s.last_name"));
                student.setAge(rs.getInt("s.age"));
                student.setPhoneNumber(rs.getString("s.phone_number"));
            }
            if(rs.first()) {
                Course course = new Course();
                course.setId(rs.getInt("c.cid"));
                course.setCourseName(rs.getString("c.course_name"));
                course.setHours(rs.getInt("c.hours"));
                course.setStartDate(rs.getDate("sc.start_date").toString());
                course.setEndDate(rs.getDate("sc.end_date").toString());

                student.addCourse(course);
            }
            while(rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("c.cid"));
                course.setCourseName(rs.getString("c.course_name"));
                course.setHours(rs.getInt("c.hours"));
                course.setStartDate(rs.getDate("sc.start_date").toString());
                course.setEndDate(rs.getDate("sc.end_date").toString());

                student.addCourse(course);
                
            }
            
            return student;
            
            
        } catch (SQLException ex) {
            DbConnection.viewMessage(ex.toString());
        } finally {
            try {
                rs.close();
                s.close();
                c.close();
            } catch(SQLException ex) {
                DbConnection.viewMessage(ex.toString());
            }
            
        }
        
        return null;
        
    }
    
}
