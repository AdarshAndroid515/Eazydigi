package com.app.eazydigi.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.FragmentViewPagerAdapter;
import com.app.eazydigi.fragment.PaidFeesFragment;
import com.app.eazydigi.fragment.PendingFeesFragment;
import com.app.eazydigi.model.old_model.FeePaymentDetailInfo;
import com.app.eazydigi.model.old_model.PaymentHistoryResponseInfo;
import com.app.eazydigi.model.old_model.StudentPendingFeeDetails;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeePaymentDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    PaidFeesFragment paidFeesFragment;
    PendingFeesFragment pendingFeesFragment;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_payment_detail);
        ButterKnife.bind(this);

        initToolbar();

        initViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.isConnectedToInternet(this)){
            getFeePaymentDetail();
        }
    }

    public void initViews() {

        paidFeesFragment = new PaidFeesFragment();
        pendingFeesFragment = new PendingFeesFragment();

        setupViewPager(viewpager);
        tabLayout.setupWithViewPager(viewpager);

    }

    private void setupViewPager(ViewPager viewPager) {

        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(paidFeesFragment, getResources().getString(R.string.paid));
        adapter.addFragment(pendingFeesFragment, getResources().getString(R.string.pending));
        viewPager.setAdapter(adapter);
    }

    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getString(R.string.fee_payment_detail));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public void getFeePaymentDetail() {

        ProgressHUD pd = showPD(null);

        MyApplication.getAppInstance().getAPIInterface().getFeePaymentHistory(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization(), getBody()
        ).enqueue(new Callback<FeePaymentDetailInfo>() {
            @Override
            public void onResponse(Call<FeePaymentDetailInfo> call, Response<FeePaymentDetailInfo> response) {

                dismissPD(pd);
                if (response.isSuccessful()) {

                    FeePaymentDetailInfo feePaymentDetailInfo = response.body();

                    if (feePaymentDetailInfo!=null){
                        if (feePaymentDetailInfo.getPaymentHistoryResponse()!=null){
                            paidFeesFragment.addAll(feePaymentDetailInfo.getPaymentHistoryResponse());
                        }
                        if (feePaymentDetailInfo.getStudentPendingFee()!=null){
                            pendingFeesFragment.addAll(getPendingFees(feePaymentDetailInfo.getStudentPendingFee()));
                        }
                    }

                } else {


                }

            }

            @Override
            public void onFailure(Call<FeePaymentDetailInfo> call, Throwable t) {
                t.printStackTrace();
                dismissPD(pd);
            }
        });

    }

    private  List<PaymentHistoryResponseInfo> getPendingFees(StudentPendingFeeDetails studentPendingFeeDetails){

        List<PaymentHistoryResponseInfo> list = new ArrayList<>();
        if (studentPendingFeeDetails.getJan()!=null && studentPendingFeeDetails.getJan()>0){
            PaymentHistoryResponseInfo paymentHistoryResponseInfo = new PaymentHistoryResponseInfo();
            paymentHistoryResponseInfo.setFeeMonth("January");
            paymentHistoryResponseInfo.setAmountToPay(studentPendingFeeDetails.getJan());
            list.add(paymentHistoryResponseInfo);
        }
        if (studentPendingFeeDetails.getFeb()!=null && studentPendingFeeDetails.getFeb()>0){
            PaymentHistoryResponseInfo paymentHistoryResponseInfo = new PaymentHistoryResponseInfo();
            paymentHistoryResponseInfo.setFeeMonth("February");
            paymentHistoryResponseInfo.setAmountToPay(studentPendingFeeDetails.getFeb());
            list.add(paymentHistoryResponseInfo);
        }
        if (studentPendingFeeDetails.getMarch()!=null && studentPendingFeeDetails.getMarch()>0){
            PaymentHistoryResponseInfo paymentHistoryResponseInfo = new PaymentHistoryResponseInfo();
            paymentHistoryResponseInfo.setFeeMonth("March");
            paymentHistoryResponseInfo.setAmountToPay(studentPendingFeeDetails.getMarch());
            list.add(paymentHistoryResponseInfo);
        }
        if (studentPendingFeeDetails.getApril()!=null && studentPendingFeeDetails.getApril()>0){
            PaymentHistoryResponseInfo paymentHistoryResponseInfo = new PaymentHistoryResponseInfo();
            paymentHistoryResponseInfo.setFeeMonth("April");
            paymentHistoryResponseInfo.setAmountToPay(studentPendingFeeDetails.getApril());
            list.add(paymentHistoryResponseInfo);
        }
        if (studentPendingFeeDetails.getMay()!=null && studentPendingFeeDetails.getMay()>0){
            PaymentHistoryResponseInfo paymentHistoryResponseInfo = new PaymentHistoryResponseInfo();
            paymentHistoryResponseInfo.setFeeMonth("May");
            paymentHistoryResponseInfo.setAmountToPay(studentPendingFeeDetails.getMay());
            list.add(paymentHistoryResponseInfo);
        }
        if (studentPendingFeeDetails.getJune()!=null && studentPendingFeeDetails.getJune()>0){
            PaymentHistoryResponseInfo paymentHistoryResponseInfo = new PaymentHistoryResponseInfo();
            paymentHistoryResponseInfo.setFeeMonth("June");
            paymentHistoryResponseInfo.setAmountToPay(studentPendingFeeDetails.getJune());
            list.add(paymentHistoryResponseInfo);
        }
        if (studentPendingFeeDetails.getJuly()!=null && studentPendingFeeDetails.getJuly()>0){
            PaymentHistoryResponseInfo paymentHistoryResponseInfo = new PaymentHistoryResponseInfo();
            paymentHistoryResponseInfo.setFeeMonth("July");
            paymentHistoryResponseInfo.setAmountToPay(studentPendingFeeDetails.getJuly());
            list.add(paymentHistoryResponseInfo);
        }
        if (studentPendingFeeDetails.getAugust()!=null && studentPendingFeeDetails.getAugust()>0){
            PaymentHistoryResponseInfo paymentHistoryResponseInfo = new PaymentHistoryResponseInfo();
            paymentHistoryResponseInfo.setFeeMonth("August");
            paymentHistoryResponseInfo.setAmountToPay(studentPendingFeeDetails.getAugust());
            list.add(paymentHistoryResponseInfo);
        }
        if (studentPendingFeeDetails.getSeptember()!=null && studentPendingFeeDetails.getSeptember()>0){
            PaymentHistoryResponseInfo paymentHistoryResponseInfo = new PaymentHistoryResponseInfo();
            paymentHistoryResponseInfo.setFeeMonth("September");
            paymentHistoryResponseInfo.setAmountToPay(studentPendingFeeDetails.getSeptember());
            list.add(paymentHistoryResponseInfo);
        }
        if (studentPendingFeeDetails.getOctober()!=null && studentPendingFeeDetails.getOctober()>0){
            PaymentHistoryResponseInfo paymentHistoryResponseInfo = new PaymentHistoryResponseInfo();
            paymentHistoryResponseInfo.setFeeMonth("October");
            paymentHistoryResponseInfo.setAmountToPay(studentPendingFeeDetails.getOctober());
            list.add(paymentHistoryResponseInfo);
        }
        if (studentPendingFeeDetails.getNovember()!=null && studentPendingFeeDetails.getNovember()>0){
            PaymentHistoryResponseInfo paymentHistoryResponseInfo = new PaymentHistoryResponseInfo();
            paymentHistoryResponseInfo.setFeeMonth("November");
            paymentHistoryResponseInfo.setAmountToPay(studentPendingFeeDetails.getNovember());
            list.add(paymentHistoryResponseInfo);
        }
        if (studentPendingFeeDetails.getDecember()!=null && studentPendingFeeDetails.getDecember()>0){
            PaymentHistoryResponseInfo paymentHistoryResponseInfo = new PaymentHistoryResponseInfo();
            paymentHistoryResponseInfo.setFeeMonth("December");
            paymentHistoryResponseInfo.setAmountToPay(studentPendingFeeDetails.getDecember());
            list.add(paymentHistoryResponseInfo);
        }


        return list;

    }

    public JsonObject getBody() {

        /*SORT OBJECT*/
        JsonObject sortObject = new JsonObject();
        sortObject.addProperty("sortBy", "BillingDate");
        sortObject.addProperty("sortDirection", "desc");

        /*PAGGING OBJECT*/
        JsonObject pagingObject = new JsonObject();
        pagingObject.addProperty("pageIndex", 0);
        pagingObject.addProperty("pageSize", 200);
        pagingObject.add("Sort", sortObject);

        /*FILTER OBJECT*/
        JsonObject filterObject = new JsonObject();
        filterObject.addProperty("searchField", "4");
        filterObject.addProperty("searchString", "924");
        filterObject.addProperty("searchClass", "");
        filterObject.addProperty("searchSection", "");

        /*MAIN OBJECT*/
        JsonObject mainJsonObject = new JsonObject();
        mainJsonObject.add("filter", filterObject);
        mainJsonObject.add("paging", pagingObject);
        mainJsonObject.addProperty("schoolId", MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId());

        return mainJsonObject;

    }

}
