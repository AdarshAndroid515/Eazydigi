package com.app.eazydigi.model.new_model.request;

import com.google.gson.annotations.SerializedName;

public class AttendanceListRequest {


    /**
     * class :
     * sec :
     * attDate :
     */

    @SerializedName("class")
    private String classX;
    private String sec;
    private String attDate;

    public String getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getAttDate() {
        return attDate;
    }

    public void setAttDate(String attDate) {
        this.attDate = attDate;
    }
}
