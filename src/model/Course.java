package model;

import java.util.ArrayList;

/*@author hai*/
/**
 * desciption:
 */
public class Course {

	 /*=================== ATTRIBUTEs ==================================*/
	 private String maHocPhan;
	 private String tenHocPhan;
	 private float trongSoGiuaKy;
	 private float trongSoCuoiKy;
	 
	 private ArrayList<String> programID;

	 /*=================== CONSTRUCTORs ================================*/

	 public Course() {
		  programID = new ArrayList<>();
	 }

	 public Course(String maHocPhan, String tenHocPhan, float trongSoGiuaKy, float trongSoCuoiKy, ArrayList<String> programID) {
		  this.maHocPhan = maHocPhan;
		  this.tenHocPhan = tenHocPhan;
		  this.trongSoGiuaKy = trongSoGiuaKy;
		  this.trongSoCuoiKy = trongSoCuoiKy;
		  this.programID = programID;
	 }


	 /*=================== GETTTERs & SETTERs  ============================*/

	 public String getMaHocPhan() {
		  return maHocPhan;
	 }

	 public void setMaHocPhan(String maHocPhan) {
		  this.maHocPhan = maHocPhan;
	 }

	 public String getTenHocPhan() {
		  return tenHocPhan;
	 }

	 public void setTenHocPhan(String tenHocPhan) {
		  this.tenHocPhan = tenHocPhan;
	 }

	 public float getTrongSoGiuaKy() {
		  return trongSoGiuaKy;
	 }

	 public void setTrongSoGiuaKy(float trongSoGiuaKy) {
		  this.trongSoGiuaKy = trongSoGiuaKy;
	 }

	 public float getTrongSoCuoiKy() {
		  return trongSoCuoiKy;
	 }

	 public void setTrongSoCuoiKy(float trongSoCuoiKy) {
		  this.trongSoCuoiKy = trongSoCuoiKy;
	 }

	 /*=================== OTHER METHODs  ===============================*/

	 public ArrayList<String> getMaChuongTrinhHoc() {
		  return programID;
	 }

	 public void setMaChuongTrinhHoc(ArrayList<String> programID) {
		  this.programID = programID;
	 }
}//class Course
