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

public class DepartmentEditDelete {

	JFrame frame;
	static DepartmentEditDelete window;
	private JTextField txtDepartment;
	private JComboBox<String> cmbDepartment;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new DepartmentEditDelete();
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}


	public DepartmentEditDelete() {
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
		}
	}
	

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 204));
		frame.setTitle("Edit / Delete Department");
		frame.setBounds(100, 100, 767, 277);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddDepartment = new JLabel("Add Department");
		lblAddDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddDepartment.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddDepartment.setBounds(194, 10, 380, 40);
		frame.getContentPane().add(lblAddDepartment);
		
		JLabel lblNewName = new JLabel("New Name");
		lblNewName.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewName.setBounds(10, 156, 219, 40);
		frame.getContentPane().add(lblNewName);
		
		txtDepartment = new JTextField();
		txtDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		txtDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDepartment.setColumns(10);
		txtDepartment.setBounds(239, 156, 300, 40);
		frame.getContentPane().add(txtDepartment);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbDepartment.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "Please select department", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(txtDepartment.getText().toString().isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "Please enter new department name", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					String getDepartment = cmbDepartment.getSelectedItem().toString();
					String setDepartment = txtDepartment.getText().toString();
					try {
						boolean row = DB.stt.execute("UPDATE department, class, subject, test, student SET "
								+ "department.department='"+setDepartment+"', class.department='"+setDepartment+"', subject.department='"+setDepartment+"', test.department='"+setDepartment+"', student.department='"+setDepartment+"' WHERE "
										+ "department.department='"+getDepartment+"' AND class.department='"+getDepartment+"' AND subject.department='"+getDepartment+"' AND test.department='"+getDepartment+"' AND student.department='"+getDepartment+"'");
						if(row)
						{
							JOptionPane.showMessageDialog(null, "Failed to update department", "Error", JOptionPane.ERROR_MESSAGE);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "Department Name Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(581, 156, 150, 40);
		frame.getContentPane().add(btnUpdate);
		
		JLabel lblPreviousName = new JLabel("Previous Name*");
		lblPreviousName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreviousName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPreviousName.setBounds(10, 77, 219, 40);
		frame.getContentPane().add(lblPreviousName);
		
		cmbDepartment = new JComboBox<String>();
		cmbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbDepartment.setBounds(239, 79, 300, 40);
		frame.getContentPane().add(cmbDepartment);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbDepartment.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "Please select department", "Department Not Selected", JOptionPane.WARNING_MESSAGE);
				}
				else 
				{
					try {
						DB.stt.execute("DELETE FROM department WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'");
						DB.stt.execute("DELETE FROM class WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'");
						DB.stt.execute("DELETE FROM subject WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'");
						DB.stt.execute("DELETE FROM test WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'");
						boolean row = DB.stt.execute("DELETE FROM student WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'");
						
						if(row)
						{
							JOptionPane.showMessageDialog(null, "Failed to delete department", "Failed", JOptionPane.ERROR_MESSAGE);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "Department Deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(581, 77, 150, 40);
		frame.getContentPane().add(btnDelete);
	}
}
