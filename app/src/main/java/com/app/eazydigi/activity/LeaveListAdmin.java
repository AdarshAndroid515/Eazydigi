package com.app.eazydigi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.LeaveListAdminAdapter;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.model.new_model.response.LeaveRequestListStudentResponse;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LeaveListAdmin extends BaseActivity implements View.OnClickListener, LeaveListAdminAdapter.itemClickListener{

    private RecyclerView recyclerViewList;
    private EditText edStudentId;
    private Toolbar toolbar;
    private LeaveListAdminAdapter leaveListAdminAdapter;
    private LeaveRequestListStudentResponse leaveRequestList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leave_request_admin);

        toolbar=findViewById(R.id.toolbar);
        edStudentId=findViewById(R.id.studentId_LeaveAdmin);
        recyclerViewList=findViewById(R.id.leaveList_LeaveAdmin);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        findViewById(R.id.searchBtn_LeaveAdmin).setOnClickListener(this);

        initToolbar();

        edStudentId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    if (s.toString().length()==0)
                        leaveListAdminAdapter=new LeaveListAdminAdapter(LeaveListAdmin.this,
                                leaveRequestList.getData(),LeaveListAdmin.this);
                    recyclerViewList.setAdapter(leaveListAdminAdapter);
                    leaveListAdminAdapter.notifyDataSetChanged();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Utils.isConnectedToInternet(LeaveListAdmin.this)) {
            getLeaveList("","1");
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

            case R.id.searchBtn_LeaveAdmin:
                if (TextUtils.isEmpty(edStudentId.getText().toString().trim())){
                    Toast.makeText(context, "Please Enter Student Id", Toast.LENGTH_SHORT).show();
                }else {
                    int StudentId= Integer.parseInt(edStudentId.getText().toString().trim());
                    List<LeaveRequestListStudentResponse.DataBean> newLeaveList = new ArrayList<>();
                    for (int i=0;i<leaveRequestList.getData().size();i++){
                        if (leaveRequestList.getData().get(i).getStudId()==StudentId){
                            newLeaveList.add(leaveRequestList.getData().get(i));
                        }
                    }
                    leaveListAdminAdapter=new LeaveListAdminAdapter(LeaveListAdmin.this,
                            newLeaveList,LeaveListAdmin.this);
                    recyclerViewList.setAdapter(leaveListAdminAdapter);
                    leaveListAdminAdapter.notifyDataSetChanged();
                }
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
                            leaveRequestList=leaveRequestListStudentResponse;
                            if (leaveRequestListStudentResponse!=null && leaveRequestListStudentResponse.getData()!=null
                                    && leaveRequestListStudentResponse.getData().size()>0){
                                leaveListAdminAdapter=new LeaveListAdminAdapter(LeaveListAdmin.this,
                                        leaveRequestList.getData(),LeaveListAdmin.this);
                                recyclerViewList.setAdapter(leaveListAdminAdapter);
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

    public static LeaveRequestListStudentResponse.DataBean dataBeanPublic;
    @Override
    public void onLocationClickTo(int position, LeaveRequestListStudentResponse.DataBean dataBean) {
        dataBeanPublic=dataBean;
        startActivity(new Intent(LeaveListAdmin.this,UpdateLeaveAdmin.class));
    }
}