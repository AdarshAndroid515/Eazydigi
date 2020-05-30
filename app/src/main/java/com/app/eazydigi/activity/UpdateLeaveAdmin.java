package com.app.eazydigi.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.app.eazydigi.R;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.model.new_model.response.LeaveRequestListStudentResponse;
import com.app.eazydigi.model.new_model.response.UpdateLeaveAdminResponse;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.app.eazydigi.activity.LeaveListAdmin.dataBeanPublic;

public class UpdateLeaveAdmin extends BaseActivity implements View.OnClickListener{

    private LeaveRequestListStudentResponse.DataBean dataBean;
    private Toolbar toolbar;
    private TextView tvStartDate,tvEndDate,tvReason,tvStatus,tvDescription,tvName,tvStudId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_request_admin);

        dataBean=dataBeanPublic;
        toolbar=findViewById(R.id.toolbar);
        tvStartDate = findViewById(R.id.startDate_updateLeaveAdmin);
        tvEndDate = findViewById(R.id.endDate_updateLeaveAdmin);
        tvReason = findViewById(R.id.reason_updateLeaveAdmin);
        tvDescription = findViewById(R.id.description_updateLeaveAdmin);
        tvStatus = findViewById(R.id.status_updateLeaveAdmin);
        tvName = findViewById(R.id.studentId_updateLeaveAdmin);
        tvStudId = findViewById(R.id.studentName_updateLeaveAdmin);

        tvStartDate.setText(dataBean.getStartDate());
        tvEndDate.setText(dataBean.getEndDate());
        tvReason.setText(dataBean.getLeaveSub());
        tvDescription.setText(dataBean.getDescription());
        tvName.setText(dataBean.getFirstName()+" "+dataBean.getLastName());
        tvStudId.setText(""+dataBean.getStudId());

        if (dataBean.getStatus()==0){
            tvStatus.setText("Pending");
            tvStatus.setTextColor(context.getResources().getColor(R.color.pending));
        }else if (dataBean.getStatus()==1){
            tvStatus.setText("Approved");
            tvStatus.setTextColor(context.getResources().getColor(R.color.present));
        }else if (dataBean.getStatus()==2){
            tvStatus.setText("Rejected");
            tvStatus.setTextColor(context.getResources().getColor(R.color.absent));
        }

        findViewById(R.id.updateBtn_updateLeaveAdmin).setOnClickListener(this);
        findViewById(R.id.changeBtn_updateLeaveAdmin).setOnClickListener(this);

        initToolbar();
    }

    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Update Leave Request");

        toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.updateBtn_updateLeaveAdmin:
                if (Utils.isConnectedToInternet(UpdateLeaveAdmin.this)) {
                    getUpdateLeave();
                } else {
                    Toast.makeText(context, getString(R.string.alert_check_connection), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.changeBtn_updateLeaveAdmin:
                showDialog();
                break;
        }
    }

    public void showDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_update_leave_admin);

        Button acceptButton = dialog.findViewById(R.id.acceptBtn_updateLeaveAdminDialog);
        acceptButton.setOnClickListener(v -> {
            tvStatus.setText("Approved");
            tvStatus.setTextColor(context.getResources().getColor(R.color.present));
            dataBean.setStatus(1);
            dialog.dismiss();
        });

        Button rejectButton = dialog.findViewById(R.id.rejectBtn_updateLeaveAdminDialog);
        rejectButton.setOnClickListener(v -> {
            tvStatus.setText("Rejected");
            tvStatus.setTextColor(context.getResources().getColor(R.color.absent));
            dataBean.setStatus(2);
            dialog.dismiss();
        });

        dialog.show();
    }

    public void getUpdateLeave(){
        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.updateLeaveRequest(dataBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UpdateLeaveAdminResponse>() {
                    @Override
                    public void onSuccess(UpdateLeaveAdminResponse updateLeaveAdminResponse) {
                        dismissPD(pd);
                        try {
                            Utils.showAlertDialogFinish(context,"Leave Request Updated",updateLeaveAdminResponse.getMessage());
                        } catch (Exception e) {
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
