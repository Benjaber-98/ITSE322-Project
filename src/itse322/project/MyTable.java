/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itse322.project;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mahmoud
 */
public class MyTable extends DefaultTableModel {
    
    public MyTable (Object data[],int args) {
        super(data,args);
    }
     
    public Class<?> getColumnClass (int column) {
        if (column==0) {
            return(Integer.class);
        }
        return(String.class);
    }
}
