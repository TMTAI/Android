package ctu.tmtai.com.models;

/**
 * Created by tranm on 10-Jul-17.
 */

public class User {
    private String code;
    private String password;
    private String electricityMeter;
    private String name;
    private String birthday;
    private String addressNumber;
    private String addressDistrict;
    private String addressCity;
    private String cmnd;
    private String phone;
    private boolean isAdmin;
    private String role;

    public User() {
    }

    public User(String code, String password, String electricityMeter, String name, String birthday, String addressNumber, String addressDistrict, String addressCity, String cmnd, String phone, boolean isAdmin, String role) {
        this.code = code;
        this.password = password;
        this.electricityMeter = electricityMeter;
        this.name = name;
        this.birthday = birthday;
        this.addressNumber = addressNumber;
        this.addressDistrict = addressDistrict;
        this.addressCity = addressCity;
        this.cmnd = cmnd;
        this.phone = phone;
        this.isAdmin = isAdmin;
        this.role = role;
    }

    public User(String code, String password, String electricityMeter, String name, String birthday, String addressNumber, String addressDistrict, String addressCity, String cmnd, String phone) {
        this.code = code;
        this.password = password;
        this.electricityMeter = electricityMeter;
        this.name = name;
        this.birthday = birthday;
        this.addressNumber = addressNumber;
        this.addressDistrict = addressDistrict;
        this.addressCity = addressCity;
        this.cmnd = cmnd;
        this.phone = phone;
        this.isAdmin = false;
        this.role = "customer";
    }

    public User(String code, String password, String name, String birthday, String addressNumber, String addressDistrict, String addressCity, String cmnd, String phone) {
        this.code = code;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.addressNumber = addressNumber;
        this.addressDistrict = addressDistrict;
        this.addressCity = addressCity;
        this.cmnd = cmnd;
        this.phone = phone;
        this.isAdmin = false;
        this.role = "employee";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getElectricityMeter() {
        return electricityMeter;
    }

    public void setElectricityMeter(String electricityMeter) {
        this.electricityMeter = electricityMeter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
