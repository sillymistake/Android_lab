package com.example.administrator.lab3_code;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2017/10/22.
 */

public class detail extends AppCompatActivity {

    String[] items = new String[]{"一键下单", "分享物品", "不感兴趣", "查看更多商品促销信息"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        Intent intent = getIntent();
        final String letter = intent.getStringExtra("letter");
        final String name = intent.getStringExtra("name");
        final String price = intent.getStringExtra("price");
        final String type = intent.getStringExtra("type");
        final String info = intent.getStringExtra("info");

        TextView rname = (TextView) findViewById(R.id.item_name);
        TextView rprice = (TextView) findViewById(R.id.item_price);
        TextView rtype = (TextView) findViewById(R.id.item_type);
        TextView rinfo = (TextView) findViewById(R.id.item_info);

        rname.setText(name);
        rprice.setText(price);
        rtype.setText(type);
        rinfo.setText(info);

        ImageView pic = (ImageView) findViewById(R.id.item_pic);
        switch (name) {
            case "Enchated Forest":
                pic.setImageResource(R.drawable.enchatedforest);
                break;
            case "Arla Milk":
                pic.setImageResource(R.drawable.arla);
                break;
            case "Devondale Milk":
                pic.setImageResource(R.drawable.devondale);
                break;
            case "Kindle Oasis":
                pic.setImageResource(R.drawable.kindle);
                break;
            case "waitrose 早餐麦片":
                pic.setImageResource(R.drawable.waitrose);
                break;
            case "Mcvitie's 饼干":
                pic.setImageResource(R.drawable.mcvitie);
                break;
            case "Ferrero Rocher":
                pic.setImageResource(R.drawable.ferrero);
                break;
            case "Maltesers":
                pic.setImageResource(R.drawable.maltesers);
                break;
            case "Lindt":
                pic.setImageResource(R.drawable.lindt);
                break;
            case "Borggreve":
                pic.setImageResource(R.drawable.borggreve);
                break;
            default:
                break;
        }

        ListView ls = (ListView) findViewById(R.id.ls);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_info, items);
        ls.setAdapter(adapter);

        final int[] flag = {0};
        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sc_intent = new Intent();
                if (flag[0] == 0) {
                    setResult(0, sc_intent);
                    finish();
                } else {
                    sc_intent.putExtra("letter", letter);
                    sc_intent.putExtra("price", price);
                    sc_intent.putExtra("name", name);
                    setResult(0, sc_intent);
                    finish();
                }
            }
        });

        final ImageButton like =(ImageButton) findViewById(R.id.like);
        like.setImageResource(R.drawable.empty_star);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(like.getTag() == "1") {
                    like.setImageResource(R.drawable.empty_star);
                    like.setTag("0");
                }
                else {
                    like.setImageResource(R.drawable.full_star);
                    like.setTag("1");
                }
            }
        });

        ImageView add_sc = (ImageView) findViewById(R.id.add_sc);
        add_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(detail.this,"商品已添加到购物车",Toast.LENGTH_SHORT).show();
                if(flag[0] == 0) {
                    flag[0] = 1;
                }
            }
        });

    }
}
