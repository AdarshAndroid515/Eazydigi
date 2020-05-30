package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TotalPaidFeeForStudentInfo implements Serializable {

    @SerializedName("total_paid_fees")
    private String totalPaidFees;

    public String getTotalPaidFees() {
        return totalPaidFees;
    }

    public void setTotalPaidFees(String totalPaidFees) {
        this.totalPaidFees = totalPaidFees;
    }
}