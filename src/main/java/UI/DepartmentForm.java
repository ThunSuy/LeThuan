package UI;

import java.awt.Dimension;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BLL.CourseBLL;
import BLL.Department;
import BLL.DepartmentBLL;
import BLL.Person;
import BLL.PersonBLL;
import BLL.StudentGradeBLL;
import DAL.DepartmentDAL;

import javax.swing.JTextField;

public class DepartmentForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox comboBoxSX;
	private JButton btnAsc;
	private JButton btnDesc;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDel;
	private JButton btnRefesh;
	private static JTable tableDepar;
	private JTextField tfFind;

	public DepartmentForm() {
		setPreferredSize(new Dimension(835, 645));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 835, 35);
		panel.setLayout(null);
		panel.setBackground(SystemColor.activeCaption);
		add(panel);
		
		JLabel lblNewLabel_3 = new JLabel("Department");
		lblNewLabel_3.setForeground(SystemColor.textHighlightText);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_3.setBounds(341, 5, 135, 25);
		panel.add(lblNewLabel_3);
		
		comboBoxSX = new JComboBox();
		comboBoxSX.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		ArrayList<String> arrDepar = new ArrayList<String>();
		arrDepar.add("Name");
        arrDepar.add("Budget");
        arrDepar.add("StartDate");
        arrDepar.add("Administrator");
		
		DefaultComboBoxModel<String> departmentModel1 = new DefaultComboBoxModel<>(arrDepar.toArray(new String[0]));
		comboBoxSX.setModel(departmentModel1);
		comboBoxSX.setSelectedIndex(-1);
		comboBoxSX.setBounds(410, 62, 198, 30);
		add(comboBoxSX);
		
		btnAsc = new JButton("ASC");
		btnAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String datafind = tfFind.getText();
				String selectedValue = (String) comboBoxSX.getSelectedItem();
				// ktra yyyy-mm-dd
				String regex = "\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])";
				Pattern pattern = Pattern.compile(regex);
		        Matcher matcher = pattern.matcher(datafind);
				// ktra số
		        String regex1 = "\\d+";
		        Pattern pattern1 = Pattern.compile(regex1);
		        Matcher matcher1 = pattern1.matcher(datafind);
		        
				if(datafind.equals("")) {
					dataFindSX(-1,"",-1, selectedValue, "ASC");
				}else {
					if(matcher1.matches()) {
						dataFindSX(Integer.parseInt(datafind) ,"1",Double.parseDouble(datafind), selectedValue, "ASC");
					}else if(matcher.matches()){
						dataFindSXByStart(datafind, selectedValue, "ASC");
					}else {						
						dataFindSX(-1,datafind,-1, selectedValue, "ASC");
					}
				}
			}
		});
		btnAsc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAsc.setBounds(618, 62, 85, 30);
		add(btnAsc);
		
		btnDesc = new JButton("DESC");
		btnDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String datafind = tfFind.getText();
				String selectedValue = (String) comboBoxSX.getSelectedItem();
				// ktra yyyy-mm-dd
				String regex = "\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])";
				Pattern pattern = Pattern.compile(regex);
		        Matcher matcher = pattern.matcher(datafind);
				// ktra số
		        String regex1 = "\\d+";
		        Pattern pattern1 = Pattern.compile(regex1);
		        Matcher matcher1 = pattern1.matcher(datafind);
		        
				if(datafind.equals("")) {
					dataFindSX(-1,"",-1, selectedValue, "DESC");
				}else {
					if(matcher1.matches()) {
						dataFindSX(Integer.parseInt(datafind),"1",Double.parseDouble(datafind), selectedValue, "DESC");
					}else if(matcher.matches()){
						dataFindSXByStart(datafind, selectedValue, "DESC");
					}else {						
						dataFindSX(-1,datafind,-1, selectedValue, "DESC");
					}
				}
			}
		});
		btnDesc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnDesc.setBounds(717, 62, 85, 30);
		add(btnDesc);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(28, 121, 774, 9);
		add(separator_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 151, 774, 369);
		add(scrollPane);
		
		tableDepar = new JTable();
		tableDepar.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"No","ID", "Name", "Budget", "StartDate", "Adminitrator"
			}
		));
		tableDepar.getColumnModel().getColumn(0).setPreferredWidth(20);
		tableDepar.getColumnModel().getColumn(1).setPreferredWidth(20);
		tableDepar.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableDepar.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableDepar.getColumnModel().getColumn(4).setPreferredWidth(120);
		tableDepar.getColumnModel().getColumn(5).setPreferredWidth(120);
		scrollPane.setViewportView(tableDepar);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setBounds(28, 543, 774, 9);
		add(separator_2_1);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepartmentFormAdd deparForm = new DepartmentFormAdd();	
				deparForm.setVisible(true);
				deparForm.setLocationRelativeTo(null);
			}
		});
		btnAdd.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnAdd.setBounds(28, 571, 85, 30);
		add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepartmentFormAdd deparForm = new DepartmentFormAdd();
				int selectedRow = tableDepar.getSelectedRow();
	            if (selectedRow != -1) {
	                // Lấy dữ liệu từ dòng được chọn
	            	String id = String.valueOf((Integer) tableDepar.getValueAt(selectedRow, 1));
	            	String name = (String) tableDepar.getValueAt(selectedRow, 2);
	                String budget = String.valueOf((Double) tableDepar.getValueAt(selectedRow, 3));
	                String start = (String) tableDepar.getValueAt(selectedRow, 4);
	                String adminString = (String) tableDepar.getValueAt(selectedRow, 5);
	                String[] admin = adminString.split(" - ");
	                
	                deparForm.setData(id,name, budget, start, admin[0],false);
					deparForm.setVisible(true);
					deparForm.setLocationRelativeTo(null);
	            }
			}
		});
		btnEdit.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnEdit.setBounds(151, 571, 85, 30);
		add(btnEdit);
		
		btnDel = new JButton("Del");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableDepar.getSelectedRow();
				if (selectedRow != -1) {				
					int id = (Integer) tableDepar.getValueAt(selectedRow, 1) ;
					int reponse = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa Department có ID = " + id,
							"Yes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (reponse == JOptionPane.YES_OPTION) {
						DepartmentBLL deparBll = new DepartmentBLL();
						if(deparBll.getById(id)) {
							JOptionPane.showMessageDialog(null, "Không thể xóa Department vì liên quan đến khóa học");
						}else {
							String rs = deparBll.delDepartment(id);
							if(rs.equals("Xoá thành công")) {
								JOptionPane.showMessageDialog(null, rs);		
								loadDataDepar();
							}
						}
					}
				}
			}
		});
		btnDel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnDel.setBounds(269, 571, 85, 30);
		add(btnDel);
		
		btnRefesh = new JButton("Refesh");
		btnRefesh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfFind.setText("");
				comboBoxSX.setSelectedIndex(-1);
				loadDataDepar();
			}
		});
		btnRefesh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnRefesh.setBounds(717, 571, 85, 30);
		add(btnRefesh);
		
		tfFind = new JTextField();
		tfFind.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		tfFind.setBounds(28, 62, 184, 30);
		add(tfFind);
		tfFind.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dataFind = tfFind.getText();
				// ktra yyyy-mm-dd
				String regex = "\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])";
				Pattern pattern = Pattern.compile(regex);
		        Matcher matcher = pattern.matcher(dataFind);
		        // ktra số
		        String regex1 = "\\d+";
		        Pattern pattern1 = Pattern.compile(regex1);
		        Matcher matcher1 = pattern1.matcher(dataFind);
		        
		        
				if (matcher.matches()) {
					getDeparByStart(dataFind);
				}else if (matcher1.matches()) {
					double buget = Double.parseDouble(dataFind);
					int id = Integer.parseInt(dataFind);
					getDeparByBudget(id,buget);
				}else {	
					getDeparByName(dataFind);
				}
				
				
			}
			
		});
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFind.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnFind.setBounds(222, 62, 85, 30);
		add(btnFind);
		
		loadDataDepar();
	}
	public static void loadDataDepar() {
		int no = 0;
		
		DepartmentBLL deparBll = new DepartmentBLL();
		ArrayList<Department> arrDepar = deparBll.getAllDepartment();
		
		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = personBll.getAllPerson();
		
		DefaultTableModel model = (DefaultTableModel) tableDepar.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Department depar : arrDepar) {
			Timestamp timestampAdmin = depar.getStartDate();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String stringAdmin = dateFormat.format(timestampAdmin);
			String[] getAdmin = stringAdmin.split(" ");
			
			for (Person person : arrPerson) {
				if(depar.getAdministrator() == person.getPersonId()) {
					String fullName = person.getPersonId() + " - " + person.getLastname() + " " + person.getFirstname();
					model.addRow(new Object[] { ++no,depar.getDepartmentID(), depar.getName(), depar.getBudget(),  getAdmin[0], fullName });					
				}
			}	
		}
	}
	public static void getDeparByStart(String startdate) {
		int no = 0;
		
		DepartmentBLL deparBll = new DepartmentBLL();
		ArrayList<Department> arrDepar = deparBll.getDeparByStart(startdate);
		
		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = personBll.getAllPerson();
		
		DefaultTableModel model = (DefaultTableModel) tableDepar.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Department depar : arrDepar) {
			Timestamp timestampAdmin = depar.getStartDate();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String stringAdmin = dateFormat.format(timestampAdmin);
			String[] getAdmin = stringAdmin.split(" ");
			
			for (Person person : arrPerson) {
				if(depar.getAdministrator() == person.getPersonId()) {
					String fullName = person.getPersonId() + " - " + person.getLastname() + " " + person.getFirstname();
					model.addRow(new Object[] { ++no,depar.getDepartmentID(), depar.getName(), depar.getBudget(),  getAdmin[0], fullName });					
				}
			}	
		}
	}
	public static void getDeparByName(String name) {
		int no = 0;
		
		DepartmentBLL deparBll = new DepartmentBLL();
		ArrayList<Department> arrDepar = deparBll.getDeparByName(name);
		
		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = personBll.getAllPerson();
		
		DefaultTableModel model = (DefaultTableModel) tableDepar.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Department depar : arrDepar) {
			Timestamp timestampAdmin = depar.getStartDate();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String stringAdmin = dateFormat.format(timestampAdmin);
			String[] getAdmin = stringAdmin.split(" ");
			
			for (Person person : arrPerson) {
				if(depar.getAdministrator() == person.getPersonId()) {
					String fullName = person.getPersonId() + " - " + person.getLastname() + " " + person.getFirstname();
					model.addRow(new Object[] { ++no,depar.getDepartmentID(), depar.getName(), depar.getBudget(),  getAdmin[0], fullName });					
				}
			}	
		}
	}
	public static void getDeparByBudget(int id,double budget) {
		int no = 0;
		
		DepartmentBLL deparBll = new DepartmentBLL();
		ArrayList<Department> arrDepar = deparBll.getDeparByBudget(id,budget);
		
		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = personBll.getAllPerson();
		
		DefaultTableModel model = (DefaultTableModel) tableDepar.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Department depar : arrDepar) {
			Timestamp timestampAdmin = depar.getStartDate();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String stringAdmin = dateFormat.format(timestampAdmin);
			String[] getAdmin = stringAdmin.split(" ");
			
			for (Person person : arrPerson) {
				if(depar.getAdministrator() == person.getPersonId()) {
					String fullName = person.getPersonId() + " - " + person.getLastname() + " " + person.getFirstname();
					model.addRow(new Object[] { ++no,depar.getDepartmentID(), depar.getName(), depar.getBudget(),  getAdmin[0], fullName });					
				}
			}	
		}
	}
	public static void dataFindSX(int id,String name,double budget,String tt,String sx) {
		int no = 0;
		
		DepartmentBLL deparBll = new DepartmentBLL();
		ArrayList<Department> arrDepar = deparBll.getDeparSx(id,name,budget,tt,sx);
		
		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = personBll.getAllPerson();
		
		DefaultTableModel model = (DefaultTableModel) tableDepar.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Department depar : arrDepar) {
			Timestamp timestampAdmin = depar.getStartDate();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String stringAdmin = dateFormat.format(timestampAdmin);
			String[] getAdmin = stringAdmin.split(" ");
			
			for (Person person : arrPerson) {
				if(depar.getAdministrator() == person.getPersonId()) {
					String fullName = person.getPersonId() + " - " + person.getLastname() + " " + person.getFirstname();
					model.addRow(new Object[] { ++no,depar.getDepartmentID(), depar.getName(), depar.getBudget(),  getAdmin[0], fullName });					
				}
			}	
		}
	}
	public static void dataFindSXByStart(String start,String tt,String sx) {
		int no = 0;
		
		DepartmentBLL deparBll = new DepartmentBLL();
		ArrayList<Department> arrDepar = deparBll.getDeparSxByStart(start,tt,sx);
		
		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = personBll.getAllPerson();
		
		DefaultTableModel model = (DefaultTableModel) tableDepar.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Department depar : arrDepar) {
			Timestamp timestampAdmin = depar.getStartDate();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String stringAdmin = dateFormat.format(timestampAdmin);
			String[] getAdmin = stringAdmin.split(" ");
			
			for (Person person : arrPerson) {
				if(depar.getAdministrator() == person.getPersonId()) {
					String fullName = person.getPersonId() + " - " + person.getLastname() + " " + person.getFirstname();
					model.addRow(new Object[] { ++no,depar.getDepartmentID(), depar.getName(), depar.getBudget(),  getAdmin[0], fullName });					
				}
			}	
		}
	}
}
