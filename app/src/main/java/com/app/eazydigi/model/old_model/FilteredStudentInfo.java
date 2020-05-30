
package com.app.eazydigi.model.old_model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FilteredStudentInfo implements Serializable {

    @SerializedName("activeStatus")
    private Boolean mActiveStatus;
    @SerializedName("address")
    private String mAddress;
    @SerializedName("admissionId")
    private String mAdmissionId;
    @SerializedName("assignedClass")
    private Object mAssignedClass;
    @SerializedName("assignedClassID")
    private Long mAssignedClassID;
    @SerializedName("assignedSection")
    private Object mAssignedSection;
    @SerializedName("assignedSectionID")
    private Long mAssignedSectionID;
    @SerializedName("category")
    private String mCategory;
    @SerializedName("classId")
    private Long mClassId;
    @SerializedName("className")
    private String mClassName;
    @SerializedName("dob")
    private String mDob;
    @SerializedName("email")
    private Object mEmail;
    @SerializedName("employeeCategory")
    private Object mEmployeeCategory;
    @SerializedName("fatherFullName")
    private String mFatherFullName;
    @SerializedName("firstName")
    private String mFirstName;
    @SerializedName("gender")
    private String mGender;
    @SerializedName("isSiblingDiscount")
    private Boolean mIsSiblingDiscount;
    @SerializedName("lastName")
    private String mLastName;
    @SerializedName("middleName")
    private String mMiddleName;
    @SerializedName("mobile")
    private String mMobile;
    @SerializedName("motherFullName")
    private String mMotherFullName;
    @SerializedName("parentOccupation")
    private Object mParentOccupation;
    @SerializedName("previousSchoolName")
    private Object mPreviousSchoolName;
    @SerializedName("profileImage")
    private Object mProfileImage;
    @SerializedName("religion")
    private String mReligion;
    @SerializedName("roleName")
    private String mRoleName;
    @SerializedName("rollNumber")
    private String mRollNumber;
    @SerializedName("rowNumber")
    private Long mRowNumber;
    @SerializedName("schoolId")
    private Long mSchoolId;
    @SerializedName("sectionId")
    private Long mSectionId;
    @SerializedName("sectionName")
    private String mSectionName;
    @SerializedName("speciality")
    private Object mSpeciality;
    @SerializedName("staffWard")
    private Boolean mStaffWard;
    @SerializedName("totalCount")
    private Long mTotalCount;
    @SerializedName("userCode")
    private String mUserCode;
    @SerializedName("userFullName")
    private String mUserFullName;
    @SerializedName("userId")
    private Long mUserId;
    @SerializedName("userRoleId")
    private Long mUserRoleId;

    public Boolean getActiveStatus() {
        return mActiveStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        mActiveStatus = activeStatus;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getAdmissionId() {
        return mAdmissionId;
    }

    public void setAdmissionId(String admissionId) {
        mAdmissionId = admissionId;
    }

    public Object getAssignedClass() {
        return mAssignedClass;
    }

    public void setAssignedClass(Object assignedClass) {
        mAssignedClass = assignedClass;
    }

    public Long getAssignedClassID() {
        return mAssignedClassID;
    }

    public void setAssignedClassID(Long assignedClassID) {
        mAssignedClassID = assignedClassID;
    }

    public Object getAssignedSection() {
        return mAssignedSection;
    }

    public void setAssignedSection(Object assignedSection) {
        mAssignedSection = assignedSection;
    }

    public Long getAssignedSectionID() {
        return mAssignedSectionID;
    }

    public void setAssignedSectionID(Long assignedSectionID) {
        mAssignedSectionID = assignedSectionID;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    public Long getClassId() {
        return mClassId;
    }

    public void setClassId(Long classId) {
        mClassId = classId;
    }

    public String getClassName() {
        return mClassName;
    }

    public void setClassName(String className) {
        mClassName = className;
    }

    public String getDob() {
        return mDob;
    }

    public void setDob(String dob) {
        mDob = dob;
    }

    public Object getEmail() {
        return mEmail;
    }

    public void setEmail(Object email) {
        mEmail = email;
    }

    public Object getEmployeeCategory() {
        return mEmployeeCategory;
    }

    public void setEmployeeCategory(Object employeeCategory) {
        mEmployeeCategory = employeeCategory;
    }

    public String getFatherFullName() {
        return mFatherFullName;
    }

    public void setFatherFullName(String fatherFullName) {
        mFatherFullName = fatherFullName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public Boolean getIsSiblingDiscount() {
        return mIsSiblingDiscount;
    }

    public void setIsSiblingDiscount(Boolean isSiblingDiscount) {
        mIsSiblingDiscount = isSiblingDiscount;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getMiddleName() {
        return mMiddleName;
    }

    public void setMiddleName(String middleName) {
        mMiddleName = middleName;
    }

    public String getMobile() {
        return mMobile;
    }

    public void setMobile(String mobile) {
        mMobile = mobile;
    }

    public String getMotherFullName() {
        return mMotherFullName;
    }

    public void setMotherFullName(String motherFullName) {
        mMotherFullName = motherFullName;
    }

    public Object getParentOccupation() {
        return mParentOccupation;
    }

    public void setParentOccupation(Object parentOccupation) {
        mParentOccupation = parentOccupation;
    }

    public Object getPreviousSchoolName() {
        return mPreviousSchoolName;
    }

    public void setPreviousSchoolName(Object previousSchoolName) {
        mPreviousSchoolName = previousSchoolName;
    }

    public Object getProfileImage() {
        return mProfileImage;
    }

    public void setProfileImage(Object profileImage) {
        mProfileImage = profileImage;
    }

    public String getReligion() {
        return mReligion;
    }

    public void setReligion(String religion) {
        mReligion = religion;
    }

    public String getRoleName() {
        return mRoleName;
    }

    public void setRoleName(String roleName) {
        mRoleName = roleName;
    }

    public String getRollNumber() {
        return mRollNumber;
    }

    public void setRollNumber(String rollNumber) {
        mRollNumber = rollNumber;
    }

    public Long getRowNumber() {
        return mRowNumber;
    }

    public void setRowNumber(Long rowNumber) {
        mRowNumber = rowNumber;
    }

    public Long getSchoolId() {
        return mSchoolId;
    }

    public void setSchoolId(Long schoolId) {
        mSchoolId = schoolId;
    }

    public Long getSectionId() {
        return mSectionId;
    }

    public void setSectionId(Long sectionId) {
        mSectionId = sectionId;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public void setSectionName(String sectionName) {
        mSectionName = sectionName;
    }

    public Object getSpeciality() {
        return mSpeciality;
    }

    public void setSpeciality(Object speciality) {
        mSpeciality = speciality;
    }

    public Boolean getStaffWard() {
        return mStaffWard;
    }

    public void setStaffWard(Boolean staffWard) {
        mStaffWard = staffWard;
    }

    public Long getTotalCount() {
        return mTotalCount;
    }

    public void setTotalCount(Long totalCount) {
        mTotalCount = totalCount;
    }

    public String getUserCode() {
        return mUserCode;
    }

    public void setUserCode(String userCode) {
        mUserCode = userCode;
    }

    public String getUserFullName() {
        return mUserFullName;
    }

    public void setUserFullName(String userFullName) {
        mUserFullName = userFullName;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }

    public Long getUserRoleId() {
        return mUserRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        mUserRoleId = userRoleId;
    }

}
