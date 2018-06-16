package control;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.*;


/*@author hai*/
/**
 * desciption:
 */
public class DAO {

    /*=================== ATTRIBUTEs ==================================*/
    private Connection conn;//ket noi
    private University university;

    /*=================== CONSTRUCTORs ================================*/
    public DAO(University university) {//ham khoi tao connector, giup ket noi JAVA - SQLServer
        this.university = university;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysv2", "root", "haile");

            System.out.println("Connected to MySQL succesfull!!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /*====================== GET DATAs // doc du lieu tu database ===========================================*/
    public ArrayList<Admin> getAdminList() {//doc cac ban ghi tu database, tra ve 1 ArrayList
        ArrayList<Admin> listAdmin = new ArrayList<>();// ArrayList de luu cac doi tuong
        String sql = "SELECT * FROM admin";//cau lenh truy van

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh truy van
            ResultSet rs = ps.executeQuery();//rs luu cac gia tri duoc query ve tu database
            //neu chi truy van - query - thi dung rs.executeQuery

            while (rs.next()) {//doc den het cac ban ghi o bang
                Admin a = new Admin();
                a.setAdminID(rs.getString("adminID"));
                a.setHoTen(rs.getString("hoTen"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));

                listAdmin.add(a);//them a vo danhSachAdmin
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listAdmin;//return ArrayList danhSachAdmin duoc doc tu database
    }

    public ArrayList<Program> getProgramList() {//doc cac ban ghi tu database, tra ve 1 ArrayList
        ArrayList<Program> listChuongTrinhHoc = new ArrayList<>();// ArrayList de luu cac doi tuong
        String sql = "SELECT * FROM chuongtrinhhoc";//cau lenh truy van

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh truy van
            ResultSet rs = ps.executeQuery();//rs luu cac gia tri duoc query ve tu database
            //neu chi truy van - query - thi dung rs.executeQuery

            while (rs.next()) {//doc den het cac ban ghi o bang
                Program a = new Program();

                a.setMaChuongTrinhHoc(rs.getString("maChuongTrinhHoc"));
                a.setTenChuongTrinhHoc(rs.getString("tenChuongTrinhHoc"));
                a.setNganhHoc(university.findSchool(rs.getString("maNganhHoc")));

                listChuongTrinhHoc.add(a);//them a vo list

                //add chuongTrinhHoc a vo list chuongTrinhHoc cua nganh tuong ung
                university.findSchool(a.getMaNganhHoc()).getChuongTrinhHoc().add(a);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listChuongTrinhHoc;//return ArrayList danhSachAdmin duoc doc tu database
    }

    public void programHasCourse() {
        String sql = "SELECT * FROM chuongtrinhhoc_baogom_hocphan";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {//doc den het cac ban ghi cua bang
                //add hocPhan vo chuongTrinhHoc
                university.findProgram(rs.getString("maChuongTrinhHoc")).getHocPhan()
                        .add(university.findCourse(rs.getString("maHocPhan")));
                //add maChuongTrinhHoc vo hocPhan
                university.findCourse(rs.getString("maHocPhan")).getMaChuongTrinhHoc()
                        .add(rs.getString("maChuongTrinhHoc"));
            }

        } catch (SQLException e) {

        }
    }

    public ArrayList<CourseRegistration> getCourseRegistrationList() {//doc cac ban ghi tu database, tra ve 1 ArrayList
        ArrayList<CourseRegistration> listDangKyHocPhan = new ArrayList<>();// ArrayList de luu cac doi tuong
        String sql = "SELECT * FROM dangkyhocphan";//cau lenh truy van

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh truy van
            ResultSet rs = ps.executeQuery();//rs luu cac gia tri duoc query ve tu database
            //neu chi truy van - query - thi dung rs.executeQuery

            while (rs.next()) {//doc den het cac ban ghi o bang
                CourseRegistration a = new CourseRegistration();

                a.setMaHocPhan(rs.getString("maHocPhan"));
                a.setMaHocKyHeThong(rs.getString("maHocKyHeThong"));

                listDangKyHocPhan.add(a);//them a vo danhSachAdmin

                university.findSystemSemester(a.getSystemSemesterID()).getDangKyHocPhan().add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listDangKyHocPhan;//return ArrayList danhSachAdmin duoc doc tu database
    }

    public ArrayList<SystemSemester> getSystemSemesterList() {//doc cac ban ghi tu database, tra ve 1 ArrayList
        ArrayList<SystemSemester> listHocKyHeThong = new ArrayList<>();// ArrayList de luu cac doi tuong
        String sql = "SELECT * FROM hockyhethong";//cau lenh truy van

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh truy van
            ResultSet rs = ps.executeQuery();//rs luu cac gia tri duoc query ve tu database
            //neu chi truy van - query - thi dung rs.executeQuery

            while (rs.next()) {//doc den het cac ban ghi o bang
                SystemSemester a = new SystemSemester();
                a.setMaHocKyHeThong(rs.getString("maHocKyHeThong"));
                a.setBatDauDangKyHocPhan(rs.getDate("batDauDangKyHocPhan"));
                a.setBatDauDangKyLopMo(rs.getDate("batDauDangKyLopMo"));
                a.setBatDauHocKy(rs.getDate("batDauHocKy"));
                a.setKetThucHocKy(rs.getDate("ketThucHocKy"));

                listHocKyHeThong.add(a);//them a vo danhSachAdmin
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listHocKyHeThong;//return ArrayList danhSachAdmin duoc doc tu database
    }

    public ArrayList<StudentSemester> getStudentSemesterList() {//doc cac ban ghi tu database, tra ve 1 ArrayList
        ArrayList<StudentSemester> listHocKySinhVien = new ArrayList<>();// ArrayList de luu cac doi tuong
        String sql = "SELECT * FROM hockysinhvien";//cau lenh truy van

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh truy van
            ResultSet rs = ps.executeQuery();//rs luu cac gia tri duoc query ve tu database
            //neu chi truy van - query - thi dung rs.executeQuery

            while (rs.next()) {//doc den het cac ban ghi o bang
                StudentSemester a = new StudentSemester();

                a.setMaHocKyHeThong(rs.getString("maHocKyHeThong"));
                a.setMssv(rs.getString("mssv"));

                listHocKySinhVien.add(a);//them a vo danhSachAdmin
                university.findStudent(a.getMssv()).getHocKySinhVien().add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listHocKySinhVien;//return ArrayList danhSachAdmin duoc doc tu database
    }

    public void studentSemesterHasCourseClass() {
        String sql = "SELECT * FROM hockysinhvien_baogom_lopmo";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {//doc den het cac ban ghi cua bang
                //add maLopMo vo hocKySinhVien
                university.findStudentSemester(rs.getString("mssv"), rs.getString("maHocKyHeThong"))
                        .getMaLopMoDangKy().add(rs.getString("maLopMo"));
                //add sinhVien vo lopMo
                university.findCourseClass(rs.getString("maLopMo")).getSinhVien()
                        .add(university.findStudent(rs.getString("mssv")));
            }

        } catch (SQLException e) {

        }
    }

    public void studentSemesterHasCourse() {
        String sql = "SELECT * FROM hockysinhvien_baogom_hocphan";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {//doc den het cac ban ghi cua bang
                //add maHocPhan vo hocKySinhVien
                university.findStudentSemester(rs.getString("mssv"), rs.getString("maHocKyHeThong"))
                        .getMaHocPhanDangKy().add(rs.getString("maHocPhan"));
                // add mssv vo CourseRegistration

                university.findCourseRegistration(rs.getString("maHocPhan"), rs.getString("maHocKyHeThong"))
                        .getMssvDangKy().add(rs.getString("mssv"));
            }

        } catch (SQLException e) {

        }
    }

    public ArrayList<Course> getCourseList() {//doc cac ban ghi tu database, tra ve 1 ArrayList
        ArrayList<Course> listHocPhan = new ArrayList<>();// ArrayList de luu cac doi tuong
        String sql = "SELECT * FROM hocphan";//cau lenh truy van

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh truy van
            ResultSet rs = ps.executeQuery();//rs luu cac gia tri duoc query ve tu database
            //neu chi truy van - query - thi dung rs.executeQuery

            while (rs.next()) {//doc den het cac ban ghi o bang
                if (rs.getString("heHocPhan").equals("TC")) {//neu la hoc phan TC
                    UnitCourse a = new UnitCourse();

                    a.setMaHocPhan(rs.getString("maHocPhan"));
                    a.setTenHocPhan(rs.getString("tenHocPhan"));
                    a.setTrongSoGiuaKy(rs.getFloat("trongSoGiuaKy"));
                    a.setTrongSoCuoiKy(rs.getFloat("trongSoCuoiKy"));

                    listHocPhan.add(((Course) a));//them a vo list

                } else {//neu rs la hoc phan nc
                    YearlyCourse a = new YearlyCourse();

                    a.setMaHocPhan(rs.getString("maHocPhan"));
                    a.setTenHocPhan(rs.getString("tenHocPhan"));
                    a.setTrongSoGiuaKy(rs.getFloat("trongSoGiuaKy"));
                    a.setTrongSoCuoiKy(rs.getFloat("trongSoCuoiKy"));
                    a.setSttHocKy(rs.getInt("sttHocKy"));

                    listHocPhan.add((Course) a);//them a vo list
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listHocPhan;//return ArrayList danhSachAdmin duoc doc tu database
    }

    public void unitCourseHasUnitCourse() {//khoi tao list hocPhanDieuKien cho UnitCourse
        String sql = "SELECT * FROM hocphantc_ladieukien_hocphantc";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {//doc den het cac ban ghi cua bang
                //tim hocPhanTC chua hocPhanTCDieuKien
                //bang hocPhanTClaDieuKienHocPhanTC chi chua cac key cua hocPhanTC
                ((UnitCourse) university.findCourse(rs.getString("maHocPhanTC")))
                        .getMaHocPhanTCDieuKien().add(rs.getString("maHocPhanTCDieuKien"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<CourseResult> getCourseResultList() {//doc cac ban ghi tu database, tra ve 1 ArrayList
        ArrayList<CourseResult> listKetQuaHocPhan = new ArrayList<>();// ArrayList de luu cac doi tuong
        String sql = "SELECT * FROM ketquahocphan";//cau lenh truy van

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh truy van
            ResultSet rs = ps.executeQuery();//rs luu cac gia tri duoc query ve tu database
            //neu chi truy van - query - thi dung rs.executeQuery

            while (rs.next()) {//doc den het cac ban ghi o bang
                CourseResult a = new CourseResult();

                a.setMssv(rs.getString("mssv"));
                a.setMaHocPhan(rs.getString("maHocPhan"));
                a.setMaHocKyHeThong(rs.getString("maHocKyHeThong"));
                a.setDiemGiuaKy(rs.getFloat("diemGiuaKy"));
                a.setDiemCuoiKy(rs.getFloat("diemCuoiKy"));
                a.setDiemHocPhan(rs.getFloat("diemHocPhan"));
                a.setKetQua(rs.getString("ketQua"));

                listKetQuaHocPhan.add(a);//them a vo danhSachAdmin

                university.findStudent(a.getMssv()).getBangDiem().add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listKetQuaHocPhan;//return ArrayList danhSachAdmin duoc doc tu database
    }

    public ArrayList<CourseClass> getCourseClassList() {//doc cac ban ghi tu database, tra ve 1 ArrayList
        ArrayList<CourseClass> listLopMo = new ArrayList<>();// ArrayList de luu cac doi tuong
        String sql = "SELECT * FROM lopmo";//cau lenh truy van

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh truy van
            ResultSet rs = ps.executeQuery();//rs luu cac gia tri duoc query ve tu database
            //neu chi truy van - query - thi dung rs.executeQuery

            while (rs.next()) {//doc den het cac ban ghi o bang
                CourseClass a = new CourseClass();

                a.setMaLopMo(rs.getString("maLopMo"));
                a.setMaHocPhan(rs.getString("maHocPhan"));
                a.setMaHocKyHeThong(rs.getString("maHocKyHeThong"));
                a.setSoLuongSinhVienToiDa(rs.getInt("soLuongSinhVienToiDa"));

                listLopMo.add(a);//them a vo danhSachAdmin

                university.findSystemSemester(a.getSystemSemesterID()).getLopMo().add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listLopMo;//return ArrayList danhSachAdmin duoc doc tu database
    }

    public ArrayList<StudentClass> getStudentClassList() {//doc cac ban ghi tu database, tra ve 1 ArrayList
        ArrayList<StudentClass> listLopSinhVien = new ArrayList<>();// ArrayList de luu cac doi tuong
        String sql = "SELECT * FROM lopsinhvien";//cau lenh truy van

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh truy van
            ResultSet rs = ps.executeQuery();//rs luu cac gia tri duoc query ve tu database
            //neu chi truy van - query - thi dung rs.executeQuery

            while (rs.next()) {//doc den het cac ban ghi o bang
                StudentClass a = new StudentClass();

                a.setMaLopSinhVien(rs.getString("maLopSinhVien"));
                a.setMaChuongTrinhHoc(rs.getString("maChuongTrinhHoc"));
                a.setNamNhapHoc(rs.getInt("namNhapHoc"));

                listLopSinhVien.add(a);//them a vo danhSachAdmin

                university.findProgram(a.getMaChuongTrinhHoc()).getLopSinhVien().add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listLopSinhVien;//return ArrayList danhSachAdmin duoc doc tu database
    }

    public ArrayList<School> getSchoolList() {//doc cac ban ghi tu database, tra ve 1 ArrayList
        ArrayList<School> listNganhHoc = new ArrayList<>();// ArrayList de luu cac doi tuong
        String sql = "SELECT * FROM nganhhoc";//cau lenh truy van

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh truy van
            ResultSet rs = ps.executeQuery();//rs luu cac gia tri duoc query ve tu database
            //neu chi truy van - query - thi dung rs.executeQuery

            while (rs.next()) {//doc den het cac ban ghi o bang
                School a = new School();

                a.setMaNganhHoc(rs.getString("maNganhHoc"));
                a.setTenNganhHoc(rs.getString("tenNganhHoc"));

                listNganhHoc.add(a);//them a vo danhSachAdmin
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listNganhHoc;//return ArrayList danhSachAdmin duoc doc tu database
    }

    public ArrayList<Student> getStudentList() {//doc cac ban ghi tu database, tra ve 1 ArrayList
        ArrayList<Student> listSinhVien = new ArrayList<>();// ArrayList de luu cac doi tuong
        String sql = "SELECT * FROM sinhvien";//cau lenh truy van

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh truy van
            ResultSet rs = ps.executeQuery();//rs luu cac gia tri duoc query ve tu database
            //neu chi truy van - query - thi dung rs.executeQuery

            while (rs.next()) {//doc den het cac ban ghi o bang
                if (rs.getString("heSinhvien").equals("TC")) {
                    UnitStudent a = new UnitStudent();

                    a.setMssv(rs.getString("mssv"));
                    a.setHoTen(rs.getString("hoTen"));
                    a.setUsername(rs.getString("username"));
                    a.setPassword(rs.getString("password"));
                    a.setTrangThai(rs.getString("trangThai"));
                    a.setBangTotNghiep(rs.getString("bangTotNghiep"));
                    a.setMaLopSinhVien(rs.getString("maLopSinhVien"));
                    a.setMaChuongTrinhHoc(rs.getString("maChuongTrinhHoc"));
                    a.setMaNganhHoc(rs.getString("maNganhHoc"));

                    listSinhVien.add(a);//them a vo danhSachAdmin

                    university.findStudentClass(a.getMaLopSinhVien()).getSinhVien().add(a);

                } else {
                    YearlyStudent a = new YearlyStudent();

                    a.setMssv(rs.getString("mssv"));
                    a.setHoTen(rs.getString("hoTen"));
                    a.setUsername(rs.getString("username"));
                    a.setPassword(rs.getString("password"));
                    a.setTrangThai(rs.getString("trangThai"));
                    a.setBangTotNghiep(rs.getString("bangTotNghiep"));
                    a.setMaLopSinhVien(rs.getString("maLopSinhVien"));
                    a.setMaChuongTrinhHoc(rs.getString("maChuongTrinhHoc"));
                    a.setMaNganhHoc(rs.getString("maNganhHoc"));

                    listSinhVien.add(a);//them a vo list
                    university.findStudentClass(a.getMaLopSinhVien()).getSinhVien().add(a);

                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listSinhVien;//return ArrayList danhSachAdmin duoc doc tu database
    }

    /*====================== UPDATE DATAs // chinh 1 ban ghi trong database  ===========================================*/
    public void updateCourseRegistration(CourseRegistration a) {

        //update soLuongSinhVienDangKy
        String sql
                = "UPDATE dangkyhocphan SET `soLuongSinhVienDangKy`='" + a.getMssvDangKy().size()
                + "' WHERE `maHocKyHeThong`='" + a.getSystemSemesterID() + "' and`maHocPhan`='" + a.getMaHocPhan() + "'";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh sql cho database
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCourseClassRegistration(CourseClass a) {
        //update soLuongSinhVienDangKy Lop
        String sql
                = "UPDATE lopmo SET `siSo`='" + a.getSinhVien().size() + "'"
                + " WHERE `maLopMo`='" + a.getMaLopMo() + "'";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh sql cho database
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(String mssv, String newPassword) {

        String sql = "UPDATE `sinhvien` SET `password` ='" + newPassword + "'WHERE  `mssv` ='" + mssv + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*====================== ADD DATAs // them ban ghi vao database ===========================================*/
    public void addCourseRegistration(CourseRegistration d) {
        //duoc goi neu dang ky hoc phan chua ton tai
        //ton tai roi -> updateDagnKyHocPhan
        String sql = "INSERT INTO dangkyhocphan(maHocKyHeThong, maHocPhan, soLuongSinhVienDangKy)"
                + "VALUES(?, ?, ?)";//cau lenh SQL chen 1 ban ghi vao bang sinhvien

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh sql cho database
            //set gia tri cho tung cot cua bang Student
            ps.setString(1, d.getSystemSemesterID());//set ID cua sinh vien vao cot 1 cua bang
            ps.setString(2, d.getMaHocPhan());//set tenSV vao cot 2
            ps.setInt(3, 1);//1 dangkyhocphan mới chỉ có 1 sinh viên đăng ký thôi

            ps.executeUpdate();//co chinh sua du lieu cua csdl thi return so luong ban ghi da chinh sua
            //khong chinh sua gi thi return 0
            //co thay doi database -> executeUpdate()
            //neu chi query du lieu -> executeQuery()
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourseRegistration(CourseRegistration d) {//xoa khi khong con ai dang ky mon nay nua
        //duoc goi neu dang ky hoc phan chua ton tai
        //ton tai roi -> updateDagnKyHocPhan
        String sql = "DELETE FROM dangkyhocphan "
                + "WHERE `maHocKyHeThong`='" + d.getSystemSemesterID() + "' and`maHocPhan`='" + d.getMaHocPhan() + "';";//cau lenh SQL chen 1 ban ghi vao bang sinhvien

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh sql cho database
            ps.executeUpdate();//co chinh sua du lieu cua csdl thi return so luong ban ghi da chinh sua

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudentSemesterHasCourse(String mssv, String maHocKyHeThong, String maHocPhan) {
        String sql = "INSERT INTO hockysinhvien_baogom_hocphan(mssv, maHocKyHeThong, maHocPhan)"
                + "VALUES(?, ?, ?)";//cau lenh SQL chen 1 ban ghi vao bang sinhvien

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh sql cho database
            //set gia tri cho tung cot cua bang Student
            ps.setString(1, mssv);//set ID cua sinh vien vao cot 1 cua bang
            ps.setString(2, maHocKyHeThong);//set tenSV vao cot 2
            ps.setString(3, maHocPhan);

            ps.executeUpdate();//co chinh sua du lieu cua csdl thi return so luong ban ghi da chinh sua
            //khong chinh sua gi thi return 0
            //co thay doi database -> executeUpdate()
            //neu chi query du lieu -> executeQuery()
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentSemesterHasCourse(String mssv, String maHocKyHeThong, String maHocPhan) {
        String sql = "DELETE FROM hockysinhvien_baogom_hocphan"
                + " WHERE `mssv`='" + mssv + "' and`maHocKyHeThong`='" + maHocKyHeThong + "' and`maHocPhan`='" + maHocPhan + "';";//cau lenh SQL chen 1 ban ghi vao bang sinhvien

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh sql cho database

            ps.executeUpdate();//co chinh sua du lieu cua csdl thi return so luong ban ghi da chinh sua
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentSemesterHasCourseClass(String mssv, String maHocKyHeThong, String maLopMo) {
        String sql = "DELETE FROM hockysinhvien_baogom_lopmo"
                + " WHERE `mssv`='" + mssv + "' and`maHocKyHeThong`='" + maHocKyHeThong + "' and`maLopMo`='" + maLopMo + "';";//cau lenh SQL chen 1 ban ghi vao bang sinhvien

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh sql cho database

            ps.executeUpdate();//co chinh sua du lieu cua csdl thi return so luong ban ghi da chinh sua
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudentSemesterHasCourseClass(String mssv, String maHocKyHeThong, String maLopMo) {
        String sql = "INSERT INTO hockysinhvien_baogom_lopmo(mssv, maHocKyHeThong, maLopMo)"
                + "VALUES(?, ?, ?)";//cau lenh SQL chen 1 ban ghi vao bang sinhvien

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh sql cho database
            //set gia tri cho tung cot cua bang Student
            ps.setString(1, mssv);//set ID cua sinh vien vao cot 1 cua bang
            ps.setString(2, maHocKyHeThong);//set tenSV vao cot 2
            ps.setString(3, maLopMo);

            ps.executeUpdate();//co chinh sua du lieu cua csdl thi return so luong ban ghi da chinh sua
            //khong chinh sua gi thi return 0
            //co thay doi database -> executeUpdate()
            //neu chi query du lieu -> executeQuery()
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSystemSemester(SystemSemester h) {
        String sql = "INSERT INTO hockyhethong(maHocKyHeThong, batDauDangKyHocPhan, batDauDangKyLopMo,"
                + "batDauHocKy, ketThucHocKy) "
                + "VALUES(?, ?, ?, ?, ?)";//cau lenh SQL chen 1 ban ghi vao bang sinhvien

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh sql cho database
            //set gia tri cho tung cot cua bang Student
            ps.setString(1, h.getSystemSemesterID());//set ID cua sinh vien vao cot 1 cua bang
            ps.setDate(2, h.getBatDauDangKyHocPhan());//set tenSV vao cot 2
            ps.setDate(3, h.getBatDauDangKyLopMo());//set tenSV vao cot 2
            ps.setDate(4, h.getBatDauHocKy());//set tenSV vao cot 2
            ps.setDate(5, h.getKetThucHocKy());//set tenSV vao cot 2

            ps.executeUpdate();//co chinh sua du lieu cua csdl thi return so luong ban ghi da chinh sua
            //khong chinh sua gi thi return 0
            //co thay doi database -> executeUpdate()
            //neu chi query du lieu -> executeQuery()
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStudentSemester(StudentSemester h) {
        String sql = "INSERT INTO hockysinhvien(mssv, maHocKyHeThong) "
                + "VALUES(?, ?)";//cau lenh SQL chen 1 ban ghi vao bang sinhvien

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh sql cho database
            //set gia tri cho tung cot cua bang Student
            ps.setString(1, h.getMssv());//set ID cua sinh vien vao cot 1 cua bang
            ps.setString(2, h.getSystemSemesterID());//set ID cua sinh vien vao cot 1 cua bang

            ps.executeUpdate();//co chinh sua du lieu cua csdl thi return so luong ban ghi da chinh sua
            //khong chinh sua gi thi return 0
            //co thay doi database -> executeUpdate()
            //neu chi query du lieu -> executeQuery()
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCourseClass(CourseClass lopMo) {
        String sql = "INSERT INTO lopmo(maLopMo, soLuongSinhVienToiDa, siSo, maHocPhan, maHocKyHeThong) "
                + "VALUES(?, ?, ?, ?, ?)";//cau lenh SQL chen 1 ban ghi vao bang sinhvien

        try {
            PreparedStatement ps = conn.prepareStatement(sql);//thuc thi cau lenh sql cho database
            //set gia tri cho tung cot cua bang Student
            ps.setString(1, lopMo.getMaLopMo());//set ID cua sinh vien vao cot 1 cua bang
            ps.setInt(2, lopMo.getSoLuongSinhVienToiDa());//set ID cua sinh vien vao cot 1 cua bang
            ps.setInt(3, lopMo.getSinhVien().size());//set ID cua sinh vien vao cot 1 cua bang
            ps.setString(4, lopMo.getMaHocPhan());//set ID cua sinh vien vao cot 1 cua bang
            ps.setString(5, lopMo.getSystemSemesterID());//set ID cua sinh vien vao cot 1 cua bang

            ps.executeUpdate();//co chinh sua du lieu cua csdl thi return so luong ban ghi da chinh sua
            //khong chinh sua gi thi return 0
            //co thay doi database -> executeUpdate()
            //neu chi query du lieu -> executeQuery()
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*======================================EDUCATION MANAGEMENT==============================================================*/
    public void addSchool(String schoolID, String schoolName) {
        String sql = "INSERT INTO nganhhoc (maNganhHoc, tenNganhHoc) VALUES ('" + schoolID + "', '" + schoolName + "');";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void editSchool(String schoolID, String newSchoolID, String newSchoolName) {
        String sql1 = "INSERT INTO nganhhoc(maNganhHoc, tenNganhHoc) VALUES(?, ?)";//them ban ghi school moi
        String sql2 = "UPDATE chuongtrinhhoc SET `maNganhHoc`='" + newSchoolID
                + "' WHERE `maNganhHoc`='" + schoolID + "'";//edit o cac cho tham chieu toi ban ghi cu
        String sql3 = "UPDATE sinhvien SET `maNganhHoc`='" + newSchoolID
                + "' WHERE `maNganhHoc`='" + schoolID + "'";//edit o cac cho tham chieu toi ban ghi cu
        String sql4 = "DELETE FROM nganhhoc WHERE `maNganhHoc`='" + schoolID + "'";//xoa ban ghi cu

        try {
            PreparedStatement ps = conn.prepareStatement(sql1);//thuc thi cau lenh sql cho database
            //set gia tri cho tung cot cua bang Student
            ps.setString(1, newSchoolID);//set ID cua sinh vien vao cot 1 cua bang
            ps.setString(2, newSchoolName);//set tenSV vao cot 2
            ps.executeUpdate();//khong chinh sua gi thi return 0
            //co thay doi database -> executeUpdate()
            //neu chi query du lieu -> executeQuery()

            //thay doi cac noi tham chieu den ban ghi school cu
            ps = conn.prepareStatement(sql2);
            ps.executeUpdate();
            ps = conn.prepareStatement(sql3);
            ps.executeUpdate();
//xoa ban ghi cu
            ps = conn.prepareStatement(sql4);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSchool(String schoolID) {
        String sql = "DELETE FROM nganhhoc WHERE `maNganhHoc`='" + schoolID + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProgram(String programID, String programName, String schoolID) {
        String sql = "INSERT INTO chuongtrinhhoc (maChuongTrinhHoc, tenChuongTrinhHoc, maNganhHoc)"
                + " VALUES ('" + programID + "', '" + programName + "', '" + schoolID + "');";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
        }
    }

    public void deleteProgram(String programID) {
        String sql = "DELETE FROM chuongtrinhhoc WHERE `maChuongTrinhHoc`='" + programID + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //duc
    public void fillCourseJtable(JTable table, String valueToSearch) {
        // int i = 0;
        // Connection conn = MyConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement("select *from hocphan where concat(maHocPhan,tenHocPhan,heHocPhan) like ?");
            ps.setString(1, "%" + valueToSearch + "%");

            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;

            while (rs.next()) {
                row = new Object[6];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                model.addRow(row);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertDeleteUpdateStudent(char operation, Integer id, String heSinhVien, String mssv, String hoTen, String username, String password, String maNganhHoc, String maChuongTrinhHoc, String maLopSinhVien, String trangThaiHocTap, String hocki) {
        // Connection conn = MyConnection.getConnection();
        PreparedStatement ps;

        if (operation == 'i') {
            try {
                ps = conn.prepareStatement("insert into sinhvien(heSinhVien,mssv,hoTen,username, password, maNganhHoc, maChuongTrinhHoc,maLopSinhVien,trangThai,bangTotNghiep) values(?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, heSinhVien);
                ps.setString(2, mssv);
                ps.setString(3, hoTen);
                ps.setString(4, username);
                ps.setString(5, password);
                ps.setString(6, maNganhHoc);
                ps.setString(7, maChuongTrinhHoc);
                ps.setString(8, maLopSinhVien);
                ps.setString(9, trangThaiHocTap);
                ps.setString(10, hocki);

                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "new student added");
                    return true;
                    // this.insertHocKySinhVien(mssv, hocki);
                } else {
                    JOptionPane.showMessageDialog(null, "Can't insert student");
                    return false;
                }

            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (operation == 'u') {
            try {
                ps = conn.prepareStatement("update sinhvien set heSinhVien=?,hoTen=?,username=?, password=?, maNganhHoc=?, maChuongTrinhHoc=?,maLopSinhVien=?,trangThai=?,bangTotNghiep=? where mssv = ?");
                ps.setString(1, heSinhVien);
                //ps.setString(2, mssv);
                ps.setString(2, hoTen);
                ps.setString(3, username);
                ps.setString(4, password);
                ps.setString(5, maNganhHoc);
                ps.setString(6, maChuongTrinhHoc);
                ps.setString(7, maLopSinhVien);
                ps.setString(8, trangThaiHocTap);
                ps.setString(9, hocki);
                ps.setString(10, mssv);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "data student updated");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Can't update student");
                    return false;
                }

            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (operation == 'd') {
            try {
                ps = conn.prepareStatement("delete from sinhvien where mssv = ?");
                ps.setString(1, mssv);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "data student deleted");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Can't delete student");
                    return false;
                }

            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return true;
    }

    public void insertHocKySinhVien(String mssv, String maHocKyHeThong) {
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement("insert into hockysinhvien(mssv,maHocKyHeThong) values(?,?)");
            ps.setString(1, mssv);
            ps.setString(2, maHocKyHeThong);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertDeleteUpdateCourse(char operation, String maHocPhan, String tenHocPhan, Float trongSoGiuaKy, Float trongSoCuoiKy, String heHocPhan, String sttHocKy) {
        PreparedStatement ps;
        if (operation == 'i') {
            try {
                ps = conn.prepareStatement("insert into hocphan(maHocPhan,tenHocPhan,trongSoGiuaKy,trongSoCuoiKy,heHocPhan,sttHocKy) values(?,?,?,?,?,?)");
                ps.setString(1, maHocPhan);
                ps.setString(2, tenHocPhan);
                ps.setFloat(3, trongSoGiuaKy);
                ps.setFloat(4, trongSoCuoiKy);
                ps.setString(5, heHocPhan);
                ps.setString(6, sttHocKy);

                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "new course added");
                    //this.insertHocKySinhVien(mssv, );
                } else {
                    JOptionPane.showMessageDialog(null, "Can't insert course");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (operation == 'u') {
            try {
                ps = conn.prepareStatement("update hocphan set tenHocPhan=?,trongSoGiuaKy=?, trongSoCuoiKy=?, heHocPhan=?, sttHocKy=? where maHocPhan = ?");
                ps.setString(6, maHocPhan);
                ps.setString(1, tenHocPhan);
                ps.setFloat(2, trongSoGiuaKy);
                ps.setFloat(3, trongSoCuoiKy);
                ps.setString(4, heHocPhan);
                ps.setString(5, sttHocKy);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "data course updated");
                } else {
                    JOptionPane.showMessageDialog(null, "Can't update course");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (operation == 'd') {
            try {
                ps = conn.prepareStatement("delete from hocphan where maHocPhan = ?");
                ps.setString(1, maHocPhan);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "data course deleted");
                } else {
                    JOptionPane.showMessageDialog(null, "Can't delete student");
                }

            } catch (SQLException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public boolean isCourseExist(String maHocPhan) {
        boolean isExist = false;
        PreparedStatement ps;
        String sql = "select * from hocphan where maHocPhan like ? ";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, maHocPhan);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                isExist = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isExist;
    }

    public void updateSiSoLopSinhVien(char operation, String maLopSinhVien, int size) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement("update lopsinhvien set siSo=? where maLopSinhVien=?");
            if (operation == 'i') {
                ps.setString(1, String.valueOf(size + 1));
            } else {
                ps.setString(1, String.valueOf(size - 1));
            }
            ps.setString(2, maLopSinhVien);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}//class DAO
