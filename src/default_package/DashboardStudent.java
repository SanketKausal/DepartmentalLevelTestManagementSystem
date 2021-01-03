package default_package;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DashboardStudent {

	JFrame frame;
	static DashboardStudent window;
	private JTable table;
	String[] columnNames;
	private JLabel lblSubject;
	DefaultTableModel model;
	Object[] row = new Object[5];
	String setName;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new DashboardStudent();
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DashboardStudent() {
		setName = "Welcome! Student";
		DB.initialize();
		initialize();
		lblSubject.setText(setName);
	}

	public DashboardStudent(String getName) {
		setName = getName;
		DB.initialize();
		initialize();
		lblSubject.setText("Welcome! " + getName);
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(238, 232, 170));
		frame.setTitle("Student Dashboard");
		frame.setBounds(100, 100, 1357, 760);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
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
		btnNewButton.setBounds(36, 10, 141, 49);
		frame.getContentPane().add(btnNewButton);
		
		
		columnNames = new String[] { "Subject", "Test Name", "Date", "Score", "Total Marks" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		try {
			DB.res = DB.stt.executeQuery("SELECT student.subject, student.test, test.date, student.score, student.total_marks FROM test, student WHERE student.name='"+setName+"' AND student.department = test.department AND student.subject = test.subject AND student.class= test.class AND student.test = test.name");		
			while(DB.res.next())
			{	
				row[0] = DB.res.getString("subject");
				row[1] = DB.res.getString("test");
				row[2] = DB.res.getString("date");
				row[3] = DB.res.getString("score");
				row[4] = DB.res.getString("total_marks");
				model.addRow(row);
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}	
		
		
		table = new JTable();
		table.setModel(model);
		table.setRowSelectionAllowed(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setIntercellSpacing(new Dimension(40, 0));
		table.setRowHeight(40);
		table.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().setLayout(null);
		table.setBounds(30, 40, 200, 300);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(0, 72, 1343, 651);
		sp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(sp);
		

		lblSubject = new JLabel();
		lblSubject.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSubject.setBounds(873, 10, 460, 49);
		frame.getContentPane().add(lblSubject);
		
	}
}
