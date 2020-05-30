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
import com.app.eazydigi.adapter.NoticesAdapter;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.listener.OnNoticeStatusChangeListener;
import com.app.eazydigi.model.new_model.EventModel;
import com.app.eazydigi.model.new_model.UpdateNoticeResponse;
import com.app.eazydigi.model.new_model.response.CreateNoticeCircularResponse;
import com.app.eazydigi.model.new_model.response.GetAllNoticesResponse;
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

public class NoticesFragment extends BaseFragment implements OnNoticeStatusChangeListener {

    @BindView(R.id.rv_notices)
    RecyclerView rvNotices;

    @BindView(R.id.tv_no_notices)
    TextView tvNoNotices;

    NoticesAdapter noticesAdapter;

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
            case "NEW_NOTICE":
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
                        noticesAdapter.addSingleNotice(dataBean);
                    }
                });

                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notices, container, false);
        ButterKnife.bind(this, view);

        noticesAdapter = new NoticesAdapter(getActivity(), this);

        initViews();

        getNoticeBoards();

        return view;

    }

    public void getNoticeBoards() {
        if (Utils.isConnectedToInternet(getActivity())) {
            getNoticeBoardLists2();
        }
    }

    private void manageBoard(NoticeInfo noticeInfo) {

        ProgressHUD pd = showPD(null);

        MyApplication.getAppInstance().getAPIInterface().manageBoard(MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization(), getBody(noticeInfo)).enqueue(new Callback<NoticeInfo>() {
            @Override
            public void onResponse(Call<NoticeInfo> call, Response<NoticeInfo> response) {

                dismissPD(pd);
                try {

                    if (response.isSuccessful()) {
                        getNoticeBoards();
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

    private JsonObject getBody(NoticeInfo noticeInfo) {

        JsonObject mainJsonObject = new JsonObject();
        mainJsonObject.addProperty("schoolId", MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId());
        mainJsonObject.addProperty("userId", MyApplication.getAppInstance().getLoginResponseInfo().getUserId());
        mainJsonObject.addProperty("id", noticeInfo.getId());
        mainJsonObject.addProperty("boardName", noticeInfo.getBoardName());
        mainJsonObject.addProperty("boardDescription", noticeInfo.getBoardDescription());
        mainJsonObject.addProperty("boardDate", noticeInfo.getBoardDate());
        mainJsonObject.addProperty("boardType", 1);
        mainJsonObject.addProperty("activeStatus", noticeInfo.isActiveStatus());
        return mainJsonObject;

    }

    private JsonObject getBody() {

        /*PAGING OBJECT*/
        JsonObject sortObject = new JsonObject();
        sortObject.addProperty("sortBy", "BoardDate");
        sortObject.addProperty("sortDirection", "desc");
        JsonObject pagingObject = new JsonObject();
        pagingObject.addProperty("pageIndex", 0);
        pagingObject.addProperty("pageSize", 10);
        pagingObject.add("Sort", sortObject);

        /*FILTER OBJECT*/
        JsonObject filterObject = new JsonObject();

        /*FILTER OBJECT*/
        JsonObject mainJsonObject = new JsonObject();
        mainJsonObject.add("filter", filterObject);
        mainJsonObject.add("paging", pagingObject);
        mainJsonObject.addProperty("schoolId", MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId());

        return mainJsonObject;

    }

    /*private void getNoticeBoardLists() {

        ProgressHUD pd = showPD(null);

        MyApplication.getAppInstance().getAPIInterface().getNoticeBoardLists(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                getBody()).enqueue(new Callback<List<NoticeInfo>>() {
            @Override
            public void onResponse(Call<List<NoticeInfo>> call, Response<List<NoticeInfo>> response) {

                dismissPD(pd);

                if (response.isSuccessful()) {

                    List<NoticeInfo> listNotices = response.body();

                    if (listNotices != null && listNotices.size() > 0) {

                        if (PreferenceUtils.getUserRoleIdFromDefaults(getActivity()) == 2) {
                            noticesAdapter.addAll(listNotices);
                            rvNotices.setVisibility(View.VISIBLE);
                        } else {

                            List<NoticeInfo> listActiveNotices = new ArrayList<>();

                            for (int i = 0; i < listNotices.size(); i++) {
                                if (listNotices.get(i).isActiveStatus()) {
                                    listActiveNotices.add(listNotices.get(i));
                                }
                            }
                            noticesAdapter.addAll(listActiveNotices);
                            if (listActiveNotices.size() > 0) {
                                rvNotices.setVisibility(View.VISIBLE);
                            }

                        }
                    } else {
                        rvNotices.setVisibility(View.GONE);
                    }

                } else {
                    rvNotices.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<List<NoticeInfo>> call, Throwable t) {
                t.printStackTrace();
                dismissPD(pd);
            }
        });
    }*/

    private List<GetAllNoticesResponse.DataBean> dataBeanList;
    private void getNoticeBoardLists2() {

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

                                    dataBeanList=new ArrayList<>();

                                    for (int i=0;i<dashboardResponse.getData().size();i++){
                                        if (dashboardResponse.getData().get(i).getCategory()==1)
                                            dataBeanList.add(dashboardResponse.getData().get(i));
                                    }

                                    noticesAdapter.addAll(dataBeanList);
                                    rvNotices.smoothScrollToPosition(noticesAdapter.getItemCount()-1);
                                    rvNotices.setVisibility(View.VISIBLE);
                                }
                            } else {
                                rvNotices.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "No Notice Found", Toast.LENGTH_SHORT).show();
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

        rvNotices.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        rvNotices.setAdapter(noticesAdapter);
    }

    @Override
    public void onStatusChangeListener(int pos, GetAllNoticesResponse.DataBean dataBean) {

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
                            Utils.showAlertDialog(getActivity(),"Notice Update",userResponse.getMessage());
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
