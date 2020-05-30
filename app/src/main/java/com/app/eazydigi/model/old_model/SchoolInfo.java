package com.app.eazydigi.model.old_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SchoolInfo implements Serializable {

    @SerializedName("connectionString")
    private String connectionString;

    @SerializedName("affiliationNo")
    private String affiliationNo;

    @SerializedName("address")
    private String address;

    @SerializedName("databaseName")
    private String databaseName;

    @SerializedName("schoolFullName")
    private String schoolFullName;

    @SerializedName("schoolNo")
    private String schoolNo;

    @SerializedName("contactNumber")
    private String contactNumber;

    @SerializedName("logo")
    private String logo;

    @SerializedName("id")
    private int id;

    @SerializedName("schoolName")
    private String schoolName;

    @SerializedName("fax")
    private String fax;

    @SerializedName("dataSource")
    private String dataSource;

    @SerializedName("email")
    private String email;

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getAffiliationNo() {
        return affiliationNo;
    }

    public void setAffiliationNo(String affiliationNo) {
        this.affiliationNo = affiliationNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getSchoolFullName() {
        return schoolFullName;
    }

    public void setSchoolFullName(String schoolFullName) {
        this.schoolFullName = schoolFullName;
    }

    public String getSchoolNo() {
        return schoolNo;
    }

    public void setSchoolNo(String schoolNo) {
        this.schoolNo = schoolNo;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}