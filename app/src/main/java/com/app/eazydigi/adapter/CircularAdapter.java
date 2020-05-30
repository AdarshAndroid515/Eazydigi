package com.app.eazydigi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.listener.OnCircularStatusChangeListener;
import com.app.eazydigi.model.new_model.response.GetAllNoticesResponse;
import com.app.eazydigi.util.PreferenceUtils;
import com.app.eazydigi.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircularAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    int userRoleId;
    List<GetAllNoticesResponse.DataBean>  listCirculars = new ArrayList<>();

    OnCircularStatusChangeListener onCircularStatusChangeListener;

    public CircularAdapter(Context c, OnCircularStatusChangeListener listener) {
        this.context = c;
        this.onCircularStatusChangeListener = listener;
        userRoleId = PreferenceUtils.getUserRoleIdFromDefaults(context);
    }

    public void addAll(List<GetAllNoticesResponse.DataBean> data) {

        try {

            listCirculars.clear();

            listCirculars.addAll(data);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();

    }

    public void addSingleNotice(GetAllNoticesResponse.DataBean data) {

        try {

            listCirculars.add(data);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_circular, parent, false);
        return new CircularViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {

            CircularViewHolder circularViewHolder = (CircularViewHolder) holder;

            circularViewHolder.tvBoard.setText("" + listCirculars.get(position).getTitle());
            circularViewHolder.tvBoardDesc.setText("" + listCirculars.get(position).getDescription());

            try {
                circularViewHolder.tvBoardDate.setText("" + Utils.convertToDateString(listCirculars.get(position).getStartDate(),"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy"));
            }catch (Exception e){
                circularViewHolder.tvBoardDate.setText("" + listCirculars.get(position).getStartDate());
                e.printStackTrace();
            }

            if (userRoleId == 2) {
                circularViewHolder.switchActive.setVisibility(View.VISIBLE);
            } else {
                circularViewHolder.switchActive.setVisibility(View.GONE);
            }

            boolean status=false;
            if (listCirculars.get(position).getIsActive()==1)
                status=true;
            else
                status=false;

            circularViewHolder.switchActive.setChecked(status);

            circularViewHolder.switchActive.setOnClickListener(v -> {
                onCircularStatusChangeListener.onStatusChangeListener(position,listCirculars.get(position));
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listCirculars.size();
    }

    public class CircularViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv_module)
        CardView cvModule;

        @BindView(R.id.tv_board)
        TextView tvBoard;

        @BindView(R.id.tv_board_date)
        TextView tvBoardDate;

        @BindView(R.id.tv_board_desc)
        TextView tvBoardDesc;

        @BindView(R.id.switch_active)
        SwitchCompat switchActive;

        public CircularViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}