package model;

/*@author hai*/
/**
 * desciption:
 */
public class CourseResult {

	 /*=================== ATTRIBUTEs ==================================*/
	 private String mssv;
	 private String maHocPhan;
	 private String maHocKyHeThong;

	 private float diemGiuaKy;
	 private float diemCuoiKy;
	 private float diemHocPhan;
	 private String ketQua;


	 /*=================== CONSTRUCTORs ================================*/
	 public CourseResult() {
	 }

	 public CourseResult(float diemGiuaKy, float diemCuoiKy, float diemHocPhan, String ketQua, String mssv, String maHocPhan, String maHocKyHeThong) {
		  this.diemGiuaKy = diemGiuaKy;
		  this.diemCuoiKy = diemCuoiKy;
		  this.diemHocPhan = diemHocPhan;
		  this.ketQua = ketQua;
		  this.mssv = mssv;
		  this.maHocPhan = maHocPhan;
		  this.maHocKyHeThong = maHocKyHeThong;
	 }



	 /*=================== GETTTERs & SETTERs  ============================*/
	 public String getMaHocPhan() {
		  return maHocPhan;
	 }

	 public void setMaHocPhan(String maHocPhan) {
		  this.maHocPhan = maHocPhan;
	 }

	 public String getSystemSemesterID() {
		  return maHocKyHeThong;
	 }

	 public void setMaHocKyHeThong(String maHocKyHeThong) {
		  this.maHocKyHeThong = maHocKyHeThong;
	 }

	 public float getDiemGiuaKy() {
		  return diemGiuaKy;
	 }

	 public void setDiemGiuaKy(float diemGiuaKy) {
		  this.diemGiuaKy = diemGiuaKy;
	 }

	 public float getDiemCuoiKy() {
		  return diemCuoiKy;
	 }

	 public void setDiemCuoiKy(float diemCuoiKy) {
		  this.diemCuoiKy = diemCuoiKy;
	 }

	 public float getDiemHocPhan() {
		  return diemHocPhan;
	 }

	 public String getMssv() {
		  return mssv;
	 }

	 public void setMssv(String mssv) {
		  this.mssv = mssv;
	 }

	 public void setDiemHocPhan(float diemHocPhan) {
		  this.diemHocPhan = diemHocPhan;
	 }

	 public String getKetQua() {
		  return ketQua;
	 }

	 public void setKetQua(String ketQua) {
		  this.ketQua = ketQua;
	 }
	 
	 
	 /*=================== OTHER METHODs  ===============================*/

}//class CourseResult
