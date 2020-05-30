package com.app.eazydigi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.eazydigi.R;
import com.app.eazydigi.activity.AcademicReportActivity;
import com.app.eazydigi.activity.FeeCollectionActivity;
import com.app.eazydigi.activity.LeaveListAdmin;
import com.app.eazydigi.activity.LeaveRequestStudent;
import com.app.eazydigi.activity.LoginActivity;
import com.app.eazydigi.activity.MarkAttendanceActivity;
import com.app.eazydigi.activity.NoticesCircularSTActivity;
import com.app.eazydigi.activity.NoticesCircularsActivity;
import com.app.eazydigi.activity.StudentHomeworkActivity;
import com.app.eazydigi.activity.TeacherHomeworkActivity;
import com.app.eazydigi.adapter.CarouselAdapter;
import com.app.eazydigi.adapter.ModulesAdapter;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.listener.OnModuleClickListener;
import com.app.eazydigi.model.new_model.EventModel;
import com.app.eazydigi.model.new_model.response.DashboardResponse;
import com.app.eazydigi.model.new_model.response.StudentDashboardResponse;
import com.app.eazydigi.model.new_model.response.TeacherDashboardResponse;
import com.app.eazydigi.model.new_model.response.TeacherDetailResponse;
import com.app.eazydigi.model.old_model.AttendanceDetailForStudentInfo;
import com.app.eazydigi.model.old_model.StudentFeeDetail;
import com.app.eazydigi.model.old_model.StudentPendingFeeDetails;
import com.app.eazydigi.model.old_model.TotalPaidFeeForStudentInfo;
import com.app.eazydigi.model.old_model.TotalStudentsByClassInfo;
import com.app.eazydigi.model.old_model.TotalUsersDataInfo;
import com.app.eazydigi.util.Constants;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.PreferenceUtils;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.app.eazydigi.util.Constants.TYPE_MODULE_ACADEMIC_REPORTS;
import static com.app.eazydigi.util.Constants.TYPE_MODULE_APPLICANTS;
import static com.app.eazydigi.util.Constants.TYPE_MODULE_FEE;
import static com.app.eazydigi.util.Constants.TYPE_MODULE_HOMEWORK;
import static com.app.eazydigi.util.Constants.TYPE_MODULE_LEAVE_REQUEST;
import static com.app.eazydigi.util.Constants.TYPE_MODULE_LEDGER;
import static com.app.eazydigi.util.Constants.TYPE_MODULE_MARK_ATTENDANCE;
import static com.app.eazydigi.util.Constants.TYPE_MODULE_MESSENGER;
import static com.app.eazydigi.util.Constants.TYPE_MODULE_NOTICE_BOARD;
import static com.app.eazydigi.util.Constants.TYPE_MODULE_REPORTS;
import static com.app.eazydigi.util.Constants.TYPE_MODULE_STUDENT_DETAILS;
import static com.app.eazydigi.util.Constants.TYPE_MODULE_STUDENT_ID_CARD;
import static com.app.eazydigi.util.Constants.TYPE_MODULE_TRANSPORT;
import static com.app.eazydigi.util.Constants.TYPE_MODULE_USERS;

public class HomeFragment extends BaseFragment implements OnModuleClickListener {

    @BindView(R.id.rv_modules)
    RecyclerView rvModules;

    @BindView(R.id.rv_carousel)
    RecyclerView rvCarousel;

    ModulesAdapter modulesAdapter;
    CarouselAdapter carouselAdapter;
    int userRoleId;
    private DashboardResponse dashboardResponse;
    private TeacherDashboardResponse teacherDashboardResponse;
    private StudentDashboardResponse studentDashboardResponse;


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
            case "DASHBOARD_RESPONSE":
               // dashboardResponse=(DashboardResponse)eventModel.responseObject;

                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        modulesAdapter = new ModulesAdapter(getActivity(), this);
        carouselAdapter = new CarouselAdapter(getActivity(), Utils.getScreenWidth(getActivity()), this);

        if (PreferenceUtils.getUserRoleIdFromDefaults(getActivity())==Constants.UserRoles.ROLE_TEACHER){
            teacherDashboardResponse=LoginActivity.teacherDashboardResponsePublic;
            getTeacherDetail();
        }else if (PreferenceUtils.getUserRoleIdFromDefaults(getActivity())==Constants.UserRoles.ROLE_STUDENT){
            studentDashboardResponse=LoginActivity.studentDashboardResponsePublic;
        }else if (PreferenceUtils.getUserRoleIdFromDefaults(getActivity())==Constants.UserRoles.ROLE_ADMIN){
            dashboardResponse=LoginActivity.dashboardResponsePublic;
        }

