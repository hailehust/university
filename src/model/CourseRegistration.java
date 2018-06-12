package model;

import java.util.ArrayList;

/*@author hai*/
/**
 * desciption:
 */
public class CourseRegistration {

	 /*=================== ATTRIBUTEs ==================================*/
	 private String maHocPhan;
	 private String maHocKyHeThong;

	 private ArrayList<String> mssvDangKy;

	 /*=================== CONSTRUCTORs ================================*/
	 public CourseRegistration() {
		  mssvDangKy = new ArrayList<>();
	 }

	 public CourseRegistration(String maHocPhan, String maHocKyHeThong, ArrayList<String> mssvDangKy) {
		  this.maHocPhan = maHocPhan;
		  this.maHocKyHeThong = maHocKyHeThong;
		  this.mssvDangKy = mssvDangKy;
	 }


	 /*=================== GETTTERs & SETTERs  ============================*/
	 public String getMaHocPhan() {
		  return maHocPhan;
	 }

	 public void setMaHocPhan(String maHocPhan) {
		  this.maHocPhan = maHocPhan;
	 }

	 public ArrayList<String> getMssvDangKy() {
		  return mssvDangKy;
	 }

	 public void setMssvDangKy(ArrayList<String> mssvDangKy) {
		  this.mssvDangKy = mssvDangKy;
	 }

	 public String getSystemSemesterID() {
		  return maHocKyHeThong;
	 }

	 public void setMaHocKyHeThong(String maHocKyHeThong) {
		  this.maHocKyHeThong = maHocKyHeThong;
	 }

	 /*=================== OTHER METHODs  ===============================*/
}//class CourseRegistration
