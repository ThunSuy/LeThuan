package UI;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.SwingConstants;

import BLL.ChartBLL;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StatisticalForm extends JPanel {

	private static final long serialVersionUID = 1L;
	static JPanel panel_chart_main = new JPanel();
	static JLabel lbl_numInstructor = new JLabel("Instructor");
	static JLabel lbl_numStudent = new JLabel("Grade");
	static ChartBLL chartBLL = new ChartBLL();

	public void switchToForm(int n) {
		panel_chart_main.removeAll();
		if (n == 1) {
			panel_chart_main.add(new CourseChartForm());
		} else if (n == 2) {
			panel_chart_main.add(new InstructorChartForm());
		} else if (n == 3) {
			panel_chart_main.add(new StudentChartForm());
		}
		panel_chart_main.revalidate();
		panel_chart_main.repaint();
	}

	public void onClick(JPanel panel) {
		panel.setBackground(new Color(100, 65, 128));
	}

	public void onLeaveClick(JPanel panel) {
		panel.setBackground(new Color(64, 43, 100));
	}

	/**
	 * Create the panel.
	 */
	public StatisticalForm() {
		setPreferredSize(new Dimension(835, 645));
		setLayout(null);

		JPanel panel_course = new JPanel();
		JPanel panel_instructor = new JPanel();
		JPanel panel_student = new JPanel();

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 835, 35);
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblStatistical = new JLabel("STATISTICAL");
		lblStatistical.setForeground(SystemColor.textHighlightText);
		lblStatistical.setFont(new Font("Segoe UI", Font.BOLD, 18));
		panel.add(lblStatistical);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 70, 835, 100);
		add(panel_1);
		panel_1.setLayout(null);

		panel_course.setBackground(new Color(64, 43, 100));
		panel_course.setBounds(30, 10, 230, 80);
		panel_1.add(panel_course);
		panel_course.setLayout(null);
		onClick(panel_course);
		switchToForm(1);

		JLabel lblNewLabel_1 = new JLabel("Course");
		lblNewLabel_1.setForeground(SystemColor.textHighlightText);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNewLabel_1.setBounds(0, 20, 150, 40);
		panel_course.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setIcon(new ImageIcon(StatisticalForm.class.getResource("/images/icons8-course-50.png")));
		lblNewLabel_3.setBounds(150, 0, 80, 80);
		panel_course.add(lblNewLabel_3);

		panel_instructor.setLayout(null);
		panel_instructor.setBackground(new Color(64, 43, 100));
		panel_instructor.setBounds(302, 10, 230, 80);
		panel_1.add(panel_instructor);

		JLabel lblNewLabel_1_1 = new JLabel("Course");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(SystemColor.textHighlightText);
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(0, 5, 150, 40);
		panel_instructor.add(lblNewLabel_1_1);

		lbl_numInstructor.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_numInstructor.setForeground(SystemColor.textHighlightText);
		lbl_numInstructor.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lbl_numInstructor.setBounds(0, 35, 150, 40);
		panel_instructor.add(lbl_numInstructor);

		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1.setIcon(new ImageIcon(StatisticalForm.class.getResource("/images/icons8-training-50.png")));
		lblNewLabel_3_1.setBounds(150, 0, 80, 80);
		panel_instructor.add(lblNewLabel_3_1);

		panel_student.setLayout(null);
		panel_student.setBackground(new Color(64, 43, 100));
		panel_student.setBounds(575, 10, 230, 80);
		panel_1.add(panel_student);

		JLabel lblNewLabel_1_2 = new JLabel("Student");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setForeground(SystemColor.textHighlightText);
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNewLabel_1_2.setBounds(0, 5, 150, 40);
		panel_student.add(lblNewLabel_1_2);

		lbl_numStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_numStudent.setForeground(SystemColor.textHighlightText);
		lbl_numStudent.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lbl_numStudent.setBounds(0, 35, 150, 40);
		panel_student.add(lbl_numStudent);

		JLabel lblNewLabel_3_2 = new JLabel("");
		lblNewLabel_3_2.setIcon(new ImageIcon(StatisticalForm.class.getResource("/images/icons8-grade-48.png")));
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_2.setBounds(150, 0, 80, 80);
		panel_student.add(lblNewLabel_3_2);

		JLabel lblNewLabel = new JLabel("Overview");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel.setBounds(30, 35, 201, 35);
		add(lblNewLabel);

		JLabel lblChart = new JLabel("Chart");
		lblChart.setForeground(new Color(0, 0, 128));
		lblChart.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblChart.setBounds(30, 180, 201, 35);
		add(lblChart);

		panel_chart_main.setBackground(new Color(240, 240, 240));
		panel_chart_main.setForeground(SystemColor.activeCaption);
		panel_chart_main.setBounds(0, 215, 835, 430);
		add(panel_chart_main);

		panel_course.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchToForm(1);
				onClick(panel_course);
				onLeaveClick(panel_instructor);
				onLeaveClick(panel_student);
			}
		});
		panel_instructor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchToForm(2);
				onClick(panel_instructor);
				onLeaveClick(panel_course);
				onLeaveClick(panel_student);
			}
		});
		panel_student.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchToForm(3);
				onClick(panel_student);
				onLeaveClick(panel_instructor);
				onLeaveClick(panel_course);
			}
		});

	}

}
