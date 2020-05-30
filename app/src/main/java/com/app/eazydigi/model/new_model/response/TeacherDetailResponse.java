package com.app.eazydigi.model.new_model.response;

public class TeacherDetailResponse {

    /**
     * status : 0
     * message : Success!!
     * data : {"staffUuid":"d197e2f9-b1df-4678-92ba-60f6b71ac0f5","staffId":14,"userId":1188,"schoolId":5,"staffType":null,"isPrincipal":null,"gender":0,"staffImgUrl":null,"speciality":null,"adharCard":null,"dateOfHiring":null,"fatherName":"anand","motherName":null,"motherTounge":null,"contactNum":7720007048,"emergNum":null,"category":null,"religion":null,"bloodGroup":null,"nationality":null,"prevDues":null,"concession":null,"bankAccount":null,"ifsc":null,"currAddress":"sdfsd","premAddress":null,"isClasstech":1,"assignedClass":"1","assignedSection":"1","isDisable":1,"isDelete":0,"emailId":null,"password":null,"userName":null,"phone":null,"firstName":null,"middleName":null,"lastName":null,"dob":null,"roleType":null,"errorFieldDetail":null,"fileLine":null}
     */

    private int status;
    private String message;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * staffUuid : d197e2f9-b1df-4678-92ba-60f6b71ac0f5
         * staffId : 14
         * userId : 1188
         * schoolId : 5
         * staffType : null
         * isPrincipal : null
         * gender : 0
         * staffImgUrl : null
         * speciality : null
         * adharCard : null
         * dateOfHiring : null
         * fatherName : anand
         * motherName : null
         * motherTounge : null
         * contactNum : 7720007048
         * emergNum : null
         * category : null
         * religion : null
         * bloodGroup : null
         * nationality : null
         * prevDues : null
         * concession : null
         * bankAccount : null
         * ifsc : null
         * currAddress : sdfsd
         * premAddress : null
         * isClasstech : 1
         * assignedClass : 1
         * assignedSection : 1
         * isDisable : 1
         * isDelete : 0
         * emailId : null
         * password : null
         * userName : null
         * phone : null
         * firstName : null
         * middleName : null
         * lastName : null
         * dob : null
         * roleType : null
         * errorFieldDetail : null
         * fileLine : null
         */

        private String staffUuid;
        private int staffId;
        private int userId;
        private int schoolId;
        private Object staffType;
        private Object isPrincipal;
        private int gender;
        private Object staffImgUrl;
        private Object speciality;
        private Object adharCard;
        private Object dateOfHiring;
        private String fatherName;
        private Object motherName;
        private Object motherTounge;
        private long contactNum;
        private Object emergNum;
        private Object category;
        private Object religion;
        private Object bloodGroup;
        private Object nationality;
        private Object prevDues;
        private Object concession;
        private Object bankAccount;
        private Object ifsc;
        private String currAddress;
        private Object premAddress;
        private int isClasstech;
        private String assignedClass;
        private String assignedSection;
        private int isDisable;
        private int isDelete;
        private Object emailId;
        private Object password;
        private Object userName;
        private Object phone;
        private Object firstName;
        private Object middleName;
        private Object lastName;
        private Object dob;
        private Object roleType;
        private Object errorFieldDetail;
        private Object fileLine;

        public String getStaffUuid() {
            return staffUuid;
        }

        public void setStaffUuid(String staffUuid) {
            this.staffUuid = staffUuid;
        }

        public int getStaffId() {
            return staffId;
        }

