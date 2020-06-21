package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Entities.Club;
import Entities.Member;
import Manager.ClubManager;
import Manager.MemberManage;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ClubFrame {

	JFrame clubframe;
	private JTextField txtClubname;
	private JTable table;
	private DefaultTableModel dtmc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClubFrame window = new ClubFrame();
					window.clubframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClubFrame() {
		initialize();
		ClubManager.showClub(dtmc, table);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		clubframe = new JFrame();
		clubframe.setBounds(100, 100, 800, 326);
		clubframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clubframe.getContentPane().setLayout(null);
		
		JLabel lblAddNewClub = new JLabel("Add New Club");
		lblAddNewClub.setBounds(176, 12, 125, 15);
		clubframe.getContentPane().add(lblAddNewClub);
		
		JLabel lblClubName = new JLabel("Club Name");
		lblClubName.setBounds(12, 75, 111, 15);
		clubframe.getContentPane().add(lblClubName);
		
		JLabel lblClubDescription = new JLabel("Club Description");
		lblClubDescription.setBounds(12, 102, 139, 15);
		clubframe.getContentPane().add(lblClubDescription);
		
		txtClubname = new JTextField();
		txtClubname.setBounds(177, 71, 124, 19);
		clubframe.getContentPane().add(txtClubname);
		txtClubname.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(176, 102, 125, 77);
		clubframe.getContentPane().add(scrollPane_1);
		
		JTextArea txtrClubdesc = new JTextArea();
		scrollPane_1.setViewportView(txtrClubdesc);
		txtrClubdesc.setWrapStyleWord(true);
		txtrClubdesc.setLineWrap(true);
		
		JButton btnSave = new JButton("Add\n");
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Club a =new Club();
				a.setCourseName(txtClubname.getText().trim());
				a.setCourseDesc(txtrClubdesc.getText().trim());
				try {
					ClubManager.addClub(a, table, txtClubname, txtrClubdesc);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		btnSave.setBounds(336, 70, 114, 25);
		clubframe.getContentPane().add(btnSave);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clubframe.dispose();
			}
		});
		btnQuit.setBounds(37, 226, 114, 25);
		clubframe.getContentPane().add(btnQuit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clubframe.dispose();
				WelcomePage welcome =  new WelcomePage();
				welcome.frame.setVisible(true);
			}
		});
		btnBack.setBounds(336, 226, 114, 25);
		clubframe.getContentPane().add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(490, 16, 250, 222);
		clubframe.getContentPane().add(scrollPane);
		
		table = new JTable();
		String [] columns = {"Club Name", "Club Des"};
		DefaultTableModel dtmc = new DefaultTableModel(columns,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(dtmc);
		
		
		scrollPane.setViewportView(table);
		
		 table.addMouseListener(new MouseAdapter(){
		        
		        @Override
		        public void mouseClicked(MouseEvent e){
		        	int i = table.getSelectedRow();
					 txtClubname.setText(dtmc.getValueAt(i, 0).toString());
						txtrClubdesc.setText(dtmc.getValueAt(i, 1).toString());
					
				
		        }
		 });
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClubManager.updateClub(table, txtClubname, txtrClubdesc, dtmc);
	                
			}
		});
		btnNewButton.setBounds(336, 118, 114, 25);
		clubframe.getContentPane().add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClubManager.deleteClub(table, dtmc, txtClubname, txtrClubdesc);
				
				
			}
		});
		btnDelete.setBounds(336, 167, 114, 25);
		clubframe.getContentPane().add(btnDelete);
	}
}
