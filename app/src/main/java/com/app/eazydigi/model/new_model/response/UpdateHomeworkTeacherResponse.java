package com.app.eazydigi.model.new_model.response;

public class UpdateHomeworkTeacherResponse {

    /**
     * status : 1
     * message : Homework detail updated successfully.
     * data : {"homeworkId":13,"schoolId":5,"studId":567,"subject":"3","dueDate":"2020-05-03","header":"learn civics","description":"learn constitution","status":2,"studIdList":null}
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
         * homeworkId : 13
         * schoolId : 5
         * studId : 567
         * subject : 3
         * dueDate : 2020-05-03
         * header : learn civics
         * description : learn constitution
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
