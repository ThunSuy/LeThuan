package UI;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BLL.OfficeAssignment;
import BLL.OfficeAssignmentBLL;
import BLL.Person;
import BLL.PersonBLL;
import DAL.OfficeAssignmentDAL;

import java.sql.Timestamp;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class OfficeAssignmentForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private static JTable tableOffice;

	/**
	 * Create the panel.
	 */
	public OfficeAssignmentForm() {
		setPreferredSize(new Dimension(835, 570));
		setLayout(null);

		JComboBox comboBox = new JComboBox();
		ArrayList<String> arrDepar = new ArrayList<String>();
		arrDepar.add("InstructorID");
		arrDepar.add("Fullname");
		arrDepar.add("Location");
		arrDepar.add("TimeStamp");

		DefaultComboBoxModel<String> departmentModel1 = new DefaultComboBoxModel<>(arrDepar.toArray(new String[0]));
		comboBox.setModel(departmentModel1);
		comboBox.setSelectedIndex(-1);
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		comboBox.setBounds(29, 29, 191, 30);
		add(comboBox);

		JButton btnAsc = new JButton("Asc");
		btnAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = (String) comboBox.getSelectedItem();
				if (value != null) {
					if (textField.getText().equals("")) {
						if (value.equals("Fullname")) {
							getAllOfficeSX("firstname", "Asc");
						} else {
							getAllOfficeSX(value, "Asc");
						}
					} else {
						String datafind = textField.getText();
						// ktra yyyy-mm-dd
						String regex = "\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])";
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(datafind);
						// ktra số
						String regex1 = "\\d+";
						Pattern pattern1 = Pattern.compile(regex1);
						Matcher matcher1 = pattern1.matcher(datafind);
						// ktra chữ và ssố
						String regex2 = ".*[a-zA-Z].*\\d+.*|.*\\d+.*[a-zA-Z].*";
						Pattern pattern2 = Pattern.compile(regex2);
						Matcher matcher2 = pattern2.matcher(datafind);

						if (matcher1.matches()) {
							if (value.equals("Fullname")) {
								getAllOfficeSXByIdByLoca("-1", "-1", Integer.parseInt(datafind), "-1", "firstname",
										"Asc");
							} else {
								getAllOfficeSXByIdByLoca("-1", "-1", Integer.parseInt(datafind), "-1", value, "Asc");
							}
						} else if (matcher.matches()) {
							if (value.equals("Fullname")) {
								getAllOfficeSXByTime(datafind, "firstname", "Asc");
							} else {
								getAllOfficeSXByTime(datafind, value, "Asc");
							}
						} else if (matcher2.matches()) {
							if (value.equals("Fullname")) {
								getAllOfficeSXByIdByLoca("-1", "-1", -1111, datafind, "firstname", "Asc");
							} else {
								getAllOfficeSXByIdByLoca("-1", "-1", -1111, datafind, value, "Asc");
							}
						} else {
							String[] text = datafind.split(" ");
							if (text.length == 1) {
								if (value.equals("Fullname")) {
									getAllOfficeSXByIdByLoca(text[0], text[0], -1111, "-1", "firstname", "Asc");
								} else {
									getAllOfficeSXByIdByLoca(text[0], text[0], -1111, "-1", value, "Asc");
								}
							} else if (text.length == 2) {
								if (value.equals("Fullname")) {
									getAllOfficeSXByIdByLoca(text[0], text[1], -1111, "-1", "firstname", "Asc");
								} else {
									getAllOfficeSXByIdByLoca(text[0], text[1], -1111, "-1", value, "Asc");
								}
							}
						}
					}
				} else {

				}
			}
		});
		btnAsc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAsc.setBounds(241, 29, 57, 30);
		add(btnAsc);

		JButton btnDesc = new JButton("Desc");
		btnDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = (String) comboBox.getSelectedItem();
				if (value != null) {
					if (textField.getText().equals("")) {
						if (value.equals("Fullname")) {
							getAllOfficeSX("firstname", "DESC");
						} else {
							getAllOfficeSX(value, "DESC");
						}
					} else {
						String datafind = textField.getText();
						// ktra yyyy-mm-dd
						String regex = "\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])";
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(datafind);
						// ktra số
						String regex1 = "\\d+";
						Pattern pattern1 = Pattern.compile(regex1);
						Matcher matcher1 = pattern1.matcher(datafind);
						// ktra chữ và ssố
						String regex2 = ".*[a-zA-Z].*\\d+.*|.*\\d+.*[a-zA-Z].*";
						Pattern pattern2 = Pattern.compile(regex2);
						Matcher matcher2 = pattern2.matcher(datafind);

						if (matcher1.matches()) {
							if (value.equals("Fullname")) {
								getAllOfficeSXByIdByLoca("-1", "-1", Integer.parseInt(datafind), "-1", "firstname",
										"DESC");
							} else {
								getAllOfficeSXByIdByLoca("-1", "-1", Integer.parseInt(datafind), "-1", value, "DESC");
							}
						} else if (matcher.matches()) {
							if (value.equals("Fullname")) {
								getAllOfficeSXByTime(datafind, "firstname", "DESC");
							} else {
								getAllOfficeSXByTime(datafind, value, "DESC");
							}
						} else if (matcher2.matches()) {
							if (value.equals("Fullname")) {
								getAllOfficeSXByIdByLoca("-1", "-1", -1111, datafind, "firstname", "DESC");
							} else {
								getAllOfficeSXByIdByLoca("-1", "-1", -1111, datafind, value, "DESC");
							}
						} else {
							String[] text = datafind.split(" ");
							if (text.length == 1) {
								if (value.equals("Fullname")) {
									getAllOfficeSXByIdByLoca(text[0], text[0], -1111, "-1", "firstname", "DESC");
								} else {
									getAllOfficeSXByIdByLoca(text[0], text[0], -1111, "-1", value, "DESC");
								}
							} else if (text.length == 2) {
								if (value.equals("Fullname")) {
									getAllOfficeSXByIdByLoca(text[0], text[1], -1111, "-1", "firstname", "DESC");
								} else {
									getAllOfficeSXByIdByLoca(text[0], text[1], -1111, "-1", value, "DESC");
								}
							}
						}
					}
				} else {

				}
			}
		});
		btnDesc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnDesc.setBounds(308, 29, 66, 30);
		add(btnDesc);

		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField.setBounds(564, 30, 173, 30);
		add(textField);
		textField.setColumns(10);

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String datafind = textField.getText();
				// ktra yyyy-mm-dd
				String regex = "\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(datafind);
				// ktra số
				String regex1 = "\\d+";
				Pattern pattern1 = Pattern.compile(regex1);
				Matcher matcher1 = pattern1.matcher(datafind);
				// ktra chữ và ssố
				String regex2 = ".*[a-zA-Z].*\\d+.*|.*\\d+.*[a-zA-Z].*";
				Pattern pattern2 = Pattern.compile(regex2);
				Matcher matcher2 = pattern2.matcher(datafind);

				if (matcher.matches()) {
					getOfficeSXByTimestamp(datafind);
				} else if (matcher1.matches()) {
					getOfficeSXByIdByLoca(Integer.parseInt(datafind), "-1");
				} else if (matcher2.matches()) {
					getOfficeSXByIdByLoca(-1, datafind);
				} else {
					String[] text = datafind.split(" ");
					if (text.length == 1) {
						getDataByFullnameOr(text[0], text[0]);
					} else if (text.length == 2) {
						getDataByFullnameOr(text[0], text[1]);
					}
				}
			}
		});
		btnFind.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFind.setBounds(744, 29, 66, 30);
		add(btnFind);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(29, 80, 781, 12);
		add(separator_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 113, 781, 345);
		add(scrollPane);

		tableOffice = new JTable();
		tableOffice.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tableOffice.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "No", "InstructorID", "FullName", "Location", "Timetamps" }));
		tableOffice.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableOffice.getColumnModel().getColumn(1).setPreferredWidth(20);
		tableOffice.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableOffice.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableOffice.getColumnModel().getColumn(4).setPreferredWidth(150);
		scrollPane.setViewportView(tableOffice);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(29, 485, 781, 12);
		add(separator_1_1);

		JButton btnDel = new JButton("Del");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableOffice.getSelectedRow();
				if (row != -1) {
					OfficeAssignmentBLL officeBll = new OfficeAssignmentBLL();
					int id = (int) tableOffice.getValueAt(row, 1);
					int reponse = JOptionPane.showConfirmDialog(null,
							"Bạn có chắc muốn xóa OfficeAssignment có InstructorID = " + id, "Yes",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (reponse == JOptionPane.YES_OPTION) {
						String rs = officeBll.delOffice(id);
						if (rs.equals("Xóa thành công")) {
							JOptionPane.showMessageDialog(null, rs);
							officeBll.delInstructor(id);
							loadDataOffice();
						}
					}
				}
			}
		});
		btnDel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnDel.setBounds(753, 517, 57, 30);
		add(btnDel);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OfficeAssignmentFormAdd ofFormAdd = new OfficeAssignmentFormAdd();
				int selectedRow = tableOffice.getSelectedRow();
				if (selectedRow != -1) {
					// Lấy dữ liệu từ dòng được chọn
					String id = String.valueOf((Integer) tableOffice.getValueAt(selectedRow, 1));
					String fullname = (String) tableOffice.getValueAt(selectedRow, 2);
					String location = (String) tableOffice.getValueAt(selectedRow, 3);
					String time = (String) tableOffice.getValueAt(selectedRow, 4);
					String id_fullname = id + " - " + fullname;
					ofFormAdd.setData(id_fullname, location, time, false);
					ofFormAdd.setVisible(true);
					ofFormAdd.setLocationRelativeTo(null);
				}
			}
		});
		btnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEdit.setBounds(657, 517, 80, 30);
		add(btnEdit);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OfficeAssignmentFormAdd officeAdd = new OfficeAssignmentFormAdd();
				officeAdd.setVisible(true);
				officeAdd.setLocationRelativeTo(null);
			}
		});
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAdd.setBounds(564, 517, 72, 30);
		add(btnAdd);

		JButton btnRefesh = new JButton("Refesh");
		btnRefesh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDataOffice();
				textField.setText("");
				comboBox.setSelectedIndex(-1);
			}
		});
		btnRefesh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnRefesh.setBounds(29, 517, 95, 30);
		add(btnRefesh);
		loadDataOffice();
	}

	public static void loadDataOffice() {
		int no = 1;
		OfficeAssignmentBLL officeBll = new OfficeAssignmentBLL();
		ArrayList<OfficeAssignment> arrOffice = officeBll.getAllOffice();

		PersonBLL personBLL = new PersonBLL();
		ArrayList<Person> arrPerson = personBLL.getAllPerson();

		DefaultTableModel model = (DefaultTableModel) tableOffice.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (OfficeAssignment o : arrOffice) {
			Timestamp timestampAdmin = o.getTimestamp();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String stringAdmin = dateFormat.format(timestampAdmin);
			String[] getAdmin = stringAdmin.split(" ");
			for (Person cong : arrPerson) {
				if (o.getInstructorID() == cong.getPersonId()) {
					String fullname = cong.getLastname() + " " + cong.getFirstname();
					model.addRow(new Object[] { no++, o.getInstructorID(), fullname, o.getLocation(), getAdmin[0] });
				}
			}
		}
	}

	public static void getDataByFullnameOr(String last, String first) {
		int no = 1;
		OfficeAssignmentBLL officeBll = new OfficeAssignmentBLL();
		ArrayList<OfficeAssignment> arrOffice = officeBll.getAllOffice();

		PersonBLL personBLL = new PersonBLL();
		ArrayList<Person> arrPerson = personBLL.getDataByFullnameOr(last, first);

		DefaultTableModel model = (DefaultTableModel) tableOffice.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (OfficeAssignment o : arrOffice) {
			Timestamp timestampAdmin = o.getTimestamp();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String stringAdmin = dateFormat.format(timestampAdmin);
			String[] getAdmin = stringAdmin.split(" ");
			for (Person cong : arrPerson) {
				if (o.getInstructorID() == cong.getPersonId()) {
					String fullname = cong.getLastname() + " " + cong.getFirstname();
					model.addRow(new Object[] { no++, o.getInstructorID(), fullname, o.getLocation(), getAdmin[0] });
				}
			}
		}
	}

	public static void getAllOfficeSX(String tt, String sx) {
		int no = 1;
		OfficeAssignmentBLL officeBll = new OfficeAssignmentBLL();
		ArrayList<OfficeAssignment> arrOffice = officeBll.getAllOfficeSX(tt, sx);

		PersonBLL personBLL = new PersonBLL();
		ArrayList<Person> arrPerson = personBLL.getAllPerson();

		DefaultTableModel model = (DefaultTableModel) tableOffice.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (OfficeAssignment o : arrOffice) {
			Timestamp timestampAdmin = o.getTimestamp();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String stringAdmin = dateFormat.format(timestampAdmin);
			String[] getAdmin = stringAdmin.split(" ");
			for (Person cong : arrPerson) {
				if (o.getInstructorID() == cong.getPersonId()) {
					String fullname = cong.getLastname() + " " + cong.getFirstname();
					model.addRow(new Object[] { no++, o.getInstructorID(), fullname, o.getLocation(), getAdmin[0] });
				}
			}
		}
	}

	public static void getAllOfficeSXByIdByLoca(String last, String first, int id, String loca, String tt, String sx) {
		int no = 1;
		OfficeAssignmentBLL officeBll = new OfficeAssignmentBLL();
		ArrayList<OfficeAssignment> arrOffice = officeBll.getAllOfficeSXByIDByLoca(last, first, id, loca, tt, sx);

		PersonBLL personBLL = new PersonBLL();
		ArrayList<Person> arrPerson = personBLL.getAllPerson();

		DefaultTableModel model = (DefaultTableModel) tableOffice.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (OfficeAssignment o : arrOffice) {
			Timestamp timestampAdmin = o.getTimestamp();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String stringAdmin = dateFormat.format(timestampAdmin);
			String[] getAdmin = stringAdmin.split(" ");
			for (Person cong : arrPerson) {
				if (o.getInstructorID() == cong.getPersonId()) {
					String fullname = cong.getLastname() + " " + cong.getFirstname();
					model.addRow(new Object[] { no++, o.getInstructorID(), fullname, o.getLocation(), getAdmin[0] });
				}
			}
		}
	}

	public static void getAllOfficeSXByTime(String time, String tt, String sx) {
		int no = 1;
		OfficeAssignmentBLL officeBll = new OfficeAssignmentBLL();
		ArrayList<OfficeAssignment> arrOffice = officeBll.getAllOfficeSXByTime(time, tt, sx);

		PersonBLL personBLL = new PersonBLL();
		ArrayList<Person> arrPerson = personBLL.getAllPerson();

		DefaultTableModel model = (DefaultTableModel) tableOffice.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (OfficeAssignment o : arrOffice) {
			Timestamp timestampAdmin = o.getTimestamp();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String stringAdmin = dateFormat.format(timestampAdmin);
			String[] getAdmin = stringAdmin.split(" ");
			for (Person cong : arrPerson) {
				if (o.getInstructorID() == cong.getPersonId()) {
					String fullname = cong.getLastname() + " " + cong.getFirstname();
					model.addRow(new Object[] { no++, o.getInstructorID(), fullname, o.getLocation(), getAdmin[0] });
				}
			}
		}
	}

	public static void getOfficeSXByTimestamp(String time) {
		int no = 1;
		OfficeAssignmentBLL officeBll = new OfficeAssignmentBLL();
		ArrayList<OfficeAssignment> arrOffice = officeBll.getOfficeSXByTimestamp(time);

		PersonBLL personBLL = new PersonBLL();
		ArrayList<Person> arrPerson = personBLL.getAllPerson();

		DefaultTableModel model = (DefaultTableModel) tableOffice.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (OfficeAssignment o : arrOffice) {
			Timestamp timestampAdmin = o.getTimestamp();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String stringAdmin = dateFormat.format(timestampAdmin);
			String[] getAdmin = stringAdmin.split(" ");
			for (Person cong : arrPerson) {
				if (o.getInstructorID() == cong.getPersonId()) {
					String fullname = cong.getLastname() + " " + cong.getFirstname();
					model.addRow(new Object[] { no++, o.getInstructorID(), fullname, o.getLocation(), getAdmin[0] });
				}
			}
		}
	}

	public static void getOfficeSXByIdByLoca(int id, String loca) {
		int no = 1;
		OfficeAssignmentBLL officeBll = new OfficeAssignmentBLL();
		ArrayList<OfficeAssignment> arrOffice = officeBll.getOfficeSXByIdByLoca(id, loca);

		PersonBLL personBLL = new PersonBLL();
		ArrayList<Person> arrPerson = personBLL.getAllPerson();

		DefaultTableModel model = (DefaultTableModel) tableOffice.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (OfficeAssignment o : arrOffice) {
			Timestamp timestampAdmin = o.getTimestamp();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String stringAdmin = dateFormat.format(timestampAdmin);
			String[] getAdmin = stringAdmin.split(" ");
			for (Person cong : arrPerson) {
				if (o.getInstructorID() == cong.getPersonId()) {
					String fullname = cong.getLastname() + " " + cong.getFirstname();
					model.addRow(new Object[] { no++, o.getInstructorID(), fullname, o.getLocation(), getAdmin[0] });
				}
			}
		}
	}
}
