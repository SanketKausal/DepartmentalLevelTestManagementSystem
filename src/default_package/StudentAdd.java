package default_package;

import java.awt.EventQueue;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class StudentAdd {

	JFrame frame;
	static StudentAdd window;
	private JComboBox<String> cmbDepartment;
	private JComboBox<String> cmbSubject;
	private JComboBox<String> cmbClass;
	private JComboBox<String> cmbTest;
	boolean isDone = false;
	boolean isClassLoad = false;
	boolean isSubjectLoad = false;
	private JTextField txtName;
	private JTextField txtMobile;
	private JPasswordField txtPassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new StudentAdd();
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public StudentAdd() {
		DB.initialize();
		initialize();
		try {
			DB.res = DB.stt.executeQuery("SELECT * FROM department");
			cmbDepartment.addItem("- Select Department -");
			while(DB.res.next())
			{
				cmbDepartment.addItem(DB.res.getString("department"));
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			isDone = true;
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(224, 255, 255));
		frame.setTitle("Add Student");
		frame.setBounds(100, 100, 804, 587);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddStudent = new JLabel("Add Student");
		lblAddStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddStudent.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddStudent.setBounds(187, 10, 380, 40);
		frame.getContentPane().add(lblAddStudent);
		
		JLabel lblSelectDepartment = new JLabel("Select Department");
		lblSelectDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectDepartment.setBounds(10, 77, 219, 40);
		frame.getContentPane().add(lblSelectDepartment);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbDepartment.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "Please select department", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(cmbClass.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "Please select class", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(cmbSubject.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "Please select subject", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(cmbTest.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "Please select test", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(txtName.getText().toString().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter stuudent name", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(txtMobile.getText().toString().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter student mobile", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(String.valueOf(txtPassword.getPassword()).isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter student account password", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else 
				{
					try {
						boolean row = DB.stt.execute("INSERT INTO student (name, mobile, password, department, class, subject, test) "
								+ "VALUES ('"+ txtName.getText().toString() + "','"
												+ txtMobile.getText().toString() + "','"
														+ String.valueOf(txtPassword.getPassword()) + "','"
																 + cmbDepartment.getSelectedItem().toString() + "','"
																		 + cmbClass.getSelectedItem().toString() + "','"
																				+ cmbSubject.getSelectedItem().toString() + "','"
																						+cmbTest.getSelectedItem().toString() + "')");
							
						if(row)
						{
							JOptionPane.showMessageDialog(null, "Failed to create student account", "Failed", JOptionPane.ERROR_MESSAGE);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "New Student Added", "Success", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreate.setBounds(613, 479, 150, 40);
		frame.getContentPane().add(btnCreate);
		
		cmbDepartment = new JComboBox<String>();
		cmbDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbDepartment.getSelectedIndex() == 0) 
				{
					isClassLoad = false;
					isSubjectLoad = false;
					cmbClass.removeAllItems();
					cmbClass.addItem("- Select Department First -");
					cmbSubject.removeAllItems();
					cmbSubject.addItem("- Select Class First -");
				}
				else
				{
					if(isDone)
					{
						cmbClass.removeAllItems();
						try {
							DB.res = DB.stt.executeQuery("SELECT * FROM class WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'");
							cmbClass.addItem("- Select Class -");
							while(DB.res.next())
							{
								cmbClass.addItem(DB.res.getString("class"));
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
						} finally {
							isClassLoad = true;
						}
					}
				}
			}
		});
		cmbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbDepartment.setBounds(267, 79, 300, 40);
		frame.getContentPane().add(cmbDepartment);
		
		JLabel lblSubjectName = new JLabel("Subject Name");
		lblSubjectName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubjectName.setBounds(10, 215, 219, 40);
		frame.getContentPane().add(lblSubjectName);
		
		cmbClass = new JComboBox<String>();
		cmbClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbClass.getSelectedIndex() == 0) 
				{
					isSubjectLoad = false;
					cmbSubject.removeAllItems();
					cmbSubject.addItem("- Select Class First -");
					cmbTest.removeAllItems();
					cmbTest.addItem("- Select Subject First -");
				}
				else
				{
					if(isClassLoad)
					{
						cmbSubject.removeAllItems();
						try {
							DB.res = DB.stt.executeQuery("SELECT * FROM subject "
									+ "WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"' AND class = '"+cmbClass.getSelectedItem().toString()+"'");
							cmbSubject.addItem("- Select Subject -");
							while(DB.res.next())
							{
								cmbSubject.addItem(DB.res.getString("subject"));
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
						} finally {
							isSubjectLoad = true;
						}
					}
				}
			}
		});
		cmbClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbClass.setBounds(267, 149, 300, 40);
		frame.getContentPane().add(cmbClass);
		
		JLabel lblSelectClass = new JLabel("Select Class");
		lblSelectClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectClass.setBounds(10, 147, 219, 40);
		frame.getContentPane().add(lblSelectClass);
		
		cmbSubject = new JComboBox<String>();
		cmbSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isSubjectLoad)
				{
					cmbTest.removeAllItems();
					try {
						DB.res = DB.stt.executeQuery("SELECT * FROM test "
								+ "WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"' AND class = '"+cmbClass.getSelectedItem().toString()+"' AND subject = '"+cmbSubject.getSelectedItem().toString()+"'");
						cmbTest.addItem("- Select Test -");
						while(DB.res.next())
						{
							cmbTest.addItem(DB.res.getString("name"));
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		cmbSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbSubject.setBounds(267, 215, 300, 40);
		frame.getContentPane().add(cmbSubject);
		
		JLabel lblTest = new JLabel("Test");
		lblTest.setHorizontalAlignment(SwingConstants.CENTER);
		lblTest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTest.setBounds(10, 282, 219, 40);
		frame.getContentPane().add(lblTest);
		
		cmbTest = new JComboBox<String>();
		cmbTest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbTest.setBounds(267, 282, 300, 40);
		frame.getContentPane().add(cmbTest);
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtName.setColumns(10);
		txtName.setBounds(267, 347, 300, 40);
		frame.getContentPane().add(txtName);
		
		JLabel lblStudentName = new JLabel("Student Name");
		lblStudentName.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStudentName.setBounds(10, 347, 219, 40);
		frame.getContentPane().add(lblStudentName);
		
		txtMobile = new JTextField();
		txtMobile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					txtMobile.setEditable(true);
	           
	            } else {
	            	txtMobile.setEditable(false);
	      
	            }
			}
		});
		txtMobile.setHorizontalAlignment(SwingConstants.CENTER);
		txtMobile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMobile.setColumns(10);
		txtMobile.setBounds(267, 415, 300, 40);
		frame.getContentPane().add(txtMobile);
		
		JLabel lblSubjectName_1_1 = new JLabel("Student Mobile");
		lblSubjectName_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectName_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubjectName_1_1.setBounds(10, 415, 219, 40);
		frame.getContentPane().add(lblSubjectName_1_1);
		
		JLabel lblSubjectName_1_1_1 = new JLabel("Student Password");
		lblSubjectName_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectName_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubjectName_1_1_1.setBounds(10, 479, 219, 40);
		frame.getContentPane().add(lblSubjectName_1_1_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setBounds(267, 479, 300, 40);
		frame.getContentPane().add(txtPassword);
	}
}