        initViews();

        setData();

        if (Constants.UserRoles.ROLE_ADMIN==userRoleId){
            getDashboardInfo();
        }else if (Constants.UserRoles.ROLE_STUDENT==userRoleId){
            getDashboardStudent();
        }else if (Constants.UserRoles.ROLE_TEACHER==userRoleId){
            getDashboardTeacher();
        }

        return view;

    }

    private void setData() {

        userRoleId = PreferenceUtils.getUserRoleIdFromDefaults(getActivity());

        switch (userRoleId) {

            case Constants.UserRoles.ROLE_ADMIN:
                modulesAdapter.addModules(Arrays.asList(Constants.listAdminModules));
                getTotalUsersData();
                getTodaysTotalFeeCollection();
                getVehicleDetails();
                getLedgerExpenseTrendByDepartment();
                break;
            case Constants.UserRoles.ROLE_TEACHER:

                modulesAdapter.addModules(Arrays.asList(Constants.listTeacherModules));
                getTotalStudentsByClass();
                getTodaysAttendanceData();
                break;
            case Constants.UserRoles.ROLE_STUDENT:

                modulesAdapter.addModules(Arrays.asList(Constants.listStudentModules));
                getTotalPaidFeeForStudent();
                getAttendanceDetailForStudent();
                getPendingFeeForSingleStudent();
                break;
            default:
                break;
        }

    }

    private void initViews() {

        rvCarousel.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        rvCarousel.setAdapter(carouselAdapter);

        rvModules.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvModules.setAdapter(modulesAdapter);

    }

    private void getTotalUsersData() {

        if (!Utils.isConnectedToInternet(getActivity())) {
            return;
        }


        try {

            int TotalStaffs=Integer.parseInt(MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TOTAL_STAFF));
            int TotalTeachers=Integer.parseInt(MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TOTAL_TEACHERS));
            int TotalStudents= Integer.parseInt(MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TOTAL_STUDENTS));
            int TotalGuardian=Integer.parseInt(MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TOTAL_PARENTS));;

            TotalUsersDataInfo totalUsersDataInfo = new TotalUsersDataInfo();
            totalUsersDataInfo.setTotalGuardian(TotalGuardian);
            totalUsersDataInfo.setTotalStaffs(TotalStaffs);
            totalUsersDataInfo.setTotalStudents(TotalStudents);
            totalUsersDataInfo.setTotalTeachers(TotalTeachers);

            carouselAdapter.addModule(carouselAdapter.TYPE_TOTAL_USERS, totalUsersDataInfo);

            rvCarousel.setVisibility(View.VISIBLE);

        }catch (Exception e){
            e.printStackTrace();
        }

        /*
        MyApplication.getAppInstance().getAPIInterface().getTotalUsersData(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId()).enqueue(new Callback<TotalUsersDataInfo>() {
            @Override
            public void onResponse(Call<TotalUsersDataInfo> call, Response<TotalUsersDataInfo> response) {

                if (response.isSuccessful()) {

                    TotalUsersDataInfo totalUsersDataInfo = response.body();

//                    carouselAdapter.addModule(totalUsersDataInfo);
                    totalUsersDataInfo.setTotalGuardian(0);
                    totalUsersDataInfo.setTotalStaffs(dashboardResponse.getData().getTotalStaffs());
                    totalUsersDataInfo.setTotalStudents(dashboardResponse.getData().getTotalStudent());
                    totalUsersDataInfo.setTotalTeachers(dashboardResponse.getData().getTotalTeachers());
                    carouselAdapter.addModule(carouselAdapter.TYPE_TOTAL_USERS, totalUsersDataInfo);

                    rvCarousel.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onFailure(Call<TotalUsersDataInfo> call, Throwable t) {
                t.printStackTrace();
            }
        }); */
    }

    private void getTodaysTotalFeeCollection() {

        if (!Utils.isConnectedToInternet(getActivity())) {
            return;
        }

        carouselAdapter.addModule(carouselAdapter.TYPE_TODAY_TOTAL_FEE_COLLECTION, new ArrayList<StudentFeeDetail>());
        rvCarousel.setVisibility(View.VISIBLE);

        /*
        MyApplication.getAppInstance().getAPIInterface().getTodaysTotalFeeCollection(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.convertToDateString(System.currentTimeMillis(), "MM/dd/yyyy")).enqueue(new Callback<List<StudentFeeDetail>>() {
            @Override
            public void onResponse(Call<List<StudentFeeDetail>> call, Response<List<StudentFeeDetail>> response) {

                if (response.isSuccessful()) {

                    List<StudentFeeDetail> listStudentFeeDetails = response.body();

//                    carouselAdapter.addModule(todaysTotalFeeCollectionInfo);
                    carouselAdapter.addModule(carouselAdapter.TYPE_TODAY_TOTAL_FEE_COLLECTION, listStudentFeeDetails);
                    rvCarousel.setVisibility(View.VISIBLE);

                } else {

                }

            }

            @Override
            public void onFailure(Call<List<StudentFeeDetail>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        */
    }

    private void getVehicleDetails() {

        if (!Utils.isConnectedToInternet(getActivity())) {
            return;
        }

        carouselAdapter.addModule(carouselAdapter.TYPE_TRANSPORT, null);

        rvCarousel.setVisibility(View.VISIBLE);

        /*
        MyApplication.getAppInstance().getAPIInterface().getVehicleDetails(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId()).enqueue(new Callback<List<VehicleDetail>>() {
            @Override
            public void onResponse(Call<List<VehicleDetail>> call, Response<List<VehicleDetail>> response) {

                if (response.isSuccessful()) {

                    List<VehicleDetail> listVehicleDetails = response.body();
//                    carouselAdapter.addModule(vehicleDetailsInfo);
                    carouselAdapter.addModule(carouselAdapter.TYPE_TRANSPORT, listVehicleDetails);

                    rvCarousel.setVisibility(View.VISIBLE);

                } else {

                }

            }

            @Override
            public void onFailure(Call<List<VehicleDetail>> call, Throwable t) {
                t.printStackTrace();
            }
        });

         */
    }

    private void getLedgerExpenseTrendByDepartment() {

        if (!Utils.isConnectedToInternet(getActivity())) {
            return;
        }
        carouselAdapter.addModule(carouselAdapter.TYPE_TOTAL_EXPENSE, null);

        rvCarousel.setVisibility(View.VISIBLE);

        /*
        MyApplication.getAppInstance().getAPIInterface().getLedgerExpenseTrendByDepartment(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.convertToDateString(System.currentTimeMillis(), "MM/dd/yyyy")).enqueue(new Callback<List<ExpenseDetail>>() {
            @Override
            public void onResponse(Call<List<ExpenseDetail>> call, Response<List<ExpenseDetail>> response) {

                if (response.isSuccessful()) {

                    List<ExpenseDetail> listExpenses = response.body();
//                    carouselAdapter.addModule(vehicleDetailsInfo);
                    carouselAdapter.addModule(carouselAdapter.TYPE_TOTAL_EXPENSE, listExpenses);

                    rvCarousel.setVisibility(View.VISIBLE);

                } else {

                }

            }

            @Override
            public void onFailure(Call<List<ExpenseDetail>> call, Throwable t) {
                t.printStackTrace();
            }
        });
         */
    }

    private void getTotalStudentsByClass() {

        if (!Utils.isConnectedToInternet(getActivity())) {
            return;
        }

        try {
            int TotalStaffs=0;
            int TotalTeachers=0;
            int TotalStudents= Integer.parseInt(MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TEACHER_TOTAL_STUDENT));
            int TotalGuardian=0;

            TotalStudentsByClassInfo totalStudentsByClassInfo = new TotalStudentsByClassInfo();
            totalStudentsByClassInfo.setTotalGuardian(TotalGuardian);
            totalStudentsByClassInfo.setTotalStaffs(TotalStaffs);
            totalStudentsByClassInfo.setTotalStudents(TotalStudents);
            totalStudentsByClassInfo.setTotalTeachers(TotalTeachers);

            carouselAdapter.addModule(carouselAdapter.TYPE_TOTAL_STUDENTS, totalStudentsByClassInfo);

            rvCarousel.setVisibility(View.VISIBLE);

        }catch (Exception e){
            e.printStackTrace();
        }
        /*
        MyApplication.getAppInstance().getAPIInterface().getTotalStudentsByClass(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                MyApplication.getAppInstance().getLoginResponseInfo().getClassId(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSectionId()).enqueue(new Callback<TotalStudentsByClassInfo>() {

            @Override
            public void onResponse(Call<TotalStudentsByClassInfo> call, Response<TotalStudentsByClassInfo> response) {

                if (response.isSuccessful()) {

                    TotalStudentsByClassInfo totalStudentsByClassInfo = response.body();
//                    carouselAdapter.addModule(totalStudentsByClassInfo);
                    carouselAdapter.addModule(carouselAdapter.TYPE_TOTAL_STUDENTS, totalStudentsByClassInfo);

                    rvCarousel.setVisibility(View.VISIBLE);
                } else {

                }

            }

            @Override
            public void onFailure(Call<TotalStudentsByClassInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });
        */
    }

    private void getTodaysAttendanceData() {

        if (!Utils.isConnectedToInternet(getActivity())) {
            return;
        }
        carouselAdapter.addModule(carouselAdapter.TYPE_TODAY_ATTENDANCE, null);

        rvCarousel.setVisibility(View.VISIBLE);

        /*
        MyApplication.getAppInstance().getAPIInterface().getTodaysAttendanceData(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                MyApplication.getAppInstance().getLoginResponseInfo().getClassId(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSectionId(),
                Utils.convertToDateString(System.currentTimeMillis(), "MM/dd/yyyy")).enqueue(new Callback<List<AttendanceDetailsByClass>>() {

            @Override
            public void onResponse(Call<List<AttendanceDetailsByClass>> call, Response<List<AttendanceDetailsByClass>> response) {

                if (response.isSuccessful()) {

                    List<AttendanceDetailsByClass> listAttendanceByClass = response.body();
//                    carouselAdapter.addModule(totalStudentsByClassInfo);
                    carouselAdapter.addModule(carouselAdapter.TYPE_TODAY_ATTENDANCE, listAttendanceByClass);

                    rvCarousel.setVisibility(View.VISIBLE);
                } else {

                }

            }

            @Override
            public void onFailure(Call<List<AttendanceDetailsByClass>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        */
    }

    private void getTotalPaidFeeForStudent() {

        if (!Utils.isConnectedToInternet(getActivity())) {
            return;
        }
        try {
            TotalPaidFeeForStudentInfo totalPaidFeeForStudentInfo = new TotalPaidFeeForStudentInfo();
            totalPaidFeeForStudentInfo.setTotalPaidFees(MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().STUDENT_FEES_PAID));
            carouselAdapter.addModule(carouselAdapter.TYPE_TOTAL_PAID_FEES, totalPaidFeeForStudentInfo);

            rvCarousel.setVisibility(View.VISIBLE);
        }catch (Exception e){
            e.printStackTrace();
        }

