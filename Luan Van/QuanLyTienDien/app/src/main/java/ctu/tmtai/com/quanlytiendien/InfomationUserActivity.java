package ctu.tmtai.com.quanlytiendien;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ctu.tmtai.com.api.ApiApp;
import ctu.tmtai.com.util.JSONReader;

import static ctu.tmtai.com.util.Constant.HTTP_CODE_USER;
import static ctu.tmtai.com.util.Constant.USERNAME;

public class InfomationUserActivity extends AppCompatActivity implements ApiApp{

    JSONObject jsonObject;
    FloatingActionButton btnChangePassword, btnEditInfomation;
    TextView txtName, txtCode, txtAddress, txtPhone, txtBirthday;
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

        btnChangePassword = (FloatingActionButton) findViewById(R.id.btnChangePassword);

        btnEditInfomation = (FloatingActionButton) findViewById(R.id.btnEditInfomation);
    }

    @Override
    public void addEvents() {
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResetPasswordActivity.class);
                startActivity(intent);
            }
        });

        btnEditInfomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateUserActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        new MyJsonTask().execute(HTTP_CODE_USER + MainActivity.preferences.getString(USERNAME, null));
    }

    // Lớp xử lý đa tiến trình:
    public class MyJsonTask extends AsyncTask<String, JSONObject, Void> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            // Lấy URL truyền vào
            String url = params[0];
            try {

                // đọc và chuyển về JSONObject
                jsonObject = JSONReader.readJsonObjectFromUrl(url);

                publishProgress(jsonObject);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(JSONObject... values) {
            super.onProgressUpdate(values);
            // ta cập nhật giao diện ở đây:
            JSONObject jsonObj = values[0];
            try {
                // kiểm tra xem có tồn tại thuộc tính id hay không
                txtName.setText(jsonObj.getString("name"));
                txtCode.setText(jsonObj.getString("code"));
                txtAddress.setText(jsonObj.getString("addressNumber") + ", " + jsonObj.getString("addressDistrict") + ", " +jsonObj.getString("addressCity"));
                txtPhone.setText(jsonObj.getString("phone"));
                txtBirthday.setText(jsonObj.getString("birthday"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
        }
    }
}
