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
import org.apache.log4j.Logger;

/**
 *
 * @author Mahmoud
 */
public class TeacherController {
    
    Logger log = Logger.getLogger(Course.class);
    
    public HashSet<Teacher> getAllTeachers() {
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        try {
            c = DbConnection.dbConnect();
            s = c.createStatement();
            String query = "SELECT * FROM teachers ORDER BY tid;";
            rs = s.executeQuery(query);
            HashSet<Teacher> teachers = new HashSet<>();
            while(rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("tid"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
                teacher.setAge(rs.getInt("age"));
                teacher.setGender(rs.getString("gender"));
                teacher.setPhoneNumber(rs.getString("phone_number"));
                teachers.add(teacher);
                
            }
            return teachers;
        } catch (SQLException ex) {
            Message.showWarningMessage(ex.toString());
            log.error("\n--------Error Message------\n",ex);
        } finally {
            try {
                if(rs != null)
                    rs.close();
                if(s != null)
                    s.close();
                if(c != null)
                    c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
                log.error("\n--------Error Message------\n",ex);
                
            }
            
        }
        
        return null;
    }
    
    public Teacher getTeacherById(Integer id)  {
        Connection c = null; 
        PreparedStatement s = null;
        ResultSet rs = null;
        Teacher teacher = new Teacher();
        try {
            c = DbConnection.dbConnect();
            String sql = "SELECT * FROM teachers t WHERE t.tid = ?";
            s = c.prepareStatement(sql);
            s.setInt(1, id);
            rs = s.executeQuery();
            if(rs.next()) {
                teacher.setId(rs.getInt("t.tid"));
                teacher.setFirstName(rs.getString("t.first_name"));
                teacher.setLastName(rs.getString("t.last_name"));
                teacher.setAge(rs.getInt("t.age"));
                teacher.setPhoneNumber(rs.getString("t.phone_number"));                
            }
            
        } catch (SQLException ex) {
            Message.showWarningMessage(ex.toString());
            log.error("\n--------Error Message------\n",ex);
        } finally {
            try {
                if(rs != null)
                    rs.close();
                if(s != null)
                    s.close();
                if(c != null)
                    c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
                log.error("\n--------Error Message------\n",ex);
            }
            
        }
        
        return teacher;
        
    }
    
    public Teacher getTeacherByIdWithCourses(Integer id) {
        Connection c = DbConnection.dbConnect(); 
        PreparedStatement s = null;
        ResultSet rs = null;
        
        Teacher teacher = getTeacherById(id);
        try {
        
            String sql = "SELECT * FROM courses c "
                    + " INNER JOIN teacher_teaches_course tc ON c.cid = tc.cid"
                    + " INNER JOIN teachers t ON tc.tid = t.tid"
                    + " WHERE t.tid = ?";

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
                teacher.addCourse(course);
            }
        } catch(SQLException ex) {
            Message.showWarningMessage(ex+"");
            log.error("\n--------Error Message------\n",ex);
        } finally {
       
            try {
                if(rs != null)
                    rs.close();
                if(s != null)
                    s.close();
                if(c != null)
                    c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
                log.error("\n--------Error Message------\n",ex);
            }
        }
        
        return teacher;
        
    }
    
    public void addTeacher(Teacher teacher) {
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = DbConnection.dbConnect();
            String query = "INSERT INTO teachers(first_name"
                    + ", last_name"
                    + ",age"
                    + ", gender"
                    + ", phone_number) VALUES ("
                    + "?, ?, ?, ?, ?);" ;
            
            s = c.prepareStatement(query);
            s.setString(1, teacher.getFirstName());
            s.setString(2, teacher.getLastName());
            s.setInt(3, teacher.getAge());
            s.setString(4, teacher.getGender());
            s.setString(5, teacher.getPhoneNumber());
            
            s.executeUpdate();
            
        } catch (SQLException ex) {
            if(ex.getErrorCode() == 1062) {
                Message.showWarningMessage("This id is already exists for a Teacher !");
            }
            //Message.showWarningMessage(ex.toString());
            System.out.println(ex);
        } finally {
            try {
                if(s != null)
                    s.close();
                if(c != null)
                    c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
                log.error("\n--------Error Message------\n",ex);
            } 
            
        }
    }
    
    public void updateTeacher(Teacher teacher) {
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = DbConnection.dbConnect();
            String query = "UPDATE teachers SET "
                    + "first_name = ?,"
                    + "last_name = ?,"
                    + "age = ?,"
                    + "gender = ?,"
                    + "phone_number = ?"
                    + " WHERE tid = ?;" ;
            
            s = c.prepareStatement(query);
            s.setString(1, teacher.getFirstName());
            s.setString(2, teacher.getLastName());
            s.setInt(3, teacher.getAge());
            s.setString(4, teacher.getGender());
            s.setString(5, teacher.getPhoneNumber());
            s.setInt(6, teacher.getId());
            
            s.executeUpdate();
            
        } catch (SQLException ex) {
            Message.showWarningMessage(ex.toString());
            log.error("\n--------Error Message------\n",ex);
        } finally {
            try {
                if(s != null)
                    s.close();
                if(c != null)
                c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
                log.error("\n--------Error Message------\n",ex);
            } 
            
        }
    }
    
    
    public void deleteTeacher(int id) {
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = DbConnection.dbConnect();
            String query = "DELETE FROM teachers WHERE tid = ?";
            
            s = c.prepareStatement(query);
            s.setInt(1, id);
            
            s.executeUpdate();
            
        } catch (SQLException ex) {
            Message.showWarningMessage(ex.toString());
            log.error("\n--------Error Message------\n",ex);
        } finally {
            try {
                if(s != null)
                    s.close();
                if(c != null)
                    c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
                log.error("\n--------Error Message------\n",ex);
            } 
            
        }
    }
        
    public void deleteRegestrationForTeacher(int tid, int cid) {
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = DbConnection.dbConnect();
            String query = "DELETE FROM teacher_teaches_course WHERE tid = ? AND cid = ?";
            
            s = c.prepareStatement(query);
            s.setInt(1, tid);
            s.setInt(2, cid);
            
            s.executeUpdate();
            
        } catch (SQLException ex) {
            Message.showWarningMessage(ex.toString());
        } finally {
            try {
                if(s != null)
                    s.close();
                if(c != null)
                    c.close();
            } catch(SQLException ex) {
                Message.showWarningMessage(ex.toString());
            } 
            
        }
    }
    
}
