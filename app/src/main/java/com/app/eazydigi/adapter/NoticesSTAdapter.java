package com.app.eazydigi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.util.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticesSTAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    List<String> listNotices = new ArrayList<>();
    int userRoleId;
    private static final String TAG = "NoticesSTAdapter";

    public NoticesSTAdapter(Context c) {
        this.context = c;
        userRoleId = PreferenceUtils.getUserRoleIdFromDefaults(context);
    }

    public void addAll(List<String> data) {

        try {

            listNotices.clear();

            listNotices.addAll(data);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_st_notice, parent, false);

        return new NoticesSTAdapter.NoticeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {

            NoticesSTAdapter.NoticeViewHolder noticeViewHolder = (NoticesSTAdapter.NoticeViewHolder) holder;

            noticeViewHolder.tvBoard.setText("" + listNotices.get(position));
            noticeViewHolder.tvBoardNumber.setText("" + position+1);

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

        @BindView(R.id.tv_board_stAdapter)
        TextView tvBoard;

        @BindView(R.id.tv_Number_stAdapter)
        TextView tvBoardNumber;

        public NoticeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}