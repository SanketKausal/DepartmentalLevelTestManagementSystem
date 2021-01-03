package default_package;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class TestAdd {

	JFrame frame;
	static TestAdd window;
	private JTextField txtTest;
	private JComboBox<String> cmbDepartment;
	private JComboBox<String> cmbSubject;
	private JComboBox<String> cmbClass;
	boolean isDone = false;
	boolean isClassLoad = false;
	private JTextField txtTestScore;
	private JFormattedTextField txtDatePicker;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new TestAdd();
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestAdd() {
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
		frame.setTitle("Add Test");
		frame.setBounds(100, 100, 801, 533);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddTest = new JLabel("Add Test");
		lblAddTest.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddTest.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddTest.setBounds(187, 10, 380, 40);
		frame.getContentPane().add(lblAddTest);
		
		JLabel lblSelectDepartment = new JLabel("Select Department");
		lblSelectDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectDepartment.setBounds(10, 77, 219, 40);
		frame.getContentPane().add(lblSelectDepartment);
		
		txtTest = new JTextField();
		txtTest.setHorizontalAlignment(SwingConstants.CENTER);
		txtTest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTest.setColumns(10);
		txtTest.setBounds(267, 281, 300, 40);
		frame.getContentPane().add(txtTest);
		
		cmbDepartment = new JComboBox<String>();
		cmbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbDepartment.getSelectedIndex() == 0) 
				{
					isClassLoad = false;
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
		cmbDepartment.setBounds(267, 79, 300, 40);
		frame.getContentPane().add(cmbDepartment);
		
		JLabel lblTestName = new JLabel("Test Name");
		lblTestName.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTestName.setBounds(10, 281, 219, 40);
		frame.getContentPane().add(lblTestName);
		
		cmbSubject = new JComboBox<String>();
		cmbSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbSubject.setBounds(267, 215, 300, 40);
		frame.getContentPane().add(cmbSubject);
		
		JLabel lblSelectSubject = new JLabel("Select Subject");
		lblSelectSubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectSubject.setBounds(10, 213, 219, 40);
		frame.getContentPane().add(lblSelectSubject);
		
		JLabel lblSelectClass_1 = new JLabel("Select Class");
		lblSelectClass_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectClass_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectClass_1.setBounds(10, 148, 219, 40);
		frame.getContentPane().add(lblSelectClass_1);
		
		cmbClass = new JComboBox<String>();
		cmbClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
					}
				}
			}
		});
		cmbClass.setBounds(267, 150, 300, 40);
		frame.getContentPane().add(cmbClass);
		
		
		JLabel lblTestDate = new JLabel("Test Date");
		lblTestDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTestDate.setBounds(10, 347, 219, 40);
		frame.getContentPane().add(lblTestDate);
		
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
		datePicker.setBounds(267, 347, 300, 40);
		frame.getContentPane().add(datePicker);
		

		JLabel lblTestName_1_1 = new JLabel("Test Score (Out Of)");
		lblTestName_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTestName_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTestName_1_1.setBounds(10, 417, 219, 40);
		frame.getContentPane().add(lblTestName_1_1);
		
		txtTestScore = new JTextField();
		txtTestScore.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
	            	txtTestScore.setEditable(true);
	           
	            } else {
	            	txtTestScore.setEditable(false);
	      
	            }
			}
		});
		txtTestScore.setHorizontalAlignment(SwingConstants.CENTER);
		txtTestScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTestScore.setColumns(10);
		txtTestScore.setBounds(267, 417, 300, 40);
		frame.getContentPane().add(txtTestScore);
		
		JButton btnCreate_1_1 = new JButton("Create");
		btnCreate_1_1.addActionListener(new ActionListener() {
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
				else if(txtTest.getText().toString().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter test name", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(txtDatePicker.getText().toString().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please select Test Date", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(txtTestScore.getText().toString().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter test score", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else 
				{
					try {
						boolean row = DB.stt.execute("INSERT INTO test (department, class, subject, name, date, total_score) "
								+ "VALUES ('"+cmbDepartment.getSelectedItem().toString()+"', "
										+ "'"+cmbClass.getSelectedItem().toString()+"',"
										+ "'"+cmbSubject.getSelectedItem().toString()+"', "
												+ "'"+txtTest.getText().toString()+"', '"+txtDatePicker.getText().toString()+"', "+txtTestScore.getText()+")");
						if(row)
						{
							JOptionPane.showMessageDialog(null, "Failed to create subject", "Failed", JOptionPane.ERROR_MESSAGE);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "New Subject Added", "Success", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnCreate_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreate_1_1.setBounds(618, 417, 150, 40);
		frame.getContentPane().add(btnCreate_1_1);
	}
}
