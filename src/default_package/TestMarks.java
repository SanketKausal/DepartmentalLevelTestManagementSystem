package default_package;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TestMarks {

	JFrame frame;
	static TestMarks window;
	private JComboBox<String> cmbDepartment;
	private JComboBox<String> cmbSubject;
	private JComboBox<String> cmbClass;
	private JComboBox<String> cmbTest;
	private JComboBox<String> cmbStudent;
	boolean isDone = false;
	boolean isClassLoad = false;
	boolean isSubjectLoad = false;
	boolean isTestLoad = false;
	private JTextField txtMarks;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new TestMarks();
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public TestMarks() {
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
		frame.getContentPane().setBackground(new Color(230, 230, 250));
		frame.setTitle("Students Marks");
		frame.setBounds(100, 100, 801, 527);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEditDelete = new JLabel("Edit / Delete Student");
		lblEditDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEditDelete.setBounds(187, 10, 380, 40);
		frame.getContentPane().add(lblEditDelete);
		
		JLabel lblSelectDepartment = new JLabel("Select Department");
		lblSelectDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectDepartment.setBounds(10, 77, 219, 40);
		frame.getContentPane().add(lblSelectDepartment);
		
		cmbDepartment = new JComboBox<String>();
		cmbDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbDepartment.getSelectedIndex() == 0) 
				{
					isClassLoad = false;
					isSubjectLoad = false;
					isTestLoad = false;
					cmbClass.removeAllItems();
					cmbClass.addItem("- Select Department First -");
					cmbSubject.removeAllItems();
					cmbSubject.addItem("- Select Class First -");
					cmbTest.removeAllItems();
					cmbTest.addItem("- Select Subject First -");
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
					isTestLoad = false;
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
							DB.res = DB.stt.executeQuery("SELECT * FROM subject WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"' AND class = '"+cmbClass.getSelectedItem().toString()+"'");
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
				if(cmbSubject.getSelectedIndex() == 0) 
				{
					isTestLoad = false;
					cmbTest.removeAllItems();
					cmbTest.addItem("- Select Subject First -");
					cmbStudent.removeAllItems();
					cmbStudent.addItem("- Select Test First -");
				}
				else
				{
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
						} finally {
							isTestLoad = true;
						}
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
		cmbTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isTestLoad)
				{
					cmbStudent.removeAllItems();
					try {
						DB.res = DB.stt.executeQuery("SELECT * FROM student "
								+ "WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"' AND class = '"+cmbClass.getSelectedItem().toString()+"' AND subject = '"+cmbSubject.getSelectedItem().toString()+"' AND test = '"+cmbTest.getSelectedItem().toString()+"'");
						cmbStudent.addItem("- Select Student -");
						while(DB.res.next())
						{
							cmbStudent.addItem(DB.res.getString("name"));
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		cmbTest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbTest.setBounds(267, 282, 300, 40);
		frame.getContentPane().add(cmbTest);
		
		txtMarks = new JTextField();
		txtMarks.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					txtMarks.setEditable(true);
	           
	            } else {
	            	txtMarks.setEditable(false);
	      
	            }
			}
		});
		txtMarks.setHorizontalAlignment(SwingConstants.CENTER);
		txtMarks.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMarks.setColumns(10);
		txtMarks.setBounds(267, 415, 300, 40);
		frame.getContentPane().add(txtMarks);
		
		JLabel lblSubjectName_1_1 = new JLabel("Marks");
		lblSubjectName_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectName_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubjectName_1_1.setBounds(10, 415, 219, 40);
		frame.getContentPane().add(lblSubjectName_1_1);
		
		JLabel lblStudentName_1 = new JLabel("Student Name");
		lblStudentName_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentName_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStudentName_1.setBounds(10, 348, 219, 40);
		frame.getContentPane().add(lblStudentName_1);
		
		cmbStudent = new JComboBox<String>();
		cmbStudent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbStudent.setBounds(267, 348, 300, 40);
		frame.getContentPane().add(cmbStudent);
		
		JButton btnUpdate = new JButton("Give Marks");
		btnUpdate.addActionListener(new ActionListener() {
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
				else if(cmbStudent.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "Please select student", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(txtMarks.getText().toString().isEmpty()) 
				{
					JOptionPane.showMessageDialog(null,"Please enter your name","Error", JOptionPane.ERROR_MESSAGE);
				}
								else
				{
					try {
						boolean row = DB.stt.execute("UPDATE student SET score='"+txtMarks.getText().toString()+"'"
								+ "WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'AND class = '"+cmbClass.getSelectedItem().toString()+"' AND subject = '"+cmbSubject.getSelectedItem().toString()+"' AND test = '"+cmbTest.getSelectedItem().toString()+"' AND name = '"+cmbStudent.getSelectedItem().toString()+"'");
					
						if(row)
						{
							JOptionPane.showMessageDialog(null, "Failed to update marks", "Error", JOptionPane.ERROR_MESSAGE);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "Marks Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(610, 415, 150, 40);
		frame.getContentPane().add(btnUpdate);
	}

}
