package com.example.administrator.lab3_code;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class MainActivity extends AppCompatActivity {

    sc_adapter scAdapter;
    item_adapter itemAdapter;
    ArrayList<Map<String,Object>> shopcar = new ArrayList<>();
    ArrayList<Map<String,Object>> item = new ArrayList<>();
    ArrayList<String> index_list = new ArrayList<>();

    final String STATICACTION = "static";
    final int[] flag = {0};
    final String [] Name = new String[] {"Enchated Forest","Arla Milk","Devondale Milk","Kindle Oasis","waitrose 早餐麦片","Mcvitie's 饼干","Ferrero Rocher","Maltesers","Lindt","Borggreve"};
    final String [] Letter = new String[] {"E","A","D","K","W","M","F","M","L","B"};
    final String [] Price = new String[] {"¥ 5.00","¥ 59.00","¥ 79.00","¥ 2399.00","¥ 179.00","¥ 14.90","¥ 132.59","¥ 141.43","¥ 139.43","¥ 28.90"};
    final String [] Type = new String[] {"作者","产地","产地","版本","重量","产地","重量","重量","重量","重量"};
    final String [] Info = new String[] {"Johanna Basford","德国","澳大利亚","8GB","2Kg","英国","300g","118g","249g","640g"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //静态广播
        Random random = new Random();
        int r = random.nextInt(Name.length);
        Bundle bundle = new Bundle();
        bundle.putString("name",Name[r]);
        bundle.putString("price",Price[r]);
        bundle.putString("type",Type[r]);
        bundle.putString("info",Info[r]);
        Intent intentBroadcast = new Intent(STATICACTION);
        intentBroadcast.putExtras(bundle);
        sendBroadcast(intentBroadcast);

        //注册订阅者
        EventBus.getDefault().register(this);


        final RecyclerView item_ls = (RecyclerView) findViewById(R.id.recycler_view);
        final ListView sc_list = (ListView) findViewById(R.id.shopcar);

        final Map<String,Object>title = new LinkedHashMap<>();
        title.put("sc_letter","*");
        title.put("sc_name","购物车");
        title.put("sc_price","价格");
        shopcar.add(title);
        scAdapter = new sc_adapter(this,shopcar);

        sc_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long l) {
                if(position != 0) {
                    String item_name = shopcar.get(position).get("sc_name").toString();
                    int index = index_list.indexOf(item_name);
                    Intent intent = new Intent(MainActivity.this, detail.class);
                    intent.putExtra("letter", Letter[index]);
                    intent.putExtra("name", Name[index]);
                    intent.putExtra("price", Price[index]);
                    intent.putExtra("type", Type[index]);
                    intent.putExtra("info", Info[index]);
                    startActivityForResult(intent, 0);
                }

            }
        });
        sc_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, final int position, long id) {
                if(position != 0) {
                    String item_name = shopcar.get(position).get("sc_name").toString();
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("移除商品")
                            .setMessage("从购物车移除"+item_name+"?")
                            .setNegativeButton("取消",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    shopcar.remove(position);
                                    scAdapter.notifyDataSetChanged();
                                }
                            })
                            .show();
                }
                return true;
            }
        });

        sc_list.setAdapter(scAdapter);
        sc_list.setVisibility(View.INVISIBLE);

        for(int i=0; i<10; i++){
            Map<String,Object>tmp = new LinkedHashMap<>();
            tmp.put("letter",Letter[i]);
            tmp.put("name",Name[i]);
            item.add(tmp);
            index_list.add(Name[i]);
        }
        itemAdapter = new item_adapter(item);
        RecyclerView.LayoutManager itemlayoutmanager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);


        itemAdapter.setOnItemClickListener(new item_adapter.OnItemClickListener() {
            @Override
            public void onClick(int i) {
                String item_name = item.get(i).get("name").toString();
                int index = index_list.indexOf(item_name);
                Intent intent = new Intent(MainActivity.this,detail.class);
                intent.putExtra("letter", Letter[index]);
                intent.putExtra("name", Name[index]);
                intent.putExtra("price", Price[index]);
                intent.putExtra("type", Type[index]);
                intent.putExtra("info", Info[index]);
                startActivityForResult(intent,0);

            }
            @Override
            public void onLongClick(int i) {
                Toast.makeText(MainActivity.this,"移除第"+(i+1)+"个商品",Toast.LENGTH_SHORT).show();
                item.remove(i);
                itemAdapter.notifyItemRemoved(i);

            }
        });

        item_ls.setAdapter(itemAdapter);
        item_ls.setLayoutManager(itemlayoutmanager);

        item_ls.setItemAnimator(new SlideInLeftAnimator());


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag[0] == 0) {
                    item_ls.setVisibility(View.INVISIBLE);
                    sc_list.setVisibility(View.VISIBLE);
                    fab.setImageResource(R.drawable.mainpage);
                    flag[0] = 1;
                }
                else {
                    item_ls.setVisibility(View.VISIBLE);
                    sc_list.setVisibility(View.INVISIBLE);
                    fab.setImageResource(R.drawable.shoplist);
                    flag[0] = 0;
                }
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Map<String,Object>tmp = new LinkedHashMap<>();
        String add_name = event.getMessage();
        int index = index_list.indexOf(add_name);
        tmp.put("sc_letter",Letter[index]);
        tmp.put("sc_name",Name[index]);
        tmp.put("sc_price",Price[index]);
        shopcar.add(tmp);
        scAdapter.notifyDataSetChanged();
    }

    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(resultCode == 0 && requestCode == 0){
            String letter = intent.getStringExtra("letter");
            String price= intent.getStringExtra("price");
            String name = intent.getStringExtra("name");
            if(letter != null && price != null && item != null){
                Map<String,Object>tmp = new LinkedHashMap<>();
                tmp.put("sc_letter",letter);
                tmp.put("sc_name",name);
                tmp.put("sc_price",price);
                shopcar.add(tmp);
                scAdapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }
    */

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getStringExtra("action");
        if(action != null) {
            final RecyclerView item_ls = (RecyclerView) findViewById(R.id.recycler_view);
            final ListView sc_list = (ListView) findViewById(R.id.shopcar);
            final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            item_ls.setVisibility(View.INVISIBLE);
            sc_list.setVisibility(View.VISIBLE);
            fab.setImageResource(R.drawable.mainpage);
            flag[0] = 1;
        }
    }

    protected void onDestory() {
        super.onDestroy();
        //注销订阅者
        EventBus.getDefault().unregister(this);
    }



}
