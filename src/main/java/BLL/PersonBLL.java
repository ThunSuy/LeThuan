package BLL;

import java.util.ArrayList;

import DAL.PersonDAL;

//UI chơi BLL
//BLL chơi DAL

public class PersonBLL {
	// tạo đối tượng DAL
	static PersonDAL personDAL = new PersonDAL();

	public static ArrayList<Person> getAllPerson() {
		return personDAL.getAllPerson();

	}

	public static ArrayList<Person> getAllPersonSX(int id, String tt, String sx, String last, String first) {
		return personDAL.getAllPersonSX(id, tt, sx, last, first);
	}

	public ArrayList<Person> getAllPersonById() {
		return personDAL.getAllPersonById();
	}

	public ArrayList<Person> getStudentByLastFirstName(String last, String first) {
		return personDAL.getStudentByLastFirstName(last, first);
	}

	public static ArrayList<Person> getAllPersonSxDate(String hire, String endate, String tt, String sx) {
		return personDAL.getAllPersonSxDate(hire, endate, tt, sx);
	}

	public static ArrayList<Person> getSxByFirstName(String sx) {
		return personDAL.getSxByFirstName(sx);
	}

	public static String delPerson(int id) {
		if (personDAL.delPerson(id)) {
			return "Xóa thành công";
		}
		return "Xóa thất bại";
	}

	public static String editPerson(Person p) {
		if (personDAL.editPerson(p)) {
			return "Cập nhật thành công";
		}
		return "Cập nhật thất bại";
	}

	public static String addPerson(Person p) {
		if (personDAL.addPerson(p)) {
			return "Thêm thành công";
		}
		return "Thêm thất bại";
	}

	public static ArrayList<Person> getPersonByName(int id, String last, String first) {
		return personDAL.getPersonByName(id, last, first);
	}

	public static ArrayList<Person> getPersonByDate(String hire, String enda) {
		return personDAL.getPersonByDate(hire, enda);
	}

	public ArrayList<Person> getDataByFullnameOr(String last, String first) {
		return personDAL.getDataByFullnameOr(last, first);
	}
}
