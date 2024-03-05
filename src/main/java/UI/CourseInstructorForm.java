package UI;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class CourseInstructorForm extends JPanel {

	private static final long serialVersionUID = 1L;
	static JPanel panel_temp = new JPanel();

	public void switchToForm(int n) {
		panel_temp.removeAll();
		if (n == 1) {
			panel_temp.add(new OfficeAssignmentForm());
		} else if (n == 2) {
			panel_temp.add(new InstructorForm());
		} else if (n == 3) {
			panel_temp.add(new PersonForm());
		}
		panel_temp.revalidate();
		panel_temp.repaint();
	}

	public void onClick(JMenu menu) {
		menu.setForeground(SystemColor.activeCaption);
	}

	public void onLeaveClick(JMenu menu) {
		menu.setForeground(Color.BLACK);
	}

	public CourseInstructorForm() {
		setPreferredSize(new Dimension(835, 645));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 835, 35);
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblCourseInstructor = new JLabel("COURSE INSTRUCTOR");
		lblCourseInstructor.setForeground(SystemColor.textHighlightText);
		lblCourseInstructor.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel.add(lblCourseInstructor);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 35, 835, 30);
		add(menuBar);

		JMenu mn_officeassignment = new JMenu("OfficeAssignment");
		JMenu mn_courseinstructor = new JMenu("CourseInstructor");
		JMenu mn_person = new JMenu("Person");
		mn_officeassignment.setForeground(SystemColor.activeCaption);

		mn_officeassignment.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchToForm(1);
				onClick(mn_officeassignment);
				onLeaveClick(mn_courseinstructor);
				onLeaveClick(mn_person);
			}
		});
		mn_officeassignment.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mn_officeassignment);

		mn_courseinstructor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchToForm(2);
				onClick(mn_courseinstructor);
				onLeaveClick(mn_officeassignment);
				onLeaveClick(mn_person);
			}
		});
		mn_courseinstructor.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mn_courseinstructor);

		mn_person.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchToForm(3);
				onClick(mn_person);
				onLeaveClick(mn_officeassignment);
				onLeaveClick(mn_courseinstructor);
			}
		});
		mn_person.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mn_person);

		switchToForm(1);
		panel_temp.setBackground(UIManager.getColor("Button.disabledShadow"));
		panel_temp.setBounds(0, 65, 835, 580);
		add(panel_temp);
	}
}
