package ctu.tmtai.com.util;

/**
 * Created by tranm on 15-Jul-17.
 */

public class Constant {
    public static String USER = "user";
    public static String USER_LIST = "user_list";
    public static String JSON_USER = "json_user";
    public static String BUNDLE_USER = "bundle_user";

    public static String USERNAME = "username";
    public static String LOGINED = "logined";
    public static String REMEMBER_ME = "rememberMe";
    public static String ERROR = "ERROR";
    public static String ROLE_ADMIN = "admin";
    public static String ROLE_CUSTOMER = "customer";
    public static String ROLE_EMPLOYEE = "employee";
    public static String NEW_PASS = "newpass";
    public static String OLD_PASS = "oldpass";
    public static String CONFIRM_PASS = "confirmpass";
    public static String NULL = "null";
    public static String NGAY_SINH = "ngaysinh";
    public static String GENDER_MALE = "nam";
    public static String GENDER_FEMALE = "nu";


    //web service
    public static String HTTP = "http://";
//    public static String HOST = "192.168.1.14";
//    public static String HOST = "192.168.0.104";
    public static String HOST = "172.30.115.65";
    public static String PORT = ":8888/";
    public static String URL = HTTP + HOST + PORT;

    public static String HTTP_GET = URL + "get%s";
    public static String HTTP_ALL = URL + "getAll%s";
    public static String HTTP_NEW_PASSWORD = URL + "newPassword%s";
    public static String HTTP_ALL_KHU_VUC = URL + "getKhuVuc";

    public static String HTTP_UPDATE_USER = URL + "update%s";
    public static String HTTP_ADD = URL + "add%s";
    public static String HTTP_DELETE = URL + "delete%s";

    public static String HTTP_ALL_KHACH_HANG_THEO_KHU_VUC = URL + "getListKhachHang";

    public static String HTTP_GET_DIEN_KE_BY_MA_KH = URL + "getDienKe";
    public static String HTTP_GET_DIEN_KE_BY_MA_NAM = URL + "getDienKeByNam";
    public static String HTTP_UPDATE_CHI_SO_DIEN_KE = URL + "updateChiSoDienKe";

    public static String HTTP_GET_THANG = URL + "getThang";
    public static String HTTP_GET_NAM = URL + "getNam";

    public static String HTTP_GET_LOCATION = "https://maps.googleapis.com/maps/api/geocode/json?&address=%s&key=%s";

    //
    public static String TAB_EMPLOYEE = "tabEmployee";
    public static String TAB_CUSTOMER = "tabCustomer";

    public static String ID = "id";
    public static String MA_KH = "makh";
    public static String TEN_KH = "tenkh";
    public static String DIA_CHI = "diachi";
    public static String ID_KHU_VUC = "id_khuvuc";
    public static String CMND = "cmnd";
    public static String PHONE = "phone";
    public static String GIOI_TINH = "gioitinh";

    public static String TEN_TINH_TP = "tentp";
    public static String TEN_KHU_VUC = "tenkv";
    public static String MA_THANH_PHO = "matp";
    public static String MA_KHU_VUC = "makv";
    public static String KHU_VUC = "khuvuc";
    public static String BUNDLE_KHU_VUC = "khuvuc";

    public static String MA_DK = "madk";
    public static String NGAY_DANG_KY= "ngaydksd";
    public static String CHI_SO_CU= "chisocu";
    public static String MA_THANG= "mathang";
    public static String MA_NAM= "manam";
    public static String CHI_SO_MOI= "chisomoi";
    public static String THANH_TIEN= "thanhtien";
    public static String THANH_TOAN= "thanhtoan";
    public static String TIEU_THU= "tieuthu";
    public static String TU_NGAY= "tungay";
    public static String DEN_NGAY= "denngay";
    public static String VAT= "vat";
    public static String MA_THUE= "mathue";
    public static String IS_THANH_TOAN= "isthanhtoan";

    public static String CODE = "code";
    public static String PASSWORD = "password";
    public static String ADDRESS = "address";
    public static String AVATAR = "avatar";
    public static String ROLE = "role";
    public static String IS_ADMIN = "isAdmin";
    public static String BIRTH_DAY = "birthday";
    public static String GENDER = "gender";
    public static String NAME = "name";
    public static String NAM = "nam";
    public static String NU = "nu";


    //Hoa Don
    public static String MA_HD = "mahd";
    public static String HOA_DON = "HD";

    //Database
    public static String MONGO_ID = "_id";
    public static String DATABASE_NAME = "electric";
    public static String TABLE_KHACH_HANG = "KhachHang";
    public static String TABLE_DIEN_KE = "DienKe";
    public static String TABLE_USER = "User";
    public static String TABLE_HOA_DON = "HoaDon";
    public static String TABLE_KHU_VUC = "khuVuc";
    public static String TABLE_NAM = "Nam";
    public static String TABLE_THANG = "Thang";
    public static String TABLE_TINH_THANH_PHO = "tinh_thanhpho";

}
