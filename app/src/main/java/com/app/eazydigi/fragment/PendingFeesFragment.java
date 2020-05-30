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
import com.app.eazydigi.adapter.PendingFeesAdapter;
import com.app.eazydigi.model.old_model.PaymentHistoryResponseInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PendingFeesFragment extends BaseFragment {

    @BindView(R.id.rv_pending_fees)
    RecyclerView rvPendingFees;

    @BindView(R.id.tv_no_pending_fees)
    TextView tvNoPendingFees;

    PendingFeesAdapter pendingFeesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pending_fees, container, false);
        ButterKnife.bind(this, view);

        pendingFeesAdapter = new PendingFeesAdapter(getActivity());

        initViews();

        return view;

    }

    public void addAll(List<PaymentHistoryResponseInfo> listPaymentHistory){
        if (listPaymentHistory.size()>0){
            rvPendingFees.setVisibility(View.VISIBLE);
            pendingFeesAdapter.addAll(listPaymentHistory);
        }
    }

    private void initViews() {

        rvPendingFees.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rvPendingFees.setAdapter(pendingFeesAdapter);
    }
}
