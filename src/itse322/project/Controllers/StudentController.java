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
            String query = "SELECT * FROM Students ORDER BY sid;";
            rs = s.executeQuery(query);
            HashSet<Student> students = new HashSet<>();
            while(rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("sid"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setAge(rs.getInt("age"));
                student.setGender(rs.getString("gender"));
                student.setPhoneNumber(rs.getString("phone_number"));
                students.add(student);
                
            }
            return students;
        } catch (SQLException ex) {
            Message.viewMessage(ex.toString());
        } finally {
            try {
                rs.close();
                s.close();
                c.close();
            } catch(SQLException ex) {
                Message.viewMessage(ex.toString());
            }
            
        }
        
        return null;
    }
    
    public static Student getStudentById(Integer id) throws SQLException {
        Connection c = null; 
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            c = DbConnection.dbConnect();
            String sql = "SELECT * FROM students s WHERE s.sid = ?";
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
            /*
            sql = "SELECT * FROM courses c "
                    + " INNER JOIN student_takes_course sc ON c.cid = sc.cid"
                    + " WHERE sc.sid = ?";
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            rs = s.executeQuery();
            while(rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("c.cid"));
                course.setCourseName(rs.getString("c.course_name"));
                course.setId(rs.getInt("c.hours"));
                student.addCourse(course);
            }
*/
            return student;
            
            
        } catch (SQLException ex) {
            Message.viewMessage(ex.toString());
        } finally {
            try {
                rs.close();
                s.close();
                c.close();
            } catch(SQLException ex) {
                Message.viewMessage(ex.toString());
            }
            
        }
        
        return null;
        
    }
    
    public static void addStudent(Student student) {
        Connection c = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            c = DbConnection.dbConnect();
            String query = "INSERT INTO students(first_name"
                    + ", last_name"
                    + ",age"
                    + ", gender"
                    + ", phone_number) VALUES ("
                    + "?, ?, ?, ?, ?);" ;
            
            s = c.prepareStatement(query);
            s.setString(1, student.getFirstName());
            s.setString(2, student.getLastName());
            s.setInt(3, student.getAge());
            s.setString(4, student.getGender());
            s.setString(5, student.getPhoneNumber());
            
            s.executeUpdate();
            
        } catch (SQLException ex) {
            if(ex.getErrorCode() == 1062) {
                Message.viewMessage("This id is already exists for a student !");
            }
            //Message.viewMessage(ex.toString());
            System.out.println(ex);
        } finally {
            try {
                s.close();
                c.close();
            } catch(SQLException ex) {
                Message.viewMessage(ex.toString());
            } 
            
        }
    }
    
    public static void updateStudent(Student student) {
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = DbConnection.dbConnect();
            String query = "UPDATE students SET "
                    + "first_name = ?,"
                    + "last_name = ?,"
                    + "age = ?,"
                    + "gender = ?,"
                    + "phone_number = ?"
                    + " WHERE sid = ?;" ;
            
            s = c.prepareStatement(query);
            s.setString(1, student.getFirstName());
            s.setString(2, student.getLastName());
            s.setInt(3, student.getAge());
            s.setString(4, student.getGender());
            s.setString(5, student.getPhoneNumber());
            s.setInt(6, student.getId());
            
            s.executeUpdate();
            
        } catch (SQLException ex) {
            Message.viewMessage(ex.toString());
        } finally {
            try {
                s.close();
                c.close();
            } catch(SQLException ex) {
                Message.viewMessage(ex.toString());
            } 
            
        }
    }
    
    
    public static void deleteStudent(int id) {
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = DbConnection.dbConnect();
            String query = "DELETE FROM students WHERE sid = ?";
            
            s = c.prepareStatement(query);
            s.setInt(1, id);
            
            s.executeUpdate();
            
        } catch (SQLException ex) {
            Message.viewMessage(ex.toString());
        } finally {
            try {
                s.close();
                c.close();
            } catch(SQLException ex) {
                Message.viewMessage(ex.toString());
            } 
            
        }
    }
    
}
