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

public class SubjectEditDelete {

	JFrame frame;
	static SubjectEditDelete window;
	private JTextField txtSubject;
	private JComboBox<String> cmbDepartment;
	private JComboBox<String> cmbSubject;
	private JComboBox<String> cmbClass;
	boolean isDone = false;
	boolean isClassLoad = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new SubjectEditDelete();
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SubjectEditDelete() {
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
		frame.getContentPane().setBackground(new Color(250, 235, 215));
		frame.setTitle("Edit / Delete  Subject");
		frame.setBounds(100, 100, 811, 403);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEditDelete_1 = new JLabel("Edit / Delete Subject");
		lblEditDelete_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditDelete_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEditDelete_1.setBounds(187, 10, 380, 40);
		frame.getContentPane().add(lblEditDelete_1);
		
		JLabel lblSelectDepartment = new JLabel("Select Department");
		lblSelectDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectDepartment.setBounds(10, 77, 219, 40);
		frame.getContentPane().add(lblSelectDepartment);
		
		txtSubject = new JTextField();
		txtSubject.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSubject.setColumns(10);
		txtSubject.setBounds(267, 290, 300, 40);
		frame.getContentPane().add(txtSubject);
		
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
				else if(txtSubject.getText().toString().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter subject name", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					try {
						String setSubject = txtSubject.getText().toString();
						String getSubject = cmbSubject.getSelectedItem().toString();
						String getClass = cmbClass.getSelectedItem().toString();
						String getDepartment = cmbDepartment.getSelectedItem().toString();
						
						boolean row = DB.stt.execute("UPDATE subject, test, student SET "
								+ "subject.subject='"+setSubject+"', test.subject='"+setSubject+"', student.subject='"+setSubject+"' WHERE "
										+ "subject.subject='"+getSubject+"' AND test.subject='"+getSubject+"' AND student.subject='"+getSubject + "' AND "
										+ "subject.class='"+getClass+"' AND test.class='"+getClass+"' AND student.class='"+getClass+"' AND "
										+ "subject.department='"+getDepartment+"' AND test.department='"+getDepartment+"' AND student.department='"+getDepartment+"'");
									
						
						if(row)
						{
							JOptionPane.showMessageDialog(null, "Failed to update subject", "Error", JOptionPane.ERROR_MESSAGE);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "Subject Name Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(620, 290, 150, 40);
		frame.getContentPane().add(btnUpdate);
		
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
		
		JLabel lblNewSubjectName = new JLabel("New Subject Name");
		lblNewSubjectName.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewSubjectName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewSubjectName.setBounds(10, 290, 219, 40);
		frame.getContentPane().add(lblNewSubjectName);
		
		JLabel lblSelectPreviousSubject = new JLabel("Select Previous Subject");
		lblSelectPreviousSubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectPreviousSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectPreviousSubject.setBounds(10, 218, 219, 40);
		frame.getContentPane().add(lblSelectPreviousSubject);
		
		cmbSubject = new JComboBox<String>();
		cmbSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbSubject.setBounds(267, 220, 300, 40);
		frame.getContentPane().add(cmbSubject);
		
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
				else 
				{
					try {
						DB.stt.execute("DELETE FROM subject WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'AND class = '"+cmbClass.getSelectedItem().toString()+"' AND subject = '"+cmbSubject.getSelectedItem().toString()+"'");
						DB.stt.execute("DELETE FROM test WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'AND class = '"+cmbClass.getSelectedItem().toString()+"' AND subject = '"+cmbSubject.getSelectedItem().toString()+"'");
						boolean row = DB.stt.execute("DELETE FROM student WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'AND class = '"+cmbClass.getSelectedItem().toString()+"' AND subject = '"+cmbSubject.getSelectedItem().toString()+"'");
						
						if(row)
						{
							JOptionPane.showMessageDialog(null, "Failed to delete subject", "Failed", JOptionPane.ERROR_MESSAGE);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "Subject Deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(620, 218, 150, 40);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblSelectClass = new JLabel("Select Class");
		lblSelectClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectClass.setBounds(10, 141, 219, 40);
		frame.getContentPane().add(lblSelectClass);
		
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
		cmbClass.setBounds(267, 143, 300, 40);
		frame.getContentPane().add(cmbClass);
	}

}
