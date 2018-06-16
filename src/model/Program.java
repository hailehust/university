package model;

import java.util.ArrayList;

/*@author hai*/
/**
 * desciption:
 */
public class Program {

    /*=================== ATTRIBUTEs ==================================*/
    private String programID;
    private String programName;

    private School school;//school ma program tham chieu toi

    private ArrayList<StudentClass> lopSinhVien;
    private ArrayList<Course> hocPhan;

    /*=================== CONSTRUCTORs ================================*/
    public Program() {
        lopSinhVien = new ArrayList<>();
        hocPhan = new ArrayList<>();
    }

    public Program(String programID, String programName, School schoolID, ArrayList<StudentClass> lopSinhVien, ArrayList<Course> hocPhan) {
        this.programID = programID;
        this.programName = programName;
        this.school = school;
        this.lopSinhVien = lopSinhVien;
        this.hocPhan = hocPhan;
    }

    /*=================== GETTTERs & SETTERs  ============================*/
    public String getMaChuongTrinhHoc() {
        return programID;
    }

    public void setMaChuongTrinhHoc(String programID) {
        this.programID = programID;
    }

    public String getTenChuongTrinhHoc() {
        return programName;
    }

    public void setTenChuongTrinhHoc(String programName) {
        this.programName = programName;
    }

    public ArrayList<StudentClass> getLopSinhVien() {
        return lopSinhVien;
    }

    public void setLopSinhVien(ArrayList<StudentClass> lopSinhVien) {
        this.lopSinhVien = lopSinhVien;
    }

    public ArrayList<Course> getHocPhan() {
        return hocPhan;
    }

    public void setHocPhan(ArrayList<Course> hocPhan) {
        this.hocPhan = hocPhan;
    }

    public String getMaNganhHoc() {//chi get duoc ma nganh hoc
        return school.getMaNganhHoc();
    }

    public void setNganhHoc(School school) {
        this.school = school;
    }
    /*=================== OTHER METHODs  ===============================*/

}//class Program
