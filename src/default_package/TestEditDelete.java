package default_package;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Properties;

import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TestEditDelete {

	JFrame frame;
	static TestEditDelete window;
	private JComboBox<String> cmbDepartment;
	private JComboBox<String> cmbSubject;
	private JComboBox<String> cmbClass;
	private JComboBox<String> cmbTest;
	boolean isDone = false;
	boolean isClassLoad = false;
	boolean isSubjectLoad = false;
	private JTextField txtTest;
	private JTextField txtTestScore;
	private JFormattedTextField txtDatePicker;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new TestEditDelete();
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public TestEditDelete() {
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
		frame.setTitle("Edit / Delete Test");
		frame.setBounds(100, 100, 806, 647);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEditDelete_1 = new JLabel("Edit / Delete Test");
		lblEditDelete_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditDelete_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEditDelete_1.setBounds(187, 10, 380, 40);
		frame.getContentPane().add(lblEditDelete_1);
		
		JLabel lblSelectDepartment = new JLabel("Select Department");
		lblSelectDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectDepartment.setBounds(10, 77, 219, 40);
		frame.getContentPane().add(lblSelectDepartment);
		
		txtTest = new JTextField();
		txtTest.setHorizontalAlignment(SwingConstants.CENTER);
		txtTest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTest.setColumns(10);
		txtTest.setBounds(267, 361, 300, 40);
		frame.getContentPane().add(txtTest);
		
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
		
		JLabel lblNewSubjectName = new JLabel("New Test Name");
		lblNewSubjectName.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewSubjectName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewSubjectName.setBounds(10, 361, 219, 40);
		frame.getContentPane().add(lblNewSubjectName);
		
		JLabel lblSelectPreviousSubject = new JLabel("Select Previous Test");
		lblSelectPreviousSubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectPreviousSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectPreviousSubject.setBounds(10, 289, 219, 40);
		frame.getContentPane().add(lblSelectPreviousSubject);
		
		cmbTest = new JComboBox<String>();
		cmbTest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbTest.setBounds(267, 291, 300, 40);
		frame.getContentPane().add(cmbTest);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
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
				else
				{
					try {
						DB.stt.execute("DELETE FROM test WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'AND class = '"+cmbClass.getSelectedItem().toString()+"' AND subject = '"+cmbSubject.getSelectedItem().toString()+"' AND name = '"+cmbTest.getSelectedItem().toString()+"'");
						boolean row = DB.stt.execute("DELETE FROM student WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'AND class = '"+cmbClass.getSelectedItem().toString()+"' AND subject = '"+cmbSubject.getSelectedItem().toString()+"' AND name = '"+cmbTest.getSelectedItem().toString()+"'");
						
						if(row)
						{
							JOptionPane.showMessageDialog(null, "Failed to delete test details", "Error", JOptionPane.ERROR_MESSAGE);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "Test Details Deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(620, 289, 150, 40);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblSelectClass = new JLabel("Select Class");
		lblSelectClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectClass.setBounds(10, 141, 219, 40);
		frame.getContentPane().add(lblSelectClass);
		
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
		cmbClass.setBounds(267, 143, 300, 40);
		frame.getContentPane().add(cmbClass);
		
		JLabel lblSelectSubject = new JLabel("Select Subject");
		lblSelectSubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectSubject.setBounds(10, 218, 219, 40);
		frame.getContentPane().add(lblSelectSubject);
		
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
		cmbSubject.setBounds(267, 220, 300, 40);
		frame.getContentPane().add(cmbSubject);
		
		JLabel lblNewTestDate = new JLabel("New Test Date");
		lblNewTestDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewTestDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewTestDate.setBounds(10, 433, 219, 40);
		frame.getContentPane().add(lblNewTestDate);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePanel.setBounds(267, 347, 300, 40);
		datePanel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		txtDatePicker = datePicker.getJFormattedTextField();
		txtDatePicker.setFont(new Font("Tahoma", Font.PLAIN, 16));
		datePicker.setBounds(267, 433, 300, 40);
		frame.getContentPane().add(datePicker);
		
		JLabel lblNewTestScore = new JLabel("New Test Score");
		lblNewTestScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewTestScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewTestScore.setBounds(10, 506, 219, 40);
		frame.getContentPane().add(lblNewTestScore);
		
		txtTestScore = new JTextField();
		txtTestScore.setHorizontalAlignment(SwingConstants.CENTER);
		txtTestScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTestScore.setColumns(10);
		txtTestScore.setBounds(267, 506, 300, 40);
		frame.getContentPane().add(txtTestScore);
		
		JButton btnUpdate = new JButton("Update");
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
				else if(txtTest.getText().toString().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter new test name", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(txtDatePicker.getText().toString().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please select new Test Date", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(txtTestScore.getText().toString().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter new test score", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					try {
						String setTest = txtTest.getText().toString();
						String setDate = txtDatePicker.getText().toString();
						String setScore = txtTestScore.getText().toString();
						String getTest = cmbTest.getSelectedItem().toString();
						String getSubject = cmbSubject.getSelectedItem().toString();
						String getClass = cmbClass.getSelectedItem().toString();
						String getDepartment = cmbDepartment.getSelectedItem().toString();
						
						boolean row = DB.stt.execute("UPDATE test, student SET "
								+ "test.name='"+setTest+"', student.test='"+setTest+"', test.date='"+setDate+"', test.total_score='"+setScore+"' WHERE "
										+ "test.name='"+getTest+"' AND student.test='"+getTest + "' AND "
										+ "test.subject='"+getSubject+"' AND student.subject='"+getSubject + "' AND "
										+ "test.class='"+getClass+"' AND student.class='"+getClass+"' AND "
										+ "test.department='"+getDepartment+"' AND student.department='"+getDepartment+"'");
						
						if(row)
						{
							JOptionPane.showMessageDialog(null, "Failed to update test details", "Error", JOptionPane.ERROR_MESSAGE);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "Test Details Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(620, 506, 150, 40);
		frame.getContentPane().add(btnUpdate);
	}
}
