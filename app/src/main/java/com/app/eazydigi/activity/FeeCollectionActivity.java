package com.app.eazydigi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.CustomSpinnerArrayAdapter;
import com.app.eazydigi.model.old_model.StudentClassInfo;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeeCollectionActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.spinner_class)
    Spinner spClass;

    @BindView(R.id.et_roll_no)
    EditText etRollNo;

    @BindView(R.id.et_addmission_id)
    EditText etAdmissionId;

    @BindView(R.id.et_student_name)
    EditText etStudentName;

    @BindView(R.id.btn_search)
    Button btnSearch;


    String rollNo="0";
    String admissionId="0";
    String studentName="NA";
    String className;

    private static final String TAG = "FeeCollectionActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fee_collection);
        ButterKnife.bind(this);

        initToolbar();

        getClasses();

    }

    @OnClick({R.id.btn_search})
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.btn_search:

                className = ((StudentClassInfo) spClass.getSelectedItem()).getValue();
                if (etRollNo.getText().length() >0) {
                    rollNo = etRollNo.getText().toString().trim();
                }
                if (etAdmissionId.getText().length() >0) {
                    admissionId = etAdmissionId.getText().toString().trim();
                }
                if (etStudentName.getText().length()>0) {
                    studentName = etStudentName.getText().toString().trim();
                }

                Intent intent = new Intent(context, FeeCollectionDetailActivity.class);
                intent.putExtra(CLASS_NAME, className);
                intent.putExtra(ROLL_NO, rollNo);
                intent.putExtra(ADMISSION_ID, admissionId);
                intent.putExtra(STUDENT_NAME, studentName);
                startActivity(intent);

            default:
                break;

        }

    }


    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(context.getString(R.string.fee_collection));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
                if (listStudentClassInfo.size() > 0 && listStudentClassInfo != null) {
                    spClass.setAdapter(new CustomSpinnerArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, listStudentClassInfo, 1));
                }

            }

            @Override
            public void onFailure(Call<List<StudentClassInfo>> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                dismissPD(pd);
            }
        });
    }


}