package com.app.eazydigi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.StudentHwAssignmentByStatusAdapter;
import com.app.eazydigi.adapter.StudentHwAssignmentStateAdapter;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.listener.OnStudentHwAssignmentStateClickListener;
import com.app.eazydigi.listener.OnUpdateHwAssignmentByStudentClickListener;
import com.app.eazydigi.model.new_model.response.HomeworkDashboardStudentResponse;
import com.app.eazydigi.model.new_model.response.HomeworkStatusResponse;
import com.app.eazydigi.model.old_model.StudentHWAssignmentByStatusInfo;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class StudentHomeworkActivity extends BaseActivity implements OnStudentHwAssignmentStateClickListener, OnUpdateHwAssignmentByStudentClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_hw_ass_state_student)
    RecyclerView rvHwAssStateStudent;

    @BindView(R.id.rv_student_hw_ass_by_status)
    RecyclerView rvStudentHwAsByStatus;


    StudentHwAssignmentStateAdapter studentHWAssignmentStateAdapter;
    StudentHwAssignmentByStatusAdapter studentHWAssignmentByStatusAdapter;

    List<StudentHWAssignmentByStatusInfo> listHwAssignmentByStatusInfo;
    List<HomeworkStatusResponse> listHwAssignmentStateForStudentInfo;

    private HomeworkDashboardStudentResponse homeworkDashboardStudentResponse;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_work);
        ButterKnife.bind(this);

        studentHWAssignmentStateAdapter = new StudentHwAssignmentStateAdapter(context, Utils.getScreenWidth(StudentHomeworkActivity.this));
        studentHWAssignmentStateAdapter.setListener(this);

        studentHWAssignmentByStatusAdapter = new StudentHwAssignmentByStatusAdapter(context);
        studentHWAssignmentByStatusAdapter.setListener(this);

        initToolbar();

        initViews();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //getStudentHwAssignmentState();

        //getStudentHwAssignmentByStatus(0);

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
        rvHwAssStateStudent.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        rvHwAssStateStudent.setAdapter(studentHWAssignmentStateAdapter);

        rvStudentHwAsByStatus.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rvStudentHwAsByStatus.setAdapter(studentHWAssignmentByStatusAdapter);

    }

    /*@OnClick({R.id.tv_assign_homework})
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
*/
    /*
    public void getStudentHwAssignmentState() {

        MyApplication.getAppInstance().getAPIInterface().getStudentHwAssignmentState(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getUserId(), MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId()
        ).enqueue(new Callback<List<StudentHWAssignmentStateInfo>>() {
            @Override
            public void onResponse(Call<List<StudentHWAssignmentStateInfo>> call, Response<List<StudentHWAssignmentStateInfo>> response) {
                listHwAssignmentStateForStudentInfo = new ArrayList<>();
                if (response.isSuccessful()) {

                    listHwAssignmentStateForStudentInfo = response.body();
                    studentHWAssignmentStateAdapter.addAll(listHwAssignmentStateForStudentInfo);
                    rvHwAssStateStudent.setVisibility(View.VISIBLE);

                } else {
                    String responseJson = Utils.loadJSONFromAsset(context, "GetStudentHwAssignmentState.json");
                    listHwAssignmentStateForStudentInfo = new Gson().fromJson(responseJson, new TypeToken<List<StudentHWAssignmentStateInfo>>() {
                    }.getType());
                    studentHWAssignmentStateAdapter.addAll(listHwAssignmentStateForStudentInfo);
                    rvHwAssStateStudent.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onFailure(Call<List<StudentHWAssignmentStateInfo>> call, Throwable t) {
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
    */


    @Override
    public void onStudentHwAssignmentStateClick(HomeworkStatusResponse hwAssignmentStateForStudentInfo) {
        if (!hwAssignmentStateForStudentInfo.isSelected()) {
            if (hwAssignmentStateForStudentInfo.getStatusId()!=0){
                for (int i = 0; i < studentHWAssignmentStateAdapter.getList().size(); i++) {

                    if (studentHWAssignmentStateAdapter.getList().get(i).getStatusId() == hwAssignmentStateForStudentInfo.getStatusId()) {
                        studentHWAssignmentStateAdapter.getList().get(i).setSelected(true);

                    } else {
                        studentHWAssignmentStateAdapter.getList().get(i).setSelected(false);
                    }

                }
                studentHWAssignmentStateAdapter.notifyDataSetChanged();

                try {
                    List<HomeworkDashboardStudentResponse.DataBean.MyHomeworkDetailListBean> list = new ArrayList<>();
                    for (int i = 0; i < homeworkDashboardStudentResponse.getData().getMyHomeworkDetailList().size(); i++) {
                        if (homeworkDashboardStudentResponse.getData().getMyHomeworkDetailList().get(i).getStatus() == hwAssignmentStateForStudentInfo.getStatusId()) {
                            list.add(homeworkDashboardStudentResponse.getData().getMyHomeworkDetailList().get(i));
                        }

                    }
                    studentHWAssignmentByStatusAdapter.addAll(list);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    /*
    public void getStudentHwAssignmentByStatus(int id) {

        MyApplication.getAppInstance().getAPIInterface().getHwAssignmentsToStudentByStatus(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), MyApplication.getAppInstance().getLoginResponseInfo().getUserId(), id
        ).enqueue(new Callback<List<StudentHWAssignmentByStatusInfo>>() {
            @Override
            public void onResponse(Call<List<StudentHWAssignmentByStatusInfo>> call, Response<List<StudentHWAssignmentByStatusInfo>> response) {
                Log.e(TAG, "--->onResponse: " + response.body());
                listHwAssignmentByStatusInfo = new ArrayList<>();
                if (response.isSuccessful()) {
                    listHwAssignmentByStatusInfo = response.body();
                    studentHWAssignmentByStatusAdapter.addAll(listHwAssignmentByStatusInfo);
                    rvStudentHwAsByStatus.setVisibility(View.VISIBLE);


                } else {
                    Log.e(TAG, "--->response not successs");
                    String responseJson = Utils.loadJSONFromAsset(context, "GetHwAssignmentsToStudentByStatus.json");
                    listHwAssignmentByStatusInfo = new Gson().fromJson(responseJson, new TypeToken<List<StudentHWAssignmentByStatusInfo>>() {
                    }.getType());
                    studentHWAssignmentByStatusAdapter.addAll(listHwAssignmentByStatusInfo);
                    rvStudentHwAsByStatus.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onFailure(Call<List<StudentHWAssignmentByStatusInfo>> call, Throwable t) {
                Log.e(TAG, "--->onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
    */

    HomeworkStatusResponse homeworkStatusResponse;
    private void getHomeWork(String id) {

        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getHomeWorkDashboardStudent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<HomeworkDashboardStudentResponse>() {
                    @Override
                    public void onSuccess(HomeworkDashboardStudentResponse homeworkDashboardResponse) {
                        dismissPD(pd);
                        try {
                            homeworkDashboardStudentResponse=homeworkDashboardResponse;
                            studentHWAssignmentByStatusAdapter.addAll(homeworkDashboardResponse.getData().getMyHomeworkDetailList());
                            rvStudentHwAsByStatus.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        try {
                            List<HomeworkStatusResponse> homeworkStatusResponseList=new ArrayList<>();

                            homeworkStatusResponse=new HomeworkStatusResponse();
                            homeworkStatusResponse.setCount(homeworkDashboardStudentResponse.getData().getAllMyHomework());
                            homeworkStatusResponse.setStatus("All");
                            homeworkStatusResponse.setStatusId(0);
                            homeworkStatusResponseList.add(homeworkStatusResponse);

                            homeworkStatusResponse=new HomeworkStatusResponse();
                            homeworkStatusResponse.setCount(homeworkDashboardStudentResponse.getData().getCompletedHomework());
                            homeworkStatusResponse.setStatus("Completed");
                            homeworkStatusResponse.setStatusId(1);
                            homeworkStatusResponseList.add(homeworkStatusResponse);

                            homeworkStatusResponse=new HomeworkStatusResponse();
                            homeworkStatusResponse.setCount(homeworkDashboardStudentResponse.getData().getPending());
                            homeworkStatusResponse.setStatus("Pending");
                            homeworkStatusResponse.setStatusId(2);
                            homeworkStatusResponseList.add(homeworkStatusResponse);

                            homeworkStatusResponse=new HomeworkStatusResponse();
                            homeworkStatusResponse.setCount(homeworkDashboardStudentResponse.getData().getMyOverDueHm());
                            homeworkStatusResponse.setStatus("Overdue");
                            homeworkStatusResponse.setStatusId(4);
                            homeworkStatusResponseList.add(homeworkStatusResponse);

                            homeworkStatusResponse=new HomeworkStatusResponse();
                            homeworkStatusResponse.setCount(homeworkDashboardStudentResponse.getData().getNeedToReworkHomework());
                            homeworkStatusResponse.setStatus("Need Rework");
                            homeworkStatusResponse.setStatusId(7);
                            homeworkStatusResponseList.add(homeworkStatusResponse);

                            listHwAssignmentStateForStudentInfo=homeworkStatusResponseList;
                            studentHWAssignmentStateAdapter.addAll(listHwAssignmentStateForStudentInfo);
                            rvHwAssStateStudent.setVisibility(View.VISIBLE);

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


    public static HomeworkDashboardStudentResponse.DataBean.MyHomeworkDetailListBean myHomeworkDetailListBean;
    @Override
    public void onUpdateStudentHwAssignmentClick2(HomeworkDashboardStudentResponse.DataBean.MyHomeworkDetailListBean homeworkDashboardStudentResponse) {

        myHomeworkDetailListBean=homeworkDashboardStudentResponse;
        Intent intent = new Intent(context, UpdateHomeWorkByStudentActivity.class);
        startActivity(intent);
    }
}
