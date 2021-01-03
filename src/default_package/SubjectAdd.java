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

public class SubjectAdd {

	//varialbe
	JFrame frame;
	static SubjectAdd window;
	private JTextField txtSubject;
	private JComboBox<String> cmbDepartment;
	private JComboBox<String> cmbClass;
	boolean isDone = false;

	// main #1
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new SubjectAdd();
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//constructor #1 ... in case 2

	public SubjectAdd() {
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
		frame.getContentPane().setBackground(new Color(250, 235, 215));
		frame.setTitle("Add Subject");
		frame.setBounds(100, 100, 809, 323);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddSubject = new JLabel("Add Subject");
		lblAddSubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddSubject.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddSubject.setBounds(187, 10, 380, 40);
		frame.getContentPane().add(lblAddSubject);
		
		JLabel lblSelectDepartment = new JLabel("Select Department");
		lblSelectDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectDepartment.setBounds(10, 77, 219, 40);
		frame.getContentPane().add(lblSelectDepartment);
		
		txtSubject = new JTextField();
		txtSubject.setHorizontalAlignment(SwingConstants.CENTER);
		txtSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSubject.setColumns(10);
		txtSubject.setBounds(267, 215, 300, 40);
		frame.getContentPane().add(txtSubject);
		
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
				else if(txtSubject.getText().toString().isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "Please enter new subject name", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else 
				{
					try {
						boolean row = DB.stt.execute("INSERT INTO subject (department, class, subject) "
								+ "VALUES ('"+cmbDepartment.getSelectedItem().toString()+"', '"+cmbClass.getSelectedItem().toString()+"', '"+txtSubject.getText().toString()+"')");
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
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreate.setBounds(618, 215, 150, 40);
		frame.getContentPane().add(btnCreate);
		
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
		
		JLabel lblSubjectName = new JLabel("Subject Name");
		lblSubjectName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubjectName.setBounds(10, 215, 219, 40);
		frame.getContentPane().add(lblSubjectName);
		
		cmbClass = new JComboBox<String>();
		cmbClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbClass.setBounds(267, 149, 300, 40);
		frame.getContentPane().add(cmbClass);
		
		JLabel lblSelectClass = new JLabel("Select Class");
		lblSelectClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectClass.setBounds(10, 147, 219, 40);
		frame.getContentPane().add(lblSelectClass);
	}

}
