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

import java.util.List;

import ctu.tmtai.com.models.DienKe;
import ctu.tmtai.com.quanlytiendien.R;

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

        DienKe dienKe = this.objects.get(position);


        if (dienKe != null){
            txtItemThangListDK.setText(dienKe.getMathang());
            txtItemChiSoCuListDK.setText(dienKe.getChisocu().toString());
            txtItemChiSoMoiListDK.setText(dienKe.getChisomoi().toString());
            txtItemSoTien.setText(String.valueOf(dienKe.getThanhtien()).toString());

            if (!dienKe.isThanhtoan()){
                txtItemThangListDK.setTextColor(Color.RED);
                txtItemChiSoCuListDK.setTextColor(Color.RED);
                txtItemChiSoMoiListDK.setTextColor(Color.RED);
                txtItemSoTien.setTextColor(Color.RED);
            }
        }else{

        }

        return view;
    }

}
