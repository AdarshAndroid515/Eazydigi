package com.app.eazydigi.model.new_model.response;

import java.util.List;

public class AttendanceStudentResponse {

    /**
     * status : 0
     * message : Success!!
     * data : [{"day":"2020-05-19","status":0,"firstName":"AARAW","lastName":"TIWARI","userId":622,"rollNumber":109,"isClassTech":null,"assignedClass":null,"assignedSection":null,"staffId":null,"studId":573,"schoolId":5,"notes":"","createdBy":null,"createdDate":null,"modifiedBy":null,"modifiedDate":null,"id":11},{"day":"2020-05-23","status":2,"firstName":"AARAW","lastName":"TIWARI","userId":622,"rollNumber":109,"isClassTech":null,"assignedClass":null,"assignedSection":null,"staffId":null,"studId":573,"schoolId":5,"notes":null,"createdBy":null,"createdDate":null,"modifiedBy":null,"modifiedDate":null,"id":46},{"day":"2020-05-26","status":1,"firstName":"AARAW","lastName":"TIWARI","userId":622,"rollNumber":109,"isClassTech":null,"assignedClass":null,"assignedSection":null,"staffId":null,"studId":573,"schoolId":5,"notes":null,"createdBy":null,"createdDate":null,"modifiedBy":null,"modifiedDate":null,"id":81}]
     */

    private int status;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * day : 2020-05-19
         * status : 0
         * firstName : AARAW
         * lastName : TIWARI
         * userId : 622
         * rollNumber : 109
         * isClassTech : null
         * assignedClass : null
         * assignedSection : null
         * staffId : null
         * studId : 573
         * schoolId : 5
         * notes :
         * createdBy : null
         * createdDate : null
         * modifiedBy : null
         * modifiedDate : null
         * id : 11
         */

        private String day;
        private int status;
        private String firstName;
        private String lastName;
        private int userId;
        private int rollNumber;
        private Object isClassTech;
        private Object assignedClass;
        private Object assignedSection;
        private Object staffId;
        private int studId;
        private int schoolId;
        private String notes;
        private Object createdBy;
        private Object createdDate;
        private Object modifiedBy;
        private Object modifiedDate;
        private int id;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getRollNumber() {
            return rollNumber;
        }

        public void setRollNumber(int rollNumber) {
            this.rollNumber = rollNumber;
        }

        public Object getIsClassTech() {
            return isClassTech;
        }

        public void setIsClassTech(Object isClassTech) {
            this.isClassTech = isClassTech;
        }

        public Object getAssignedClass() {
            return assignedClass;
        }

        public void setAssignedClass(Object assignedClass) {
            this.assignedClass = assignedClass;
        }

        public Object getAssignedSection() {
            return assignedSection;
        }

        public void setAssignedSection(Object assignedSection) {
            this.assignedSection = assignedSection;
        }

        public Object getStaffId() {
            return staffId;
        }

        public void setStaffId(Object staffId) {
            this.staffId = staffId;
        }

        public int getStudId() {
            return studId;
        }

        public void setStudId(int studId) {
            this.studId = studId;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public Object getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Object createdBy) {
            this.createdBy = createdBy;
        }

        public Object getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(Object createdDate) {
            this.createdDate = createdDate;
        }

        public Object getModifiedBy() {
            return modifiedBy;
        }

        public void setModifiedBy(Object modifiedBy) {
            this.modifiedBy = modifiedBy;
        }

        public Object getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(Object modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