/*
        MyApplication.getAppInstance().getAPIInterface().getTotalPaidFeeForStudent(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                MyApplication.getAppInstance().getLoginResponseInfo().getUserId(), 1).enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {

                    try {
                        String feesPaid = response.body().string();

                        TotalPaidFeeForStudentInfo totalPaidFeeForStudentInfo = new TotalPaidFeeForStudentInfo();
                        totalPaidFeeForStudentInfo.setTotalPaidFees(feesPaid);
//                        carouselAdapter.addModule(totalPaidFeeForStudentInfo);
                        carouselAdapter.addModule(carouselAdapter.TYPE_TOTAL_PAID_FEES, totalPaidFeeForStudentInfo);

                        rvCarousel.setVisibility(View.VISIBLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
        */
    }

    private void getAttendanceDetailForStudent() {

        if (!Utils.isConnectedToInternet(getActivity())) {
            return;
        }

       try {
           AttendanceDetailForStudentInfo attendanceDetailForStudentInfo = new AttendanceDetailForStudentInfo();
           attendanceDetailForStudentInfo.setAbsent(MyApplication.getAppInstance().sharedPref().getInt(MyApplication.getAppInstance().sharedPref().STUDENT_MY_ABSENT));
           attendanceDetailForStudentInfo.setPresent(MyApplication.getAppInstance().sharedPref().getInt(MyApplication.getAppInstance().sharedPref().STUDENT_MY_PRESENT));
           try {
               attendanceDetailForStudentInfo.setAttendancePercentage(Float.parseFloat(MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().STUDENT_ATTENDANCE_PER)));
           }catch (Exception e){
               attendanceDetailForStudentInfo.setAttendancePercentage(0.1f);
               e.printStackTrace();
           }
           carouselAdapter.addModule(carouselAdapter.TYPE_MY_ATTENDANCE, attendanceDetailForStudentInfo);
           rvCarousel.setVisibility(View.VISIBLE);
       }catch (Exception e){
           e.printStackTrace();
       }

