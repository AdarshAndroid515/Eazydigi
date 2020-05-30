package com.app.eazydigi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.model.new_model.response.StudentListResponse;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ListViewHolder> {

    private Context context ;
    private itemClickListener itemClickListener;
    private List<StudentListResponse.DataBean> dataBeanList;
    private List<Integer> studentSelectedList=new ArrayList<>();


    public interface itemClickListener {
        void onLocationClickTo(List<Integer> studentSelectedList);
    }

    public StudentListAdapter(Context context,List<StudentListResponse.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList=dataBeanList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.student_list_adapter,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    public List<Integer> getStudentSelectedList(){
        return studentSelectedList;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvRollNumber,tvName;
        private CheckBox checkBox;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRollNumber = itemView.findViewById(R.id.studentRollNo_studentListDialog);
            tvName=itemView.findViewById(R.id.studentName_studentListDialog);
            checkBox=itemView.findViewById(R.id.checkBox_studentListDialog);
            itemView.setOnClickListener(this);


        }

        public void bindData(final int position) {
            tvName.setText(dataBeanList.get(position).getFirstName()+" "+dataBeanList.get(position).getLastName());
            tvRollNumber.setText(""+dataBeanList.get(position).getRollNumber());

            try{
                if (dataBeanList.get(position)!=null)
                    if (dataBeanList.get(position).isSelectedInHW()){
                        checkBox.setChecked(true);
                        studentSelectedList.add(dataBeanList.get(position).getStudId());
                    }else {
                        checkBox.setChecked(false);
                    }

            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        studentSelectedList.add(dataBeanList.get(position).getStudId());
                        dataBeanList.get(position).setSelectedInHW(true);
                    }else {
                        studentSelectedList.remove(position);
                        dataBeanList.get(position).setSelectedInHW(false);
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {

            if (itemClickListener != null) {
                itemClickListener.onLocationClickTo(studentSelectedList);
            }

        }
    }
}

