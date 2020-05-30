package com.app.eazydigi.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.app.eazydigi.R;
import com.app.eazydigi.model.old_model.CollectFeeDetailnfo;
import com.app.eazydigi.model.old_model.FilteredStudentInfo;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectFeeDetailForStudentActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_user_name)
    TextView tvUserName;

    @BindView(R.id.tv_class)
    TextView tvClass;

    @BindView(R.id.tv_section)
    TextView tvSection;

    @BindView(R.id.tv_course)
    TextView tvCourse;

    FilteredStudentInfo filteredStudentInfo;

    List<CollectFeeDetailnfo> listCollectFeeDetail;


    private final String TAG = this.getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_fee_detail_for_student);

        ButterKnife.bind(this);

        initToolbar();

        if (getIntent() != null && getIntent().hasExtra(FILTER_STUDENT_INFO)) {

            filteredStudentInfo = (FilteredStudentInfo) getIntent().getSerializableExtra(FILTER_STUDENT_INFO);

        }

        getCollectFeeDetail();

    }

    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(context.getString(R.string.collect_fee_detail));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });

    }


    public void getCollectFeeDetail() {

        ProgressHUD pd = showPD(null);

        MyApplication.getAppInstance().getAPIInterface().getCollctFeeDetail(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), 4,
                filteredStudentInfo.getUserCode()).enqueue(new Callback<List<CollectFeeDetailnfo>>() {
            @Override
            public void onResponse(Call<List<CollectFeeDetailnfo>> call, Response<List<CollectFeeDetailnfo>> response) {
                Log.e(TAG, "---->onResponse: " + response.body());
                dismissPD(pd);

                if (response.isSuccessful()) {

                    listCollectFeeDetail = response.body();

                    if(listCollectFeeDetail.size()>0 && listCollectFeeDetail!=null){

                        tvUserName.setText("" + listCollectFeeDetail.get(0).getUserName());

                        tvClass.setText("" + listCollectFeeDetail.get(0).getStudentClass());

                        tvCourse.setText("" + listCollectFeeDetail.get(0).getCourse());

                        tvSection.setText("" + listCollectFeeDetail.get(0).getSection());
                    }

                } else {
                }

            }

            @Override
            public void onFailure(Call<List<CollectFeeDetailnfo>> call, Throwable t) {
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                t.printStackTrace();
                dismissPD(pd);
            }
        });


    }


}
