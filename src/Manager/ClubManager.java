package Manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Entities.Club;

public class ClubManager {
	public static void addClub(Club clb,JTable table,JTextField txtClubname,JTextArea txtrClubdesc) throws IOException {
		
		
		if(txtClubname.getText().toString().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Enter Club name");
		}else if(txtrClubdesc.getText().toString().equals("")) {
            JOptionPane.showMessageDialog(null, "Please Enter Club Description");
		
		
		}else {
		 
		 String data = clb.getCourseName() + " " + clb.getCourseDesc();
		 BufferedWriter reader = new BufferedWriter(new FileWriter(new File("Club.txt"),true));
			reader.write(data);
			reader.newLine();
			reader.close();
			String courseName = txtClubname.getText().trim();
			String courseDesc = txtrClubdesc.getText().trim();
				
			
			Object [] row = {courseName, courseDesc};
			
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.insertRow(0, row);
            JOptionPane.showMessageDialog(null, "Added");
            txtClubname.setText("");
            txtrClubdesc.setText("");

		}
	
}
	 public static void deleteClub(JTable table,DefaultTableModel dtm,JTextField txtClubname,JTextArea txtrClubdesc) {
	 int i = table.getSelectedRow();
	 if(i<0) {
         JOptionPane.showMessageDialog(null, "Please Select Row from table");

	 }else {
		if(i>=0) {
			dtm.removeRow(i);
		}
		  try{
           File file = new File("Club.txt");
           if(!file.exists()){
               file.createNewFile();
           }
           
           FileWriter fw = new FileWriter(file.getAbsoluteFile());
           BufferedWriter bw = new BufferedWriter(fw);
           
           for(int a = 0; a < table.getRowCount(); a++){
               for(int j = 0; j < table.getColumnCount(); j++){
                   bw.write(table.getModel().getValueAt(a, j)+" ");
               }
                
               bw.newLine();

           }
           bw.close();
           fw.close();
           JOptionPane.showMessageDialog(null, "Deleted");
           txtClubname.setText("");
           txtrClubdesc.setText("");
           }catch(Exception ex){
               ex.printStackTrace();
           }
	 }
	}
	 public static void updateClub(JTable table,JTextField txtClubname,JTextArea txtrClubdesc,DefaultTableModel dtm) {
		 int i = table.getSelectedRow();
		 if(i<0) {
	         JOptionPane.showMessageDialog(null, "Please Select Row from table");

		 }else {
        if(i >= 0) 
        {
           dtm.setValueAt(txtClubname.getText(), i, 0);
           dtm.setValueAt(txtrClubdesc.getText(), i, 1);
           
        }
        try{
           File file = new File("Club.txt");
           if(!file.exists()){
               file.createNewFile();
           }
           
           FileWriter fw = new FileWriter(file.getAbsoluteFile());
           BufferedWriter bw = new BufferedWriter(fw);
           
           for(int a = 0; a < table.getRowCount(); a++){
               for(int j = 0; j < table.getColumnCount(); j++){
                   bw.write(table.getModel().getValueAt(a, j)+" ");
               }
              
bw.newLine();
           }
           bw.close();
           fw.close();
           JOptionPane.showMessageDialog(null, "Updated");
           txtClubname.setText("");
           txtrClubdesc.setText("");
           }catch(Exception ex){
               ex.printStackTrace();
           }
	 }
	 }
	 public static void showClub(DefaultTableModel dtm,JTable table) {
			try {
	            @SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(new File ("Club.txt")));
	            

				
				dtm = (DefaultTableModel) table.getModel();
				
	            
	           
	            Object[] tableLines = br.lines().toArray();
	          
	            for(int i = 0; i < tableLines.length; i++)
	            {
	                String line = tableLines[i].toString().trim();
	                String[] dataRow = line.split(" ");
	                dtm.addRow(dataRow);
	            }
	            
	            
	        } catch (Exception ex) {
	            System.out.println(ex);
	        }
	 }
}
