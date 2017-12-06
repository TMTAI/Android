package ctu.tmtai.com.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import ctu.tmtai.com.models.DienKe;
import ctu.tmtai.com.quanlytiendien.R;
import ctu.tmtai.com.util.MonthConstant;

import static ctu.tmtai.com.util.FormatConstant.CURRENCY_FORMAT;
import static ctu.tmtai.com.util.FormatConstant.VND;

/**
 * Created by tranm on 08-Aug-17.
 */

public class DienKeAdaper extends ArrayAdapter<DienKe> {
    private Activity context;
    private int resource;
    private List<DienKe> objects;

    public DienKeAdaper(@NonNull Activity context, @LayoutRes int resource, @NonNull List<DienKe> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(this.resource, null);

        TextView txtItemThangListDK = (TextView) view.findViewById(R.id.txtItemThangListDK);
        TextView txtItemChiSoCuListDK = (TextView) view.findViewById(R.id.txtItemChiSoCuListDK);
        TextView txtItemChiSoMoiListDK = (TextView) view.findViewById(R.id.txtItemChiSoMoiListDK);
        TextView txtItemSoTien = (TextView) view.findViewById(R.id.txtItemSoTien);
        TextView txtView = (TextView) view.findViewById(R.id.txtView);

        DienKe dienKe = this.objects.get(position);
        String month = "month_" + dienKe.getMathang();
        Integer thang = MonthConstant.getMapThang().get(month);
        NumberFormat number = new DecimalFormat(CURRENCY_FORMAT + " " + VND);

        String oldNumber = context.getResources().getString(R.string.old_number);
        String newNumber = context.getResources().getString(R.string.new_number);
        String money = context.getResources().getString(R.string.money);
        if (dienKe != null){
            txtItemThangListDK.setText(context.getText(thang));
            txtItemChiSoCuListDK.setText(oldNumber + ": " + dienKe.getChisocu().toString());
            txtItemChiSoMoiListDK.setText(newNumber + ": "+ dienKe.getChisomoi().toString());
            txtItemSoTien.setText(money + ": " + number.format((dienKe.getThanhtien() + dienKe.getThanhtien()*0.1)*10));

            if (!dienKe.isThanhtoan()){
                txtItemThangListDK.setTextColor(Color.RED);
                txtItemChiSoCuListDK.setTextColor(Color.RED);
                txtItemChiSoMoiListDK.setTextColor(Color.RED);
                txtItemSoTien.setTextColor(Color.RED);
                txtView.setTextColor(Color.RED);
            }
        }else{

        }

        return view;
    }

}
