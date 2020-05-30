package com.app.eazydigi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.eazydigi.R;
import com.app.eazydigi.listener.OnModuleClickListener;
import com.app.eazydigi.model.old_model.AttendanceDetailForStudentInfo;
import com.app.eazydigi.model.old_model.TotalPaidFeeForStudentInfo;
import com.app.eazydigi.model.old_model.TotalStudentsByClassInfo;
import com.app.eazydigi.model.old_model.TotalUsersDataInfo;
import com.app.eazydigi.util.MyApplication;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarouselAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;

    LinkedHashMap<Integer, Object> listModules = new LinkedHashMap<>();

    List<Integer> listKeysets = new ArrayList<>();

    OnModuleClickListener onModuleClickListener;

    int itemWidth;

    public final int TYPE_TOTAL_USERS = 1;
    public final int TYPE_TOTAL_EXPENSE = 2;
    public final int TYPE_TODAY_TOTAL_FEE_COLLECTION = 3;
    public final int TYPE_TRANSPORT = 4;

    public final int TYPE_TOTAL_STUDENTS = 5;
    public final int TYPE_TODAY_ATTENDANCE = 6;

    public final int TYPE_TOTAL_PAID_FEES = 7;
    public final int TYPE_TOTAL_PENDING_FEES = 8;
    public final int TYPE_MY_ATTENDANCE = 9;

    public CarouselAdapter(Context c, int screenWidth, OnModuleClickListener listener) {
        this.context = c;
        this.onModuleClickListener = listener;
        itemWidth = (int) (screenWidth * 0.80);

    }

    public void addModule(int key, Object obj) {

        try {

            listModules.put(key, obj);

        } catch (Exception e) {
            e.printStackTrace();
        }

        listKeysets = new ArrayList<>(listModules.keySet());

        notifyDataSetChanged();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        switch (viewType) {

            case TYPE_TODAY_TOTAL_FEE_COLLECTION:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_today_fee_collection, parent, false);
                if (itemWidth > 0) {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(itemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                    itemView.setLayoutParams(lp);
                }
                return new TodayFeeCollectionViewHolder(itemView);

            case TYPE_TOTAL_EXPENSE:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_total_expense, parent, false);
                if (itemWidth > 0) {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(itemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                    itemView.setLayoutParams(lp);
                }
                return new TotalExpenseViewHolder(itemView);

            case TYPE_TOTAL_USERS:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_users, parent, false);
                if (itemWidth > 0) {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(itemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                    itemView.setLayoutParams(lp);
                }
                return new TotalUsersViewHolder(itemView);

            case TYPE_TOTAL_STUDENTS:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_students, parent, false);
                if (itemWidth > 0) {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(itemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                    itemView.setLayoutParams(lp);
                }
                return new TotalStudentsViewHolder(itemView);
            case TYPE_TODAY_ATTENDANCE:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_today_total_attendance, parent, false);
                if (itemWidth > 0) {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(itemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                    itemView.setLayoutParams(lp);
                }
                return new TodayAttendanceViewHolder(itemView);

            case TYPE_TOTAL_PAID_FEES:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_fees_paid, parent, false);
                if (itemWidth > 0) {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(itemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                    itemView.setLayoutParams(lp);
                }
                return new TotalFeesPaidViewHolder(itemView);

            case TYPE_MY_ATTENDANCE:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_my_attendance, parent, false);
                if (itemWidth > 0) {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(itemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                    itemView.setLayoutParams(lp);
                }
                return new MyAttendanceViewHolder(itemView);
            case TYPE_TOTAL_PENDING_FEES:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_fees_pending, parent, false);
                if (itemWidth > 0) {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(itemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                    itemView.setLayoutParams(lp);
                }
                return new TotalFeesPendingViewHolder(itemView);
            default:
            case TYPE_TRANSPORT:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_home_transport, parent, false);
                if (itemWidth > 0) {
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(itemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
                    itemView.setLayoutParams(lp);
                }
                return new TransportViewHolder(itemView);

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        try {

            switch (getItemViewType(position)) {

                case TYPE_TODAY_TOTAL_FEE_COLLECTION:

                    try {

                        TodayFeeCollectionViewHolder todayFeeCollectionViewHolder = (TodayFeeCollectionViewHolder) holder;

                        /*
                        List<StudentFeeDetail> listStudentsFeeDetails = (List<StudentFeeDetail>) listModules.get(TYPE_TODAY_TOTAL_FEE_COLLECTION);

                        double totalFeeCollection = 0;
                        for (StudentFeeDetail studentFeeDetails : listStudentsFeeDetails) {
                            totalFeeCollection += studentFeeDetails.getTotalFeeAndTaxes();
                        }

                        todayFeeCollectionViewHolder.tvCollectedFees.setText("" + totalFeeCollection);
                        */

                        todayFeeCollectionViewHolder.tvCollectedFees.setText("" + MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TODAY_COLLECTION));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                case TYPE_TOTAL_EXPENSE:
                    try {

                        //List<ExpenseDetail> listExpenses = (List<ExpenseDetail>) listModules.get(TYPE_TOTAL_EXPENSE);

                        TotalExpenseViewHolder totalExpenseViewHolder = (TotalExpenseViewHolder) holder;
                        /*
                        double totalExpense = 0;
                        for (ExpenseDetail expenseDetail : listExpenses) {
                            totalExpense += expenseDetail.getTotalLedgerExpense();
                        }
                        */

                        totalExpenseViewHolder.tvTotalExpense.setText("" + MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TOTAL_EXPENSE));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case TYPE_TOTAL_USERS:

                    try {

                        TotalUsersDataInfo totalUsersDataInfo = (TotalUsersDataInfo) listModules.get(TYPE_TOTAL_USERS);

                        TotalUsersViewHolder totalUsersViewHolder = (TotalUsersViewHolder) holder;

                        totalUsersViewHolder.tvStudents.setText("" + totalUsersDataInfo.getTotalStudents());
                        totalUsersViewHolder.tvParents.setText("" + totalUsersDataInfo.getTotalGuardian());
                        totalUsersViewHolder.tvTeachers.setText("" + totalUsersDataInfo.getTotalTeachers());
                        totalUsersViewHolder.tvStaffs.setText("" + totalUsersDataInfo.getTotalStaffs());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                case TYPE_TOTAL_STUDENTS:

                    try {

                        TotalStudentsByClassInfo totalStudentsByClassInfo = (TotalStudentsByClassInfo) listModules.get(TYPE_TOTAL_STUDENTS);
                        TotalStudentsViewHolder totalStudentsViewHolder = (TotalStudentsViewHolder) holder;
                        totalStudentsViewHolder.tvStudents.setText("" + totalStudentsByClassInfo.getTotalStudents());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                case TYPE_TOTAL_PAID_FEES:

                    try {

                        TotalPaidFeeForStudentInfo totalPaidFeeForStudentInfo = (TotalPaidFeeForStudentInfo) listModules.get(TYPE_TOTAL_PAID_FEES);

                        TotalFeesPaidViewHolder totalFeesPaidViewHolder = (TotalFeesPaidViewHolder) holder;

                        totalFeesPaidViewHolder.tvFees.setText("" + totalPaidFeeForStudentInfo.getTotalPaidFees());

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                case TYPE_TODAY_ATTENDANCE:
                    try {

                        TodayAttendanceViewHolder todayAttendanceViewHolder = (TodayAttendanceViewHolder) holder;
                        /*
                        List<AttendanceDetailsByClass> listAttendanceDetailByClass = (List<AttendanceDetailsByClass>) listModules.get(TYPE_TODAY_ATTENDANCE);

                        double absent = 0;
                        double present = 0;
                        for (AttendanceDetailsByClass attendanceDetailsByClass : listAttendanceDetailByClass) {
                            absent += attendanceDetailsByClass.getAbsent();
                            present += attendanceDetailsByClass.getPresent();
                        }
                        */

                        todayAttendanceViewHolder.tvAbsent.setText("" +MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TEACHER_TOTAL_ABSENT));
                        todayAttendanceViewHolder.tvPresent.setText("" + MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TEACHER_TOTAL_PRESENT));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case TYPE_MY_ATTENDANCE:

                    try {

                        AttendanceDetailForStudentInfo attendanceDetailForStudentInfo = (AttendanceDetailForStudentInfo) listModules.get(TYPE_MY_ATTENDANCE);

                        MyAttendanceViewHolder myAttendanceViewHolder = (MyAttendanceViewHolder) holder;

                        myAttendanceViewHolder.tvAbsent.setText("" + attendanceDetailForStudentInfo.getAbsent());

                        myAttendanceViewHolder.tvPresent.setText("" + attendanceDetailForStudentInfo.getPresent());

                        myAttendanceViewHolder.tvPercentage.setText("" + String.format("%.2f", attendanceDetailForStudentInfo.getAttendancePercentage()) + "%");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                case TYPE_TOTAL_PENDING_FEES:

                    try {

                        //StudentPendingFeeDetails studentPendingFeeDetails = (StudentPendingFeeDetails) listModules.get(TYPE_TOTAL_PENDING_FEES);

                        TotalFeesPendingViewHolder totalFeesPendingViewHolder = (TotalFeesPendingViewHolder) holder;

                        totalFeesPendingViewHolder.tvFees.setText(""+MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().STUDENT_FEES_PENDING));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                default:
                case TYPE_TRANSPORT:

                    try {

                        TransportViewHolder transportViewHolder = (TransportViewHolder) holder;

                        transportViewHolder.tvBus.setText(""+MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TOTAL_BUS));

                        transportViewHolder.tvVan.setText(""+MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TOTAL_VAN));

                        transportViewHolder.tvOthers.setText(""+MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().TOTAL_OTHER_VEHICLE));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
//        return 8;
//        return listObjects.size();
        return listModules.keySet().size();
    }

    @Override
    public int getItemViewType(int position) {
        return listKeysets.get(position);
    }

    public class TodayFeeCollectionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_fee_colllected)
        TextView tvCollectedFees;

        public TodayFeeCollectionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class TotalUsersViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_students)
        LinearLayout llStudents;

        @BindView(R.id.tv_students)
        TextView tvStudents;

        @BindView(R.id.ll_parents)
        LinearLayout llParents;

        @BindView(R.id.tv_parents)
        TextView tvParents;

        @BindView(R.id.ll_teachers)
        LinearLayout llTeachers;

        @BindView(R.id.tv_teachers)
        TextView tvTeachers;

        @BindView(R.id.ll_staffs)
        LinearLayout llStaffs;

        @BindView(R.id.tv_staffs)
        TextView tvStaffs;

        public TotalUsersViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public class TotalStudentsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_students)
        LinearLayout llStudents;

        @BindView(R.id.tv_students)
        TextView tvStudents;

        public TotalStudentsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public class TodayAttendanceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_present)
        TextView tvPresent;

        @BindView(R.id.tv_absent)
        TextView tvAbsent;

        public TodayAttendanceViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public class TotalFeesPaidViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_fees)
        TextView tvFees;

        public TotalFeesPaidViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public class MyAttendanceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_present)
        TextView tvPresent;

        @BindView(R.id.tv_absent)
        TextView tvAbsent;

        @BindView(R.id.tv_percentage)
        TextView tvPercentage;

        public MyAttendanceViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public class TotalFeesPendingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_fees)
        TextView tvFees;

        public TotalFeesPendingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }


    public class TransportViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_van)
        TextView tvVan;

        @BindView(R.id.tv_bus)
        TextView tvBus;

        @BindView(R.id.tv_others)
        TextView tvOthers;

        public TransportViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    public class TotalExpenseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_total_expense)
        TextView tvTotalExpense;

        public TotalExpenseViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}