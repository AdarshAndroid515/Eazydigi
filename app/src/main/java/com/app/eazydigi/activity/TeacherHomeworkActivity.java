package com.app.eazydigi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.TeacherHwAssignmentByStatusAdapter;
import com.app.eazydigi.adapter.TeacherHwAssignmentStateAdapter;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.listener.OnTeacherHwAssignmentStateClickListener;
import com.app.eazydigi.listener.OnUpdateHwAssignmentByTeacherClickListener;
import com.app.eazydigi.model.new_model.response.HomeWorkDashboardTeacherResponse;
import com.app.eazydigi.model.new_model.response.HomeworkStatusResponse;
import com.app.eazydigi.model.old_model.TeacherHWAssignmentByStatusInfo;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
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

public class TeacherHomeworkActivity extends BaseActivity implements  OnTeacherHwAssignmentStateClickListener, OnUpdateHwAssignmentByTeacherClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.rv_hw_ass_state_teacher)
    RecyclerView rvHwAssStateTeacher;

    @BindView(R.id.rv_teacher_hw_ass_by_status)
    RecyclerView rvTeacherHwAsByStatus;

    @BindView(R.id.tv_assign_homework)
    TextView tvAssignHomewrok;


    TeacherHwAssignmentStateAdapter teacherHWAssignmentStateAdapter;
    TeacherHwAssignmentByStatusAdapter teacherHWAssignmentByStatusAdapter;

    List<TeacherHWAssignmentByStatusInfo> listHwAssignmentByStatusInfo;
    List<HomeworkStatusResponse> listHwAssignmentStateForTeacherInfo;
    private HomeWorkDashboardTeacherResponse homeWorkDashboardTeacherResponse;

    private static final String TAG = "CreateNoticeCircularAct";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home_work);
        ButterKnife.bind(this);

        teacherHWAssignmentStateAdapter = new TeacherHwAssignmentStateAdapter(context, Utils.getScreenWidth(TeacherHomeworkActivity.this));
        teacherHWAssignmentStateAdapter.setListener(this);

        teacherHWAssignmentByStatusAdapter = new TeacherHwAssignmentByStatusAdapter(context);
        teacherHWAssignmentByStatusAdapter.setListener(this);

        initToolbar();

        initViews();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //getTeacherHwAssignmentState();

        //getTeacherHwAssignmentByStatus(0);
        getHomeWork(" ");
    }

    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getString(R.string.home_work));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    public void initViews() {
        rvHwAssStateTeacher.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        rvHwAssStateTeacher.setAdapter(teacherHWAssignmentStateAdapter);

        rvTeacherHwAsByStatus.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rvTeacherHwAsByStatus.setAdapter(teacherHWAssignmentByStatusAdapter);

    }
    @OnClick({R.id.tv_assign_homework})
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.tv_assign_homework:
                startActivity(new Intent(context,AssignHomeWorkActivity.class));
                break;

            default:
                break;

        }
    }
/*
    public void getTeacherHwAssignmentState() {

        MyApplication.getAppInstance().getAPIInterface().getTeacherHwAssignmentState(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getUserId(), MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId()
        ).enqueue(new Callback<List<TeacherHWAssignmentStateInfo>>() {
            @Override
            public void onResponse(Call<List<TeacherHWAssignmentStateInfo>> call, Response<List<TeacherHWAssignmentStateInfo>> response) {
                listHwAssignmentStateForTeacherInfo=new ArrayList<>();
                if (response.isSuccessful()) {

                    listHwAssignmentStateForTeacherInfo=response.body();
                    teacherHWAssignmentStateAdapter.addAll(listHwAssignmentStateForTeacherInfo);
                    rvHwAssStateTeacher.setVisibility(View.VISIBLE);

                } else {
                    String responseJson = Utils.loadJSONFromAsset(context, "GetTeacherHwAssignmentState.json");
                    listHwAssignmentStateForTeacherInfo = new Gson().fromJson(responseJson, new TypeToken<List<TeacherHWAssignmentStateInfo>>() {
                    }.getType());
                    teacherHWAssignmentStateAdapter.addAll(listHwAssignmentStateForTeacherInfo);
                    rvHwAssStateTeacher.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onFailure(Call<List<TeacherHWAssignmentStateInfo>> call, Throwable t) {
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }*/


