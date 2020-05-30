package com.app.eazydigi.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.CustomSpinnerArrayAdapter;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.model.new_model.response.HomeWorkDashboardTeacherResponse;
import com.app.eazydigi.model.new_model.response.UpdateHomeworkTeacherResponse;
import com.app.eazydigi.model.old_model.FilteredStudentInfo;
import com.app.eazydigi.model.old_model.HWStatusInfo;
import com.app.eazydigi.model.old_model.StudentHWAssignmentByStatusInfo;
import com.app.eazydigi.model.old_model.StudentHWAssignmentStateInfo;
import com.app.eazydigi.model.old_model.SubjectInfo;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
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

import static com.app.eazydigi.activity.TeacherHomeworkActivity.studentListBean;

public class UpdateHomeWorkByTeacherActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinner_subject)
    Spinner spSubject;

    @BindView(R.id.spinner_status)
    Spinner spStatus;

    @BindView(R.id.tv_due_date)
    TextView tvDueDate;

    @BindView(R.id.et_header)
    EditText etHeader;

    @BindView(R.id.et_description)
    EditText etDescription;

    @BindView(R.id.tv_error)
    TextView tvError;

    @BindView(R.id.btn_update)
    Button btnUpdate;

    @BindView(R.id.btn_cancel)
    Button btnCancel;

    DatePickerDialog datePickerDialog;

    Calendar calendar;

    String subject;
    String status;
    String dueDate;
    String header;
    String description;

    List<HWStatusInfo> listHwStatusInfo;
    List<SubjectInfo> listSubjectInfo;
    //TeacherHWAssignmentByStatusInfo teacherHWAssignmentByStatusInfo;
    HomeWorkDashboardTeacherResponse.DataBean.StudentListBean teacherHWAssignmentByStatusInfo;

    int subjectId;
    int statusId;
    int statusID=0;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_homework_by_teacher);
        ButterKnife.bind(this);

        calendar = Calendar.getInstance();
        teacherHWAssignmentByStatusInfo=studentListBean;

        initToolbar();

        initDatePicker();

