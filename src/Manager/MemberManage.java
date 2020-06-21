package Manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Entities.Member;

public class MemberManage {
	public static void addAccount(Member acc, JTable table, JTextField txtMemberage, JTextField txtMemberid,
			JTextField txtMemberfirstn, JTextField txtMemberlastn) {
		
		if (txtMemberid.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please add ID of Member");
		} else if (!(txtMemberid.getText().toString().trim().matches("[0-9]+"))) {
			JOptionPane.showMessageDialog(null, "Please add Integer ID of Member");

		} else if (txtMemberage.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please add Age of Member");
		} else if (!(txtMemberage.getText().toString().trim().matches("[0-9]+"))) {
			JOptionPane.showMessageDialog(null, "Please add Integer Age of Member");

		} else if (txtMemberfirstn.getText().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "Please Add First Name");

		} else if (txtMemberlastn.getText().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "Please Add Last Name");

		} else if (txtMemberage.getText().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "Please Add Age of Member");


			}else {

			String id = (txtMemberid.getText().trim());
			String firstname = txtMemberfirstn.getText().trim();
			String lastname = txtMemberlastn.getText().trim();
			String age = (txtMemberage.getText().trim());

			Object[] row = { id, firstname, lastname, age };

			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.insertRow(0, row);
			JOptionPane.showMessageDialog(null, "Added");
			txtMemberid.setText("");
			txtMemberfirstn.setText("");
			txtMemberlastn.setText("");
			txtMemberage.setText("");
			String data = acc.getId() + " " + acc.getFirstName() + " " + acc.getLastName() + " " + acc.getAge();
			try {
				BufferedWriter reader = new BufferedWriter(new FileWriter(new File("Member.txt"), true));
				reader.write(data);
				reader.newLine();
				reader.close();

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	

	public static void deleteAccount(JTable table, JTextField txtMemberage, JTextField txtMemberid,
			JTextField txtMemberfirstn, JTextField txtMemberlastn, DefaultTableModel dtm) {
		
		int i = table.getSelectedRow();
		if (i < 0) {
			JOptionPane.showMessageDialog(null, "Please Select Row from table");

		} else {
			
			if (i >= 0) {
				dtm.removeRow(i);
			}
			try {
				File file = new File("Member.txt");
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				
				for (int a = 0; a < table.getRowCount(); a++) {
					for (int j = 0; j < table.getColumnCount(); j++) {
						bw.write(table.getModel().getValueAt(a, j) + " ");
					}

					bw.newLine();

				}
				bw.close();
				fw.close();
				JOptionPane.showMessageDialog(null, "Deleted");
			
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

	}

	public static void updateAccount(JTable table, JTextField txtMemberage, JTextField txtMemberid,
			JTextField txtMemberfirstn, JTextField txtMemberlastn, DefaultTableModel dtm) {
		int i = table.getSelectedRow();
		 if(i<0) {
	         JOptionPane.showMessageDialog(null, "Please Select Row from table");

		 }else {
		if(txtMemberid.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please add ID of Member");
		}else if(!(txtMemberid.getText().toString().trim().matches("[0-9]+"))){
			JOptionPane.showMessageDialog(null, "Please add Integer ID of Member");

		}else if(txtMemberage.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please add Age of Member");
		}else if(!(txtMemberage.getText().toString().trim().matches("[0-9]+"))){
			JOptionPane.showMessageDialog(null, "Please add Integer Age of Member");

		}else if(txtMemberfirstn.getText().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "Please Add First Name");

		}else if (txtMemberlastn.getText().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "Please Add Last Name");

		}else if(txtMemberage.getText().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "Please Add Age of Member");

		}else {
		
		if (i >= 0) {
			dtm.setValueAt(txtMemberid.getText(), i, 0);
			dtm.setValueAt(txtMemberfirstn.getText(), i, 1);
			dtm.setValueAt(txtMemberlastn.getText(), i, 2);
			dtm.setValueAt(txtMemberage.getText(), i, 3);
		}
		try {
			File file = new File("Member.txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);

			for (int a = 0; a < table.getRowCount(); a++) {
				for (int j = 0; j < table.getColumnCount(); j++) {
					bw.write(table.getModel().getValueAt(a, j) + " ");
				}
			
				bw.newLine();
			}
			bw.close();
			fw.close();
			JOptionPane.showMessageDialog(null, "Updated");
			txtMemberid.setText("");
			txtMemberfirstn.setText("");
			txtMemberlastn.setText("");
			txtMemberage.setText("");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		txtMemberid.setText("");
		txtMemberfirstn.setText("");
		txtMemberlastn.setText("");
		txtMemberage.setText("");
	}
	}
	}

	public static void showAccount(DefaultTableModel dtm, JTable table) {
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(new File("Member.txt")));

			dtm = (DefaultTableModel) table.getModel();

			Object[] tableLines = br.lines().toArray();

			for (int i = 0; i < tableLines.length; i++) {
				String line = tableLines[i].toString().trim();
				String[] dataRow = line.split(" ");
				dtm.addRow(dataRow);
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}