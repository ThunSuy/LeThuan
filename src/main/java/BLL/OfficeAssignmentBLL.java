package BLL;

import java.util.ArrayList;
import DAL.OfficeAssignmentDAL;

public class OfficeAssignmentBLL {
	OfficeAssignmentDAL officeDal = new OfficeAssignmentDAL();

	public ArrayList<OfficeAssignment> getAllOffice() {
		return officeDal.getAllOffice();
	}

	public ArrayList<OfficeAssignment> getAllOfficeSX(String tt, String sx) {
		return officeDal.getAllOfficeSX(tt, sx);
	}

	public ArrayList<OfficeAssignment> getAllOfficeSXByIDByLoca(String last, String first, int id, String loca,
			String tt, String sx) {
		return officeDal.getAllOfficeSXByIDByLoca(last, first, id, loca, tt, sx);
	}

	public ArrayList<OfficeAssignment> getAllOfficeSXByTime(String time, String tt, String sx) {
		return officeDal.getAllOfficeSXByTime(time, tt, sx);
	}

	public ArrayList<OfficeAssignment> getOfficeSXByTimestamp(String time) {
		return officeDal.getOfficeSXByTimestamp(time);
	}

	public ArrayList<OfficeAssignment> getOfficeSXByIdByLoca(int id, String loca) {
		return officeDal.getOfficeSXByIdByLoca(id, loca);
	}

	public String delOffice(int id) {
		if (officeDal.delOffice(id)) {
			return "Xóa thành công";
		}
		return "Xóa thất bại";
	}

	public String addOffice(OfficeAssignment p) {
		if (officeDal.addOffice(p)) {
			return "Thêm thành công";
		}
		return "Thêm thất bại";
	}

	public String editOffice(OfficeAssignment p) {
		if (officeDal.editOffice(p)) {
			return "Cập nhật thành công";
		}
		return "Cập nhật thất bại";
	}

	public String delInstructor(int id) {
		if (officeDal.delInstructor(id)) {
			return "Xóa thành công";
		}
		return "Xóa thất bại";
	}
}
