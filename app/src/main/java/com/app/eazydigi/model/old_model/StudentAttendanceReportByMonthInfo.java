
package com.app.eazydigi.model.old_model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class StudentAttendanceReportByMonthInfo implements Serializable {

    @SerializedName("attendanceStatus")
    private Long mAttendanceStatus;
    @SerializedName("createdDate")
    private String mCreatedDate;
    @SerializedName("dayName")
    private String mDayName;
    @SerializedName("status")
    private String mStatus;

    public Long getAttendanceStatus() {
        return mAttendanceStatus;
    }

    public void setAttendanceStatus(Long attendanceStatus) {
        mAttendanceStatus = attendanceStatus;
    }

    public String getCreatedDate() {
        return mCreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        mCreatedDate = createdDate;
    }

    public String getDayName() {
        return mDayName;
    }

    public void setDayName(String dayName) {
        mDayName = dayName;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
