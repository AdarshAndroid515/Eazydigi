package com.app.eazydigi.model.new_model.response;

import java.util.List;

public class LeaveRequestListStudentResponse {

    /**
     * status : 1
     * message : Success!!
     * data : [{"comment":"Approved","description":"Personal Leave","status":1,"firstName":"AARAW","lastName":"TIWARI","leaveSub":"Personal Leave","numOfLeave":null,"studId":573,"startDate":"2020-05-28","endDate":"2020-05-29","leaveId":8,"classNum":1,"section":"1"},{"comment":"Approved","description":"My son is on sick leave tomorrow. Doctor advised to take rest for one day.","status":1,"firstName":"AARAW","lastName":"TIWARI","leaveSub":"Sick Leave","numOfLeave":null,"studId":573,"startDate":"2020-05-28","endDate":"2020-05-29","leaveId":7,"classNum":1,"section":"1"},{"comment":"rejected","description":"leave","status":2,"firstName":"AARAW","lastName":"TIWARI","leaveSub":"family function","numOfLeave":null,"studId":573,"startDate":"2020-05-03","endDate":"2020-05-08","leaveId":5,"classNum":1,"section":"1"}]
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
         * comment : Approved
         * description : Personal Leave
         * status : 1
         * firstName : AARAW
         * lastName : TIWARI
         * leaveSub : Personal Leave
         * numOfLeave : null
         * studId : 573
         * startDate : 2020-05-28
         * endDate : 2020-05-29
         * leaveId : 8
         * classNum : 1
         * section : 1
         */

        private String comment;
        private String description;
        private int status;
        private String firstName;
        private String lastName;
        private String leaveSub;
        private Object numOfLeave;
        private int studId;
        private String startDate;
        private String endDate;
        private int leaveId;
        private int classNum;
        private String section;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public String getLeaveSub() {
            return leaveSub;
        }

        public void setLeaveSub(String leaveSub) {
            this.leaveSub = leaveSub;
        }

        public Object getNumOfLeave() {
            return numOfLeave;
        }

        public void setNumOfLeave(Object numOfLeave) {
            this.numOfLeave = numOfLeave;
        }

        public int getStudId() {
            return studId;
        }

        public void setStudId(int studId) {
            this.studId = studId;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public int getLeaveId() {
            return leaveId;
        }

        public void setLeaveId(int leaveId) {
            this.leaveId = leaveId;
        }

        public int getClassNum() {
            return classNum;
        }

        public void setClassNum(int classNum) {
            this.classNum = classNum;
        }

        public String getSection() {
            return section;
        }

        public void setSection(String section) {
            this.section = section;
        }
    }
}
