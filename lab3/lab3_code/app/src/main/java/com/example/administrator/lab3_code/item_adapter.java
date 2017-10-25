package com.example.administrator.lab3_code;

import android.content.DialogInterface;
import android.support.v7.util.AsyncListUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/22.
 */

public class item_adapter extends RecyclerView.Adapter<item_adapter.ViewHolder> {

    private ArrayList<Map<String,Object>> data;

    public item_adapter(ArrayList<Map<String,Object>> data) {
        this.data = data;
    }

    public interface OnItemClickListener {
        void onClick(int i);
        void onLongClick(int i);
    }

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener cl) {
        this.mOnItemClickListener = cl;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder;
        holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {
        holder.letter.setText(data.get(i).get("letter").toString());
        holder.name.setText(data.get(i).get("name").toString());
        if(mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClick(holder.getAdapterPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickListener.onLongClick(holder.getAdapterPosition());
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(data == null) {
            return 0;
        }
        else {
            return data.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView letter;
        TextView name;

        public ViewHolder(View v) {
            super(v);
            letter = (TextView) v.findViewById(R.id.letter);
            name = (TextView) v.findViewById(R.id.name);
        }
    }

}
