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

	 public UnitCourse(String maHocPhan, String tenHocPhan, float trongSoGiuaKy, float trongSoCuoiKy, ArrayList<Program> program) {
		  super(maHocPhan, tenHocPhan, trongSoGiuaKy, trongSoCuoiKy, program);
		  maHocPhanTCDieuKien = new ArrayList<>();

	 }

	 public UnitCourse(ArrayList<String> maHocPhanTCDieuKien) {
		  this.maHocPhanTCDieuKien = maHocPhanTCDieuKien;
	 }

	 public UnitCourse(ArrayList<String> maHocPhanTCDieuKien, String maHocPhan, String tenHocPhan, float trongSoGiuaKy, float trongSoCuoiKy, ArrayList<Program> program) {
		  super(maHocPhan, tenHocPhan, trongSoGiuaKy, trongSoCuoiKy, program);
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
