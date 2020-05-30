package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TotalStudentsByClassInfo implements Serializable {

    @SerializedName("totalStudents")
    private int totalStudents;

    @SerializedName("totalGuardian")
    private int totalGuardian;

    @SerializedName("totalTeachers")
    private int totalTeachers;

    @SerializedName("totalStaffs")
    private int totalStaffs;

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public int getTotalGuardian() {
        return totalGuardian;
    }

    public void setTotalGuardian(int totalGuardian) {
        this.totalGuardian = totalGuardian;
    }

    public int getTotalTeachers() {
        return totalTeachers;
    }

    public void setTotalTeachers(int totalTeachers) {
        this.totalTeachers = totalTeachers;
    }

    public int getTotalStaffs() {
        return totalStaffs;
    }

    public void setTotalStaffs(int totalStaffs) {
        this.totalStaffs = totalStaffs;
    }

}