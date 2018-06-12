package model;

import java.util.ArrayList;

/*@author hai*/
/**
 * desciption:
 */
public class YearlyStudent extends Student {

    public YearlyStudent() {
    }

    public YearlyStudent(String mssv, String fullName, String username, String password, String trangThai, String bangTotNghiep, String studentClassID, String maLopChuongTrinhHoc, String maLopNganhHoc, ArrayList<CourseResult> bangDiem, ArrayList<StudentSemester> hocKySinhVien) {
        super(mssv, fullName, username, password, trangThai, bangTotNghiep, studentClassID, maLopChuongTrinhHoc, maLopNganhHoc, bangDiem, hocKySinhVien);
    }


    /*=================== ATTRIBUTEs ==================================*/

 /*=================== CONSTRUCTORs ================================*/
 /*=================== GETTTERs & SETTERs  ============================*/

 /*=================== OTHER METHODs  ===============================*/
}//class YearlyStudent
