package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StudentDetailInfo implements Serializable {

	@SerializedName("fatherName")
	private Object fatherName;

	@SerializedName("admissionDate")
	private Object admissionDate;

	@SerializedName("rte")
	private Object rte;

	@SerializedName("fatherContactNumber")
	private Object fatherContactNumber;

	@SerializedName("mobileNumber")
	private Object mobileNumber;

	@SerializedName("isStaffWard")
	private boolean isStaffWard;

	@SerializedName("motherName")
	private Object motherName;

	@SerializedName("guardianID")
	private int guardianID;

	@SerializedName("className")
	private String className;

	@SerializedName("studentGroup")
	private Object studentGroup;

	@SerializedName("sectionID")
	private int sectionID;

	@SerializedName("transportBusStop")
	private Object transportBusStop;

	@SerializedName("sectionName")
	private String sectionName;

	@SerializedName("classID")
	private int classID;

	@SerializedName("transportRouteId")
	private Object transportRouteId;

	@SerializedName("isNewAdmission")
	private boolean isNewAdmission;

	@SerializedName("isSiblingConcession")
	private boolean isSiblingConcession;

	@SerializedName("studentName")
	private Object studentName;

	@SerializedName("schoolID")
	private int schoolID;

	@SerializedName("admissionID")
	private String admissionID;

	@SerializedName("rollNumber")
	private String rollNumber;

	@SerializedName("registrationDate")
	private String registrationDate;

	@SerializedName("id")
	private int id;

	@SerializedName("rowNumber")
	private int rowNumber;

	public Object getFatherName() {
		return fatherName;
	}

	public void setFatherName(Object fatherName) {
		this.fatherName = fatherName;
	}

	public Object getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Object admissionDate) {
		this.admissionDate = admissionDate;
	}

	public Object getRte() {
		return rte;
	}

	public void setRte(Object rte) {
		this.rte = rte;
	}

	public Object getFatherContactNumber() {
		return fatherContactNumber;
	}

	public void setFatherContactNumber(Object fatherContactNumber) {
		this.fatherContactNumber = fatherContactNumber;
	}

	public Object getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Object mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isStaffWard() {
		return isStaffWard;
	}

	public void setStaffWard(boolean staffWard) {
		isStaffWard = staffWard;
	}

	public Object getMotherName() {
		return motherName;
	}

	public void setMotherName(Object motherName) {
		this.motherName = motherName;
	}

	public int getGuardianID() {
		return guardianID;
	}

	public void setGuardianID(int guardianID) {
		this.guardianID = guardianID;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Object getStudentGroup() {
		return studentGroup;
	}

	public void setStudentGroup(Object studentGroup) {
		this.studentGroup = studentGroup;
	}

	public int getSectionID() {
		return sectionID;
	}

	public void setSectionID(int sectionID) {
		this.sectionID = sectionID;
	}

	public Object getTransportBusStop() {
		return transportBusStop;
	}

	public void setTransportBusStop(Object transportBusStop) {
		this.transportBusStop = transportBusStop;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}

	public Object getTransportRouteId() {
		return transportRouteId;
	}

	public void setTransportRouteId(Object transportRouteId) {
		this.transportRouteId = transportRouteId;
	}

	public boolean isNewAdmission() {
		return isNewAdmission;
	}

	public void setNewAdmission(boolean newAdmission) {
		isNewAdmission = newAdmission;
	}

	public boolean isSiblingConcession() {
		return isSiblingConcession;
	}

	public void setSiblingConcession(boolean siblingConcession) {
		isSiblingConcession = siblingConcession;
	}

	public Object getStudentName() {
		return studentName;
	}

	public void setStudentName(Object studentName) {
		this.studentName = studentName;
	}

	public int getSchoolID() {
		return schoolID;
	}

	public void setSchoolID(int schoolID) {
		this.schoolID = schoolID;
	}

	public String getAdmissionID() {
		return admissionID;
	}

	public void setAdmissionID(String admissionID) {
		this.admissionID = admissionID;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
}