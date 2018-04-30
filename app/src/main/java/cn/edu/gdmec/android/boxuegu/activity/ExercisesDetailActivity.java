package cn.edu.gdmec.android.boxuegu.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.adapter.ExercisesDetailListItemAdapter;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

public class ExercisesDetailActivity extends Activity {
    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout title_bar;
    private TextView tv_save;
    private TextView tv_di;
    private int id;
    private String title;
    private RecyclerView rv_list;

    private List<ExercisesBean> ebl;
    private ExercisesDetailListItemAdapter adapter;
    //
    public  int ff = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        id = getIntent().getIntExtra("id",0);
        title = getIntent().getStringExtra("title");
        ebl = new ArrayList<ExercisesBean>();
        initData();
        initView();
    }

    private void initData() {
        //从xml文件中获取习题数据
        try {
            InputStream is = getResources().getAssets().open("chapter" + id + ".xml");
            ebl = AnalysisUtils.getExercisesInfos(is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        tv_save = findViewById(R.id.tv_save);
        tv_back = findViewById(R.id.tv_back);
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_main_title.setText(title);
        title_bar = findViewById(R.id.title_bar);
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        //
        tv_di = findViewById(R.id.tv_di);
        rv_list = findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExercisesDetailActivity.this.finish();
            }
        });
        adapter = new ExercisesDetailListItemAdapter(ExercisesDetailActivity.this,
                new ExercisesDetailListItemAdapter.OnSelectListener() {
                    @Override
                    public void onSelectA(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                        //判断如果答案不是1即A选项
                        if (ebl.get(position).answer != 1) {
                            ebl.get(position).select = 1;
                        } else {
                            ebl.get(position).select = 0;
                        }
                        switch (ebl.get(position).answer) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 2:
                                iv_a.setImageResource(R.drawable.exercises_error_icon);
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 3:
                                iv_a.setImageResource(R.drawable.exercises_error_icon);
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 4:
                                iv_a.setImageResource(R.drawable.exercises_error_icon);
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }

                    @Override
                    public void onSelectB(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                        //判断如果答案不是2即B选项
                        if (ebl.get(position).answer != 2) {
                            ebl.get(position).select = 2;
                        } else {
                            ebl.get(position).select = 0;
                        }
                        switch (ebl.get(position).answer) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                iv_b.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 2:
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 3:
                                iv_b.setImageResource(R.drawable.exercises_error_icon);
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 4:
                                iv_b.setImageResource(R.drawable.exercises_error_icon);
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }

                    @Override
                    public void onSelectC(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                        //判断如果答案不是3即C选项
                        if (ebl.get(position).answer != 3) {
                            ebl.get(position).select = 3;
                        } else {
                            ebl.get(position).select = 0;
                        }
                        switch (ebl.get(position).answer) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                iv_c.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 2:
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                iv_c.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 3:
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 4:
                                iv_c.setImageResource(R.drawable.exercises_error_icon);
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }

                    @Override
                    public void onSelectD(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
                        //判断如果答案不是4即D选项
                        if (ebl.get(position).answer != 4) {
                            ebl.get(position).select = 4;
                        } else {
                            ebl.get(position).select = 0;
                        }
                        switch (ebl.get(position).answer) {
                            case 1:
                                iv_a.setImageResource(R.drawable.exercises_right_icon);
                                iv_d.setImageResource(R.drawable.exercises_error_icon);
                                break;
                            case 2:
                                iv_d.setImageResource(R.drawable.exercises_error_icon);
                                iv_b.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 3:
                                iv_d.setImageResource(R.drawable.exercises_error_icon);
                                iv_c.setImageResource(R.drawable.exercises_right_icon);
                                break;
                            case 4:
                                iv_d.setImageResource(R.drawable.exercises_right_icon);
                                break;
                        }
                        AnalysisUtils.setABCDEnable(false, iv_a, iv_b, iv_c, iv_d);
                    }
                });
        adapter.setData(ebl);
        adapter.setOnItemListener(new ExercisesDetailListItemAdapter.OnItemListener() {
            @Override
            public void onItem(View view, int position) {
                ff=ff+1;
                tv_di.setText("第"+(position+1)+"题完成，共"+adapter.getItemCount()+"题");

                if (ff==5){
                    AnalysisUtils.saveExerciseStatus(ExercisesDetailActivity.this,id);
                    Log.i("DD",id+"");
                    setResult(RESULT_OK);
                    //ff = 0;
                }
            }

        });
        rv_list.setAdapter(adapter);
    }
}