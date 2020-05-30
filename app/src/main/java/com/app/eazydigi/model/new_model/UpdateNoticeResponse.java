package com.app.eazydigi.model.new_model;

public class UpdateNoticeResponse {

    /**
     * status : 1
     * message : Notice/Circular updated successfully.
     * data : {"noticeId":8,"schoolId":5,"category":1,"title":"test notice","description":"test notice","startDate":"2020-04-18 00:00:00","isActive":1}
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
         * noticeId : 8
         * schoolId : 5
         * category : 1
         * title : test notice
         * description : test notice
         * startDate : 2020-04-18 00:00:00
         * isActive : 1
         */

        private int noticeId;
        private int schoolId;
        private int category;
        private String title;
        private String description;
        private String startDate;
        private int isActive;

        public int getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public int getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(int schoolId) {
            this.schoolId = schoolId;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public int getIsActive() {
            return isActive;
        }

        public void setIsActive(int isActive) {
            this.isActive = isActive;
        }
    }
}
