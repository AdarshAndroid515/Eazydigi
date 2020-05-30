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

public class AttendanceReportForTeacherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;
    public List<AttendanceListResponse.DataBean>  listAttendanceDetailForStudent = new ArrayList<>();
    private static final String TAG = "NoticesAdapter";

    public AttendanceReportForTeacherAdapter(Activity c) {
        this.context = c;
    }

//    public void addAll(List<AttendanceDetailForStudentInfo> list) {
//
//        listAttendanceDetailForStudent.clear();
//        listAttendanceDetailForStudent.addAll(list);
//        notifyDataSetChanged();
//    }

    public void addAll(List<AttendanceListResponse.DataBean>  list) {

        listAttendanceDetailForStudent.clear();
        listAttendanceDetailForStudent.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_attendance_report_teacher, parent, false);

        return new AttendanceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {

            AttendanceViewHolder attendanceViewHolder = (AttendanceViewHolder) holder;

            AttendanceListResponse.DataBean attendanceDetailForStudentInfo = listAttendanceDetailForStudent.get(position);

            attendanceViewHolder.tvRollNo.setText(""+attendanceDetailForStudentInfo.getRollNumber());

            attendanceViewHolder.tvStudentName.setText(attendanceDetailForStudentInfo.getFirstName()+" "+attendanceDetailForStudentInfo.getLastName());

            attendanceViewHolder.tvStatus.setText(attendanceDetailForStudentInfo.getStatus());

            if (attendanceDetailForStudentInfo.getStatus()!=null && !TextUtils.isEmpty(attendanceDetailForStudentInfo.getStatus())){

                if (attendanceDetailForStudentInfo.getStatus().equalsIgnoreCase("2")) {
                    attendanceViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.absent));
                    attendanceViewHolder.tvStatus.setText(context.getString(R.string.absent));
                } else if (attendanceDetailForStudentInfo.getStatus().equalsIgnoreCase("1")) {
                    attendanceViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.present));
                    attendanceViewHolder.tvStatus.setText(context.getString(R.string.present));
                } else if (attendanceDetailForStudentInfo.getStatus().equalsIgnoreCase("3")) {
                    attendanceViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                    attendanceViewHolder.tvStatus.setText(context.getString(R.string.holiday));
                } else if (attendanceDetailForStudentInfo.getStatus().equalsIgnoreCase("0")) {
                    attendanceViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorAccent));
                    attendanceViewHolder.tvStatus.setText(context.getString(R.string.late));
                }else {
                    attendanceViewHolder.tvStatus.setText("NA");
                    attendanceViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.pending));
                }
            }else {
                attendanceViewHolder.tvStatus.setText("NA");
                attendanceViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.pending));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listAttendanceDetailForStudent.size();
    }

    public class AttendanceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_rollno)
        TextView tvRollNo;

        @BindView(R.id.tv_student_name)
        TextView tvStudentName;

        @BindView(R.id.tv_status)
        TextView tvStatus;

        public AttendanceViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}