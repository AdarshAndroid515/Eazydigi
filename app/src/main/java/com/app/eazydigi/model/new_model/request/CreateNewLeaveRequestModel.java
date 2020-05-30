package com.app.eazydigi.model.new_model.request;

public class CreateNewLeaveRequestModel {

    /**
     * leaveSub : EID
     * description : EID Celebration
     * startDate : 2020-05-28
     * endDate : 2020-05-29
     */

    private String leaveSub;
    private String description;
    private String startDate;
    private String endDate;

    public String getLeaveSub() {
        return leaveSub;
    }

    public void setLeaveSub(String leaveSub) {
        this.leaveSub = leaveSub;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
