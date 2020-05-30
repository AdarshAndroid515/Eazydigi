package com.app.eazydigi.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.AttendanceReportForTeacherAdapter;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.model.new_model.request.AttendanceListRequest;
import com.app.eazydigi.model.new_model.response.AttendanceListResponse;
import com.app.eazydigi.model.old_model.AttendanceDetailForStudentInfo;
import com.app.eazydigi.model.old_model.AttendanceSheetByDateInfo;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceReportForTeacherActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_attendance)
    RecyclerView rvAttendance;

    @BindView(R.id.tv_attendance_date)
    TextView tvAttendanceDate;

    @BindView(R.id.fab_date)
    FloatingActionButton fabDate;

    AttendanceReportForTeacherAdapter attendanceReportForTeacherAdapter;

    DatePickerDialog datePickerDialog;

    Calendar calendar;

    private static final String TAG = "AdminAttendanceReportAc";
    private String ClassName="",ClassSection="",DateC="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_report_teacher);
        ButterKnife.bind(this);

        attendanceReportForTeacherAdapter = new AttendanceReportForTeacherAdapter(this);

        ClassName=MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TEACHER_CLASS);
        ClassSection=MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TEACHER_SECTION);

        calendar = Calendar.getInstance();

        initToolbar();

        initViews();

        initDatePicker();

        calendar.setTime(new Date(System.currentTimeMillis()));

        setDate();

        if (TextUtils.isEmpty(tvAttendanceDate.getText().toString())){
            Toast.makeText(context, "Please select a date", Toast.LENGTH_SHORT).show();
        }else {
            AttendanceListRequest attendanceListRequest = new AttendanceListRequest();
            attendanceListRequest.setSec(ClassSection);
            attendanceListRequest.setClassX(ClassName);
            attendanceListRequest.setAttDate(DateC);
            getAttendanceList(attendanceListRequest);
        }

    }

    @OnClick({R.id.tv_attendance_date, R.id.fab_date})
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.fab_date:
                datePickerDialog.show();
                break;
            default:
                break;

        }
    }

    private void getAttendanceDetail() {

        ProgressHUD pd = showPD(null);
        MyApplication.getAppInstance().getAPIInterface().getAttendanceDetailByDate(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                MyApplication.getAppInstance().getLoginResponseInfo().getClassId(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSectionId(), tvAttendanceDate.getText().toString()).enqueue(new Callback<AttendanceSheetByDateInfo>() {
            @Override
            public void onResponse(Call<AttendanceSheetByDateInfo> call, Response<AttendanceSheetByDateInfo> response) {
                dismissPD(pd);
                try {
                    if (response.isSuccessful()) {

                        AttendanceSheetByDateInfo attendanceSheetByDateInfo = response.body();
                        if (attendanceSheetByDateInfo.getList() != null && attendanceSheetByDateInfo.getList().size() > 0) {

                            rvAttendance.setVisibility(View.VISIBLE);
                            Collections.sort(attendanceSheetByDateInfo.getList(), new Comparator<AttendanceDetailForStudentInfo>() {
                                @Override
                                public int compare(AttendanceDetailForStudentInfo attendanceDetailForStudentInfo, AttendanceDetailForStudentInfo t1) {
                                    return attendanceDetailForStudentInfo.getRollNumber().compareToIgnoreCase(t1.getRollNumber());
                                }
                            });

                           // attendanceReportForTeacherAdapter.addAll(attendanceSheetByDateInfo.getList());

                        } else {
                            rvAttendance.setVisibility(View.GONE);
                        }

                    } else {

                        rvAttendance.setVisibility(View.GONE);

                    }

                } catch (Exception e) {
                    Log.e(TAG, "--->exception " + e.getMessage());
                    e.printStackTrace();
                    rvAttendance.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<AttendanceSheetByDateInfo> call, Throwable t) {
                t.printStackTrace();
                dismissPD(pd);
            }
        });
    }

    private void getAttendanceList(AttendanceListRequest attendanceListRequest) {

        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getAttendanceList1(attendanceListRequest.getClassX(),
                attendanceListRequest.getSec(), attendanceListRequest.getAttDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<AttendanceListResponse>() {
                    @Override
                    public void onSuccess(AttendanceListResponse attendanceListResponse) {
                        dismissPD(pd);
                        try {
                            if (attendanceListResponse.getData()!=null && attendanceListResponse.getData().size()>0){
                                rvAttendance.setVisibility(View.VISIBLE);
                                attendanceReportForTeacherAdapter.addAll(attendanceListResponse.getData());
                            }else {
                                rvAttendance.setVisibility(View.GONE);
                                Toast.makeText(context, "No Data Found!", Toast.LENGTH_SHORT).show();
                            }
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

    private void setDate() {
        DateC=Utils.convertToDateString(calendar.getTimeInMillis(), "MM/dd/yyyy");
        tvAttendanceDate.setText(Utils.convertToDateString(calendar.getTimeInMillis(), "dd/MM/yyyy"));
    }

    public void initDatePicker() {

        datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setDate();
                //getAttendanceDetail();
                if (TextUtils.isEmpty(tvAttendanceDate.getText().toString())){
                    Toast.makeText(context, "Please select a date", Toast.LENGTH_SHORT).show();
                }else {
                    AttendanceListRequest attendanceListRequest = new AttendanceListRequest();
                    attendanceListRequest.setSec(ClassSection);
                    attendanceListRequest.setClassX(ClassName);
                    attendanceListRequest.setAttDate(DateC);
                    getAttendanceList(attendanceListRequest);
                }

            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMaxDate(System.currentTimeMillis() - 1000);

    }


    public void initViews() {
        rvAttendance.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rvAttendance.setAdapter(attendanceReportForTeacherAdapter);
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