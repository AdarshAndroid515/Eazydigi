package com.app.eazydigi.model.new_model.response;

import java.util.List;

public class AssignHomeworkResponse {

    /**
     * status : 1
     * message : Assign Homework is successfully.
     * data : [{"homeworkId":17,"schoolId":5,"studId":573,"subject":"2","dueDate":"2020-05-18","header":"Biology","description":"read chapter 1 of bio","status":2,"studIdList":null}]
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
         * homeworkId : 17
         * schoolId : 5
         * studId : 573
         * subject : 2
         * dueDate : 2020-05-18
         * header : Biology
         * description : read chapter 1 of bio
         * status : 2
         * studIdList : null
         */

        private int homeworkId;
        private int schoolId;
        private int studId;
        private String subject;
        private String dueDate;
        private String header;
        private String description;
        private int status;
        private Object studIdList;

        public int getHomeworkId() {
            return homeworkId;
        }

        public void setHomeworkId(int homeworkId) {
            this.homeworkId = homeworkId;
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

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
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

        public Object getStudIdList() {
            return studIdList;
        }

        public void setStudIdList(Object studIdList) {
            this.studIdList = studIdList;
        }
    }
}
