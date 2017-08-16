package com.develop.tmtai.travelmanagement;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.develop.tmtai.adapter.MyFragmentPagerAdapter;
import com.develop.tmtai.models.Schedule;
import com.develop.tmtai.services.InternetConectionUtil;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private MyFragmentPagerAdapter adapterViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyFragmentPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
    }


}
