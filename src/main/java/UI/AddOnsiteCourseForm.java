package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

import BLL.Course;
import BLL.CourseBLL;
import BLL.Department;
import BLL.DepartmentBLL;
import BLL.OnsiteCourse;
import BLL.OnsiteCourseBLL;

import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddOnsiteCourseForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField tf_credits;
	private static JTextField tf_title;
	private static JTextField tf_location;
	private static JTextField tf_time;
	private static JComboBox cbb_departmentID = new JComboBox();
	private static JLabel lbl_departmentName = new JLabel("");
	private static CourseBLL courseBLL = new CourseBLL();
	private static OnsiteCourseBLL onsiteCourseBLL = new OnsiteCourseBLL();
	static JCheckBox chb_m = new JCheckBox("M");
	static JCheckBox chb_t = new JCheckBox("T");
	static JCheckBox chb_w = new JCheckBox("W");
	static JCheckBox chb_h = new JCheckBox("H");
	static JCheckBox chb_f = new JCheckBox("F");
	static DepartmentBLL departmentBLL = new DepartmentBLL();
	static ArrayList<Department> arr = departmentBLL.getAllDepartment();
	public static boolean update = false;
	public static int courseID;
	public static boolean isCloseWindow = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddOnsiteCourseForm frame = new AddOnsiteCourseForm();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void selectCheckBoxes(String days) {
		for (int i = 0; i < days.length(); i++) {
			char ch = days.charAt(i);
			switch (ch) {
			case 'M':
				chb_m.setSelected(true);
				break;
			case 'T':
				chb_t.setSelected(true);
				break;
			case 'W':
				chb_w.setSelected(true);
				break;
			case 'H':
				chb_h.setSelected(true);
				break;
			case 'F':
				chb_f.setSelected(true);
				break;
			}
		}
	}

	public static String getSelectedCheckBoxesText(JCheckBox... checkBoxes) {
		StringBuilder result = new StringBuilder();
		for (JCheckBox checkBox : checkBoxes) {
			if (checkBox.isSelected()) {
				result.append(checkBox.getText());
			}
		}
		return result.toString();
	}

	public void setData(int courseID, String title, int credits, int departmentID, String location, String days,
			String time) {
		clearCheckBoxes(chb_m, chb_t, chb_w, chb_h, chb_f);
		update = true;
		this.courseID = courseID;
		tf_title.setText(title);
		tf_credits.setText(credits + "");
		tf_location.setText(location);
		for (int i = 0; i < cbb_departmentID.getItemCount(); i++) {
			if ((int) cbb_departmentID.getItemAt(i) == departmentID) {
				cbb_departmentID.setSelectedItem(departmentID);
				break;
			}
		}
		selectCheckBoxes(days);
		tf_time.setText(time);
	}

	public static void loadDataCbb() {
		cbb_departmentID.removeAllItems();

		for (Department d : arr) {
			cbb_departmentID.addItem(d.getDepartmentID());
		}
		cbb_departmentID.setMaximumRowCount(20);
		cbb_departmentID.setFont(new Font("Segoe UI", Font.BOLD, 13));
		cbb_departmentID.setSelectedIndex(-1);
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

	public static boolean areAnySelected(JCheckBox... checkboxes) {
		for (JCheckBox checkbox : checkboxes) {
			if (checkbox.isSelected()) {
				return true;
			}
		}
		return false;
	}

	public static void clearCheckBoxes(JCheckBox... checkBoxes) {
		for (JCheckBox checkBox : checkBoxes) {
			checkBox.setSelected(false);
		}
	}

	public static boolean checkSelectCbb() {
		if (cbb_departmentID.getSelectedIndex() == -1) {
			return true;
		}
		return false;
	}

	public static boolean checkInfo() {
		return (tf_title.getText().trim().equals("") || tf_credits.getText().trim().equals("")
				|| tf_location.getText().trim().equals("") || checkSelectCbb()
				|| !areAnySelected(chb_m, chb_t, chb_w, chb_h, chb_f) || tf_time.getText().trim().equals(""));
	}

	public static void saveOnsiteCourse() {
		if (!update) {
			if (checkInfo()) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
			} else {
				Course c = new Course();
				OnsiteCourse o = new OnsiteCourse();
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
				// Add course
				if (courseBLL.addCourse(c)) {
					o.setCourseID(courseBLL.newCourseID());
					// Location
					o.setLocation(tf_location.getText());
					// Time
					if (isValidTime(tf_time.getText())) {
						o.setTime(Time.valueOf(tf_time.getText()));
					} else {
						JOptionPane.showMessageDialog(null, "Lỗi định dạng Time (HH:mm:ss)");
						return;
					}
					// days
					o.setDays(getSelectedCheckBoxesText(chb_m, chb_t, chb_w, chb_h, chb_f));
					// create
					JOptionPane.showMessageDialog(null, onsiteCourseBLL.addOnsiteeCourse(o));
					isCloseWindow = true;
				} else {
					JOptionPane.showMessageDialog(null, "Lỗi");
				}

			}
		} else {
			if (checkInfo()) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
			} else {
				// title
				String title = tf_title.getText();
				// credits
				int credits;
				String tempCredit = tf_credits.getText().trim();
				if (isNumeric(tempCredit)) {
					credits = Integer.parseInt(tempCredit);
				} else {
					JOptionPane.showMessageDialog(null, "Lỗi định dạng Credits (int)");
					return;
				}
				// department
				int department = (int) cbb_departmentID.getSelectedItem();
				// create coures
				if (courseBLL.editCourse(courseID, title, credits, department)) {
					// Location
					String location = tf_location.getText();
					// Time
					Time time;
					if (isValidTime(tf_time.getText())) {
						time = Time.valueOf(tf_time.getText());
					} else {
						JOptionPane.showMessageDialog(null, "Lỗi định dạng Time (HH:mm:ss)");
						return;
					}
					// Time
					String days;
					if (areAnySelected(chb_m, chb_t, chb_w, chb_h, chb_f)) {
						days = getSelectedCheckBoxesText(chb_m, chb_t, chb_w, chb_h, chb_f);
					} else {
						JOptionPane.showMessageDialog(null, "Lỗi không chọn checkbox");
						return;
					}
					JOptionPane.showMessageDialog(null,
							onsiteCourseBLL.editOnsiteCourse(courseID, location, days, time));
					isCloseWindow = true;
				} else {
					JOptionPane.showMessageDialog(null, "Lỗi");
				}
				update = false;
			}
		}
	}

	public AddOnsiteCourseForm() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 639, 663);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 630, 35);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblOnsiteCourse = new JLabel("ONSITE COURSE");
		lblOnsiteCourse.setForeground(SystemColor.textHighlightText);
		lblOnsiteCourse.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel.add(lblOnsiteCourse);

		JLabel lblNewLabel_1_1_1 = new JLabel("Title");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(47, 81, 90, 30);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Credits");
		lblNewLabel_1_1_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(47, 141, 90, 30);
		contentPane.add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_1_3 = new JLabel("DepartmentID");
		lblNewLabel_1_1_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_3.setBounds(47, 201, 110, 30);
		contentPane.add(lblNewLabel_1_1_3);

		JLabel lblNewLabel_1_1_4 = new JLabel("DepartmentName");
		lblNewLabel_1_1_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_4.setBounds(47, 261, 130, 30);
		contentPane.add(lblNewLabel_1_1_4);
		lbl_departmentName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lbl_departmentName.setBounds(177, 261, 180, 30);
		contentPane.add(lbl_departmentName);

		cbb_departmentID.setBounds(177, 201, 180, 30);
		contentPane.add(cbb_departmentID);

		tf_credits = new JTextField();
		tf_credits.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tf_credits.setColumns(10);
		tf_credits.setBounds(177, 141, 400, 30);
		contentPane.add(tf_credits);

		tf_title = new JTextField();
		tf_title.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tf_title.setColumns(10);
		tf_title.setBounds(177, 81, 400, 30);
		contentPane.add(tf_title);

		JLabel lblNewLabel_1_1_4_1 = new JLabel("Location");
		lblNewLabel_1_1_4_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_4_1.setBounds(47, 318, 130, 30);
		contentPane.add(lblNewLabel_1_1_4_1);

		JLabel lblNewLabel_1_1_4_2 = new JLabel("Days");
		lblNewLabel_1_1_4_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_4_2.setBounds(47, 371, 130, 30);
		contentPane.add(lblNewLabel_1_1_4_2);

		JLabel lblNewLabel_1_1_4_3 = new JLabel("Time");
		lblNewLabel_1_1_4_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1_1_4_3.setBounds(47, 426, 130, 30);
		contentPane.add(lblNewLabel_1_1_4_3);

		tf_location = new JTextField();
		tf_location.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tf_location.setColumns(10);
		tf_location.setBounds(177, 318, 400, 30);
		contentPane.add(tf_location);

		tf_time = new JTextField();
		tf_time.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tf_time.setColumns(10);
		tf_time.setBounds(177, 426, 400, 30);
		contentPane.add(tf_time);

		JButton btn_save = new JButton("Save");
		btn_save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				isCloseWindow = false;
				saveOnsiteCourse();
				CourseForm.loadTableOnsite();
				if (isCloseWindow) {
					dispose();
				}
			}
		});
		btn_save.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btn_save.setBounds(138, 506, 120, 40);
		contentPane.add(btn_save);

		JButton btn_cancel = new JButton("Cancel");
		btn_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btn_cancel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btn_cancel.setBounds(361, 506, 120, 40);
		contentPane.add(btn_cancel);

		chb_m.setFont(new Font("Segoe UI", Font.BOLD, 14));
		chb_m.setBounds(177, 371, 60, 30);
		contentPane.add(chb_m);

		chb_t.setFont(new Font("Segoe UI", Font.BOLD, 14));
		chb_t.setBounds(239, 371, 60, 30);
		contentPane.add(chb_t);

		chb_w.setFont(new Font("Segoe UI", Font.BOLD, 14));
		chb_w.setBounds(301, 371, 60, 30);
		contentPane.add(chb_w);

		chb_h.setFont(new Font("Segoe UI", Font.BOLD, 14));
		chb_h.setBounds(361, 371, 60, 30);
		contentPane.add(chb_h);

		chb_f.setFont(new Font("Segoe UI", Font.BOLD, 14));
		chb_f.setBounds(421, 371, 60, 30);
		contentPane.add(chb_f);

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
