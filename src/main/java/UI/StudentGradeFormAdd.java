package UI;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BLL.CourseBLL;
import BLL.Person;
import BLL.PersonBLL;
import BLL.StudentGrade;
import BLL.StudentGradeBLL;

import java.awt.Color;

public class StudentGradeFormAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable tableList;
	private static JTable tableListAdd;
	private static JLabel lblDepar;
	private static JLabel lblCourse;
	private static ArrayList<Object[]> arrListAdd = new ArrayList<>();
	private static int courseid;
	private static String department1;
	private static String course1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGradeFormAdd frame = new StudentGradeFormAdd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentGradeFormAdd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 719, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 705, 35);
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaption);
		contentPane.add(panel);
		
		JLabel lblNew = new JLabel("StudenGrade Add");
		lblNew.setHorizontalAlignment(SwingConstants.CENTER);
		lblNew.setBounds(0, 0, 705, 35);
		lblNew.setForeground(SystemColor.textHighlightText);
		lblNew.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel.add(lblNew);
		
		lblDepar = new JLabel("Department");
		lblDepar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDepar.setBounds(20, 45, 203, 30);
		contentPane.add(lblDepar);
		
		lblCourse = new JLabel("Course");
		lblCourse.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCourse.setBounds(469, 42, 203, 30);
		contentPane.add(lblCourse);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 85, 675, 9);
		contentPane.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 132, 286, 286);
		contentPane.add(scrollPane);
		
		tableList = new JTable();
		tableList.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No","ID", "Fullname"
			}
		));
		tableList.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableList.getColumnModel().getColumn(1).setPreferredWidth(10);
		tableList.getColumnModel().getColumn(2).setPreferredWidth(150);
		scrollPane.setViewportView(tableList);
		
		JLabel lblListPerson = new JLabel("List Person");
		lblListPerson.setForeground(new Color(255, 0, 0));
		lblListPerson.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblListPerson.setBounds(20, 92, 84, 30);
		contentPane.add(lblListPerson);
		
		JLabel lblListStudenAdd = new JLabel("List Studen Add");
		lblListStudenAdd.setForeground(new Color(255, 0, 0));
		lblListStudenAdd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblListStudenAdd.setBounds(409, 92, 116, 30);
		contentPane.add(lblListStudenAdd);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(409, 132, 286, 286);
		contentPane.add(scrollPane_1);
		
		tableListAdd = new JTable();
		tableListAdd.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No","ID", "Fullname"
			}
		));
		tableListAdd.getColumnModel().getColumn(0).setPreferredWidth(10);
		tableListAdd.getColumnModel().getColumn(1).setPreferredWidth(10);
		tableListAdd.getColumnModel().getColumn(2).setPreferredWidth(150);
		scrollPane_1.setViewportView(tableListAdd);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 485, 675, 9);
		contentPane.add(separator_1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableList.getSelectedRow();
		        if (selectedRow != -1) {
		            
		            int id = (Integer) tableList.getValueAt(selectedRow, 1);
		            String fullname = (String) tableList.getValueAt(selectedRow, 2);

		            boolean idExists = false;
		            for (Object[] rowData : arrListAdd) {
		                if ((Integer) rowData[0] == id) {
		                    idExists = true;
		                    break;
		                }
		            }

		            if (!idExists) {
		                arrListAdd.add(new Object[] { id, fullname });
		                int no = 0;
		                DefaultTableModel model = (DefaultTableModel) tableListAdd.getModel();
		                model.setRowCount(0);

		                for (Object[] rowData : arrListAdd) {
		                    model.addRow(new Object[] { ++no, rowData[0], rowData[1] });
		                }
		            } else {
		            	JOptionPane.showMessageDialog(null, "Person đã tồn tại");
		            }
		        }
		    }
		});

		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAdd.setBounds(20, 439, 85, 30);
		contentPane.add(btnAdd);
		
		JButton btnDel = new JButton("Del");
		btnDel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = tableListAdd.getSelectedRow();
		        if (selectedRow != -1) {
		            int id = (Integer) tableListAdd.getValueAt(selectedRow, 1);

		            java.util.Iterator<Object[]> iterator = arrListAdd.iterator();
		            while (iterator.hasNext()) {
		                Object[] rowData = iterator.next();
		                if ((Integer) rowData[0] == id) {
		                    iterator.remove(); 
		                    break;
		                }
		            }

		            int no = 0;
		            DefaultTableModel model = (DefaultTableModel) tableListAdd.getModel();
		            model.setRowCount(0);

		            for (Object[] rowData : arrListAdd) {
		                model.addRow(new Object[] { ++no, rowData[0], rowData[1] });
		            }
		        }
		    }
		});

		btnDel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnDel.setBounds(610, 439, 85, 30);
		contentPane.add(btnDel);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Do you really want to cancel?", "Confirmation", JOptionPane.YES_NO_OPTION);
		        
		        if (result == JOptionPane.YES_OPTION) {
		            dispose();
		        }
			}
		});
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCancel.setBounds(610, 504, 85, 30);
		contentPane.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Object[] rowData : arrListAdd) {
	                StudentGradeBLL stdBll = new StudentGradeBLL();
					int studenid = (Integer) rowData[0];
	                stdBll.addStudent(studenid,courseid);
	            }
				java.util.Iterator<Object[]> iterator = arrListAdd.iterator();
	            while (iterator.hasNext()) {
	                Object[] rowData = iterator.next();
	                    iterator.remove(); 
	            }
	            JOptionPane.showMessageDialog(null, "Thêm thành công");
	            StudentGradeForm.loadDataMark(department1, course1);
	            dispose();
			}
		});
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSave.setBounds(496, 504, 85, 30);
		contentPane.add(btnSave);
		
		JButton btnRefesh = new JButton("Refesh");
		btnRefesh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.util.Iterator<Object[]> iterator = arrListAdd.iterator();
	            while (iterator.hasNext()) {
	                Object[] rowData = iterator.next();
	                    iterator.remove(); 
	            }

	            int no = 0;
	            DefaultTableModel model = (DefaultTableModel) tableListAdd.getModel();
	            model.setRowCount(0);

	            for (Object[] rowData : arrListAdd) {
	                model.addRow(new Object[] { ++no, rowData[0], rowData[1] });
	            }
			}
		});
		btnRefesh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnRefesh.setBounds(20, 504, 85, 30);
		contentPane.add(btnRefesh);
	}
	public static void setData(String depar,String course,int courseid1) {
		lblDepar.setText("Department : " + depar);
		lblCourse.setText("Course : " + course);
		department1 = depar;
		course1 = course;
		courseid = courseid1;
		int no = 0;
		CourseBLL courseBll = new CourseBLL();
		int courseId = courseBll.getCourseIDByTitleName(depar,course);
		
		StudentGradeBLL gradeBLL = new StudentGradeBLL();
		ArrayList<StudentGrade> arrGrade = gradeBLL.getStudentIdByCourseID(courseId);
		
		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = personBll.getAllPerson();
		
		ArrayList<Person> arrGV = personBll.getAllPersonById();
		
		Set<Integer> uniqueStudentIDs = new HashSet<>();

		for (StudentGrade studentGrade : arrGrade) {
		    uniqueStudentIDs.add(studentGrade.getStudentID());
		}
		for (Person person : arrGV) {
		    uniqueStudentIDs.add(person.getPersonId());
		}
		
		DefaultTableModel model = (DefaultTableModel) tableList.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Person person : arrPerson ) {
				if (!uniqueStudentIDs.contains(person.getPersonId()))
				{
					String fullname = person.getLastname() + " " + person.getFirstname();
					model.addRow(new Object[] { ++no,person.getPersonId(),fullname });
					
				}
			
		}
	}
}
