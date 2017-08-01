package ctu.tmtai.com.quanlytiendien;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;


import org.json.JSONException;

import java.io.IOException;
import java.util.Calendar;

import ctu.tmtai.com.api.ApiApp;
import ctu.tmtai.com.models.User;
import ctu.tmtai.com.service.UserService;

public class AddUserActivity extends AppCompatActivity implements ApiApp {

    EditText txtCodeUser, txtPasswordUser, txtNameUser, txtBirthdayUser, txtAddressUserNumber, txtAddressUserDistrict, txtAddressUserCity, txtIdUser, txtPhoneUser, txtElectricityUser;
    RadioButton rdCustomer, rdEmployee;
    FloatingActionButton btnAddUser, btnClearUser;
    ImageButton btnBirthdayUser;
    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        addControls();
        addEvents();
    }

    public void addControls() {
        txtCodeUser = (EditText) findViewById(R.id.txtCodeUser);
        txtPasswordUser = (EditText) findViewById(R.id.txtPasswordUser);
        txtBirthdayUser = (EditText) findViewById(R.id.txtBirthdayUser);
        txtNameUser = (EditText) findViewById(R.id.txtNameUser);
        txtAddressUserNumber = (EditText) findViewById(R.id.txtAddressUserNumber);
        txtAddressUserDistrict = (EditText) findViewById(R.id.txtAddressUserDistrict);
        txtAddressUserCity = (EditText) findViewById(R.id.txtAddressUserCity);
        txtIdUser = (EditText) findViewById(R.id.txtIdUser);
        txtPhoneUser = (EditText) findViewById(R.id.txtPhoneUser);
        txtElectricityUser = (EditText) findViewById(R.id.txtElectricityUser);

        rdEmployee = (RadioButton) findViewById(R.id.rdEmployee);
        rdCustomer = (RadioButton) findViewById(R.id.rdCustomer);

        btnAddUser = (FloatingActionButton) findViewById(R.id.btnAddUser);
        btnClearUser = (FloatingActionButton) findViewById(R.id.btnClearUser);
        btnBirthdayUser = (ImageButton) findViewById(R.id.btnBirthdayUser);
    }

    public void addEvents() {
        btnClearUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCodeUser.setText("");
                txtPasswordUser.setText("");
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
    }

    private void addUser() throws IOException, JSONException {
        String code = txtCodeUser.getText().toString();
        String password = txtPasswordUser.getText().toString();
        String electricityMeter = txtElectricityUser.getText().toString();
        String name = txtNameUser.getText().toString();
        String birthday = txtBirthdayUser.getText().toString();
        String addressNumber = txtAddressUserNumber.getText().toString();
        String addressDistrict = txtAddressUserDistrict.getText().toString();
        String addressCity = txtAddressUserCity.getText().toString();
        String id = txtIdUser.getText().toString();
        String phone = txtPhoneUser.getText().toString();
        boolean isAdmin = false;
        String role = null;
        if (rdCustomer.isChecked()) {
            role = rdCustomer.getText().toString();
        }
        if (rdEmployee.isChecked()) {
            role = rdEmployee.getText().toString();
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
}