//        if (getIntent() != null && getIntent().hasExtra(EXTRA_TEACHER_HW_ASSIGNMENT_STATUS_INFO)) {
//
//            teacherHWAssignmentByStatusInfo = (TeacherHWAssignmentByStatusInfo) getIntent().getSerializableExtra(EXTRA_TEACHER_HW_ASSIGNMENT_STATUS_INFO);
//
//            setData();
//
//        }
        setData();
        //getAllHomeworkStatus();

        getSubjects();
        setSpinners();
        spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String STATUS=spStatus.getItemAtPosition(position).toString();
                if (STATUS.equalsIgnoreCase("Completed")){
                    statusID=1;
                }else if (STATUS.equalsIgnoreCase("Pending")){
                    statusID=2;
                }else if (STATUS.equalsIgnoreCase("Need Rework")){
                    statusID=7;
                }else if (STATUS.equalsIgnoreCase("Overdue")){
                    statusID=4;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    void setSpinners(){
        ArrayList<String> statusList=new ArrayList<>();

        statusList.add("Completed");
        statusList.add("Pending");
        statusList.add("Need Rework");
        statusList.add("Overdue");

        ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusList);
        spStatus.setAdapter(sectionAdapter);

        if(teacherHWAssignmentByStatusInfo.getStatus()==2){
            spStatus.setSelection(1);
        }else if(teacherHWAssignmentByStatusInfo.getStatus()==7) {
            spStatus.setSelection(2);
        } else if(teacherHWAssignmentByStatusInfo.getStatus()==4) {
            spStatus.setSelection(3);
        }else if(teacherHWAssignmentByStatusInfo.getStatus()==1) {
            spStatus.setSelection(0);
        }
    }
    private void setDate() {
        int month=calendar.get(Calendar.MONTH) + 1;
        if (String.valueOf(month).length()==1)
            tvDueDate.setText(calendar.get(Calendar.YEAR)+"-0"+(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) );
        else
            tvDueDate.setText(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) );
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
        datePicker.setMinDate(System.currentTimeMillis() - 1000);

    }

    private boolean isValid() {

        boolean isValid = false;

        subject = spSubject.getSelectedItem().toString();
        dueDate = tvDueDate.getText().toString();
        status = spStatus.getSelectedItem().toString();
        header = etHeader.getText().toString();
        description = etDescription.getText().toString().trim();

        if (dueDate.length() == 0) {
            tvError.setText(getString(R.string.error_enter_due_date));
        } else if (description.length() == 0) {
            tvError.setText(getString(R.string.error_enter_description));
        } else if (header.length() == 0) {
            tvError.setText(getString(R.string.error_enter_header));
        } else {
            isValid = true;
        }

        if (!isValid) {
            tvError.setVisibility(View.VISIBLE);
        }

        return isValid;
    }

    @OnClick({R.id.tv_due_date, R.id.btn_update, R.id.btn_cancel})
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.tv_due_date:
                datePickerDialog.show();
                break;
            case R.id.btn_update:
                if (isValid()) {
                    if (Utils.isConnectedToInternet(UpdateHomeWorkByTeacherActivity.this)) {
                        //updateHomeworkStatus();

                        teacherHWAssignmentByStatusInfo.setDescription(description);
                        teacherHWAssignmentByStatusInfo.setHeader(header);
                        teacherHWAssignmentByStatusInfo.setStatus(statusID);
                        teacherHWAssignmentByStatusInfo.setDueDate(tvDueDate.getText().toString().trim());
                        getUpdateHomework(teacherHWAssignmentByStatusInfo);
                    } else {
                        Toast.makeText(context, getString(R.string.alert_check_connection), Toast.LENGTH_SHORT).show();
                    }
                }


                break;
            case R.id.btn_cancel:
                finish();
                break;
            default:
                break;

        }
    }

    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getString(R.string.update_homework));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public void setData() {

        try {
            etDescription.setText("" + teacherHWAssignmentByStatusInfo.getDescription());
            etHeader.setText("" + teacherHWAssignmentByStatusInfo.getHeader());
            tvDueDate.setText("" + teacherHWAssignmentByStatusInfo.getDueDate());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /*
    public void getAllHomeworkStatus() {

        MyApplication.getAppInstance().getAPIInterface().getAllHomeworkStatus(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId()).enqueue(new Callback<List<HWStatusInfo>>() {
            @Override
            public void onResponse(Call<List<HWStatusInfo>> call, Response<List<HWStatusInfo>> response) {

                listHwStatusInfo = new ArrayList<>();

                if (response.isSuccessful()) {
                    listHwStatusInfo = response.body();
                    spStatus.setAdapter(new CustomSpinnerArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, listHwStatusInfo, 2));

                } else {

                    String responseJson = Utils.loadJSONFromAsset(context, "GetAllHomeworkStatus.json");
                    listHwStatusInfo = new Gson().fromJson(responseJson, new TypeToken<List<HWStatusInfo>>() {
                    }.getType());
                    spStatus.setAdapter(new CustomSpinnerArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, listHwStatusInfo, 2));

                }

                for (int i = 0; i < listHwStatusInfo.size(); i++) {

                    if (listHwStatusInfo.get(i).getStatus().equalsIgnoreCase(teacherHWAssignmentByStatusInfo.getStatus())){
                        spStatus.setSelection(i);
                        break;
                    }

                }

            }

            @Override
            public void onFailure(Call<List<HWStatusInfo>> call, Throwable t) {
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
    */

    public void getSubjects() {

        MyApplication.getAppInstance().getAPIInterface().getSubjects(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization()).enqueue(new Callback<List<SubjectInfo>>() {
            @Override
            public void onResponse(Call<List<SubjectInfo>> call, Response<List<SubjectInfo>> response) {
                Log.e(TAG, "---->onResponse: " + response.body());
                listSubjectInfo = new ArrayList<>();
                if (response.isSuccessful()) {

                    listSubjectInfo = response.body();
                    spSubject.setAdapter(new CustomSpinnerArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, listSubjectInfo, 3));

                } else {
                    Log.e(TAG, "---->elseonResponse: ");
                    String responseJson = Utils.loadJSONFromAsset(context, "GetSubjects.json");
                    listSubjectInfo = new Gson().fromJson(responseJson, new TypeToken<List<SubjectInfo>>() {
                    }.getType());
                    spSubject.setAdapter(new CustomSpinnerArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, listSubjectInfo, 3));

                }

                String subject="";
                if (teacherHWAssignmentByStatusInfo.getSubject().equals("1")){
                    subject="English";
                }else if (teacherHWAssignmentByStatusInfo.getSubject().equals("2")){
                    subject="Science";
                }else if (teacherHWAssignmentByStatusInfo.getSubject().equals("3")){
                    subject="Social Science";
                }else if (teacherHWAssignmentByStatusInfo.getSubject().equals("4")){
                    subject="Maths";
                }else if (teacherHWAssignmentByStatusInfo.getSubject().equals("5")){
                    subject="Hindi";
                }else if (teacherHWAssignmentByStatusInfo.getSubject().equals("6")){
                    subject="Computer";
                }


                for (int i = 0; i < listSubjectInfo.size(); i++) {

                    if (listSubjectInfo.get(i).getValue().equalsIgnoreCase(subject)){
                        spSubject.setSelection(i);
                        break;
                    }

                }

            }

            @Override
            public void onFailure(Call<List<SubjectInfo>> call, Throwable t) {
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });


    }

    public void getUpdateHomework(HomeWorkDashboardTeacherResponse.DataBean.StudentListBean  studentListBean){
        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getUpdateHomeworkTeacher(studentListBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UpdateHomeworkTeacherResponse>() {
                    @Override
                    public void onSuccess(UpdateHomeworkTeacherResponse updateHomeworkTeacherResponse) {
                        dismissPD(pd);
                        try {
                            Utils.showAlertDialogFinish(context,"Update Homework",updateHomeworkTeacherResponse.getMessage());

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
    public void updateHomeworkStatus() {
        Log.e(TAG, "--->updateHomeworkStatus:assignmentid "+teacherHWAssignmentByStatusInfo.getAssignmentId());

        MyApplication.getAppInstance().getAPIInterface().updateHomeworkStatus(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization(),
                teacherHWAssignmentByStatusInfo.getAssignmentId(), MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), getUpdatedHWStatusBody()
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e(TAG, "--->onResponse: " + response.body());

                if (response.isSuccessful()) {
                    Log.e(TAG, "--->onResponse Successfull ");

                } else {


                }

                Toast.makeText(context,"Homework Updated SuccessFully....",Toast.LENGTH_SHORT).show();
                finish();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
    */

    /*
    public JsonObject getUpdatedHWStatusBody() {

        Log.e(TAG, "--->getUpdatedHWStatusBody: ");

        statusId = ((HWStatusInfo) spStatus.getSelectedItem()).getId();

        subjectId = ((SubjectInfo) spSubject.getSelectedItem()).getId();



        JsonObject mainJsonObject = new JsonObject();
        mainJsonObject.addProperty("HomeworkId", teacherHWAssignmentByStatusInfo.getHomeworkId());
        mainJsonObject.addProperty("StatusId", statusId);
        mainJsonObject.addProperty("AssignedTo", teacherHWAssignmentByStatusInfo.getAsignedTo());
        mainJsonObject.addProperty("LastUpdatedOn", Utils.convertToDateString(System.currentTimeMillis(),"yyyy-MM-dd") );
        mainJsonObject.addProperty("LastUpdatedBy", MyApplication.getAppInstance().getLoginResponseInfo().getUserId());

        return mainJsonObject;
    }
    */

    public void getFilteredStudents() {

        MyApplication.getAppInstance().getAPIInterface().getFilteredStudents(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                0, 2000, 1,
                1, 0, 0, "NA", MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), 1).enqueue(new Callback<List<FilteredStudentInfo>>() {
            @Override
            public void onResponse(Call<List<FilteredStudentInfo>> call, Response<List<FilteredStudentInfo>> response) {
                Log.e(TAG, "---->onResponse: " + response.body());

                if (response.isSuccessful()) {

                } else {
                    Log.e(TAG, "---->elseonResponse: ");
                    String responseJson = Utils.loadJSONFromAsset(context, "GetFilteredStudents.json");
                    List<FilteredStudentInfo> filteredStudentInfo = new Gson().fromJson(responseJson, new TypeToken<List<FilteredStudentInfo>>() {
                    }.getType());



                }

            }

            @Override
            public void onFailure(Call<List<FilteredStudentInfo>> call, Throwable t) {
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });


    }

    public void getStudentHwAssignmentState() {

        MyApplication.getAppInstance().getAPIInterface().getStudentHwAssignmentState(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization(),
                924, MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId()
        ).enqueue(new Callback<List<StudentHWAssignmentStateInfo>>() {
            @Override
            public void onResponse(Call<List<StudentHWAssignmentStateInfo>> call, Response<List<StudentHWAssignmentStateInfo>> response) {
                Log.e(TAG, "--->onResponse: " + response.body());

                if (response.isSuccessful()) {

                } else {
                    Log.e(TAG, "--->elseonResponse: ");
                    String responseJson = Utils.loadJSONFromAsset(context, "GetStudentHwAssignmentState.json");
                    List<StudentHWAssignmentStateInfo> hwAssignmentStateForStudentInfo = new Gson().fromJson(responseJson, new TypeToken<List<StudentHWAssignmentStateInfo>>() {
                    }.getType());
                    Log.e(TAG, "----> StudentHWAssignmentStateInfo" + hwAssignmentStateForStudentInfo.get(0).getStatus());

                }

            }

            @Override
            public void onFailure(Call<List<StudentHWAssignmentStateInfo>> call, Throwable t) {
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void getHwAssignmentsToStudentByStatus() {

        MyApplication.getAppInstance().getAPIInterface().getHwAssignmentsToStudentByStatus(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), 1606, 3
        ).enqueue(new Callback<List<StudentHWAssignmentByStatusInfo>>() {
            @Override
            public void onResponse(Call<List<StudentHWAssignmentByStatusInfo>> call, Response<List<StudentHWAssignmentByStatusInfo>> response) {
                Log.e(TAG, "--->onResponse: " + response.body());

                if (response.isSuccessful()) {

                } else {
                    String responseJson = Utils.loadJSONFromAsset(context, "GetHwAssignmentsToStudentByStatus");
                    List<StudentHWAssignmentByStatusInfo> hwAssignmentStatusForStudentInfo = new Gson().fromJson(responseJson, new TypeToken<List<StudentHWAssignmentByStatusInfo>>() {
                    }.getType());
                    Log.e(TAG, "--->StudentHWAssignmentByStatusInfo" + hwAssignmentStatusForStudentInfo.get(0).getStatus());
                }
            }

            @Override
            public void onFailure(Call<List<StudentHWAssignmentByStatusInfo>> call, Throwable t) {
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}





