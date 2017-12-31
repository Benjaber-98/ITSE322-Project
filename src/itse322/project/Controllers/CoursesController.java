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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import org.apache.log4j.Logger;

/**
 *
 * @author Mahmoud
 */
public class CoursesController {
    
    Logger log = Logger.getLogger(Course.class);
    public HashSet<Course> getAllCourses() {
        
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;
        try {
            c = DbConnection.dbConnect();
            String query = "SELECT * FROM courses;";
            s = c.createStatement();
            rs = s.executeQuery(query);
            HashSet<Course> courses = new HashSet<>();
            while(rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("cid"));
                course.setCourseName(rs.getString("course_name"));
                course.setHours(rs.getInt("hours"));
                course.setStartDate(rs.getDate("start_date").toString());
                course.setEndDate(rs.getDate("end_date").toString());
                courses.add(course);
            }
            return courses;
        } catch (SQLException ex) {
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
                log.error("\n--------Error Message------\n",ex);
            }
            
        }
        
        return null;
    }
    
    public void updateCourse(Course course) {
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = DbConnection.dbConnect();
            String query = "Update Courses SET course_name = ?, hours = ?, start_date = CAST(? AS DATE), end_date = CAST(? AS DATE)"
                    + " WHERE cid = ?";
            s = c.prepareStatement(query);
            s.setString(1, course.getCourseName());
            s.setInt(2, course.getHours());
            s.setString(3, course.getStartDate());
            s.setString(4, course.getEndDate());
            s.setInt(5, course.getId());
            
            s.executeUpdate();
            
        } catch(SQLException ex) {
            
            log.error("\n--------Error Message------\n",ex);
                
        }
    }
    
    public void deleteCourse(int cid) {
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = DbConnection.dbConnect();
            String query = "DELETE FROM courses WHERE cid = ?";
            s = c.prepareStatement(query);
            s.setInt(1, cid);
            
            s.executeUpdate();
            
        } catch(SQLException ex) {
            
            log.error("\n--------Error Message------\n",ex);
                
        }
    }
    
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
    
    public void addCourse(Course course) {
        Connection c = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            c = DbConnection.dbConnect();
            String query = "INSERT INTO Courses(course_name, hours, start_date, end_date) "
                    + "VALUES (?,?,CAST(? AS DATE),CAST(? AS DATE) );";
            s = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            s.setString(1, course.getCourseName());
            s.setInt(2, course.getHours());
            s.setString(3, course.getStartDate());
            s.setString(4, course.getEndDate());
            s.executeUpdate();
            if(course.getTeacher() != null) {
                rs = s.getGeneratedKeys();
                if(rs.next()) {
                    addTeacherToCourse(course.getTeacher(), rs.getInt(1));
                }
            }
            
            
        } catch(SQLException ex) {
            System.err.println(ex);
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
    }
    
    public void addTeacherToCourse(Teacher teacher, int cid) {
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = DbConnection.dbConnect();
            String query = "INSERT INTO teacher_teaches_course VALUES("
                    + "? , ?);";
            s = c.prepareStatement(query);
            s.setInt(1, teacher.getId());
            s.setInt(2, cid);

            s.executeUpdate();
            
        } catch(SQLException ex) {
            System.err.println(ex);
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
    
    private void updateTeacherCourse(Teacher teacher, int cid) {
        Connection c = null;
        PreparedStatement s = null;
        try {
            c = DbConnection.dbConnect();
            String query = "UPDATE teacher_teaches_course SET tid = ? WHERE cid = ?;";
            s = c.prepareStatement(query);
            s.setInt(1, teacher.getId());
            s.setInt(2, cid);

            s.executeUpdate();
            
        } catch(SQLException ex) {
            System.err.println(ex);
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
    
    public Teacher getTeacher(int cid) {
        Connection c = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            c = DbConnection.dbConnect();
            String query = "SELECT t.tid, t.first_name, t.last_name FROM teachers t "
                    + "INNER JOIN teacher_teaches_course tc "
                    + "ON t.tid = tc.tid INNER JOIN courses c ON c.cid = tc.cid WHERE c.cid = ?";
            s = c.prepareStatement(query);
            s.setInt(1, cid);

            rs = s.executeQuery();
            if(rs.next()) {
                Teacher t = new Teacher();
                t.setId(rs.getInt("t.tid"));
                t.setFirstName(rs.getString("t.first_name"));
                t.setLastName(rs.getString("t.last_name"));
                return t;
            }
            
        } catch(SQLException ex) {
            System.err.println(ex);
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
    
    public HashSet<Student> getRegisteredStudents(int cid) {
        Connection c = null;
        PreparedStatement s = null;
        ResultSet rs = null;
        try {
            c = DbConnection.dbConnect();
            String query = "SELECT s.sid, first_name, last_name, gender, age, phone_number FROM students s INNER JOIN student_takes_course sc"
                    + " ON sc.sid = s.sid INNER JOIN courses c ON c.cid = sc.cid WHERE c.cid = ?;";
            s = c.prepareStatement(query);
            s.setInt(1, cid);
            rs = s.executeQuery();
            HashSet<Student> students = new HashSet<>();
            while(rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("s.sid"));
                student.setFirstName(rs.getString("s.first_name"));
                student.setLastName(rs.getString("s.last_name"));
                student.setGender(rs.getString("s.gender"));
                student.setAge(rs.getInt("s.age"));
                student.setPhoneNumber(rs.getString("s.phone_number"));
                students.add(student);
            }
            return students;
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
    
    
    
}
