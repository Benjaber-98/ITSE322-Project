/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itse322.project.Frames.students;

import itse322.project.Controllers.CoursesController;
import itse322.project.Controllers.StudentController;
import itse322.project.Message;
import itse322.project.Models.Course;
import itse322.project.Models.Student;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mahmoud
 */
public class StudentDetails extends javax.swing.JFrame {
        
    StudentController studentController = new StudentController();
    Student student = null;
    CoursesController coursesController = new CoursesController();

    /**
     * Creates new form StudentDetails
     */
    public StudentDetails() {
        initComponents();
    }
    
    public StudentDetails(Student student) {
        initComponents();
        this.student = student;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        studentName.setText(student.getFirstName() + " " + student.getLastName() + "'s details");
        refreshTable();
        coursesComboBoxCreate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        studentDetailsTable = new javax.swing.JTable();
        studentName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        coursesComboBox = new javax.swing.JComboBox<>();
        dateComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        addBtn = new javax.swing.JButton();
        deleteRegestrationBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setTitle("Student details");
        setResizable(false);

        studentDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Course Id", "Course Name", "Teacher", "Hours", "Start Date", "End Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(studentDetailsTable);
        if (studentDetailsTable.getColumnModel().getColumnCount() > 0) {
            studentDetailsTable.getColumnModel().getColumn(0).setResizable(false);
            studentDetailsTable.getColumnModel().getColumn(1).setResizable(false);
            studentDetailsTable.getColumnModel().getColumn(2).setResizable(false);
            studentDetailsTable.getColumnModel().getColumn(3).setResizable(false);
            studentDetailsTable.getColumnModel().getColumn(4).setResizable(false);
            studentDetailsTable.getColumnModel().getColumn(5).setResizable(false);
        }

        studentName.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        studentName.setText("Student Name here :");

        jLabel1.setText("Course Name");

        coursesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coursesComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Time");

        addBtn.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mahmoud\\Documents\\NetBeansProjects\\ITSE322 Project\\Icons\\add_green.png")); // NOI18N
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        deleteRegestrationBtn.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mahmoud\\Documents\\NetBeansProjects\\ITSE322 Project\\Icons\\delete.png")); // NOI18N
        deleteRegestrationBtn.setText("Detele Regestration");
        deleteRegestrationBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRegestrationBtnActionPerformed(evt);
            }
        });

        backBtn.setIcon(new javax.swing.ImageIcon("C:\\Users\\Mahmoud\\Documents\\NetBeansProjects\\ITSE322 Project\\Icons\\back.png")); // NOI18N
        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(studentName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addComponent(coursesComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addComponent(dateComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(addBtn)))
                    .addComponent(backBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deleteRegestrationBtn)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(studentName)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(coursesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(dateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addBtn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteRegestrationBtn)
                    .addComponent(backBtn))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void coursesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coursesComboBoxActionPerformed
        dateComboBox.removeAllItems();
        timesComboBoxCreate();
    }//GEN-LAST:event_coursesComboBoxActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        if(dateComboBox.getSelectedItem() == null) 
        {
            Message.showWarningMessage("This course has no teacher at this moment");
            return;
        }
        Course c = (Course)dateComboBox.getSelectedItem();
        studentController.addCourseToStudent(student.getId(), c.getId());
        refreshTable();
    }//GEN-LAST:event_addBtnActionPerformed

    private void deleteRegestrationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRegestrationBtnActionPerformed
        int row = studentDetailsTable.getSelectedRow();
        
        int dialogButton = JOptionPane.YES_NO_OPTION;
        if(row != -1) {
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this regestration ?","Warning", dialogButton);
            if(dialogResult == JOptionPane.NO_OPTION){
              return;
            }
        }
        
        int count = studentDetailsTable.getRowCount();
        int nextRow = row+1;
        if(nextRow == studentDetailsTable.getRowCount()) {
            nextRow = row -1;
        }
        if(row != -1) {
            int sid = student.getId();
            int cid = Integer.parseInt( String.valueOf( studentDetailsTable.getValueAt(row, 0) ) );
            studentController.deleteRegestrationForStudent(sid, cid);
            if(count == 1){
                refreshTable();
            } else {
                DefaultTableModel model = (DefaultTableModel)studentDetailsTable.getModel();
                studentDetailsTable.setRowSelectionInterval( nextRow, nextRow );
                model.removeRow(row);
            }
        }
    }//GEN-LAST:event_deleteRegestrationBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_backBtnActionPerformed

    private void coursesComboBoxCreate() {
        HashSet<String> courses = coursesController.getCoursesNames();
        
        for(String course : courses) {
            coursesComboBox.addItem(course);
        }

    }
    
    private void timesComboBoxCreate(){
        String c = (String) coursesComboBox.getSelectedItem();
        HashSet<Course> dates = coursesController.getCourseTimes(c);
        for(Course date : dates) {
            dateComboBox.addItem(date);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentDetails().setVisible(true);
            }
        });
    }
    
    private void refreshTable() {
        try {
            studentDetailsTable.setModel(getTableContent());
        } catch (SQLException ex) {
            Logger.getLogger(StudentDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //return model contains all content to put in a table
    private DefaultTableModel getTableContent() throws SQLException {
        String[] columnNames = {"Course Id", "Course Name","Teacher","Hours", "Start Date", "End Date"};

        DefaultTableModel dtm = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };

        dtm.setColumnCount(6);
        
        
        Student studentDetails = studentController.getStudentByIdWithCourses(student.getId());
        
        for(Course c : studentDetails.getCourses()) {
            dtm.addRow(new Object[] {
                c.getId(),
                c.getCourseName(),
                c.getTeacher().getFirstName() + " " + c.getTeacher().getLastName(),
                c.getHours(),
                c.getStartDate(),
                c.getEndDate()
            });
        }
        
        return dtm;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JComboBox<String> coursesComboBox;
    private javax.swing.JComboBox<Course> dateComboBox;
    private javax.swing.JButton deleteRegestrationBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable studentDetailsTable;
    private javax.swing.JLabel studentName;
    // End of variables declaration//GEN-END:variables
}
