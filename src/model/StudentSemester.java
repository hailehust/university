package model;

import java.util.ArrayList;

/*@author hai*/
/**
*desciption:
*/
public class StudentSemester {
/*=================== ATTRIBUTEs ==================================*/
	 private String maHocKyHeThong;
	 private String mssv;
	 
	 private ArrayList<String> maHocPhanDangKy;
	 private ArrayList<String> maLopMoDangKy;
/*=================== CONSTRUCTORs ================================*/
	 public StudentSemester() {
		  maHocPhanDangKy  = new ArrayList<>();
		  maLopMoDangKy  = new ArrayList<>();
	 }

	 public StudentSemester(String maHocKyHeThong,String mssv, ArrayList<String> maHocPhanTheoHoc, ArrayList<String> maLopMoTheoHoc) {
		  this.maHocKyHeThong = maHocKyHeThong;
		  this.mssv = mssv;
		  this.maHocPhanDangKy = maHocPhanTheoHoc;
		  this.maLopMoDangKy = maLopMoTheoHoc;
	 }


/*=================== GETTTERs & SETTERs  ============================*/


	 public String getSystemSemesterID() {
		  return maHocKyHeThong;
	 }

	 public void setMaHocKyHeThong(String maHocKyHeThong) {
		  this.maHocKyHeThong = maHocKyHeThong;
	 }

	 public ArrayList<String> getMaHocPhanDangKy() {
		  return maHocPhanDangKy;
	 }

	 public void setMaHocPhanDangKy(ArrayList<String> maHocPhanDangKy) {
		  this.maHocPhanDangKy = maHocPhanDangKy;
	 }

	 public ArrayList<String> getMaLopMoDangKy() {
		  return maLopMoDangKy;
	 }

	 public void setMaLopMoDangKy(ArrayList<String> maLopMoDangKy) {
		  this.maLopMoDangKy = maLopMoDangKy;
	 }



/*=================== OTHER METHODs  ===============================*/

	 public String getMssv() {
		  return mssv;
	 }

	 public void setMssv(String mssv) {
		  this.mssv = mssv;
	 }

}//class StudentSemester
