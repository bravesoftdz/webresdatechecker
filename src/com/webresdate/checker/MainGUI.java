package com.webresdate.checker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

public class MainGUI {

	private JFrame frmWebresDateChecker;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frmWebresDateChecker.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWebresDateChecker = new JFrame();
		frmWebresDateChecker.setTitle("Webres Date Checker");
		frmWebresDateChecker.setBounds(100, 100, 450, 116);
		frmWebresDateChecker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWebresDateChecker.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 297, 23);
		frmWebresDateChecker.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnCheckDate = new JButton("Check Date");
		btnCheckDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					URL url = new URL(textField.getText());
					HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
					long date = httpCon.getLastModified();
					if (date != 0) {
						httpCon.disconnect();
						msgBox("Last Modified:" + new Date(date).toString(), "Resource");
					}
					else
						msgBox("Last Modified date not available", "Resource");
				} catch (Exception e) {
					e.printStackTrace();
					msgBox("Resource is unreachable\nMake sure URL starts with http:// or https://", "Resource");
				}
			}
		});
		btnCheckDate.setBounds(317, 36, 107, 23);
		frmWebresDateChecker.getContentPane().add(btnCheckDate);
		
		JLabel lblNewLabel = new JLabel("Enter URL of resource (must start with http:// or https://):");
		lblNewLabel.setBounds(10, 11, 414, 14);
		frmWebresDateChecker.getContentPane().add(lblNewLabel);
	}
	
	public static void msgBox(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
