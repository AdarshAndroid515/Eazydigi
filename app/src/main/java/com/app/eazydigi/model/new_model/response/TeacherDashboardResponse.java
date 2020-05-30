package com.app.eazydigi.model.new_model.response;

import java.util.List;

public class TeacherDashboardResponse {

    /**
     * status : 0
     * message : Success!!
     * data : {"notices":[""],"totalStud":35,"presentStud":64,"absentStud":1,"circulars":[""]}
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
         * notices : [""]
         * totalStud : 35
         * presentStud : 64
         * absentStud : 1
         * circulars : [""]
         */

        private int totalStud;
        private int presentStud;
        private int absentStud;
        private List<String> notices;
        private List<String> circulars;

        public int getTotalStud() {
            return totalStud;
        }

        public void setTotalStud(int totalStud) {
            this.totalStud = totalStud;
        }

        public int getPresentStud() {
            return presentStud;
        }

        public void setPresentStud(int presentStud) {
            this.presentStud = presentStud;
        }

        public int getAbsentStud() {
            return absentStud;
        }

        public void setAbsentStud(int absentStud) {
            this.absentStud = absentStud;
        }

        public List<String> getNotices() {
            return notices;
        }

        public void setNotices(List<String> notices) {
            this.notices = notices;
        }

        public List<String> getCirculars() {
            return circulars;
        }

        public void setCirculars(List<String> circulars) {
            this.circulars = circulars;
        }
    }
}
