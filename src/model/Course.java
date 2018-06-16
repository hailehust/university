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

    private ArrayList<Program> program;

    /*=================== CONSTRUCTORs ================================*/
    public Course() {
        program = new ArrayList<>();
    }

    public Course(String maHocPhan, String tenHocPhan, float trongSoGiuaKy, float trongSoCuoiKy, ArrayList<Program> program) {
        this.maHocPhan = maHocPhan;
        this.tenHocPhan = tenHocPhan;
        this.trongSoGiuaKy = trongSoGiuaKy;
        this.trongSoCuoiKy = trongSoCuoiKy;
        this.program = program;
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
    public ArrayList<String> getMaChuongTrinhHoc() {//get list cac maChuongTrinhHoc cua course
        ArrayList<String> programID = new ArrayList<>();
        for (Program p : program) {
            programID.add(p.getMaChuongTrinhHoc());
        }
        return programID;
    }

    public void setChuongTrinhHoc(Program p) {//chen 1 chuong trinh hoc ma hoc phan nam trong
        this.program.add(p);
    }
}//class Course
