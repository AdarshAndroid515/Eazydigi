
package com.app.eazydigi.model.old_model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class TeacherHWAssignmentByStatusInfo implements Serializable {

    @SerializedName("admissionID")
    private String mAdmissionID;

    @SerializedName("asignedTo")
    private int mAsignedTo;

    @SerializedName("assignedOn")
    private String mAssignedOn;

    @SerializedName("assignmentId")
    private int mAssignmentId;

    @SerializedName("class")
    private String mClass;

    @SerializedName("dueDate")
    private String mDueDate;

    @SerializedName("homeworkDetail")
    private String mHomeworkDetail;

    @SerializedName("homeworkHeader")
    private String mHomeworkHeader;

    @SerializedName("homeworkId")
    private int mHomeworkId;

    @SerializedName("rollNumber")
    private String mRollNumber;

    @SerializedName("section")
    private String mSection;

    @SerializedName("status")
    private String mStatus;

    @SerializedName("statusId")
    private int mStatusId;

    @SerializedName("studentName")
    private String mStudentName;

    @SerializedName("subjectName")
    private String mSubjectName;

    public String getAdmissionID() {
        return mAdmissionID;
    }

    public void setAdmissionID(String admissionID) {
        mAdmissionID = admissionID;
    }

    public int getAsignedTo() {
        return mAsignedTo;
    }

    public void setAsignedTo(int asignedTo) {
        mAsignedTo = asignedTo;
    }

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

    public String getStudentClass() {
        return mClass;
    }

    public void setStudentClass(String mclass) {
        mClass = mclass;
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

    public int getHomeworkId() {
        return mHomeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        mHomeworkId = homeworkId;
    }

    public String getRollNumber() {
        return mRollNumber;
    }

    public void setRollNumber(String rollNumber) {
        mRollNumber = rollNumber;
    }

    public String getSection() {
        return mSection;
    }

    public void setSection(String section) {
        mSection = section;
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

    public String getStudentName() {
        return mStudentName;
    }

    public void setStudentName(String studentName) {
        mStudentName = studentName;
    }

    public String getSubjectName() {
        return mSubjectName;
    }

    public void setSubjectName(String subjectName) {
        mSubjectName = subjectName;
    }

}
