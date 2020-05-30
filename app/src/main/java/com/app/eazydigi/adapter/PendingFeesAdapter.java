package com.app.eazydigi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.model.old_model.PaymentHistoryResponseInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PendingFeesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    List<PaymentHistoryResponseInfo> listPaymentHistory = new ArrayList<>();

    public PendingFeesAdapter(Context c) {
        this.context = c;

    }

    public void addAll(List<PaymentHistoryResponseInfo> list) {

        try {

            listPaymentHistory.clear();

            listPaymentHistory.addAll(list);

            notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }

        notifyDataSetChanged();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_student_pending_fee_detail, parent, false);
        return new FeePaymentDetailHolder(itemView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        try {

            FeePaymentDetailHolder feePaymentDetailHolder = (FeePaymentDetailHolder) holder;

            PaymentHistoryResponseInfo paymentHistoryResponseInfo = listPaymentHistory.get(position);

            feePaymentDetailHolder.tvAmountToPay.setText(""+paymentHistoryResponseInfo.getAmountToPay());
            feePaymentDetailHolder.tvFeeMonth.setText(""+paymentHistoryResponseInfo.getFeeMonth());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return listPaymentHistory.size();
    }


    public class FeePaymentDetailHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_amount_to_pay)
        TextView tvAmountToPay;

        @BindView(R.id.tv_fee_month)
        TextView tvFeeMonth;

        public FeePaymentDetailHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }


}