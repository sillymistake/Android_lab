package com.example.administrator.lab2_code;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView mImage;
    RadioGroup mGroup;
    Button mLogin, mRegister;
    TextInputLayout mNumberText, mPassText;
    EditText mNumberEdit, mPassEdit;


    final String[] items = new String[]{"从相册上传","拍照"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImage = (ImageView) findViewById(R.id.imageView);
        mGroup = (RadioGroup) findViewById(R.id.id0);
        mLogin = (Button) findViewById(R.id.button);
        mRegister = (Button) findViewById(R.id.button2);
        mNumberText = (TextInputLayout) findViewById(R.id.textInputLayout1);
        mPassText = (TextInputLayout) findViewById(R.id.textInputLayout2);
        mNumberEdit = mNumberText.getEditText();
        mPassEdit = mPassText.getEditText();

        mImage.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("上传头像")
                                .setItems(
                                        items,
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int i) {
                                                Toast.makeText(MainActivity.this,"您选择了"+"["+items[i]+"]",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                )
                                .setNegativeButton(
                                        "取消",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int i) {
                                                Toast.makeText(MainActivity.this,"您选择了[取消]",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                )
                                .show();
                    }
                }
        );

        mGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    public void onCheckedChanged(RadioGroup g, int checkedId) {
                        if(checkedId == R.id.id1) {
                            Snackbar.make(g, "您选择了学生", Snackbar.LENGTH_SHORT)
                                    .setAction(
                                            "确定",
                                            new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(MainActivity.this,"Snackbar 的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                    )
                                    .show();
                        }
                        if(checkedId == R.id.id2) {
                            Snackbar.make(g, "您选择了教职工", Snackbar.LENGTH_SHORT)
                                    .setAction(
                                            "确定",
                                            new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(MainActivity.this,"Snackbar 的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                    )
                                    .show();
                        }

                    }
                }
        );

        mLogin.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(TextUtils.isEmpty(mNumberEdit.getText().toString())) {
                            mNumberText.setErrorEnabled(true);
                            mNumberText.setError("学号不能为空");
                        }
                        else {
                            mNumberText.setErrorEnabled(false);
                            if(TextUtils.isEmpty(mPassEdit.getText().toString())) {
                                mPassText.setErrorEnabled(true);
                                mPassText.setError("密码不能为空");
                            }
                            else {
                                mPassText.setErrorEnabled(false);
                                if((mNumberEdit.getText().toString().equals("123456")) && mPassEdit.getText().toString().equals("6666")) {
                                    Snackbar.make(v, "登陆成功", Snackbar.LENGTH_SHORT)
                                            .setAction(
                                                    "确定",
                                                    new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            Toast.makeText(MainActivity.this,"Snackbar 的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                            )
                                            .show();
                                }
                                else {
                                    Snackbar.make(v, "学号或密码错误", Snackbar.LENGTH_SHORT)
                                            .setAction(
                                                    "确定",
                                                    new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            Toast.makeText(MainActivity.this,"Snackbar 的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                            )
                                            .show();
                                }
                            }
                        }
                    }
                }
        );

        mRegister.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        if(mGroup.getCheckedRadioButtonId() == R.id.id1) {
                            Snackbar.make(v, "学生注册功能尚未启用", Snackbar.LENGTH_SHORT)
                                    .setAction(
                                            "确定",
                                            new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(MainActivity.this,"Snackbar 的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                    )
                                    .show();

                        }
                        else if(mGroup.getCheckedRadioButtonId() == R.id.id2) {
                            Snackbar.make(v, "教职工注册功能尚未启动", Snackbar.LENGTH_SHORT)
                                    .setAction(
                                            "确定",
                                            new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Toast.makeText(MainActivity.this,"Snackbar 的确定按钮被点击了",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                    )
                                    .show();

                        }
                    }
                }
        );






    }


}
