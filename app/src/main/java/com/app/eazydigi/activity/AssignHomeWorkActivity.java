package com.app.eazydigi.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.CustomSpinnerArrayAdapter;
import com.app.eazydigi.adapter.StudentListAdapter;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.model.new_model.request.AssignHomeworkRequest;
import com.app.eazydigi.model.new_model.response.AssignHomeworkResponse;
import com.app.eazydigi.model.new_model.response.StudentListResponse;
import com.app.eazydigi.model.old_model.HWStatusInfo;
import com.app.eazydigi.model.old_model.SubjectInfo;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignHomeWorkActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinner_subject)
    Spinner spSubject;

    @BindView(R.id.spinner_status)
    Spinner spStatus;

    @BindView(R.id.spinner_Class)
    Spinner spClass;

    @BindView(R.id.spinner_Section)
    Spinner spSection;

    @BindView(R.id.tv_due_date)
    TextView tvDueDate;

    @BindView(R.id.et_header)
    EditText etHeader;

    @BindView(R.id.et_description)
    EditText etDescription;

    @BindView(R.id.tv_error)
    TextView tvError;

    DatePickerDialog datePickerDialog;

    Calendar calendar;

    String subject;
    String status;
    String dueDate;
    String header;
    String description;
    String ClassName="",SectionName="",SubjectName;

    List<HWStatusInfo> listHwStatusInfo;
    List<SubjectInfo> listSubjectInfo;
    private List<String> classList,sectionList,subjectList;
    private StudentListResponse studentListResponse=new StudentListResponse();
    private List<Integer> studentSelectedList=new ArrayList<>();

    private static final String TAG = AssignHomeWorkActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_home_work);
        ButterKnife.bind(this);

        studentSelectedList.clear();
        calendar = Calendar.getInstance();

        initToolbar();

        initDatePicker();

        getAllHomeworkStatus();

        //getSubjects();

        setSpinners();

        findViewById(R.id.selectStudentBtn).setOnClickListener(this::onClick);

        spClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    ClassName=spClass.getItemAtPosition(position).toString();
                    if (ClassName.equalsIgnoreCase("Nursery")){
                        ClassName="-3";
                    }else if (ClassName.equalsIgnoreCase("L.K.G")){
                        ClassName="-2";
                    }else if (ClassName.equalsIgnoreCase("U.K.G")){
                        ClassName="-1";
                    }
                }else
                    ClassName="";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    SectionName=spSection.getItemAtPosition(position).toString();
                    if (SectionName.equalsIgnoreCase("A")){
                        SectionName="1";
                    }else if (SectionName.equalsIgnoreCase("B")){
                        SectionName="2";
                    }else if (SectionName.equalsIgnoreCase("C")){
                        SectionName="3";
                    }else if (SectionName.equalsIgnoreCase("D")){
                        SectionName="4";
                    }else if (SectionName.equalsIgnoreCase("E")){
                        SectionName="5";
                    }else if (SectionName.equalsIgnoreCase("F")){
                        SectionName="6";
                    }
                    if (!TextUtils.isEmpty(ClassName) && !TextUtils.isEmpty(SectionName))
                        getStudentList(ClassName,SectionName);

                }else {
                    SectionName="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    SubjectName=spSubject.getItemAtPosition(position).toString();
                }else
                    SubjectName="";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void setDate() {

        tvDueDate.setText(Utils.convertToDateString(calendar.getTimeInMillis(),"dd/MM/yyyy"));

    }

    void setSpinners(){
        sectionList=new ArrayList<>();
        classList= new ArrayList<>();
        subjectList= new ArrayList<>();

        classList.add("Select Class");
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

        sectionList.add("Select Section");
        sectionList.add("A");
        sectionList.add("B");
        sectionList.add("C");
        sectionList.add("D");
        sectionList.add("E");
        sectionList.add("F");

        subjectList.add("Select Subject");
        subjectList.add("English");
        subjectList.add("Science");
        subjectList.add("Social Science");
        subjectList.add("Maths");
        subjectList.add("Hindi");
        subjectList.add("Computer");

        ArrayAdapter<String> classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, classList);
        spClass.setAdapter(classAdapter);

        ArrayAdapter<String> sectionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sectionList);
        spSection.setAdapter(sectionAdapter);

        ArrayAdapter<String> subjectAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subjectList);
        spSubject.setAdapter(subjectAdapter);
    }

    private void getStudentList(String className,String sectionName) {

        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getStudentList(className,"","","",sectionName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<StudentListResponse>() {
                    @Override
                    public void onSuccess(StudentListResponse studentListResponse1) {
                        dismissPD(pd);

                        try {
                            studentListResponse=studentListResponse1;
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
        } else if (header.length() == 0) {
            tvError.setText(getString(R.string.error_enter_header));
        } else if (description.length() == 0) {
            tvError.setText(getString(R.string.error_enter_description));
        } else {
            isValid = true;
        }

        if (!isValid) {
            tvError.setVisibility(View.VISIBLE);
        }

        return isValid;
    }

    @OnClick({R.id.tv_due_date, R.id.btn_assign})
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.tv_due_date:
                datePickerDialog.show();
                break;

            case R.id.selectStudentBtn:

                try {
                    if (studentListResponse!=null && studentListResponse.getData()!=null && studentListResponse.getData().size()>0){
                        CustomDialogClass cdd=new CustomDialogClass(AssignHomeWorkActivity.this);
                        cdd.show();
                    }else {
                        Toast.makeText(context, "Please Select Class And Section First", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
               // showStudentDialog();
                break;

            case R.id.btn_assign:
                if (isValid()) {
                    if (Utils.isConnectedToInternet(AssignHomeWorkActivity.this)) {
                        //assignHomework();
                        if (studentSelectedList.size()>0){
                            AssignHomeworkRequest assignHomeworkRequest=new AssignHomeworkRequest();
                            assignHomeworkRequest.setDescription(description);
                            assignHomeworkRequest.setHeader(header);
                            assignHomeworkRequest.setDueDate(parseDateToddMMyyyy(dueDate));
                            assignHomeworkRequest.setStudIdList(studentSelectedList);

                            if (SubjectName.equalsIgnoreCase("English")){
                                assignHomeworkRequest.setSubject("1");
                            }else if (SubjectName.equalsIgnoreCase("Science")){
                                assignHomeworkRequest.setSubject("2");
                            }else if (SubjectName.equalsIgnoreCase("Maths")){
                                assignHomeworkRequest.setSubject("4");
                            }else if (SubjectName.equalsIgnoreCase("Hindi")){
                                assignHomeworkRequest.setSubject("5");
                            }else if (SubjectName.equalsIgnoreCase("Computer")){
                                assignHomeworkRequest.setSubject("6");
                            }else{
                                assignHomeworkRequest.setSubject("3");
                            }


                            getAssignHomework(assignHomeworkRequest);
                        }else {
                            Toast.makeText(context, "Please Select Students First", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(context, getString(R.string.alert_check_connection), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                break;

        }
    }

    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getString(R.string.assign_homework));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

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

                    if (listHwStatusInfo.get(i).getStatus().equalsIgnoreCase("pending")){
                        spStatus.setSelection(i);
                        break;
                    }

                }

            }

            @Override
            public void onFailure(Call<List<HWStatusInfo>> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    public void getSubjects() {

        MyApplication.getAppInstance().getAPIInterface().getSubjects(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization()).enqueue(new Callback<List<SubjectInfo>>() {
            @Override
            public void onResponse(Call<List<SubjectInfo>> call, Response<List<SubjectInfo>> response) {
                listSubjectInfo = new ArrayList<>();
                if (response.isSuccessful()) {

                    listSubjectInfo = response.body();
                    spSubject.setAdapter(new CustomSpinnerArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, listSubjectInfo, 3));

                } else {
                    String responseJson = Utils.loadJSONFromAsset(context, "GetSubjects.json");
                    listSubjectInfo = new Gson().fromJson(responseJson, new TypeToken<List<SubjectInfo>>() {
                    }.getType());
                    spSubject.setAdapter(new CustomSpinnerArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, listSubjectInfo, 3));

                }

            }

            @Override
            public void onFailure(Call<List<SubjectInfo>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void assignHomework() {

        MyApplication.getAppInstance().getAPIInterface().assignHomework(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization(), getAssignHomeworkBody()
        ).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {

                } else {
                    setResult(RESULT_OK);
                    finish();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });


    }

    public JsonObject getAssignHomeworkBody() {

        int subjectID = ((SubjectInfo)spSubject.getSelectedItem()).getId();
        int statusID = ((HWStatusInfo)spStatus.getSelectedItem()).getId();

        JsonObject mainJsonObject = new JsonObject();
        mainJsonObject.addProperty("SubjectId", subjectID);
        mainJsonObject.addProperty("Header", etHeader.getText().toString());
        mainJsonObject.addProperty("Description", etDescription.getText().toString());
        mainJsonObject.addProperty("AssignedBy", MyApplication.getAppInstance().getLoginResponseInfo().getUserId());
        mainJsonObject.addProperty("AssignedOn", Utils.convertToDateString(System.currentTimeMillis(),"yyyy-MM-dd"));
        mainJsonObject.addProperty("DueDate", Utils.convertToDateString(calendar.getTimeInMillis(),"yyyy-MM-dd"));
        mainJsonObject.addProperty("SchoolId", MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId());

        JsonArray jsonArrayHWAssignment = new JsonArray();
        JsonObject hwAssignmentDetail = new JsonObject();
        hwAssignmentDetail.addProperty("HomeworkId", 0);
        hwAssignmentDetail.addProperty("StatusId", statusID);
        hwAssignmentDetail.addProperty("AssignedTo", 924);
        hwAssignmentDetail.addProperty("LastUpdatedOn", Utils.convertToDateString(System.currentTimeMillis(),"yyyy-MM-dd"));
        hwAssignmentDetail.addProperty("LastUpdatedBy", MyApplication.getAppInstance().getLoginResponseInfo().getUserId());
        jsonArrayHWAssignment.add(hwAssignmentDetail);

        mainJsonObject.add("HomeworkAssignments", jsonArrayHWAssignment);
        return mainJsonObject;

    }

    public void getAssignHomework(AssignHomeworkRequest assignHomeworkRequest){
        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getAssignHomework(assignHomeworkRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<AssignHomeworkResponse>() {
                    @Override
                    public void onSuccess(AssignHomeworkResponse assignHomeworkResponse) {
                        dismissPD(pd);
                        try {

                            Utils.showAlertDialogFinish(context,"Assign Homework",assignHomeworkResponse.getMessage());

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


    public class CustomDialogClass extends Dialog {

        public Activity activity;
        public Dialog d;

        public CustomDialogClass(Activity activity) {
            super(activity);
            // TODO Auto-generated constructor stub
            this.activity = activity;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.show_student_list_dialog);

            ImageView backBtn=findViewById(R.id.backBtn_StudentListDialog);
            ImageView saveBtn=findViewById(R.id.saveBtn_StudentListDialog);
            RecyclerView recyclerViewList=findViewById(R.id.recyclerStudentList_StudentListDialog);
            recyclerViewList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

            StudentListAdapter studentListAdapter=new StudentListAdapter(AssignHomeWorkActivity.this,studentListResponse.getData());
            recyclerViewList.setAdapter(studentListAdapter);

            saveBtn.setOnClickListener(v -> {
                studentSelectedList=studentListAdapter.getStudentSelectedList();
                dismiss();
            });

            backBtn.setOnClickListener(v -> {
                dismiss();
            });
        }
    }

    public String parseDateToddMMyyyy(String time) {
        String inputPattern = "dd/MM/yyyy";//5/22/2020
        String outputPattern = "yyyy-MM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

}
