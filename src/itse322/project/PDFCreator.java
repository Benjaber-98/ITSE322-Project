/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itse322.project;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import itse322.project.Frames.courses.Courses;
import itse322.project.Models.Student;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import org.apache.log4j.Logger;

/**
 *
 * @author Mahmoud
 */
public class PDFCreator {
    
    private Logger log = Logger.getLogger(PDFCreator.class);
    
    public void createPDF(String name, JTable table) {
        try {
            String file = JOptionPane.showInputDialog("Enter file name");
            if(file == "" || file == null) return;
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(name+" reports\\"+file+".pdf"));
            doc.open();
            doc.add(new Paragraph(name + "' table report",
                    FontFactory.getFont(FontFactory.TIMES_BOLD, 24, BaseColor.BLUE) )
            );
            doc.add(new Paragraph("  "));
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
            pdfTable.setWidthPercentage(100);
            
            createHeader(table, pdfTable);
            
            //extracting data from the JTable and inserting it to PdfPTable
            for (int rows = 0; rows < table.getRowCount(); rows++) {
                extractRow(table, pdfTable, rows);
            }
            doc.add(pdfTable);
            doc.add(new Paragraph("Number Of "+ name +" : " + table.getRowCount()));
            
            doc.add(new Paragraph("Created At : "+ TimeManager.getCurrentTime(), 
                    FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.BLACK) 
            ));
            
            doc.close();
            log.info(LoggedUser.getUsername() + " Exported " + file+".pdf Report");
            Message.showDoneMessage("Report Created Successfully");
        } catch (DocumentException | FileNotFoundException ex) {
            Message.showWarningMessage(ex.toString());
            log.error("\n--------Error Message------\n",ex);
        }
    }
    
    public void createHeader(JTable table, PdfPTable pdfTable) {
        //adding table headers
        for (int i = 0; i < table.getColumnCount(); i++) {
            PdfPCell cell = new PdfPCell(new Paragraph(table.getColumnName(i)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell.setPaddingTop(5);
            cell.setPaddingBottom(5);
            pdfTable.addCell(cell);
        }
    }
    
    public void extractRow(JTable table, PdfPTable pdfTable, int row) {
        for(int i = 0; i < table.getColumnCount(); i++) {
            PdfPCell cell = new PdfPCell(
                new Paragraph(table.getModel().getValueAt(row, i).toString())
            );
            cell.setPaddingTop(3);
            cell.setPaddingBottom(3);
            pdfTable.addCell(cell);
        }
    }
    
    public void extractStudentDetails(JTable table, PdfPTable pdfTable){
        try {
            int row = table.getSelectedRow();
            String file = table.getValueAt(row, 0).toString() + "-" +table.getValueAt(row, 1).toString();

            if("".equals(file) || file == null) return;

            Document doc = new Document();

            PdfWriter.getInstance(doc, new FileOutputStream("Courses reports\\"+file+".pdf"));
            doc.open();
            doc.add(new Paragraph(file + " course report",
                    FontFactory.getFont(FontFactory.TIMES_BOLD, 24, BaseColor.BLUE) )
            );
            doc.add(new Paragraph("  "));
            
            PdfPTable courseDetailsTable = new PdfPTable(table.getColumnCount());
            courseDetailsTable.setWidthPercentage(100);
            
            createHeader(table, courseDetailsTable);
            
            //extracting data from the JTable and inserting it to PdfPTable
            int modelRow = table.convertRowIndexToModel(row);
            extractRow(table, courseDetailsTable, modelRow);
            
            doc.add(courseDetailsTable);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Registered Student in the course",
               FontFactory.getFont(FontFactory.TIMES_BOLD, 24, BaseColor.BLUE) )
            );
            
            PdfPTable studentsTable = new PdfPTable(6);
            studentsTable.setWidthPercentage(100);
            
            Courses c = new Courses();
            HashSet<Student> students = c.getListStudent();
            c = null;
            String header[] = {"ID", "First Name", "Last Name", "Gender", "Age", " Phone Number"};
            for(int i = 0; i < header.length; i++) {
                PdfPCell cell = new PdfPCell( new Paragraph(header[i]) );
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell.setPaddingTop(5);
                cell.setPaddingBottom(5);
                studentsTable.addCell(cell);
            }
            
            createStudentTable(students, studentsTable);
            
            doc.add(new Paragraph(" "));
            doc.add(studentsTable);
            
            doc.add(new Paragraph("Number Of Students : " + students.size() ));
            doc.add(new Paragraph("Created At : "+TimeManager.getCurrentTime(), 
                    FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.BLACK) 
            ));
            
            doc.close();
            log.info(LoggedUser.getUsername() + " Exported " + file+".pdf Report");
            Message.showDoneMessage("Report Created Successfully");
            
            
            
            
        } catch (DocumentException | FileNotFoundException ex) {
            Message.showWarningMessage(ex.toString());
            log.error("\n--------Error Message------\n",ex);
        }
    }

    private void createStudentTable(HashSet<Student> students, PdfPTable studentsTable) {
        for(Student s : students) {
            PdfPCell cell = new PdfPCell( new Paragraph( ""+s.getId() ) );
            cell.setPaddingTop(3);
            cell.setPaddingBottom(3);
            studentsTable.addCell(cell);
            
            cell = new PdfPCell( new Paragraph( ""+s.getFirstName()) );
            cell.setPaddingTop(3);
            cell.setPaddingBottom(3);
            studentsTable.addCell(cell);
            
            cell = new PdfPCell( new Paragraph( ""+s.getLastName()) );
            cell.setPaddingTop(3);
            cell.setPaddingBottom(3);
            studentsTable.addCell(cell);
            
            cell = new PdfPCell( new Paragraph( ""+s.getGender()) );
            cell.setPaddingTop(3);
            cell.setPaddingBottom(3);
            studentsTable.addCell(cell);
            
            cell = new PdfPCell( new Paragraph( ""+s.getAge()) );
            cell.setPaddingTop(3);
            cell.setPaddingBottom(3);
            studentsTable.addCell(cell);
            
            cell = new PdfPCell( new Paragraph( "" + s.getPhoneNumber()) );
            cell.setPaddingTop(3);
            cell.setPaddingBottom(3);
            studentsTable.addCell(cell);
            
        }
    }
}
