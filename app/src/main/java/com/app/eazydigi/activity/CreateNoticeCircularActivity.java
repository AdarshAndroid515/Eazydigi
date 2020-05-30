package com.app.eazydigi.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.model.new_model.EventModel;
import com.app.eazydigi.model.new_model.request.CreateNoticeCircularRequest;
import com.app.eazydigi.model.new_model.response.CreateNoticeCircularResponse;
import com.app.eazydigi.model.old_model.NoticeInfo;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

public class CreateNoticeCircularActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinner_category)
    Spinner spinnerCategory;

    @BindView(R.id.et_title)
    EditText etTitle;

    @BindView(R.id.et_description)
    EditText etDescription;

    @BindView(R.id.tv_start_date)
    TextView tvStartDate;

    @BindView(R.id.tv_error)
    TextView tvError;

    @BindView(R.id.btn_create)
    Button btnCreate;

    DatePickerDialog datePickerDialog;

    Calendar calendar;

    String title;
    String description;
    String startDate;
    String boardType;
    private static final String TAG = "CreateNoticeCircularAct";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notice_circular);
        ButterKnife.bind(this);

        calendar = Calendar.getInstance();

        initToolbar();

        initViews();

        initDatePicker();

    }

    private void createBoard() {

        ProgressHUD pd = showPD(null);

        MyApplication.getAppInstance().getAPIInterface().manageBoard(MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),Utils.getAuthorization(),getBody()).enqueue(new Callback<NoticeInfo>() {
            @Override
            public void onResponse(Call<NoticeInfo> call, Response<NoticeInfo> response) {

                dismissPD(pd);
                try {

                    if (response.isSuccessful()) {

                        Toast.makeText(context, "Board Created Successful...", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();

                    } else {
                        String error = response.errorBody().string();
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    Log.e(TAG, "--->onResponse:"+e.getMessage() );
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<NoticeInfo> call, Throwable t) {
                Log.e(TAG, "--->onFailure: "+t.getMessage() );
                t.printStackTrace();
                dismissPD(pd);
            }
        });
    }

    private JsonObject getBody() {

        JsonObject mainJsonObject = new JsonObject();
        mainJsonObject.addProperty("schoolId", MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId());
        mainJsonObject.addProperty("userId",  MyApplication.getAppInstance().getLoginResponseInfo().getUserId());
        mainJsonObject.addProperty("boardName", title);
        mainJsonObject.addProperty("boardDescription", description);
        mainJsonObject.addProperty("boardImage", "");
        mainJsonObject.addProperty("boardDate", startDate);
        mainJsonObject.addProperty("boardType", boardType);
        return mainJsonObject;

    }

    private void setDate() {
        int month=calendar.get(Calendar.MONTH) + 1;
        if (String.valueOf(month).length()==1)
            tvStartDate.setText(calendar.get(Calendar.YEAR)+"-0"+(calendar.get(Calendar.MONTH) + 1)+"-"+calendar.get(Calendar.DAY_OF_MONTH) );
        else
            tvStartDate.setText(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH) + 1)+"-"+calendar.get(Calendar.DAY_OF_MONTH) );
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

        title = etTitle.getText().toString().trim();
        description = etDescription.getText().toString().trim();
        startDate = tvStartDate.getText().toString().trim();
        boardType=spinnerCategory.getSelectedItem().toString();
        if(boardType.equalsIgnoreCase("Notice")){
            boardType="1";
        }else{
            boardType="2";
        }


        if (title.length() == 0) {
            tvError.setText(getString(R.string.error_enter_title));
        } else if (description.length() == 0) {
            tvError.setText(getString(R.string.error_enter_description));
        } else if (startDate.length() == 0) {
            tvStartDate.setText(getString(R.string.error_enter_start_date));
        } else {
            isValid = true;
        }

        if (!isValid) {
            tvError.setVisibility(View.VISIBLE);
        }

        return isValid;
    }

    @OnClick({R.id.tv_start_date, R.id.btn_create})
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.tv_start_date:
                datePickerDialog.show();
                break;
            case R.id.btn_create:
                if (isValid()) {
                    if (Utils.isConnectedToInternet(CreateNoticeCircularActivity.this)) {

                        //createBoard();
                        CreateNoticeCircularRequest createNoticeCircularRequest=new CreateNoticeCircularRequest();
                        createNoticeCircularRequest.setCategory(Integer.parseInt(boardType));
                        createNoticeCircularRequest.setTitle(title);
                        createNoticeCircularRequest.setDescription(description);
                        createNoticeCircularRequest.setStartDate(tvStartDate.getText().toString());
                        createNotification(createNoticeCircularRequest);

                    } else {
                        Toast.makeText(context, getString(R.string.alert_check_connection), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                break;

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

    private void initViews() {

        String[] listTypes = {getString(R.string.notice), getString(R.string.circular)};

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listTypes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnerCategory.setAdapter(aa);

    }


    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getString(R.string.create_notice_circular));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public void createNotification(CreateNoticeCircularRequest createNoticeCircularRequest){
        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.createNotice(createNoticeCircularRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CreateNoticeCircularResponse>() {
                    @Override
                    public void onSuccess(CreateNoticeCircularResponse userResponse) {
                        dismissPD(pd);
                        try {
                            Utils.showAlertDialogFinish(CreateNoticeCircularActivity.this,"Add Notice/Circular",userResponse.getMessage());
                            if (createNoticeCircularRequest.getCategory()==1)
                                EventBus.getDefault().post(new EventModel(null,userResponse,"NEW_NOTICE",null));
                            else
                                EventBus.getDefault().post(new EventModel(null,userResponse,"NEW_CIRCULAR",null));

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
