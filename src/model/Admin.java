package model;

/*@author hai*/
/**
 * desciption:
 */
public class Admin extends User {

    /*=================== ATTRIBUTEs ==================================*/
    private String adminID;
    private String fullName;
    private String username;
    private String password;

    /*=================== CONSTRUCTORs ================================*/

    public Admin() {
    }

    public Admin(String adminID, String fullName, String username, String password) {
        this.adminID = adminID;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    /*=================== GETTTERs & SETTERs  ============================*/

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getHoTen() {
        return fullName;
    }

    public void setHoTen(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*=================== OTHER METHODs  ===============================*/
}//class Admin
