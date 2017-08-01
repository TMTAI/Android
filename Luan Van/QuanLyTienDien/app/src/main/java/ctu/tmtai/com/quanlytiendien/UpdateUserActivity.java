package ctu.tmtai.com.quanlytiendien;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ctu.tmtai.com.api.ApiApp;
import ctu.tmtai.com.models.User;
import ctu.tmtai.com.notify.Notify;
import ctu.tmtai.com.service.UserService;

import static ctu.tmtai.com.util.Constant.HTTP_CODE_USER;
import static ctu.tmtai.com.util.Constant.ROLE_EMPLOYEE;
import static ctu.tmtai.com.util.Constant.USERNAME;

public class UpdateUserActivity extends AppCompatActivity implements ApiApp {
    EditText txtUpdateNameUser, txtUpdateBirthdayUser, txtUpdateAddressUserNumber, txtUpdateAddressUserDistrict, txtUpdateAddressUserCity, txtUpdateIdUser, txtUpdatePhoneUser;
    FloatingActionButton btnUpdateBirthdayUser, btnUpdateUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        addControls();
        addEvents();
    }

    @Override
    public void addControls() {
        txtUpdateNameUser = (EditText) findViewById(R.id.txtUpdateNameUser);
        txtUpdateBirthdayUser = (EditText) findViewById(R.id.txtUpdateBirthdayUser);
        txtUpdateAddressUserNumber = (EditText) findViewById(R.id.txtUpdateAddressUserNumber);
        txtUpdateAddressUserDistrict = (EditText) findViewById(R.id.txtUpdateAddressUserDistrict);
        txtUpdateAddressUserCity = (EditText) findViewById(R.id.txtUpdateAddressUserCity);
        txtUpdateIdUser = (EditText) findViewById(R.id.txtUpdateIdUser);
        txtUpdatePhoneUser = (EditText) findViewById(R.id.txtUpdatePhoneUser);

        btnUpdateBirthdayUser = (FloatingActionButton) findViewById(R.id.btnUpdateBirthdayUser);
        btnUpdateUser = (FloatingActionButton) findViewById(R.id.btnUpdateUser);
    }

    @Override
    public void addEvents() {
        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = MainActivity.preferences.getString(USERNAME, "null");
                RequestQueue requestQueue = Volley.newRequestQueue(UpdateUserActivity.this);
                final Gson gson = new GsonBuilder().create();
                StringRequest jsonObjectRequest = new StringRequest(
                        Request.Method.GET,
                        HTTP_CODE_USER + username,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                User user = gson.fromJson(response, User.class);
                                setUser(user);
                                UserService.updateUser(user, UpdateUserActivity.this);
                                Notify.showToast(getApplicationContext(), R.string.update_susscess, Notify.SHORT);
                                if (user.isAdmin()) {
                                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                    startActivity(intent);
                                } else {
                                    if (user.getRole().equalsIgnoreCase(ROLE_EMPLOYEE)) {
                                        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Notify.showToast(getApplicationContext(), error.toString(), Notify.SHORT);
                            }
                        }
                );
                requestQueue.add(jsonObjectRequest);
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
}
