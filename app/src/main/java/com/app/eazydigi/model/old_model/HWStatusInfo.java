
package com.app.eazydigi.model.old_model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class HWStatusInfo implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("status")
    private String mStatus;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
