package ctu.tmtai.com.quanlytiendien;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ctu.tmtai.com.api.ApiApp;
import ctu.tmtai.com.models.KhachHang;
import ctu.tmtai.com.models.User;

import static ctu.tmtai.com.util.Constant.BUNDLE_USER;
import static ctu.tmtai.com.util.Constant.ROLE;
import static ctu.tmtai.com.util.Constant.ROLE_ADMIN;
import static ctu.tmtai.com.util.Constant.ROLE_CUSTOMER;
import static ctu.tmtai.com.util.Constant.USER;

public class InfomationUserActivity extends AppCompatActivity implements ApiApp {

    private static User user = new User();
    private static KhachHang khachHang = new KhachHang();
    Bundle bundle;
    FloatingActionButton btnEditInfomation;
    TextView txtName, txtCode, txtAddress, txtPhone, txtBirthday, txtGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation_user);

        addControls();
        addEvents();
    }

    @Override
    public void addControls() {

        txtName = (TextView) findViewById(R.id.txtName);
        txtCode = (TextView) findViewById(R.id.txtCode);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtPhone = (TextView) findViewById(R.id.txtPhone);
        txtBirthday = (TextView) findViewById(R.id.txtBirthday);
        txtGender = (TextView) findViewById(R.id.txtGender);

        btnEditInfomation = (FloatingActionButton) findViewById(R.id.btnEditInfomation);

        Intent intent = getIntent();

        bundle = intent.getBundleExtra(BUNDLE_USER);
        if (bundle != null) {
            String role = bundle.getString(ROLE);
            if (role.equals(ROLE_CUSTOMER)) {
                khachHang = (KhachHang) bundle.getSerializable(USER);
                txtCode.setText(khachHang.getMakh());
                txtName.setText(khachHang.getTenkh());
                txtAddress.setText(khachHang.getDiachi());
                txtPhone.setText(khachHang.getPhone());
                txtBirthday.setText(khachHang.getNgaysinh());
                txtGender.setText(khachHang.getGioitinh());

                btnEditInfomation.setVisibility(View.INVISIBLE);
            } else {
                user = (User) bundle.getSerializable(USER);

                txtCode.setText(user.getCode());
                txtName.setText(user.getName());
                txtAddress.setText(user.getAddress());
                txtPhone.setText(user.getPhone());
                txtBirthday.setText(user.getBirthday());
                txtGender.setText(user.getGender());

                if (!user.getRole().equals(ROLE_ADMIN)){
                    btnEditInfomation.setVisibility(View.INVISIBLE);
                }
            }
        }

    }

    @Override
    public void addEvents() {
        btnEditInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateUserActivity.class);
                intent.putExtra(BUNDLE_USER, bundle);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        // do something on back.
        if (user != null){
            if (user.getRole().equals(ROLE_ADMIN)){
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra(BUNDLE_USER, bundle);
                startActivity(intent);
                finish();
            }
        }else if (khachHang != null){
            Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);
            intent.putExtra(BUNDLE_USER, bundle);
            startActivity(intent);
            finish();
        }
    }
}
