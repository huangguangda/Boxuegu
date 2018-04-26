package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
/*任务在主界面的Hello World位置显示：用户名+“登录成功”*/
public class MainActivity extends AppCompatActivity {
    private TextView et_user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_user_name = findViewById(R.id.et_user_name);
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            String userName = data.getStringExtra("userName");
            //if (!TextUtils.isEmpty(userName)){
            Toast.makeText(MainActivity.this,"登陆成功："+userName, Toast.LENGTH_SHORT).show();
            et_user_name.setText(userName);
            //}
        }
    }
}
