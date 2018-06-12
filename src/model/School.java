package model;

import java.util.ArrayList;

/*@author hai*/
/**
 * desciption:
 */
public class School {

	 /*=================== ATTRIBUTEs ==================================*/
	 private String schoolID;
	 private String tenNganhHoc;
	 
	 private ArrayList<Program> chuongTrinhHoc;

	 /*=================== CONSTRUCTORs ================================*/
	 public School() {
		  chuongTrinhHoc = new ArrayList<>();
	 }

	 public School(String schoolID, String tenNganhHoc, ArrayList<Program> chuongTrinhHoc) {
		  this.schoolID = schoolID;
		  this.tenNganhHoc = tenNganhHoc;
		  this.chuongTrinhHoc = chuongTrinhHoc;
	 }

	 /*=================== GETTTERs & SETTERs  ============================*/
	 public String getMaNganhHoc() {
		  return schoolID;
	 }

	 public void setMaNganhHoc(String schoolID) {
		  this.schoolID = schoolID;
	 }

	 public String getTenNganhHoc() {
		  return tenNganhHoc;
	 }

	 public void setTenNganhHoc(String tenNganhHoc) {
		  this.tenNganhHoc = tenNganhHoc;
	 }

	 public ArrayList<Program> getChuongTrinhHoc() {
		  return chuongTrinhHoc;
	 }

	 public void setChuongTrinhHoc(ArrayList<Program> chuongTrinhHoc) {
		  this.chuongTrinhHoc = chuongTrinhHoc;
	 }
	 /*=================== OTHER METHODs  ===============================*/

}//class School
