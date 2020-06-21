package View;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Entities.Member;
import Manager.MemberToClub;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ListFrame {

	JFrame frame;
	private JTable table;
	private DefaultTableModel dtml;
	DefaultTableModel filter;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListFrame window = new ListFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListFrame() {
		initialize();
		MemberToClub.showList(dtml, table);

		JLabel lblMemberAddTo = new JLabel("Member add to Club");
		lblMemberAddTo.setBounds(164, 12, 222, 15);
		frame.getContentPane().add(lblMemberAddTo);

		JLabel lblMember = new JLabel("Member");
		lblMember.setBounds(19, 45, 66, 15);
		frame.getContentPane().add(lblMember);

		JLabel lblClub = new JLabel("Club");
		lblClub.setBounds(19, 86, 66, 15);
		frame.getContentPane().add(lblClub);

		JLabel lblFilterByClub = new JLabel("Search - \n\n");
		lblFilterByClub.setBounds(38, 172, 114, 15);
		frame.getContentPane().add(lblFilterByClub);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String get = textField.getText();
				MemberToClub.filterTheList(table, get, dtml);
			}
		});
		textField.setBounds(99, 170, 302, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 679);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 217, 363, 354);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table = new JTable();
		String[] columns = { "Member id", "Firstname", "LastName", "Age", "Club Name", "Club Decs." };
		DefaultTableModel dtml = new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(dtml);

		scrollPane.setViewportView(table);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(79, 81, 322, 24);
		frame.getContentPane().add(comboBox_1);
		try {
			MemberToClub.ClubAddComboBox(comboBox_1);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(79, 45, 322, 24);
		frame.getContentPane().add(comboBox);
		MemberToClub.MemberAddComboBox(comboBox);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MemberToClub.MemberAddClub(comboBox, comboBox_1, table);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnNewButton.setBounds(287, 110, 114, 25);
		frame.getContentPane().add(btnNewButton);

		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberToClub.deleteFromList(table, dtml);
			}
		});
		btnNewButton_2.setBounds(287, 594, 114, 25);
		frame.getContentPane().add(btnNewButton_2);

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnQuit.setBounds(38, 594, 114, 25);
		frame.getContentPane().add(btnQuit);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				WelcomePage welcome = new WelcomePage();
				welcome.frame.setVisible(true);
			}
		});
		btnBack.setBounds(164, 594, 114, 25);
		frame.getContentPane().add(btnBack);

	}
}
