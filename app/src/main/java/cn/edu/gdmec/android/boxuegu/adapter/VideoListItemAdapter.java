package cn.edu.gdmec.android.boxuegu.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.activity.VideoPlayActivity;
import cn.edu.gdmec.android.boxuegu.bean.VideoBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.utils.DBUtils;

public class VideoListItemAdapter extends BaseAdapter {

    private List<VideoBean> objects = new ArrayList<VideoBean>();
    private int selectedPosition = -1;

    private DBUtils db;
    private Context context;
    private LayoutInflater layoutInflater;

    public VideoListItemAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        db = DBUtils.getInstance(context);
    }
    public void setData(List<VideoBean> vbl){
        this.objects = vbl;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    public void setSelectedPosition(int position){
        selectedPosition = position;
    }

    @Override
    public VideoBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.video_list_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((VideoBean)getItem(position), (ViewHolder) convertView.getTag(), position, convertView);
        return convertView;
    }

    private void initializeViews(final VideoBean object, final ViewHolder holder, final int position, View convertView) {
        holder.ivLeftIcon.setImageResource(R.drawable.course_bar_icon);
        holder.tvVideoTitle.setTextColor(Color.parseColor("#333333"));
        if (object != null){
            holder.tvVideoTitle.setText(object.secondTitle);
            if (selectedPosition == position){
                holder.ivLeftIcon.setImageResource(R.drawable.course_intro_icon);;
                holder.tvVideoTitle.setTextColor(Color.parseColor("#009958"));
            }else {
                holder.ivLeftIcon.setImageResource(R.drawable.course_bar_icon);
                holder.tvVideoTitle.setTextColor(Color.parseColor("#333333"));
            }
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectedPosition(position);
                if (object != null){
                    String videoPath = object.videoPath;
                    notifyDataSetChanged();
                    if (TextUtils.isEmpty(videoPath)){
                        Toast.makeText(context, "本地没有此视频，暂无法播放", Toast.LENGTH_SHORT).show();
                    }else {
                        if (readLoginStatus()){
                            String userName = AnalysisUtils.readLoginUserName(context);
                            db.saveVideoPlayList(objects.get(position), userName);
                        }
                        //视频播放
                        Intent intent = new Intent(context, VideoPlayActivity.class);
                        intent.putExtra("videoPath", videoPath);
                        intent.putExtra("position", position);
                        ((Activity) context).startActivityForResult(intent, 1);
                    }
                }
            }
        });
    }
    private boolean readLoginStatus(){
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }

    protected class ViewHolder {
        private ImageView ivLeftIcon;
        private TextView tvVideoTitle;

        public ViewHolder(View view) {
            ivLeftIcon = (ImageView) view.findViewById(R.id.iv_left_icon);
            tvVideoTitle = (TextView) view.findViewById(R.id.tv_video_title);
        }
    }
}