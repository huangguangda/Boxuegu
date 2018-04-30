package cn.edu.gdmec.android.boxuegu.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.bean.UserBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.utils.DBUtils;

public class UserInfoActivity extends Activity implements View.OnClickListener{
    //标题栏
    private RelativeLayout title_bar;
    private TextView tv_back;
    private TextView tv_main_title;
    //
    private RelativeLayout rl_head;
    private ImageView iv_head_icon;
    private RelativeLayout rl_account;//
    private TextView tv_user_name;
    private RelativeLayout rl_nickName;
    private TextView tv_nick_name;
    private RelativeLayout rl_sex;
    private TextView tv_sex;
    private RelativeLayout rl_signature;
    private TextView tv_signature;
    private RelativeLayout rl_qq;
    private TextView tv_qq;
    //
    private String spUserName;
    private String new_info;
    private static final int CHANGE_NICKNAME = 1;
    private static final int CHANGE_SIGNATURE = 2;
    private static final int CHANGE_QQ = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        spUserName= AnalysisUtils.readLoginUserName(this);
        initView();
        initDate();
    }
    private void initDate() {
        UserBean bean = null;
        //实例化DBUtils，同时调用其方法获取个人信息资料
        bean = DBUtils.getInstance(this).getUserInfo(spUserName);
        //如果第一次进入，数据库没有保留用户信息
        if (bean == null) {
            bean = new UserBean();
            bean.userName = spUserName;
            bean.nickName = "问答精灵";
            bean.sex = "男";
            bean.signature = "这个人很懒，什么都没留下...";
            bean.qq = "未添加";
            //保存到数据库
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        setValue(bean);
    }
    private void initView() {
        //个人资料界面
        tv_back = findViewById(R.id.tv_back);
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_main_title.setText("个人资料界面");
        title_bar = findViewById(R.id.title_bar);
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        /**
         * rl_head iv_head_icon
         * rl_account tv_user_name
         * rl_nickName tv_nick_name
         * rl_sex tv_sex
         * rl_signature tv_signature
         * rl_qq tv_qq
         */
        rl_head=findViewById(R.id.rl_head);
        iv_head_icon=findViewById(R.id.iv_head_icon);
        rl_account=findViewById(R.id.rl_account);
        tv_user_name=findViewById(R.id.tv_user_name);
        rl_nickName=findViewById(R.id.rl_nickName);
        tv_nick_name=findViewById(R.id.tv_nick_name);
        rl_sex=findViewById(R.id.rl_sex);
        tv_sex=findViewById(R.id.tv_sex);
        rl_signature=findViewById(R.id.rl_signature);
        tv_signature=findViewById(R.id.tv_signature);
        rl_qq=findViewById(R.id.rl_qq);
        tv_qq=findViewById(R.id.tv_qq);
        //
        tv_back.setOnClickListener(this);
        rl_head.setOnClickListener(this);
        rl_account.setOnClickListener(this);
        rl_nickName.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_signature.setOnClickListener(this);
        rl_qq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                this.finish();
                break;
            case R.id.rl_nickName:
                //昵称
                String name = tv_nick_name.getText().toString();
                Bundle bdName = new Bundle();
                bdName.putString("content", name);      //传递界面上的昵称数据
                bdName.putString("title", "昵称");
                bdName.putInt("flag", 1);
                //ActivityUserInfoActivity的Onclick()里昵称和签名响应加上跳转代码
                enterActivityForResult(ChangeUserInfoActivity.class, CHANGE_NICKNAME, bdName);//跳转到修改界面
                break;
            case R.id.rl_sex:
                String sex = tv_sex.getText().toString();
                sexDialog(sex);
                break;
            case R.id.rl_signature:
                //签名
                String signature = tv_signature.getText().toString();
                Bundle bdSignature = new Bundle();
                bdSignature.putString("content", signature); //传递界面上的签名数据
                bdSignature.putString("title", "签名");
                bdSignature.putInt("flag", 2);
                //ActivityUserInfoActivity的Onclick()里昵称和签名响应加上跳转代码
                enterActivityForResult(ChangeUserInfoActivity.class, CHANGE_SIGNATURE, bdSignature);
                break;
            case R.id.rl_qq:
                String qq = tv_qq.getText().toString();
                Bundle bdqq = new Bundle();
                bdqq.putString("content", qq); //传递界面上的签名数据
                bdqq.putString("title", "QQ号");
                bdqq.putInt("flag", 3);
                //ActivityUserInfoActivity的Onclick()里昵称和签名响应加上跳转代码
                enterActivityForResult(ChangeUserInfoActivity.class, CHANGE_QQ, bdqq);
                break;
            default:
                break;
        }
    }

    /**
     * 为界面空间设置值
     **/
    private void setValue(UserBean bean) {
        tv_user_name.setText(bean.userName);
        tv_nick_name.setText(bean.nickName);
        tv_sex.setText(bean.sex);
        tv_signature.setText(bean.signature);
        tv_qq.setText(bean.qq);
    }
    /**
     * 设置性别弹出框
     **/
    private void sexDialog(String sex) {
        int sexFlag = 0;
        if ("男".equals(sex)) {
            sexFlag = 0;
        } else if ("女".equals(sex)) {
            sexFlag = 1;
        }
        final String items[] = {"男", "女"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("性别");
        //sexFlag用来区分显示被选中项，如果sexFlag的值和在数组中的索引严格符合，下方which也可用sexFlag代替
        builder.setSingleChoiceItems(items, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(UserInfoActivity.this, items[which], Toast.LENGTH_SHORT).show();
                setSex(items[which]);
            }
        });
        builder.create().show();
    }
    /**
     * 更新性别数据
     **/
    private void setSex(String sex) {
        tv_sex.setText(sex);
        //更新数据库字段
        DBUtils.getInstance(this).updateUserInfo("sex", sex, spUserName);
    }

    /**
     * 其实就是把需要传递的数值放置于bundle中，bundle作为附加到intent中
     * 获取回传数据是需要的跳转方法，第三个参数标示跳转是传递的数据
     **/
    public void enterActivityForResult(Class<?> to, int requestCode, Bundle b) {
        Intent i = new Intent(this, to);
        i.putExtras(b);
        startActivityForResult(i, requestCode);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHANGE_NICKNAME:
                if (data != null) {
                    new_info = data.getStringExtra("nickName");
                    if (TextUtils.isEmpty(new_info)) {
                        return;
                    }
                    tv_nick_name.setText(new_info);
                    //更新数据库中昵称字段
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("nickName",
                            new_info, spUserName);
                }
                break;
            case CHANGE_SIGNATURE:
                if (data != null) {
                    new_info = data.getStringExtra("signature");
                    if (TextUtils.isEmpty(new_info)) {
                        return;
                    }
                    tv_signature.setText(new_info);
                    //更新数据库中签名字段
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("signature",
                            new_info, spUserName);
                }
                break;
            case CHANGE_QQ:
                if (data != null) {
                    new_info = data.getStringExtra("qq");
                    if (TextUtils.isEmpty(new_info)) {
                        return;
                    }
                    tv_qq.setText(new_info);
                    //更新数据库中QQ字段
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("qq",
                            new_info, spUserName);
                }
                break;
        }
    }
}

