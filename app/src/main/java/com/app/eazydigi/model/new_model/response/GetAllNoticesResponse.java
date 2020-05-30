package com.app.eazydigi.model.new_model.response;

import java.util.List;

public class GetAllNoticesResponse {

    /**
     * status : 1
     * message : Success!!
     * data : [{"noticeId":8,"schoolId":5,"category":1,"title":"test notice","description":"test notice","startDate":"2020-04-18 00:00:00","isActive":0},{"noticeId":9,"schoolId":5,"category":1,"title":"19 april notice","description":"corona close ","startDate":"2020-04-19 00:00:00","isActive":1},{"noticeId":10,"schoolId":5,"category":2,"title":"19th april ","description":"corona circular","startDate":"2020-04-19 00:00:00","isActive":1},{"noticeId":11,"schoolId":5,"category":1,"title":"school is closed today","description":"school is closed today","startDate":"2020-05-04 00:00:00","isActive":1},{"noticeId":14,"schoolId":5,"category":1,"title":"demo notice","description":"demo notice","startDate":"2020-05-19 00:00:00","isActive":0},{"noticeId":15,"schoolId":5,"category":1,"title":"Corono Holiday","description":"Corono Holiday","startDate":"2020-05-20 00:00:00","isActive":1},{"noticeId":16,"schoolId":5,"category":1,"title":"11","description":"11","startDate":"2020-05-20 00:00:00","isActive":1}]
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
         * noticeId : 8
         * schoolId : 5
         * category : 1
         * title : test notice
         * description : test notice
         * startDate : 2020-04-18 00:00:00
         * isActive : 0
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
