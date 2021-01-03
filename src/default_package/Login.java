package default_package;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class Login {

	//variable
	JFrame frame;
	private JPasswordField txtAdminPassword;
	private JPasswordField txtStudentPassword;
	private JTextField txtAdminMobileNumber;
	private JTextField txtStudentMobile;
	
	static Login window;
	
	//void main #1
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					window = new Login(); //its will automatically call constructor  #2
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);
			}
		});
	}

	// constructor
	public Login() {
		DB.initialize(); //#3
		initialize(); //#4
	}

	//method
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Login");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(245, 255, 250));
		panel.setBounds(0, 145, 400, 418);
		panel.setBackground(new Color(175, 238, 238));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mobile Number");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(147, 92, 107, 40);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(10, 194, 380, 40);
		panel.add(lblPassword);
		
		txtAdminPassword = new JPasswordField();
		txtAdminPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAdminPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtAdminPassword.setBounds(50, 237, 300, 40);
		panel.add(txtAdminPassword);
		
		JButton btnLoginAdmin = new JButton("Login");
		btnLoginAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtAdminMobileNumber.getText().toString().isEmpty()) 
				{
					JOptionPane.showMessageDialog(null,"Please enter mobile number","Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(txtAdminMobileNumber.getText().toString().length() != 10) 
				{
					JOptionPane.showMessageDialog(null,"Mobile Number should be of 10 digit","Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(String.valueOf(txtAdminPassword.getPassword()).isEmpty()) 
				{
					JOptionPane.showMessageDialog(null,"Please enter password","Error", JOptionPane.ERROR_MESSAGE);
				}
				else 
				{
					try {
						DB.res = DB.stt.executeQuery("SELECT * FROM admin WHERE mobile = '"+txtAdminMobileNumber.getText().toString()+"' AND password = '"+String.valueOf(txtAdminPassword.getPassword())+"'");
						if(DB.res.next() == false)
						{
							JOptionPane.showMessageDialog(null, "Invalid Mobile / Password", "User Not Found", JOptionPane.WARNING_MESSAGE);
						}
						else
						{
							window.frame.dispose();
							DashboardAdmin window = new DashboardAdmin(DB.res.getString("name"));
							CommonFunction.centreWindow(window.frame);
							window.frame.setVisible(true);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnLoginAdmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLoginAdmin.setBounds(51, 315, 150, 40);
		panel.add(btnLoginAdmin);
		
		JButton btnRegisterAdmin = new JButton("Register");
		btnRegisterAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterAdmin window = new RegisterAdmin();
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnRegisterAdmin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegisterAdmin.setBounds(200, 315, 150, 40);
		panel.add(btnRegisterAdmin);
		
		JLabel lblAdminLogin = new JLabel("Admin Login");
		lblAdminLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdminLogin.setBounds(10, 30, 380, 40);
		panel.add(lblAdminLogin);
		
		txtAdminMobileNumber = new JTextField();
		txtAdminMobileNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					txtAdminMobileNumber.setEditable(true);
	           
	            } else {
	            	txtAdminMobileNumber.setEditable(false);
	      
	            }
			}
		});
		txtAdminMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtAdminMobileNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtAdminMobileNumber.setBounds(50, 133, 300, 40);
		panel.add(txtAdminMobileNumber);
		txtAdminMobileNumber.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(245, 255, 250));
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(396, 145, 390, 418);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Mobile Number");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(147, 90, 107, 40);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword_1.setBounds(10, 192, 380, 40);
		panel_1.add(lblPassword_1);
		
		txtStudentPassword = new JPasswordField();
		txtStudentPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtStudentPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtStudentPassword.setBounds(50, 235, 300, 40);
		panel_1.add(txtStudentPassword);
		
		JButton btnLoginStudent = new JButton("Login");
		btnLoginStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtStudentMobile.getText().toString().isEmpty()) 
				{
					JOptionPane.showMessageDialog(null,"Please enter mobile number","Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(txtStudentMobile.getText().toString().length() != 10) 
				{
					JOptionPane.showMessageDialog(null,"Mobile Number should be of 10 digit","Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(String.valueOf(txtStudentPassword.getPassword()).isEmpty()) 
				{
					JOptionPane.showMessageDialog(null,"Please enter password","Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						DB.res = DB.stt.executeQuery("SELECT * FROM student WHERE mobile = '"+txtStudentMobile.getText().toString()+"' AND password = '"+String.valueOf(txtStudentPassword.getPassword())+"'");
						if(DB.res.next() == false)
						{
							JOptionPane.showMessageDialog(null, 
		                            "Invalid Mobile / Password", 
		                            "User Not Found", 
		                            JOptionPane.WARNING_MESSAGE);
						}
						else
						{
							window.frame.dispose();
							DashboardStudent window = new DashboardStudent(DB.res.getString("name"));
							CommonFunction.centreWindow(window.frame);
							window.frame.setVisible(true);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnLoginStudent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLoginStudent.setBounds(132, 312, 150, 40);
		panel_1.add(btnLoginStudent);
		
		JLabel lblAdminLogin_1 = new JLabel("Student Login");
		lblAdminLogin_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminLogin_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdminLogin_1.setBounds(10, 28, 380, 40);
		panel_1.add(lblAdminLogin_1);
		
		txtStudentMobile = new JTextField();
		txtStudentMobile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					txtStudentMobile.setEditable(true);
	           
	            } else {
	            	txtStudentMobile.setEditable(false);
	      
	            }
			}
		});
		txtStudentMobile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtStudentMobile.setHorizontalAlignment(SwingConstants.CENTER);
		txtStudentMobile.setColumns(10);
		txtStudentMobile.setBounds(50, 137, 300, 40);
		panel_1.add(txtStudentMobile);
		
		JLabel lblNewLabel_1 = new JLabel("Departmental Level Test Management System");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 28, 766, 82);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
