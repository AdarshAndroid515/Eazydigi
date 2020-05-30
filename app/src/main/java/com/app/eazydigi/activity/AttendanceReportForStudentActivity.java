package com.app.eazydigi.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.AttendanceReportForStudentAdapter;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.model.new_model.response.AttendanceStudentResponse;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class AttendanceReportForStudentActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_attendance)
    RecyclerView rvAttendance;

    @BindView(R.id.tv_attendance_date)
    TextView tvAttendanceDate;

    @BindView(R.id.fab_date)
    FloatingActionButton fabDate;

    AttendanceReportForStudentAdapter attendanceAdapter;

    DatePickerDialog datePickerDialog;
    private AttendanceStudentResponse attendanceStudentResponse;

    Calendar calendar;
    int selectedMonth;
    private String dateC="";

    private static final String TAG = "MarkAttendanceActivity";
    public static final int RESULT_ACTIVITY_CREATE_NOTICE = 123;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_report_student);
        ButterKnife.bind(this);

        attendanceAdapter = new AttendanceReportForStudentAdapter(this);

        calendar = Calendar.getInstance();

        initToolbar();

        initViews();

        initDatePicker();

        calendar.setTime(new Date(System.currentTimeMillis()));

        selectedMonth = (calendar.get(Calendar.MONTH) + 1);
        setDate();

        if (Utils.isConnectedToInternet(context)) {
            //getAttendanceByMonth();
            getAttendanceByDate(dateC);
        }

    }

    @OnClick({R.id.fab_date})
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.fab_date:
                Log.e(TAG, "--->fab_dateonClick:");
                //createDialogWithoutDateField().show();
                datePickerDialog.show();
                break;
            default:
                break;

        }
    }

    /*
    private void getAttendanceByMonth() {

        ProgressHUD pd = showPD(null);
        MyApplication.getAppInstance().getAPIInterface().getAttendanceByMonthForStudent(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(), MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                MyApplication.getAppInstance().getLoginResponseInfo().getUserId(),
                selectedMonth).enqueue(new Callback<List<StudentAttendanceReportByMonthInfo>>() {

            @Override
            public void onResponse(Call<List<StudentAttendanceReportByMonthInfo>> call, Response<List<StudentAttendanceReportByMonthInfo>> response) {
                dismissPD(pd);
                try {

                    if (response.isSuccessful()) {

                        List<StudentAttendanceReportByMonthInfo> listAttendanceByMoth = response.body();
                        attendanceAdapter.addAll(listAttendanceByMoth);
                        if (listAttendanceByMoth.size() == 0) {
                            rvAttendance.setVisibility(View.GONE);
                        }else{
                            rvAttendance.setVisibility(View.VISIBLE);
                        }
                    } else {
                        rvAttendance.setVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    rvAttendance.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<List<StudentAttendanceReportByMonthInfo>> call, Throwable t) {
                t.printStackTrace();
                dismissPD(pd);
            }
        });
    }*/

    private void getAttendanceByDate(String date) {

        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getAttendanceStudentByDate(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<AttendanceStudentResponse>() {
                    @Override
                    public void onSuccess(AttendanceStudentResponse attendanceResponse) {
                        dismissPD(pd);
                        try {
                            if (attendanceResponse!=null && attendanceResponse.getData()!=null && attendanceResponse.getData().size()>0){
                                attendanceStudentResponse = attendanceResponse;
                                attendanceAdapter.addAll(attendanceStudentResponse.getData());
                                rvAttendance.setVisibility(View.VISIBLE);
                            }else {
                                rvAttendance.setVisibility(View.GONE);
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

    private void setDate() {
        dateC=Utils.convertToDateString(calendar.getTimeInMillis(),"MM/dd/yyyy");
        tvAttendanceDate.setText(Utils.convertToDateString(calendar.getTimeInMillis(),"dd/MM/yyyy"));
    }

    public void initDatePicker() {

        datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setDate();
                getAttendanceByDate(dateC);
            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMaxDate(System.currentTimeMillis() - 1000);

    }

    private DatePickerDialog createDialogWithoutDateField() {

        datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        setDate();

                        selectedMonth = (calendar.get(Calendar.MONTH) + 1);

                        if (Utils.isConnectedToInternet(context)) {
                            //getAttendanceByMonth();
                            //getAttendanceByDate(tvAttendanceDate.getText().toString().trim());
                        }

                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        ((ViewGroup) datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);

        return datePickerDialog;

    }

    public void initViews() {
        rvAttendance.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rvAttendance.setAdapter(attendanceAdapter);
    }

    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(context.getString(R.string.attendance_report));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}