package UI;

import javax.swing.JPanel;

import BLL.ChartBLL;

import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class InstructorChartForm extends JPanel {

	private static final long serialVersionUID = 1L;
	static ChartBLL chartBLL = new ChartBLL();

	static JLabel lbl_numCourse = new JLabel("13");
	static JLabel lbl_notAssignedCourse = new JLabel("6");
	static JLabel lbl_assignedCourse = new JLabel("7");
	static JLabel lbl_numIntructor = new JLabel("13");
	static JLabel lbl_notAssignedInstructor = new JLabel("6");
	static JLabel lbl_assignedInstructor = new JLabel("7");

	static int numCourse = chartBLL.getNumCourse();
	static int numCourseAssigned = chartBLL.getNumCourseInstructor();
	static int numNotAssignedCourse = numCourse - numCourseAssigned;

	static int numInstructor = chartBLL.getNumInstructor();
	static int numNotAssignedInstructor = chartBLL.getUnassignedInstructorsCount();
	static int numCourseInstructor = numCourseAssigned - numNotAssignedInstructor;

	public static void loadDataLbl() {
		lbl_numCourse.setText(numCourse + "");
		lbl_notAssignedCourse.setText(numNotAssignedCourse + "");
		lbl_assignedCourse.setText(numCourseAssigned + "");

		lbl_numIntructor.setText(numInstructor + "");
		lbl_notAssignedInstructor.setText(numNotAssignedInstructor + "");
		lbl_assignedInstructor.setText(numCourseInstructor + "");
	}

	public static void loadNumData() {
		numCourse = chartBLL.getNumCourse();
		numCourseAssigned = chartBLL.getNumCourseInstructor();
		numNotAssignedCourse = numCourse - numCourseAssigned;

		numInstructor = chartBLL.getNumInstructor();
		numNotAssignedInstructor = chartBLL.getUnassignedInstructorsCount();
		numCourseInstructor = numCourseAssigned - numNotAssignedInstructor;
	}

	/**
	 * Create the panel.
	 */
	public InstructorChartForm() {
		setPreferredSize(new Dimension(835, 430));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(45, 20, 350, 350);
		add(panel);

		PieChart pieChart = new PieChart();
		pieChart.setPreferredSize(new Dimension(350, 350));

		// Tạo dữ liệu và thêm vào biểu đồ
		ModelPieChart onsiteData = new ModelPieChart("Course assigned", numCourseAssigned, SystemColor.activeCaption);
		ModelPieChart onlineData = new ModelPieChart("Not Assigned course", numNotAssignedCourse,
				new Color(64, 43, 100));
		pieChart.addData(onsiteData);
		pieChart.addData(onlineData);

		// Đặt vị trí và kích thước của biểu đồ trong JPanel
		pieChart.setBounds(0, 0, 350, 350);
		panel.add(pieChart);

		JLabel lbl = new JLabel("Course");
		lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbl.setBounds(487, 45, 180, 30);
		add(lbl);

		JLabel lblNumberOfOnlinecourse = new JLabel("Not Assigned Course");
		lblNumberOfOnlinecourse.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNumberOfOnlinecourse.setBounds(487, 95, 180, 30);
		add(lblNumberOfOnlinecourse);

		JLabel lbl1 = new JLabel("Assigned Course");
		lbl1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbl1.setBounds(487, 145, 180, 30);
		add(lbl1);

		lbl_numCourse.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbl_numCourse.setBounds(666, 45, 82, 30);
		add(lbl_numCourse);

		lbl_notAssignedCourse.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbl_notAssignedCourse.setBounds(666, 95, 82, 30);
		add(lbl_notAssignedCourse);

		lbl_assignedCourse.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbl_assignedCourse.setBounds(666, 145, 82, 30);
		add(lbl_assignedCourse);

		JLabel lblNewLabel = new JLabel("Not Assigned Course");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(64, 43, 100));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(63, 370, 150, 20);
		add(lblNewLabel);

		JLabel lblOnsite = new JLabel("Assigned Course");
		lblOnsite.setHorizontalAlignment(SwingConstants.CENTER);
		lblOnsite.setForeground(SystemColor.activeCaption);
		lblOnsite.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOnsite.setBounds(223, 370, 172, 20);
		add(lblOnsite);

		JSeparator separator = new JSeparator();
		separator.setBounds(480, 195, 283, 2);
		add(separator);

		JLabel lblNumberOfInstructor = new JLabel("Number of Instructor");
		lblNumberOfInstructor.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNumberOfInstructor.setBounds(487, 218, 180, 30);
		add(lblNumberOfInstructor);

		JLabel lblAssignedInstructor = new JLabel("Not Assigned Instructor");
		lblAssignedInstructor.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblAssignedInstructor.setBounds(487, 268, 180, 30);
		add(lblAssignedInstructor);

		JLabel lblAssignedInstructor_1 = new JLabel("Assigned Instructor");
		lblAssignedInstructor_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblAssignedInstructor_1.setBounds(487, 318, 180, 30);
		add(lblAssignedInstructor_1);

		lbl_numIntructor.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbl_numIntructor.setBounds(666, 218, 82, 30);
		add(lbl_numIntructor);

		lbl_notAssignedInstructor.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbl_notAssignedInstructor.setBounds(666, 268, 82, 30);
		add(lbl_notAssignedInstructor);

		lbl_assignedInstructor.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbl_assignedInstructor.setBounds(666, 318, 82, 30);
		add(lbl_assignedInstructor);

		loadNumData();
		loadDataLbl();
	}
}
