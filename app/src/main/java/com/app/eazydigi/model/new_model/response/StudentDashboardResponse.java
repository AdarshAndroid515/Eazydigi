package com.app.eazydigi.model.new_model.response;

import java.util.List;

public class StudentDashboardResponse {

    /**
     * status : 0
     * message : Success!!
     * data : {"notices":null,"totalFeesPending":0,"myAbsent":1,"totalFeesPaid":7200,"myPercentage":0,"myPresent":1,"circulars":null}
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
         * notices : null
         * totalFeesPending : 0.0
         * myAbsent : 1
         * totalFeesPaid : 7200
         * myPercentage : 0.0
         * myPresent : 1
         * circulars : null
         */

        private List<String> notices;
        private double totalFeesPending;
        private int myAbsent;
        private int totalFeesPaid;
        private double myPercentage;
        private int myPresent;
        private List<String>  circulars;

        public List<String>  getNotices() {
            return notices;
        }

        public void setNotices(List<String>  notices) {
            this.notices = notices;
        }

        public double getTotalFeesPending() {
            return totalFeesPending;
        }

        public void setTotalFeesPending(double totalFeesPending) {
            this.totalFeesPending = totalFeesPending;
        }

        public int getMyAbsent() {
            return myAbsent;
        }

        public void setMyAbsent(int myAbsent) {
            this.myAbsent = myAbsent;
        }

        public int getTotalFeesPaid() {
            return totalFeesPaid;
        }

        public void setTotalFeesPaid(int totalFeesPaid) {
            this.totalFeesPaid = totalFeesPaid;
        }

        public double getMyPercentage() {
            return myPercentage;
        }

        public void setMyPercentage(double myPercentage) {
            this.myPercentage = myPercentage;
        }

        public int getMyPresent() {
            return myPresent;
        }

        public void setMyPresent(int myPresent) {
            this.myPresent = myPresent;
        }

        public List<String>  getCirculars() {
            return circulars;
        }

        public void setCirculars(List<String>  circulars) {
            this.circulars = circulars;
        }
    }
}
