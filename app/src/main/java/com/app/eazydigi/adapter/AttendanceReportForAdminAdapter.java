package com.app.eazydigi.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.model.new_model.response.AttendanceListResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttendanceReportForAdminAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;

    public List<AttendanceListResponse.DataBean>  listAttendanceDetail = new ArrayList<>();

    private static final String TAG = "AdminAttendanceSheetAda";

    public AttendanceReportForAdminAdapter(Activity c) {
        this.context = c;
    }

    public void addAll(List<AttendanceListResponse.DataBean>  list) {

        listAttendanceDetail.clear();
        listAttendanceDetail.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_admin_attendance_by_date, parent, false);

        return new AttendanceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {

            AttendanceViewHolder attendanceViewHolder = (AttendanceViewHolder) holder;

            AttendanceListResponse.DataBean attendanceDetailForStudentInfo = listAttendanceDetail.get(position);

            attendanceViewHolder.tvRollNo.setText("" + attendanceDetailForStudentInfo.getRollNumber());
            attendanceViewHolder.tvStudentName.setText(attendanceDetailForStudentInfo.getFirstName()+" "+attendanceDetailForStudentInfo.getLastName());

            if (attendanceDetailForStudentInfo.getStatus()!=null && !TextUtils.isEmpty(attendanceDetailForStudentInfo.getStatus())){

                if (attendanceDetailForStudentInfo.getStatus().equalsIgnoreCase("2")) {
                    attendanceViewHolder.tvAttendance.setTextColor(context.getResources().getColor(R.color.absent));
                    attendanceViewHolder.tvAttendance.setText(context.getString(R.string.absent));
                } else if (attendanceDetailForStudentInfo.getStatus().equalsIgnoreCase("1")) {
                    attendanceViewHolder.tvAttendance.setTextColor(context.getResources().getColor(R.color.present));
                    attendanceViewHolder.tvAttendance.setText(context.getString(R.string.present));
                } else if (attendanceDetailForStudentInfo.getStatus().equalsIgnoreCase("3")) {
                    attendanceViewHolder.tvAttendance.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    attendanceViewHolder.tvAttendance.setText(context.getString(R.string.holiday));
                } else if (attendanceDetailForStudentInfo.getStatus().equalsIgnoreCase("0")) {
                    attendanceViewHolder.tvAttendance.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    attendanceViewHolder.tvAttendance.setText(context.getString(R.string.late));
                }else {
                    attendanceViewHolder.tvAttendance.setText("NA");
                    attendanceViewHolder.tvAttendance.setTextColor(context.getResources().getColor(R.color.pending));
                }
            }else {
                attendanceViewHolder.tvAttendance.setText("NA");
                attendanceViewHolder.tvAttendance.setTextColor(context.getResources().getColor(R.color.pending));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listAttendanceDetail.size();
    }

    public class AttendanceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_rollno)
        TextView tvRollNo;

        @BindView(R.id.tv_student_name)
        TextView tvStudentName;

        @BindView(R.id.tv_attendance)
        TextView tvAttendance;

        public AttendanceViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}