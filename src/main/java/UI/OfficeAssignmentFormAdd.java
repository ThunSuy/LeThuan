package UI;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import BLL.OfficeAssignment;
import BLL.OfficeAssignmentBLL;
import BLL.Person;
import BLL.PersonBLL;


import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;

public class OfficeAssignmentFormAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField textField;
	private static JComboBox comboBox;
	private static JDateChooser dateChooser;
	private static boolean ischeck = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OfficeAssignmentFormAdd frame = new OfficeAssignmentFormAdd();
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
	public OfficeAssignmentFormAdd() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 385, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		btnCancel.setBounds(253, 217, 85, 30);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("List Person");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(28, 35, 93, 30);
		contentPane.add(lblNewLabel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(textField.getText().isEmpty() || comboBox.getSelectedItem() == null || dateChooser.getDate() == null) {
	                	JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
	                }else {
	                	OfficeAssignmentBLL officeBLL = new OfficeAssignmentBLL();
	                	
	                	String selectedValue = (String) comboBox.getSelectedItem();
	                	String[] cbbValue = selectedValue.split(" - ");
	                	int id = Integer.parseInt(cbbValue[0]);
	                	
	                	String location = textField.getText();
	                	
	                	Date selectedDate = dateChooser.getDate();
	                	
	                	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                	String formattedDate = dateFormat.format(selectedDate);
	                	
	                	LocalDateTime now = LocalDateTime.now();
	                	DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
	                	String formattedTime = now.format(timeFormat);
	                	
	                	String dateTimeString = formattedDate + " " + formattedTime;
	                	Timestamp timestamp1;
	                	try {
	                		
	                		LocalDate selectedLocalDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	                		LocalDateTime localDateTime = LocalDateTime.of(selectedLocalDate, LocalTime.MIDNIGHT);
	                		timestamp1 = Timestamp.valueOf(localDateTime);
	                		
	                		if(ischeck == true) {
	                			OfficeAssignment office = new OfficeAssignment(id, location, timestamp1);
	                            String rs = officeBLL.addOffice(office);
	                            JOptionPane.showMessageDialog(null, rs);
	                            
	                			if(rs.equals("Thêm thành công")) {
	                				OfficeAssignmentForm.loadDataOffice();
	                				ischeck = true;
	                			}
	                		}else {
	                			OfficeAssignment office = new OfficeAssignment(id, location, timestamp1);
	                			String rs = officeBLL.editOffice(office);
	                			JOptionPane.showMessageDialog(null, rs);
	                			OfficeAssignmentForm.loadDataOffice();
	                			
	                			if(rs.equals("Cập nhật thành công")) {
	                				OfficeAssignmentForm.loadDataOffice();
	                				ischeck = true;
	                			}
	                		}
	                		
	                    	textField.setText("");
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
		btnSave.setBounds(127, 217, 85, 30);
		contentPane.add(btnSave);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		PersonBLL psBll =new PersonBLL();
		ArrayList<Person> arrps = psBll.getAllPerson();
		
		OfficeAssignmentBLL officeBll = new OfficeAssignmentBLL();
		ArrayList<OfficeAssignment> arrof = officeBll.getAllOffice();
		
		Set<Integer> uniqueStudentIDs = new HashSet<>();

		for (OfficeAssignment office : arrof) {
		    uniqueStudentIDs.add(office.getInstructorID());
		}
		ArrayList<String> arrData = new ArrayList<String>();
		for(Person o : arrps) {
			if (!uniqueStudentIDs.contains(o.getPersonId())) {
				String fullname =o.getPersonId() + " - " + o.getLastname() + " " + o.getFirstname();
				arrData.add(fullname);
			}
		}	
		DefaultComboBoxModel<String> departmentModel1 = new DefaultComboBoxModel<>(arrData.toArray(new String[0]));
		comboBox.setModel(departmentModel1);
		comboBox.setSelectedIndex(-1);
		comboBox.setBounds(127, 37, 211, 30);
		comboBox.setMaximumRowCount(4);
		contentPane.add(comboBox);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblLocation.setBounds(28, 93, 93, 30);
		contentPane.add(lblLocation);
		
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textField.setBounds(127, 96, 211, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTimestamp = new JLabel("Timestamp");
		lblTimestamp.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblTimestamp.setBounds(28, 155, 93, 30);
		contentPane.add(lblTimestamp);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(127, 149, 211, 30);
		contentPane.add(dateChooser);
	}
	public static void setData(String cbb,String loca,String time,boolean ischeck1) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(time, formatter);
		
        comboBox.removeAllItems();
        comboBox.addItem(cbb);
		ischeck = ischeck1;
		textField.setText(loca);
		dateChooser.setDate(java.sql.Date.valueOf(localDate));
	}
}
