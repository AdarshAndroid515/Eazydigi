package com.app.eazydigi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.listener.OnUpdateHwAssignmentByStudentClickListener;
import com.app.eazydigi.model.new_model.response.HomeworkDashboardStudentResponse;
import com.app.eazydigi.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentHwAssignmentByStatusAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    List<HomeworkDashboardStudentResponse.DataBean.MyHomeworkDetailListBean> listHwStatus = new ArrayList<>();

    OnUpdateHwAssignmentByStudentClickListener onUpdateStudentHwAssignmentClickListener;
    private static SparseBooleanArray sSelectedItems = new SparseBooleanArray();

    public StudentHwAssignmentByStatusAdapter(Context c) {
        this.context = c;

    }
//    public List<StudentHWAssignmentByStatusInfo> getList(){
//        return listHwStatus;
//    }

    public void addAll(List<HomeworkDashboardStudentResponse.DataBean.MyHomeworkDetailListBean> list) {

        try {

            listHwStatus.clear();

            listHwStatus.addAll(list);

            notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();

    }

    public void setListener(OnUpdateHwAssignmentByStudentClickListener onUpdateStudentHwAssignmentClickListener) {
        this.onUpdateStudentHwAssignmentClickListener = onUpdateStudentHwAssignmentClickListener;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_student_hw_assignment_by_status, parent, false);
        return new HWAssignmentSateViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {

            HWAssignmentSateViewHolder hwAssignmentSateViewHolder = (HWAssignmentSateViewHolder) holder;

            HomeworkDashboardStudentResponse.DataBean.MyHomeworkDetailListBean studentHWAssignmentByStatusInfo = listHwStatus.get(position);


            hwAssignmentSateViewHolder.tvTeacherName.setText("" +studentHWAssignmentByStatusInfo.getTeacherFName()+" "+studentHWAssignmentByStatusInfo.getTeacherLName());
            hwAssignmentSateViewHolder.tvStatus.setText("" +studentHWAssignmentByStatusInfo.getStatus());
            hwAssignmentSateViewHolder.tvHomeworkHeader.setText("" + studentHWAssignmentByStatusInfo.getHeader());
            hwAssignmentSateViewHolder.tvHomeworkDetail.setText("" + studentHWAssignmentByStatusInfo.getDescription());
            //hwAssignmentSateViewHolder.tvAssignedOn.setText("" + Utils.convertToDateString(studentHWAssignmentByStatusInfo.getAssignedOn(), "yyyy-MM-dd'T'HH:mm:ss", "dd MMM, yyyy"));
            hwAssignmentSateViewHolder.tvDueDate.setText("" +  Utils.convertToDateString(studentHWAssignmentByStatusInfo.getDueDate(),"yyyy-mm-dd","dd MMM,yyyy"));

            if (studentHWAssignmentByStatusInfo.getSubject().equals("1")){
                hwAssignmentSateViewHolder.tvSubject.setText("English");
            }else if (studentHWAssignmentByStatusInfo.getSubject().equals("2")){
                hwAssignmentSateViewHolder.tvSubject.setText("Science");
            }else if (studentHWAssignmentByStatusInfo.getSubject().equals("3")){
                hwAssignmentSateViewHolder.tvSubject.setText("Social Science");
            }else if (studentHWAssignmentByStatusInfo.getSubject().equals("4")){
                hwAssignmentSateViewHolder.tvSubject.setText("Maths");
            }else if (studentHWAssignmentByStatusInfo.getSubject().equals("5")){
                hwAssignmentSateViewHolder.tvSubject.setText("Hindi");
            }else if (studentHWAssignmentByStatusInfo.getSubject().equals("6")){
                hwAssignmentSateViewHolder.tvSubject.setText("Computer");
            }

            if(studentHWAssignmentByStatusInfo.getStatus()==2){

                hwAssignmentSateViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.pending));
                hwAssignmentSateViewHolder.tvStatus.setText("Pending");

            }else if(studentHWAssignmentByStatusInfo.getStatus()==7) {

                hwAssignmentSateViewHolder.tvStatus.setText("Need Rework");
                hwAssignmentSateViewHolder.tvStatus.setTextColor(Color.BLUE);

            } else if(studentHWAssignmentByStatusInfo.getStatus()==4) {

                hwAssignmentSateViewHolder.tvStatus.setText("Overdue");
                hwAssignmentSateViewHolder.tvStatus.setTextColor(Color.RED);

            }else if(studentHWAssignmentByStatusInfo.getStatus()==0) {

                hwAssignmentSateViewHolder.tvStatus.setText("All");
                hwAssignmentSateViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.all));

            }else if(studentHWAssignmentByStatusInfo.getStatus()==1) {

                hwAssignmentSateViewHolder.tvStatus.setText("Completed");
                hwAssignmentSateViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorPrimary));

            }

            hwAssignmentSateViewHolder.itemView.setOnClickListener(v -> {
                onUpdateStudentHwAssignmentClickListener.onUpdateStudentHwAssignmentClick2(studentHWAssignmentByStatusInfo);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listHwStatus.size();
    }


    public class HWAssignmentSateViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_teacher_name)
        TextView tvTeacherName;

        @BindView(R.id.tv_status)
        TextView tvStatus;

        @BindView(R.id.tv_subject)
        TextView tvSubject;

        @BindView(R.id.tv_homework_header)
        TextView tvHomeworkHeader;

        @BindView(R.id.tv_homework_detail)
        TextView tvHomeworkDetail;

        @BindView(R.id.tv_assigned_on)
        TextView tvAssignedOn;

        @BindView(R.id.tv_due_date)
        TextView tvDueDate;

        public HWAssignmentSateViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }


}