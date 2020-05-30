package com.app.eazydigi.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eazydigi.R;
import com.app.eazydigi.util.Constants;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;
import com.google.gson.JsonObject;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_school_code)
    EditText etSchoolCode;

    @BindView(R.id.et_fname)
    EditText etFname;

    @BindView(R.id.et_mname)
    EditText etMname;

    @BindView(R.id.et_lname)
    EditText etLname;

    @BindView(R.id.et_father_name)
    EditText etFatherName;

    @BindView(R.id.et_mother_name)
    EditText etMotherName;

    @BindView(R.id.et_contact)
    EditText etContact;

    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.spinner_class)
    Spinner spinnerClass;

    @BindView(R.id.et_pre_school)
    EditText etPreSchool;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;

    @BindView(R.id.tv_dob)
    TextView tvDob;

    @BindView(R.id.rg_gender)
    RadioGroup rgGender;

    @BindView(R.id.rb_male)
    RadioButton rbMale;

    @BindView(R.id.rb_female)
    RadioButton rbFemale;

    @BindView(R.id.rb_other)
    RadioButton rbOther;

    @BindView(R.id.rg_profession)
    RadioGroup rgProfession;

    @BindView(R.id.rb_applicant)
    RadioButton rbApplicant;

    @BindView(R.id.rb_staff)
    RadioButton rbStaff;

    @BindView(R.id.tv_error)
    TextView tvError;

    DatePickerDialog datePickerDialog;

    Calendar calendar;

    String schoolCode;
    String fName;
    String mName;
    String lName;
    String fullName;
    String fatherName;
    String motherName;
    String contact;
    String email;
    String preSchool;
    String gender;
    String dob;
    String password;
    String confirmPassword;
    String roleName;
    String spClass;
    private static final String TAG = "RegisterActivity";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        calendar = Calendar.getInstance();

        initViews();

        initDatePicker();

    }

    private void setDate() {
        tvDob.setText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void initViews() {

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Constants.listClassTypes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnerClass.setAdapter(aa);

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

    private void register() {

        ProgressHUD pd = showPD(null);

        MyApplication.getAppInstance().getAPIInterface().register(schoolCode,
                getBody()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                dismissPD(pd);

                try {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, "Registration Successful...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finishAffinity();
                    } else {
                        String error = response.errorBody().string();
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();

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
    }

    private JsonObject getBody() {

        /*STUDENTDETAIL OBJECT*/
        JsonObject studentObject = new JsonObject();
        studentObject.addProperty("rollNumber", 0);
        studentObject.addProperty("schoolID", schoolCode);
        studentObject.addProperty("className", "Nursery");
        studentObject.addProperty("studentName", fullName);
        studentObject.addProperty("fatherName", fatherName);
        studentObject.addProperty("motherName", motherName);

        /*STAFF OBJECT*/
        JsonObject staffObject = new JsonObject();

        /*FILTER OBJECT*/
        JsonObject mainJsonObject = new JsonObject();
        mainJsonObject.add("studentDetail", studentObject);
        mainJsonObject.add("staff", staffObject);
        mainJsonObject.addProperty("userRoleId", 7);
        mainJsonObject.addProperty("schoolID", schoolCode);
        mainJsonObject.addProperty("firstName", fName);
        mainJsonObject.addProperty("lastName", lName);
        mainJsonObject.addProperty("fatherFullName", fatherName);
        mainJsonObject.addProperty("fatherName", fatherName);
        mainJsonObject.addProperty("motherFullName", motherName);
        mainJsonObject.addProperty("motherName", motherName);
        mainJsonObject.addProperty("mobile", contact);
        mainJsonObject.addProperty("email", email);
        mainJsonObject.addProperty("class", spClass);
        mainJsonObject.addProperty("previousSchool", preSchool);
        mainJsonObject.addProperty("dob", dob);
        mainJsonObject.addProperty("sex", gender);
        mainJsonObject.addProperty("roleName", roleName);
        if (rbApplicant.isChecked()) {
            mainJsonObject.addProperty("roleId", 7);
            mainJsonObject.addProperty("userCode", 4);
        } else {
            mainJsonObject.addProperty("userCode", 1);
        }
        mainJsonObject.addProperty("password", password);
        mainJsonObject.addProperty("confirmPassword", confirmPassword);

        return mainJsonObject;

    }

    private boolean isValid() {

        schoolCode = etSchoolCode.getText().toString().trim();
        fName = etFname.getText().toString().trim();
        mName = etMname.getText().toString().trim();
        lName = etLname.getText().toString().trim();
        fullName = fName + mName + lName;
        fatherName = etFatherName.getText().toString().trim();
        motherName = etMotherName.getText().toString().trim();
        contact = etContact.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        preSchool = etPreSchool.getText().toString().trim();
        dob = tvDob.getText().toString().trim();

        if (rbMale.isChecked()) {
            gender = "1";
        } else if (rbFemale.isChecked()) {
            gender = "2";
        } else if (rbOther.isChecked()) {
            gender = "3";
        }

        int selectedProfessionID = rgProfession.getCheckedRadioButtonId();
        if (selectedProfessionID != -1) {
            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedProfessionID);
            roleName = selectedRadioButton.getText().toString();
        }

        spClass = spinnerClass.getSelectedItem().toString();
        password = etPassword.getText().toString().trim();
        confirmPassword = etConfirmPassword.getText().toString().trim();

        boolean isValid = false;

        tvError.setVisibility(View.GONE);

        if (schoolCode.length() == 0) {
            tvError.setText(getString(R.string.error_enter_school_code));
        } else if (fName.length() == 0) {
            tvError.setText(getString(R.string.error_enter_first_name));
        } else if (mName.length() == 0) {
            tvError.setText(getString(R.string.error_enter_middle_name));
        } else if (lName.length() == 0) {
            tvError.setText(getString(R.string.error_enter_last_name));
        } else if (fatherName.length() == 0) {
            tvError.setText(getString(R.string.error_enter_father_name));
        } else if (motherName.length() == 0) {
            tvError.setText(getString(R.string.error_enter_mother_name));
        } else if (contact.length() == 0) {
            tvError.setText(getString(R.string.error_enter_contact_number));
        } else if (contact.length() < 10) {
            tvError.setText(getString(R.string.error_enter_valid_contact_number));
        } else if (preSchool.length() == 0) {
            tvError.setText(getString(R.string.error_enter_preschool_name));
        } else if (!Utils.isValidEmail(email)) {
            tvError.setText(getString(R.string.error_enter_valid_email));
        } else {
            isValid = true;
        }

        if (!isValid) {
            tvError.setVisibility(View.VISIBLE);
        }

        return isValid;
    }

    @OnClick({R.id.tv_dob, R.id.btn_register, R.id.ll_login})
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.tv_dob:
                datePickerDialog.show();
                break;
            case R.id.btn_register:
                if (isValid()) {

                    if (Utils.isConnectedToInternet(context)) {
                        register();
                    } else {
                        Toast.makeText(context, getString(R.string.alert_check_connection), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.ll_login:
                finish();
                break;
            default:
                break;

        }
    }

}