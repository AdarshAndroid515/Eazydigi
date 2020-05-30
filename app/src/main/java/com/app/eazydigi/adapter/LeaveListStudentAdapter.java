package com.app.eazydigi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.model.new_model.response.LeaveRequestListStudentResponse;
import com.app.eazydigi.util.Utils;

import java.util.List;

public class LeaveListStudentAdapter extends RecyclerView.Adapter<LeaveListStudentAdapter.ListViewHolder> {

    private Context context ;
    private List<LeaveRequestListStudentResponse.DataBean> data;

    public interface itemClickListener {
        void onLocationClickTo(int position);
    }

    public LeaveListStudentAdapter(Context context, List<LeaveRequestListStudentResponse.DataBean> data1) {
        this.context = context;
        data=data1;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.leave_request_student_adapter,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvStartDate,tvEndDate,tvReason,tsStatus,tvDescription,tvName,tvStudId;
        private Button EditBtn;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStartDate = itemView.findViewById(R.id.startDate_leaveListStudentAdapter);
            tvEndDate = itemView.findViewById(R.id.endDate_leaveListStudentAdapter);
            tvReason = itemView.findViewById(R.id.reason_leaveListStudentAdapter);
            tvDescription = itemView.findViewById(R.id.description_leaveListStudentAdapter);
            tsStatus = itemView.findViewById(R.id.status_leaveListStudentAdapter);
            tvName = itemView.findViewById(R.id.studentId_leaveListStudentAdapter);
            tvStudId = itemView.findViewById(R.id.studentName_leaveListStudentAdapter);
            EditBtn = itemView.findViewById(R.id.editBtn_leaveListStudentAdapter);

            itemView.setOnClickListener(this);

        }

        public void bindData(final int position) {

            EditBtn.setVisibility(View.GONE);

            tvReason.setText(data.get(position).getLeaveSub());
            tvDescription.setText(data.get(position).getDescription());
            tvName.setText(data.get(position).getFirstName()+" "+data.get(position).getLastName());
            tvStudId.setText(""+data.get(position).getStudId());

            try {
                tvStartDate.setText("" + Utils.convertToDateString(data.get(position).getStartDate(),"yyyy-MM-dd","dd-MM-yyyy"));
            }catch (Exception e){
                tvStartDate.setText(data.get(position).getStartDate());
                e.printStackTrace();
            }

            try {
                tvEndDate.setText("" + Utils.convertToDateString(data.get(position).getStartDate(),"yyyy-MM-dd","dd-MM-yyyy"));
            }catch (Exception e){
                tvEndDate.setText(data.get(position).getStartDate());
                e.printStackTrace();
            }


            if (data.get(position).getStatus()==0){
                tsStatus.setText("Pending");
                tsStatus.setTextColor(context.getResources().getColor(R.color.pending));
            }else if (data.get(position).getStatus()==1){
                tsStatus.setText("Approved");
                tsStatus.setTextColor(context.getResources().getColor(R.color.present));
            }else if (data.get(position).getStatus()==2){
                tsStatus.setText("Rejected");
                tsStatus.setTextColor(context.getResources().getColor(R.color.absent));
            }

        }

        @Override
        public void onClick(View v) {

        }
    }
}

