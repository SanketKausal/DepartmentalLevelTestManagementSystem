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

public class ClassAdd {

	JFrame frame;
	static ClassAdd window;
	private JTextField txtClass;
	private JComboBox<String> cmbDepartment;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new ClassAdd();
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	
	public ClassAdd() {
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
		frame.getContentPane().setBackground(new Color(204, 255, 204));
		frame.setTitle("Add Class");
		frame.setBounds(100, 100, 817, 259);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddClass = new JLabel("Add Class");
		lblAddClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddClass.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddClass.setBounds(187, 10, 380, 40);
		frame.getContentPane().add(lblAddClass);
		
		JLabel lblSelectDepartment = new JLabel("Select Department");
		lblSelectDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectDepartment.setBounds(10, 77, 219, 40);
		frame.getContentPane().add(lblSelectDepartment);
		
		txtClass = new JTextField();
		txtClass.setHorizontalAlignment(SwingConstants.CENTER);
		txtClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtClass.setColumns(10);
		txtClass.setBounds(267, 147, 300, 40);
		frame.getContentPane().add(txtClass);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cmbDepartment.getSelectedIndex() == 0)
				{
					JOptionPane.showMessageDialog(null, "Please select department", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else if(txtClass.getText().toString().isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "Please enter new class name", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else 
				{
					try {
						boolean row = DB.stt.execute("INSERT INTO class (department, class) VALUES ('"+cmbDepartment.getSelectedItem().toString()+"', '"+txtClass.getText().toString()+"')");
						if(row)
						{
							JOptionPane.showMessageDialog(null, "Failed to create class", "Failed", JOptionPane.ERROR_MESSAGE);
						} 
						else 
						{
							JOptionPane.showMessageDialog(null, "New CLass Added.", "Success", JOptionPane.INFORMATION_MESSAGE);
							frame.dispose();
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreate.setBounds(617, 147, 150, 40);
		frame.getContentPane().add(btnCreate);
		
		cmbDepartment = new JComboBox<String>();
		cmbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbDepartment.setBounds(267, 79, 300, 40);
		frame.getContentPane().add(cmbDepartment);
		
		JLabel lblClassName = new JLabel("Class Name");
		lblClassName.setHorizontalAlignment(SwingConstants.CENTER);
		lblClassName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClassName.setBounds(10, 147, 219, 40);
		frame.getContentPane().add(lblClassName);
	}
}
