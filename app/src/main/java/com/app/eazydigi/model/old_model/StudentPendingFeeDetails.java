package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentPendingFeeDetails implements Serializable {

	@SerializedName("fatherName")
	private String fatherName;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("motherName")
	private Object motherName;

	@SerializedName("rollNo")
	private String rollNo;

	@SerializedName("jan")
	private Float jan;

	@SerializedName("feb")
	private Float feb;

	@SerializedName("march")
	private Float march;

	@SerializedName("april")
	private Float april;

	@SerializedName("may")
	private Float may;

	@SerializedName("june")
	private Float june;

	@SerializedName("july")
	private Float july;

	@SerializedName("august")
	private Float august;

	@SerializedName("september")
	private Float september;

	@SerializedName("october")
	private Float october;

	@SerializedName("november")
	private Float november;

	@SerializedName("december")
	private Float december;

	@SerializedName("sNo")
	private int sNo;

	@SerializedName("studentName")
	private String studentName;

	@SerializedName("admissionID")
	private String admissionID;

	@SerializedName("class")
	private String jsonMemberClass;

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Object getMotherName() {
		return motherName;
	}

	public void setMotherName(Object motherName) {
		this.motherName = motherName;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public Float getJan() {
		return jan;
	}

	public void setJan(Float jan) {
		this.jan = jan;
	}

	public Float getFeb() {
		return feb;
	}

	public void setFeb(Float feb) {
		this.feb = feb;
	}

	public Float getMarch() {
		return march;
	}

	public void setMarch(Float march) {
		this.march = march;
	}

	public Float getApril() {
		return april;
	}

	public void setApril(Float april) {
		this.april = april;
	}

	public Float getMay() {
		return may;
	}

	public void setMay(Float may) {
		this.may = may;
	}

	public Float getJune() {
		return june;
	}

	public void setJune(Float june) {
		this.june = june;
	}

	public Float getJuly() {
		return july;
	}

	public void setJuly(Float july) {
		this.july = july;
	}

	public Float getAugust() {
		return august;
	}

	public void setAugust(Float august) {
		this.august = august;
	}

	public Float getSeptember() {
		return september;
	}

	public void setSeptember(Float september) {
		this.september = september;
	}

	public Float getOctober() {
		return october;
	}

	public void setOctober(Float october) {
		this.october = october;
	}

	public Float getNovember() {
		return november;
	}

	public void setNovember(Float november) {
		this.november = november;
	}

	public Float getDecember() {
		return december;
	}

	public void setDecember(Float december) {
		this.december = december;
	}

	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getAdmissionID() {
		return admissionID;
	}

	public void setAdmissionID(String admissionID) {
		this.admissionID = admissionID;
	}

	public String getJsonMemberClass() {
		return jsonMemberClass;
	}

	public void setJsonMemberClass(String jsonMemberClass) {
		this.jsonMemberClass = jsonMemberClass;
	}
}