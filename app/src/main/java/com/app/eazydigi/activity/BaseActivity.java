package com.app.eazydigi.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.eazydigi.R;
import com.app.eazydigi.util.ProgressHUD;

public class BaseActivity extends AppCompatActivity {

    Context context;
    public static final String EXTRA_TEACHER_HW_ASSIGNMENT_STATUS_INFO = "teacher_hw_assignment_status_info";

    public static final String EXTRA_STUDENT_HW_ASSIGNMENT_STATUS_INFO = "student_hw_assignment_status_info";

    public static final String CLASS_NAME = "class_name";

    public static final String ROLL_NO = "roll_no";

    public static final String ADMISSION_ID = "admission_id";

    public static final String STUDENT_NAME = "student_name";

    public static final String  FILTER_STUDENT_INFO="filter_student_info";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        /*FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, msg);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });*/

    }

    public ProgressHUD showPD(String message) {

        try {
            ProgressHUD pd = ProgressHUD.show(this, (message != null) ? message : getString(R.string.please_wait), true, false);
            return pd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void dismissPD(ProgressHUD pd) {

        try {
            if (pd != null && pd.isShowing()) {
                pd.dismiss();
                pd = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}