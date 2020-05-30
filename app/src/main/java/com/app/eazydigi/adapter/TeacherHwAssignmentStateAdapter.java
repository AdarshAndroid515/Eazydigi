package com.app.eazydigi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.listener.OnTeacherHwAssignmentStateClickListener;
import com.app.eazydigi.model.new_model.response.HomeworkStatusResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeacherHwAssignmentStateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    List<HomeworkStatusResponse> listHwState = new ArrayList<>();


    OnTeacherHwAssignmentStateClickListener onHwAssignmentStateClickListener;

    private static final String TAG = "HwAssignmentStateForTea";

    int itemWidth;


    public TeacherHwAssignmentStateAdapter(Context c, int screenWidth) {
        Log.e(TAG, "@@@@@@@TeacherHwAssignmentStateAdapter: ");
        this.context = c;
        itemWidth = (int) (screenWidth * 0.80);

    }

    public List<HomeworkStatusResponse> getList(){
        return listHwState;
    }

    public void addAll(List<HomeworkStatusResponse> list) {

        try {

            listHwState.clear();

            listHwState.addAll(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();

    }

    public void setListener(OnTeacherHwAssignmentStateClickListener onHwAssignmentStateClickListener) {
        this.onHwAssignmentStateClickListener = onHwAssignmentStateClickListener;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_hw_assignment_state_for_teacher, parent, false);
        if (itemWidth > 0) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(itemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
            itemView.setLayoutParams(lp);
        }

        return new HWAssignmentSateViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        try {

            HWAssignmentSateViewHolder hwAssignmentSateViewHolder = (HWAssignmentSateViewHolder) holder;

            HomeworkStatusResponse hwAssignmentStateForTeacherInfo = listHwState.get(position);

            hwAssignmentSateViewHolder.tvStatus.setText("" + hwAssignmentStateForTeacherInfo.getStatus());
            hwAssignmentSateViewHolder.tvCount.setText("" + hwAssignmentStateForTeacherInfo.getCount());

            hwAssignmentSateViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onHwAssignmentStateClickListener.onHwAssignmentStateClick(hwAssignmentStateForTeacherInfo);
                }
            });

            if(hwAssignmentStateForTeacherInfo.isSelected()){
                hwAssignmentSateViewHolder.tvStatus.setTextColor(Color.WHITE);
                hwAssignmentSateViewHolder.tvCount.setTextColor(Color.WHITE);
                hwAssignmentSateViewHolder.cvHwStateTeacher.setCardBackgroundColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
            }else{
                hwAssignmentSateViewHolder.tvStatus.setTextColor(Color.BLACK);
                hwAssignmentSateViewHolder.tvCount.setTextColor(Color.BLACK);
                hwAssignmentSateViewHolder.cvHwStateTeacher.setCardBackgroundColor(Color.WHITE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return listHwState.size();
    }


    public class HWAssignmentSateViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_count)
        TextView tvCount;

        @BindView(R.id.tv_status)
        TextView tvStatus;

        @BindView(R.id.cv_hw_state_teacher)
        CardView cvHwStateTeacher;

        public HWAssignmentSateViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }


}