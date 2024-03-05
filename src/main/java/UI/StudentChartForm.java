package UI;

import javax.swing.JPanel;

import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import BLL.ChartBLL;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class StudentChartForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JComboBox comboBox = new JComboBox();
	static ChartBLL chartBLL = new ChartBLL();
	static PieChart pieChart = new PieChart();

	static JLabel lbl_gA = new JLabel("13");
	static JLabel lbl_gB = new JLabel("4");
	static JLabel lbl_gC = new JLabel("9");
	static JLabel lbl_gD = new JLabel("9");
	static JLabel lbl_gF = new JLabel("0");

	static int numGA = 0;
	static int numGB = 0;
	static int numGC = 0;
	static int numGD = 0;
	static int numGF = 0;

	public static void loadDataLbl() {
		lbl_gA.setText(numGA + "");
		lbl_gB.setText(numGB + "");
		lbl_gC.setText(numGC + "");
		lbl_gD.setText(numGD + "");
		lbl_gF.setText(numGF + "");
	}

	public static void loadDataCbb() {
		comboBox.removeAllItems();
		for (String d : chartBLL.getUniqueCourseIDs()) {
			comboBox.addItem(d);
		}
		comboBox.setSelectedIndex(0);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickCbb();
			}
		});

	}

	public static void clickCbb() {
		String selectedOption = (String) comboBox.getSelectedItem();
		if (selectedOption != null) {
			int courseID = Integer.parseInt(selectedOption.split(" - ")[0]);
			loadNumGradeAndDrawChart(courseID);
		}
	}

	public static void loadNumGradeAndDrawChart(int courseID) {
		ArrayList<BigDecimal> grades = chartBLL.getStudentIdByCourseID(courseID);
		resetCount();
		for (BigDecimal grade : grades) {
			if (grade != null) {
				if (grade != null) {
					double gradeValue = grade.doubleValue();
					if (gradeValue <= 4.00 && gradeValue >= 3.20) {
						numGA++;
					} else if (gradeValue <= 3.19 && gradeValue >= 2.40) {
						numGB++;
					} else if (gradeValue <= 2.39 && gradeValue >= 1.60) {
						numGC++;
					} else if (gradeValue <= 1.59 && gradeValue >= 0.80) {
						numGD++;
					} else if (gradeValue < 0.80) {
						numGF++;
					}
				}
			}
		}
		loadChart();
		loadDataLbl();
	}

	public static void loadChart() {
		pieChart.clearData();

		ModelPieChart gradeA = new ModelPieChart("Grade A", numGA, new Color(104, 03, 100));
		ModelPieChart gradeB = new ModelPieChart("Grade B", numGB, new Color(64, 43, 100));
		ModelPieChart gradeC = new ModelPieChart("Grade C", numGC, new Color(24, 63, 100));
		ModelPieChart gradeD = new ModelPieChart("Grade D", numGD, new Color(04, 83, 100));
		ModelPieChart gradeF = new ModelPieChart("Grade F", numGF, new Color(84, 23, 100));

		pieChart.addData(gradeA);
		pieChart.addData(gradeB);
		pieChart.addData(gradeC);
		pieChart.addData(gradeD);
		pieChart.addData(gradeF);

		pieChart.repaint();
	}

	public static void resetCount() {
		numGA = 0;
		numGB = 0;
		numGC = 0;
		numGD = 0;
		numGF = 0;
	}

	/**
	 * Create the panel.
	 */
	public StudentChartForm() {
		setPreferredSize(new Dimension(835, 430));
		setLayout(null);

		pieChart.setPreferredSize(new Dimension(350, 350));

		JPanel panel = new JPanel();
		panel.setBounds(45, 20, 350, 350);
		add(panel);

		pieChart.setBounds(0, 0, 350, 350);
		panel.add(pieChart);

		JLabel lbl = new JLabel("Grade A (3.20 - 4.00)");
		lbl.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbl.setBounds(497, 112, 180, 30);
		add(lbl);

		lbl_gA.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbl_gA.setBounds(676, 112, 82, 30);
		add(lbl_gA);

		JLabel lblNumberOfOnlinecourse = new JLabel("Grade B (2.40 - 3.19)");
		lblNumberOfOnlinecourse.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNumberOfOnlinecourse.setBounds(497, 162, 180, 30);
		add(lblNumberOfOnlinecourse);

		lbl_gB.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbl_gB.setBounds(676, 162, 82, 30);
		add(lbl_gB);

		JLabel lbl1 = new JLabel("Grade C (1.60 - 2.39)");
		lbl1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lbl1.setBounds(497, 212, 180, 30);
		add(lbl1);

		lbl_gC.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbl_gC.setBounds(676, 212, 82, 30);
		add(lbl_gC);

		lbl_gD.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbl_gD.setBounds(676, 262, 82, 30);
		add(lbl_gD);

		JLabel lblNumberOfInstructor = new JLabel("Grade D (0.80 - 1.60)");
		lblNumberOfInstructor.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNumberOfInstructor.setBounds(497, 262, 180, 30);
		add(lblNumberOfInstructor);

		JLabel lblAssignedInstructor = new JLabel("Grade F (<0.80)");
		lblAssignedInstructor.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblAssignedInstructor.setBounds(497, 312, 180, 30);
		add(lblAssignedInstructor);

		lbl_gF.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lbl_gF.setBounds(676, 312, 82, 30);
		add(lbl_gF);

		JLabel lblCourse = new JLabel("Course");
		lblCourse.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCourse.setBounds(497, 20, 59, 30);
		add(lblCourse);

		comboBox.setBounds(567, 20, 180, 30);
		add(comboBox);

		JLabel lblGradeb = new JLabel("GradeB");
		lblGradeb.setHorizontalAlignment(SwingConstants.CENTER);
		lblGradeb.setForeground(new Color(64, 43, 100));
		lblGradeb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGradeb.setBounds(129, 372, 52, 20);
		add(lblGradeb);

		JLabel lblGradec = new JLabel("GradeC");
		lblGradec.setHorizontalAlignment(SwingConstants.CENTER);
		lblGradec.setForeground(new Color(24, 63, 100));
		lblGradec.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGradec.setBounds(191, 372, 52, 20);
		add(lblGradec);

		JLabel lblGraded = new JLabel("GradeD");
		lblGraded.setHorizontalAlignment(SwingConstants.CENTER);
		lblGraded.setForeground(new Color(04, 83, 100));
		lblGraded.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGraded.setBounds(253, 372, 52, 20);
		add(lblGraded);

		JLabel lblGradef = new JLabel("GradeF");
		lblGradef.setHorizontalAlignment(SwingConstants.CENTER);
		lblGradef.setForeground(new Color(84, 23, 100));
		lblGradef.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGradef.setBounds(315, 372, 52, 20);
		add(lblGradef);

		JLabel lblNewLabel = new JLabel("GradeA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(104, 03, 100));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(71, 372, 52, 20);
		add(lblNewLabel);

		loadDataCbb();

		String selectedOption = comboBox.getItemAt(0).toString();
		if (selectedOption != null) {
			int courseID = Integer.parseInt(selectedOption.split(" - ")[0]);
			loadNumGradeAndDrawChart(courseID);
		}
	}
}
