package default_package;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DashboardAdmin {

	JFrame frame;
	static DashboardAdmin window;
	private JLabel lblNewLabel = new JLabel();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new DashboardAdmin();
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//constructor
	public DashboardAdmin() {
		DB.initialize();
		initialize();
		lblNewLabel.setText("Welcome! Admin");	
	}

	// parametrized constructor
	public DashboardAdmin(String string) {
		DB.initialize();
		initialize();
		lblNewLabel.setText("Welcome! " + string); // custom name aayega
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Dashboard Admin");
		frame.setBounds(100, 100, 938, 479);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(348, 15, 567, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 85, 526, 61);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Department");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(14, 10, 146, 30);
		panel.add(lblNewLabel_1);
		
		JButton btnAddDepartment = new JButton("Add");
		btnAddDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DepartmentAdd window = new DepartmentAdd();
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnAddDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddDepartment.setBounds(174, 12, 146, 29);
		panel.add(btnAddDepartment);
		
		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Login window = new Login();
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(11, 12, 141, 49);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnEditDepartment = new JButton("Edit / Delete");
		btnEditDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DepartmentEditDelete window = new DepartmentEditDelete();
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnEditDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditDepartment.setBounds(334, 12, 178, 29);
		panel.add(btnEditDepartment);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 156, 526, 61);
		frame.getContentPane().add(panel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Class");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(12, 10, 146, 30);
		panel_1.add(lblNewLabel_1_1);
		
		JButton btnAddClass = new JButton("Add");
		btnAddClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClassAdd window = new ClassAdd();
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnAddClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddClass.setBounds(170, 12, 146, 29);
		panel_1.add(btnAddClass);
		
		JButton btnEditClass = new JButton("Edit / Delete");
		btnEditClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ClassEditDelete window = new ClassEditDelete();
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnEditClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditClass.setBounds(328, 12, 184, 29);
		panel_1.add(btnEditClass);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(10, 227, 526, 61);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel_1_2 = new JLabel("Subject");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(13, 10, 146, 30);
		panel_2.add(lblNewLabel_1_2);
		
		JButton btnAddSubject = new JButton("Add");
		btnAddSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SubjectAdd window = new SubjectAdd(); // automatically it will call constructor
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnAddSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddSubject.setBounds(172, 12, 146, 29);
		panel_2.add(btnAddSubject);
		
		JButton btnEditSubject = new JButton("Edit / Delete");
		btnEditSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SubjectEditDelete window = new SubjectEditDelete();
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnEditSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditSubject.setBounds(331, 12, 181, 29);
		panel_2.add(btnEditSubject);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(10, 298, 905, 61);
		frame.getContentPane().add(panel_4);
		
		JLabel lblNewLabel_1_4 = new JLabel("Test");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(10, 10, 146, 30);
		panel_4.add(lblNewLabel_1_4);
		
		JButton btnAddTest = new JButton("Add");
		btnAddTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestAdd window = new TestAdd();
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnAddTest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddTest.setBounds(166, 12, 146, 29);
		panel_4.add(btnAddTest);
		
		JButton btnEditTest = new JButton("Edit / Delete");
		btnEditTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestEditDelete window = new TestEditDelete();
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnEditTest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditTest.setBounds(334, 12, 166, 29);
		panel_4.add(btnEditTest);
		
		JButton btnReportTest = new JButton("Report");
		btnReportTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestReport window = new TestReport();
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnReportTest.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReportTest.setBounds(520, 12, 146, 29);
		panel_4.add(btnReportTest);
		
		JButton btnGiveMarksTo = new JButton("Give marks to Student");
		btnGiveMarksTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestMarks window = new TestMarks();
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnGiveMarksTo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGiveMarksTo.setBounds(691, 12, 204, 29);
		panel_4.add(btnGiveMarksTo);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 369, 694, 61);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Student");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(10, 10, 146, 30);
		panel_3.add(lblNewLabel_1_3);
		
		JButton btnAddStudent = new JButton("Add");
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentAdd window = new StudentAdd();
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnAddStudent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddStudent.setBounds(166, 12, 146, 29);
		panel_3.add(btnAddStudent);
		
		JButton btnEditStudent = new JButton("Edit / Delete");
		btnEditStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentEditDelete window = new StudentEditDelete();
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnEditStudent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEditStudent.setBounds(322, 12, 175, 29);
		panel_3.add(btnEditStudent);
		
		JButton btnReportStudent = new JButton("Report");
		btnReportStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentReport window = new StudentReport();
				CommonFunction.centreWindow(window.frame);
				window.frame.setVisible(true);
			}
		});
		btnReportStudent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReportStudent.setBounds(519, 12, 161, 29);
		panel_3.add(btnReportStudent);
	}
}
