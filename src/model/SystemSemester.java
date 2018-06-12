package model;

import java.sql.Date;
import java.util.ArrayList;

/*@author hai*/
/**
*desciption:
*/
public class SystemSemester {
/*=================== ATTRIBUTEs ==================================*/
	 private String maHocKyHeThong;
	 private	Date batDauDangKyHocPhan;
	 private	Date batDauDangKyLopMo;
	 private	Date batDauHocKy;
	 private	Date ketThucHocKy;
	 
	 private ArrayList<CourseRegistration> dangKyHocPhan;
	 private ArrayList<CourseClass> lopMo;
/*=================== CONSTRUCTORs ================================*/
	 public SystemSemester() {
		  dangKyHocPhan = new ArrayList<>();
		  lopMo = new ArrayList<>();
	 }

	 public SystemSemester(String maHocKyHeThong, Date batDauDangKyHocPhan, Date batDauDangKyLopMo, Date batDauHocKy, Date ketThucHocKy, ArrayList<CourseRegistration> dangKyHocPhan, ArrayList<CourseClass> lopMo) {
		  this.maHocKyHeThong = maHocKyHeThong;
		  this.batDauDangKyHocPhan = batDauDangKyHocPhan;
		  this.batDauDangKyLopMo = batDauDangKyLopMo;
		  this.batDauHocKy = batDauHocKy;
		  this.ketThucHocKy = ketThucHocKy;
		  this.dangKyHocPhan = dangKyHocPhan;
		  this.lopMo = lopMo;
	 }

/*=================== GETTTERs & SETTERs  ============================*/

/*=================== OTHER METHODs  ===============================*/


	 public String getSystemSemesterID() {
		  return maHocKyHeThong;
	 }

	 public void setMaHocKyHeThong(String maHocKyHeThong) {
		  this.maHocKyHeThong = maHocKyHeThong;
	 }

	 public Date getBatDauDangKyHocPhan() {
		  return batDauDangKyHocPhan;
	 }

	 public void setBatDauDangKyHocPhan(Date batDauDangKyHocPhan) {
		  this.batDauDangKyHocPhan = batDauDangKyHocPhan;
	 }

	 public Date getBatDauDangKyLopMo() {
		  return batDauDangKyLopMo;
	 }

	 public void setBatDauDangKyLopMo(Date batDauDangKyLopMo) {
		  this.batDauDangKyLopMo = batDauDangKyLopMo;
	 }

	 public Date getBatDauHocKy() {
		  return batDauHocKy;
	 }

	 public void setBatDauHocKy(Date batDauHocKy) {
		  this.batDauHocKy = batDauHocKy;
	 }

	 public Date getKetThucHocKy() {
		  return ketThucHocKy;
	 }

	 public void setKetThucHocKy(Date ketThucHocKy) {
		  this.ketThucHocKy = ketThucHocKy;
	 }

	 public ArrayList<CourseRegistration> getDangKyHocPhan() {
		  return dangKyHocPhan;
	 }

	 public void setDangKyHocPhan(ArrayList<CourseRegistration> dangKyHocPhan) {
		  this.dangKyHocPhan = dangKyHocPhan;
	 }

	 public ArrayList<CourseClass> getLopMo() {
		  return lopMo;
	 }

	 public void setLopMo(ArrayList<CourseClass> lopMo) {
		  this.lopMo = lopMo;
	 }

}//class SystemSemester
