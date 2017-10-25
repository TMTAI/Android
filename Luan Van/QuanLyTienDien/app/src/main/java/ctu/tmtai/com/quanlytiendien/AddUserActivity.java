package ctu.tmtai.com.quanlytiendien;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ctu.tmtai.com.api.ApiApp;
import ctu.tmtai.com.models.KhachHang;
import ctu.tmtai.com.models.User;
import ctu.tmtai.com.notify.Notify;
import ctu.tmtai.com.service.UserService;

import static ctu.tmtai.com.util.Constant.ADDRESS;
import static ctu.tmtai.com.util.Constant.BIRTH_DAY;
import static ctu.tmtai.com.util.Constant.BUNDLE_USER;
import static ctu.tmtai.com.util.Constant.CMND;
import static ctu.tmtai.com.util.Constant.CODE;
import static ctu.tmtai.com.util.Constant.DIA_CHI;
import static ctu.tmtai.com.util.Constant.GENDER;
import static ctu.tmtai.com.util.Constant.GENDER_FEMALE;
import static ctu.tmtai.com.util.Constant.GENDER_MALE;
import static ctu.tmtai.com.util.Constant.GIOI_TINH;
import static ctu.tmtai.com.util.Constant.HTTP_ADD;
import static ctu.tmtai.com.util.Constant.HTTP_ALL_KHACH_HANG;
import static ctu.tmtai.com.util.Constant.HTTP_ALL_USER;
import static ctu.tmtai.com.util.Constant.IS_ADMIN;
import static ctu.tmtai.com.util.Constant.MA_DK;
import static ctu.tmtai.com.util.Constant.MA_KH;
import static ctu.tmtai.com.util.Constant.MA_KHU_VUC;
import static ctu.tmtai.com.util.Constant.NAME;
import static ctu.tmtai.com.util.Constant.NGAY_SINH;
import static ctu.tmtai.com.util.Constant.PASSWORD;
import static ctu.tmtai.com.util.Constant.PHONE;
import static ctu.tmtai.com.util.Constant.ROLE;
import static ctu.tmtai.com.util.Constant.ROLE_ADMIN;
import static ctu.tmtai.com.util.Constant.ROLE_CUSTOMER;
import static ctu.tmtai.com.util.Constant.ROLE_EMPLOYEE;
import static ctu.tmtai.com.util.Constant.TEN_KH;
import static ctu.tmtai.com.util.Constant.USER;

public class AddUserActivity extends AppCompatActivity implements ApiApp, TextWatcher {

    EditText txtCodeUser, txtNameUser, txtBirthdayUser, txtAddressUserNumber, txtIdUser, txtPhoneUser, txtElectricityUser;
    AutoCompleteTextView txtAddressUserDistrict, txtAddressUserCity;
    RadioButton rdCustomer, rdEmployee, rdMale, rdFemale;
    FloatingActionButton btnAddUser, btnClearUser;
    ImageButton btnBirthdayUser;
    Calendar cal = Calendar.getInstance();
    private Bundle bundle;
    private User user;
    String code, electricityMeter, name, birthday, addressNumber, addressDistrict, addressCity, id, phone, gender, role;

