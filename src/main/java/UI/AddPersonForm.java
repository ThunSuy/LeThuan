package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.demo.DateChooserPanel;

import BLL.Person;
import BLL.PersonBLL;
import DAL.Connect;

import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class AddPersonForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField tf_lastname;
	private static JTextField tf_firstname;
	private static JDateChooser hiredate = new JDateChooser();
	private static JDateChooser endate = new JDateChooser();
	private static boolean ischeck = true;
	private static int personID;
	private static boolean checkWindow = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPersonForm frame = new AddPersonForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean hasPersonId(int id) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Select * from person where PersonID=" + id;
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				result = rs.next();
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return result;
	}

	public static boolean checkBlankTf() {
		return (tf_lastname.getText().trim().equals("") || tf_firstname.getText().trim().equals("")
				|| hiredate.getDateFormatString().equals("") || endate.getDateFormatString().equals(""));
	}

	public static void addPerson() {
		try {
			if (tf_lastname.getText().isEmpty() || tf_firstname.getText().isEmpty() || hiredate.getDate() == null
					|| endate.getDate() == null) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
				return;
			} else {
				String last = tf_lastname.getText();
				String first = tf_firstname.getText();

				Date selectedDate = hiredate.getDate();

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String formattedDate = dateFormat.format(selectedDate);

				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
				String formattedTime = now.format(timeFormat);

				String dateTimeString = formattedDate + " " + formattedTime;
				Timestamp timestamp;

				Date enDate = endate.getDate();

				SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				String formattedDate1 = dateFormat1.format(enDate);

				LocalDateTime now1 = LocalDateTime.now();
				DateTimeFormatter timeFormat1 = DateTimeFormatter.ofPattern("HH:mm:ss");
				String formattedTime1 = now1.format(timeFormat1);

				String dateTimeString1 = formattedDate1 + " " + formattedTime1;
				Timestamp timestamp1;
				try {
					LocalDate selectedLocalDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDateTime localDateTime = LocalDateTime.of(selectedLocalDate, LocalTime.MIDNIGHT);
					timestamp = Timestamp.valueOf(localDateTime);

					LocalDate selectedLocalDate1 = enDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDateTime localDateTime1 = LocalDateTime.of(selectedLocalDate1, LocalTime.MIDNIGHT);
					timestamp1 = Timestamp.valueOf(localDateTime1);

					if (ischeck == true) {
						Person ps = new Person();
						ps.setLastname(last);
						ps.setFirstname(first);
						ps.setHireDate(timestamp);
						ps.setEnrollmentDate(timestamp1);
						String rs = PersonBLL.addPerson(ps);
						if (rs.equals("Thêm thành công")) {
							JOptionPane.showMessageDialog(null, rs);
							PersonForm.displayData();
							checkWindow = true;
							ischeck = true;
						}
					} else {
						Person ps = new Person();
						ps.setLastname(last);
						ps.setFirstname(first);
						ps.setHireDate(timestamp);
						ps.setEnrollmentDate(timestamp1);
						ps.setPersonId(personID);
						String rs = PersonBLL.editPerson(ps);
						if (rs.equals("Cập nhật thành công")) {
							JOptionPane.showMessageDialog(null, rs);
							PersonForm.displayData();
							ischeck = true;
							checkWindow = true;
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Lỗi định dạng Timestamp. Định dạng đúng: yyyy-MM-dd HH:mm:ss");
					return;
				}
			}
		} catch (Exception e2) {
			System.out.println(e2);
		}
	}

	public static void setData(int id, String lastname, String firstname, String hiredate1, String endate1,
			boolean check1) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		ischeck = check1;
		tf_lastname.setText(lastname);
		tf_firstname.setText(firstname);
		personID = id;

		if (hiredate1.equals(" ")) {
			hiredate.setDate(null);
		} else {
			LocalDate dataHire = LocalDate.parse(hiredate1, formatter);
			hiredate.setDate(java.sql.Date.valueOf(dataHire));
		}
		if (endate1.equals(" ")) {
			endate.setDate(null);
		} else {
			LocalDate dataEn = LocalDate.parse(endate1, formatter);
			endate.setDate(java.sql.Date.valueOf(dataEn));
		}
	}

	/**
	 * Create the frame.
	 */
	public AddPersonForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 475, 35);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblPerson = new JLabel("Person Add");
		lblPerson.setForeground(SystemColor.textHighlightText);
		lblPerson.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel.add(lblPerson);

		JLabel lblLastname = new JLabel("LastName");
		lblLastname.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblLastname.setBounds(34, 83, 95, 30);
		contentPane.add(lblLastname);

		JLabel lblFirstname = new JLabel("FirstName");
		lblFirstname.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblFirstname.setBounds(34, 142, 95, 30);
		contentPane.add(lblFirstname);

		JLabel lblHiredate = new JLabel("HireDate");
		lblHiredate.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblHiredate.setBounds(34, 215, 95, 30);
		contentPane.add(lblHiredate);

		JLabel lblEnrollmentdate = new JLabel("EnrollmentDate");
		lblEnrollmentdate.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblEnrollmentdate.setBounds(34, 288, 137, 30);
		contentPane.add(lblEnrollmentdate);

		tf_lastname = new JTextField();
		tf_lastname.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tf_lastname.setColumns(10);
		tf_lastname.setBounds(181, 83, 249, 30);
		contentPane.add(tf_lastname);

		tf_firstname = new JTextField();
		tf_firstname.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tf_firstname.setColumns(10);
		tf_firstname.setBounds(181, 142, 249, 30);
		contentPane.add(tf_firstname);

		hiredate = new JDateChooser();
		hiredate.setDateFormatString("yyyy-MM-dd");
		hiredate.setBounds(181, 215, 249, 30);
		contentPane.add(hiredate);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkWindow = false;
				addPerson();
				if(checkWindow == true) {
					dispose();

				}
			}
		});
		btnSave.setForeground(SystemColor.controlDkShadow);
		btnSave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSave.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnSave.setBounds(184, 349, 95, 30);
		contentPane.add(btnSave);

		JButton btnRefesh = new JButton("Refesh");
		btnRefesh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tf_firstname.setText("");
				tf_lastname.setText("");
				hiredate.setDate(null);
				endate.setDate(null);
			}
		});
		btnRefesh.setForeground(SystemColor.controlDkShadow);
		btnRefesh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnRefesh.setBackground(UIManager.getColor("Button.darkShadow"));
		btnRefesh.setBounds(335, 349, 95, 30);
		contentPane.add(btnRefesh);

		endate = new JDateChooser();
		endate.setDateFormatString("yyyy-MM-dd");
		endate.setBounds(181, 288, 252, 30);
		contentPane.add(endate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int reponse = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Yes",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (reponse == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});

		btnCancel.setForeground(SystemColor.controlDkShadow);
		btnCancel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnCancel.setBackground(UIManager.getColor("Button.darkShadow"));
		btnCancel.setBounds(370, 403, 95, 30);
		contentPane.add(btnCancel);
	}
}
