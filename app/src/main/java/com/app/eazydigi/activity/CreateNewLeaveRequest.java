package com.app.eazydigi.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.app.eazydigi.R;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.model.new_model.request.CreateNewLeaveRequestModel;
import com.app.eazydigi.model.new_model.response.CreatenewLeaveResponse;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;

import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CreateNewLeaveRequest extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private EditText edDescription,edTitle;
    private TextView tvStartDate,tvEndDate;

    private DatePickerDialog datePickerDialog;
    private Calendar calendar;
    private int checkTextView=0;
    String Title="", Description="",StartDate="",EndDate="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_leave_request);

        calendar = Calendar.getInstance();

        toolbar=findViewById(R.id.toolbar);
        edDescription=findViewById(R.id.description_createNewLeave);
        edTitle=findViewById(R.id.title_createNewLeave);
        tvStartDate=findViewById(R.id.startDate_createNewLeave);
        tvEndDate=findViewById(R.id.endDate_createNewLeave);

        tvStartDate.setOnClickListener(this);
        tvEndDate.setOnClickListener(this);
        findViewById(R.id.createBtn_createNewRequest).setOnClickListener(this);

        initToolbar();
        initDatePicker();
    }

    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Create Leave Request");

        toolbar.setNavigationOnClickListener(view -> onBackPressed());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.createBtn_createNewRequest:
                Title=edTitle.getText().toString().trim();
                Description=edDescription.getText().toString().trim();
                StartDate=tvStartDate.getText().toString().trim();
                EndDate=tvEndDate.getText().toString().trim();

                if (TextUtils.isEmpty(Title)){
                    Toast.makeText(context, "Please Enter Title For Leave", Toast.LENGTH_SHORT).show();
                }else {
                    if (TextUtils.isEmpty(Description)){
                        Toast.makeText(context, "Please Enter Description For Leave", Toast.LENGTH_SHORT).show();
                    }else {
                        if (TextUtils.isEmpty(StartDate)){
                            Toast.makeText(context, "Please Select Start Date For Leave", Toast.LENGTH_SHORT).show();
                        }else {
                            if (TextUtils.isEmpty(EndDate)){
                                Toast.makeText(context, "Please Select End Date For Leave", Toast.LENGTH_SHORT).show();
                            }else {
                                if (Utils.isConnectedToInternet(CreateNewLeaveRequest.this)) {
                                    CreateNewLeaveRequestModel createNewLeaveRequestModel=new CreateNewLeaveRequestModel();
                                    createNewLeaveRequestModel.setDescription(Description);
                                    createNewLeaveRequestModel.setLeaveSub(Title);
                                    createNewLeaveRequestModel.setStartDate(StartDate);
                                    createNewLeaveRequestModel.setEndDate(EndDate);
                                    getCreateLeave(createNewLeaveRequestModel);
                                } else {
                                    Toast.makeText(context, getString(R.string.alert_check_connection), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                }

                break;

            case R.id.startDate_createNewLeave:
                checkTextView=1;
                datePickerDialog.show();
                break;

            case R.id.endDate_createNewLeave:
                checkTextView=2;
                datePickerDialog.show();
                break;
        }
    }

    public void initDatePicker() {

        datePickerDialog = new DatePickerDialog(context, (view, year, monthOfYear, dayOfMonth) -> {

            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            if (checkTextView==1)
                setDate(tvStartDate);
            else
                setDate(tvEndDate);


        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMinDate(System.currentTimeMillis() - 1000);
    }

    private void setDate(TextView textView) {
        int month=calendar.get(Calendar.MONTH) + 1;
        if (String.valueOf(month).length()==1)
            textView.setText(calendar.get(Calendar.YEAR)+"-0"+(calendar.get(Calendar.MONTH) + 1)+"-"+calendar.get(Calendar.DAY_OF_MONTH) );
        else
            textView.setText(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH) + 1)+"-"+calendar.get(Calendar.DAY_OF_MONTH) );

    }

    public void getCreateLeave(CreateNewLeaveRequestModel createNewLeaveRequest){
        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.createNewLeaveRequest(createNewLeaveRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CreatenewLeaveResponse>() {
                    @Override
                    public void onSuccess(CreatenewLeaveResponse createnewLeaveResponse) {
                        dismissPD(pd);
                        try {
                            Utils.showAlertDialogFinish(context,"Leave Request",createnewLeaveResponse.getMessage());
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
