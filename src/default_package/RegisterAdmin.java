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

public class RegisterAdmin {

	JFrame frame;
	static RegisterAdmin window;
	private JPasswordField txtPassword;
	private JTextField txtMobileNumber;
	private JTextField txtName;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					window = new RegisterAdmin();
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);	
			}
		});
	}
	
	public RegisterAdmin() {
		initialize();
		DB.initialize();
	}
	

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Register");
		frame.setBounds(100, 100, 500, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(new Color(245, 255, 250));
		panel.setBackground(new Color(245, 245, 220));
		panel.setBounds(10, 10, 466, 543);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Mobile Number");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(82, 208, 300, 40);
		panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(82, 310, 300, 40);
		panel.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setBounds(82, 353, 300, 40);
		panel.add(txtPassword);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtName.getText().toString().isEmpty()) 
				{
					JOptionPane.showMessageDialog(null,"Please enter your name","Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(txtMobileNumber.getText().toString().length() != 10) 
				{
					JOptionPane.showMessageDialog(null,"Mobile Number should be of 10 digit","Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(String.valueOf(txtPassword.getPassword()).isEmpty()) 
				{
					JOptionPane.showMessageDialog(null,"Please enter password","Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						boolean row = DB.stt.execute("INSERT INTO admin (name, mobile, password) VALUES" + 
			                    "('"+txtName.getText().toString()+"', '"+txtMobileNumber.getText().toString()+"', '"+String.valueOf(txtPassword.getPassword())+"')");
						if(row)
						{
							JOptionPane.showMessageDialog(null, "Failed to create account", "Failed", JOptionPane.ERROR_MESSAGE);
							
							
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "Admin Account Created.", "Success", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Exception", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRegister.setBounds(82, 430, 300, 40);
		panel.add(btnRegister);
		
		JLabel lblRegisterAdmin = new JLabel("Register Admin");
		lblRegisterAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegisterAdmin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRegisterAdmin.setBounds(10, 30, 447, 40);
		panel.add(lblRegisterAdmin);
		
		txtMobileNumber = new JTextField();
		txtMobileNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					txtMobileNumber.setEditable(true);
	           
	            } else {
	            	txtMobileNumber.setEditable(false);
	      
	            }
			}
		});
		txtMobileNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMobileNumber.setColumns(10);
		txtMobileNumber.setBounds(82, 249, 300, 40);
		panel.add(txtMobileNumber);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(82, 103, 300, 40);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtName.setColumns(10);
		txtName.setBounds(82, 144, 300, 40);
		panel.add(txtName);
	}

}
