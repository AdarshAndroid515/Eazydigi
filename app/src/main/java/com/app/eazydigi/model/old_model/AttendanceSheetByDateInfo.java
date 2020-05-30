package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AttendanceSheetByDateInfo implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("attendanceMonth")
    private int attendanceMonth;

    @SerializedName("attendanceYear")
    private int attendanceYear;

    @SerializedName("classId")
    private int classId;

    @SerializedName("sectionId")
    private int sectionId;

    @SerializedName("schoolId")
    private int schoolId;

    @SerializedName("acadmicYear")
    private String acadmicYear;

    @SerializedName("attendanceDetail")
    private List<AttendanceDetailForStudentInfo> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAttendanceMonth() {
        return attendanceMonth;
    }

    public void setAttendanceMonth(int attendanceMonth) {
        this.attendanceMonth = attendanceMonth;
    }

    public int getAttendanceYear() {
        return attendanceYear;
    }

    public void setAttendanceYear(int attendanceYear) {
        this.attendanceYear = attendanceYear;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getAcadmicYear() {
        return acadmicYear;
    }

    public void setAcadmicYear(String acadmicYear) {
        this.acadmicYear = acadmicYear;
    }

    public List<AttendanceDetailForStudentInfo> getList() {
        return list;
    }

    public void setList(List<AttendanceDetailForStudentInfo> list) {
        this.list = list;
    }



}