package ctu.tmtai.com.notify;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by tranm on 10-Jul-17.
 */

public class Notify{
    private static Notify notify = new Notify();
    public static int LONG = Toast.LENGTH_LONG;
    public static int SHORT = Toast.LENGTH_SHORT;

    public static void showToast(Context context, String str,int time){
        Toast.makeText(context, str, time).show();
    }

    public static void showToast(Context context, int in,int time){
        Toast.makeText(context, in, time).show();
    }

    public static void showToast(Context context, boolean boo,int time){
        Toast.makeText(context, String.valueOf(boo), time).show();
    }
}
