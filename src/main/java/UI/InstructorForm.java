package UI;

import java.awt.Dimension;


import javax.swing.JPanel;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;

import BLL.Course;
import BLL.CourseBLL;
import BLL.CourseInstructor;
import BLL.InstructorBLL;
import BLL.Person;
import BLL.PersonBLL;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class InstructorForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JTable table;
	private JTextField textField;

	
	/**
	 * Create the panel.
	 */
	public static void findByTitle(String title) {
		int no = 1;
		
		InstructorBLL instructorBLL = new InstructorBLL();
		ArrayList<CourseInstructor> arr = instructorBLL.findByTitle(title);
		
		PersonBLL personBLL= new PersonBLL();
		ArrayList<Person> arrperson = personBLL.getAllPerson();
		
		CourseBLL courseBLL= new CourseBLL();
		ArrayList<Course> arrCourse = courseBLL.getAllCourse();
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (CourseInstructor o : arr) {
			for (Course o1 :arrCourse) {
				if(o.getCourseID() == o1.getCourseID()) {
					for (Person o2 :arrperson) {
						if(o.getPersonID()==o2.getPersonId()){
							String fullCourse = o1.getCourseID() + " - " + o1.getTitle();
							String fullname = o2.getPersonId() + " - " + o2.getLastname()+ " " +o2.getFirstname();
							model.addRow(new Object[] {no++,fullCourse ,fullname });
						}
					}
				}
			}
			
			
		}
	}
	public static void displayData() {
		int no = 1;
		
		InstructorBLL instructorBLL = new InstructorBLL();
		ArrayList<CourseInstructor> arr = instructorBLL.getAllCourseInstructor();
		
		PersonBLL personBLL= new PersonBLL();
		ArrayList<Person> arrperson = personBLL.getAllPerson();
		
		CourseBLL courseBLL= new CourseBLL();
		ArrayList<Course> arrCourse = courseBLL.getAllCourse();
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (CourseInstructor o : arr) {
			for (Course o1 :arrCourse) {
				if(o.getCourseID() == o1.getCourseID()) {
					for (Person o2 :arrperson) {
						if(o.getPersonID()==o2.getPersonId()){
							String fullCourse = o1.getCourseID() + " - " + o1.getTitle();
							String fullname = o2.getPersonId() + " - " + o2.getLastname()+ " " +o2.getFirstname();
							model.addRow(new Object[] {no++,fullCourse ,fullname });
						}
					}
				}
			}
			
			
		}
	}
	public InstructorForm() {
		setPreferredSize(new Dimension(835,580));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 91, 627, 372);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No","Title","Fullname"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField.setBounds(461, 22, 169, 30);
		add(textField);
		textField.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dataFind = textField.getText();
				findByTitle(dataFind);
			}
		});
		btnFind.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFind.setBounds(640, 22, 85, 30);
		add(btnFind);
		
		JButton btnRefesh = new JButton("Refesh");
		btnRefesh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				displayData();
			}
		});
		btnRefesh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnRefesh.setBounds(100, 520, 85, 30);
		add(btnRefesh);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
	            if (selectedRow != -1) {
	            	String insString = (String) table.getValueAt(selectedRow, 2);
	            	String couString = (String) table.getValueAt(selectedRow, 1);
	            	String[] ins = insString.split(" - ");
	                String[] cou = couString.split(" - ");
	                int idIns = Integer.parseInt(ins[0]);
	                int idCou = Integer.parseInt(cou[0]);
	                InstructorFormAdd inForm = new InstructorFormAdd();
	                inForm.setData(idIns,idCou, ins[1], cou[1],false);
	                inForm.setVisible(true);
	                inForm.setLocationRelativeTo(null);
	            }
			}
		});
		btnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEdit.setBounds(634, 520, 91, 30);
		add(btnEdit);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(98, 68, 627, 11);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(98, 489, 627, 11);
		add(separator_1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InstructorFormAdd inForm = new InstructorFormAdd();
                inForm.setVisible(true);
                inForm.setLocationRelativeTo(null);
			}
		});
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAdd.setBounds(519, 520, 91, 30);
		add(btnAdd);
		displayData();
	}
}
