package model;

import java.util.ArrayList;

/*@author hai*/
/**
 * desciption:
 */
public class YearlyCourse extends Course {

	 /*=================== ATTRIBUTEs ==================================*/
	 private int sttHocKy;

	 /*=================== CONSTRUCTORs ================================*/

	 public YearlyCourse() {
	 }


	 public YearlyCourse(int sttHocKy, String maHocPhan, String tenHocPhan, float trongSoGiuaKy, float trongSoCuioiKy, ArrayList<String> programID) {
		  super(maHocPhan, tenHocPhan, trongSoGiuaKy, trongSoCuioiKy, programID);
		  this.sttHocKy = sttHocKy;
	 }



	 /*=================== GETTTERs & SETTERs  ============================*/

	 public int getSttHocKy() {
		  return sttHocKy;
	 }

	 public void setSttHocKy(int sttHocKy) {
		  this.sttHocKy = sttHocKy;
	 }

	 /*=================== OTHER METHODs  ===============================*/
}//class YearlyCourse
