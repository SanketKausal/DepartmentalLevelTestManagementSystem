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
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;

public class TestReport {

	JFrame frame;
	static TestReport window;
	private JTable table;
	String[] columnNames;
	private JLabel lblSelectDepartment;
	private JComboBox<String> cmbDepartment;
	private JLabel lblClass;
	private JComboBox<String> cmbClass;
	private JComboBox<String> cmbSubject;
	private JLabel lblSubject;
	DefaultTableModel model;
	Object[] row = new Object[6];

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new TestReport();
					CommonFunction.centreWindow(window.frame);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TestReport() {
		DB.initialize();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(230, 230, 250));
		frame.setTitle("Test Report");
		frame.setBounds(100, 100, 1357, 760);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		columnNames = new String[] { "Department", "Class", "Subject", "Test", "Date", "Total Marks" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		try {
			DB.res = DB.stt.executeQuery("SELECT * FROM test");
			while(DB.res.next())
			{
				row[0] = DB.res.getString("department");
				row[1] = DB.res.getString("class");
				row[2] = DB.res.getString("subject");
				row[3] = DB.res.getString("name");
				row[4] = DB.res.getString("date");
				row[5] = DB.res.getString("total_score");
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

		lblSelectDepartment = new JLabel("Department");
		lblSelectDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectDepartment.setBounds(10, 10, 161, 40);
		frame.getContentPane().add(lblSelectDepartment);

		cmbDepartment = new JComboBox<String>();
		cmbDepartment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbDepartment.setBounds(181, 10, 272, 40);
		cmbDepartment.addItem("- Select Department -");
		try {
			DB.res = DB.stt.executeQuery("SELECT DISTINCT department FROM test"); // no repeat
			while(DB.res.next())
			{
				cmbDepartment.addItem(DB.res.getString("department"));
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}	
		frame.getContentPane().add(cmbDepartment);

		lblClass = new JLabel("Class");
		lblClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblClass.setBounds(463, 10, 118, 40);
		frame.getContentPane().add(lblClass);

		cmbClass = new JComboBox<String>();
		cmbClass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbClass.setBounds(591, 10, 272, 40);
		cmbClass.addItem("- Select Class -");
		try {
			DB.res = DB.stt.executeQuery("SELECT DISTINCT class FROM test");
			while(DB.res.next())
			{
				cmbClass.addItem(DB.res.getString("class"));
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}	
		frame.getContentPane().add(cmbClass);

		cmbSubject = new JComboBox<String>();
		cmbSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbSubject.setBounds(1011, 10, 272, 40);
		cmbSubject.addItem("- Select Subject -");
		try {
			DB.res = DB.stt.executeQuery("SELECT DISTINCT subject FROM test");
			while(DB.res.next())
			{
				cmbSubject.addItem(DB.res.getString("subject"));
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}	
		frame.getContentPane().add(cmbSubject);

		lblSubject = new JLabel("Subject");
		lblSubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubject.setBounds(873, 10, 128, 40);
		frame.getContentPane().add(lblSubject);
		
		cmbDepartment.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filterTable();
			}
		});
		
		cmbClass.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filterTable();
			}
		});
		
