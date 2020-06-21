package View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomePage {
	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage window = new WelcomePage();
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
	public WelcomePage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcomeToRegistration = new JLabel("Welcome to Registration ");
		lblWelcomeToRegistration.setBounds(146, 12, 171, 15);
		frame.getContentPane().add(lblWelcomeToRegistration);
		
		JButton btnAddNewMember = new JButton("Add New Member");
		btnAddNewMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MemberFrame memberframe = new MemberFrame();
				memberframe.memberframe.setVisible(true);
			}
		});
		btnAddNewMember.setBounds(138, 55, 179, 35);
		frame.getContentPane().add(btnAddNewMember);
		
		JButton btnAddNewClub = new JButton("Add New Club");
		btnAddNewClub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				ClubFrame clubframe = new ClubFrame();
				clubframe.clubframe.setVisible(true);
			}
		});
		btnAddNewClub.setBounds(138, 107, 179, 35);
		frame.getContentPane().add(btnAddNewClub);
		
		JButton btnList = new JButton("List");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListFrame list = new ListFrame();
				list.frame.setVisible(true);
			}
		});
		btnList.setBounds(138, 163, 179, 35);
		frame.getContentPane().add(btnList);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnQuit.setBounds(12, 226, 114, 25);
		frame.getContentPane().add(btnQuit);
	}
}
