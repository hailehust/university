package model;

import control.DAO;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import view.AdminView;
import view.LoginView;
import view.YearlyStudentView;
import view.UnitStudentView;
import view.ChangePasswordView;

/**
 *
 * @author hai
 */
public final class University {// this quản lý sinh viên (giống như hệ thống của 1 trường đại học ngoài đời thật)
    //khac

    private Date today;//ngay chay he thong

    //list cac class chua du lieu cua hethong
    private ArrayList<Admin> adminList;//done
    private ArrayList<Program> programList;
    private ArrayList<CourseRegistration> courseRegistrationList;
    private ArrayList<SystemSemester> systemSemesterList;
    private ArrayList<StudentSemester> studentSemesterList;
    private ArrayList<Course> courseList;
    private ArrayList<CourseResult> courseResultList;
    private ArrayList<CourseClass> courseClassList;
    private ArrayList<StudentClass> studentClassList;
    private ArrayList<School> schoolList;
    private ArrayList<Student> studentList;

    /*====================== CONSTRUCTORs ========================*/
    public University() {
        DAO dao = new DAO(this);//bien cuc bo, ko phai ket tap (nho chuong trinh khoi tao ho)
        today = Date.valueOf(LocalDate.now());//khoi tao ngay hom nay

        /**
         * ======================= import databse: nhờ dao + database khởi tạo
         * các đối tượng cho hệ thống================= CÁCH IMPORT DATABASE ->
         * CÁC ĐỐI TƯỢNG CỦA HỆ THỐNG 1/ Đọc từ database, lưu vô đối tượng toàn
         * bộ các cột, mà, cột đó ứng với dữ liệu không phải là kết tập của đối
         * tượng 2/ Nếu đối tượng đang đọc là 1 thuộc tính kết tập của đối tượng
         * khác -> tìm đối tượng đó, add đối tượng đang đọc vào đối tướng kết
         * tập đó 3/ Đối với sự kết tập lẫn nhau (mối quan hệ n-n), đọc các bảng
         * của liên kết n-n -> thêm lẫn nhau vô thuộc tính kết tập của 2 đối kết
         * tập lẫn nhau đó
         */
        /**
         * THỨ TỰ ĐỌC DỮ LIỆU, KHỞI TẠO ĐỐI TƯỢNG: 1/ Đọc các đối tượng kết tập
         * to nhất (các đối tượng mà nó kết tập đối tượng khác) 2/ Đọc các mức
         * nhỏ hơn
         */
        // thu tu khoi tao doi tuong
        adminList = dao.getAdminList();

        systemSemesterList = dao.getSystemSemesterList();
        courseClassList = dao.getCourseClassList();
        courseRegistrationList = dao.getCourseRegistrationList();

        schoolList = dao.getSchoolList();
        programList = dao.getProgramList();
        studentClassList = dao.getStudentClassList();
        courseList = dao.getCourseList();

        studentList = dao.getStudentList();
        studentSemesterList = dao.getStudentSemesterList();
        courseResultList = dao.getCourseResultList();

        //cap nhat du lieu tu cac bang lien ket n-n
        dao.unitCourseHasUnitCourse();
        dao.studentSemesterHasCourseClass();
        dao.studentSemesterHasCourse();
        dao.programHasCourse();

//========================ĐẾN ĐÂY LÀ ĐÃ ĐỌC XONG HẾT DỮ LIỆU TỪ DATABASE========================
        //khoi tao loginView dau tien, cac userView khoi tao sau khi dang nhap thanh cong
        this.initLoginSession(dao);

        //khởi tạo học kỳ hệ thống, học kỳ sinh viên nếu cần thiết
        this.initSemesters(dao);//khoi tao cac hoc ky he thong/ sinh vien nếu vừa sang thời điểm đăng ký học phần kỳ mới (thágn 11/5)
        //bắt đầu đăng ký học phần: tháng 11/t tháng 5
        this.registerCourseForAllYearlyStudent(dao);//dang ky hoc phan cho sinh vien nc neu vừa đến thời điểm đăng ký học phần kỳ mới(thágn 11/5)

        //bắt đầu đăng ký lớp : tháng 12/ thágn 6
        this.initCourseClass(dao);//khởi tạo các lớp mở cho ca sinhvienTC va NC khi vừa sang thời điểm dnăg ký lớp mở (tháng 12/6)
        this.registerCourseClassForAllYearlyStudent(dao);//tu dong dong ky lop cho sinh vien NC neu can(tháng 12/6)

    }

    /*========================= GETTERs SETTERs : get, set các list đối tượng ==============================================*/
    public ArrayList<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(ArrayList<Admin> adminList) {
        this.adminList = adminList;
    }

    public ArrayList<Program> getProgramList() {
        return programList;
    }

    public void setProgramList(ArrayList<Program> programList) {
        this.programList = programList;
    }

    public ArrayList<CourseRegistration> getCourseRegistrationList() {
        return courseRegistrationList;
    }