        public void setStaffId(int staffId) {
            this.staffId = staffId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public Object getStaffType() {
            return staffType;
        }

        public void setStaffType(Object staffType) {
            this.staffType = staffType;
        }

        public Object getIsPrincipal() {
            return isPrincipal;
        }

        public void setIsPrincipal(Object isPrincipal) {
            this.isPrincipal = isPrincipal;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public Object getStaffImgUrl() {
            return staffImgUrl;
        }

        public void setStaffImgUrl(Object staffImgUrl) {
            this.staffImgUrl = staffImgUrl;
        }

        public Object getSpeciality() {
            return speciality;
        }

        public void setSpeciality(Object speciality) {
            this.speciality = speciality;
        }

        public Object getAdharCard() {
            return adharCard;
        }

        public void setAdharCard(Object adharCard) {
            this.adharCard = adharCard;
        }

        public Object getDateOfHiring() {
            return dateOfHiring;
        }

        public void setDateOfHiring(Object dateOfHiring) {
            this.dateOfHiring = dateOfHiring;
        }

        public String getFatherName() {
            return fatherName;
        }

        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }

        public Object getMotherName() {
            return motherName;
        }

        public void setMotherName(Object motherName) {
            this.motherName = motherName;
        }

        public Object getMotherTounge() {
            return motherTounge;
        }

        public void setMotherTounge(Object motherTounge) {
            this.motherTounge = motherTounge;
        }

        public long getContactNum() {
            return contactNum;
        }

        public void setContactNum(long contactNum) {
            this.contactNum = contactNum;
        }

        public Object getEmergNum() {
            return emergNum;
        }

        public void setEmergNum(Object emergNum) {
            this.emergNum = emergNum;
        }

        public Object getCategory() {
            return category;
        }

        public void setCategory(Object category) {
            this.category = category;
        }

        public Object getReligion() {
            return religion;
        }

        public void setReligion(Object religion) {
            this.religion = religion;
        }

        public Object getBloodGroup() {
            return bloodGroup;
        }

        public void setBloodGroup(Object bloodGroup) {
            this.bloodGroup = bloodGroup;
        }

        public Object getNationality() {
            return nationality;
        }

        public void setNationality(Object nationality) {
            this.nationality = nationality;
        }

        public Object getPrevDues() {
            return prevDues;
        }

        public void setPrevDues(Object prevDues) {
            this.prevDues = prevDues;
        }

        public Object getConcession() {
            return concession;
        }

        public void setConcession(Object concession) {
            this.concession = concession;
        }

        public Object getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(Object bankAccount) {
            this.bankAccount = bankAccount;
        }

        public Object getIfsc() {
            return ifsc;
        }

        public void setIfsc(Object ifsc) {
            this.ifsc = ifsc;
        }

        public String getCurrAddress() {
            return currAddress;
        }

        public void setCurrAddress(String currAddress) {
            this.currAddress = currAddress;
        }

        public Object getPremAddress() {
            return premAddress;
        }

        public void setPremAddress(Object premAddress) {
            this.premAddress = premAddress;
        }

        public int getIsClasstech() {
            return isClasstech;
        }

        public void setIsClasstech(int isClasstech) {
            this.isClasstech = isClasstech;
        }

        public String getAssignedClass() {
            return assignedClass;
        }

        public void setAssignedClass(String assignedClass) {
            this.assignedClass = assignedClass;
        }

        public String getAssignedSection() {
            return assignedSection;
        }

        public void setAssignedSection(String assignedSection) {
            this.assignedSection = assignedSection;
        }

        public int getIsDisable() {
            return isDisable;
        }

        public void setIsDisable(int isDisable) {
            this.isDisable = isDisable;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public Object getEmailId() {
            return emailId;
        }

        public void setEmailId(Object emailId) {
            this.emailId = emailId;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getFirstName() {
            return firstName;
        }

        public void setFirstName(Object firstName) {
            this.firstName = firstName;
        }

        public Object getMiddleName() {
            return middleName;
        }

        public void setMiddleName(Object middleName) {
            this.middleName = middleName;
        }

        public Object getLastName() {
            return lastName;
        }

        public void setLastName(Object lastName) {
            this.lastName = lastName;
        }

        public Object getDob() {
            return dob;
        }

        public void setDob(Object dob) {
            this.dob = dob;
        }

        public Object getRoleType() {
            return roleType;
        }

        public void setRoleType(Object roleType) {
            this.roleType = roleType;
        }

        public Object getErrorFieldDetail() {
            return errorFieldDetail;
        }

        public void setErrorFieldDetail(Object errorFieldDetail) {
            this.errorFieldDetail = errorFieldDetail;
        }

        public Object getFileLine() {
            return fileLine;
        }

        public void setFileLine(Object fileLine) {
            this.fileLine = fileLine;
        }
    }
}
