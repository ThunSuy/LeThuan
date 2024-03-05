package BLL;

import java.sql.Timestamp;
import java.util.ArrayList;


import DAL.DepartmentDAL;


public class DepartmentBLL {
	DepartmentDAL deparDAL = new DepartmentDAL();
	
	public ArrayList<Department> getAllDepartment(){
		return deparDAL.getAllDepartment();
	}
	
	public ArrayList<String> getAllName(){
		return deparDAL.getAllName();
	}
	
	public int getDeparId(){
		return deparDAL.getDeparId();
	}
	
	public String editDepartment(Department p) {
		if (deparDAL.editDepartment(p)) {
			return "Cập nhật thành công";
		}
		return "Cập nhật thất bại";
	}
	
	public String delDepartment(int id) {
		if (deparDAL.delDepartment(id)) {
			return "Xoá thành công";
		}
		return "Xoá thất bại";
	}
	
	public String addDepartment(Department p) {
		if (deparDAL.addDepartment(p)) {
			return "Thêm thành công";
		}
		return "Thêm thất bại";
	}
	public boolean getById(int c) {
		if (deparDAL.getById(c)) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Department> getDeparByName(String name){
		return deparDAL.getDeparByName(name);
	}
	public ArrayList<Department> getDeparByBudget(int id,double budget){
		return deparDAL.getDeparByBudget(id,budget);
	}
	public ArrayList<Department> getDeparByStart(String startdate){
		return deparDAL.getDeparByStart(startdate);
	}
	public ArrayList<Department> getDeparSx(int id,String name,double budget,String tt,String sx){
		return deparDAL.getDeparSx(id,name,budget, tt, sx);
	}
	public ArrayList<Department> getDeparSxByStart(String start,String tt,String sx){
		return deparDAL.getDeparSxByStart(start, tt, sx);
	}
	public ArrayList<String> getDeparByNamByTitle(String name,String title){
		return deparDAL.getDeparByNameByTitle(name,title);
	}
}
