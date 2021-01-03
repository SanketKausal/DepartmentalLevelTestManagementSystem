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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ClassEditDelete {

	JFrame frame;
	static ClassEditDelete window;
	private JTextField txtClass;
	private JComboBox<String> cmbDepartment;
	private JComboBox<String> cmbClass;
	boolean isDone = false;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ClassEditDelete();
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	
	public ClassEditDelete() {
		DB.initialize();
		initialize();
		try {
			DB.res = DB.stt.executeQuery("SELECT * FROM department");
			cmbDepartment.addItem("- Select Department -");
			cmbClass.addItem("- Select Department First -");
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
		frame.getContentPane().setBackground(new Color(204, 255, 204));
		frame.setTitle("Edit / Delete Class");
		frame.setBounds(100, 100, 816, 337);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEditDelete = new JLabel("Edit / Delete Class");
		lblEditDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEditDelete.setBounds(187, 10, 380, 40);
		frame.getContentPane().add(lblEditDelete);
		
		JLabel lblSelectDepartment = new JLabel("Select Department");
		lblSelectDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectDepartment.setBounds(10, 77, 219, 40);
		frame.getContentPane().add(lblSelectDepartment);
		
		txtClass = new JTextField();
		txtClass.setHorizontalAlignment(SwingConstants.CENTER);
		txtClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtClass.setColumns(10);
		txtClass.setBounds(267, 219, 300, 40);
		frame.getContentPane().add(txtClass);
		
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
				else if(txtClass.getText().toString().isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "Please enter new class name", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					try {
						String setClass = txtClass.getText().toString();
						String getClass = cmbClass.getSelectedItem().toString();
						String getDepartment = cmbDepartment.getSelectedItem().toString();
						
						boolean row = DB.stt.execute("UPDATE class, subject, test, student SET "
								+ "class.class='"+setClass+"', subject.class='"+setClass+"', test.class='"+setClass+"', student.class='"+setClass+"' WHERE "
										+ "class.class='"+getClass+"' AND subject.class='"+getClass+"' AND test.class='"+getClass+"' AND student.class='"+getClass+"' AND "
												+ "class.department='"+getDepartment+"' AND subject.department='"+getDepartment+"' AND test.department='"+getDepartment+"' AND student.department='"+getDepartment+"'");
						
						if(row)
						{
							JOptionPane.showMessageDialog(null, "Failed to update class", "Error", JOptionPane.ERROR_MESSAGE);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "Class Name Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(620, 219, 150, 40);
		frame.getContentPane().add(btnUpdate);
		
		cmbDepartment = new JComboBox<String>();
		cmbDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
					}
				}
			}
		});
		cmbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbDepartment.setBounds(267, 79, 300, 40);
		frame.getContentPane().add(cmbDepartment);
		
		JLabel lblNewClassName = new JLabel("New Class Name");
		lblNewClassName.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewClassName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewClassName.setBounds(10, 219, 219, 40);
		frame.getContentPane().add(lblNewClassName);
		
		JLabel lblSelectPreviousClass = new JLabel("Select Previous Class");
		lblSelectPreviousClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectPreviousClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectPreviousClass.setBounds(10, 147, 219, 40);
		frame.getContentPane().add(lblSelectPreviousClass);
		
		cmbClass = new JComboBox<String>();
		cmbClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbClass.setBounds(267, 149, 300, 40);
		frame.getContentPane().add(cmbClass);
		
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
				else 
				{
					try {
						DB.stt.execute("DELETE FROM class WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'AND class = '"+cmbClass.getSelectedItem().toString()+"'");
						DB.stt.execute("DELETE FROM subject WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'AND class = '"+cmbClass.getSelectedItem().toString()+"'");
						DB.stt.execute("DELETE FROM test WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'AND class = '"+cmbClass.getSelectedItem().toString()+"'");
						boolean row = DB.stt.execute("DELETE FROM student WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'AND class = '"+cmbClass.getSelectedItem().toString()+"'");
						
						if(row)
						{
							JOptionPane.showMessageDialog(null, "Failed to delete class", "Failed", JOptionPane.ERROR_MESSAGE);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "Class Deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(620, 147, 150, 40);
		frame.getContentPane().add(btnDelete);
	}
}
