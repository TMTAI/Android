package ctu.tmtai.com.util;

/**
 * Created by tranm on 15-Jul-17.
 */

public class Constant {
    public static String CODE = "code";
    public static String USERNAME = "username";
    public static String PASSWORD = "password";
    public static String ROLE = "role";
    public static String LOGINED = "logined";
    public static String IS_ADMIN = "isAdmin";
    public static String ALL_DATA_USER_LOGIN = "allDataUserLogin";
    public static String REMEMBER_ME = "rememberMe";

    public static String ROLE_ADMIN = "admin";
    public static String ROLE_CUSTOMER = "customer";
    public static String ROLE_EMPLOYEE = "employee";

    public static String NULL = "null";

    //web service
    public static String HTTP = "http://";
    public static String HOST = "192.168.0.108";
    public static String PORT = ":8888/";
    public static String URL = HTTP + HOST + PORT;
    public static String HTTP_ALL_USER = URL + "getAllUsers";
    public static String HTTP_ALL_KHACH_HANG = URL + "getAllKhachHang";
    public static String HTTP_CODE_USER = URL+ "rest/users/code/";
    public static String HTTP_ADD_USER = URL + "rest/user/add/";
    public static String HTTP_UPDATE_USER = URL + "rest/user/update/";

    //
    public static String TAB_EMPLOYEE = "tabEmployee";
    public static String TAB_CUSTOMER = "tabCustomer";

}
