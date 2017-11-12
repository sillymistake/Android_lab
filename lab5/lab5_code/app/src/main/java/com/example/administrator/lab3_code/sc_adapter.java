package com.example.administrator.lab3_code;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextClassification;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2017/10/22.
 */

public class sc_adapter extends BaseAdapter {

    private Context context;
    private List<Map<String,Object>> sc_list;

    public sc_adapter(Context context, List<Map<String,Object>> sc_list) {
        this.context = context;
        this.sc_list = sc_list;
    }

    @Override
    public int getCount() {
        if(sc_list == null) {
            return 0;
        }
        return sc_list.size();
    }

    @Override
    public Object getItem(int i) {
        if(sc_list == null) {
            return null;
        }
        return sc_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView;
        ViewHolder viewHolder;

        if(view == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.shopcar,null);
            viewHolder = new ViewHolder();
            viewHolder.sc_letter = (TextView) convertView.findViewById(R.id.sc_letter);
            viewHolder.sc_name = (TextView) convertView.findViewById(R.id.sc_name);
            viewHolder.sc_price = (TextView) convertView.findViewById(R.id.sc_price);
            convertView.setTag(viewHolder);
        }
        else {
            convertView = view;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.sc_letter.setText(sc_list.get(i).get("sc_letter").toString());
        viewHolder.sc_name.setText(sc_list.get(i).get("sc_name").toString());
        viewHolder.sc_price.setText(sc_list.get(i).get("sc_price").toString());

        return convertView;
    }

    private class ViewHolder {
        public TextView sc_letter;
        public TextView sc_name;
        public TextView sc_price;
    }
}
