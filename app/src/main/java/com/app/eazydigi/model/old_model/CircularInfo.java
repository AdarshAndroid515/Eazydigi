package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CircularInfo implements Serializable {

	@SerializedName("boardType")
	private int boardType;

	@SerializedName("activeStatus")
	private boolean activeStatus;

	@SerializedName("boardDescription")
	private String boardDescription;

	@SerializedName("id")
	private int id;

	@SerializedName("boardImage")
	private String boardImage;

	@SerializedName("rowNumber")
	private int rowNumber;

	@SerializedName("totalCount")
	private int totalCount;

	@SerializedName("boardName")
	private String boardName;

	@SerializedName("boardDate")
	private String boardDate;

	public void setBoardType(int boardType){
		this.boardType = boardType;
	}

	public int getBoardType(){
		return boardType;
	}

	public void setActiveStatus(boolean activeStatus){
		this.activeStatus = activeStatus;
	}

	public boolean isActiveStatus(){
		return activeStatus;
	}

	public void setBoardDescription(String boardDescription){
		this.boardDescription = boardDescription;
	}

	public String getBoardDescription(){
		return boardDescription;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setBoardImage(String boardImage){
		this.boardImage = boardImage;
	}

	public String getBoardImage(){
		return boardImage;
	}

	public void setRowNumber(int rowNumber){
		this.rowNumber = rowNumber;
	}

	public int getRowNumber(){
		return rowNumber;
	}

	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
	}

	public int getTotalCount(){
		return totalCount;
	}

	public void setBoardName(String boardName){
		this.boardName = boardName;
	}

	public String getBoardName(){
		return boardName;
	}

	public void setBoardDate(String boardDate){
		this.boardDate = boardDate;
	}

	public String getBoardDate(){
		return boardDate;
	}
}