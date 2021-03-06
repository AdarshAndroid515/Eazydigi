package com.app.eazydigi.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.CircularSTAdapter;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.model.new_model.response.StudentDashboardResponse;
import com.app.eazydigi.model.new_model.response.TeacherDashboardResponse;
import com.app.eazydigi.util.Constants;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.PreferenceUtils;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CircularSTFragment extends BaseFragment {

    @BindView(R.id.rv_circulars)
    RecyclerView rvCirculars;

    @BindView(R.id.tv_no_circulars)
    TextView tvNoCirculars;

    CircularSTAdapter circularAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circulars, container, false);
        ButterKnife.bind(this, view);

        circularAdapter = new CircularSTAdapter(getActivity() );

        initViews();

        getCircularBoards();

        return view;

    }

    public void getCircularBoards() {
        if (Utils.isConnectedToInternet(getActivity())) {
            int userRoleId = PreferenceUtils.getUserRoleIdFromDefaults(getActivity());
            if (userRoleId == Constants.UserRoles.ROLE_TEACHER) {
                getDashboardTeacher();
            } else if (userRoleId == Constants.UserRoles.ROLE_STUDENT) {
                getDashboardStudent();
            }
        }
    }

    public void getDashboardTeacher(){
        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(getActivity()).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getDashboardTeacher()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<TeacherDashboardResponse>() {
                    @Override
                    public void onSuccess(TeacherDashboardResponse dashboardResponse) {
                        dismissPD(pd);
                        try {
                            if (dashboardResponse!=null && dashboardResponse.getData()!=null &&
                                    dashboardResponse.getData().getCirculars()!=null && dashboardResponse.getData().getCirculars().size()>0){
                                rvCirculars.setVisibility(View.VISIBLE);
                                circularAdapter.addAll(dashboardResponse.getData().getCirculars());
                            }else {
                                rvCirculars.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "No Circular Found", Toast.LENGTH_SHORT).show();
                            }
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
        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(getActivity()).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getDashboardStudent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<StudentDashboardResponse>() {
                    @Override
                    public void onSuccess(StudentDashboardResponse dashboardResponse) {
                        dismissPD(pd);
                        try {
                            if (dashboardResponse!=null && dashboardResponse.getData()!=null &&
                                    dashboardResponse.getData().getCirculars()!=null && dashboardResponse.getData().getCirculars().size()>0){
                                rvCirculars.setVisibility(View.VISIBLE);
                                circularAdapter.addAll(dashboardResponse.getData().getCirculars());
                            }else {
                                rvCirculars.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "No Circular Found", Toast.LENGTH_SHORT).show();
                            }
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


    private void initViews() {
        rvCirculars.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rvCirculars.setAdapter(circularAdapter);
    }
}