//    @Override
//    public void onHwAssignmentStateClick(TeacherHWAssignmentStateInfo hwAssignmentStateForTeacherInfo) {
//
//        if (!hwAssignmentStateForTeacherInfo.isSelected()) {
//            for (int i = 0; i < teacherHWAssignmentStateAdapter.getList().size(); i++) {
//
//                if (teacherHWAssignmentStateAdapter.getList().get(i).getStatusId() == hwAssignmentStateForTeacherInfo.getStatusId()) {
//                    teacherHWAssignmentStateAdapter.getList().get(i).setSelected(true);
//
//                } else {
//                    teacherHWAssignmentStateAdapter.getList().get(i).setSelected(false);
//                }
//
//            }
//            teacherHWAssignmentStateAdapter.notifyDataSetChanged();
//            List<TeacherHWAssignmentByStatusInfo> list=new ArrayList<>();
//            for (int i = 0; i <listHwAssignmentByStatusInfo.size() ; i++) {
//                if(listHwAssignmentByStatusInfo.get(i).getStatusId()==hwAssignmentStateForTeacherInfo.getStatusId()){
//                    list.add(listHwAssignmentByStatusInfo.get(i));
//                }
//
//            }
//            teacherHWAssignmentByStatusAdapter.addAll(list);
//        }
//
//
//    }

    public void getTeacherHwAssignmentByStatus(int id) {

        MyApplication.getAppInstance().getAPIInterface().getTeacherHwAssignmentsByStatus(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), MyApplication.getAppInstance().getLoginResponseInfo().getUserId(), id
        ).enqueue(new Callback<List<TeacherHWAssignmentByStatusInfo>>() {
            @Override
            public void onResponse(Call<List<TeacherHWAssignmentByStatusInfo>> call, Response<List<TeacherHWAssignmentByStatusInfo>> response) {
                Log.e(TAG, "--->onResponse: " + response.body());
                listHwAssignmentByStatusInfo = new ArrayList<>();
                if (response.isSuccessful()) {
                    listHwAssignmentByStatusInfo = response.body();
                   // teacherHWAssignmentByStatusAdapter.addAll(listHwAssignmentByStatusInfo);
                    rvTeacherHwAsByStatus.setVisibility(View.VISIBLE);


                } else {
                    Log.e(TAG, "--->response not successs");
                    String responseJson = Utils.loadJSONFromAsset(context, "GetTeacherHwAssignmentsByStatus.json");
                    listHwAssignmentByStatusInfo = new Gson().fromJson(responseJson, new TypeToken<List<TeacherHWAssignmentByStatusInfo>>() {
                    }.getType());
                    //teacherHWAssignmentByStatusAdapter.addAll(listHwAssignmentByStatusInfo);
                    rvTeacherHwAsByStatus.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onFailure(Call<List<TeacherHWAssignmentByStatusInfo>> call, Throwable t) {
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });


    }

    HomeworkStatusResponse homeworkStatusResponse;
    private void getHomeWork(String id) {

        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getHomeWorkDashboardTeacher(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<HomeWorkDashboardTeacherResponse>() {
                    @Override
                    public void onSuccess(HomeWorkDashboardTeacherResponse homeWorkDashboardResponse) {
                        dismissPD(pd);
                        try {
                            homeWorkDashboardTeacherResponse=homeWorkDashboardResponse;
                            teacherHWAssignmentByStatusAdapter.addAll(homeWorkDashboardTeacherResponse.getData().getStudentList());
                            rvTeacherHwAsByStatus.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        try {
                            List<HomeworkStatusResponse> homeworkStatusResponseList=new ArrayList<>();

                            homeworkStatusResponse=new HomeworkStatusResponse();
                            homeworkStatusResponse.setCount(homeWorkDashboardResponse.getData().getAllStudHomework());
                            homeworkStatusResponse.setStatus("All");
                            homeworkStatusResponse.setStatusId(0);
                            homeworkStatusResponseList.add(homeworkStatusResponse);

                            homeworkStatusResponse=new HomeworkStatusResponse();
                            homeworkStatusResponse.setCount(homeWorkDashboardResponse.getData().getCompleted());
                            homeworkStatusResponse.setStatus("Completed");
                            homeworkStatusResponse.setStatusId(1);
                            homeworkStatusResponseList.add(homeworkStatusResponse);

                            homeworkStatusResponse=new HomeworkStatusResponse();
                            homeworkStatusResponse.setCount(homeWorkDashboardResponse.getData().getPendingHomework());
                            homeworkStatusResponse.setStatus("Pending");
                            homeworkStatusResponse.setStatusId(2);
                            homeworkStatusResponseList.add(homeworkStatusResponse);

                            homeworkStatusResponse=new HomeworkStatusResponse();
                            homeworkStatusResponse.setCount(homeWorkDashboardResponse.getData().getOverDueHomework());
                            homeworkStatusResponse.setStatus("Overdue");
                            homeworkStatusResponse.setStatusId(4);
                            homeworkStatusResponseList.add(homeworkStatusResponse);

                            homeworkStatusResponse=new HomeworkStatusResponse();
                            homeworkStatusResponse.setCount(homeWorkDashboardResponse.getData().getNeedToRework());
                            homeworkStatusResponse.setStatus("Need Rework");
                            homeworkStatusResponse.setStatusId(7);
                            homeworkStatusResponseList.add(homeworkStatusResponse);

                            listHwAssignmentStateForTeacherInfo=homeworkStatusResponseList;
                            teacherHWAssignmentStateAdapter.addAll(listHwAssignmentStateForTeacherInfo);
                            rvHwAssStateTeacher.setVisibility(View.VISIBLE);

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


    @Override
    public void onUpdateHwAssignmentClick(TeacherHWAssignmentByStatusInfo hwAssignmentByStatusInfo) {
        Intent intent=new Intent(context, UpdateHomeWorkByTeacherActivity.class);
        intent.putExtra(EXTRA_TEACHER_HW_ASSIGNMENT_STATUS_INFO,hwAssignmentByStatusInfo);
        startActivity(intent);

    }

    public static HomeWorkDashboardTeacherResponse.DataBean.StudentListBean studentListBean;
    @Override
    public void onUpdateHwAssignmentClick2(HomeWorkDashboardTeacherResponse.DataBean.StudentListBean studentBean) {
        studentListBean=studentBean;
        Intent intent=new Intent(context, UpdateHomeWorkByTeacherActivity.class);
        startActivity(intent);
    }

    @Override
    public void onHwAssignmentStateClick(HomeworkStatusResponse hwAssignmentStateForTeacherInfo) {
        if (!hwAssignmentStateForTeacherInfo.isSelected()) {
            if (hwAssignmentStateForTeacherInfo.getStatusId()!=0){
                for (int i = 0; i < teacherHWAssignmentStateAdapter.getList().size(); i++) {

                    if (teacherHWAssignmentStateAdapter.getList().get(i).getStatusId() == hwAssignmentStateForTeacherInfo.getStatusId()) {
                        teacherHWAssignmentStateAdapter.getList().get(i).setSelected(true);

                    } else {
                        teacherHWAssignmentStateAdapter.getList().get(i).setSelected(false);
                    }

                }
                teacherHWAssignmentStateAdapter.notifyDataSetChanged();
                List<HomeWorkDashboardTeacherResponse.DataBean.StudentListBean> list=new ArrayList<>();
                for (int i = 0; i <homeWorkDashboardTeacherResponse.getData().getStudentList().size() ; i++) {
                    if(homeWorkDashboardTeacherResponse.getData().getStudentList().get(i).getStatus()==hwAssignmentStateForTeacherInfo.getStatusId()){
                        list.add(homeWorkDashboardTeacherResponse.getData().getStudentList().get(i));
                    }

                }
                teacherHWAssignmentByStatusAdapter.addAll(list);
            }

        }
    }
}
