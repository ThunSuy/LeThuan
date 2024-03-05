package UI;

import javax.swing.JPanel;

import BLL.ChartBLL;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JSeparator;

public class CourseChartForm extends JPanel {

	private static final long serialVersionUID = 1L;
	static JLabel lbl_numCourse = new JLabel("New label");
	static JLabel lbl_numOnlineCourse = new JLabel("New label");
	static JLabel lbl_numOnsiteCourse = new JLabel("New label");
	static ChartBLL chartBLL = new ChartBLL();
	static int numCourse = chartBLL.getNumCourse();
	static int numOnlineCourse = chartBLL.getNumOnlineCourse();
	static int numOnsiteCourse = chartBLL.getNumOnsiteCourse();

	public static void loadDataLbl() {
		lbl_numCourse.setText(numCourse+"");
		lbl_numOnlineCourse.setText(numOnlineCourse+"");
		lbl_numOnsiteCourse.setText(numOnsiteCourse+"");
	}
	
	public static void loadNumCourse() {
		numCourse = chartBLL.getNumCourse();
		numOnlineCourse = chartBLL.getNumOnlineCourse();
		numOnsiteCourse = chartBLL.getNumOnsiteCourse();
	}

	/**
	 * Create the panel.
	 */
	public CourseChartForm() {
		setPreferredSize(new Dimension(835, 430));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(45, 20, 350, 350);
		add(panel);

		PieChart pieChart = new PieChart();
		pieChart.setPreferredSize(new Dimension(350, 350));

		ModelPieChart onsiteData = new ModelPieChart("Onsite", numOnsiteCourse, SystemColor.activeCaption);
		ModelPieChart onlineData = new ModelPieChart("Online", numOnlineCourse, new Color(64, 43, 100));
		pieChart.addData(onsiteData);
		pieChart.addData(onlineData);

		pieChart.setBounds(0, 0, 350, 350);
		panel.add(pieChart);

		JLabel lbl = new JLabel("Number of Course");
		lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbl.setBounds(479, 108, 180, 30);
		add(lbl);

		JLabel lblNumberOfOnlinecourse = new JLabel("Number of OnlineCourse");
		lblNumberOfOnlinecourse.setForeground(Color.BLACK);
		lblNumberOfOnlinecourse.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNumberOfOnlinecourse.setBounds(479, 167, 180, 30);
		add(lblNumberOfOnlinecourse);

		JLabel lblNumberOfOnsitecourse = new JLabel("Number of OnsiteCourse");
		lblNumberOfOnsitecourse.setForeground(Color.BLACK);
		lblNumberOfOnsitecourse.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNumberOfOnsitecourse.setBounds(479, 227, 180, 30);
		add(lblNumberOfOnsitecourse);

		lbl_numOnsiteCourse.setForeground(Color.BLACK);
		lbl_numOnlineCourse.setForeground(Color.BLACK);
		
		lbl_numCourse.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbl_numCourse.setBounds(666, 108, 82, 30);
		add(lbl_numCourse);

		lbl_numOnlineCourse.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbl_numOnlineCourse.setBounds(666, 167, 82, 30);
		add(lbl_numOnlineCourse);

		lbl_numOnsiteCourse.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbl_numOnsiteCourse.setBounds(666, 227, 82, 30);
		add(lbl_numOnsiteCourse);
		
		JLabel lblNewLabel = new JLabel("Online");
		lblNewLabel.setForeground(new Color(64, 43, 100));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(130, 370, 50, 20);
		add(lblNewLabel);
		
		JLabel lblOnsite = new JLabel("Onsite");
		lblOnsite.setForeground(SystemColor.activeCaption);
		lblOnsite.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOnsite.setBounds(240, 370, 50, 20);
		add(lblOnsite);

		loadNumCourse();
		loadDataLbl();
	}
}
