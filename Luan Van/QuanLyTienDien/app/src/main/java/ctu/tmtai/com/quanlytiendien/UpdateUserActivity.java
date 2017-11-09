package ctu.tmtai.com.quanlytiendien;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.RadioButton;

import org.jsoup.Jsoup;

import java.io.IOException;

import ctu.tmtai.com.api.ApiApp;
import ctu.tmtai.com.models.User;
import ctu.tmtai.com.notify.Notify;

import static ctu.tmtai.com.util.Constant.ADDRESS;
import static ctu.tmtai.com.util.Constant.BIRTH_DAY;
import static ctu.tmtai.com.util.Constant.BUNDLE_USER;
import static ctu.tmtai.com.util.Constant.CMND;
import static ctu.tmtai.com.util.Constant.CODE;
import static ctu.tmtai.com.util.Constant.GENDER;
import static ctu.tmtai.com.util.Constant.HTTP_NEW_PASSWORD;
import static ctu.tmtai.com.util.Constant.HTTP_UPDATE_USER;
import static ctu.tmtai.com.util.Constant.NAM;
import static ctu.tmtai.com.util.Constant.NAME;
import static ctu.tmtai.com.util.Constant.NEW_PASS;
import static ctu.tmtai.com.util.Constant.NU;
import static ctu.tmtai.com.util.Constant.PHONE;
import static ctu.tmtai.com.util.Constant.ROLE;
import static ctu.tmtai.com.util.Constant.USER;

public class UpdateUserActivity extends AppCompatActivity implements ApiApp {
    private EditText txtUpdateNameUser, txtUpdateBirthdayUser, txtUpdateAddressUserNumber, txtUpdateAddressUserDistrict, txtUpdateAddressUserCity, txtUpdateIdUser, txtUpdatePhoneUser;
    private FloatingActionButton btnUpdateBirthdayUser, btnUpdateUser;
    private RadioButton rdMale, rdFemale;
    private Bundle bundle;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        addControls();
        addEvents();
    }

    @Override
    public void addControls() {
        Intent intent = getIntent();
        bundle = intent.getBundleExtra(BUNDLE_USER);

        txtUpdateNameUser = (EditText) findViewById(R.id.txtUpdateNameUser);
        txtUpdateBirthdayUser = (EditText) findViewById(R.id.txtUpdateBirthdayUser);
        txtUpdateAddressUserNumber = (EditText) findViewById(R.id.txtUpdateAddressUserNumber);
        txtUpdateAddressUserDistrict = (EditText) findViewById(R.id.txtUpdateAddressUserDistrict);
        txtUpdateAddressUserCity = (EditText) findViewById(R.id.txtUpdateAddressUserCity);
        txtUpdateIdUser = (EditText) findViewById(R.id.txtUpdateIdUser);
        txtUpdatePhoneUser = (EditText) findViewById(R.id.txtUpdatePhoneUser);

        rdMale = (RadioButton) findViewById(R.id.rdMale);
        rdFemale = (RadioButton) findViewById(R.id.rdFemale);

        btnUpdateBirthdayUser = (FloatingActionButton) findViewById(R.id.btnUpdateBirthdayUser);
        btnUpdateUser = (FloatingActionButton) findViewById(R.id.btnUpdateUser);

        if (bundle != null){
            user = (User) bundle.getSerializable(USER);
            txtUpdateNameUser.setText(user.getName());
            txtUpdateBirthdayUser.setText(user.getBirthday());
            String[] address = user.getAddress().split(",");

            if (address.length == 3){
                txtUpdateAddressUserNumber.setText(address[0]);
                txtUpdateAddressUserDistrict.setText(address[1]);
                txtUpdateAddressUserCity.setText(address[2]);
            }else if((address.length == 4)){
                txtUpdateAddressUserNumber.setText(address[0]);
                txtUpdateAddressUserDistrict.setText(address[1]+", "+address[2]);
                txtUpdateAddressUserCity.setText(address[3]);
            }

            txtUpdateIdUser.setText(user.getCmnd());
            txtUpdatePhoneUser.setText(user.getPhone());

            if (user.getGender().equalsIgnoreCase(NAM)){
                rdMale.setChecked(true);
            }else{
                rdFemale.setChecked(true);
            }
        }
    }

    @Override
    public void addEvents() {
        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setCode(user.getCode());
                user.setName(txtUpdateNameUser.getText().toString());
                user.setBirthday(txtUpdateBirthdayUser.getText().toString());
                user.setAddress(txtUpdateAddressUserNumber.getText().toString() + ", " + txtUpdateAddressUserDistrict.getText().toString()+", " +txtUpdateAddressUserCity.getText().toString());
                user.setCmnd(txtUpdateIdUser.getText().toString());
                user.setPhone(txtUpdatePhoneUser.getText().toString());
                if (rdMale.isChecked()){
                    user.setGender(NAM);
                }else{
                    user.setGender(NU);
                }
                new UserConnection().execute();
                Notify.showToast(getApplicationContext(), R.string.Update_completed, Notify.SHORT);

                Intent intent = new Intent(UpdateUserActivity.this, InfomationUserActivity.class);
                bundle.putSerializable(USER, user);
                bundle.putString(ROLE, user.getRole());
                intent.putExtra(BUNDLE_USER, bundle);
                startActivity(intent);
            }
        });

        btnUpdateBirthdayUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setUser(User user) {
        boolean check = false;
        if (txtUpdateNameUser.getText().toString() == null || txtUpdateNameUser.getText().toString().equals("")) {
            user.setName(txtUpdateNameUser.getText().toString());
        }
        if (txtUpdateBirthdayUser.getText().toString() == null || txtUpdateBirthdayUser.getText().toString().equals("")) {
            user.setName(txtUpdateBirthdayUser.getText().toString());
        }
        if (txtUpdateAddressUserNumber.getText().toString() == null || txtUpdateAddressUserNumber.getText().toString().equals("")) {
            user.setName(txtUpdateAddressUserNumber.getText().toString());
        }
        if (txtUpdateAddressUserDistrict.getText().toString() == null || txtUpdateAddressUserDistrict.getText().toString().equals("")) {
            user.setName(txtUpdateAddressUserDistrict.getText().toString());
        }
        if (txtUpdateAddressUserCity.getText().toString() == null || txtUpdateAddressUserCity.getText().toString().equals("")) {
            user.setName(txtUpdateAddressUserCity.getText().toString());
        }
        if (txtUpdateIdUser.getText().toString() == null || txtUpdateIdUser.getText().toString().equals("")) {
            user.setName(txtUpdateIdUser.getText().toString());
        }
        if (txtUpdatePhoneUser.getText().toString() == null || txtUpdatePhoneUser.getText().toString().equals("")) {
            user.setName(txtUpdatePhoneUser.getText().toString());
        }
    }

    public class UserConnection extends AsyncTask<String, Object, Void> {
        @Override
        protected Void doInBackground(String... params) {
            try {
                String url = null;
                if (user.isAdmin()){
                    url = String.format(HTTP_UPDATE_USER, "Admin");
                }else{
                    url = String.format(HTTP_UPDATE_USER, "User");
                }
                Jsoup.connect(url)
                        .data(CODE, user.getCode())
                        .data(NAME, user.getName())
                        .data(BIRTH_DAY, user.getBirthday())
                        .data(ADDRESS, user.getAddress())
                        .data(CMND, user.getCmnd())
                        .data(PHONE, user.getPhone())
                        .data(GENDER, user.getGender()).post();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), InfomationUserActivity.class);
        intent.putExtra(BUNDLE_USER, bundle);
        startActivity(intent);
        finish();
    }
}
