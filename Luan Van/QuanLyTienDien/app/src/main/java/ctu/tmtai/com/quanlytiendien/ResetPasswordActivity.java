package ctu.tmtai.com.quanlytiendien;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class ResetPasswordActivity extends AppCompatActivity implements ApiApp{

    EditText txtOldPassword, txtNewPassword, txtConfirmPassword;
    FloatingActionButton btnChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        addControls();
        addEvents();
    }

    @Override
    public void addControls() {
        txtOldPassword = (EditText) findViewById(R.id.txtOldPassword);
        txtNewPassword = (EditText) findViewById(R.id.txtNewPassword);
        txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);

        btnChangePassword = (FloatingActionButton) findViewById(R.id.btnChangePassword);
    }

    @Override
    public void addEvents() {
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = MainActivity.preferences.getString(USERNAME,"null");
                RequestQueue requestQueue = Volley.newRequestQueue(ResetPasswordActivity.this);
                final Gson gson = new GsonBuilder().create();
                StringRequest jsonObjectRequest = new StringRequest(
                        Request.Method.GET,
                        HTTP_CODE_USER+username,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                User user = gson.fromJson(response, User.class);
                                if (txtOldPassword.getText().toString().equals(user.getPassword())){
                                    if (!txtNewPassword.getText().toString().equals("") && txtNewPassword.getText().toString().equals(txtConfirmPassword.getText().toString())){
                                        user.setPassword(txtNewPassword.getText().toString());
                                        UserService.updateUser(user, ResetPasswordActivity.this);
                                        Notify.showToast(getApplicationContext(), R.string.change_susscess, Notify.SHORT);
                                        if (user.isAdmin()){
                                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                                            startActivity(intent);
                                        }else{
                                            if (user.getRole().equalsIgnoreCase(ROLE_EMPLOYEE)){
                                                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                                                startActivity(intent);
                                            }else{
                                                Intent intent = new Intent(getApplicationContext(), CustomerActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    }else{
                                        Notify.showToast(getApplicationContext(), R.string.password_empty, Notify.SHORT);
                                    }
                                }else{
                                    Notify.showToast(getApplicationContext(), R.string.password_invalid, Notify.SHORT);
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
    }
}
