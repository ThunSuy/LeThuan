package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.TextField;
import java.awt.Canvas;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.List;
import java.awt.Panel;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class MainForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JPanel panel_main = new JPanel();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setLocationRelativeTo(null);
			        frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void switchToForm(int n) {
		panel_main.removeAll();
		if (n == 1) {
			panel_main.add(new CourseForm());
		} else if (n == 2) {
			panel_main.add(new CourseInstructorForm());
		} else if (n == 3) {
			panel_main.add(new StudentGradeForm());
		}  else if (n == 4) {
			panel_main.add(new DepartmentForm());
		} else if (n == 5) {
			panel_main.add(new StatisticalForm());
		}
		panel_main.revalidate();
		panel_main.repaint();
	}

	public void onClick(JPanel panel) {
		panel.setBackground(new Color(86, 65, 118));
	}

	public void onLeaveClick(JPanel panel) {
		panel.setBackground(new Color(64,43,100));
	}

	public MainForm() {
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 1049, 682);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel_main.removeAll();
		panel_main.add(new CourseForm());
		panel_main.revalidate();
		panel_main.repaint();
		JPanel panel = new JPanel();
		panel.setBackground(new Color(54, 33, 89));
		panel.setBounds(0, 0, 200, 645);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Course");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setForeground(SystemColor.inactiveCaptionBorder);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblNewLabel.setBounds(0, 10, 200, 40);
		panel.add(lblNewLabel);

		JLabel lblManagement = new JLabel("Management");
		lblManagement.setVerticalAlignment(SwingConstants.TOP);
		lblManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagement.setForeground(SystemColor.inactiveCaptionBorder);
		lblManagement.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblManagement.setBounds(0, 50, 200, 40);
		panel.add(lblManagement);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 100, 180, 2);
		panel.add(separator);

		JPanel panel_op_course = new JPanel();
		JPanel panel_op_department = new JPanel();
		JPanel panel_op_courseistructor = new JPanel();
		JPanel panel_op_studentgrade = new JPanel();
		JPanel panel_op_statistical = new JPanel();
		panel_op_statistical.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchToForm(5);
				onClick(panel_op_statistical);
				onLeaveClick(panel_op_courseistructor);
				onLeaveClick(panel_op_studentgrade);
				onLeaveClick(panel_op_department);
				onLeaveClick(panel_op_course);
			}
		});

		panel_op_course.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchToForm(1);
				onClick(panel_op_course);
				onLeaveClick(panel_op_courseistructor);
				onLeaveClick(panel_op_studentgrade);
				onLeaveClick(panel_op_department);
				onLeaveClick(panel_op_statistical);
			}
		});
		panel_op_course.setBackground(new Color(86, 65, 118));
		panel_op_course.setBounds(0, 160, 200, 50);
		panel.add(panel_op_course);
		panel_op_course.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Course");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(SystemColor.control);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_1.setBounds(0, 0, 200, 50);
		panel_op_course.add(lblNewLabel_1);

		panel_op_courseistructor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchToForm(2);
				onClick(panel_op_courseistructor);
				onLeaveClick(panel_op_course);
				onLeaveClick(panel_op_studentgrade);
				onLeaveClick(panel_op_department);
				onLeaveClick(panel_op_statistical);
			}
		});
		panel_op_courseistructor.setLayout(null);
		panel_op_courseistructor.setBackground(new Color(64,43,100));
		panel_op_courseistructor.setBounds(0, 215, 200, 50);
		panel.add(panel_op_courseistructor);

		JLabel lblNewLabel_2 = new JLabel("CourseInstructor");
		lblNewLabel_2.setForeground(SystemColor.control);
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 0, 200, 50);
		panel_op_courseistructor.add(lblNewLabel_2);

		panel_op_studentgrade.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchToForm(3);
				onClick(panel_op_studentgrade);
				onLeaveClick(panel_op_course);
				onLeaveClick(panel_op_courseistructor);
				onLeaveClick(panel_op_department);
				onLeaveClick(panel_op_statistical);
			}
		});
		panel_op_studentgrade.setLayout(null);
		panel_op_studentgrade.setBackground(new Color(64,43,100));
		panel_op_studentgrade.setBounds(0, 270, 200, 50);
		panel.add(panel_op_studentgrade);

		JLabel lblNewLabel_3 = new JLabel("StudenGrade");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(SystemColor.control);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_3.setBounds(0, 0, 200, 50);
		panel_op_studentgrade.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(MainForm.class.getResource("/images/icons8-grade-100.png")));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(0, 484, 200, 124);
		panel.add(lblNewLabel_4);
		
		
		panel_op_department.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchToForm(4);
				onClick(panel_op_department);
				onLeaveClick(panel_op_course);
				onLeaveClick(panel_op_courseistructor);
				onLeaveClick(panel_op_studentgrade);
				onLeaveClick(panel_op_statistical);
			}
		});
		panel_op_department.setLayout(null);
		panel_op_department.setBackground(new Color(64, 43, 100));
		panel_op_department.setBounds(0, 325, 200, 50);
		panel.add(panel_op_department);
		
		JLabel lblNewLabel_3_1 = new JLabel("Department");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setForeground(SystemColor.control);
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_3_1.setBounds(0, 0, 200, 50);
		panel_op_department.add(lblNewLabel_3_1);
		
		
		panel_op_statistical.setLayout(null);
		panel_op_statistical.setBackground(new Color(64, 43, 100));
		panel_op_statistical.setBounds(0, 380, 200, 50);
		panel.add(panel_op_statistical);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Statistical");
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setForeground(SystemColor.control);
		lblNewLabel_3_1_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel_3_1_1.setBounds(0, 0, 200, 50);
		panel_op_statistical.add(lblNewLabel_3_1_1);

		panel_main.setBackground(SystemColor.inactiveCaption);
		panel_main.setBounds(200, 0, 835, 645);
		contentPane.add(panel_main);
		panel_main.setLayout(new CardLayout(0, 0));
		
		
	}
}
