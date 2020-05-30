package com.app.eazydigi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.FeeCollectionAdapter;
import com.app.eazydigi.listener.OnFeeCollectionClickListener;
import com.app.eazydigi.model.old_model.FilteredStudentInfo;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeeCollectionDetailActivity extends BaseActivity implements OnFeeCollectionClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_fee_collection)
    RecyclerView rvFeeCollection;

    FeeCollectionAdapter feeCollectionAdapter;

    List<FilteredStudentInfo> filteredStudentInfo;

    String rollNo;
    String admissionId;
    String studentName;
    String className;

    private static final String TAG = "FeeCollectionDetailActi";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_collection_detail);

        ButterKnife.bind(this);

        feeCollectionAdapter = new FeeCollectionAdapter(this);
        feeCollectionAdapter.setListener(this);

        initToolbar();

        if (getIntent() != null && getIntent().hasExtra(CLASS_NAME) && getIntent().hasExtra(ROLL_NO) && getIntent().hasExtra(ADMISSION_ID) && getIntent().hasExtra(STUDENT_NAME)) {

            rollNo = getIntent().getStringExtra(ROLL_NO);

            admissionId = getIntent().getStringExtra(ADMISSION_ID);

            studentName = getIntent().getStringExtra(STUDENT_NAME);

            className = getIntent().getStringExtra(CLASS_NAME);

        }

        initViews();

        getFilteredStudents();


    }

    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(context.getString(R.string.fee_collection_detail));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();


            }
        });

    }

    public void initViews() {
        rvFeeCollection.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rvFeeCollection.setAdapter(feeCollectionAdapter);
    }

    public void getFilteredStudents() {

        ProgressHUD pd = showPD(null);

        MyApplication.getAppInstance().getAPIInterface().getFilteredStudents(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                0, 2000, className,
                0, rollNo, admissionId, studentName, MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), "true").enqueue(new Callback<List<FilteredStudentInfo>>() {
            @Override
            public void onResponse(Call<List<FilteredStudentInfo>> call, Response<List<FilteredStudentInfo>> response) {
                Log.e(TAG, "---->onResponse: " + response.body());
                dismissPD(pd);
                filteredStudentInfo = new ArrayList<>();

                if (response.isSuccessful()) {

                    filteredStudentInfo = response.body();

                    if (filteredStudentInfo.size() > 0 && filteredStudentInfo != null) {

                        Collections.sort(filteredStudentInfo, new Comparator<FilteredStudentInfo>() {
                            @Override
                            public int compare(FilteredStudentInfo filteredStudentInfo, FilteredStudentInfo ft1) {
                                return filteredStudentInfo.getRollNumber().compareToIgnoreCase(ft1.getRollNumber());
                            }
                        });

                        feeCollectionAdapter.addAll(filteredStudentInfo);
                        rvFeeCollection.setVisibility(View.VISIBLE);
                    } else {
                        rvFeeCollection.setVisibility(View.GONE);
                    }

                } else {
                    dismissPD(pd);

                    String responseJson = Utils.loadJSONFromAsset(context, "GetFilteredStudents.json");
                    filteredStudentInfo = new Gson().fromJson(responseJson, new TypeToken<List<FilteredStudentInfo>>() {
                    }.getType());
                    if (filteredStudentInfo.size() > 0 && filteredStudentInfo != null) {
                        feeCollectionAdapter.addAll(filteredStudentInfo);
                        rvFeeCollection.setVisibility(View.VISIBLE);
                    } else {
                        rvFeeCollection.setVisibility(View.GONE);
                    }

                }

            }

            @Override
            public void onFailure(Call<List<FilteredStudentInfo>> call, Throwable t) {
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                t.printStackTrace();
                dismissPD(pd);
            }
        });

    }

    @Override
    public void onFeeCollectionListener(FilteredStudentInfo filteredStudentInfo) {

        if (filteredStudentInfo.getSectionId() != 0) {
            Intent intent = new Intent(context, CollectFeeDetailForStudentActivity.class);
            intent.putExtra(FILTER_STUDENT_INFO, filteredStudentInfo);
            startActivity(intent);
        } else {
            Toast.makeText(context, "Collect Fee Detail not Available...", Toast.LENGTH_SHORT).show();
            finish();
        }


    }
}


