package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AttendanceDetailsByClass implements Serializable {

	@SerializedName("classId")
	private int classId;

	@SerializedName("lateMark")
	private int lateMark;

	@SerializedName("totalStudent")
	private int totalStudent;

	@SerializedName("leave")
	private int leave;

	@SerializedName("absent")
	private int absent;

	@SerializedName("className")
	private String className;

	@SerializedName("present")
	private int present;

	public void setClassId(int classId){
		this.classId = classId;
	}

	public int getClassId(){
		return classId;
	}

	public void setLateMark(int lateMark){
		this.lateMark = lateMark;
	}

	public int getLateMark(){
		return lateMark;
	}

	public void setTotalStudent(int totalStudent){
		this.totalStudent = totalStudent;
	}

	public int getTotalStudent(){
		return totalStudent;
	}

	public void setLeave(int leave){
		this.leave = leave;
	}

	public int getLeave(){
		return leave;
	}

	public void setAbsent(int absent){
		this.absent = absent;
	}

	public int getAbsent(){
		return absent;
	}

	public void setClassName(String className){
		this.className = className;
	}

	public String getClassName(){
		return className;
	}

	public void setPresent(int present){
		this.present = present;
	}

	public int getPresent(){
		return present;
	}
}