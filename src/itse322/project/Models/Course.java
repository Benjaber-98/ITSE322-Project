/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itse322.project.Models;

import java.util.HashSet;

/**
 *
 * @author Mahmoud
 */
public class Course {

    
    private int id;
    private String courseName;
    private String startDate;
    private String endDate;
    private int hours;
    private int price;
    
    private Person teacher;
    private HashSet<Student> students;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getHours() {
        return hours;
    }
    
    public void setHours(int hours) {
        this.hours = hours;
    }
    
    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }
    
    public void addStudent(Student s) {
        students.add(s);
    }

    public HashSet<Student> getStudents() {
        return students;
    }
    
     public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    @Override
    public String toString(){
        return this.getStartDate() + " => " + this.getEndDate();
    }

   
            
}
