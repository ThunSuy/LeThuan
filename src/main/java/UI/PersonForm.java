package UI;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

import BLL.Person;
import BLL.PersonBLL;


import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JSeparator;

public class PersonForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JTable table;
	private static JTextField tf_findperson;

	public static void displayData() {
		int no = 0;
		PersonBLL personBLL = new PersonBLL();
		ArrayList<Person> arr = personBLL.getAllPerson();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		 for (Person o : arr) {
		        Timestamp hiredate = o.getHireDate();
		        Timestamp endate = o.getEnrollmentDate();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        if (hiredate != null && endate != null) { // Kiểm tra xem hiredate và endate có null không
		            String string_1 = dateFormat.format(hiredate);
		            String[] gethiredate = string_1.split(" ");
		            String string_2 = dateFormat.format(endate);
		            String[] getendate = string_2.split(" ");
		            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), gethiredate[0], getendate[0] });
		        } else if (hiredate != null && endate == null) { // Nếu hiredate không null nhưng endate là null
		            String string_1 = dateFormat.format(hiredate);
		            String[] gethiredate = string_1.split(" ");
		            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), gethiredate[0], " " }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
		        } else if (hiredate == null && endate != null) { // Nếu hiredate là null nhưng endate không null
		            String string_2 = dateFormat.format(endate);
		            String[] getendate = string_2.split(" ");
		            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), " ", getendate[0] }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
		        } else { // Cả hiredate và endate đều null
		            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), " ", " " }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
		        }
		    }
	}
	

	public static void getAllPersonSX(int id,String tt, String sx,String last,String first) {
		int no = 0;
		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = PersonBLL.getAllPersonSX(id,tt, sx,last,first);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Person o : arrPerson) {
			Timestamp hiredate = o.getHireDate();
	        Timestamp endate = o.getEnrollmentDate();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        if (hiredate != null && endate != null) { // Kiểm tra xem hiredate và endate có null không
	            String string_1 = dateFormat.format(hiredate);
	            String[] gethiredate = string_1.split(" ");
	            String string_2 = dateFormat.format(endate);
	            String[] getendate = string_2.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), gethiredate[0], getendate[0] });
	        } else if (hiredate != null && endate == null) { // Nếu hiredate không null nhưng endate là null
	            String string_1 = dateFormat.format(hiredate);
	            String[] gethiredate = string_1.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), gethiredate[0], " " }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        } else if (hiredate == null && endate != null) { // Nếu hiredate là null nhưng endate không null
	            String string_2 = dateFormat.format(endate);
	            String[] getendate = string_2.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), " ", getendate[0] }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        } else { // Cả hiredate và endate đều null
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), " ", " " }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        }
		}
	}
	public static void getAllPersonSxDate(String hire,String endate1,String tt,String sx) {
		int no = 0;
		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = PersonBLL.getAllPersonSxDate(hire,endate1,tt, sx);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Person o : arrPerson) {
			Timestamp hiredate = o.getHireDate();
	        Timestamp endate = o.getEnrollmentDate();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        if (hiredate != null && endate != null) { // Kiểm tra xem hiredate và endate có null không
	            String string_1 = dateFormat.format(hiredate);
	            String[] gethiredate = string_1.split(" ");
	            String string_2 = dateFormat.format(endate);
	            String[] getendate = string_2.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), gethiredate[0], getendate[0] });
	        } else if (hiredate != null && endate == null) { // Nếu hiredate không null nhưng endate là null
	            String string_1 = dateFormat.format(hiredate);
	            String[] gethiredate = string_1.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), gethiredate[0], " " }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        } else if (hiredate == null && endate != null) { // Nếu hiredate là null nhưng endate không null
	            String string_2 = dateFormat.format(endate);
	            String[] getendate = string_2.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), " ", getendate[0] }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        } else { // Cả hiredate và endate đều null
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), " ", " " }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        }
		}
	}

	public static void getSxByFirstName(String sx) {
		int no = 0;
		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = PersonBLL.getSxByFirstName(sx);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Person o : arrPerson) {
			Timestamp hiredate = o.getHireDate();
	        Timestamp endate = o.getEnrollmentDate();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        if (hiredate != null && endate != null) { // Kiểm tra xem hiredate và endate có null không
	            String string_1 = dateFormat.format(hiredate);
	            String[] gethiredate = string_1.split(" ");
	            String string_2 = dateFormat.format(endate);
	            String[] getendate = string_2.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), gethiredate[0], getendate[0] });
	        } else if (hiredate != null && endate == null) { // Nếu hiredate không null nhưng endate là null
	            String string_1 = dateFormat.format(hiredate);
	            String[] gethiredate = string_1.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), gethiredate[0], " " }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        } else if (hiredate == null && endate != null) { // Nếu hiredate là null nhưng endate không null
	            String string_2 = dateFormat.format(endate);
	            String[] getendate = string_2.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), " ", getendate[0] }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        } else { // Cả hiredate và endate đều null
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), " ", " " }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        }
		}
	}
	public static void getPersonByName(int id,String last, String first) {
		int no = 0;
		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = PersonBLL.getPersonByName(id,last, first);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Person o : arrPerson) {
			Timestamp hiredate = o.getHireDate();
	        Timestamp endate = o.getEnrollmentDate();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        if (hiredate != null && endate != null) { // Kiểm tra xem hiredate và endate có null không
	            String string_1 = dateFormat.format(hiredate);
	            String[] gethiredate = string_1.split(" ");
	            String string_2 = dateFormat.format(endate);
	            String[] getendate = string_2.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), gethiredate[0], getendate[0] });
	        } else if (hiredate != null && endate == null) { // Nếu hiredate không null nhưng endate là null
	            String string_1 = dateFormat.format(hiredate);
	            String[] gethiredate = string_1.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), gethiredate[0], " " }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        } else if (hiredate == null && endate != null) { // Nếu hiredate là null nhưng endate không null
	            String string_2 = dateFormat.format(endate);
	            String[] getendate = string_2.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), " ", getendate[0] }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        } else { // Cả hiredate và endate đều null
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), " ", " " }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        }
		}
	}
	public static void getPersonByDate(String hire, String enda) {
		int no = 0;
		PersonBLL personBll = new PersonBLL();
		ArrayList<Person> arrPerson = PersonBLL.getPersonByDate(hire, enda);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		while (model.getRowCount() != 0) {
			model.removeRow(0);
		}
		for (Person o : arrPerson) {
			Timestamp hiredate = o.getHireDate();
	        Timestamp endate = o.getEnrollmentDate();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        if (hiredate != null && endate != null) { // Kiểm tra xem hiredate và endate có null không
	            String string_1 = dateFormat.format(hiredate);
	            String[] gethiredate = string_1.split(" ");
	            String string_2 = dateFormat.format(endate);
	            String[] getendate = string_2.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), gethiredate[0], getendate[0] });
	        } else if (hiredate != null && endate == null) { // Nếu hiredate không null nhưng endate là null
	            String string_1 = dateFormat.format(hiredate);
	            String[] gethiredate = string_1.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), gethiredate[0], " " }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        } else if (hiredate == null && endate != null) { // Nếu hiredate là null nhưng endate không null
	            String string_2 = dateFormat.format(endate);
	            String[] getendate = string_2.split(" ");
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), " ", getendate[0] }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        } else { // Cả hiredate và endate đều null
	            model.addRow(new Object[] { ++no, o.getPersonId(), o.getLastname(), o.getFirstname(), " ", " " }); // Thay "null" bằng giá trị mặc định hoặc chuỗi thích hợp
	        }
		}
	}
	private JComboBox cb_sort;
	
	/**
	 * Create the panel.
	 */
	public PersonForm() {
		setPreferredSize(new Dimension(835, 580));
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 107, 752, 356);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "No", "ID",  "LastName","FirstName", "HireDate", "EnrollmentDate" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		
		cb_sort = new JComboBox();
		ArrayList<String> arrDepar = new ArrayList<String>();
		arrDepar.add("PersonID");
		arrDepar.add("LastName");
		arrDepar.add("FirstName");
		arrDepar.add("HireDate");
		arrDepar.add("EnrollmentDate");
		DefaultComboBoxModel<String> departmentModel1 = new DefaultComboBoxModel<>(arrDepar.toArray(new String[0]));
		cb_sort.setModel(departmentModel1);
		cb_sort.setSelectedIndex(-1);
		cb_sort.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cb_sort.setBounds(50, 20, 120, 30);
		add(cb_sort);

		JButton btnAsc = new JButton("Asc");
		btnAsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = (String) cb_sort.getSelectedItem();
				String datafind=tf_findperson.getText();
				
				String[] data=datafind.split(" ");
				
				// ktra yyyy-mm-dd
				String regex = "\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])";
				Pattern pattern = Pattern.compile(regex);
		        Matcher matcher = pattern.matcher(datafind);
				// ktra số
		        String regex1 = "\\d+";
		        Pattern pattern1 = Pattern.compile(regex1);
		        Matcher matcher1 = pattern1.matcher(datafind);
				if(datafind.equals("")) {
					if (value != null) {
						if (value.equals("null")) {

						} else {
							getAllPersonSX(-1,value, "ASC","","");
						}
					} 
				}
				else {
					if (matcher.matches()) {
						getAllPersonSxDate(datafind, datafind, value, "ASC");
					}else if (matcher1.matches()) {
						getAllPersonSX(Integer.parseInt(datafind),value, "ASC","-1","-1");
					}else {
						if(data.length==1) {
							getAllPersonSX(-1,value, "ASC",data[0],data[0]);
							
						}else if(data.length==2) {
							getAllPersonSX(-1,value, "ASC",data[0],data[1]);
						}
					}
				}
			}
		});

		btnAsc.setForeground(SystemColor.controlDkShadow);
		btnAsc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnAsc.setBackground(UIManager.getColor("Button.background"));
		btnAsc.setBounds(179, 20, 60, 30);
		add(btnAsc);

		JButton btnDesc = new JButton("Desc");
		btnDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String value = (String) cb_sort.getSelectedItem();
				String datafind=tf_findperson.getText();
				
				String[] data=datafind.split(" ");
				
				// ktra yyyy-mm-dd
				String regex = "\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])";
				Pattern pattern = Pattern.compile(regex);
		        Matcher matcher = pattern.matcher(datafind);
				// ktra số
		        String regex1 = "\\d+";
		        Pattern pattern1 = Pattern.compile(regex1);
		        Matcher matcher1 = pattern1.matcher(datafind);
				if(datafind.equals("")) {
					if (value != null) {
						if (value.equals("null")) {

						} else {
							getAllPersonSX(-1,value, "DESC","","");
						}
					} 
				}
				else {
					if (matcher.matches()) {
						getAllPersonSxDate(datafind, datafind, value, "DESC");
					}else if (matcher1.matches()) {
						getAllPersonSX(Integer.parseInt(datafind),value, "DESC","-1","-1");
					}else {
						if(data.length==1) {
							getAllPersonSX(-1,value, "DESC",data[0],data[0]);
						}else if(data.length==2) {
							getAllPersonSX(-1,value, "DESC",data[0],data[1]);
						}
					}
				}
			}
		});
		btnDesc.setForeground(SystemColor.controlDkShadow);
		btnDesc.setFont(new Font("Segoe UI", Font.BOLD, 12));
		btnDesc.setBackground(UIManager.getColor("Button.background"));
		btnDesc.setBounds(249, 20, 60, 30);
		add(btnDesc);

		tf_findperson = new JTextField();
		tf_findperson.setColumns(10);
		tf_findperson.setBounds(463, 20, 250, 30);
		add(tf_findperson);

		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String datafind=tf_findperson.getText();
				String[] data=datafind.split(" ");
				// ktra yyyy-mm-dd
				String regex = "\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])";
				Pattern pattern = Pattern.compile(regex);
		        Matcher matcher = pattern.matcher(datafind);
				// ktra số
		        String regex1 = "\\d+";
		        Pattern pattern1 = Pattern.compile(regex1);
		        Matcher matcher1 = pattern1.matcher(datafind);
		        if(matcher.matches()) {
		        	getPersonByDate(datafind, datafind);
		        }else if (matcher1.matches()) {
		        	getPersonByName(Integer.parseInt(datafind),"-1","-1");
				}else {
					if(data.length==1) {
						getPersonByName(-1,data[0],data[0]);
						
					}else if(data.length==2) {
						getPersonByName(-1,data[0],data[1]);
					}
				}
			}
		});
		btnFind.setForeground(SystemColor.textHighlightText);
		btnFind.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnFind.setBackground(SystemColor.activeCaption);
		btnFind.setBounds(723, 20, 80, 30);
		add(btnFind);

		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tf_findperson.setText("");
				cb_sort.setSelectedIndex(-1);
				displayData();
			}
		});
		btnRefresh.setForeground(SystemColor.controlDkShadow);
		btnRefresh.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnRefresh.setBackground(UIManager.getColor("Button.background"));
		btnRefresh.setBounds(343, 509, 100, 30);
		add(btnRefresh);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddPersonForm add = new AddPersonForm();
				add.setDefaultCloseOperation(AddPersonForm.DISPOSE_ON_CLOSE);
				add.setLocationRelativeTo(null);
				add.setVisible(true);
			}
		});
		btnAdd.setForeground(SystemColor.controlDkShadow);
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnAdd.setBackground(UIManager.getColor("Button.background"));
		btnAdd.setBounds(463, 509, 100, 30);
		add(btnAdd);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectedRow = table.getSelectedRow();
	            if (selectedRow != -1) {
	                // Lấy dữ liệu từ dòng được chọn
	            	int id = (Integer) table.getValueAt(selectedRow, 1);
	            	String lastname = (String) table.getValueAt(selectedRow, 2);
	                String firstname = (String) table.getValueAt(selectedRow, 3);
	                String hiredate;
	                if(table.getValueAt(selectedRow, 4)!=null) {
	                	  hiredate = (String) table.getValueAt(selectedRow, 4);
	                }
	                else {
	                	hiredate="";
	                }
	                String endate ;
	                if(table.getValueAt(selectedRow, 5)!=null) {
	                	endate = (String) table.getValueAt(selectedRow, 5);
	                }
	                else {
	                	endate="";
	                }
	                AddPersonForm add = new AddPersonForm();
					add.setData(id,lastname,firstname,hiredate,endate,false);
					add.setDefaultCloseOperation(AddPersonForm.DISPOSE_ON_CLOSE);
					add.setLocationRelativeTo(null);
					add.setVisible(true);
	            }
				
			}
		});
		btnEdit.setForeground(SystemColor.controlDkShadow);
		btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnEdit.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnEdit.setBounds(583, 509, 100, 30);
		add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PersonBLL personBLL = new PersonBLL();
				int row = table.getSelectedRow();
				if (row != -1) {
					int personid = (int) table.getValueAt(row, 1);
					int reponse = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa Person có ID = " + personid,
							"Yes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (reponse == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(null, personBLL.delPerson(personid));
						displayData();
					}
				}
			}
		});
		btnDelete.setForeground(SystemColor.controlDkShadow);
		btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnDelete.setBackground(UIManager.getColor("Button.darkShadow"));
		btnDelete.setBounds(703, 509, 100, 30);
		add(btnDelete);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(50, 80, 748, 7);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(50, 488, 752, 18);
		add(separator_1);
		table.getColumnModel().getColumn(5).setPreferredWidth(88);

		displayData();
	}
}
