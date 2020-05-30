package com.app.eazydigi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.PaidFeesAdapter;
import com.app.eazydigi.model.old_model.PaymentHistoryResponseInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaidFeesFragment extends BaseFragment {

    @BindView(R.id.rv_paid_fees)
    RecyclerView rvPaidFees;

    @BindView(R.id.tv_no_paid_fees)
    TextView tvNoPaidFees;

    PaidFeesAdapter paidFeesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paid_fees, container, false);
        ButterKnife.bind(this, view);

        paidFeesAdapter = new PaidFeesAdapter(getActivity());

        initViews();

        return view;

    }

    public void addAll(List<PaymentHistoryResponseInfo> listPaymentHistory){
        if (listPaymentHistory.size()>0){
            rvPaidFees.setVisibility(View.VISIBLE);
            paidFeesAdapter.addAll(listPaymentHistory);
        }
    }

    private void initViews() {

        rvPaidFees.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rvPaidFees.setAdapter(paidFeesAdapter);
    }
}
