package com.app.eazydigi.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.model.new_model.response.AttendanceStudentResponse;
import com.app.eazydigi.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttendanceReportForStudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;

    List<AttendanceStudentResponse.DataBean> listAttendanceByMonth = new ArrayList<>();

    private static final String TAG = "NoticesAdapter";

    public AttendanceReportForStudentAdapter(Activity c) {
        this.context = c;
    }

    public void addAll(List<AttendanceStudentResponse.DataBean> list) {

        listAttendanceByMonth.clear();
        listAttendanceByMonth.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_attendance_by_month, parent, false);

        return new AttendanceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {

            AttendanceViewHolder attendanceViewHolder = (AttendanceViewHolder) holder;

            AttendanceStudentResponse.DataBean studentAttendanceReportByMonthInfo = listAttendanceByMonth.get(position);

            try {
                attendanceViewHolder.tvDate.setText("" + Utils.convertToDateString(studentAttendanceReportByMonthInfo.getDay(),"yyyy-MM-dd","dd-MM-yyyy"));
            }catch (Exception e){
                attendanceViewHolder.tvDate.setText("" + studentAttendanceReportByMonthInfo.getDay());
                e.printStackTrace();
            }
            attendanceViewHolder.tvDay.setVisibility(View.GONE);

            int status= studentAttendanceReportByMonthInfo.getStatus();
            if(status==2){
                attendanceViewHolder.tvStatus.setText(""+context.getString(R.string.absent));
                attendanceViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.absent));
            }else if(status==1){
                attendanceViewHolder.tvStatus.setText(""+context.getString(R.string.present));
                attendanceViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.present));
            }else if(status==3){
                attendanceViewHolder.tvStatus.setText(""+context.getString(R.string.holiday));
                attendanceViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            }else if(status==0){
                attendanceViewHolder.tvStatus.setText(""+context.getString(R.string.late));
                attendanceViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listAttendanceByMonth.size();
    }

    public class AttendanceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_date)
        TextView tvDate;

        @BindView(R.id.tv_day)
        TextView tvDay;

        @BindView(R.id.tv_status)
        TextView tvStatus;



        public AttendanceViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}