package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Attendance implements Serializable {

	@SerializedName("dayName")
	private String dayName;

	@SerializedName("attendanceStatusName")
	private String attendanceStatusName;

	@SerializedName("attendanceStatusReason")
	private String attendanceStatusReason;

	@SerializedName("id")
	private int id;

	@SerializedName("day")
	private int day;

	@SerializedName("attendanceStatus")
	private int attendanceStatus;

	public void setDayName(String dayName){
		this.dayName = dayName;
	}

	public String getDayName(){
		return dayName;
	}

	public void setAttendanceStatusName(String attendanceStatusName){
		this.attendanceStatusName = attendanceStatusName;
	}

	public String getAttendanceStatusName(){
		return attendanceStatusName;
	}

	public void setAttendanceStatusReason(String attendanceStatusReason){
		this.attendanceStatusReason = attendanceStatusReason;
	}

	public String getAttendanceStatusReason(){
		return attendanceStatusReason;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDay(int day){
		this.day = day;
	}

	public int getDay(){
		return day;
	}

	public void setAttendanceStatus(int attendanceStatus){
		this.attendanceStatus = attendanceStatus;
	}

	public int getAttendanceStatus(){
		return attendanceStatus;
	}
}