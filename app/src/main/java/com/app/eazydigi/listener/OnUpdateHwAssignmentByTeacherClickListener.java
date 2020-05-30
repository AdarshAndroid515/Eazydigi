package com.app.eazydigi.listener;

import com.app.eazydigi.model.new_model.response.HomeWorkDashboardTeacherResponse;
import com.app.eazydigi.model.old_model.TeacherHWAssignmentByStatusInfo;

public interface OnUpdateHwAssignmentByTeacherClickListener {

    public void onUpdateHwAssignmentClick(TeacherHWAssignmentByStatusInfo hwAssignmentByStatusInfo);

    public void onUpdateHwAssignmentClick2(HomeWorkDashboardTeacherResponse.DataBean.StudentListBean studentListBean);
}
