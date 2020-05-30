
package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class FeePaymentDetailInfo implements Serializable {

    @SerializedName("paymentHistoryResponse")
    private List<PaymentHistoryResponseInfo> mPaymentHistoryResponse;

    @SerializedName("studentPendingFee")
    private StudentPendingFeeDetails mStudentPendingFee;

    public List<PaymentHistoryResponseInfo> getPaymentHistoryResponse() {
        return mPaymentHistoryResponse;
    }

    public void setPaymentHistoryResponse(List<PaymentHistoryResponseInfo> paymentHistoryResponse) {
        mPaymentHistoryResponse = paymentHistoryResponse;
    }

    public StudentPendingFeeDetails getStudentPendingFee() {
        return mStudentPendingFee;
    }

    public void setStudentPendingFee(StudentPendingFeeDetails studentPendingFee) {
        mStudentPendingFee = studentPendingFee;
    }

}
