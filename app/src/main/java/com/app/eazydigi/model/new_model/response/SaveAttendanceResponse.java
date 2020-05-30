package com.app.eazydigi.model.new_model.response;

import java.util.List;

public class SaveAttendanceResponse {

    /**
     * status : 0
     * message : Success!!
     * data : [{"id":11,"schoolId":5,"studId":573,"day":"2020-05-19","status":0,"notes":"","firstName":"AARAW","lastName":"TIWARI","rollNumber":109}]
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
         * id : 11
         * schoolId : 5
         * studId : 573
         * day : 2020-05-19
         * status : 0
         * notes :
         * firstName : AARAW
         * lastName : TIWARI
         * rollNumber : 109
         */

        private int id;
        private int schoolId;
        private int studId;
        private String day;
        private int status;
        private String notes;
        private String firstName;
        private String lastName;
        private int rollNumber;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public int getStudId() {
            return studId;
        }

        public void setStudId(int studId) {
            this.studId = studId;
        }

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

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
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

        public int getRollNumber() {
            return rollNumber;
        }

        public void setRollNumber(int rollNumber) {
            this.rollNumber = rollNumber;
        }
    }
}
