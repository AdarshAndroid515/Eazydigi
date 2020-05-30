package com.app.eazydigi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eazydigi.R;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends BaseActivity {

    @BindView(R.id.et_school_code)
    EditText etSchoolCode;

    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.tv_error)
    TextView tvError;

    String schoolCode;
    String email;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
    }

    private void checkEmailRegistered() {

        ProgressHUD pd = showPD(null);

        MyApplication.getAppInstance().getAPIInterface().checkEmailRegistered(schoolCode, email).enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                dismissPD(pd);

                try {
                    if (response.isSuccessful()) {

                        JSONObject jsonObject = new JSONObject(response.body().toString());

                        if (jsonObject.has("result") && jsonObject.getBoolean("result")) {

                            restorePassword();

                        } else {

                            tvError.setVisibility(View.VISIBLE);
                            tvError.setText("Email is not registered with our system!");

                        }

                    } else if (response.code() == 401) {

                        tvError.setVisibility(View.VISIBLE);
                        tvError.setText("Username or Password incorrect");

                    } else if (response.code() == 500) {

                        tvError.setVisibility(View.VISIBLE);
                        tvError.setText("Something went wrong...");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.printStackTrace();
                dismissPD(pd);
            }
        });


    }

    private void restorePassword() {

        ProgressHUD pd = showPD(null);

        MyApplication.getAppInstance().getAPIInterface().restorePasswordByEmail(schoolCode, email).enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                dismissPD(pd);

                try {

                    if (response.isSuccessful()) {
                        Toast.makeText(context, "Reset Password instructions has been sent to mail...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    } else if (response.code() == 401) {
                        tvError.setVisibility(View.VISIBLE);
                        tvError.setText("Username or Password incorrect");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.printStackTrace();
                dismissPD(pd);
            }
        });


    }

    private boolean isValid() {

        schoolCode = etSchoolCode.getText().toString().trim();
        email = etEmail.getText().toString().trim();

        boolean isValid = false;

        tvError.setVisibility(View.GONE);

        if (schoolCode.length() == 0) {

            tvError.setText(getString(R.string.error_enter_school_code));

        } else if (email.length() == 0) {

            tvError.setText(getString(R.string.error_enter_username));

        } else {
            isValid = true;
        }

        if (!isValid) {
            tvError.setVisibility(View.VISIBLE);
        }

        return isValid;
    }

    @OnClick({R.id.btn_submit, R.id.tv_login})
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.btn_submit:
                if (isValid()) {
                    if (Utils.isConnectedToInternet(context)) {
                        checkEmailRegistered();
                    } else {
                        Toast.makeText(context, getString(R.string.alert_check_connection), Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.tv_login:
                finish();
                break;
            default:
                break;

        }
    }


}
