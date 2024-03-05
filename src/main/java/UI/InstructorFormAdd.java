package UI;

import java.awt.EventQueue;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Course;
import BLL.CourseBLL;
import BLL.CourseInstructor;
import BLL.InstructorBLL;
import BLL.OfficeAssignment;
import BLL.OfficeAssignmentBLL;
import BLL.Person;
import BLL.PersonBLL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class InstructorFormAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JComboBox cbbCourse;
	private static JLabel lblCourse;
	private static JLabel lblInstructor;
	private static JComboBox cbbPerson;
	private static boolean ischeck =true;
	private static ArrayList<String> arrDataSup = new ArrayList<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InstructorFormAdd frame = new InstructorFormAdd();
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
	public InstructorFormAdd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 487, 470);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 473, 35);
		panel_1.setBackground(SystemColor.activeCaption);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("CourseInstructor Add");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel_1.add(lblNewLabel);
		
		cbbPerson = new JComboBox();	
		
		OfficeAssignmentBLL offBll =new OfficeAssignmentBLL();
		ArrayList<OfficeAssignment> arrOff = offBll.getAllOffice();
		
		PersonBLL perBll =new PersonBLL();
		ArrayList<Person> arrPer = perBll.getAllPerson();
		ArrayList<String> arrData = new ArrayList<String>();
			for(OfficeAssignment off : arrOff) {	
				for(Person ps : arrPer) {
					if(off.getInstructorID() == ps.getPersonId()) {						
						String fullname = off.getInstructorID() + " - " + ps.getLastname() + " " + ps.getFirstname();
						arrData.add(fullname);
						arrDataSup.add(fullname);
					}
				}
			}
		DefaultComboBoxModel<String> departmentModel1 = new DefaultComboBoxModel<>(arrData.toArray(new String[0]));
		cbbPerson.setModel(departmentModel1);
		cbbPerson.setSelectedIndex(-1);
		cbbPerson.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String valueString = (String) cbbPerson.getSelectedItem();
		    	String[] value = null;
		    	if (valueString != null) {
		    	    value = valueString.split(" - ");
		    	    lblInstructor.setText("Instructor : " + value[1]);
		    	}
		    }
		});
		cbbPerson.setBounds(23, 172, 183, 30);
		contentPane.add(cbbPerson);
		
		lblInstructor = new JLabel("Instructor : ");
		lblInstructor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblInstructor.setBounds(23, 45, 334, 30);
		contentPane.add(lblInstructor);
		
		lblCourse = new JLabel("Course");
		lblCourse.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCourse.setBounds(23, 80, 315, 30);
		contentPane.add(lblCourse);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Person");
		lblNewLabel_1_1_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(23, 132, 93, 30);
		contentPane.add(lblNewLabel_1_1_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(23, 120, 424, 13);
		contentPane.add(separator);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Course");
		lblNewLabel_1_1_2.setForeground(Color.RED);
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1_2.setBounds(264, 132, 93, 30);
		contentPane.add(lblNewLabel_1_1_2);
		
		cbbCourse = new JComboBox();
    	CourseBLL couBll = new CourseBLL();
    	ArrayList<Integer> arrIn = couBll.getCbbCourse();
    	Set<Integer> uniqueStudentIDs = new HashSet<>();
    	
    	for (int id : arrIn) {
		    uniqueStudentIDs.add(id);
		}	
		ArrayList<Course> arrCou = couBll.getAllCourse();
		ArrayList<String> arrData1 = new ArrayList<String>();
		for (Course cou : arrCou ) {
			if (!uniqueStudentIDs.contains(cou.getCourseID()))
			{
				String fullname = cou.getCourseID() + " - " + cou.getTitle(); 
				arrData1.add(fullname);
			}
		
		}
		DefaultComboBoxModel<String> departmentModel = new DefaultComboBoxModel<>(arrData1.toArray(new String[0]));
		cbbCourse.setModel(departmentModel);
		cbbCourse.setSelectedIndex(-1);
		cbbCourse.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	String valueString = (String) cbbCourse.getSelectedItem();
		    	String[] value = null;
		    	if (valueString != null) {
		    	    value = valueString.split(" - ");
		    	    lblCourse.setText("Course : " + value[1]);
		    	}
		    }
		});
		cbbCourse.setBounds(264, 172, 183, 30);
		contentPane.add(cbbCourse);
		
		JButton btnRefesh = new JButton("Refesh");
		btnRefesh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblInstructor.setText("Instructor : ");
				lblCourse.setText("Course : ");
				cbbPerson.setSelectedIndex(-1);
				cbbCourse.setSelectedIndex(-1);
			}
		});
		btnRefesh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnRefesh.setBounds(23, 392, 85, 30);
		contentPane.add(btnRefesh);
		
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
		btnCancel.setBounds(362, 392, 85, 30);
		contentPane.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valueStringPer = (String) cbbPerson.getSelectedItem();
				String valueStringCou = (String) cbbCourse.getSelectedItem();
				
				String[] valuePer = valueStringPer.split(" - ");
				String[] valueCou = valueStringCou.split(" - ");
				
				InstructorBLL insBLL = new InstructorBLL();
				CourseInstructor ins = new CourseInstructor();
				ins.setPersonID(Integer.parseInt(valuePer[0]));
				ins.setCourseID(Integer.parseInt(valueCou[0]));
				if(ischeck == true) {					
					String rs = insBLL.addInstructor(ins);
					if(rs.equals("Thêm thành công")) {
						JOptionPane.showMessageDialog(null, rs);
						ischeck = true;
						dispose();
						InstructorForm.displayData();
					}
				}else {
					String rs = insBLL.editInstructor(ins);
					if(rs.equals("Cập nhật thành công")) {
						JOptionPane.showMessageDialog(null, rs);
						ischeck = true;
						dispose();
						InstructorForm.displayData();
					}
				}
			}
		});
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSave.setBounds(264, 392, 85, 30);
		contentPane.add(btnSave);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(23, 369, 424, 13);
		contentPane.add(separator_1);
	}
	public static void setData(int idPer,int idCou,String nameIns,String nameCou,boolean ischeck1) {
		ischeck = ischeck1;
		lblInstructor.setText("Instructor : " + nameIns);
		lblCourse.setText("Course : " + nameCou);
		
		String fullPer = idPer + " - " + nameIns;
		String fullCou = idCou + " - " + nameCou;
		
		cbbCourse.removeAllItems();
		cbbCourse.addItem(fullCou);
		
		int index = -1;
		for(int i = 0; i < arrDataSup.size();i++) {
			if(arrDataSup.get(i).equals(fullPer)) {
				index = i;
				break;
			}
		}
		cbbPerson.setSelectedIndex(index);
		
	}
}
