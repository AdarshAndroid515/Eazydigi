package com.app.eazydigi.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.MarkAttendanceAdapter;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.listener.OnWriteNoteClickListener;
import com.app.eazydigi.model.new_model.request.AttendanceListRequest;
import com.app.eazydigi.model.new_model.response.MarkAttendanceListResponse;
import com.app.eazydigi.model.new_model.response.SaveAttendanceResponse;
import com.app.eazydigi.model.old_model.AttendanceDetailForStudentInfo;
import com.app.eazydigi.model.old_model.AttendanceSheetByDateInfo;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
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

public class MarkAttendanceActivity extends BaseActivity implements OnWriteNoteClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_attendance)
    RecyclerView rvAttendance;

    @BindView(R.id.tv_attendance_date)
    TextView tvAttendanceDate;

    @BindView(R.id.fab_date)
    FloatingActionButton fabDate;

    MarkAttendanceAdapter attendanceAdapter;
    AttendanceSheetByDateInfo attendanceSheetByDateInfo;
    MarkAttendanceListResponse attendanceListResponsePublic;
    DatePickerDialog datePickerDialog;

    Calendar calendar;

    private static final String TAG = "MarkAttendanceActivity";
    public static final int RESULT_ACTIVITY_CREATE_NOTICE = 123;
    private String ClassName="",ClassSection="";

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);
        ButterKnife.bind(this);

        attendanceAdapter = new MarkAttendanceAdapter(this);
        attendanceAdapter.setListener(this);

        ClassName=MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TEACHER_CLASS);
        ClassSection=MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TEACHER_SECTION);

        calendar = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = df.format(calendar.getTime());
        tvAttendanceDate.setText(formattedDate);
        initToolbar();

        initViews();

        initDatePicker();

        calendar.setTime(new Date(System.currentTimeMillis()));
        setDate();

        if (Utils.isConnectedToInternet(context)) {
            //getAttendanceDetail();
            if (TextUtils.isEmpty(tvAttendanceDate.getText().toString())){

            }else {
                AttendanceListRequest attendanceListRequest = new AttendanceListRequest();
                attendanceListRequest.setSec(ClassSection);
                attendanceListRequest.setClassX(ClassName);
                attendanceListRequest.setAttDate(formattedDate);
                getAttendanceList(attendanceListRequest);
            }
        }

        try { fabDate.setVisibility(View.GONE); }catch (Exception e){}

    }

    @OnClick({R.id.fab_date})
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
/*
    private void updateAttendanceDetail() {

        ProgressHUD pd = showPD(null);
        MyApplication.getAppInstance().getAPIInterface().updateAttendanceSheet(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                Utils.convertToDateString(calendar.getTimeInMillis(), "MM/dd/yyyy"),
                MyApplication.getAppInstance().getLoginResponseInfo().getUserId(),
                getBody()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dismissPD(pd);
                try {
                    if (response.isSuccessful()) {

                        String responseString = response.body().string();

                        if (responseString.equalsIgnoreCase("true")) {
                            Toast.makeText(context, getString(R.string.attendance_update_successfully), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                dismissPD(pd);
            }
        });
    }*/

    private void setDate() {
        tvAttendanceDate.setText(Utils.convertToDateString(calendar.getTimeInMillis(), "dd/MM/yyyy"));
    }

    private void getAttendanceDetail() {

        ProgressHUD pd = showPD(null);
        MyApplication.getAppInstance().getAPIInterface().getAttendanceDetailByDate(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                MyApplication.getAppInstance().getLoginResponseInfo().getClassId(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSectionId(),
                Utils.convertToDateString(calendar.getTimeInMillis(), "MM/dd/yyyy")).enqueue(new Callback<AttendanceSheetByDateInfo>() {
            @Override
            public void onResponse(Call<AttendanceSheetByDateInfo> call, Response<AttendanceSheetByDateInfo> response) {
                dismissPD(pd);
                try {
                    if (response.isSuccessful()) {

                        attendanceSheetByDateInfo = response.body();

                        if (attendanceSheetByDateInfo != null && attendanceSheetByDateInfo.getList() != null) {
                            Collections.sort(attendanceSheetByDateInfo.getList(), new Comparator<AttendanceDetailForStudentInfo>() {
                                @Override
                                public int compare(AttendanceDetailForStudentInfo attendanceDetailForStudentInfo, AttendanceDetailForStudentInfo t1) {
                                    return attendanceDetailForStudentInfo.getRollNumber().compareToIgnoreCase(t1.getRollNumber());
                                }
                            });

                            //attendanceAdapter.addAll(attendanceSheetByDateInfo.getList());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
        MyApplication.getAppInstance().getDisposable().add(apiService.getMarkAttendanceList(attendanceListRequest.getClassX(),
                attendanceListRequest.getSec(), attendanceListRequest.getAttDate())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<MarkAttendanceListResponse>() {
                    @Override
                    public void onSuccess(MarkAttendanceListResponse attendanceListResponse) {
                        dismissPD(pd);
                        try {
                            attendanceListResponsePublic=attendanceListResponse;
                            if (attendanceListResponse.getData()!=null && attendanceListResponse.getData().size()>0){
                                rvAttendance.setVisibility(View.VISIBLE);
                                attendanceAdapter.addAll(attendanceListResponsePublic.getData());
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

    public void initDatePicker() {

        datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                setDate();
                if (Utils.isConnectedToInternet(context)) {
                    //getAttendanceDetail();
                    AttendanceListRequest attendanceListRequest = new AttendanceListRequest();
                    attendanceListRequest.setSec(ClassSection);
                    attendanceListRequest.setClassX(ClassName);
                    attendanceListRequest.setAttDate(tvAttendanceDate.getText().toString());
                    getAttendanceList(attendanceListRequest);
                }

            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMaxDate(System.currentTimeMillis() - 1000);

    }

    @Override
    public void onWriteNoteClick2(int position, String Reason, MarkAttendanceListResponse.DataBean dataBean) {
        CustomDialogClass cdd=new CustomDialogClass(MarkAttendanceActivity.this,position);
        cdd.show();
    }

    public class CustomDialogClass extends Dialog {

        public Activity activity;
        public Dialog d;
        private int position;

        public CustomDialogClass(Activity activity,int pos) {
            super(activity);
            // TODO Auto-generated constructor stub
            this.activity = activity;
            this.position=pos;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.enter_notes_dialog);

            ImageView backBtn=findViewById(R.id.backBtn_notesDialog);
            ImageView saveBtn=findViewById(R.id.saveBtn_notesDialog);
            EditText edNotes =findViewById(R.id.editNotes_notesDialog);


            saveBtn.setOnClickListener(v -> {
                if (!TextUtils.isEmpty(edNotes.getText().toString().trim())){
                    attendanceListResponsePublic.getData().get(position).setNotes(edNotes.getText().toString().trim());
                    attendanceAdapter.notifyDataSetChanged();
                    dismiss();
                }else {
                    edNotes.setError("Please Enter Any Text");
                }


            });

            backBtn.setOnClickListener(v -> {
                dismiss();
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.attendance_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_absent:

                for (int i = 0; i < attendanceAdapter.listAttendanceDetail.size(); i++) {
                    attendanceAdapter.listAttendanceDetail.get(i).setStatus(2);
                    attendanceAdapter.notifyDataSetChanged();

                    attendanceListResponsePublic.getData().get(i).setStatus(2);

                }
                break;
            case R.id.action_present:
                for (int i = 0; i < attendanceAdapter.listAttendanceDetail.size(); i++) {
                    attendanceAdapter.listAttendanceDetail.get(i).setStatus(1);
                    attendanceAdapter.notifyDataSetChanged();

                    attendanceListResponsePublic.getData().get(i).setStatus(1);
                }
                break;
            case R.id.action_late:
                for (int i = 0; i < attendanceAdapter.listAttendanceDetail.size(); i++) {
                    attendanceAdapter.listAttendanceDetail.get(i).setStatus(0);
                    attendanceAdapter.notifyDataSetChanged();
                    attendanceListResponsePublic.getData().get(i).setStatus(0);
                }
                break;
            case R.id.action_holiday:
                for (int i = 0; i < attendanceAdapter.listAttendanceDetail.size(); i++) {
                    attendanceAdapter.listAttendanceDetail.get(i).setStatus(3);
                    attendanceAdapter.notifyDataSetChanged();
                    attendanceListResponsePublic.getData().get(i).setStatus(3);
                }
                break;
            case R.id.action_save:
                if (Utils.isConnectedToInternet(MarkAttendanceActivity.this)) {
                    if (attendanceListResponsePublic == null) {
                        Toast.makeText(context, "Please select data first to save attendance", Toast.LENGTH_SHORT).show();
                    } else {
                        //updateAttendanceDetail();
                        saveAttandanceList();
                    }

                } else {
                    Toast.makeText(context, getString(R.string.alert_check_connection), Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void saveAttandanceList(){
        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getSaveAttendanceList(attendanceListResponsePublic.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<SaveAttendanceResponse>() {
                    @Override
                    public void onSuccess(SaveAttendanceResponse saveAttendanceResponse) {
                        dismissPD(pd);
                        try {

                            Utils.showAlertDialogFinish(context,"Mark Attendance",saveAttendanceResponse.getMessage());

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

    /*
    private JsonObject getBody() {

        JsonObject mainJsonObject = new JsonObject();
        mainJsonObject.addProperty("id", attendanceSheetByDateInfo.getId());
        mainJsonObject.addProperty("attendanceMonth", (calendar.get(Calendar.MONTH) + 1));
        mainJsonObject.addProperty("attendanceYear", calendar.get(Calendar.YEAR));
        mainJsonObject.addProperty("classId", MyApplication.getAppInstance().getLoginResponseInfo().getClassId());
        mainJsonObject.addProperty("sectionId", MyApplication.getAppInstance().getLoginResponseInfo().getSectionId());
        mainJsonObject.addProperty("schoolId", MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId());

        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < attendanceAdapter.listAttendanceDetail.size(); i++) {

            JsonObject studentDetail = new JsonObject();
            studentDetail.addProperty("id", attendanceAdapter.listAttendanceDetail.get(i).getId());
            studentDetail.addProperty("studentName", attendanceAdapter.listAttendanceDetail.get(i).getStudentName());
//            studentDetail.addProperty("studentProfileImage", attendanceAdapter.listAttendanceDetail.get(i).getStudentProfileImage());
            studentDetail.addProperty("studentID", attendanceAdapter.listAttendanceDetail.get(i).getStudentID());
            studentDetail.addProperty("rollNumber", attendanceAdapter.listAttendanceDetail.get(i).getRollNumber());

            JsonArray jsonArrayAttendance = new JsonArray();
            JsonObject studentAttendanceDetail = new JsonObject();
            studentAttendanceDetail.addProperty("id", attendanceAdapter.listAttendanceDetail.get(i).getAttendanceList().get(0).getId());
            studentAttendanceDetail.addProperty("day", attendanceAdapter.listAttendanceDetail.get(i).getAttendanceList().get(0).getDay());
            studentAttendanceDetail.addProperty("dayName", attendanceAdapter.listAttendanceDetail.get(i).getAttendanceList().get(0).getDayName());
            studentAttendanceDetail.addProperty("attendanceStatus", attendanceAdapter.listAttendanceDetail.get(i).getAttendanceList().get(0).getAttendanceStatus());
            studentAttendanceDetail.addProperty("attendanceStatusName", attendanceAdapter.listAttendanceDetail.get(i).getAttendanceList().get(0).getAttendanceStatusName());
            studentAttendanceDetail.addProperty("attendanceStatusReason", attendanceAdapter.listAttendanceDetail.get(i).getAttendanceList().get(0).getAttendanceStatusReason());
            jsonArrayAttendance.add(studentAttendanceDetail);
            studentDetail.add("attendance", jsonArrayAttendance);

            jsonArray.add(studentDetail);


        }
        mainJsonObject.add("attendanceDetail", jsonArray);
        return mainJsonObject;
    }*/

    public void initViews() {
        rvAttendance.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rvAttendance.setAdapter(attendanceAdapter);
    }

    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Mark Attendance");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}