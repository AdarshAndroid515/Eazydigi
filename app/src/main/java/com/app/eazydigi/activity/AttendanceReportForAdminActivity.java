package com.app.eazydigi.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.AttendanceReportForAdminAdapter;
import com.app.eazydigi.adapter.CustomSpinnerArrayAdapter;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.model.new_model.request.AttendanceListRequest;
import com.app.eazydigi.model.new_model.response.AttendanceListResponse;
import com.app.eazydigi.model.old_model.AttendanceDetailForStudentInfo;
import com.app.eazydigi.model.old_model.AttendanceSheetByDateInfo;
import com.app.eazydigi.model.old_model.StudentClassInfo;
import com.app.eazydigi.model.old_model.StudentSectionInfo;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceReportForAdminActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_attendance)
    RecyclerView rvAttendance;

    @BindView(R.id.spinner_class)
    Spinner spClass;

    @BindView(R.id.spinner_section)
    Spinner spSection;

    @BindView(R.id.tv_attendance_date)
    TextView tvAttendanceDate;

    @BindView(R.id.btn_search)
    Button btnSearch;

    AttendanceReportForAdminAdapter attendanceAdapter;

    DatePickerDialog datePickerDialog;

    Calendar calendar;

    int classId;
    int sectionId;
    private String ClassName="",SectionName="",DateC="";
    private List<String> classList,sectionList;

    private static final String TAG = "AdminAttendanceReportAc";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_report_admin);
        ButterKnife.bind(this);

        attendanceAdapter = new AttendanceReportForAdminAdapter(this);

        calendar = Calendar.getInstance();

        initToolbar();

        initViews();

        initDatePicker();

        //getClasses();

        //getSections();

        calendar.setTime(new Date(System.currentTimeMillis()));

        setDate();

        setSpinners();

        spClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ClassName=spClass.getItemAtPosition(position).toString();
                if (ClassName.equalsIgnoreCase("Nursery")){
                    ClassName="-3";
                }else if (ClassName.equalsIgnoreCase("L.K.G")){
                    ClassName="-2";
                }else if (ClassName.equalsIgnoreCase("U.K.G")){
                    ClassName="-1";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SectionName=String.valueOf(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    void setSpinners(){
        sectionList=new ArrayList<>();
        classList= new ArrayList<>();

        classList.add("Nursery");
        classList.add("L.K.G");
        classList.add("U.K.G");
        classList.add("1");
        classList.add("2");
        classList.add("3");
        classList.add("4");
        classList.add("5");
        classList.add("6");
        classList.add("7");
        classList.add("8");
        classList.add("9");
        classList.add("10");
        classList.add("11");
        classList.add("12");

        sectionList.add("A");
        sectionList.add("B");
        sectionList.add("C");
        sectionList.add("D");
        sectionList.add("E");
        sectionList.add("F");

        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, classList);
        spClass.setAdapter(classAdapter);

        ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sectionList);
        spSection.setAdapter(sectionAdapter);


    }

    @OnClick({R.id.tv_attendance_date, R.id.btn_search})
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.tv_attendance_date:
                datePickerDialog.show();
                break;
            case R.id.btn_search:
                //getAttendanceDetail();
                if (TextUtils.isEmpty(tvAttendanceDate.getText().toString())){
                    Toast.makeText(context, "Please select a date", Toast.LENGTH_SHORT).show();
                }else {
                    AttendanceListRequest attendanceListRequest = new AttendanceListRequest();
                    attendanceListRequest.setSec(SectionName);
                    attendanceListRequest.setClassX(ClassName);
                    attendanceListRequest.setAttDate(DateC);
                    getAttendanceList(attendanceListRequest);
                }

            default:
                break;

        }
    }

    public void getClasses() {

        ProgressHUD pd = showPD(null);
        MyApplication.getAppInstance().getAPIInterface().getStudentClass(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization()).enqueue(new Callback<List<StudentClassInfo>>() {

            @Override
            public void onResponse(Call<List<StudentClassInfo>> call, Response<List<StudentClassInfo>> response) {

                dismissPD(pd);
                List<StudentClassInfo> listStudentClassInfo = response.body();
//                spClass.setAdapter(new CustomSpinnerArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, listStudentClassInfo,1));

            }

            @Override
            public void onFailure(Call<List<StudentClassInfo>> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                dismissPD(pd);
            }
        });
    }

    public void getSections() {

        ProgressHUD pd = showPD(null);
        MyApplication.getAppInstance().getAPIInterface().getStudentSection(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization()).enqueue(new Callback<List<StudentSectionInfo>>() {

            @Override
            public void onResponse(Call<List<StudentSectionInfo>> call, Response<List<StudentSectionInfo>> response) {

                dismissPD(pd);

                List<StudentSectionInfo> listStudentSectionInfo = response.body();
                spSection.setAdapter(new CustomSpinnerArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, listStudentSectionInfo,4));

            }

            @Override
            public void onFailure(Call<List<StudentSectionInfo>> call, Throwable t) {
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                t.printStackTrace();
                dismissPD(pd);
            }
        });
    }

    private void getAttendanceDetail() {

        classId = ((StudentClassInfo) spClass.getSelectedItem()).getId();
        sectionId = ((StudentSectionInfo) spSection.getSelectedItem()).getId();

        ProgressHUD pd = showPD(null);
        MyApplication.getAppInstance().getAPIInterface().getAttendanceDetailByDate(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                classId,
                sectionId, tvAttendanceDate.getText().toString()).enqueue(new Callback<AttendanceSheetByDateInfo>() {
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

                            //attendanceAdapter.addAll(attendanceSheetByDateInfo.getList());

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
            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMaxDate(System.currentTimeMillis() - 1000);

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
                                attendanceAdapter.addAll(attendanceListResponse.getData());
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
}