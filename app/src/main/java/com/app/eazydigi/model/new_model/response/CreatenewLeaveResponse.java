package com.app.eazydigi.model.new_model.response;

public class CreatenewLeaveResponse {

    /**
     * status : 1
     * message : Leave request added successfully.
     * data : {"leaveId":9,"schoolId":5,"studId":573,"leaveSub":"EID","startDate":"2020-05-28","endDate":"2020-05-29","numOfLeave":null,"description":"EID Celebration","comment":"","status":0}
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
         * leaveId : 9
         * schoolId : 5
         * studId : 573
         * leaveSub : EID
         * startDate : 2020-05-28
         * endDate : 2020-05-29
         * numOfLeave : null
         * description : EID Celebration
         * comment :
         * status : 0
         */

        private int leaveId;
        private int schoolId;
        private int studId;
        private String leaveSub;
        private String startDate;
        private String endDate;
        private Object numOfLeave;
        private String description;
        private String comment;
        private int status;

        public int getLeaveId() {
            return leaveId;
        }

        public void setLeaveId(int leaveId) {
            this.leaveId = leaveId;
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

        public String getLeaveSub() {
            return leaveSub;
        }

        public void setLeaveSub(String leaveSub) {
            this.leaveSub = leaveSub;
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

        public Object getNumOfLeave() {
            return numOfLeave;
        }

        public void setNumOfLeave(Object numOfLeave) {
            this.numOfLeave = numOfLeave;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
