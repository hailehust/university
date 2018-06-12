package model;

import java.util.ArrayList;

/*@author hai*/
/**
 * desciption:
 */
public class UnitCourse extends Course {

	 /*=================== ATTRIBUTEs ==================================*/
	 private ArrayList<String> maHocPhanTCDieuKien;

	 /*=================== CONSTRUCTORs ================================*/
	 public UnitCourse() {
		  maHocPhanTCDieuKien = new ArrayList<>();
	 }

	 public UnitCourse(String maHocPhan, String tenHocPhan, float trongSoGiuaKy, float trongSoCuoiKy, ArrayList<String> programID) {
		  super(maHocPhan, tenHocPhan, trongSoGiuaKy, trongSoCuoiKy, programID);
		  maHocPhanTCDieuKien = new ArrayList<>();

	 }

	 public UnitCourse(ArrayList<String> maHocPhanTCDieuKien) {
		  this.maHocPhanTCDieuKien = maHocPhanTCDieuKien;
	 }

	 public UnitCourse(ArrayList<String> maHocPhanTCDieuKien, String maHocPhan, String tenHocPhan, float trongSoGiuaKy, float trongSoCuoiKy, ArrayList<String> programID) {
		  super(maHocPhan, tenHocPhan, trongSoGiuaKy, trongSoCuoiKy, programID);
		  this.maHocPhanTCDieuKien = maHocPhanTCDieuKien;
	 }

	 /*=================== GETTTERs & SETTERs  ============================*/
	 public ArrayList<String> getMaHocPhanTCDieuKien() {
		  return maHocPhanTCDieuKien;
	 }

	 public void setMaHocPhanTCDieuKien(ArrayList<String> maHocPhanTCDieuKien) {
		  this.maHocPhanTCDieuKien = maHocPhanTCDieuKien;
	 }

}//class UnitCourse
