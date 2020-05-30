
package com.app.eazydigi.model.old_model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class PaymentHistoryResponseInfo implements Serializable {

    @SerializedName("className")
    private String mClassName;
    @SerializedName("feeMonth")
    private String mFeeMonth;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("invoiceNumber")
    private String mInvoiceNumber;
    @SerializedName("lastName")
    private String mLastName;
    @SerializedName("lateCharges")
    private Float mLateCharges;
    @SerializedName("paidAmount")
    private Float mPaidAmount;
    @SerializedName("amount_to_pay")
    private Float amountToPay;
    @SerializedName("paymentDate")
    private String mPaymentDate;
    @SerializedName("rollNumber")
    private String mRollNumber;
    @SerializedName("schoolId")
    private int mSchoolId;
    @SerializedName("sectionName")
    private String mSectionName;
    @SerializedName("totalCount")
    private int mTotalCount;
    @SerializedName("totaldues")
    private Object mTotaldues;
    @SerializedName("userCode")
    private String mUserCode;

    public String getClassName() {
        return mClassName;
    }

    public void setClassName(String className) {
        mClassName = className;
    }

    public String getFeeMonth() {
        return mFeeMonth;
    }

    public void setFeeMonth(String feeMonth) {
        mFeeMonth = feeMonth;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getInvoiceNumber() {
        return mInvoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        mInvoiceNumber = invoiceNumber;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public Float getLateCharges() {
        return mLateCharges;
    }

    public void setLateCharges(Float lateCharges) {
        mLateCharges = lateCharges;
    }

    public Float getPaidAmount() {
        return mPaidAmount;
    }

    public void setPaidAmount(Float paidAmount) {
        mPaidAmount = paidAmount;
    }

    public Float getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Float amountToPay) {
        this.amountToPay = amountToPay;
    }

    public String getPaymentDate() {
        return mPaymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        mPaymentDate = paymentDate;
    }

    public String getRollNumber() {
        return mRollNumber;
    }

    public void setRollNumber(String rollNumber) {
        mRollNumber = rollNumber;
    }

    public int getSchoolId() {
        return mSchoolId;
    }

    public void setSchoolId(int schoolId) {
        mSchoolId = schoolId;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public void setSectionName(String sectionName) {
        mSectionName = sectionName;
    }

    public int getTotalCount() {
        return mTotalCount;
    }

    public void setTotalCount(int totalCount) {
        mTotalCount = totalCount;
    }

    public Object getTotaldues() {
        return mTotaldues;
    }

    public void setTotaldues(Object totaldues) {
        mTotaldues = totaldues;
    }

    public String getUserCode() {
        return mUserCode;
    }

    public void setUserCode(String userCode) {
        mUserCode = userCode;
    }

}
