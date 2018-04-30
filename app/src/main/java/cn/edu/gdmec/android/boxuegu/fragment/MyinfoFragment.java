package cn.edu.gdmec.android.boxuegu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.activity.LoginActivity;
import cn.edu.gdmec.android.boxuegu.activity.SettingActivity;
import cn.edu.gdmec.android.boxuegu.activity.UserInfoActivity;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

public class MyinfoFragment extends Fragment implements View.OnClickListener{

    private LinearLayout llHead;
    private ImageView ivHeadIcon;
    private TextView tvUserName;
    private RelativeLayout rlCourseHistory;
    private ImageView ivCourseHistoryIcon;
    private RelativeLayout rlSetting;
    private ImageView ivUserInfoIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myinfo, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        llHead = (LinearLayout) view.findViewById(R.id.ll_head);
        ivHeadIcon = (ImageView) view.findViewById(R.id.iv_head_icon);
        tvUserName = (TextView) view.findViewById(R.id.tv_user_name);
        rlCourseHistory = (RelativeLayout) view.findViewById(R.id.rl_course_history);
        ivCourseHistoryIcon = (ImageView) view.findViewById(R.id.iv_course_history_icon);
        rlSetting = (RelativeLayout) view.findViewById(R.id.rl_setting);
        ivUserInfoIcon = (ImageView) view.findViewById(R.id.iv_userInfo_icon);

        if (AnalysisUtils.readLoginStatus(getActivity())){
            tvUserName.setText(AnalysisUtils.readLoginUserName(getActivity()));
        }else {
            tvUserName.setText("点击登录");
        }

        llHead.setOnClickListener(this);
        rlCourseHistory.setOnClickListener(this);
        rlSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_head:
                if (AnalysisUtils.readLoginStatus(getActivity())){
                    //跳转到个人资料界面
                    Intent intent=new Intent(getActivity(), UserInfoActivity.class);
                    getActivity().startActivity(intent);
                }else {
                    //跳转到登录界面
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivityForResult(intent,1);
                }
                break;
            case R.id.rl_course_history:
                if (AnalysisUtils.readLoginStatus(getActivity())){
                    //跳转到播放记录页面
                }else {
                    Toast.makeText(getActivity(),"您未登录，请先登录",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_setting:
                if (AnalysisUtils.readLoginStatus(getActivity())){
                    //跳转到设置界面
                    Intent intent=new Intent(getActivity(), SettingActivity.class);
                    getActivity().startActivityForResult(intent,1);
                }else {
                    Toast.makeText(getActivity(),"您未登录，请先登录",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

