package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AttendanceDetailForStudentInfo implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("present")
    private int present;

    @SerializedName("absent")
    private int absent;

    @SerializedName("attendancePercentage")
    private float attendancePercentage;

    @SerializedName("studentName")
    private String studentName;

    @SerializedName("studentProfileImage")
    private String studentProfileImage;

    @SerializedName("studentID")
    private int studentID;

    @SerializedName("rollNumber")
    private String rollNumber;

    @SerializedName("attendance")
    private List<Attendance> attendanceList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public float getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(float attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentProfileImage() {
        return studentProfileImage;
    }

    public void setStudentProfileImage(String studentProfileImage) {
        this.studentProfileImage = studentProfileImage;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }





}