
package com.app.eazydigi.model.old_model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TeacherHWAssignmentStateInfo implements Serializable {

    @SerializedName("count")
    private int mCount;

    @SerializedName("status")
    private String mStatus;

    @SerializedName("statusId")
    private int mStatusId;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private boolean isSelected;

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
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

}
