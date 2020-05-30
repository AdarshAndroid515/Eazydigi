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

public class CircularSTAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    int userRoleId;
    List<String> listCirculars = new ArrayList<>();


    public CircularSTAdapter(Context c) {
        this.context = c;
        userRoleId = PreferenceUtils.getUserRoleIdFromDefaults(context);
    }

    public void addAll(List<String> data) {

        try {

            listCirculars.clear();

            listCirculars.addAll(data);

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_st_notice, parent, false);
        return new CircularSTAdapter.CircularViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {

            CircularSTAdapter.CircularViewHolder circularViewHolder = (CircularSTAdapter.CircularViewHolder) holder;

            circularViewHolder.tvBoard.setText("" + listCirculars.get(position));
            circularViewHolder.tvBoardNumber.setText("" + position+1);

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

        @BindView(R.id.tv_board_stAdapter)
        TextView tvBoard;

        @BindView(R.id.tv_Number_stAdapter)
        TextView tvBoardNumber;

        public CircularViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}