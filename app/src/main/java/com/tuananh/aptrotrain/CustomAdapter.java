package com.tuananh.aptrotrain;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Reminder> {
    private Context mContext;
    private ArrayList<Reminder> mArrayList;
    private int mResource;

    public CustomAdapter(@NonNull Context context,
                         int resource,
                         @NonNull ArrayList<Reminder> reminders) {
        super(context, resource, reminders);
        this.mArrayList = reminders;
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        TextView noidung = convertView.findViewById(R.id.tv_content);
        View quantrong = convertView.findViewById(R.id.view_important);
        noidung.setText(mArrayList.get(position).getNoidung());
        quantrong.setBackgroundColor(mArrayList.get(position).isQuantrong() ? Color.RED :
                Color.GREEN);
        return convertView;
    }
}
