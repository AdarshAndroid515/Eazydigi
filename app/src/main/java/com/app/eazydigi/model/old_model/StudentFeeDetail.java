package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentFeeDetail implements Serializable {

	@SerializedName("totalFees")
	private double totalFees;

	@SerializedName("totalFeeAndTaxes")
	private double totalFeeAndTaxes;

	@SerializedName("studentRollNumber")
	private String studentRollNumber;

	@SerializedName("section")
	private String section;

	@SerializedName("feeMonth")
	private String feeMonth;

	@SerializedName("studentClass")
	private String studentClass;

	@SerializedName("partialPayment")
	private boolean partialPayment;

	@SerializedName("billingDate")
	private String billingDate;

	@SerializedName("studentName")
	private String studentName;

	@SerializedName("lateCharges")
	private double lateCharges;

	@SerializedName("invoiceNumber")
	private String invoiceNumber;

	@SerializedName("cashierID")
	private int cashierID;

	@SerializedName("rowNumber")
	private int rowNumber;

	public void setTotalFees(double totalFees){
		this.totalFees = totalFees;
	}

	public double getTotalFees(){
		return totalFees;
	}

	public void setTotalFeeAndTaxes(double totalFeeAndTaxes){
		this.totalFeeAndTaxes = totalFeeAndTaxes;
	}

	public double getTotalFeeAndTaxes(){
		return totalFeeAndTaxes;
	}

	public void setStudentRollNumber(String studentRollNumber){
		this.studentRollNumber = studentRollNumber;
	}

	public String getStudentRollNumber(){
		return studentRollNumber;
	}

	public void setSection(String section){
		this.section = section;
	}

	public String getSection(){
		return section;
	}

	public void setFeeMonth(String feeMonth){
		this.feeMonth = feeMonth;
	}

	public String getFeeMonth(){
		return feeMonth;
	}

	public void setStudentClass(String studentClass){
		this.studentClass = studentClass;
	}

	public String getStudentClass(){
		return studentClass;
	}

	public void setPartialPayment(boolean partialPayment){
		this.partialPayment = partialPayment;
	}

	public boolean isPartialPayment(){
		return partialPayment;
	}

	public void setBillingDate(String billingDate){
		this.billingDate = billingDate;
	}

	public String getBillingDate(){
		return billingDate;
	}

	public void setStudentName(String studentName){
		this.studentName = studentName;
	}

	public String getStudentName(){
		return studentName;
	}

	public void setLateCharges(double lateCharges){
		this.lateCharges = lateCharges;
	}

	public double getLateCharges(){
		return lateCharges;
	}

	public void setInvoiceNumber(String invoiceNumber){
		this.invoiceNumber = invoiceNumber;
	}

	public String getInvoiceNumber(){
		return invoiceNumber;
	}

	public void setCashierID(int cashierID){
		this.cashierID = cashierID;
	}

	public int getCashierID(){
		return cashierID;
	}

	public void setRowNumber(int rowNumber){
		this.rowNumber = rowNumber;
	}

	public int getRowNumber(){
		return rowNumber;
	}
}