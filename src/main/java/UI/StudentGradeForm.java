package UI;

import java.awt.Dimension;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import BLL.Course;
import BLL.CourseBLL;
import BLL.Department;
import BLL.DepartmentBLL;
import BLL.Person;
import BLL.PersonBLL;
import BLL.StudentGrade;
import BLL.StudentGradeBLL;
import DAL.CourseDAL;
import DAL.DepartmentDAL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class StudentGradeForm extends JPanel {

	private static final long serialVersionUID = 1L;
	public static JComboBox cbbCourse;
	public static JComboBox cbbDepartment;
	public static JTable table_onsite;
	public static JTable table_online;
	private static JPanel panel1;
	private static JPanel panel2;
	private static JLabel lblDepartment;
	private static JLabel lblCourse;
	private static JLabel lblClassSize;
	private static JTable tableGrade;
	private static JTabbedPane tabbedPane;
	private static boolean isHandleTabTable;
	private JTextField textField;
	private JTextField textFieldFind;
	private JComboBox cbbArrangeGrade;
	private JComboBox cbbIdLastFirstName;
	private static int isCbb = -1;
	private static int isCbbTt = -1;

	public StudentGradeForm() {
		setPreferredSize(new Dimension(1670, 645));
		setLayout(null);

		panel2 = new JPanel();
		panel2.setForeground(new Color(255, 255, 255));
		panel2.setBounds(552, 0, 835, 645);
		panel2.setVisible(false);
		add(panel2);
		panel2.setLayout(null);

		lblDepartment = new JLabel();
		lblDepartment.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblDepartment.setBounds(20, 45, 364, 23);
		panel2.add(lblDepartment);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(SystemColor.activeCaption);
		panel_3.setBounds(0, 0, 835, 35);
		panel2.add(panel_3);

		JLabel lblNewLabel_3 = new JLabel("StudenGrade");
		lblNewLabel_3.setForeground(SystemColor.textHighlightText);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_3.setBounds(341, 5, 135, 25);
		panel_3.add(lblNewLabel_3);

		lblCourse = new JLabel();

		lblCourse.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblCourse.setBounds(20, 78, 364, 23);
		panel2.add(lblCourse);

		lblClassSize = new JLabel("Class size : 45");
		lblClassSize.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblClassSize.setBounds(20, 111, 107, 23);
		panel2.add(lblClassSize);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(20, 175, 787, 365);
		panel2.add(scrollPane_2);

		tableGrade = new JTable();
		tableGrade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tableGrade.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "No", "Id", "FullName", "Grade" }));
		tableGrade.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableGrade.getColumnModel().getColumn(1).setPreferredWidth(20);
		tableGrade.getColumnModel().getColumn(2).setPreferredWidth(200);
		tableGrade.getColumnModel().getColumn(3).setPreferredWidth(40);

		scrollPane_2.setViewportView(tableGrade);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TableModel model = tableGrade.getModel();
				int rowCount = model.getRowCount();
				int columnCount = model.getColumnCount();
				Boolean isCheck = true;

				for (int row = 0; row < rowCount; row++) {
					Object value = model.getValueAt(row, 3);
					BigDecimal decimalValue = null;

					if (value != null) {
						if (value instanceof BigDecimal) {
							decimalValue = (BigDecimal) value;
						} else if (value instanceof String) {
							// Thử chuyển đổi String thành BigDecimal
							try {
								decimalValue = new BigDecimal((String) value);
							} catch (NumberFormatException ex) {
								// Xử lý trường hợp không thể chuyển đổi (ví dụ: đầu vào không phải là số hợp
								// lệ)
								isCheck = false;
								break;
							}
						}

						// Kiểm tra xem giá trị BigDecimal có nằm trong khoảng chỉ định không
						if (decimalValue != null && decimalValue.compareTo(BigDecimal.ZERO) >= 0
								&& decimalValue.compareTo(new BigDecimal("4.00")) <= 0) {
							isCheck = true;
						} else {
							isCheck = false;
							break;
						}
					}
				}

				if (isCheck == false) {
					JOptionPane.showMessageDialog(null, "Grade chỉ trong khoảng (0.00 - 4.00 )\nVui lòng nhập lại");
				} else {
					String courseStringMarks = lblCourse.getText().toString();
					String departmentStringMarks = lblDepartment.getText().toString();
					String[] courseStringMark = courseStringMarks.split(" : ");
					String[] departmentStringMark = departmentStringMarks.split(" : ");

					CourseBLL courseBll = new CourseBLL();
					int courseId = courseBll.getCourseIDByTitleName(departmentStringMark[1], courseStringMark[1]);

					StudentGradeBLL gradeBLL = new StudentGradeBLL();
					ArrayList<StudentGrade> arrGrade = gradeBLL.getStudentIdByCourseID(courseId);

					String resultString = "Lưu thành công";
					for (int row = 0; row < rowCount; row++) {
						Object value = model.getValueAt(row, 3);
						BigDecimal grade = null;
						if (value != null) {
							if (value instanceof BigDecimal) {
								grade = (BigDecimal) value;
							} else if (value instanceof String) {
								// Nếu giá trị là kiểu String, chuyển đổi sang BigDecimal
								grade = new BigDecimal((String) value);
							}
							int studentId = (int) model.getValueAt(row, 1);
							resultString = gradeBLL.UpdateGrade(grade, studentId, courseId);
						}
					}
					if (resultString.equals("Lưu thành công")) {
						JOptionPane.showMessageDialog(null, resultString);
						panel1.setVisible(true);
						panel2.setVisible(false);
					}
				}

			}
		});
		btnSave.setForeground(SystemColor.controlDkShadow);
		btnSave.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnSave.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnSave.setBounds(569, 583, 100, 30);
		panel2.add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Do you really want to cancel?", "Confirmation",
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					panel1.setVisible(true);
					panel2.setVisible(false);
				}
			}
		});

		btnCancel.setForeground(SystemColor.controlDkShadow);
		btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnCancel.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnCancel.setBounds(700, 583, 100, 30);
		panel2.add(btnCancel);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 152, 786, 9);
		panel2.add(separator_2);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dataDepar = lblDepartment.getText();
				String[] depar = dataDepar.split(" : ");
				String dataCourse = lblCourse.getText();
				String[] course = dataCourse.split(" : ");

				CourseBLL courseBll = new CourseBLL();
				int courseId = courseBll.getCourseIDByTitleName(depar[1], course[1]);

				StudentGradeFormAdd stdAdd = new StudentGradeFormAdd();
				stdAdd.setData(depar[1], course[1], courseId);
				stdAdd.setVisible(true);
				stdAdd.setLocationRelativeTo(null);
			}
		});
		btnAdd.setForeground(SystemColor.controlDkShadow);
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnAdd.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnAdd.setBounds(20, 583, 100, 30);
		panel2.add(btnAdd);

		JButton btnDel = new JButton("Del");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableGrade.getSelectedRow();
				if (selectedRow != -1) {		
					int id = (Integer) tableGrade.getValueAt(selectedRow, 1);
					int reponse = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa Student có ID = " + id, "Yes",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (reponse == JOptionPane.YES_OPTION) {
						String courseStringMarks = lblCourse.getText().toString();
						String departmentStringMarks = lblDepartment.getText().toString();
						String[] courseStringMark = courseStringMarks.split(" : ");
						String[] departmentStringMark = departmentStringMarks.split(" : ");
						CourseBLL courseBll = new CourseBLL();
						int courseId = courseBll.getCourseIDByTitleName(departmentStringMark[1], courseStringMark[1]);
						StudentGradeBLL stdBll = new StudentGradeBLL();
						JOptionPane.showMessageDialog(null, stdBll.delStudent(id, courseId));
						handleMark();
					}
				}
			}
		});
		btnDel.setForeground(SystemColor.controlDkShadow);
		btnDel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnDel.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnDel.setBounds(146, 583, 100, 30);
		panel2.add(btnDel);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(20, 564, 786, 9);
		panel2.add(separator_1_1);

		textFieldFind = new JTextField();
		textFieldFind.setBounds(499, 107, 198, 30);
		panel2.add(textFieldFind);
		textFieldFind.setColumns(10);

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fullText = textFieldFind.getText();
				String[] text = fullText.split(" ");

				if (fullText.matches(".*\\d.*")) {
					double grade = Double.parseDouble(fullText);
					getFindByGrade(grade);
				} else {
					if (text.length == 1) {
						findStudentByLastFirstName(fullText, fullText);
					} else if (text.length == 2) {
						findStudentByLastFirstName(text[0], text[1]);
					}
				}

				cbbArrangeGrade.setSelectedIndex(-1);
				cbbIdLastFirstName.setSelectedIndex(-1);
				textFieldFind.setText(fullText);
			}
		});
		btnFind.setForeground(SystemColor.textHighlightText);
		btnFind.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnFind.setBackground(SystemColor.activeCaption);
		btnFind.setBounds(707, 107, 100, 30);
		panel2.add(btnFind);

		cbbIdLastFirstName = new JComboBox();
		cbbIdLastFirstName.setModel(new DefaultComboBoxModel(new String[] { "Id", "FullName", "Grade" }));
		cbbIdLastFirstName.setBounds(499, 45, 138, 30);
		cbbIdLastFirstName.setSelectedIndex(-1);
		cbbIdLastFirstName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = cbbIdLastFirstName.getSelectedIndex();
				isCbbTt = selectedIndex;
			}
		});
		panel2.add(cbbIdLastFirstName);

		JButton btnAsc = new JButton("Asc");
		btnAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldFind.setText("");
				String selectedValue = (String) cbbIdLastFirstName.getSelectedItem();
				if (isCbb == -1) {
					if (selectedValue.equals("FullName")) {
						getStudentSXByCourseId("firstname", "ASC");
					} else if (selectedValue.equals("Grade")) {
						getStudentSXByCourseId("grade", "ASC");
					} else {
						getStudentSXByCourseId("personID", "ASC");
					}
				} else if (isCbb == 0) {
					if (isCbbTt == -1) {
						findStudentSX(3.2, 4, "grade", "ASC");
					} else {
						if (selectedValue.equals("FullName")) {
							findStudentSX(3.2, 4, "firstname", "ASC");
						} else if (selectedValue.equals("Grade")) {
							findStudentSX(3.2, 4, "grade", "ASC");
						} else {
							findStudentSX(3.2, 4, "personID", "ASC");
						}
					}
				} else if (isCbb == 1) {
					if (isCbbTt == -1) {
						findStudentSX(2.40, 3.19, "grade", "ASC");
					} else {
						if (selectedValue.equals("FullName")) {
							findStudentSX(2.40, 3.19, "firstname", "ASC");
						} else if (selectedValue.equals("Grade")) {
							findStudentSX(2.40, 3.19, "grade", "ASC");
						} else {
							findStudentSX(2.40, 3.19, "personID", "ASC");
						}
					}
				} else if (isCbb == 2) {
					if (isCbbTt == -1) {
						findStudentSX(1.60, 2.39, "grade", "ASC");
					} else {
						if (selectedValue.equals("FullName")) {
							findStudentSX(1.60, 2.39, "firstname", "ASC");
						} else if (selectedValue.equals("Grade")) {
							findStudentSX(1.60, 2.39, "grade", "ASC");
						} else {
							findStudentSX(1.60, 2.39, "personID", "ASC");
						}
					}
				} else if (isCbb == 3) {
					if (isCbbTt == -1) {
						findStudentSX(0.80, 1.59, "grade", "ASC");
					} else {
						if (selectedValue.equals("FullName")) {
							findStudentSX(0.80, 1.59, "firstname", "ASC");
						} else if (selectedValue.equals("Grade")) {
							findStudentSX(0.80, 1.59, "grade", "ASC");
						} else {
							findStudentSX(0.80, 1.59, "personID", "ASC");
						}
					}
				} else if (isCbb == 4) {
					if (isCbbTt == -1) {
						findStudentSX(0, 0.80, "grade", "ASC");
					} else {
						if (selectedValue.equals("FullName")) {
							findStudentSX(0, 0.80, "firstname", "ASC");
						} else if (selectedValue.equals("Grade")) {
							findStudentSX(0, 0.80, "grade", "ASC");
						} else {
							findStudentSX(0, 0.80, "personID", "ASC");
						}
					}
				} else {
					getStudentGradeIsnull();
				}
			}
		});
		btnAsc.setForeground(SystemColor.controlDkShadow);
		btnAsc.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnAsc.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnAsc.setBounds(647, 45, 76, 30);
		panel2.add(btnAsc);

		JButton btnDesc = new JButton("Desc");
		btnDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldFind.setText("");
				String selectedValue = (String) cbbIdLastFirstName.getSelectedItem();
				if (isCbb == -1) {
					if (selectedValue.equals("FullName")) {
						getStudentSXByCourseId("firstname", "DESC");
					} else if (selectedValue.equals("Grade")) {
						getStudentSXByCourseId("grade", "DESC");
					} else {
						getStudentSXByCourseId("personID", "DESC");
					}
				} else if (isCbb == 0) {
					if (isCbbTt == -1) {
						findStudentSX(3.2, 4, "grade", "DESC");
					} else {
						if (selectedValue.equals("FullName")) {
							findStudentSX(3.2, 4, "firstname", "DESC");
						} else if (selectedValue.equals("Grade")) {
							findStudentSX(3.2, 4, "grade", "DESC");
						} else {
							findStudentSX(3.2, 4, "personID", "DESC");
						}
					}
				} else if (isCbb == 1) {
					if (isCbbTt == -1) {
						findStudentSX(2.40, 3.19, "grade", "DESC");
					} else {
						if (selectedValue.equals("FullName")) {
							findStudentSX(2.40, 3.19, "firstname", "DESC");
						} else if (selectedValue.equals("Grade")) {
							findStudentSX(2.40, 3.19, "grade", "DESC");
						} else {
							findStudentSX(2.40, 3.19, "personID", "DESC");
						}
					}
				} else if (isCbb == 2) {
					if (isCbbTt == -1) {
						findStudentSX(1.60, 2.39, "grade", "DESC");
					} else {
						if (selectedValue.equals("FullName")) {
							findStudentSX(1.60, 2.39, "firstname", "DESC");
						} else if (selectedValue.equals("Grade")) {
							findStudentSX(1.60, 2.39, "grade", "DESC");
						} else {
							findStudentSX(1.60, 2.39, "personID", "DESC");
						}
					}
				} else if (isCbb == 3) {
					if (isCbbTt == -1) {
						findStudentSX(0.80, 1.59, "grade", "DESC");
					} else {
						if (selectedValue.equals("FullName")) {
							findStudentSX(0.80, 1.59, "firstname", "DESC");
						} else if (selectedValue.equals("Grade")) {
							findStudentSX(0.80, 1.59, "grade", "DESC");
						} else {
							findStudentSX(0.80, 1.59, "personID", "DESC");
						}
					}
				} else if (isCbb == 4) {
					if (isCbbTt == -1) {
						findStudentSX(0, 0.80, "grade", "DESC");
					} else {
						if (selectedValue.equals("FullName")) {
							findStudentSX(0, 0.80, "firstname", "DESC");
						} else if (selectedValue.equals("Grade")) {
							findStudentSX(0, 0.80, "grade", "DESC");
						} else {
							findStudentSX(0, 0.80, "personID", "DESC");
						}
					}
				} else {
					getStudentGradeIsnull();
				}
			}
		});
		btnDesc.setForeground(SystemColor.controlDkShadow);
		btnDesc.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnDesc.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnDesc.setBounds(731, 45, 76, 30);
		panel2.add(btnDesc);

		String[] grades = { "Grade A(3.20 - 4.00)", "Grade B(2.40 - 3.19)", "Grade C(1.60 - 2.39)",
				"Grade D(0.80 - 1.59)", "Grade F(< 0.80)", "Grade is null" };
		cbbArrangeGrade = new JComboBox<>(grades);
		cbbArrangeGrade.setSelectedIndex(-1);
		cbbArrangeGrade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldFind.setText("");
				int selectedIndex = cbbArrangeGrade.getSelectedIndex();
				isCbb = selectedIndex;
				if (selectedIndex == 0) {
					findCbbMark(3.2, 4.0);
				} else if (selectedIndex == 1) {
					findCbbMark(2.40, 3.19);
				} else if (selectedIndex == 2) {
					findCbbMark(1.60, 2.39);
				} else if (selectedIndex == 3) {
					findCbbMark(0.80, 1.59);
				} else if (selectedIndex == 4) {
					findCbbMark(0, 0.80);
				} else if (selectedIndex == 5) {
					getStudentGradeIsnull();
				}
			}
		});
		cbbArrangeGrade.setBounds(350, 45, 138, 30);
		panel2.add(cbbArrangeGrade);

		JButton btnRefesh_1 = new JButton("Refesh");
		btnRefesh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbbArrangeGrade.setSelectedIndex(-1);
				cbbIdLastFirstName.setSelectedIndex(-1);
				textFieldFind.setText("");
				String courseStringMarks = lblCourse.getText().toString();
				String departmentStringMarks = lblDepartment.getText().toString();
				String[] courseStringMark = courseStringMarks.split(" ");
				String[] departmentStringMark = departmentStringMarks.split(" ");
				loadDataMark(departmentStringMark[2], courseStringMark[2]);
			}
		});
		btnRefesh_1.setForeground(SystemColor.controlDkShadow);
		btnRefesh_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnRefesh_1.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnRefesh_1.setBounds(350, 104, 88, 30);
		panel2.add(btnRefesh_1);

		panel1 = new JPanel();
		panel1.setBounds(0, 0, 835, 645);
		add(panel1);
		panel1.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 835, 35);
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaption);
		panel1.add(panel);

		JLabel lblNewLabel = new JLabel("StudenGrade");
		lblNewLabel.setBounds(341, 5, 135, 25);
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel.add(lblNewLabel);

		JButton btnMark = new JButton("Mark");
		btnMark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleMark();
			}
		});
		btnMark.setForeground(SystemColor.controlDkShadow);
		btnMark.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnMark.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnMark.setBounds(703, 586, 100, 30);
		panel1.add(btnMark);

		JLabel lblNewLabel_1 = new JLabel("Department");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(27, 61, 84, 30);
		panel1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Course");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(348, 61, 56, 30);
		panel1.add(lblNewLabel_1_1);

		cbbCourse = new JComboBox();
		cbbCourse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedDepartment = (String) cbbDepartment.getSelectedItem();
				String selectedCourse = (String) cbbCourse.getSelectedItem();

				dataFindDeparByTitle(selectedDepartment, selectedCourse);
			}
		});
		cbbCourse.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbbCourse.setBounds(402, 61, 233, 30);
		panel1.add(cbbCourse);

		cbbDepartment = new JComboBox();
		DepartmentDAL deparDal = new DepartmentDAL();
		ArrayList<String> arrDepar = DepartmentDAL.getAllName();

		DefaultComboBoxModel<String> departmentModel1 = new DefaultComboBoxModel<>(arrDepar.toArray(new String[0]));
		cbbDepartment.setModel(departmentModel1);
		cbbDepartment.setSelectedIndex(-1);
		cbbDepartment.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cbbDepartment.setBounds(121, 61, 217, 30);

		// xử lý sự kiện cho cbb
		cbbDepartment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedDepartment = (String) cbbDepartment.getSelectedItem();

				CourseDAL courseDal = new CourseDAL();
				ArrayList<String> arrCourseTitle = courseDal.getTitle(selectedDepartment);

				DefaultComboBoxModel<String> departmentModel = new DefaultComboBoxModel<>(
						arrCourseTitle.toArray(new String[0]));
				cbbCourse.setModel(departmentModel);
				dataFindDepar(selectedDepartment);
			}
		});

		panel1.add(cbbDepartment);

		JSeparator separator = new JSeparator();
		separator.setBounds(27, 111, 786, 9);
		panel1.add(separator);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (tabbedPane.getSelectedIndex() == 0) {
					isHandleTabTable = true;
				} else if (tabbedPane.getSelectedIndex() == 1) {
					isHandleTabTable = false;
				}
			}
		});
		tabbedPane.setBounds(23, 120, 790, 410);
		panel1.add(tabbedPane);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Online", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 786, 397);
		panel_1.add(scrollPane);

		table_online = new JTable();
		table_online.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table_online.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "No", "Title", "Credits", "Department" }));
		table_online.getColumnModel().getColumn(0).setPreferredWidth(30);
		table_online.getColumnModel().getColumn(1).setPreferredWidth(200);
		table_online.getColumnModel().getColumn(2).setPreferredWidth(40);
		table_online.getColumnModel().getColumn(3).setPreferredWidth(200);

		scrollPane.setViewportView(table_online);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Onsite", null, panel_2, null);
		panel_2.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 786, 383);
		panel_2.add(scrollPane_1);

		table_onsite = new JTable();
		table_onsite.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		table_onsite.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "No", "Title", "Credits", "Department" }));
		table_onsite.getColumnModel().getColumn(0).setPreferredWidth(30);
		table_onsite.getColumnModel().getColumn(1).setPreferredWidth(200);
		table_onsite.getColumnModel().getColumn(2).setPreferredWidth(40);
		table_onsite.getColumnModel().getColumn(3).setPreferredWidth(200);
		scrollPane_1.setViewportView(table_onsite);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(27, 567, 786, 9);
		panel1.add(separator_1);

		JButton btnRefesh = new JButton("Refesh");
		btnRefesh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbbDepartment.setSelectedIndex(-1);
				displayDataCourseOnline();
				displayDataCourseOnsite();
			}
		});
		btnRefesh.setForeground(SystemColor.controlDkShadow);
		btnRefesh.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnRefesh.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnRefesh.setBounds(703, 61, 100, 30);
		panel1.add(btnRefesh);

		displayDataCourseOnline();
		displayDataCourseOnsite();
	}

	public static void handleMark() {
		if (isHandleTabTable == true) {
			int selectedRow = table_online.getSelectedRow();
			if (selectedRow != -1) {
				// Lấy dữ liệu từ dòng được chọn
				String title = (String) table_online.getValueAt(selectedRow, 1);
				String department = (String) table_online.getValueAt(selectedRow, 3);

				loadDataMark(department, title);
				panel1.setVisible(false);
				panel2.setVisible(true);
				panel2.setBounds(0, 0, 835, 645);
			}
		} else {
			int selectedRow = table_onsite.getSelectedRow();
			if (selectedRow != -1) {
				// Lấy dữ liệu từ dòng được chọn
				String title = (String) table_onsite.getValueAt(selectedRow, 1);
				String department = (String) table_onsite.getValueAt(selectedRow, 3);

				loadDataMark(department, title);
				panel1.setVisible(false);
				panel2.setVisible(true);
				panel2.setBounds(0, 0, 835, 645);
			}
		}
	}

	public static void findStudentSX(double grade1, double grade2, String tt, String sx) {
		int no = 0;
		String courseStringMarks = lblCourse.getText().toString();
		String departmentStringMarks = lblDepartment.getText().toString();
		String[] courseStringMark = courseStringMarks.split(" ");
		String[] departmentStringMark = departmentStringMarks.split(" ");

		CourseBLL courseBll = new CourseBLL();
		int courseId = courseBll.getCourseIDByTitleName(departmentStringMark[2], courseStringMark[2]);

		StudentGradeBLL gradeBLL = new StudentGradeBLL();
		ArrayList<StudentGrade> arrGrade = gradeBLL.getStudentSX(courseId, grade1, grade2, tt, sx);

		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = personBll.getAllPerson();

		DefaultTableModel model = (DefaultTableModel) tableGrade.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (StudentGrade studentGrade : arrGrade) {
			for (Person person : arrPerson) {
				if (studentGrade.getStudentID() == person.getPersonId()) {
					String fullname = person.getLastname() + " " + person.getFirstname();
					model.addRow(new Object[] { ++no, person.getPersonId(), fullname, studentGrade.getGrade() });
				}
			}
		}
	}

	public static void getStudentSXByCourseId(String tt, String sx) {
		int no = 0;
		String courseStringMarks = lblCourse.getText().toString();
		String departmentStringMarks = lblDepartment.getText().toString();
		String[] courseStringMark = courseStringMarks.split(" ");
		String[] departmentStringMark = departmentStringMarks.split(" ");

		CourseBLL courseBll = new CourseBLL();
		int courseId = courseBll.getCourseIDByTitleName(departmentStringMark[2], courseStringMark[2]);

		StudentGradeBLL gradeBLL = new StudentGradeBLL();
		ArrayList<StudentGrade> arrGrade = gradeBLL.getStudentSXByCourseId(courseId, tt, sx);

		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = personBll.getAllPerson();

		DefaultTableModel model = (DefaultTableModel) tableGrade.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (StudentGrade studentGrade : arrGrade) {
			for (Person person : arrPerson) {
				if (studentGrade.getStudentID() == person.getPersonId()) {
					String fullname = person.getLastname() + " " + person.getFirstname();
					model.addRow(new Object[] { ++no, person.getPersonId(), fullname, studentGrade.getGrade() });
				}
			}
		}
	}

	public static void findStudentByLastFirstName(String last, String first) {
		int no = 0;
		String courseStringMarks = lblCourse.getText().toString();
		String departmentStringMarks = lblDepartment.getText().toString();
		String[] courseStringMark = courseStringMarks.split(" ");
		String[] departmentStringMark = departmentStringMarks.split(" ");

		CourseBLL courseBll = new CourseBLL();
		int courseId = courseBll.getCourseIDByTitleName(departmentStringMark[2], courseStringMark[2]);

		StudentGradeBLL gradeBLL = new StudentGradeBLL();
		ArrayList<StudentGrade> arrGrade = gradeBLL.getStudentIdByCourseID(courseId);

		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = personBll.getStudentByLastFirstName(last, first);

		DefaultTableModel model = (DefaultTableModel) tableGrade.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (StudentGrade studentGrade : arrGrade) {
			for (Person person : arrPerson) {
				if (studentGrade.getStudentID() == person.getPersonId()) {
					String fullname = person.getLastname() + " " + person.getFirstname();
					model.addRow(new Object[] { ++no, person.getPersonId(), fullname, studentGrade.getGrade() });
				}
			}
		}
	}

	public static void getStudentGradeIsnull() {
		int no = 0;
		String courseStringMarks = lblCourse.getText().toString();
		String departmentStringMarks = lblDepartment.getText().toString();
		String[] courseStringMark = courseStringMarks.split(" ");
		String[] departmentStringMark = departmentStringMarks.split(" ");

		CourseBLL courseBll = new CourseBLL();
		int courseId = courseBll.getCourseIDByTitleName(departmentStringMark[2], courseStringMark[2]);

		StudentGradeBLL gradeBLL = new StudentGradeBLL();
		ArrayList<StudentGrade> arrGrade = gradeBLL.getStudentGradeIsnull(courseId);

		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = personBll.getAllPerson();

		DefaultTableModel model = (DefaultTableModel) tableGrade.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (StudentGrade studentGrade : arrGrade) {
			for (Person person : arrPerson) {
				if (studentGrade.getStudentID() == person.getPersonId()) {
					String fullname = person.getLastname() + " " + person.getFirstname();
					model.addRow(new Object[] { ++no, person.getPersonId(), fullname, studentGrade.getGrade() });
				}
			}
		}
	}

	public static void getFindByGrade(double grade) {
		int no = 0;
		String courseStringMarks = lblCourse.getText().toString();
		String departmentStringMarks = lblDepartment.getText().toString();
		String[] courseStringMark = courseStringMarks.split(" ");
		String[] departmentStringMark = departmentStringMarks.split(" ");

		CourseBLL courseBll = new CourseBLL();
		int courseId = courseBll.getCourseIDByTitleName(departmentStringMark[2], courseStringMark[2]);

		StudentGradeBLL gradeBLL = new StudentGradeBLL();
		ArrayList<StudentGrade> arrGrade = gradeBLL.getFindByGrade(courseId, grade);

		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = personBll.getAllPerson();

		DefaultTableModel model = (DefaultTableModel) tableGrade.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (StudentGrade studentGrade : arrGrade) {
			for (Person person : arrPerson) {
				if (studentGrade.getStudentID() == person.getPersonId()) {
					String fullname = person.getLastname() + " " + person.getFirstname();
					model.addRow(new Object[] { ++no, person.getPersonId(), fullname, studentGrade.getGrade() });
				}
			}
		}
	}

	public static void findCbbMark(double grade1, double grade2) {
		int no = 0;
		String courseStringMarks = lblCourse.getText().toString();
		String departmentStringMarks = lblDepartment.getText().toString();
		String[] courseStringMark = courseStringMarks.split(" ");
		String[] departmentStringMark = departmentStringMarks.split(" ");

		CourseBLL courseBll = new CourseBLL();
		int courseId = courseBll.getCourseIDByTitleName(departmentStringMark[2], courseStringMark[2]);

		StudentGradeBLL gradeBLL = new StudentGradeBLL();
		ArrayList<StudentGrade> arrGrade = gradeBLL.getStudentByGrade(courseId, grade1, grade2);

		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = personBll.getAllPerson();

		DefaultTableModel model = (DefaultTableModel) tableGrade.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (StudentGrade studentGrade : arrGrade) {
			for (Person person : arrPerson) {
				if (studentGrade.getStudentID() == person.getPersonId()) {
					String fullname = person.getLastname() + " " + person.getFirstname();
					model.addRow(new Object[] { ++no, person.getPersonId(), fullname, studentGrade.getGrade() });
				}
			}
		}
	}

	public static void loadDataMark(String departmentStringMark, String courseStringMark) {
		int no = 0;
		CourseBLL courseBll = new CourseBLL();
		int courseId = courseBll.getCourseIDByTitleName(departmentStringMark, courseStringMark);

		StudentGradeBLL gradeBLL = new StudentGradeBLL();
		ArrayList<StudentGrade> arrGrade = gradeBLL.getStudentIdByCourseID(courseId);

		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = personBll.getAllPerson();

		DefaultTableModel model = (DefaultTableModel) tableGrade.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (StudentGrade studentGrade : arrGrade) {
			for (Person person : arrPerson) {
				if (studentGrade.getStudentID() == person.getPersonId()) {
					String fullname = person.getLastname() + " " + person.getFirstname();
					model.addRow(new Object[] { ++no, person.getPersonId(), fullname, studentGrade.getGrade() });
				}
			}
		}
		lblCourse.setText("Course : " + courseStringMark);
		lblDepartment.setText("Department : " + departmentStringMark);
		lblClassSize.setText("Class size : " + arrGrade.size());
	}

	public static void displayDataCourseOnline() {
		int no = 0;
		CourseBLL courseBll = new CourseBLL();
		ArrayList<Course> arrCourse = courseBll.getAllCourseOnline();
		DepartmentBLL deparBLL = new DepartmentBLL();
		ArrayList<Department> arrDepar = deparBLL.getAllDepartment();

		DefaultTableModel model = (DefaultTableModel) table_online.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Course course : arrCourse) {
			for (Department depar : arrDepar) {
				if (course.getDepartmentID() == depar.getDepartmentID()) {
					model.addRow(new Object[] { ++no, course.getTitle(), course.getCredits(), depar.getName() });
				}
			}
		}
	}

	public static void displayDataCourseOnsite() {
		int no = 0;
		CourseBLL courseBll = new CourseBLL();
		ArrayList<Course> arrCourse = courseBll.getAllCourseOnsite();
		DepartmentBLL deparBLL = new DepartmentBLL();
		ArrayList<Department> arrDepar = deparBLL.getAllDepartment();

		DefaultTableModel model = (DefaultTableModel) table_onsite.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Course course : arrCourse) {
			for (Department depar : arrDepar) {
				if (course.getDepartmentID() == depar.getDepartmentID()) {
					model.addRow(new Object[] { ++no, course.getTitle(), course.getCredits(), depar.getName() });
				}
			}
		}
	}

	public static void dataFindDepar(String name) {
		int no = 0;
		int no1 = 0;
		CourseBLL courseBll = new CourseBLL();
		ArrayList<Course> arrCourse = courseBll.getAllCourseOnsite();

		CourseBLL courseBll1 = new CourseBLL();
		ArrayList<Course> arrCourse1 = courseBll1.getAllCourseOnline();

		DepartmentBLL deparBLL = new DepartmentBLL();
		ArrayList<Department> arrDepar = deparBLL.getDeparByName(name);

		DefaultTableModel model = (DefaultTableModel) table_online.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Course course : arrCourse1) {
			for (Department depar : arrDepar) {
				if (course.getDepartmentID() == depar.getDepartmentID()) {
					model.addRow(new Object[] { ++no, course.getTitle(), course.getCredits(), depar.getName() });
				}
			}
		}

		DefaultTableModel model1 = (DefaultTableModel) table_onsite.getModel();
		while (model1.getRowCount() != 0) {
			model1.removeRow(0);
		}
		for (Course course : arrCourse) {
			for (Department depar : arrDepar) {
				if (course.getDepartmentID() == depar.getDepartmentID()) {
					model1.addRow(new Object[] { ++no1, course.getTitle(), course.getCredits(), depar.getName() });
				}
			}
		}
	}

	public static void dataFindDeparByTitle(String name, String title) {
		int no = 0;
		int no1 = 0;
		CourseBLL courseBll = new CourseBLL();
		ArrayList<Course> arrCourse = courseBll.getAllCourseOnsite();

		CourseBLL courseBll1 = new CourseBLL();
		ArrayList<Course> arrCourse1 = courseBll1.getAllCourseOnline();
		DepartmentBLL deparBLL = new DepartmentBLL();
		ArrayList<String> arrDepar = deparBLL.getDeparByNamByTitle(name, title);

		DefaultTableModel model = (DefaultTableModel) table_online.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Course course : arrCourse1) {
			if (course.getTitle().equals(arrDepar.get(0))) {
				model.addRow(new Object[] { ++no, arrDepar.get(0), arrDepar.get(1), arrDepar.get(2) });
			}
		}

		DefaultTableModel model1 = (DefaultTableModel) table_onsite.getModel();
		while (model1.getRowCount() != 0) {
			model1.removeRow(0);
		}
		for (Course course : arrCourse) {
			if (course.getTitle().equals(arrDepar.get(0))) {
				model1.addRow(new Object[] { ++no, arrDepar.get(0), arrDepar.get(1), arrDepar.get(2) });
			}
		}
	}
}
