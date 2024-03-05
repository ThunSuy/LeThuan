package UI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.time.LocalDate;

import BLL.Department;
import BLL.DepartmentBLL;
import BLL.Person;
import BLL.PersonBLL;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class DepartmentFormAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnRefesh;
	private JButton btnCancel;
	private JButton btnSave;
	private static JTextField tf_name;
	private static JTextField tf_budget;
	private static JDateChooser dateChooser;
	private static JComboBox comboBox;
	private static boolean ischeck = true;
	private static int departmentId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentFormAdd frame = new DepartmentFormAdd();
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
	public DepartmentFormAdd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 373, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 506, 35);
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaption);
		contentPane.add(panel);

		JLabel lblNewLabel_3 = new JLabel("Department Add");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(SystemColor.textHighlightText);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_3.setBounds(0, 0, 367, 35);
		panel.add(lblNewLabel_3);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(54, 65, 44, 30);
		contentPane.add(lblNewLabel);

		tf_name = new JTextField();
		tf_name.setBounds(120, 68, 199, 30);
		contentPane.add(tf_name);
		tf_name.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Budget");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(49, 108, 61, 30);
		contentPane.add(lblNewLabel_1);

		tf_budget = new JTextField();
		tf_budget.setColumns(10);
		tf_budget.setBounds(120, 107, 199, 30);
		contentPane.add(tf_budget);

		JLabel lblNewLabel_1_1 = new JLabel("StartDate");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(34, 149, 61, 30);
		contentPane.add(lblNewLabel_1_1);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(120, 143, 199, 30);
		contentPane.add(dateChooser);

		JLabel lblNewLabel_1_1_1 = new JLabel("Administrator");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(10, 191, 100, 30);
		contentPane.add(lblNewLabel_1_1_1);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		PersonBLL psBll = new PersonBLL();
		ArrayList<Person> arrps = psBll.getAllPerson();

		ArrayList<String> arrData = new ArrayList<String>();
		for (Person o : arrps) {
			String fullname = o.getPersonId() + " - " + o.getLastname() + " " + o.getFirstname();
			arrData.add(fullname);
		}
		DefaultComboBoxModel<String> departmentModel1 = new DefaultComboBoxModel<>(arrData.toArray(new String[0]));
		comboBox.setModel(departmentModel1);
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(120, 189, 199, 30);
		contentPane.add(comboBox);

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tfname = tf_name.getText();
				String regex = "[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(tfname);

				String tfbudget = tf_budget.getText();

				Date selectedDate1 = dateChooser.getDate();
				Date currentDate = new Date();
				Date selectedDate2 = dateChooser.getDate();

				if (tf_name.getText().isEmpty() || tf_budget.getText().isEmpty() || comboBox.getSelectedItem() == null
						|| dateChooser.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
				} else if (tfname.matches(".*\\d.*") || matcher.find()) {
					JOptionPane.showMessageDialog(null, "Name Department không được chứa chữ số và kí tự đặc biệt");
				} else if (tfbudget.matches(".*[a-zA-Z].*")) {
					JOptionPane.showMessageDialog(null, "budget Department không được chứ kí tự chữ");
				} else {
					DepartmentBLL deparBll = new DepartmentBLL();
					int idDepar = deparBll.getDeparId() + 1;
					// dữ liệu cần
					String name = tf_name.getText();
					// dữ liệu cần
					double budget = Double.parseDouble(tf_budget.getText());
					String selectedValue = (String) comboBox.getSelectedItem();
					String[] value = selectedValue.split(" - ");
					// dữ liệu cần
					int idAdmin = Integer.parseInt(value[0]);

					Date selectedDate = dateChooser.getDate();

					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String formattedDate = dateFormat.format(selectedDate);

					LocalDateTime now = LocalDateTime.now();
					DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
					String formattedTime = now.format(timeFormat);

					String dateTimeString = formattedDate + " " + formattedTime;
					Timestamp timestamp1;
					try {

						LocalDate selectedLocalDate = selectedDate.toInstant().atZone(ZoneId.systemDefault())
								.toLocalDate();
						LocalDateTime localDateTime = LocalDateTime.of(selectedLocalDate, LocalTime.MIDNIGHT);
						timestamp1 = Timestamp.valueOf(localDateTime);

						if (ischeck == true) {
							Department dep = new Department(idDepar, name, budget, timestamp1, idAdmin);
							String rs = deparBll.addDepartment(dep);
							JOptionPane.showMessageDialog(null, rs);
							DepartmentForm.loadDataDepar();
						} else {
							Department dep = new Department(departmentId, name, budget, timestamp1, idAdmin);
							String rs = deparBll.editDepartment(dep);
							JOptionPane.showMessageDialog(null, rs);
							DepartmentForm.loadDataDepar();

						}
						ischeck = true;
						tf_budget.setText("");
						tf_name.setText("");
						comboBox.setSelectedIndex(-1);
						dateChooser.setDate(null);
						dispose();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSave.setBounds(120, 250, 85, 30);
		contentPane.add(btnSave);

		btnRefesh = new JButton("Refesh");
		btnRefesh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_budget.setText("");
				tf_name.setText("");
				comboBox.setSelectedIndex(-1);
				dateChooser.setDate(null);
			}
		});
		btnRefesh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnRefesh.setBounds(234, 250, 85, 30);
		contentPane.add(btnRefesh);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reponse = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Yes",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (reponse == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCancel.setBounds(264, 310, 85, 30);
		contentPane.add(btnCancel);
	}

	public static void setData(String id, String name, String budget, String start, String admin, boolean ischeck1) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(start, formatter);

		int temp = Integer.parseInt(admin);
		int index = -1;
		PersonBLL psBll = new PersonBLL();
		ArrayList<Person> arr = psBll.getAllPerson();
		for (int i = 0; i < arr.size(); i++) {
			Person ps = arr.get(i);
			if (ps.getPersonId() == temp) {
				index = i;
				break;
			}
		}
		int deparId = Integer.parseInt(id);

		departmentId = deparId;
		comboBox.setSelectedIndex(index);
		dateChooser.setDate(java.sql.Date.valueOf(localDate));
		tf_name.setText(name);
		tf_budget.setText(budget);
		ischeck = ischeck1;
	}
}
