package model;

import java.util.ArrayList;

/*@author hai*/
/**
 * desciption:
 */
public class StudentClass {

	 /*=================== ATTRIBUTEs ==================================*/
	 private String studentClassID;
	 private int namNhapHoc;
	 private String programID;
	 
	 private int soLuongSinhVienToiDa;
	 private ArrayList<Student> sinhVien;

	 /*=================== CONSTRUCTORs ================================*/
	 public StudentClass() {
		  sinhVien = new ArrayList<>();
	 }

	 public StudentClass(String studentClassID, int namNhapHoc, int soLuongSinhVienToiDa, ArrayList<Student> sinhVien, String maChuongTrinhHo) {
		  this.studentClassID = studentClassID;
		  this.namNhapHoc = namNhapHoc;
		  this.sinhVien = sinhVien;
		  this.programID = programID;
		  this.soLuongSinhVienToiDa = soLuongSinhVienToiDa;
	 }



	 /*=================== GETTTERs & SETTERs  ============================*/

	 public String getMaLopSinhVien() {
		  return studentClassID;
	 }

	 public void setMaLopSinhVien(String studentClassID) {
		  this.studentClassID = studentClassID;
	 }

	 public int getNamNhapHoc() {
		  return namNhapHoc;
	 }

	 public void setNamNhapHoc(int namNhapHoc) {
		  this.namNhapHoc = namNhapHoc;
	 }

	 public ArrayList<Student> getSinhVien() {
		  return sinhVien;
	 }

	 public void setSinhVien(ArrayList<Student> sinhVien) {
		  this.sinhVien = sinhVien;
	 }

	 /*=================== OTHER METHODs  ===============================*/

	 public String getMaChuongTrinhHoc() {
		  return programID;
	 }

	 public void setMaChuongTrinhHoc(String programID) {
		  this.programID = programID;
	 }

	 public int getSoLuongSinhVienToiDa() {
		  return soLuongSinhVienToiDa;
	 }

	 public void setSoLuongSinhVienToiDa(int soLuongSinhVienToiDa) {
		  this.soLuongSinhVienToiDa = soLuongSinhVienToiDa;
	 }


}//class StudentClass
