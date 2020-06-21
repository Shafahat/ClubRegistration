package View;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Entities.Member;
import Manager.MemberManage;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class MemberFrame {

	JFrame memberframe;
	private JTextField txtMemberid;
	private JTextField txtMemberfirstn;
	private JTextField txtMemberlastn;
	private JTextField txtMemberage;
	private JTable table;
	private DefaultTableModel dtm;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberFrame window = new MemberFrame();
					window.memberframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MemberFrame() {
		initialize();
		MemberManage.showAccount(dtm, table);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		memberframe = new JFrame();
		memberframe.setBounds(100, 100, 800, 300);
		memberframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		memberframe.getContentPane().setLayout(null);
		
		JLabel lblAddNewMember = new JLabel("Add New Member");
		lblAddNewMember.setBounds(112, 12, 125, 23);
		memberframe.getContentPane().add(lblAddNewMember);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(12, 62, 66, 15);
		memberframe.getContentPane().add(lblId);
		
		JLabel lblName = new JLabel("Firstname");
		lblName.setBounds(12, 89, 99, 15);
		memberframe.getContentPane().add(lblName);
		
		JLabel lblNewLabel = new JLabel("Lastname");
		lblNewLabel.setBounds(12, 116, 82, 15);
		memberframe.getContentPane().add(lblNewLabel);
		
		
		txtMemberid = new JTextField();
		txtMemberid.setBounds(112, 58, 124, 19);
		memberframe.getContentPane().add(txtMemberid);
		txtMemberid.setColumns(10);
		
		txtMemberfirstn = new JTextField();
		txtMemberfirstn.setBounds(112, 85, 124, 19);
		memberframe.getContentPane().add(txtMemberfirstn);
		txtMemberfirstn.setColumns(10);
		
		txtMemberlastn = new JTextField();
		txtMemberlastn.setBounds(112, 112, 124, 19);
		memberframe.getContentPane().add(txtMemberlastn);
		txtMemberlastn.setColumns(10);
		
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				memberframe.dispose();
				WelcomePage welcome =  new WelcomePage();
				welcome.frame.setVisible(true);
			}
		});
		btnBack.setBounds(285, 213, 114, 25);
		memberframe.getContentPane().add(btnBack);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    Member a = new Member();
			a.setId(txtMemberid.getText().trim());
			a.setFirstName(txtMemberfirstn.getText().trim());
			a.setLastName(txtMemberlastn.getText().trim());
			a.setAge(txtMemberage.getText().trim());
			MemberManage.addAccount(a, table, txtMemberage, txtMemberid, txtMemberfirstn, txtMemberlastn);
			
	    } 
			
	
		
		});
		btnAdd.setBounds(285, 62, 114, 25);
		memberframe.getContentPane().add(btnAdd);
		
		JButton btnNewButton = new JButton("Quit\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberframe.dispose();
			}
		});
		btnNewButton.setBounds(43, 213, 114, 25);
		memberframe.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(432, 16, 350, 187);
		memberframe.getContentPane().add(scrollPane);
		
		table = new JTable();
		String [] columns = {"Id", "Firstname", "Lastname","Age"};
		DefaultTableModel dtm = new DefaultTableModel(columns,0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(dtm);
		
		
		scrollPane.setViewportView(table);
		
		 table.addMouseListener(new MouseAdapter(){
		        
		        @Override
		        public void mouseClicked(MouseEvent e){
		        	int i = table.getSelectedRow();
					txtMemberid.setText(dtm.getValueAt(i, 0).toString());
					txtMemberfirstn.setText(dtm.getValueAt(i, 1).toString());
					txtMemberlastn.setText(dtm.getValueAt(i, 2).toString());
					txtMemberage.setText(dtm.getValueAt(i, 3).toString());
		        }
		 });
		        
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			MemberManage.updateAccount(table, txtMemberage, txtMemberid, txtMemberfirstn, txtMemberlastn, dtm);
			
			
			}
		});
		btnUpdate.setBounds(285, 111, 114, 25);
		memberframe.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
						MemberManage.deleteAccount(table, txtMemberage, txtMemberage, txtMemberage, txtMemberage, dtm);
						txtMemberid.setText("");
						txtMemberfirstn.setText("");
						txtMemberlastn.setText("");
						txtMemberage.setText("");
			
				
			}
		});
		btnDelete.setBounds(285, 143, 114, 25);
		memberframe.getContentPane().add(btnDelete);
		
		
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(12, 143, 66, 15);
		memberframe.getContentPane().add(lblAge);
		
		txtMemberage = new JTextField();
		txtMemberage.setBounds(112, 143, 124, 19);
		memberframe.getContentPane().add(txtMemberage);
		txtMemberage.setColumns(10);
	}
}
