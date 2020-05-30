package com.app.eazydigi.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.listener.OnWriteNoteClickListener;
import com.app.eazydigi.model.new_model.response.MarkAttendanceListResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarkAttendanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;
    OnWriteNoteClickListener onWriteNoteClickListener;

    public List<MarkAttendanceListResponse.DataBean> listAttendanceDetail = new ArrayList<>();

    private static final String TAG = "NoticesAdapter";

    public MarkAttendanceAdapter(Activity c) {
        this.context = c;
    }

//    public void addAll(List<AttendanceDetailForStudentInfo> list) {
//
//        listAttendanceDetail.clear();
//        listAttendanceDetail.addAll(list);
//        notifyDataSetChanged();
//    }

    public void addAll(List<MarkAttendanceListResponse.DataBean>  list) {

        listAttendanceDetail.clear();
        listAttendanceDetail.addAll(list);
        notifyDataSetChanged();
    }

    public void setListener(OnWriteNoteClickListener onWriteNoteClickListener) {
        this.onWriteNoteClickListener = onWriteNoteClickListener;


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_attendance, parent, false);

        return new AttendanceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {

            AttendanceViewHolder attendanceViewHolder = (AttendanceViewHolder) holder;

            MarkAttendanceListResponse.DataBean attendanceDetailForStudentInfo = listAttendanceDetail.get(position);

            attendanceViewHolder.tvRollNo.setText("" + attendanceDetailForStudentInfo.getRollNumber());
            attendanceViewHolder.tvStudentName.setText(attendanceDetailForStudentInfo.getFirstName()+" "+attendanceDetailForStudentInfo.getLastName());
            int attendanceStatus = attendanceDetailForStudentInfo.getStatus();

            if (attendanceStatus==2) {
                attendanceViewHolder.rbAbsent.setChecked(true);
            } else if (attendanceStatus==3) {
                attendanceViewHolder.rbHoliday.setChecked(true);
            } else if (attendanceStatus==0) {
                attendanceViewHolder.rbLate.setChecked(true);
            } else if (attendanceStatus==1){
                attendanceViewHolder.rbPresent.setChecked(true);
            }


            if (attendanceDetailForStudentInfo.getNotes() != null &&
                    attendanceDetailForStudentInfo.getNotes().length() > 0) {
                attendanceViewHolder.tvWriteNote.setText("" + attendanceDetailForStudentInfo.getNotes());
            } else {
                attendanceViewHolder.tvWriteNote.setText(context.getString(R.string.write_note));
            }


            attendanceViewHolder.tvWriteNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (onWriteNoteClickListener != null) {

                        onWriteNoteClickListener.onWriteNoteClick2(position,
                                attendanceViewHolder.tvWriteNote.getText().toString().trim(),
                                attendanceDetailForStudentInfo);
                    }

                }
            });

            attendanceViewHolder.rgattendance.setOnCheckedChangeListener(null);

            attendanceViewHolder.rbAbsent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    attendanceDetailForStudentInfo.setStatus(2);
                }
            });

            attendanceViewHolder.rbPresent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attendanceDetailForStudentInfo.setStatus(1);
                }
            });

            attendanceViewHolder.rbLate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attendanceDetailForStudentInfo.setStatus(0);
                }
            });

            attendanceViewHolder.rbHoliday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    attendanceDetailForStudentInfo.setStatus(3);
                }
            });

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

        @BindView(R.id.rg_attendance)
        RadioGroup rgattendance;

        @BindView(R.id.rb_absent)
        RadioButton rbAbsent;

        @BindView(R.id.rb_present)
        RadioButton rbPresent;

        @BindView(R.id.rb_holiday)
        RadioButton rbHoliday;

        @BindView(R.id.rb_late)
        RadioButton rbLate;

        @BindView(R.id.tv_write_note)
        TextView tvWriteNote;

        public AttendanceViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}