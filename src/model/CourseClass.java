package model;

import java.util.ArrayList;

/*@author hai*/
/**
 * desciption:
 */
public class CourseClass {

	 /*=================== ATTRIBUTEs ==================================*/
	 private String maLopMo;
	 private String maHocPhan;
	 private String maHocKyHeThong;
	 private int soLuongSinhVienToiDa;
	 
	 private ArrayList<Student> sinhVien;
	 

	 /*=================== CONSTRUCTORs ================================*/
	 public CourseClass() {
		  sinhVien = new ArrayList<>();
	 }

	 public CourseClass(String maLopMo, String maHocPhan, String maHocKyHeThong, int soLuongSinhVienToiDa, ArrayList<Student> sinhVien) {
		  this.maLopMo = maLopMo;
		  this.maHocPhan = maHocPhan;
		  this.maHocKyHeThong = maHocKyHeThong;
		  this.soLuongSinhVienToiDa = soLuongSinhVienToiDa;
		  this.sinhVien = sinhVien;
	 }

	 /*=================== GETTTERs & SETTERs  ============================*/
	 public String getMaLopMo() {
		  return maLopMo;
	 }

	 public void setMaLopMo(String maLopMo) {
		  this.maLopMo = maLopMo;
	 }

	 public String getMaHocPhan() {
		  return maHocPhan;
	 }

	 public void setMaHocPhan(String maHocPhan) {
		  this.maHocPhan = maHocPhan;
	 }

	 public int getSoLuongSinhVienToiDa() {
		  return soLuongSinhVienToiDa;
	 }

	 public void setSoLuongSinhVienToiDa(int soLuongSinhVienToiDa) {
		  this.soLuongSinhVienToiDa = soLuongSinhVienToiDa;
	 }


	 public ArrayList<Student> getSinhVien() {
		  return sinhVien;
	 }

	 public void setSinhVien(ArrayList<Student> sinhVien) {
		  this.sinhVien = sinhVien;
	 }

	 /*=================== OTHER METHODs  ===============================*/

	 public String getSystemSemesterID() {
		  return maHocKyHeThong;
	 }

	 public void setMaHocKyHeThong(String maHocKyHeThong) {
		  this.maHocKyHeThong = maHocKyHeThong;
	 }
}//class CourseClass
