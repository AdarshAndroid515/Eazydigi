
package com.app.eazydigi.model.old_model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class StudentHWAssignmentByStatusInfo implements Serializable {

    @SerializedName("assignedOn")
    private String mAssignedOn;
    @SerializedName("assignmentId")
    private int mAssignmentId;
    @SerializedName("dueDate")
    private String mDueDate;
    @SerializedName("homeworkDetail")
    private String mHomeworkDetail;
    @SerializedName("homeworkHeader")
    private String mHomeworkHeader;
    @SerializedName("homeworkId")
    private Long mHomeworkId;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("statusId")
    private int mStatusId;
    @SerializedName("subjectName")
    private String mSubjectName;
    @SerializedName("teacherName")
    private String mTeacherName;

    public String getAssignedOn() {
        return mAssignedOn;
    }

    public void setAssignedOn(String assignedOn) {
        mAssignedOn = assignedOn;
    }

    public int getAssignmentId() {
        return mAssignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        mAssignmentId = assignmentId;
    }

    public String getDueDate() {
        return mDueDate;
    }

    public void setDueDate(String dueDate) {
        mDueDate = dueDate;
    }

    public String getHomeworkDetail() {
        return mHomeworkDetail;
    }

    public void setHomeworkDetail(String homeworkDetail) {
        mHomeworkDetail = homeworkDetail;
    }

    public String getHomeworkHeader() {
        return mHomeworkHeader;
    }

    public void setHomeworkHeader(String homeworkHeader) {
        mHomeworkHeader = homeworkHeader;
    }

    public Long getHomeworkId() {
        return mHomeworkId;
    }

    public void setHomeworkId(Long homeworkId) {
        mHomeworkId = homeworkId;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public int getStatusId() {
        return mStatusId;
    }

    public void setStatusId(int statusId) {
        mStatusId = statusId;
    }

    public String getSubjectName() {
        return mSubjectName;
    }

    public void setSubjectName(String subjectName) {
        mSubjectName = subjectName;
    }

    public String getTeacherName() {
        return mTeacherName;
    }

    public void setTeacherName(String teacherName) {
        mTeacherName = teacherName;
    }

}
