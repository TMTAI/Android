package ctu.tranminhtai.ungdung;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by DELL on 9/26/2016.
 */

public class KiemTraDK extends DangKi {
    public void main(String args[])
    {
        Bundle bd=getIntent().getExtras();
        if(bd != null)
        {
            String layht = bd.getString("hoten");
            Intent gui1 = new Intent(getApplicationContext(),NhanDangKi.class);
            gui1.putExtra("hotenkt",gui1.toString());
            startActivity(gui1);
        }
    }
}
