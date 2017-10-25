package ctu.tmtai.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ctu.tmtai.com.models.KhachHang;
import ctu.tmtai.com.models.User;
import ctu.tmtai.com.quanlytiendien.R;

/**
 * Created by tranm on 15-Jul-17.
 */

public class CustomerAdapter<K> extends ArrayAdapter<KhachHang> {
    Activity context;
    int resource;
    List<KhachHang> objects;

    public CustomerAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<KhachHang> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(this.resource, null);

        TextView txtItemCustomerName = (TextView) view.findViewById(R.id.txtItemCustomerName);
        TextView txtItemCustomerAddress = (TextView) view.findViewById(R.id.txtItemCustomerAddress);
        TextView txtItemCustomerPhone = (TextView) view.findViewById(R.id.txtItemCustomerPhone);

        KhachHang user = this.objects.get(position);

        txtItemCustomerName.setText(user.getTenkh());
        txtItemCustomerAddress.setText(user.getDiachi());
        txtItemCustomerPhone.setText(user.getPhone());

        return view;
    }
}
