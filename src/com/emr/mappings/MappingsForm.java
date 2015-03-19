/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emr.mappings;

import com.almworks.sqlite4java.SQLite;
import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteStatement;
import com.emr.schemas.EditMappingsForm;
import com.emr.schemas.SourceDataPreview;
import com.emr.utilities.SQliteDataLoadWorker;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 * Handles data mappings that should be applied to the source data before
 * it is copied to the destination.
 * @author LEONARD NDUATI
 */
public class MappingsForm extends javax.swing.JInternalFrame {
    DefaultTableModel model=new DefaultTableModel();//=new DefaultTableModel(new Object[]{"Value","Mapping"},25);
    /**
     * Creates new form MappingsForm
     * SQliteDataLoadWorker is a SwingWorker that fetches the saved mappings from the sqlite database,
     * and populates this to the mappings table
     * 
     */
    public MappingsForm() {
        //model=new DefaultTableModel(new Object[]{"Source Value","Map To"}, 25);
    	
        SQliteDataLoadWorker dl=new SQliteDataLoadWorker(tblMappings, "select sourceValue,dataMapping from mappings");
        dl.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent event) {
                switch (event.getPropertyName()) {
                    case "progress":
                        System.out.println("Fetching data from db");
                        break;
                    case "state":
                        switch ((SwingWorker.StateValue) event.getNewValue()) {
                            case DONE:
                                try {
                                    model=dl.get();
                                    tblMappings.setModel(model);
                                }catch (final CancellationException ex) {
                                    Logger.getLogger(SourceDataPreview.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(SourceDataPreview.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ExecutionException ex) {
                                    Logger.getLogger(SourceDataPreview.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            break;
                        }
                    break;
                }
            }
            
        });
        dl.execute();
        initComponents();
        //todo: get already added mappings and use their count to initilize model
        this.setClosable(true);
        
        /*TextBoxTableCellEditor sourceValEditor=new TextBoxTableCellEditor(new JTextField());
        tblMappings.getColumnModel().getColumn(0).setCellEditor(sourceValEditor);
        TextBoxTableCellEditor mappingValEditor=new TextBoxTableCellEditor(new JTextField());
        tblMappings.getColumnModel().getColumn(1).setCellEditor(mappingValEditor);*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMappings = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        btnSave = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnCancel = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Mappings");

        jLabel1.setText("<html><b color='green'>Mappings defined here will be applied to all Source data before it is copied to the destination.</b><br /><i color='blue'>Double click to edit</i></html>");

        tblMappings.setModel(model);
        jScrollPane1.setViewportView(tblMappings);

        jToolBar1.setRollover(true);

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/emr/icons/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.setFocusable(false);
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSave);
        jToolBar1.add(jSeparator1);

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/emr/icons/cancel.png"))); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.setToolTipText("");
        btnCancel.setFocusable(false);
        btnCancel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCancel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCancel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Handles click event for the Cancel button 
     * <br />
     * Disposes the form
     * @param evt {@link ActionEvent} object
     */
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed
    /**
     * Handles click event for the Save button
     * uses the {@link SwingWorker} AddMappings to save the data
     * @param evt {@link ActionEvent} Object
     */
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        
        tblMappings.editingStopped(null);//force editing to stop
        new AddMappings().execute();
    }//GEN-LAST:event_btnSaveActionPerformed
    /**
     * Method to get the contents of a {@link JTable} as an array
     * @param table {@link JTable} The table to be parsed
     * @return {@link Object[][]} A multidimensional array 
     */
    public Object[][] getTableData (JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        for (int i = 0 ; i < nRow ; i++)
            for (int j = 0 ; j < nCol ; j++){
                
                if(dtm.getValueAt(i, j)!=null){
                    tableData[i][j] = dtm.getValueAt(i,j);
                }
            }
        return tableData;
    }
    /**
     * {@link Swingworker} class to save mappings
     * <br />
     * Gets contents of the mappings table, and saves this to a table called mappings
     * in the sqlite database.
     */
    private class AddMappings extends SwingWorker<Boolean, Object>{
    	String error_msg="";
        @Override
        protected Boolean doInBackground() throws Exception {
            SQLiteConnection db=null;
            
            try{
                File file=new File("sqlite/db");
                if(!file.exists()){
                    file.createNewFile();
                }
                db=new SQLiteConnection(file);
                db.open(true);
                //db.exec("drop table if exists mappings");
                db.exec("create table if not exists mappings(sourceValue text,dataMapping text)");
                db.exec("delete from mappings");
                Object[][] columns=getTableData(tblMappings);
                int length=0; //hack: get length of non empty columns
                for(Object[] row: columns){
                    if(row[0]!=null && row[1]!=null){
                        length++;
                    }
                }
                int counter=1;
                for(Object[] row: columns){
                    if(row[0]!=null && row[1]!=null){
                        String val=(String)row[0];
                        String mapping=(String)row[1];
                        System.out.println("Inserted row: " + val + "-" + mapping);
                        SQLiteStatement st=db.prepare("insert into mappings(sourceValue,dataMapping) values(?,?)");
                        st.bind(1, val);
                        st.bind(2, mapping);
                        st.step();
                        st.dispose();
                    }
                }
            }catch(Exception e){
            	error_msg=org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(e);
                
                return Boolean.FALSE;
            }finally{
                if(db!=null)
                    db.dispose();
            }
            return Boolean.TRUE;
        }
        protected void done() {
            Boolean success=null;
            try {
                success=get();
            } catch (InterruptedException ex) {
            	String stacktrace=org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(ex);
                JOptionPane.showMessageDialog(null, "Mapping was not successfully saved. Details: " + stacktrace, "Failed", JOptionPane.ERROR_MESSAGE);
                success=false;
            } catch (ExecutionException ex) {
            	String stacktrace=org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(ex);
                
                JOptionPane.showMessageDialog(null, "Mapping was not successfully saved. Details: " + stacktrace, "Failed", JOptionPane.ERROR_MESSAGE);
                success=false;
            }
            if(success){
                JOptionPane.showMessageDialog(null, "Successfully saved mapping", "Success", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Mapping was not successfully saved. Details: " + error_msg, "Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblMappings;
    // End of variables declaration//GEN-END:variables
}
