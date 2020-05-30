package com.app.eazydigi.model.new_model.response;

public class HomeworkStatusResponse {

    /**
     * statusId : 1
     * status : Pending
     * count : 26
     */

    private int statusId;
    private String status;
    private int count;
    private boolean selected;


    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
