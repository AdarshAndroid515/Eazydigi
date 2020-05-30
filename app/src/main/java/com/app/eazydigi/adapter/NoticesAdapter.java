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
import com.app.eazydigi.listener.OnNoticeStatusChangeListener;
import com.app.eazydigi.model.new_model.response.GetAllNoticesResponse;
import com.app.eazydigi.util.PreferenceUtils;
import com.app.eazydigi.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    List<GetAllNoticesResponse.DataBean> listNotices = new ArrayList<>();
    int userRoleId;
    private static final String TAG = "NoticesAdapter";

    OnNoticeStatusChangeListener onNoticeStatusChangeListener;

    public NoticesAdapter(Context c, OnNoticeStatusChangeListener listener) {
        this.context = c;
        this.onNoticeStatusChangeListener = listener;
        userRoleId = PreferenceUtils.getUserRoleIdFromDefaults(context);
    }

    public void addAll(List<GetAllNoticesResponse.DataBean> data) {

        try {

            listNotices.clear();

            listNotices.addAll(data);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();

    }

    public void addSingleNotice(GetAllNoticesResponse.DataBean data) {

        try {

            listNotices.add(data);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_notice, parent, false);

        return new NoticeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {

            NoticeViewHolder noticeViewHolder = (NoticeViewHolder) holder;

            noticeViewHolder.tvBoard.setText("" + listNotices.get(position).getTitle());
            noticeViewHolder.tvBoardDesc.setText("" + listNotices.get(position).getDescription());
            try {
                noticeViewHolder.tvBoardDate.setText("" + Utils.convertToDateString(listNotices.get(position).getStartDate(),"yyyy-MM-dd HH:mm:ss","dd-MM-yyyy"));
            }catch (Exception e){
                noticeViewHolder.tvBoardDate.setText("" + listNotices.get(position).getStartDate());
                e.printStackTrace();
            }

            if (userRoleId == 2) {
                noticeViewHolder.switchActive.setVisibility(View.VISIBLE);
            } else {
                noticeViewHolder.switchActive.setVisibility(View.GONE);
            }

            boolean status=false;
            if (listNotices.get(position).getIsActive()==1)
                status=true;
            else
                status=false;

            noticeViewHolder.switchActive.setChecked(status);

            noticeViewHolder.switchActive.setOnClickListener(v -> {
                onNoticeStatusChangeListener.onStatusChangeListener(position,listNotices.get(position));
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return listNotices.size();
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder {

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

        public NoticeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}