package com.app.eazydigi.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.listener.OnFeeCollectionClickListener;
import com.app.eazydigi.model.old_model.FilteredStudentInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeeCollectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;

    public List<FilteredStudentInfo> listFeeCollection = new ArrayList<>();
    OnFeeCollectionClickListener onFeeCollectionClickListener;

    private static final String TAG = "AdminAttendanceSheetAda";

    public FeeCollectionAdapter(Activity c) {
        this.context = c;
    }

    public void addAll(List<FilteredStudentInfo> list) {

        listFeeCollection.clear();
        listFeeCollection.addAll(list);
        notifyDataSetChanged();
    }

    public void setListener(OnFeeCollectionClickListener onFeeCollectionClickListener) {

        this.onFeeCollectionClickListener = onFeeCollectionClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_fee_collection_detail, parent, false);

        return new FeeCollectionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {

            FeeCollectionViewHolder feeCollectionViewHolder = (FeeCollectionViewHolder) holder;

            FilteredStudentInfo filteredStudentInfo = listFeeCollection.get(position);
            feeCollectionViewHolder.tvRollNo.setText("" + filteredStudentInfo.getRollNumber());
            feeCollectionViewHolder.tvStudentName.setText("" + filteredStudentInfo.getUserFullName());
            feeCollectionViewHolder.tvSection.setText("" + filteredStudentInfo.getSectionId());

            feeCollectionViewHolder.tvFatherName.setText("" + filteredStudentInfo.getFatherFullName());
            feeCollectionViewHolder.tvMobile.setText(filteredStudentInfo.getMobile() != null ? filteredStudentInfo.getMobile() : "");

            feeCollectionViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onFeeCollectionClickListener != null) {

                        onFeeCollectionClickListener.onFeeCollectionListener(filteredStudentInfo);
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listFeeCollection.size();
    }

    public class FeeCollectionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_rollno)
        TextView tvRollNo;

        @BindView(R.id.tv_student_name)
        TextView tvStudentName;

        @BindView(R.id.tv_section)
        TextView tvSection;

        @BindView(R.id.tv_father_name)
        TextView tvFatherName;

        @BindView(R.id.tv_mobile)
        TextView tvMobile;

        public FeeCollectionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}