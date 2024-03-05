package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Course;
import BLL.CourseBLL;
import BLL.Department;
import BLL.DepartmentBLL;
import BLL.InstructorBLL;
import BLL.OnlineCourse;
import BLL.OnlineCourseBLL;

import java.awt.SystemColor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddOnlineCourseForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField tf_title;
	private static JTextField tf_credits;
	private static JTextField tf_url;
	private static JComboBox cbb_departmentID = new JComboBox();
	private static JLabel lbl_departmentName = new JLabel("");
	private static CourseBLL courseBLL = new CourseBLL();
	private static OnlineCourseBLL onlineCourseBLL = new OnlineCourseBLL();
	static DepartmentBLL departmentBLL = new DepartmentBLL();
	static InstructorBLL instructorBLL = new InstructorBLL();
	static ArrayList<Department> arr = departmentBLL.getAllDepartment();
	public static int courseID;
	public static boolean isCloseWindow = false;
	public static boolean update = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddOnlineCourseForm frame = new AddOnlineCourseForm();

					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static boolean checkSelectCbb() {
		if (cbb_departmentID.getSelectedIndex() == -1) {
			return true;
		}
		return false;
	}

	public static void loadDataCbb() {
		cbb_departmentID.removeAllItems();
		try {
			for (Department d : arr) {
				cbb_departmentID.addItem(d.getDepartmentID());
			}
			cbb_departmentID.setMaximumRowCount(20);
			cbb_departmentID.setFont(new Font("Segoe UI", Font.BOLD, 13));

			cbb_departmentID.setSelectedIndex(-1);
		} catch (Exception e) {
			// TODO: handle exception
		}

		cbb_departmentID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickCbb();
			}
		});

	}

	public static void clickCbb() {
		Integer selectedOption = (Integer) cbb_departmentID.getSelectedItem();
		if (selectedOption != null) {
			for (Department d : arr) {
				if (selectedOption == d.getDepartmentID()) {
					lbl_departmentName.setText(d.getName());
				}
			}
		}

	}

	public static boolean checkInfo() {
		return (tf_title.getText().trim().equals("") || tf_credits.getText().trim().equals("")
				|| tf_url.getText().trim().equals("") || checkSelectCbb());
	}

	public static void saveOnlineCourse() {
		if (!update) {
			if (checkInfo()) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
			} else {
				Course c = new Course();
				OnlineCourse o = new OnlineCourse();
				// Title
				c.setTitle(tf_title.getText());
				// Credits
				String tempCredit = tf_credits.getText().trim();
				if (isNumeric(tempCredit)) {
					c.setCredits(Integer.parseInt(tempCredit));
				} else {
					JOptionPane.showMessageDialog(null, "Lỗi định dạng Credits (int)");
					return;
				}
				// Department
				String selectedItem = cbb_departmentID.getSelectedItem().toString();
				if (isNumeric(selectedItem)) {
					c.setDepartmentID(Integer.parseInt(selectedItem));
				} else {
					JOptionPane.showMessageDialog(null, "Lỗi định dạng DepartmentID (int)");
					return;
				}
				if (courseBLL.addCourse(c)) {
					o.setCourseID(courseBLL.newCourseID());
					o.setUrl(tf_url.getText());
					JOptionPane.showMessageDialog(null, onlineCourseBLL.addOnlineCourse(o));
					isCloseWindow = true;
				} else {
					JOptionPane.showMessageDialog(null, "Lỗi");
				}
			}
		} else {
			if (checkInfo()) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
			} else {
				String title = tf_title.getText();

				int credits;
				String tempCredit = tf_credits.getText().trim();
				if (isNumeric(tempCredit)) {
					credits = Integer.parseInt(tempCredit);
				} else {
					JOptionPane.showMessageDialog(null, "Lỗi định dạng Credits (int)");
					return;
				}
				int department = (int) cbb_departmentID.getSelectedItem();
				if (courseBLL.editCourse(courseID, title, credits, department)) {
					String url = tf_url.getText();
					JOptionPane.showMessageDialog(null, onlineCourseBLL.editOnlineCourse(courseID, url));
					isCloseWindow = true;
				} else {
					JOptionPane.showMessageDialog(null, "Lỗi");
				}
				update = false;
			}
		}

	}

	public void setData(int courseID, String title, int credits, int departmentID, String url) {
		update = true;
		this.courseID = courseID;
		tf_title.setText(title);
		tf_credits.setText(credits + "");
		tf_url.setText(url);
		for (int i = 0; i < cbb_departmentID.getItemCount(); i++) {
			if ((int) cbb_departmentID.getItemAt(i) == departmentID) {
				cbb_departmentID.setSelectedItem(departmentID);
				break;
			}
		}
	}

	public AddOnlineCourseForm() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 630, 35);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("ONLINE COURSE");
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1_1_1 = new JLabel("Title");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(45, 92, 90, 30);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Credits");
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(45, 152, 90, 30);
		contentPane.add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_1_3 = new JLabel("DepartmentID");
		lblNewLabel_1_1_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_3.setBounds(45, 212, 110, 30);
		contentPane.add(lblNewLabel_1_1_3);

		JLabel lblNewLabel_1_1_4 = new JLabel("DepartmentName");
		lblNewLabel_1_1_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_4.setBounds(45, 272, 130, 30);
		contentPane.add(lblNewLabel_1_1_4);

		JLabel lblNewLabel_1_1_5 = new JLabel("Url");
		lblNewLabel_1_1_5.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_5.setBounds(45, 332, 90, 30);
		contentPane.add(lblNewLabel_1_1_5);
		lbl_departmentName.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		lbl_departmentName.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_departmentName.setBounds(175, 272, 180, 30);
		contentPane.add(lbl_departmentName);

		tf_title = new JTextField();
		tf_title.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tf_title.setColumns(10);
		tf_title.setBounds(175, 92, 400, 30);
		contentPane.add(tf_title);

		tf_credits = new JTextField();
		tf_credits.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tf_credits.setColumns(10);
		tf_credits.setBounds(175, 152, 400, 30);
		contentPane.add(tf_credits);

		tf_url = new JTextField();
		tf_url.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tf_url.setColumns(10);
		tf_url.setBounds(175, 332, 400, 30);
		contentPane.add(tf_url);

		cbb_departmentID.setBounds(175, 212, 180, 30);
		contentPane.add(cbb_departmentID);

		JButton btn_save = new JButton("Save");
		btn_save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				isCloseWindow = false;
				saveOnlineCourse();
				CourseForm.loadTableOnline();
				if (isCloseWindow) {
					dispose();
				}
			}
		});
		btn_save.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btn_save.setBounds(129, 421, 120, 40);
		contentPane.add(btn_save);

		JButton btn_cancel = new JButton("Cancel");
		btn_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();

			}
		});
		btn_cancel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btn_cancel.setBounds(352, 421, 120, 40);
		contentPane.add(btn_cancel);

		loadDataCbb();
	}

	public static boolean isNumeric(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	public static boolean isValidTime(String input) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setLenient(false);

		try {
			sdf.parse(input);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
