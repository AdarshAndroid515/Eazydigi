package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExpenseDetail implements Serializable {

    @SerializedName("department")
    private String department;

    @SerializedName("totalLedgerExpense")
    private double totalLedgerExpense;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getTotalLedgerExpense() {
        return totalLedgerExpense;
    }

    public void setTotalLedgerExpense(double totalLedgerExpense) {
        this.totalLedgerExpense = totalLedgerExpense;
    }
}