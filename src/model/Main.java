package model;

/*@author hai*/
/**
*desciption: là class để chạy chương trình
* không phải là hệ thống quản lý sinh viên
*/
public class Main {//chương trình Quản lý sinh viên của WAKAI BUFFALO TEAM
	 
	 /*=================== MAIN ========================*/
	 public static void main(String[] args) {//chuong trinh chinh cua he thong
		  /**
			* khởi tạo hệ thống:
			* "truyền cho nó" kết nối, các phiên làm việc, để nó tương tác với những cái đó
			* university có quyền thay đổi giá trị của những thứ mà mình truyền vào 
			* -> đó là ý nghĩa của việc "truyền tham số vô phương thức của đối tượng nào đó"
			* => TAO CHO PHÉP MÀY THAY ĐỔI GIÁ TRỊ NHỮNG CÁI TAO TRUYỀN CHO MÀY
			* NHƯNG NHỮNG CÁI ĐÓ VẪN THUỘC SỞ HỮU CỦA TAO <=
			*/
		  University university = new University();//khoi tao he thong, de no tu lam viec
	 }

}//class Main
