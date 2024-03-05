package UI;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.TextField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import BLL.Course;
import BLL.CourseBLL;
import BLL.InstructorBLL;
import BLL.OnlineCourse;
import BLL.OnlineCourseBLL;
import BLL.OnsiteCourse;
import BLL.OnsiteCourseBLL;
import BLL.StudentGradeBLL;

import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CourseForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JTable table_online;
	private static JTextField tf_find;
	private static JTable table_onsite;
	private static JComboBox comboBox = new JComboBox();
	private static boolean isClickOnline = true;
	private static boolean isAsc = true;
	private static boolean isFind = false;

	private static CourseBLL courseBLL = new CourseBLL();
	private static OnsiteCourseBLL onsiteCourseBLL = new OnsiteCourseBLL();
	private static OnlineCourseBLL onlineCourseBLL = new OnlineCourseBLL();
	private static InstructorBLL instructorBLL = new InstructorBLL();
	private static StudentGradeBLL studentGradeBLL = new StudentGradeBLL();

	// Load table OnlineCourse
	public static void loadTableOnline() {
		ArrayList<Course> arr = courseBLL.getAllCourse();
		ArrayList<OnlineCourse> arro = onlineCourseBLL.getAllOnlineCourse();
		int no = 0;

		DefaultTableModel model = (DefaultTableModel) table_online.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Course c : arr) {
			for (OnlineCourse o : arro) {
				if (c.getCourseID() == o.getCourseID()) {
					model.addRow(new Object[] { ++no, c.getCourseID(), c.getTitle(), c.getCredits(),
							c.getDepartmentID(), o.getUrl() });
				}
			}
		}
	}

	// Load table OnsiteCourse
	public static void loadTableOnsite() {
		ArrayList<Course> arr = courseBLL.getAllCourse();
		ArrayList<OnsiteCourse> arro = onsiteCourseBLL.getAllOnsiteCourse();
		int no = 0;

		DefaultTableModel model = (DefaultTableModel) table_onsite.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Course c : arr) {
			for (OnsiteCourse o : arro) {
				if (c.getCourseID() == o.getCourseID()) {
					model.addRow(new Object[] { ++no, c.getCourseID(), c.getTitle(), c.getCredits(),
							c.getDepartmentID(), o.getLocation(), o.getDays(), o.getTime() });
				}
			}
		}
	}

	// setup and load table online for sort and find
	public static void setupTableOnline(ArrayList<Course> arr, ArrayList<OnlineCourse> arro, int selectedIndex) {
		int no = 0;
		DefaultTableModel model = (DefaultTableModel) table_online.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		if (selectedIndex >= 0 && selectedIndex <= 3) {
			for (Course c : arr) {
				for (OnlineCourse o : arro) {
					if (o.getCourseID() == c.getCourseID()) {
						model.addRow(new Object[] { ++no, c.getCourseID(), c.getTitle(), c.getCredits(),
								c.getDepartmentID(), o.getUrl() });
					}
				}
			}
		} else if (selectedIndex == 4) {
			for (OnlineCourse o : arro) {
				for (Course c : arr) {
					if (o.getCourseID() == c.getCourseID()) {
						model.addRow(new Object[] { ++no, c.getCourseID(), c.getTitle(), c.getCredits(),
								c.getDepartmentID(), o.getUrl() });
					}

				}
			}
		}
	}

	// setup and load table onsite for sort and find
	public static void setupTableOnsite(ArrayList<Course> arr, ArrayList<OnsiteCourse> arro, int selectedIndex) {
		int no = 0;
		DefaultTableModel model = (DefaultTableModel) table_onsite.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		if (selectedIndex >= 0 && selectedIndex <= 3) {
			for (Course c : arr) {
				for (OnsiteCourse o : arro) {
					if (o.getCourseID() == c.getCourseID()) {
						model.addRow(new Object[] { ++no, c.getCourseID(), c.getTitle(), c.getCredits(),
								c.getDepartmentID(), o.getLocation(), o.getDays(), o.getTime() });
					}
				}
			}
		} else if (selectedIndex >= 4 && selectedIndex <= 6) {
			for (OnsiteCourse o : arro) {
				for (Course c : arr) {
					if (o.getCourseID() == c.getCourseID()) {
						model.addRow(new Object[] { ++no, c.getCourseID(), c.getTitle(), c.getCredits(),
								c.getDepartmentID(), o.getLocation(), o.getDays(), o.getTime() });
					}
				}
			}
		} else {
			return;
		}
	}

	// Find and sorted table online
	public static void loadSortedTableOnline(int selectedIndex, String selectedValue) {
		ArrayList<Course> arr = new ArrayList<Course>();
		ArrayList<OnlineCourse> arro = new ArrayList<OnlineCourse>();

		if (selectedIndex == -1) {
			arr = courseBLL.findCourseOnline(tf_find.getText().trim());
			arro = onlineCourseBLL.getAllOnlineCourse();
			setupTableOnline(arr, arro, 1);
			return;

		} else {
			String temp = tf_find.getText().trim();
			arr = courseBLL.findCombineSortOnline(selectedValue, temp, isAsc);
			arro = onlineCourseBLL.getAllOnlineCourse();
			setupTableOnline(arr, arro, 1);
			return;
		}
	}

	// Find and sorted table onsite
	public static void loadSortedTableOnsite(int selectedIndex, String selectedValue) {
		ArrayList<Course> arr = new ArrayList<Course>();
		ArrayList<OnsiteCourse> arro = new ArrayList<OnsiteCourse>();

		if (selectedIndex == -1) {
			if (isFind) {
				arr = courseBLL.findCourseOnsite(tf_find.getText().trim());
				arro = onsiteCourseBLL.getAllOnsiteCourse();
				setupTableOnsite(arr, arro, 1);
				return;
			}
		} else {
			String temp = tf_find.getText().trim();
			arr = courseBLL.findCombineSortOnsite(selectedValue, temp, isAsc);
			arro = onsiteCourseBLL.getAllOnsiteCourse();
			setupTableOnsite(arr, arro, 1);
			return;
		}
	}

	// Load Combobox
	public void loadCbb(String[] arr) {
		comboBox.removeAllItems();
		for (String item : arr) {
			comboBox.addItem(item);
		}
		comboBox.setSelectedIndex(-1);
	}

	// Load loadSortedTableBySelection()
	public void loadSortedTableBySelection() {
		int selectedIndex = comboBox.getSelectedIndex();
		String selectedValue = (String) comboBox.getSelectedItem();
		if (isClickOnline) {
			loadSortedTableOnline(selectedIndex, selectedValue);
		} else {
			loadSortedTableOnsite(selectedIndex, selectedValue);
		}
	}

	public static void clickEdit() {
		if (isClickOnline) {
			int row = table_online.getSelectedRow();
			if (row != -1) {
				int courseid = (int) table_online.getValueAt(row, 1);
				String title = table_online.getValueAt(row, 2).toString();
				int credits = (int) table_online.getValueAt(row, 3);
				int department = (int) table_online.getValueAt(row, 4);
				String url = table_online.getValueAt(row, 5).toString();

				AddOnlineCourseForm AddForm = new AddOnlineCourseForm();
				AddForm.setData(courseid, title, credits, department, url);
				AddForm.setVisible(true);

			}
		} else {
			int row = table_onsite.getSelectedRow();
			if (row != -1) {
				int courseid = (int) table_onsite.getValueAt(row, 1);
				String title = table_onsite.getValueAt(row, 2).toString();
				int credits = (int) table_onsite.getValueAt(row, 3);
				int department = (int) table_onsite.getValueAt(row, 4);
				String location = table_onsite.getValueAt(row, 5).toString();
				String days = table_onsite.getValueAt(row, 6).toString();
				String time = table_onsite.getValueAt(row, 7).toString();

				AddOnsiteCourseForm AddForm1 = new AddOnsiteCourseForm();
				AddForm1.setVisible(true);
				AddForm1.setData(courseid, title, credits, department, location, days, time);
				AddForm1.setLocationRelativeTo(null);
			}

		}
	}

	public static void clickDel() {
		if (isClickOnline) {
			int row = table_online.getSelectedRow();
			if (row != -1) {
				int courseid = (int) table_online.getValueAt(row, 1);
				int reponse = JOptionPane.showConfirmDialog(null,
						"Bạn có chắc muốn xóa Course có CourseID = " + courseid, "Yes", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (reponse == JOptionPane.YES_OPTION) {
					if (instructorBLL.checkCourseInstructor(courseid)) {
						JOptionPane.showMessageDialog(null, "Lỗi xóa. Khóa này đang được tiến hành giảng dạy");
					} else if (studentGradeBLL.checkStudent(courseid)) {
						JOptionPane.showMessageDialog(null, "Lỗi xóa. Khóa này đang được tiến hành giảng dạy");
					} else {
						if (onlineCourseBLL.delOnlineCourse(courseid)) {
							JOptionPane.showMessageDialog(null, courseBLL.delCourse(courseid));
							loadTableOnline();
						}
					}

				}
			}
		} else {
			int row = table_onsite.getSelectedRow();
			if (row != -1) {
				int courseid = (int) table_onsite.getValueAt(row, 1);
				int reponse = JOptionPane.showConfirmDialog(null,
						"Bạn có chắc muốn xóa Course có CourseID = " + courseid, "Yes", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (reponse == JOptionPane.YES_OPTION) {
					if (instructorBLL.checkCourseInstructor(courseid)) {
						JOptionPane.showMessageDialog(null, "Lỗi xóa. Khóa này đang được tiến hành giảng dạy");
					} else if (studentGradeBLL.checkStudent(courseid)) {
						JOptionPane.showMessageDialog(null, "Lỗi xóa. Khóa này đang được tiến hành giảng dạy");
					} else {
						if (onsiteCourseBLL.delOnsiteCourse(courseid)) {
							JOptionPane.showMessageDialog(null, courseBLL.delCourse(courseid));
							loadTableOnsite();
						}
					}

				}

			}
		}
	}

	public static void clickAdd() {
		Object[] options = { "Form Online", "Form Onsite" };
		int result = JOptionPane.showOptionDialog(null, "Chọn một lựa chọn:", "Thông báo", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		if (result == JOptionPane.YES_OPTION) {
			AddOnlineCourseForm AddForm = new AddOnlineCourseForm();
			AddForm.setVisible(true);
			AddForm.setLocationRelativeTo(null);
		} else if (result == JOptionPane.NO_OPTION) {
			AddOnsiteCourseForm AddForm1 = new AddOnsiteCourseForm();
			AddForm1.setVisible(true);
			AddForm1.setLocationRelativeTo(null);
		} else {
			JOptionPane.showMessageDialog(null, "Bạn đã đóng cửa sổ");
		}
	}

	public CourseForm() {
		setPreferredSize(new Dimension(835, 645));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 835, 35);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("COURSE");
		lblNewLabel.setBounds(382, 5, 70, 25);
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel.add(lblNewLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(23, 120, 790, 410);
		add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Online", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 785, 383);
		panel_1.add(scrollPane);

		table_online = new JTable();
		table_online.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_online.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "No", "CourseID", "Tilte", "Credits", "DepartmentID", "Url" }));
		table_online.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_online.getColumnModel().getColumn(1).setPreferredWidth(40);
		table_online.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_online.getColumnModel().getColumn(3).setPreferredWidth(40);
		table_online.getColumnModel().getColumn(4).setPreferredWidth(60);
		table_online.getColumnModel().getColumn(5).setPreferredWidth(200);
		scrollPane.setViewportView(table_online);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Onsite", null, panel_2, null);
		panel_2.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 785, 383);
		panel_2.add(scrollPane_1);

		table_onsite = new JTable();
		table_onsite.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "No", "CourseID", "Title", "Credits", "DepartmentID", "Location", "Days", "Time" }));
		table_onsite.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_onsite.getColumnModel().getColumn(1).setPreferredWidth(40);
		table_onsite.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_onsite.getColumnModel().getColumn(3).setPreferredWidth(40);
		table_onsite.getColumnModel().getColumn(4).setPreferredWidth(60);
		table_onsite.getColumnModel().getColumn(5).setPreferredWidth(100);
		table_onsite.getColumnModel().getColumn(6).setPreferredWidth(100);
		table_onsite.getColumnModel().getColumn(7).setPreferredWidth(100);
		scrollPane_1.setViewportView(table_onsite);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickAdd();
			}
		});
		btnAdd.setBackground(UIManager.getColor("Button.background"));
		btnAdd.setForeground(SystemColor.controlDkShadow);
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnAdd.setBounds(430, 570, 100, 30);
		add(btnAdd);

		JButton btn_find = new JButton("Find");
		btn_find.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isFind = true;
				if (isClickOnline) {
					loadSortedTableOnline(comboBox.getSelectedIndex(), (String) comboBox.getSelectedItem());
				} else {
					loadSortedTableOnsite(comboBox.getSelectedIndex(), (String) comboBox.getSelectedItem());
				}
				comboBox.setSelectedIndex(-1);
			}
		});
		btn_find.setForeground(SystemColor.textHighlightText);
		btn_find.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btn_find.setBackground(SystemColor.activeCaption);
		btn_find.setBounds(731, 60, 80, 30);
		add(btn_find);

		tf_find = new JTextField();
		tf_find.setBounds(468, 60, 250, 30);
		add(tf_find);
		tf_find.setColumns(10);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickEdit();
			}
		});
		btnEdit.setForeground(SystemColor.controlDkShadow);
		btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnEdit.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnEdit.setBounds(550, 570, 100, 30);
		add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickDel();
			}
		});
		btnDelete.setForeground(SystemColor.controlDkShadow);
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnDelete.setBackground(UIManager.getColor("CheckBox.darkShadow"));
		btnDelete.setBounds(670, 570, 100, 30);
		add(btnDelete);

		JSeparator separator = new JSeparator();
		separator.setBounds(23, 540, 788, 2);
		add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(23, 110, 788, 2);
		add(separator_1);

		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		String[] data = { "CourseID", "Title", "Credits", "DepartmentID", "Url" };
		loadCbb(data);
		comboBox.setBounds(23, 60, 120, 30);
		add(comboBox);

		JButton btnRefesh = new JButton("Refesh");

		btnRefesh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboBox.setSelectedIndex(-1);
				loadTableOnline();
				loadTableOnsite();
				tf_find.setText("");
			}
		});
		btnRefesh.setForeground(SystemColor.controlDkShadow);
		btnRefesh.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnRefesh.setBackground(UIManager.getColor("Button.background"));
		btnRefesh.setBounds(310, 570, 100, 30);
		add(btnRefesh);

		JButton btnAsc = new JButton("Asc");
		btnAsc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				isAsc = true;
				loadSortedTableBySelection();

			}
		});
		btnAsc.setForeground(SystemColor.controlDkShadow);
		btnAsc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAsc.setBackground(UIManager.getColor("Button.background"));
		btnAsc.setBounds(152, 60, 70, 30);
		add(btnAsc);

		JButton btnDesc = new JButton("Desc");
		btnDesc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				isAsc = false;
				loadSortedTableBySelection();
			}
		});
		btnDesc.setForeground(SystemColor.controlDkShadow);
		btnDesc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnDesc.setBackground(UIManager.getColor("Button.background"));
		btnDesc.setBounds(230, 60, 70, 30);
		add(btnDesc);

		// Event
		// Event tabbedPane
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (tabbedPane.getSelectedIndex() == 0) {
					isClickOnline = true;
					// load data Combobox
					String[] data = { "CourseID", "Title", "Credits", "DepartmentID", "Url" };
					loadCbb(data);
					loadTableOnline();
				} else if (tabbedPane.getSelectedIndex() == 1) {
					isClickOnline = false;
					// load data Combobox
					String[] data = { "CourseID", "Title", "Credits", "DepartmentID", "Location", "Days", "Time" };
					loadCbb(data);
					loadTableOnsite();
				}
			}
		});

		// Load table
		loadTableOnline();
		loadTableOnsite();
	}

	public boolean isNumeric(String str) {
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
