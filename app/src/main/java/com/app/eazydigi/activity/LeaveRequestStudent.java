package com.app.eazydigi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.LeaveListStudentAdapter;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.model.new_model.response.LeaveRequestListStudentResponse;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LeaveRequestStudent extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerViewList;
    private Button CreateLeaveBtn;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave_request_student);

        toolbar=findViewById(R.id.toolbar);
        recyclerViewList=findViewById(R.id.leaveList_LeaveStudent);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        findViewById(R.id.createBtn_LeaveStudent).setOnClickListener(this);

        initToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Utils.isConnectedToInternet(LeaveRequestStudent.this)) {
            getLeaveList("","2");
        } else {
            Toast.makeText(context, getString(R.string.alert_check_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Leave Requests");

        toolbar.setNavigationOnClickListener(view -> onBackPressed());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.createBtn_LeaveStudent:
                startActivity(new Intent(LeaveRequestStudent.this,CreateNewLeaveRequest.class));
                break;
        }
    }

    private void getLeaveList(String studentId,String flag) {

        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getLeaveListStudent(studentId, flag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LeaveRequestListStudentResponse>() {
                    @Override
                    public void onSuccess(LeaveRequestListStudentResponse leaveRequestListStudentResponse) {
                        dismissPD(pd);
                        try {
                            if (leaveRequestListStudentResponse!=null && leaveRequestListStudentResponse.getData()!=null
                                    && leaveRequestListStudentResponse.getData().size()>0){
                                LeaveListStudentAdapter leaveListStudentAdapter=new LeaveListStudentAdapter(LeaveRequestStudent.this,
                                        leaveRequestListStudentResponse.getData());
                                recyclerViewList.setAdapter(leaveListStudentAdapter);
                            }else {
                                Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissPD(pd);
                        Log.v("error",e.getMessage());
                    }
                }));
    }
}
