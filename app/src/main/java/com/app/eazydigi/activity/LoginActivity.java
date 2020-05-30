package com.app.eazydigi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eazydigi.BuildConfig;
import com.app.eazydigi.R;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.model.new_model.request.LoginRequest;
import com.app.eazydigi.model.new_model.response.DashboardResponse;
import com.app.eazydigi.model.new_model.response.LoginResponse;
import com.app.eazydigi.model.new_model.response.StudentDashboardResponse;
import com.app.eazydigi.model.new_model.response.TeacherDashboardResponse;
import com.app.eazydigi.model.old_model.LoginResponseInfo;
import com.app.eazydigi.util.Constants;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.PreferenceUtils;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_school_code)
    EditText etSchoolCode;

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.tv_error)
    TextView tvError;

    String schoolCode;
    String username;
    String password;
    public static DashboardResponse dashboardResponsePublic;
    public static TeacherDashboardResponse teacherDashboardResponsePublic;
    public static StudentDashboardResponse studentDashboardResponsePublic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (BuildConfig.DEBUG) {
            etSchoolCode.setText("4");
            etUsername.setText("");
            etPassword.setText("");
//            etUsername.setText("OMRA0106");
//            etPassword.setText("OM@RAO");
//            etUsername.setText("ST1383");
//            etPassword.setText("CHANDAN@KUMAR");
        }
    }

    /*
    private void login() {

        ProgressHUD pd = showPD(getString(R.string.signing_in));
        MyApplication.getAppInstance().getAPIInterface().authenticate(username, password).enqueue(new Callback<LoginResponseInfo>() {

            @Override
            public void onResponse(Call<LoginResponseInfo> call, Response<LoginResponseInfo> response) {

                dismissPD(pd);

                if (response.isSuccessful()) {
                    LoginResponseInfo loginResponseInfo = response.body();
                    if (loginResponseInfo != null) {
                        if (loginResponseInfo.isActiveStatus()) {
                            PreferenceUtils.saveeUserInfoToDefaults(context, loginResponseInfo);
                            PreferenceUtils.saveUerRoleIdToDefaults(context, loginResponseInfo.getUserRoleId());
                            Intent intent = new Intent(context, DashboardActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        }
                    }
                } else if (response.code() == 401) {
                    tvError.setVisibility(View.VISIBLE);
                    tvError.setText("Username or Password incorrect");
                }

            }

            @Override
            public void onFailure(Call<LoginResponseInfo> call, Throwable t) {
                t.printStackTrace();
                dismissPD(pd);
            }
        });
    } */

    public void login2(){
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        ProgressHUD pd = showPD(getString(R.string.signing_in));

            APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
            MyApplication.getAppInstance().getDisposable().add(apiService.authenticate(loginRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<LoginResponse>() {
                        @Override
                        public void onSuccess(LoginResponse userResponse) {
                            dismissPD(pd);
                            try {
                                if (userResponse.getToken()!=null && !TextUtils.isEmpty(userResponse.getToken())){

                                    JWTUtils jwtUtils=new JWTUtils();
                                    try {
                                        MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().ACCESS_TOKEN,userResponse.getToken());
                                        String returnString=jwtUtils.decoded(userResponse.getToken());
                                        LoginResponseInfo loginResponseInfo=new LoginResponseInfo();
                                        try {
                                            JSONObject jsonObject = new JSONObject(returnString);
                                            JSONArray jsonArray=jsonObject.getJSONObject("loginUser").getJSONArray("roles");
                                            JSONObject jsonObjectUser=jsonObject.getJSONObject("loginUser");


                                            loginResponseInfo.setSchoolFullName(jsonObject.getString("schoolName"));
                                            loginResponseInfo.setClassName("");
                                            loginResponseInfo.setProfileImage("");
                                            loginResponseInfo.setSectionId(0);
                                            loginResponseInfo.setUserId(jsonObjectUser.getInt("userId"));
                                            loginResponseInfo.setSectionName("");
                                            loginResponseInfo.setClassId(0);
                                            loginResponseInfo.setActiveStatus(true);
                                            loginResponseInfo.setSchoolId(jsonObjectUser.getInt("schoolId"));
                                            loginResponseInfo.setUserRoleId(jsonArray.getJSONObject(0).getInt("roleId"));
                                            loginResponseInfo.setApproved(true);
                                            loginResponseInfo.setSchoolName(jsonObject.getString("schoolName"));
                                            loginResponseInfo.setSchoolLogo(jsonObject.getString("schoolLogo"));
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }
                                        PreferenceUtils.saveeUserInfoToDefaults(context, loginResponseInfo);
                                        PreferenceUtils.saveUerRoleIdToDefaults(context, loginResponseInfo.getUserRoleId());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    int userRoleId = PreferenceUtils.getUserRoleIdFromDefaults(LoginActivity.this);
                                    if (Constants.UserRoles.ROLE_ADMIN==userRoleId){
                                        getDashboardInfo();
                                    }else if (Constants.UserRoles.ROLE_STUDENT==userRoleId){//6
                                        getDashboardStudent();
                                    }else if (Constants.UserRoles.ROLE_TEACHER==userRoleId){//4
                                        getDashboardTeacher();
                                    }else {
                                        Toast.makeText(context, "Something Went Wrong! Please Try Again", Toast.LENGTH_SHORT).show();
                                    }

                                }else {
                                    Toast.makeText(context, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    tvError.setVisibility(View.VISIBLE);
                                    tvError.setText("Username or Password incorrect");
                                    Utils.showAlertDialog(LoginActivity.this,"Login","Username or Password incorrect");
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Utils.showAlertDialog(LoginActivity.this,"Login","Username or Password incorrect");
                            tvError.setVisibility(View.VISIBLE);
                            tvError.setText("Username or Password incorrect");
                            dismissPD(pd);
                            Log.v("error",e.getMessage());
                        }
                    }));
    }

    public void getDashboardInfo(){
        ProgressHUD pd = showPD(getString(R.string.signing_in));

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getDashboard()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DashboardResponse>() {
                    @Override
                    public void onSuccess(DashboardResponse dashboardResponse) {
                        dismissPD(pd);
                        try {
                            dashboardResponsePublic=dashboardResponse;
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TODAY_COLLECTION, String.valueOf(dashboardResponse.getData().getTodaysCollection()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_EXPENSE, String.valueOf(dashboardResponse.getData().getTotalExpense()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_BUS, String.valueOf(dashboardResponse.getData().getTotalBus()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_VAN, String.valueOf(dashboardResponse.getData().getTotalVan()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_OTHER_VEHICLE, String.valueOf(dashboardResponse.getData().getOthersVehicle()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_STUDENTS, String.valueOf(dashboardResponse.getData().getTotalStudent()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_TEACHERS, String.valueOf(dashboardResponse.getData().getTotalTeachers()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_STAFF, String.valueOf(dashboardResponse.getData().getTotalStaffs()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_PARENTS, "0");


                            Intent intent = new Intent(context, DashboardActivity.class);
                            startActivity(intent);
                            finishAffinity();
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

    public void getDashboardTeacher(){
        ProgressHUD pd = showPD(getString(R.string.signing_in));

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getDashboardTeacher()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<TeacherDashboardResponse>() {
                    @Override
                    public void onSuccess(TeacherDashboardResponse dashboardResponse) {
                        dismissPD(pd);
                        try {
                            teacherDashboardResponsePublic=dashboardResponse;
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TEACHER_TOTAL_ABSENT, String.valueOf(dashboardResponse.getData().getAbsentStud()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TEACHER_TOTAL_PRESENT, String.valueOf(dashboardResponse.getData().getPresentStud()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TEACHER_TOTAL_STUDENT, String.valueOf(dashboardResponse.getData().getTotalStud()));

                            Intent intent = new Intent(context, DashboardActivity.class);
                            startActivity(intent);
                            finishAffinity();
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

    public void getDashboardStudent(){
        ProgressHUD pd = showPD(getString(R.string.signing_in));

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getDashboardStudent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<StudentDashboardResponse>() {
                    @Override
                    public void onSuccess(StudentDashboardResponse dashboardResponse) {
                        dismissPD(pd);
                        try {
                            studentDashboardResponsePublic=dashboardResponse;

                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().STUDENT_FEES_PENDING, String.valueOf(dashboardResponse.getData().getTotalFeesPending()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().STUDENT_FEES_PAID, String.valueOf(dashboardResponse.getData().getTotalFeesPaid()));
                            MyApplication.getAppInstance().sharedPref().setInt(MyApplication.getAppInstance().sharedPref().STUDENT_MY_ABSENT, dashboardResponse.getData().getMyAbsent());
                            MyApplication.getAppInstance().sharedPref().setInt(MyApplication.getAppInstance().sharedPref().STUDENT_MY_PRESENT, dashboardResponse.getData().getMyPresent());
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().STUDENT_ATTENDANCE_PER, String.valueOf(dashboardResponse.getData().getMyPercentage()));

                            Intent intent = new Intent(context, DashboardActivity.class);
                            startActivity(intent);
                            finishAffinity();
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

    private boolean isValid() {

        schoolCode = etSchoolCode.getText().toString().trim();
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        boolean isValid = false;

        tvError.setVisibility(View.GONE);

//        if (schoolCode.length() == 0) {
//
//            tvError.setText(getString(R.string.error_enter_school_code));
//
//        } else
            if (username.length() == 0) {

            tvError.setText(getString(R.string.error_enter_username));

        } else if (password.length() == 0) {

            tvError.setText(getString(R.string.error_enter_password));

        } else {
            isValid = true;
        }

        if (!isValid) {
            tvError.setVisibility(View.VISIBLE);
        }

        return isValid;
    }

    @OnClick({R.id.btn_login, R.id.ll_register, R.id.tv_forgot_password})
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.btn_login:
                if (isValid()) {

                    if (Utils.isConnectedToInternet(context)) {
                        login2();
                    } else {
                        Toast.makeText(context, getString(R.string.alert_check_connection), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.ll_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_forgot_password:
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;
            default:
                break;

        }
    }

    public class JWTUtils {

        public String decoded(String JWTEncoded) throws Exception {
            String returnString = "";
            try {
                String[] split = JWTEncoded.split("\\.");
                Log.v("JWT_DECODED", "Header: " + getJson(split[0]));
                Log.v("JWT_DECODED", "Body: " + getJson(split[1]));

                returnString=getJson(split[1]);
            } catch (UnsupportedEncodingException e) {
                //Error
            }
            return returnString;
        }

        private  String getJson(String strEncoded) throws UnsupportedEncodingException{
            byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
            return new String(decodedBytes, "UTF-8");
        }
    }

}