    JSONArray array = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        new getAllConnection().execute();
        addControls();
        addEvents();
    }

    public void addControls() {
        txtCodeUser = (EditText) findViewById(R.id.txtCodeUser);
        txtBirthdayUser = (EditText) findViewById(R.id.txtBirthdayUser);
        txtNameUser = (EditText) findViewById(R.id.txtNameUser);
        txtAddressUserNumber = (EditText) findViewById(R.id.txtAddressUserNumber);
        txtIdUser = (EditText) findViewById(R.id.txtIdUser);
        txtPhoneUser = (EditText) findViewById(R.id.txtPhoneUser);
        txtElectricityUser = (EditText) findViewById(R.id.txtElectricityUser);

        txtAddressUserDistrict = (AutoCompleteTextView) findViewById(R.id.txtAddressUserDistrict);
        txtAddressUserCity = (AutoCompleteTextView) findViewById(R.id.txtAddressUserCity);

        rdEmployee = (RadioButton) findViewById(R.id.rdEmployee);
        rdCustomer = (RadioButton) findViewById(R.id.rdCustomer);
        rdMale = (RadioButton) findViewById(R.id.rdMale);
        rdFemale = (RadioButton) findViewById(R.id.rdFemale);


        rdEmployee.setChecked(true);
        txtElectricityUser.setVisibility(View.INVISIBLE);

        rdMale.setChecked(true);

        btnAddUser = (FloatingActionButton) findViewById(R.id.btnAddUser);
        btnClearUser = (FloatingActionButton) findViewById(R.id.btnClearUser);
        btnBirthdayUser = (ImageButton) findViewById(R.id.btnBirthdayUser);

        Intent intent = getIntent();

        bundle = intent.getBundleExtra(BUNDLE_USER);
        if (bundle != null) {
            user = (User) bundle.getSerializable(USER);
        }

        String[] arrayTinh = {"TP. Cần Thơ", "Hậu Giang", "TP. HCM"};
        String[] arrayKhuVuc = {"Ninh Kiều", "Bình Thủy", "Cờ Đỏ", "Phú Nhuận"};

        txtAddressUserDistrict.addTextChangedListener(this);
        txtAddressUserCity.addTextChangedListener(this);

        txtAddressUserDistrict.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, arrayKhuVuc
        ));

        txtAddressUserCity.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, arrayTinh
        ));


    }

    public void addEvents() {
        btnClearUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCodeUser.setText("");
                txtBirthdayUser.setText("");
                txtNameUser.setText("");
                txtAddressUserNumber.setText("");
                txtAddressUserDistrict.setText("");
                txtAddressUserCity.setText("");
                txtIdUser.setText("");
                txtPhoneUser.setText("");
                txtElectricityUser.setText("");
            }
        });

        btnBirthdayUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addUser();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        rdCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtElectricityUser.setVisibility(View.VISIBLE);
            }
        });

        rdEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtElectricityUser.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void addUser() throws IOException, JSONException {
        code = txtCodeUser.getText().toString();
        electricityMeter = txtElectricityUser.getText().toString();
        name = txtNameUser.getText().toString();
        birthday = txtBirthdayUser.getText().toString();
        addressNumber = txtAddressUserNumber.getText().toString();
        addressDistrict = txtAddressUserDistrict.getText().toString();
        addressCity = txtAddressUserCity.getText().toString();
        id = txtIdUser.getText().toString();
        phone = txtPhoneUser.getText().toString();
        gender = null;
        if (rdMale.isChecked()) {
            gender = GENDER_MALE;
        } else if (rdFemale.isChecked()) {
            gender = GENDER_FEMALE;
        }

        boolean check = true;
        if (code.equalsIgnoreCase("admin")) {
            check = false;
        } else {
            for (int i = 0; i < array.length(); i++) {
                if (array.getJSONObject(i).getString(ROLE).equals(ROLE_CUSTOMER)) {
                    if (array.getJSONObject(i).getString(MA_KH).equalsIgnoreCase(code)) {
                        check = false;
                        break;
                    }
                } else if (array.getJSONObject(i).getString(ROLE).equals(ROLE_EMPLOYEE)) {
                    if (array.getJSONObject(i).getString(CODE).equalsIgnoreCase(code)) {
                        check = false;
                        break;
                    }
                }
            }
        }

        if (check) {
            if (rdCustomer.isChecked()) {
                new addKhachHangConnection().execute();

            } else if (rdEmployee.isChecked()) {
                new addUserConnection().execute();
            }

            Notify.showToast(getApplicationContext(), R.string.Add_susscess, Notify.SHORT);
            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
            intent.putExtra(BUNDLE_USER, bundle);
            startActivity(intent);
            finish();
        } else {
            Notify.showToast(getApplicationContext(), R.string.user_exist, Notify.SHORT);
        }

    }

    /**
     * Hàm lấy các thông số mặc định khi lần đầu tiền chạy ứng dụng
     */
    public void showDatePickerDialog() {

        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear,
                                  int dayOfMonth) {
                //Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView Date
                txtBirthdayUser.setText(
                        (dayOfMonth) + "/" + (monthOfYear + 1) + "/" + year);
                //Lưu vết lại biến ngày hoàn thành
                cal.set(year, monthOfYear, dayOfMonth);
            }
        };

        //các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
        //sẽ giống với trên TextView khi mở nó lên
        String s = "1/1/1990";
        String strArrtmp[] = s.split("/");
        int date = Integer.parseInt(strArrtmp[0]);
        int month = Integer.parseInt(strArrtmp[1]);
        int year = Integer.parseInt(strArrtmp[2]);
        DatePickerDialog pic = new DatePickerDialog(
                AddUserActivity.this,
                callback, year, month, date);
        pic.setTitle("Chọn ngày hoàn thành");
        pic.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    public class getAllConnection extends AsyncTask<String, JSONObject, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(HTTP_ALL_USER).get();
                String str = document.body().text();

                Document doc = Jsoup.connect(HTTP_ALL_KHACH_HANG).get();
                String strKH = doc.body().text();
                JSONArray jsonArray = new JSONArray(str);
                array = new JSONArray(strKH);

                for (int i = 0; i < jsonArray.length(); i++) {
                    array.put(jsonArray.getJSONObject(i));
                }


            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class addUserConnection extends AsyncTask<String, Object, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String address = addressNumber + ", " + addressDistrict + ", " + addressCity;
            String url = String.format(HTTP_ADD, "User");
            try {
                Jsoup.connect(url)
                        .data(CODE, code)
                        .data(NAME, name)
                        .data(BIRTH_DAY, birthday)
                        .data(ADDRESS, address)
                        .data(CMND, id)
                        .data(PHONE, phone)
                        .data(GENDER, gender).post();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class addKhachHangConnection extends AsyncTask<String, Object, Void> {

        @Override
        protected Void doInBackground(String... params) {
            String makv = getMaKV(addressDistrict, addressCity);
            String url = String.format(HTTP_ADD, "KhachHang");
            try {

                Jsoup.connect(url)
                        .data(MA_KH, code)
                        .data(TEN_KH, name)
                        .data(DIA_CHI, addressNumber)
                        .data(MA_KHU_VUC, makv)
                        .data(MA_DK, electricityMeter)
                        .data(CMND, id)
                        .data(NGAY_SINH, phone)
                        .data(GIOI_TINH, gender)
                        .data(PHONE, phone).post();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private String getMaKV(String addressDistrict, String addressCity) {
        if (addressCity.equalsIgnoreCase("TP. Cần Thơ")) {
            if (addressDistrict.equalsIgnoreCase("Ninh Kiều")) {
                return "KV01";
            } else if (addressDistrict.equalsIgnoreCase("Bình Thủy")) {
                return "KV02";
            } else if (addressDistrict.equalsIgnoreCase("Cờ Đỏ")) {
                return "KV03";
            } else if (addressDistrict.equalsIgnoreCase("Thới Lai")) {
                return "KV03";
            } else {
                return "KV05";
            }
        } else {

        }
        return null;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
