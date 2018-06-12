package model;

import java.util.ArrayList;

/*@author hai*/
/**
 * desciption:
 */
public class Student extends User{

	 /*=================== ATTRIBUTEs ==================================*/
	 private String mssv;
	 private String fullName;
	 private String username;
	 private String password;
	 private String trangThai;
	 private String bangTotNghiep;

	 private String studentClassID;
	 private String programID;
	 private String schoolID;

	 private ArrayList<CourseResult> bangDiem;
	 private ArrayList<StudentSemester> hocKySinhVien;

	 /*=================== CONSTRUCTORs ================================*/
	 public Student() {
		  bangDiem = new ArrayList<>();
		  hocKySinhVien = new ArrayList();
	 }

	 public Student(String mssv, String fullName, String username, String password, String trangThai, String bangTotNghiep, String studentClassID, String programID, String schoolID, ArrayList<CourseResult> bangDiem, ArrayList<StudentSemester> hocKySinhVien) {
		  this.mssv = mssv;
		  this.fullName = fullName;
		  this.username = username;
		  this.password = password;
		  this.trangThai = trangThai;
		  this.bangTotNghiep = bangTotNghiep;
		  this.studentClassID = studentClassID;
		  this.programID = programID;
		  this.schoolID = schoolID;
		  this.bangDiem = bangDiem;
		  this.hocKySinhVien = hocKySinhVien;
	 }



	 /*=================== GETTTERs & SETTERs  ============================*/
	 public String getMssv() {
		  return mssv;
	 }

	 public void setMssv(String mssv) {
		  this.mssv = mssv;
	 }

	 public String getHoTen() {
		  return fullName;
	 }

	 public void setHoTen(String fullName) {
		  this.fullName = fullName;
	 }

	 public String getUsername() {
		  return username;
	 }

	 public void setUsername(String username) {
		  this.username = username;
	 }

	 public String getPassword() {
		  return password;
	 }

	 public void setPassword(String password) {
		  this.password = password;
	 }

	 public String getTrangThai() {
		  return trangThai;
	 }

	 public void setTrangThai(String trangThai) {
		  this.trangThai = trangThai;
	 }

	 public String getBangTotNghiep() {
		  return bangTotNghiep;
	 }

	 public void setBangTotNghiep(String bangTotNghiep) {
		  this.bangTotNghiep = bangTotNghiep;
	 }

	 public ArrayList<CourseResult> getBangDiem() {
		  return bangDiem;
	 }

	 public void setBangDiem(ArrayList<CourseResult> bangDiem) {
		  this.bangDiem = bangDiem;
	 }

	 public ArrayList<StudentSemester> getHocKySinhVien() {
		  return hocKySinhVien;
	 }

	 public void setHocKySinhVien(ArrayList<StudentSemester> hocKySinhVien) {
		  this.hocKySinhVien = hocKySinhVien;
	 }

	 public String getMaLopSinhVien() {
		  return studentClassID;
	 }

	 public void setMaLopSinhVien(String studentClassID) {
		  this.studentClassID = studentClassID;
	 }

	 /*=================== OTHER METHODs  ===============================*/

	 public String getMaChuongTrinhHoc() {
		  return programID;
	 }

	 public void setMaChuongTrinhHoc(String programID) {
		  this.programID = programID;
	 }

	 public String getMaNganhHoc() {
		  return schoolID;
	 }

	 public void setMaNganhHoc(String schoolID) {
		  this.schoolID = schoolID;
	 }

}//class Student