/*
        MyApplication.getAppInstance().getAPIInterface().getAttendanceDetailForStudent(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                MyApplication.getAppInstance().getLoginResponseInfo().getUserId(), 1).enqueue(new Callback<AttendanceDetailForStudentInfo>() {

            @Override
            public void onResponse(Call<AttendanceDetailForStudentInfo> call, Response<AttendanceDetailForStudentInfo> response) {

                if (response.isSuccessful()) {

                    try {
                        AttendanceDetailForStudentInfo attendanceDetailForStudentInfo = response.body();
//                        carouselAdapter.addModule(attendanceDetailForStudentInfo);
                        carouselAdapter.addModule(carouselAdapter.TYPE_MY_ATTENDANCE, attendanceDetailForStudentInfo);
                        rvCarousel.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {

                }

            }

            @Override
            public void onFailure(Call<AttendanceDetailForStudentInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });
       */
    }

    private void getPendingFeeForSingleStudent() {

        if (!Utils.isConnectedToInternet(getActivity())) {
            return;
        }
        StudentPendingFeeDetails studentPendingFeeDetails=new StudentPendingFeeDetails();
        carouselAdapter.addModule(carouselAdapter.TYPE_TOTAL_PENDING_FEES, studentPendingFeeDetails);
        rvCarousel.setVisibility(View.VISIBLE);

        /*
        MyApplication.getAppInstance().getAPIInterface().getPendingFeeForSingleStudent(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                MyApplication.getAppInstance().getLoginResponseInfo().getUserId()).enqueue(new Callback<StudentPendingFeeDetails>() {

            @Override
            public void onResponse(Call<StudentPendingFeeDetails> call, Response<StudentPendingFeeDetails> response) {

                if (response.isSuccessful()) {

                    StudentPendingFeeDetails studentPendingFeeDetails = response.body();
//                    carouselAdapter.addModule(totalStudentsByClassInfo);
                    carouselAdapter.addModule(carouselAdapter.TYPE_TOTAL_PENDING_FEES, studentPendingFeeDetails);

                    rvCarousel.setVisibility(View.VISIBLE);
                } else {

                }

            }

            @Override
            public void onFailure(Call<StudentPendingFeeDetails> call, Throwable t) {
                t.printStackTrace();
            }
        });
        */

    }

    public void getTeacherDetail(){

        APIInterface apiService = ApiClient.getClient(getActivity()).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getTeacherDetail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<TeacherDetailResponse>() {
                    @Override
                    public void onSuccess(TeacherDetailResponse teacherDetailResponse) {

                        try {
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TEACHER_CLASS,teacherDetailResponse.getData().getAssignedClass());
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TEACHER_SECTION,teacherDetailResponse.getData().getAssignedSection());
                            MyApplication.getAppInstance().sharedPref().setInt(MyApplication.getAppInstance().sharedPref().IS_CLASS_TEACHER,teacherDetailResponse.getData().getIsClasstech());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.v("error",e.getMessage());
                    }
                }));
    }

    @Override
    public void onClick(String s) {

        if (s.equalsIgnoreCase(TYPE_MODULE_NOTICE_BOARD)) {

            if (userRoleId == Constants.UserRoles.ROLE_ADMIN)
                startActivity(new Intent(getActivity(), NoticesCircularsActivity.class));
            else
                startActivity(new Intent(getActivity(), NoticesCircularSTActivity.class));


        } else if (s.equalsIgnoreCase(TYPE_MODULE_MARK_ATTENDANCE)) {

            startActivity(new Intent(getActivity(), MarkAttendanceActivity.class));

        } else if (s.equalsIgnoreCase(TYPE_MODULE_ACADEMIC_REPORTS)) {

            startActivity(new Intent(getActivity(), AcademicReportActivity.class));

        } else if (s.equalsIgnoreCase(TYPE_MODULE_HOMEWORK)) {
            if (userRoleId == Constants.UserRoles.ROLE_TEACHER) {
                startActivity(new Intent(getActivity(), TeacherHomeworkActivity.class));
            } else if (userRoleId == Constants.UserRoles.ROLE_STUDENT) {
                startActivity(new Intent(getActivity(), StudentHomeworkActivity.class));
            }

        } else if (s.equalsIgnoreCase(TYPE_MODULE_FEE)) {
            startActivity(new Intent(getActivity(), FeeCollectionActivity.class));
        } else if (s.equalsIgnoreCase(TYPE_MODULE_USERS)) {
            Toast.makeText(getActivity(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show();
        } else if (s.equalsIgnoreCase(TYPE_MODULE_APPLICANTS)) {
            Toast.makeText(getActivity(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show();
        } else if (s.equalsIgnoreCase(TYPE_MODULE_LEDGER)) {
            Toast.makeText(getActivity(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show();
        } else if (s.equalsIgnoreCase(TYPE_MODULE_MESSENGER)) {
            Toast.makeText(getActivity(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show();
        } else if (s.equalsIgnoreCase(TYPE_MODULE_TRANSPORT)) {
            Toast.makeText(getActivity(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show();
        } else if (s.equalsIgnoreCase(TYPE_MODULE_STUDENT_ID_CARD)) {
            Toast.makeText(getActivity(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show();
        } else if (s.equalsIgnoreCase(TYPE_MODULE_REPORTS)) {
            Toast.makeText(getActivity(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show();
        } else if (s.equalsIgnoreCase(TYPE_MODULE_STUDENT_DETAILS)) {
            Toast.makeText(getActivity(), getString(R.string.coming_soon), Toast.LENGTH_SHORT).show();
        }else if (s.equalsIgnoreCase(TYPE_MODULE_LEAVE_REQUEST)) {

            if (userRoleId == Constants.UserRoles.ROLE_ADMIN) {
                startActivity(new Intent(getActivity(), LeaveListAdmin.class));
            } else if (userRoleId == Constants.UserRoles.ROLE_STUDENT) {
                startActivity(new Intent(getActivity(), LeaveRequestStudent.class));
            }
        }
    }

    public void getDashboardInfo(){
        ProgressHUD pd = showPD(null);

        APIInterface apiService = ApiClient.getClient(getActivity()).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getDashboard()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DashboardResponse>() {
                    @Override
                    public void onSuccess(DashboardResponse dashboardResponse) {
                        dismissPD(pd);
                        try {

                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TODAY_COLLECTION, String.valueOf(dashboardResponse.getData().getTodaysCollection()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_EXPENSE, String.valueOf(dashboardResponse.getData().getTotalExpense()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_BUS, String.valueOf(dashboardResponse.getData().getTotalBus()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_VAN, String.valueOf(dashboardResponse.getData().getTotalVan()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_OTHER_VEHICLE, String.valueOf(dashboardResponse.getData().getOthersVehicle()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_STUDENTS, String.valueOf(dashboardResponse.getData().getTotalStudent()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_TEACHERS, String.valueOf(dashboardResponse.getData().getTotalTeachers()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_STAFF, String.valueOf(dashboardResponse.getData().getTotalStaffs()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TOTAL_PARENTS, "0");

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

                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TEACHER_TOTAL_ABSENT, String.valueOf(dashboardResponse.getData().getAbsentStud()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TEACHER_TOTAL_PRESENT, String.valueOf(dashboardResponse.getData().getPresentStud()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().TEACHER_TOTAL_STUDENT, String.valueOf(dashboardResponse.getData().getTotalStud()));

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


                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().STUDENT_FEES_PENDING, String.valueOf(dashboardResponse.getData().getTotalFeesPending()));
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().STUDENT_FEES_PAID, String.valueOf(dashboardResponse.getData().getTotalFeesPaid()));
                            MyApplication.getAppInstance().sharedPref().setInt(MyApplication.getAppInstance().sharedPref().STUDENT_MY_ABSENT, dashboardResponse.getData().getMyAbsent());
                            MyApplication.getAppInstance().sharedPref().setInt(MyApplication.getAppInstance().sharedPref().STUDENT_MY_PRESENT, dashboardResponse.getData().getMyPresent());
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().STUDENT_ATTENDANCE_PER, String.valueOf(dashboardResponse.getData().getMyPercentage()));

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
