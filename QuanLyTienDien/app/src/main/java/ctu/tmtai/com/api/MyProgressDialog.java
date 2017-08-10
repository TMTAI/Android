package ctu.tmtai.com.api;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by tranm on 01-Aug-17.
 */

public class MyProgressDialog extends ProgressDialog{
    private static ProgressDialog myProgress;

    public MyProgressDialog(Context context){
        super(context);
        //Tạo Progress Bar
        myProgress = new ProgressDialog(context);
        myProgress.setTitle("Loading ...");
        myProgress.setMessage("Please waiting ...");
        myProgress.setCancelable(true);
    }

    /**
     * Hiển thị bảng chờ khi load dữ liệu
     * */
    public void showProgressBar(){
        //Hiển thị Progress Bar
        myProgress.show();
    }

    /**
     * Đóng bảng chờ0.
     * 1111111111111111111111
     * */
    public void closeProgressBar(){
        myProgress.cancel();
    }
}