		cmbSubject.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filterTable();
				}
		});
	}

	protected void filterTable() {
		if(cmbDepartment.getSelectedIndex() != 0 && cmbClass.getSelectedIndex() != 0 && cmbSubject.getSelectedIndex() != 0 ) 
		{
			model.setRowCount(0);
			try {
				DB.res = DB.stt.executeQuery("SELECT * FROM test WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"' AND class = '"+cmbClass.getSelectedItem().toString()+"' AND subject = '"+cmbSubject.getSelectedItem().toString()+"'");
				while(DB.res.next())
				{
					row[0] = DB.res.getString("department");
					row[1] = DB.res.getString("class");
					row[2] = DB.res.getString("subject");
					row[3] = DB.res.getString("name");
					row[4] = DB.res.getString("date");
					row[5] = DB.res.getString("total_score");
					model.addRow(row);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
		else if(cmbDepartment.getSelectedIndex() != 0 && cmbClass.getSelectedIndex() != 0 && cmbSubject.getSelectedIndex() == 0 ) 
		{
			model.setRowCount(0);
			try {
				DB.res = DB.stt.executeQuery("SELECT * FROM test WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"' AND class = '"+cmbClass.getSelectedItem().toString()+"'");
				while(DB.res.next())
				{
					row[0] = DB.res.getString("department");
					row[1] = DB.res.getString("class");
					row[2] = DB.res.getString("subject");
					row[3] = DB.res.getString("name");
					row[4] = DB.res.getString("date");
					row[5] = DB.res.getString("total_score");
					model.addRow(row);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
		
		else if(cmbDepartment.getSelectedIndex() != 0 && cmbClass.getSelectedIndex() == 0 && cmbSubject.getSelectedIndex() != 0 ) 
		{
			model.setRowCount(0);
			try {
				DB.res = DB.stt.executeQuery("SELECT * FROM test WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"' AND subject = '"+cmbSubject.getSelectedItem().toString()+"'");
				while(DB.res.next())
				{
					row[0] = DB.res.getString("department");
					row[1] = DB.res.getString("class");
					row[2] = DB.res.getString("subject");
					row[3] = DB.res.getString("name");
					row[4] = DB.res.getString("date");
					row[5] = DB.res.getString("total_score");
					model.addRow(row);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
		
		else if(cmbDepartment.getSelectedIndex() == 0 && cmbClass.getSelectedIndex() != 0 && cmbSubject.getSelectedIndex() != 0 ) 
		{
			model.setRowCount(0);
			try {
				DB.res = DB.stt.executeQuery("SELECT * FROM test WHERE class = '"+cmbClass.getSelectedItem().toString()+"' AND subject = '"+cmbSubject.getSelectedItem().toString()+"'");
				while(DB.res.next())
				{
					row[0] = DB.res.getString("department");
					row[1] = DB.res.getString("class");
					row[2] = DB.res.getString("subject");
					row[3] = DB.res.getString("name");
					row[4] = DB.res.getString("date");
					row[5] = DB.res.getString("total_score");
					model.addRow(row);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
		else if(cmbDepartment.getSelectedIndex() != 0 && cmbClass.getSelectedIndex() == 0 && cmbSubject.getSelectedIndex() == 0 ) 
		{
			model.setRowCount(0);
			try {
				DB.res = DB.stt.executeQuery("SELECT * FROM test WHERE department = '"+cmbDepartment.getSelectedItem().toString()+"'");
				while(DB.res.next())
				{
					row[0] = DB.res.getString("department");
					row[1] = DB.res.getString("class");
					row[2] = DB.res.getString("subject");
					row[3] = DB.res.getString("name");
					row[4] = DB.res.getString("date");
					row[5] = DB.res.getString("total_score");
					model.addRow(row);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
		else if(cmbDepartment.getSelectedIndex() == 0 && cmbClass.getSelectedIndex() != 0 && cmbSubject.getSelectedIndex() == 0 ) 
		{
			model.setRowCount(0);
			try {
				DB.res = DB.stt.executeQuery("SELECT * FROM test WHERE class = '"+cmbClass.getSelectedItem().toString()+"'");
				while(DB.res.next())
				{
					row[0] = DB.res.getString("department");
					row[1] = DB.res.getString("class");
					row[2] = DB.res.getString("subject");
					row[3] = DB.res.getString("name");
					row[4] = DB.res.getString("date");
					row[5] = DB.res.getString("total_score");
					model.addRow(row);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
		else if(cmbDepartment.getSelectedIndex() == 0 && cmbClass.getSelectedIndex() == 0 && cmbSubject.getSelectedIndex() != 0 ) 
		{
			model.setRowCount(0);
			try {
				DB.res = DB.stt.executeQuery("SELECT * FROM test WHERE subject = '"+cmbSubject.getSelectedItem().toString()+"'");
				while(DB.res.next())
				{
					row[0] = DB.res.getString("department");
					row[1] = DB.res.getString("class");
					row[2] = DB.res.getString("subject");
					row[3] = DB.res.getString("name");
					row[4] = DB.res.getString("date");
					row[5] = DB.res.getString("total_score");
					model.addRow(row);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
		else {
			model.setRowCount(0);
			try {
				DB.res = DB.stt.executeQuery("SELECT * FROM test ");
				while(DB.res.next())
				{
					row[0] = DB.res.getString("department");
					row[1] = DB.res.getString("class");
					row[2] = DB.res.getString("subject");
					row[3] = DB.res.getString("name");
					row[4] = DB.res.getString("date");
					row[5] = DB.res.getString("total_score");
					model.addRow(row);
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}	
		}
		
	}
}
