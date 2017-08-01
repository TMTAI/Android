package ctu.tmtai.com.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ctu.tmtai.com.models.User;
import ctu.tmtai.com.quanlytiendien.R;

/**
 * Created by tranm on 10-Jul-17.
 */

public class UserAdapter extends ArrayAdapter<User> {
    Activity context;
    int resource;
    List<User> objects;

    public UserAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<User> objects) {
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

        TextView txtItemName = (TextView) view.findViewById(R.id.txtItemName);
        TextView txtItemCode = (TextView) view.findViewById(R.id.txtItemCode);
        TextView txtItemPhone = (TextView) view.findViewById(R.id.txtItemPhone);

        User user = this.objects.get(position);
        txtItemName.setText(user.getName());
        txtItemCode.setText(user.getCode());
        txtItemPhone.setText(user.getPhone());

        return view;
    }
}
