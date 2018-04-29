package cn.edu.gdmec.android.boxuegu.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.utils.MD5Utils;

public class ModifyPswActivity extends Activity implements View.OnClickListener {
    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_save;
    private RelativeLayout title_bar;
    private EditText et_original_psw;
    private EditText et_new_psw;
    private EditText et_new_psw_again;
    private Button btn_save;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psw);
        initView();
        userName = AnalysisUtils.readLoginUserName(this);
    }

    private void initView() {
        tv_back=findViewById(R.id.tv_back);
        tv_main_title=findViewById(R.id.tv_main_title);
        tv_save=findViewById(R.id.tv_save);
        title_bar=findViewById(R.id.title_bar);
        et_original_psw=findViewById(R.id.et_original_psw);
        et_new_psw=findViewById(R.id.et_new_psw);
        et_new_psw_again=findViewById(R.id.et_new_psw_again);
        btn_save=findViewById(R.id.btn_save);
        tv_main_title.setText("修改密码");
        btn_save.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                submit();
                break;
            case R.id.tv_back:
                finish();
                break;
        }
    }

    private void submit() {
        String psw=et_original_psw.getText().toString().trim();
        String newPsw=et_new_psw.getText().toString().trim();
        String again=et_new_psw_again.getText().toString().trim();
        if (TextUtils.isEmpty(psw)){
            Toast.makeText(this,"请输入原始密码",Toast.LENGTH_SHORT).show();
            return;
        }else if (!MD5Utils.md5(psw).equals(readPsw())){
            Log.i("MD5Utils.md5(psw)",""+MD5Utils.md5(psw));
            Log.i("readPsw",""+readPsw());
            Toast.makeText(this,"输入的密码与原始密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }else if (MD5Utils.md5(newPsw).equals(readPsw())){
            Toast.makeText(this,"输入的新密码与原始密码不能一致",Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(psw)){
            Toast.makeText(this,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(again)){
            Toast.makeText(this,"请再次输入新密码",Toast.LENGTH_SHORT).show();
        }else if (!newPsw.equals(again)){
            Toast.makeText(this,"再次输入的新密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }else {
            Toast.makeText(this,"新密码设置成功", Toast.LENGTH_SHORT).show();
            modifyPsw(newPsw);//
            Intent intent=new Intent(ModifyPswActivity.this,LoginActivity.class);
            startActivity(intent);
            //关闭设置页面
            //在submit方法中，密码修改成功之后除了把当前页面关了，还要把设置界面也关了，所以用到instance
            SettingActivity.instance.finish();
            //关闭修改密码页面
            ModifyPswActivity.this.finish();
        }
    }

    private void modifyPsw(String newPsw) {
        String md5psw= MD5Utils.md5(newPsw);
        SharedPreferences sharedPreferences=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(userName,md5psw);
        editor.commit();
    }

    private String readPsw() {
        SharedPreferences sharedPreferences=getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw=sharedPreferences.getString(userName,"");
        Log.i("username",userName);
        Log.i("spPsw",spPsw);
        return spPsw;
    }
}
