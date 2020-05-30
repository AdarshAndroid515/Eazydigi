package com.app.eazydigi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.AcademicReportModulesAdapter;
import com.app.eazydigi.listener.OnModuleClickListener;
import com.app.eazydigi.util.Constants;
import com.app.eazydigi.util.PreferenceUtils;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcademicReportActivity extends BaseActivity implements OnModuleClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_modules)
    RecyclerView rvModules;

    public static final String TYPE_MODULE_ATTENDANCE_REPORTS = "Attendance Reports";
    public static final String TYPE_MODULE_STUDENT_REPORTS = "Student Type Reports";
    public static final String TYPE_MODULE_GENERATE_CERTIFICATE = "Generate Certificate";

//    public static final String[] listAcademicReportModulesForAdmin = {TYPE_MODULE_STUDENT_REPORTS, TYPE_MODULE_ATTENDANCE_REPORTS, TYPE_MODULE_GENERATE_CERTIFICATE};
    public static final String[] listAcademicReportModulesForAdmin = {TYPE_MODULE_ATTENDANCE_REPORTS};
    public static final String[] listAcademicReportModulesForTeacher = {TYPE_MODULE_ATTENDANCE_REPORTS};
    public static final String[] listAcademicReportModulesForStudent = {TYPE_MODULE_ATTENDANCE_REPORTS};

//    public static final Integer[] listAcademicReportModulesImagesForAdmin = {R.drawable.ic_student, R.drawable.ic_mark_attendance, R.drawable.ic_student};
    public static final Integer[] listAcademicReportModulesImagesForAdmin = {R.drawable.ic_mark_attendance};
    public static final Integer[] listAcademicReportModulesImagesForTeacher = {R.drawable.ic_mark_attendance};
    public static final Integer[] listAcademicReportModulesImagesForStudent = {R.drawable.ic_mark_attendance};


    AcademicReportModulesAdapter modulesAdapter;

    int userRoleId;

    private static final String TAG = "AcademicReportActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_report);
        ButterKnife.bind(this);

        modulesAdapter = new AcademicReportModulesAdapter(this, this);

        userRoleId = PreferenceUtils.getUserRoleIdFromDefaults(context);

        initToolbar();

        initViews();

        setData();


    }

    private void setData() {

        switch (userRoleId) {
            case Constants.UserRoles.ROLE_ADMIN:
                modulesAdapter.addModules(Arrays.asList(listAcademicReportModulesForAdmin), Arrays.asList(listAcademicReportModulesImagesForAdmin));
                break;
            case Constants.UserRoles.ROLE_TEACHER:
                modulesAdapter.addModules(Arrays.asList(listAcademicReportModulesForTeacher), Arrays.asList(listAcademicReportModulesImagesForTeacher));
                break;
            case Constants.UserRoles.ROLE_STUDENT:
                modulesAdapter.addModules(Arrays.asList(listAcademicReportModulesForStudent), Arrays.asList(listAcademicReportModulesImagesForStudent));
                break;
            default:
                break;
        }

    }

    public void initViews() {

        rvModules.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvModules.setAdapter(modulesAdapter);

    }

    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getString(R.string.academic_report));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onClick(String s) {

        if (s.equalsIgnoreCase(TYPE_MODULE_ATTENDANCE_REPORTS)) {
            if (userRoleId == Constants.UserRoles.ROLE_ADMIN) {
                startActivity(new Intent(context, AttendanceReportForAdminActivity.class));
            } else if (userRoleId == Constants.UserRoles.ROLE_TEACHER) {
                startActivity(new Intent(context, AttendanceReportForTeacherActivity.class));
            } else if (userRoleId == Constants.UserRoles.ROLE_STUDENT) {
                startActivity(new Intent(context, AttendanceReportForStudentActivity.class));
            }
        }
    }
}