    public void setCourseRegistrationList(ArrayList<CourseRegistration> courseRegistrationList) {
        this.courseRegistrationList = courseRegistrationList;
    }

    public ArrayList<SystemSemester> getSystemSemesterList() {
        return systemSemesterList;
    }

    public void setSystemSemesterList(ArrayList<SystemSemester> systemSemesterList) {
        this.systemSemesterList = systemSemesterList;
    }

    public ArrayList<StudentSemester> getStudentSemesterList() {
        return studentSemesterList;
    }

    public void setStudentSemesterList(ArrayList<StudentSemester> studentSemesterList) {
        this.studentSemesterList = studentSemesterList;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public ArrayList<CourseResult> getCourseResultList() {
        return courseResultList;
    }

    public void setCourseResultList(ArrayList<CourseResult> courseResultList) {
        this.courseResultList = courseResultList;
    }

    public ArrayList<CourseClass> getCourseClassList() {
        return courseClassList;
    }

    public void setCourseClassList(ArrayList<CourseClass> courseClassList) {
        this.courseClassList = courseClassList;
    }

    public ArrayList<StudentClass> getStudentClassList() {
        return studentClassList;
    }

    public void setStudentClassList(ArrayList<StudentClass> studentClassList) {
        this.studentClassList = studentClassList;
    }

    public ArrayList<School> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(ArrayList<School> schoolList) {
        this.schoolList = schoolList;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setListSinhVien(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    /*========================= SEARCH OBJECTS  ==============================================*/
    public Admin findAdmin(String ma) {//tim School theo ma, tra ve 1 School (TC/NC)
        for (Admin a : adminList) {
            if (a.getAdminID().equals(ma)) {
                return a;
            }
        }
        return null;//khong tim thay
    }

    public Program findProgram(String ma) {//tim School theo ma, tra ve 1 School (TC/NC)
        for (Program a : programList) {
            if (a.getMaChuongTrinhHoc().equals(ma)) {
                return a;
            }
        }
        return null;//khong tim thay
    }

    public CourseRegistration findCourseRegistration(String maHocPhan, String maHocKyHeThong) {//tim School theo ma, tra ve 1 School (TC/NC)
        for (CourseRegistration a : courseRegistrationList) {
            if (a.getMaHocPhan().equals(maHocPhan) && (a.getSystemSemesterID()).equals(maHocKyHeThong)) {
                return a;
            }
        }
        return null;//khong tim thay
    }

    public SystemSemester findSystemSemester(String ma) {//tim School theo ma, tra ve 1 School (TC/NC)
        for (SystemSemester a : systemSemesterList) {
            if (a.getSystemSemesterID().equals(ma)) {
                return a;
            }
        }
        return null;//khong tim thay
    }

    public StudentSemester findStudentSemester(String mssv, String maHocKyHeThong) {//tim School theo ma, tra ve 1 School (TC/NC)
        for (StudentSemester a : studentSemesterList) {
            if (a.getMssv().equals(mssv) && (a.getSystemSemesterID()).equals(maHocKyHeThong)) {
                return a;
            }
        }
        return null;//khong tim thay
    }

    public Course findCourse(String ma) {//tim School theo ma, tra ve 1 School (TC/NC)
        for (Course a : courseList) {
            if (a.getMaHocPhan().equals(ma)) {
                return a;
            }
        }
        return null;//khong tim thay
    }

    public CourseResult findCourseResult(String mssv, String maHocPhan, String maHocKyHeThong) {//tim School theo ma, tra ve 1 School (TC/NC)
        for (CourseResult a : courseResultList) {
            if (a.getMssv().equals(mssv) && (a.getMaHocPhan().equals(maHocPhan)) && (a.getSystemSemesterID().equals(maHocKyHeThong))) {
                return a;
            }
        }
        return null;//khong tim thay
    }

    public CourseClass findCourseClass(String ma) {//tim School theo ma, tra ve 1 School (TC/NC)
        for (CourseClass a : courseClassList) {
            if (a.getMaLopMo().equals(ma)) {
                return a;
            }
        }
        return null;//khong tim thay
    }

    public StudentClass findStudentClass(String ma) {//tim School theo ma, tra ve 1 School (TC/NC)
        for (StudentClass a : studentClassList) {
            if (a.getMaLopSinhVien().equals(ma)) {
                return a;
            }
        }
        return null;//khong tim thay
    }

    public School findSchool(String ma) {//tim School theo ma, tra ve 1 School (TC/NC)
        for (School a : schoolList) {
            if (a.getMaNganhHoc().equals(ma)) {
                return a;
            }
        }
        return null;//khong tim thay
    }

    public Student findStudent(String ma) {//tim School theo ma, tra ve 1 School (TC/NC)
        for (Student a : studentList) {
            if (a.getMssv().equals(ma)) {
                return a;
            }
        }
        return null;//khong tim thay
    }

    /*=========================  INITSESSION METHODs : khoi tao cac phien lam viecs  ==============================================*/
    public void initSemesters(DAO dao) {//khoi tao hockyhethong + hockysinhvien neu can
        //khởi tạo khi sang thời điểm đăng ký học phần của kỳ mới
        //có 2 thời điểm mở lớp mới: tháng 11/ tháng 5 (trước batDauHocKy 2 tháng)

        //đọc dữ liệu về học kỳ hệ thống + học kỳ sinh viên của database
        //so sánh với thời điẻm chạy CT, khởi tạo học kỳ hệ thống/ học kỳ sinh viên mới nhất cho từng sinh viên nếu cần
        SystemSemester hocKyHeThongCuoi;
        SystemSemester hocKyHeThongMoiNhat = new SystemSemester();
        StudentSemester hocKySinhVienMoiNhat = new StudentSemester();

        if (this.getSystemSemesterList().isEmpty()) {//nếu trong CSDL chưa có học kỳ hệ thống nào
            if (today.getMonth() + 1 == 11) {//thágn 11 -> khởi tạo học kỳ hệ thống 1
                hocKyHeThongMoiNhat.setMaHocKyHeThong(((String.valueOf(today.getYear() + 1900 + 1))) + "1");
                hocKyHeThongMoiNhat.setBatDauDangKyHocPhan(new Date(today.getYear(), 10, 1));
                hocKyHeThongMoiNhat.setBatDauDangKyLopMo(new Date(today.getYear(), 11, 1));
                hocKyHeThongMoiNhat.setBatDauHocKy(new Date(today.getYear() + 1, 0, 1));
                hocKyHeThongMoiNhat.setKetThucHocKy(new Date(today.getYear() + 1, 6, 1));

            } else {//nếu là kì 2
                if (today.getMonth() + 1 == 5) {//khởi tạo hcọ kỳ hệ thống 2
                    hocKyHeThongMoiNhat.setMaHocKyHeThong(((String.valueOf(today.getYear() + 1900))) + "2");
                    hocKyHeThongMoiNhat.setBatDauDangKyHocPhan(new Date(today.getYear(), 4, 1));
                    hocKyHeThongMoiNhat.setBatDauDangKyLopMo(new Date(today.getYear(), 5, 1));
                    hocKyHeThongMoiNhat.setBatDauHocKy(new Date(today.getYear(), 6, 1));
                    hocKyHeThongMoiNhat.setKetThucHocKy(new Date(today.getYear() + 1, 0, 1));
                }
            }
            this.getSystemSemesterList().add(hocKyHeThongMoiNhat);//add HocKyHeThongMoiNhat vo systemSemesterList cua he thong
            dao.addSystemSemester(hocKyHeThongMoiNhat);//add vô CSDL

            for (Student a : this.getStudentList()) {//khởi tạo và add hocKySinhVien cho các sinh viên
                hocKySinhVienMoiNhat = new StudentSemester(hocKyHeThongMoiNhat.getSystemSemesterID(), a.getMssv(), new ArrayList<>(),
                        new ArrayList<>());
                a.getHocKySinhVien().add(hocKySinhVienMoiNhat);//add vo list hockysinh vien cua sinh vein do
                this.getStudentSemesterList().add(hocKySinhVienMoiNhat);//add vo list hockysinhvien cua he thong

                dao.addStudentSemester(hocKySinhVienMoiNhat);
            }
            JOptionPane.showMessageDialog(null, "Welcome to the new semester - " + hocKyHeThongMoiNhat.getSystemSemesterID() + "!!");

        } else {//CSDL đã có, nhưng đã chuyển sang kỳ mới -> tạo kỳ mới

            hocKyHeThongCuoi = this.getSystemSemesterList().get(this.getSystemSemesterList().size() - 1);//hoc ky cuoi cung trong he thong
            LocalDate ketThucKyCuoi = hocKyHeThongCuoi.getKetThucHocKy().toLocalDate();
            LocalDate batDauDangKyHocPhanKyMoi = ketThucKyCuoi.minusMonths(2);

            if (today.after(Date.valueOf(batDauDangKyHocPhanKyMoi))) {//cần khởi tạp học kỳ cho hệ thống và các sinh viên

                //có 2 thời điểm mở lớp mới: tháng 11/ tháng 5 (trước batDauHocKy 2 tháng)
                if (today.getMonth() + 1 == 11) {//nếu today là tháng 11 -> thời điểm mở đăng ký học phần của kỳ mới 
                    //-> tạo học kỳ hệ thống + học kỳ sinh viên
                    hocKyHeThongMoiNhat.setMaHocKyHeThong(((String.valueOf(today.getYear() + 1900 + 1))) + "1");
                    hocKyHeThongMoiNhat.setBatDauDangKyHocPhan(new Date(today.getYear(), 10, 1));
                    hocKyHeThongMoiNhat.setBatDauDangKyLopMo(new Date(today.getYear(), 11, 1));
                    hocKyHeThongMoiNhat.setBatDauHocKy(new Date(today.getYear() + 1, 0, 1));
                    hocKyHeThongMoiNhat.setKetThucHocKy(new Date(today.getYear() + 1, 6, 1));

                } else {
                    if (today.getMonth() + 1 == 5) {//nếu today là tháng 11 -> thời điểm mở đăng ký học phần của kỳ mới 
                        //nếu là kì 2
                        //-> tạo học kỳ hệ thống + học kỳ sinh viên
                        hocKyHeThongMoiNhat.setMaHocKyHeThong(((String.valueOf(today.getYear() + 1900))) + "2");
                        hocKyHeThongMoiNhat.setBatDauDangKyHocPhan(new Date(today.getYear(), 4, 1));
                        hocKyHeThongMoiNhat.setBatDauDangKyLopMo(new Date(today.getYear(), 5, 1));
                        hocKyHeThongMoiNhat.setBatDauHocKy(new Date(today.getYear(), 6, 1));
                        hocKyHeThongMoiNhat.setKetThucHocKy(new Date(today.getYear() + 1, 0, 1));
                    }
                }

                this.getSystemSemesterList().add(hocKyHeThongMoiNhat);//add HocKyHeThongMoiNhat vo systemSemesterList cua he thong
                dao.addSystemSemester(hocKyHeThongMoiNhat);//add vô CSDL
                //add hockysinh vien
                for (Student a : this.getStudentList()) {
                    hocKySinhVienMoiNhat = new StudentSemester(hocKyHeThongMoiNhat.getSystemSemesterID(), a.getMssv(), new ArrayList<>(),
                            new ArrayList<>());
                    a.getHocKySinhVien().add(hocKySinhVienMoiNhat);//add vo list hockysinh vien cua sinh vein do
                    this.getStudentSemesterList().add(hocKySinhVienMoiNhat);//add vo list hockysinhvien cua he thong
                    dao.addStudentSemester(hocKySinhVienMoiNhat);
                }

                JOptionPane.showMessageDialog(null, "Welcome to the new semester - " + hocKyHeThongMoiNhat.getSystemSemesterID() + "!!!");

            }

        }

    }

    public void initCourseClass(DAO dao) {//khi initCourseClass, thì hocKyHeThong và hocKySinhVien đã tồn tại rồi
        //dụa vào courseRegistrationList để mở lớp
        //mở tự động khi chạy chương trình vào đầu tháng 12/ thágn 5 hàng năm
        SystemSemester hocKyHeThongHienTai = this.getSystemSemesterList().get(this.getSystemSemesterList().size() - 1);//hoc ky hien tai

        if (today.after(hocKyHeThongHienTai.getBatDauDangKyLopMo()) && today.before(hocKyHeThongHienTai.getBatDauHocKy())) {
            //nếu đã sang thời gian đăng ký lớp của học kỳ hiện tại
            if (hocKyHeThongHienTai.getLopMo().isEmpty()) {//neu hoc ky hien tai chua duoc mo lop

                int soSinhVienMoLop = 5;//5 sinh vien dang ky hoc phan -> mo 1 lop
                int soSinhVienDangKyHocPhan;
                int soLopMo = 0;//số lớp mở của 1 học phầnTC hay NC
                int soLopMoTC = 0;//để đếm
                int soLopMoNC = 0;
                CourseClass lopMo;

                for (CourseRegistration a : hocKyHeThongHienTai.getDangKyHocPhan()) {//duyet qua tat cac cac dang ky hoc phan TA và NC
                    soSinhVienDangKyHocPhan = a.getMssvDangKy().size();

                    if (soSinhVienDangKyHocPhan <= soSinhVienMoLop) {
                        soLopMo = 1;
                    } else if ((soSinhVienDangKyHocPhan % soSinhVienMoLop) == 0) {
                        soLopMo = soSinhVienDangKyHocPhan / soSinhVienMoLop;
                    } else if ((soSinhVienDangKyHocPhan % soSinhVienMoLop) != 0) {
                        soLopMo = 1 + soSinhVienDangKyHocPhan / soSinhVienMoLop;
                    }

                    for (int i = 0; i < soLopMo; i++) {
                        //khoi tao so lop mo tuong ung voi so luong dang ky cua tung ho phan, add vo he thong
                        if (this.findCourse(a.getMaHocPhan()) instanceof UnitCourse) {

                            lopMo = new CourseClass("TC_" + hocKyHeThongHienTai.getSystemSemesterID() + "_" + (soLopMoTC + 1),
                                    a.getMaHocPhan(), a.getSystemSemesterID(), soSinhVienMoLop, new ArrayList<>());
                            //add vo he thong
                            this.getCourseClassList().add(lopMo);
                            hocKyHeThongHienTai.getLopMo().add(lopMo);

                            //add vo CSDL
                            dao.addCourseClass(lopMo);
                            soLopMoTC++;
                        } else {
                            lopMo = new CourseClass("NC_" + hocKyHeThongHienTai.getSystemSemesterID() + "_" + (soLopMoNC + 1),
                                    a.getMaHocPhan(), a.getSystemSemesterID(), soSinhVienMoLop, new ArrayList<>());
                            //add vo he thong
                            this.getCourseClassList().add(lopMo);
                            hocKyHeThongHienTai.getLopMo().add(lopMo);
                            //add vo CSDL
                            dao.addCourseClass(lopMo);
                            soLopMoNC++;
                        }

                    }

                }

                JOptionPane.showMessageDialog(null, "Unit and Yearly course classes of " + hocKyHeThongHienTai.getSystemSemesterID()
                        + " are initialized!");

            }
        }

    }

    public void initLoginSession(DAO dao) {//khoi tao phien dang nhap
        LoginView loginView = new LoginView(this);
        loginView.setVisible(true);
    }


    public void initUserSession(String userID) {//khoi tao phien lam viec cho user (svtc/svnc/admin)
        User user = this.findAdmin(userID);
        if (user == null) {//user la sinh vien
            user = findStudent(userID);

            if (user instanceof UnitStudent) {
                UnitStudentView sinhVienTCView = new UnitStudentView(this, ((UnitStudent) user).getMssv());//khoi tao loginView
                sinhVienTCView.setVisible(true);
            } else {

                YearlyStudentView sinhVienNCView = new YearlyStudentView(this, ((YearlyStudent) user).getMssv());//khoi tao loginView
                sinhVienNCView.setVisible(true);
            }
        } else {//user la admin
            AdminView adminView = new AdminView(this, ((Admin) user).getAdminID());
            adminView.setVisible(true);
        }
    }

    /*=========================  CHECK METHODs : check khi sinh vien dang ky hoc tap  ==============================================*/
    public boolean checkCourseRegistrationOfUnitStudent(String mssv, String maHocPhan, String maHocKyHeThong) {
        UnitStudent s = (UnitStudent) this.findStudent(mssv);

        int flag = 0;
        for (Course a : this.findProgram(s.getMaChuongTrinhHoc()).getHocPhan()) {
            if (a.getMaHocPhan().equals(maHocPhan)) {
                flag = 1;
                break;
            }//tìm xem trong chương trình học của sinh viên có học phần đó ko?
        }
        if (flag == 0) {
            JOptionPane.showMessageDialog(null, "Your study program doesn't has this course!");

            return false;//không tìm thấy học phần trong chương trình học của sinh viên 
        }

        for (String a : this.findStudentSemester(mssv, maHocKyHeThong).getMaHocPhanDangKy()) {
            if (a.equals(maHocPhan)) {
                JOptionPane.showMessageDialog(null, "You have registered this course already!");

                return false;//nếu sinh viên đó, kỳ đó đã đăng ký học phần đó rồi
            }
        }
        //check học phần điều kiện	  

        return true;
    }

    public boolean checkCourseClassRegistrationOfUnitStudent(String mssv, String maLopMo, String maHocKyHeThong) {
        //kiểm tra xem sinh viên đó có được đăng ký lớp đó, kỳ đó
        UnitStudent s = (UnitStudent) this.findStudent(mssv);

        if (this.findCourseClass(maLopMo) == null || this.findCourse(this.findCourseClass(maLopMo).getMaHocPhan()) instanceof YearlyCourse) {
            JOptionPane.showMessageDialog(null, "Wrong class!");
            return false;//ma lop mo sai  

        }
        int flag = 0;
        for (String maHocPhan : this.findStudentSemester(mssv, maHocKyHeThong).getMaHocPhanDangKy()) {
            //duyet trong list maHocPhanDangKy cua hockysinh vien
            if (this.findCourseClass(maLopMo).getMaHocPhan().equals(maHocPhan)) {
                flag = 1;
                break;
            }
        }

        if (flag == 0) {
            JOptionPane.showMessageDialog(null, "You have not registered the " + this.findCourseClass(maLopMo).getMaHocPhan() + " course!");
            return false;//sinh vien nay chua dang ky hoc phan cua lop mo
        }

        for (String a : this.findStudentSemester(mssv, maHocKyHeThong).getMaLopMoDangKy()) {
            if (this.findCourseClass(a).getMaHocPhan().equals(this.findCourseClass(maLopMo).getMaHocPhan())) {
                JOptionPane.showMessageDialog(null, "You have registerd a class already for the " + this.findCourseClass(a).getMaHocPhan() + " course!");
                return false;//nếu sinh viên đó, kỳ đó đã đăng ký lop do roi
            }
        }

        return true;
    }

    public boolean checkLopMoCuaHocPhanDieuKien(String mssv, String maHocPhan) {
        //check xem toàn bộ các học phần điều kiện của maHocPhan đã được sinh viên học chưa
        //kiểm tra sinh viên này, đến thời điẻm hiện tại đã học các lớp của cac học phần là điều kiện của hocphan này chưa
        UnitStudent s = (UnitStudent) this.findStudent(mssv);
        UnitCourse h = (UnitCourse) this.findCourse(maHocPhan);//hocPhak muon dang ky
        int soLopMoTimThay = 0;

        for (String maHocPhanDieuKien : h.getMaHocPhanTCDieuKien()) {//duyệt qua các học phần đk của hocPhan muốn học
            for (StudentSemester a : s.getHocKySinhVien()) {//duyệt qua các học kỳ sih viên của sinh viên 
                for (String maLopMo : a.getMaLopMoDangKy()) {//duyệt qua các lớp mở của học kỳ đó
                    if (this.findCourseClass(maLopMo).getMaHocPhan().equals(maHocPhanDieuKien)) {//học phần điều kiện đã được đăng ký lớp
                        soLopMoTimThay++;
                    }
                }
            }
        }

        if (soLopMoTimThay == h.getMaHocPhanTCDieuKien().size()) {//có bao nhiêu học phần điều kiện thì có bấy nhiêu lớp mở mà sinh viên đã học
            return true;
        } else {
            return false;
        }
    }

    /*========================= DANG KY HOC TAP - chỉ có đăng ký học tập của kỳ mới nhất  ==============================================*/
    public boolean registerCourseForUnitStudent(String mssv, String maHocPhan, String maHocKyHeThong, DAO dao) {

        SystemSemester h = this.findSystemSemester(maHocKyHeThong);
        Course hocPhan = this.findCourse(maHocPhan);
        UnitStudent s = (UnitStudent) this.findStudent(mssv);

        if (hocPhan == null || hocPhan instanceof YearlyCourse) {
            JOptionPane.showMessageDialog(null, "Wrong course!");
            return false;
        }
        UnitCourse hptc = (UnitCourse) hocPhan;

        if (!(maHocPhan.equals("") || maHocKyHeThong.equals(""))) {
            //đã nhập input
            if (this.getToday().after(h.getBatDauDangKyHocPhan()) && this.getToday().before(h.getBatDauDangKyLopMo())) {
                //trong thời gian đăng ký học phần
                if (hptc.getMaHocPhanTCDieuKien().isEmpty() || this.checkLopMoCuaHocPhanDieuKien(mssv, maHocPhan)) {
                    //đã học hết các học phần điều kiện
                    if (this.checkCourseRegistrationOfUnitStudent(mssv, maHocPhan, maHocKyHeThong)) {

                        //input chính xác: sinh viên được phép đăng ký học phần này, kỳ này
                        CourseRegistration d = this.findCourseRegistration(maHocPhan, maHocKyHeThong);
                        if (d == null) {//dangkyhocphan chưa tồn tại (chưa có sinh viên nào đăng ký học phần này, kỳ này)
                            //Cap nhat doi tuong
                            d = new CourseRegistration(maHocPhan, maHocKyHeThong, new ArrayList<>());
                            d.getMssvDangKy().add(mssv);
                            this.getCourseRegistrationList().add(d);
                            this.findStudentSemester(mssv, maHocKyHeThong).getMaHocPhanDangKy().add(maHocPhan);

                            //Cap nhat csdl
                            dao.addCourseRegistration(d);
                            dao.addStudentSemesterHasCourse(mssv, maHocKyHeThong, maHocPhan);
                        } else {
                            //Cap nhat doi tuong
                            d.getMssvDangKy().add(mssv);
                            this.findStudentSemester(mssv, maHocKyHeThong).getMaHocPhanDangKy().add(maHocPhan);

                            //Cap nhat csdl
                            dao.updateCourseRegistration(d);
                            dao.addStudentSemesterHasCourse(mssv, maHocKyHeThong, maHocPhan);								//Cap nhat csdl
                        }
                        return true;

                    } else {
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You have not studied the conditional courses of " + maHocPhan + " !");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Course registration time of " + maHocKyHeThong + " is over!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please input semester and course!");
        }

        return false;
    }

    public boolean delelteCourseRegistrationOfUnitStudent(String mssv, String maHocPhan, String maHocKyHeThong, DAO dao) {
        SystemSemester h = this.findSystemSemester(maHocKyHeThong);
        UnitCourse hptc = ((UnitCourse) this.findCourse(maHocPhan));
        UnitStudent s = (UnitStudent) this.findStudent(mssv);
        CourseRegistration d = this.findCourseRegistration(maHocPhan, maHocKyHeThong);

        //đã nhập input
        if (this.getToday().after(h.getBatDauDangKyHocPhan()) && this.getToday().before(h.getBatDauDangKyLopMo())) {
            //trong thời gian đăng ký học phần
            //xoa o he thong
            this.findCourseRegistration(maHocPhan, maHocKyHeThong).getMssvDangKy().remove(s.getMssv());
            this.findStudentSemester(s.getMssv(), maHocKyHeThong).getMaHocPhanDangKy().remove(maHocPhan);
            //xoa o csdl
            if (d.getMssvDangKy().isEmpty()) {//neu dangky hoc phan ko co ai dang ky
                dao.deleteCourseRegistration(d);
                this.getCourseRegistrationList().remove(d);
            } else {
                dao.updateCourseRegistration(d);
            }

            dao.deleteStudentSemesterHasCourse(s.getMssv(), maHocKyHeThong, maHocPhan);
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "Course registration time of " + maHocKyHeThong + " is over!");
            return false;
        }

    }

    public boolean registerCourseClassForUnitStudent(String mssv, String maLopMo, String maHocKyHeThong, DAO dao) {
        SystemSemester h = this.findSystemSemester(maHocKyHeThong);
        CourseClass lopMo;
        UnitStudent s = (UnitStudent) this.findStudent(mssv);

        if (!(maLopMo.equals("") || maHocKyHeThong.equals(""))) {
            //đã nhập input
            if (this.getToday().after(h.getBatDauDangKyLopMo()) && this.getToday().before(h.getBatDauHocKy())) {
                //trong thời gian đăng ký lopMo

                if (this.checkCourseClassRegistrationOfUnitStudent(mssv, maLopMo, maHocKyHeThong)) {
                    lopMo = this.findCourseClass(maLopMo);
                    if (!(lopMo.getSoLuongSinhVienToiDa() == this.findCourseClass(maLopMo).getSinhVien().size())) {

                        // cap nhat thong tin vo university
                        lopMo.getSinhVien().add(this.findStudent(mssv));
                        this.findStudentSemester(mssv, maHocKyHeThong).getMaLopMoDangKy().add(lopMo.getMaLopMo());
                        //cap nhat du lieu vo csdl

                        dao.updateCourseClassRegistration(lopMo);
                        dao.addStudentSemesterHasCourseClass(mssv, maHocKyHeThong, maLopMo);
                        return true;

                    } else {
                        JOptionPane.showMessageDialog(null, "Class " + maLopMo + " is full!");
                    }

                } else {
                }

            } else {
                JOptionPane.showMessageDialog(null, "Class registration time of " + maHocKyHeThong + " is over!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please input semester and class!");
        }
        return false;

    }

    public boolean deleteCourseClassRegistrationOfUnitStudent(String mssv, String maLopMo, String maHocKyHeThong, DAO dao) {

        SystemSemester h = this.findSystemSemester(maHocKyHeThong);
        UnitStudent s = (UnitStudent) this.findStudent(mssv);
        CourseClass l;//đã nhập input
        if (this.getToday().after(h.getBatDauDangKyLopMo()) && this.getToday().before(h.getBatDauHocKy())) {
            //trong thời gian đăng ký học phần
            //xoa o he thong
            this.findCourseClass(maLopMo).getSinhVien().remove(s);
            this.findStudentSemester(s.getMssv(), maHocKyHeThong).getMaLopMoDangKy().remove(maLopMo);
            //xoa o csdl
            l = this.findCourseClass(maLopMo);

            if (l.getSinhVien().isEmpty()) {//neu mop mo ko co ai dang ky
                //khong xoa lop mo
                dao.updateCourseClassRegistration(l);
            } else {
                dao.updateCourseClassRegistration(l);
            }

            dao.deleteStudentSemesterHasCourseClass(s.getMssv(), maHocKyHeThong, maLopMo);
            return true;

        } else {
            JOptionPane.showMessageDialog(null, "Class registration time of " + maHocKyHeThong + " is over!");
            return false;
        }
    }

    public void registerCourseForAllYearlyStudent(DAO dao) {
        /**
         * Đăng ký học phần của kỳ học mới nhất cho toàn bộ sinh viên niên chế
         */
        SystemSemester hocKyHeThongHienTai = this.getSystemSemesterList().get(this.getSystemSemesterList().size() - 1);
        CourseRegistration dangKyHocPhan;//dang ky hoc phan nc

        if (today.after(hocKyHeThongHienTai.getBatDauDangKyHocPhan()) && today.before(hocKyHeThongHienTai.getBatDauDangKyLopMo())) {
            //hom nay trong thoi diem dang ky hoc phan
            int flag = 0;
            for (CourseRegistration d : hocKyHeThongHienTai.getDangKyHocPhan()) {//kiem tra dnag ky hoc phan cua ky hientai
                if (this.findCourse(d.getMaHocPhan()) instanceof YearlyCourse) {
                    flag = 1;//da dang ky hoc phan cho toan bo sinh vien NC o hoc ky hien tai roi
                    break;//chỉ cần 1 dangkyhocphan có học phần là hocphanNC -> đã dky học phần cho toàn bộ svnc rồi
                }
            }

            if (flag == 0) {//day la lan dau tien dang ky cho toan bo sinh vien nc cua ky nay

                for (Student s : this.getStudentList()) {
                    if (s instanceof YearlyStudent) {//chi dang ky cho sinh vien nc

                        int sttHocKyMoiNhat = s.getHocKySinhVien().size();

                        for (Course h : this.findProgram(s.getMaChuongTrinhHoc()).getHocPhan()) {
                            //add nhung hoc phan trong CTH cua sinh vien ma co sttHocKy == stthocKYMoiNhat
                            if (((YearlyCourse) h).getSttHocKy() == sttHocKyMoiNhat) {//neu la hocphan cua ky hien tai

                                dangKyHocPhan = this.findCourseRegistration(h.getMaHocPhan(), hocKyHeThongHienTai.getSystemSemesterID());
                                if (dangKyHocPhan == null) {//neu ky nay chua co sinh vien tc nao dang ky mon nay	
                                    //add vo he thong
                                    dangKyHocPhan = new CourseRegistration(h.getMaHocPhan(), hocKyHeThongHienTai.getSystemSemesterID(), new ArrayList<>());
                                    dangKyHocPhan.getMssvDangKy().add(s.getMssv());
                                    this.getCourseRegistrationList().add(dangKyHocPhan);

                                    this.findStudentSemester(s.getMssv(), hocKyHeThongHienTai.getSystemSemesterID())
                                            .getMaHocPhanDangKy().add(h.getMaHocPhan());

                                    //add vo CSDL
                                    dao.addStudentSemesterHasCourse(s.getMssv(), hocKyHeThongHienTai.getSystemSemesterID(), h.getMaHocPhan());
                                    dao.addCourseRegistration(dangKyHocPhan);
                                } else {
                                    //add vo he thong
                                    dangKyHocPhan.getMssvDangKy().add(s.getMssv());
                                    this.findStudentSemester(s.getMssv(), hocKyHeThongHienTai.getSystemSemesterID())
                                            .getMaHocPhanDangKy().add(h.getMaHocPhan());
                                    //add vo CSDL
                                    dao.addStudentSemesterHasCourse(s.getMssv(), hocKyHeThongHienTai.getSystemSemesterID(), h.getMaHocPhan());
                                    dao.updateCourseRegistration(dangKyHocPhan);

                                }

                            }
                        }

                    }
                }
                JOptionPane.showMessageDialog(null, "Automatically registering courses for all Yearly students done!");

            }

        }
    }

    public void registerCourseClassForAllYearlyStudent(DAO dao) {
        /**
         * Đăng ký lop mo của kỳ học mới nhất cho toàn bộ sinh viên niên chế //
         */

        SystemSemester hocKyHeThongHienTai = this.getSystemSemesterList().get(this.getSystemSemesterList().size() - 1);
        StudentSemester hocKySinhVienHienTai;
        CourseClass lopMo = new CourseClass();

        if (today.after(hocKyHeThongHienTai.getBatDauDangKyLopMo()) && today.before(hocKyHeThongHienTai.getBatDauHocKy())) {
            //hom nay trong thoi diem dang ky lop mo

            int flag = 0;
            for (CourseClass d : hocKyHeThongHienTai.getLopMo()) {
                if (this.findCourse(d.getMaHocPhan()) instanceof YearlyCourse && !d.getSinhVien().isEmpty()) {

                    flag = 1;//da dang ky lop mo cho toan bo sinh vien nc roi
                    //chi can co 1 lop o ky hien tai la hoc phan nc <=> hệ thống đã tự động đăng ký lớp cho các sinh niên nc rồi
                    break;
                }
            }

            if (flag == 0) {//day la lan dau tien dang ky cho toan bo sinh vien nc cua ky nay

                for (Student s : this.getStudentList()) {//duyet qua het tat ca cac sinh vien nc

                    if (s instanceof YearlyStudent) {//chi dang ky cho sinh vien nc

                        hocKySinhVienHienTai = s.getHocKySinhVien().get(s.getHocKySinhVien().size() - 1);//hoc ky moi nhat cua sinh vien do

                        for (String maHocPhan : hocKySinhVienHienTai.getMaHocPhanDangKy()) {//duyệt qua các học phần mà sinh viên đó đã đăng ký
                            //moi hoc phan phai dky 1 lop

                            for (CourseClass a : this.getCourseClassList()) {
                                //kiem tra cac lop mo cua hoc phan do
                                if (a.getSystemSemesterID().equals(hocKyHeThongHienTai.getSystemSemesterID())//cac lop ko cua hy hien tai
                                        && a.getMaHocPhan().equals(maHocPhan)//dung hoc phan
                                        && a.getSinhVien().size() < a.getSoLuongSinhVienToiDa()) {
                                    lopMo = a;//tim duoc lop mo ma chua full
                                    break;
                                }
                            }
                            //add du lieu vo he thong
                            lopMo.getSinhVien().add(s);
                            hocKySinhVienHienTai.getMaLopMoDangKy().add(lopMo.getMaLopMo());

                            //add vo csdl
                            dao.updateCourseClassRegistration(lopMo);
                            dao.addStudentSemesterHasCourseClass(s.getMssv(), hocKyHeThongHienTai.getSystemSemesterID(), lopMo.getMaLopMo());
                        }
                    }
                }

                JOptionPane.showMessageDialog(null, "Automatically registering classes for all Yealy student done!");

            }
        }
    }

    /*==================================== EDUCATION MANAGEMENT ======================================================================*/
    public void editSchool(String schoolID, String newSchoolID, String newSchoolName) {
        DAO dao = new DAO(this);
        School s = this.findSchool(schoolID);

        dao.editSchool(schoolID, newSchoolID, newSchoolName);//chih sua o database

        s.setMaNganhHoc(newSchoolID);
        s.setTenNganhHoc(newSchoolName);

    }

}//class University
