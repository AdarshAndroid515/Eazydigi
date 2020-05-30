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
import com.app.eazydigi.adapter.CircularAdapter;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.listener.OnCircularStatusChangeListener;
import com.app.eazydigi.model.new_model.EventModel;
import com.app.eazydigi.model.new_model.UpdateNoticeResponse;
import com.app.eazydigi.model.new_model.response.CreateNoticeCircularResponse;
import com.app.eazydigi.model.new_model.response.GetAllNoticesResponse;
import com.app.eazydigi.model.old_model.CircularInfo;
import com.app.eazydigi.model.old_model.NoticeInfo;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.PreferenceUtils;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CircularFragment extends BaseFragment implements OnCircularStatusChangeListener {

    @BindView(R.id.rv_circulars)
    RecyclerView rvCirculars;

    @BindView(R.id.tv_no_circulars)
    TextView tvNoCirculars;

    CircularAdapter circularAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void  onMessageEvent(final EventModel eventModel) {
        if (eventModel == null)
            return;
        switch (eventModel.notificationName) {
            case "NEW_CIRCULAR":
                CreateNoticeCircularResponse userResponse=(CreateNoticeCircularResponse)eventModel.responseObject;
                GetAllNoticesResponse.DataBean dataBean=new GetAllNoticesResponse.DataBean();
                dataBean.setCategory(userResponse.getData().getCategory());
                dataBean.setNoticeId(userResponse.getData().getNoticeId());
                dataBean.setSchoolId(userResponse.getData().getSchoolId());
                dataBean.setTitle(userResponse.getData().getTitle());
                dataBean.setDescription(userResponse.getData().getDescription());
                dataBean.setStartDate(userResponse.getData().getStartDate());
                dataBean.setIsActive(userResponse.getData().getIsActive());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        circularAdapter.addSingleNotice(dataBean);
                    }
                });

                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circulars, container, false);
        ButterKnife.bind(this, view);

        circularAdapter = new CircularAdapter(getActivity(), this);

        initViews();

        getCircularBoards();

        return view;

    }

    public void getCircularBoards() {
        if (Utils.isConnectedToInternet(getActivity())) {
            //getCircularBoard();
            getCircularBoard2();
        }
    }

    private void manageBoard(CircularInfo circularInfo) {

        ProgressHUD pd = showPD(null);

        MyApplication.getAppInstance().getAPIInterface().manageBoard(MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization(), getBody(circularInfo)).enqueue(new Callback<NoticeInfo>() {
            @Override
            public void onResponse(Call<NoticeInfo> call, Response<NoticeInfo> response) {

                dismissPD(pd);
                try {

                    if (response.isSuccessful()) {
                        getCircularBoards();
                    } else {
                        String error = response.errorBody().string();
                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<NoticeInfo> call, Throwable t) {
                t.printStackTrace();
                dismissPD(pd);
            }
        });
    }


    private JsonObject getBody(CircularInfo circularInfo) {

        JsonObject mainJsonObject = new JsonObject();
        mainJsonObject.addProperty("schoolId", MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId());
        mainJsonObject.addProperty("userId", MyApplication.getAppInstance().getLoginResponseInfo().getUserId());
        mainJsonObject.addProperty("id", circularInfo.getId());
        mainJsonObject.addProperty("boardName", circularInfo.getBoardName());
        mainJsonObject.addProperty("boardDescription", circularInfo.getBoardDescription());
        mainJsonObject.addProperty("boardDate", circularInfo.getBoardDate());
        mainJsonObject.addProperty("boardType", 2);
        mainJsonObject.addProperty("activeStatus", circularInfo.isActiveStatus());
        return mainJsonObject;

    }

    /*private void getCircularBoard() {

        ProgressHUD pd = showPD(null);

        MyApplication.getAppInstance().getAPIInterface().getCircularBoard(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId()).enqueue(new Callback<List<CircularInfo>>() {
            @Override
            public void onResponse(Call<List<CircularInfo>> call, Response<List<CircularInfo>> response) {

                dismissPD(pd);

                if (response.isSuccessful()) {

                    List<CircularInfo> listCirculars = response.body();

                    if (listCirculars != null && listCirculars.size() > 0) {
                        if (PreferenceUtils.getUserRoleIdFromDefaults(getActivity()) == 2) {
                            circularAdapter.addAll(listCirculars);
                            rvCirculars.setVisibility(View.VISIBLE);
                        } else {

                            List<CircularInfo> listActiveCirculars = new ArrayList<>();

                            for (int i = 0; i < listCirculars.size(); i++) {
                                if (listCirculars.get(i).isActiveStatus()) {
                                    listActiveCirculars.add(listCirculars.get(i));
                                }
                            }
                            circularAdapter.addAll(listActiveCirculars);
                            if (listActiveCirculars.size() > 0) {
                                rvCirculars.setVisibility(View.VISIBLE);
                            }else{
                                rvCirculars.setVisibility(View.GONE);
                            }
                        }
                    } else {
                        rvCirculars.setVisibility(View.GONE);
                    }


                } else {
                    rvCirculars.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<List<CircularInfo>> call, Throwable t) {
                t.printStackTrace();
                dismissPD(pd);
            }
        });
    } */

    private void getCircularBoard2() {

        ProgressHUD pd = showPD(getString(R.string.signing_in));

        APIInterface apiService = ApiClient.getClient(getActivity()).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getNoticeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<GetAllNoticesResponse>() {
                    @Override
                    public void onSuccess(GetAllNoticesResponse dashboardResponse) {
                        dismissPD(pd);
                        try {

                            //List<NoticeInfo> listNotices = dashboardResponse.getData();

                            if (dashboardResponse.getData() != null && dashboardResponse.getData().size() > 0) {

                                if (PreferenceUtils.getUserRoleIdFromDefaults(getActivity()) == 2) {
                                    List<GetAllNoticesResponse.DataBean> dataBeanList=new ArrayList<>();

                                    for (int i=0;i<dashboardResponse.getData().size();i++){
                                        if (dashboardResponse.getData().get(i).getCategory()==2)
                                            dataBeanList.add(dashboardResponse.getData().get(i));
                                    }
                                    circularAdapter.addAll(dataBeanList);
                                    rvCirculars.smoothScrollToPosition(circularAdapter.getItemCount()-1);
                                    rvCirculars.setVisibility(View.VISIBLE);
                                }
                            } else {
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

    @Override
    public void onStatusChangeListener(int position, GetAllNoticesResponse.DataBean dataBean) {

        if (dataBean.getIsActive()==1){
            dataBean.setIsActive(0);
        }else {
            dataBean.setIsActive(1);
        }

        updateNotice(dataBean);
    }
    public void updateNotice(GetAllNoticesResponse.DataBean dataBean){
        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(getActivity()).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.updateNotice(dataBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UpdateNoticeResponse>() {
                    @Override
                    public void onSuccess(UpdateNoticeResponse userResponse) {
                        dismissPD(pd);
                        try {
                            try {
                                Utils.showAlertDialog(getActivity(),"Notice Update",userResponse.getMessage());
                            } catch (Exception e) {
                                e.printStackTrace();
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

}
