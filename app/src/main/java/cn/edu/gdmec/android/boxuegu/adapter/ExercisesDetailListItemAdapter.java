package cn.edu.gdmec.android.boxuegu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

public class ExercisesDetailListItemAdapter extends
        RecyclerView.Adapter<ExercisesDetailListItemAdapter.ViewHolder> {
    private List<ExercisesBean> objects = new ArrayList<ExercisesBean>();
    private ArrayList<String> selectedPosition = new ArrayList<String>();
    private Context context;
    private LayoutInflater layoutInflater;
    private OnSelectListener onSelectListener;
    private OnItemListener onItemListener;

    public ExercisesDetailListItemAdapter(Context context, OnSelectListener onSelectListener) {
        this.context = context;
        this.onSelectListener = onSelectListener;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.exercises_detail_list_item, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        initializeViews(objects.get(position), holder, position);
    }

    public void setData(List<ExercisesBean> objects) {
        this.objects = objects;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    private void initializeViews(ExercisesBean object, final ViewHolder holder,
                                 final int position) {
        ExercisesBean bean = object;
        if (bean != null) {
            holder.tvSubject.setText(bean.subject);
            holder.tvA.setText(bean.a);
            holder.tvB.setText(bean.b);
            holder.tvC.setText(bean.c);
            holder.tvD.setText(bean.d);
        }
        if (!selectedPosition.contains("" + position)) {
            holder.ivA.setImageResource(R.drawable.exercises_a);
            holder.ivB.setImageResource(R.drawable.exercises_b);
            holder.ivC.setImageResource(R.drawable.exercises_c);
            holder.ivD.setImageResource(R.drawable.exercises_d);
            AnalysisUtils.setABCDEnable(true, holder.ivA, holder.ivB, holder.ivC,
                    holder.ivD);
        } else {
            AnalysisUtils.setABCDEnable(false, holder.ivA, holder.ivB, holder.ivC,
                    holder.ivD);
            switch (bean.select) {
                case 0:
                    if (bean.answer == 1) {
                        holder.ivA.setImageResource(R.drawable.exercises_right_icon);
                        holder.ivB.setImageResource(R.drawable.exercises_b);
                        holder.ivC.setImageResource(R.drawable.exercises_c);
                        holder.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 2) {
                        holder.ivA.setImageResource(R.drawable.exercises_a);
                        holder.ivB.setImageResource(R.drawable.exercises_right_icon);
                        holder.ivC.setImageResource(R.drawable.exercises_c);
                        holder.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 3) {
                        holder.ivA.setImageResource(R.drawable.exercises_a);
                        holder.ivB.setImageResource(R.drawable.exercises_b);
                        holder.ivC.setImageResource(R.drawable.exercises_right_icon);
                        holder.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 4) {
                        holder.ivA.setImageResource(R.drawable.exercises_a);
                        holder.ivB.setImageResource(R.drawable.exercises_b);
                        holder.ivC.setImageResource(R.drawable.exercises_c);
                        holder.ivD.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 1:
                    holder.ivA.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.answer == 2) {
                        holder.ivB.setImageResource(R.drawable.exercises_right_icon);
                        holder.ivC.setImageResource(R.drawable.exercises_c);
                        holder.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 3) {
                        holder.ivB.setImageResource(R.drawable.exercises_b);
                        holder.ivC.setImageResource(R.drawable.exercises_right_icon);
                        holder.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 4) {
                        holder.ivB.setImageResource(R.drawable.exercises_b);
                        holder.ivC.setImageResource(R.drawable.exercises_c);
                        holder.ivD.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 2:
                    holder.ivB.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.answer == 1) {
                        holder.ivA.setImageResource(R.drawable.exercises_right_icon);
                        holder.ivC.setImageResource(R.drawable.exercises_c);
                        holder.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 3) {
                        holder.ivA.setImageResource(R.drawable.exercises_a);
                        holder.ivC.setImageResource(R.drawable.exercises_right_icon);
                        holder.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 4) {
                        holder.ivA.setImageResource(R.drawable.exercises_a);
                        holder.ivC.setImageResource(R.drawable.exercises_c);
                        holder.ivD.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 3:
                    holder.ivC.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.answer == 1) {
                        holder.ivA.setImageResource(R.drawable.exercises_right_icon);
                        holder.ivB.setImageResource(R.drawable.exercises_b);
                        holder.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 2) {
                        holder.ivA.setImageResource(R.drawable.exercises_a);
                        holder.ivB.setImageResource(R.drawable.exercises_right_icon);
                        holder.ivD.setImageResource(R.drawable.exercises_d);
                    } else if (bean.answer == 4) {
                        holder.ivA.setImageResource(R.drawable.exercises_a);
                        holder.ivB.setImageResource(R.drawable.exercises_b);
                        holder.ivD.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;
                case 4:
                    holder.ivD.setImageResource(R.drawable.exercises_error_icon);
                    if (bean.answer == 1) {
                        holder.ivA.setImageResource(R.drawable.exercises_right_icon);
                        holder.ivB.setImageResource(R.drawable.exercises_b);
                        holder.ivC.setImageResource(R.drawable.exercises_c);
                    } else if (bean.answer == 2) {
                        holder.ivA.setImageResource(R.drawable.exercises_a);
                        holder.ivB.setImageResource(R.drawable.exercises_right_icon);
                        holder.ivC.setImageResource(R.drawable.exercises_c);
                    } else if (bean.answer == 3) {
                        holder.ivA.setImageResource(R.drawable.exercises_a);
                        holder.ivB.setImageResource(R.drawable.exercises_b);
                        holder.ivC.setImageResource(R.drawable.exercises_right_icon);
                    }
                    break;

            }

        }
        holder.ivA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemListener!=null){
                    onItemListener.onItem(v,position);
                }
                if (selectedPosition.contains("" + position)) {
                    selectedPosition.remove("" + position);
                } else {
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectA(position, holder.ivA,
                        holder.ivB, holder.ivC, holder.ivD);
            }
        });
        holder.ivB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemListener!=null){
                    onItemListener.onItem(v,position);
                }
                if (selectedPosition.contains("" + position)) {
                    selectedPosition.remove("" + position);
                } else {
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectB(position, holder.ivA,
                        holder.ivB, holder.ivC, holder.ivD);
            }
        });
        holder.ivC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemListener!=null){
                    onItemListener.onItem(v,position);
                }
                if (selectedPosition.contains("" + position)) {
                    selectedPosition.remove("" + position);
                } else {
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectC(position, holder.ivA,
                        holder.ivB, holder.ivC, holder.ivD);
            }
        });
        holder.ivD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemListener!=null){
                    onItemListener.onItem(v,position);
                }
                if (selectedPosition.contains("" + position)) {
                    selectedPosition.remove("" + position);
                } else {
                    selectedPosition.add(position + "");
                }
                onSelectListener.onSelectD(position, holder.ivA,
                        holder.ivB, holder.ivC, holder.ivD);
            }
        });

    }


    protected class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSubject;
        private ImageView ivA;
        private TextView tvA;
        private ImageView ivB;
        private TextView tvB;
        private ImageView ivC;
        private TextView tvC;
        private ImageView ivD;
        private TextView tvD;

        public ViewHolder(View view) {
            super(view);
            tvSubject = (TextView) view.findViewById(R.id.tv_subject);
            ivA = (ImageView) view.findViewById(R.id.iv_a);
            tvA = (TextView) view.findViewById(R.id.tv_a);
            ivB = (ImageView) view.findViewById(R.id.iv_b);
            tvB = (TextView) view.findViewById(R.id.tv_b);
            ivC = (ImageView) view.findViewById(R.id.iv_c);
            tvC = (TextView) view.findViewById(R.id.tv_c);
            ivD = (ImageView) view.findViewById(R.id.iv_d);
            tvD = (TextView) view.findViewById(R.id.tv_d);
        }
    }

    public interface OnSelectListener {
        void onSelectA(int position, ImageView iv_a,
                       ImageView iv_b, ImageView iv_c,
                       ImageView iv_d);

        void onSelectB(int position, ImageView iv_a,
                       ImageView iv_b, ImageView iv_c,
                       ImageView iv_d);

        void onSelectC(int position, ImageView iv_a,
                       ImageView iv_b, ImageView iv_c,
                       ImageView iv_d);

        void onSelectD(int position, ImageView iv_a,
                       ImageView iv_b, ImageView iv_c,
                       ImageView iv_d);
    }
    public interface OnItemListener{
        void onItem(View view,int position);
    }
    public void setOnItemListener(OnItemListener onItemListener){
        this.onItemListener=onItemListener;
    }
}