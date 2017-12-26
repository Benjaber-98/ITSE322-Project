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
import itse322.project.Models.Teacher;
import java.sql.*;
import java.util.HashSet;

/**
 *
 * @author Mahmoud
 */
public class StudentController {
    
    public HashSet<Student> getAllStudents() {
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
    
    public Student getStudentById(Integer id)  {
        Connection c = null; 
        PreparedStatement s = null;
        ResultSet rs = null;
        Student student = new Student();
        try {
            c = DbConnection.dbConnect();
            String sql = "SELECT * FROM students s WHERE s.sid = ?";
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            rs = s.executeQuery();
            if(rs.next()) {
                student.setId(rs.getInt("s.sid"));
                student.setFirstName(rs.getString("s.first_name"));
                student.setLastName(rs.getString("s.last_name"));
                student.setAge(rs.getInt("s.age"));
                student.setPhoneNumber(rs.getString("s.phone_number"));                
            }
            
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
        
        return student;
        
    }
    
    public Student getStudentByIdWithCourses(Integer id) {
        Connection c = DbConnection.dbConnect(); 
        PreparedStatement s = null;
        ResultSet rs = null;
        
        Student student = getStudentById(id);
        try {
        
            String sql = "SELECT * FROM courses c "
                    + " INNER JOIN student_takes_course sc ON c.cid = sc.cid"
                    + " INNER JOIN teacher_teaches_course tc ON c.cid = tc.cid"
                    + " INNER JOIN teachers t ON tc.tid = t.tid"
                    + " WHERE sc.sid = ?";

            s = c.prepareStatement(sql);
            s.setInt(1, id);
            rs = s.executeQuery();
            while(rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("c.cid"));
                course.setCourseName(rs.getString("c.course_name"));
                course.setStartDate(rs.getDate("c.start_date").toString());
                course.setEndDate(rs.getDate("c.end_date").toString());
                course.setHours(rs.getInt("c.hours"));
                Teacher teacher = new Teacher();
                teacher.setFirstName(rs.getString("t.first_name"));
                teacher.setLastName(rs.getString("t.last_name"));
                course.setTeacher(teacher);
                student.addCourse(course);
            }
        } catch(SQLException ex) {
            Message.showWarningMessage(ex+"");
        } finally {
       
            try {
                rs.close();
                s.close();
                c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
            }
        }
        
        return student;
        
    }
    
    public void addStudent(Student student) {
        Connection c = null;
        PreparedStatement s = null;
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
                Message.showWarningMessage("This id is already exists for a student !");
            }
            //Message.showWarningMessage(ex.toString());
            System.out.println(ex);
        } finally {
            try {
                s.close();
                c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
            } 
            
        }
    }
    
    public void updateStudent(Student student) {
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
            Message.showWarningMessage(ex.toString());
        } finally {
            try {
                s.close();
                c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
            } 
            
        }
    }
    
    
    public void deleteStudent(int id) {
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = DbConnection.dbConnect();
            String query = "DELETE FROM students WHERE sid = ?";
            
            s = c.prepareStatement(query);
            s.setInt(1, id);
            
            s.executeUpdate();
            
        } catch (SQLException ex) {
            Message.showWarningMessage(ex.toString());
        } finally {
            try {
                s.close();
                c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
            } 
            
        }
    }
    
    public void addCourseToStudent(int sid, int cid){
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = DbConnection.dbConnect();
            String query = "INSERT INTO student_takes_course VALUES(?, ?);";
            
            s = c.prepareStatement(query);
            s.setInt(1, cid);
            s.setInt(2, sid);
            
            s.executeUpdate();
            
        } catch (SQLException ex) {
            if(ex.getErrorCode() == 1062) {
                Message.showWarningMessage("This student already takes this course !");
            }
            //Message.showWarningMessage(ex.toString());
            System.out.println(ex);
        } finally {
            try {
                s.close();
                c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
            } 
            
        }
    }
    
    public void deleteRegestrationForStudent(int sid, int cid) {
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = DbConnection.dbConnect();
            String query = "DELETE FROM student_takes_course WHERE sid = ? AND cid = ?";
            
            s = c.prepareStatement(query);
            s.setInt(1, sid);
            s.setInt(2, cid);
            
            s.executeUpdate();
            
        } catch (SQLException ex) {
            Message.showWarningMessage(ex.toString());
        } finally {
            try {
                s.close();
                c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
            } 
            
        }
    }
    
}
