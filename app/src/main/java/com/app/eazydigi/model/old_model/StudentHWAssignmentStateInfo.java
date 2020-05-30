
package com.app.eazydigi.model.old_model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentHWAssignmentStateInfo implements Serializable {

    @SerializedName("count")
    private Long mCount;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("statusId")
    private Long mStatusId;

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public Long getStatusId() {
        return mStatusId;
    }

    public void setStatusId(Long statusId) {
        mStatusId = statusId;
    }

}
