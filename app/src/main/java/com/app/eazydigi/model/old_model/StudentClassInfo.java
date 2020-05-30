
package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class StudentClassInfo implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("value")
    private String mValue;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

}
