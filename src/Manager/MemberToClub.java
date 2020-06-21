package Manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class MemberToClub {
	 public static void MemberAddClub(JComboBox comboBox,JComboBox comboBox_1,JTable table ) throws IOException {
		 String member = (String) comboBox.getSelectedItem();
		 String club = (String) comboBox_1.getSelectedItem();
		 String data = member + " " + club;
		 BufferedWriter reader = new BufferedWriter(new FileWriter(new File("List.txt"),true));
			reader.write(data);
			reader.newLine();
			reader.close();
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			
                
                String[] dataRow = data.split(" ");
                dtm.insertRow(0,dataRow);
            
			
            JOptionPane.showMessageDialog(null, "Added");

			
	
	 }
	 public static void MemberAddComboBox(JComboBox comboBox) {
			DefaultComboBoxModel member = new DefaultComboBoxModel();
			File file1 = new File("Member.txt");
			try(Scanner in = new Scanner(file1)) {
				while(in.hasNextLine()) {
					 
			            
					String line= in.nextLine();
					member.addElement(line);
					comboBox.setModel(member);
				
			}
			}catch(FileNotFoundException ex){
				System.out.println(ex);
				
			}
		 
	 }
	 
	 public static void ClubAddComboBox(JComboBox comboBox_1) throws Exception {
		 DefaultComboBoxModel club = new DefaultComboBoxModel();
			File file = new File("Club.txt");
			Scanner in = new Scanner(file);
				while(in.hasNextLine()) {
					 
			            
					String line= in.nextLine();
					club.addElement(line);
					comboBox_1.setModel(club);
				}
			
				}
	 public static void showList(DefaultTableModel dtm,JTable table) {
			try {
	            @SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader(new File ("List.txt")));
	            

				
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
	 public static void deleteFromList(JTable table,DefaultTableModel dtm) {
		 int i = table.getSelectedRow();
		 if(i<0) {
	         JOptionPane.showMessageDialog(null, "Please Select Row from table");

		 }else {
			if(i>=0) {
				dtm.removeRow(i);
			}
			  try{
	             //the  path
	            File file = new File("List.txt");
	            //if the file not exist create one
	            if(!file.exists()){
	                file.createNewFile();
	            }
	            
	            FileWriter fw = new FileWriter(file.getAbsoluteFile());
	            BufferedWriter bw = new BufferedWriter(fw);
	            
	            for(int a = 0; a < table.getRowCount(); a++){
	                //loop for jtable column
	                for(int j = 0; j < table.getColumnCount(); j++){
	                    bw.write(table.getModel().getValueAt(a, j)+" ");
	                }
	                //break line at the begin 
	                //break line at the end 
	                bw.newLine();

	            }
	            //close BufferedWriter
	            bw.close();
	            //close FileWriter 
	            fw.close();
	            JOptionPane.showMessageDialog(null, "Deleted");
	            
	            }catch(Exception ex){
	                ex.printStackTrace();
	            }
		 }
		}
	 public static void filterTheList(JTable table,String get, DefaultTableModel dtm) {
		dtm = (DefaultTableModel)table.getModel();
	TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(dtm);
	 table.setRowSorter(tr);
	 tr.setRowFilter(RowFilter.regexFilter(get));
	 }
}
