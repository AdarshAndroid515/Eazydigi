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
import com.app.eazydigi.listener.OnUpdateHwAssignmentByTeacherClickListener;
import com.app.eazydigi.model.new_model.response.HomeWorkDashboardTeacherResponse;
import com.app.eazydigi.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeacherHwAssignmentByStatusAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    List<HomeWorkDashboardTeacherResponse.DataBean.StudentListBean> listHwStatus = new ArrayList<>();

    OnUpdateHwAssignmentByTeacherClickListener onUpdateHwAssignmentClickListener;
    private static SparseBooleanArray sSelectedItems = new SparseBooleanArray();


    private static final String TAG = "HwAssignmentStateForTea";



    public TeacherHwAssignmentByStatusAdapter(Context c) {
        this.context = c;

    }
//    public List<TeacherHWAssignmentByStatusInfo> getList(){
//        return listHwStatus;
//    }

    public void addAll(List<HomeWorkDashboardTeacherResponse.DataBean.StudentListBean> list) {

        try {

            listHwStatus.clear();

            listHwStatus.addAll(list);

            notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();

    }

    public void setListener(OnUpdateHwAssignmentByTeacherClickListener onUpdateHwAssignmentClickListener) {
        this.onUpdateHwAssignmentClickListener = onUpdateHwAssignmentClickListener;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_teacher_hw_assignment_by_status, parent, false);
        return new HWAssignmentSateViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {

            HWAssignmentSateViewHolder hwAssignmentSateViewHolder = (HWAssignmentSateViewHolder) holder;

            HomeWorkDashboardTeacherResponse.DataBean.StudentListBean teacherHWAssignmentByStatusInfo = listHwStatus.get(position);

            hwAssignmentSateViewHolder.tvRollNo.setText("" + teacherHWAssignmentByStatusInfo.getRollNumber());
            hwAssignmentSateViewHolder.tvStudentName.setText("" +teacherHWAssignmentByStatusInfo.getFirstName()+" "+teacherHWAssignmentByStatusInfo.getLastName());
            hwAssignmentSateViewHolder.tvClass.setText("" + teacherHWAssignmentByStatusInfo.getClassNum());
            hwAssignmentSateViewHolder.tvSection.setText("" +teacherHWAssignmentByStatusInfo.getSection());

            hwAssignmentSateViewHolder.tvHomeworkHeader.setText("" + teacherHWAssignmentByStatusInfo.getHeader());
            hwAssignmentSateViewHolder.tvHomeworkDetail.setText("" + teacherHWAssignmentByStatusInfo.getDescription());
            //hwAssignmentSateViewHolder.tvAssignedOn.setText("" +Utils.convertToDateString(teacherHWAssignmentByStatusInfo.getAssignedOn(), "yyyy-MM-dd'T'HH:mm:ss", "dd MMM, yyyy"));
            if (teacherHWAssignmentByStatusInfo.getSubject().equals("1")){
                hwAssignmentSateViewHolder.tvSubject.setText("English");
            }else if (teacherHWAssignmentByStatusInfo.getSubject().equals("2")){
                hwAssignmentSateViewHolder.tvSubject.setText("Science");
            }else if (teacherHWAssignmentByStatusInfo.getSubject().equals("3")){
                hwAssignmentSateViewHolder.tvSubject.setText("Social Science");
            }else if (teacherHWAssignmentByStatusInfo.getSubject().equals("4")){
                hwAssignmentSateViewHolder.tvSubject.setText("Maths");
            }else if (teacherHWAssignmentByStatusInfo.getSubject().equals("5")){
                hwAssignmentSateViewHolder.tvSubject.setText("Hindi");
            }else if (teacherHWAssignmentByStatusInfo.getSubject().equals("6")){
                hwAssignmentSateViewHolder.tvSubject.setText("Computer");
            }

            try {
                hwAssignmentSateViewHolder.tvDueDate.setText("" +  Utils.convertToDateString(teacherHWAssignmentByStatusInfo.getDueDate(),"yyyy-mm-dd","dd MMM,yyyy"));

            }catch (Exception e){
                e.printStackTrace();
            }

            if(teacherHWAssignmentByStatusInfo.getStatus()==2){

                hwAssignmentSateViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.pending));
                hwAssignmentSateViewHolder.tvStatus.setText("Pending");

            }else if(teacherHWAssignmentByStatusInfo.getStatus()==7) {

                hwAssignmentSateViewHolder.tvStatus.setText("Need Rework");
                hwAssignmentSateViewHolder.tvStatus.setTextColor(Color.BLUE);

            } else if(teacherHWAssignmentByStatusInfo.getStatus()==4) {

                hwAssignmentSateViewHolder.tvStatus.setText("Overdue");
                hwAssignmentSateViewHolder.tvStatus.setTextColor(Color.RED);

            }else if(teacherHWAssignmentByStatusInfo.getStatus()==0) {

                hwAssignmentSateViewHolder.tvStatus.setText("All");
                hwAssignmentSateViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.all));

            }else if(teacherHWAssignmentByStatusInfo.getStatus()==1) {

                hwAssignmentSateViewHolder.tvStatus.setText("Completed");
                hwAssignmentSateViewHolder.tvStatus.setTextColor(context.getResources().getColor(R.color.colorPrimary));

            }

            hwAssignmentSateViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //onUpdateHwAssignmentClickListener.onUpdateHwAssignmentClick(teacherHWAssignmentByStatusInfo);

                    onUpdateHwAssignmentClickListener.onUpdateHwAssignmentClick2(teacherHWAssignmentByStatusInfo);

                }
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

        @BindView(R.id.tv_student_name)
        TextView tvStudentName;

        @BindView(R.id.tv_status)
        TextView tvStatus;

        @BindView(R.id.tv_rollno)
        TextView tvRollNo;

        @BindView(R.id.tv_subject)
        TextView tvSubject;

        @BindView(R.id.tv_class)
        TextView tvClass;

        @BindView(R.id.tv_section)
        TextView tvSection;

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