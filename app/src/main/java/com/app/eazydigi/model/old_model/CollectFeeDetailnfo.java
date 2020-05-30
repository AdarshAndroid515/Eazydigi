
package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CollectFeeDetailnfo implements Serializable {

    @SerializedName("classId")
    private Long mClassId;
    @SerializedName("course")
    private String mCourse;
    @SerializedName("fatherName")
    private String mFatherName;
    @SerializedName("monthsAlreadyPaid")
    private List<Long> mMonthsAlreadyPaid;
    @SerializedName("rollNumber")
    private String mRollNumber;
    @SerializedName("section")
    private String mSection;
    @SerializedName("studentClass")
    private String mStudentClass;
    @SerializedName("userCode")
    private String mUserCode;
    @SerializedName("userId")
    private Long mUserId;
    @SerializedName("userName")
    private String mUserName;

    public Long getClassId() {
        return mClassId;
    }

    public void setClassId(Long classId) {
        mClassId = classId;
    }

    public String getCourse() {
        return mCourse;
    }

    public void setCourse(String course) {
        mCourse = course;
    }

    public String getFatherName() {
        return mFatherName;
    }

    public void setFatherName(String fatherName) {
        mFatherName = fatherName;
    }

    public List<Long> getMonthsAlreadyPaid() {
        return mMonthsAlreadyPaid;
    }

    public void setMonthsAlreadyPaid(List<Long> monthsAlreadyPaid) {
        mMonthsAlreadyPaid = monthsAlreadyPaid;
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

    public String getStudentClass() {
        return mStudentClass;
    }

    public void setStudentClass(String studentClass) {
        mStudentClass = studentClass;
    }

    public String getUserCode() {
        return mUserCode;
    }

    public void setUserCode(String userCode) {
        mUserCode = userCode;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

}
