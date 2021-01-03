package default_package;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DepartmentAdd {

	JFrame frame;
	static DepartmentAdd window;
	private JTextField txtName;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new DepartmentAdd();
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DepartmentAdd() {
		DB.initialize();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 204));
		frame.setTitle("Add Department");
		frame.setBounds(100, 100, 845, 220);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddDepartment = new JLabel("Add Department");
		lblAddDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddDepartment.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddDepartment.setBounds(223, 23, 380, 40);
		frame.getContentPane().add(lblAddDepartment);
		
		JLabel lblNameOfDepartment = new JLabel("Name of Department");
		lblNameOfDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameOfDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNameOfDepartment.setBounds(46, 90, 219, 40);
		frame.getContentPane().add(lblNameOfDepartment);
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtName.setColumns(10);
		txtName.setBounds(301, 90, 300, 40);
		frame.getContentPane().add(txtName);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtName.getText().toString().isEmpty()) 
				{
					JOptionPane.showMessageDialog(null,"Please enter department name","Error", JOptionPane.ERROR_MESSAGE);
				}
				else 
				{
					try {
						boolean row = DB.stt.execute("INSERT INTO department (department) VALUES ('"+txtName.getText().toString()+"')");
						if(row)
						{
							JOptionPane.showMessageDialog(null, "Failed to create department", "Failed", JOptionPane.ERROR_MESSAGE);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "New Department Added.", "Success", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreate.setBounds(651, 90, 150, 40);
		frame.getContentPane().add(btnCreate);
	}
}
