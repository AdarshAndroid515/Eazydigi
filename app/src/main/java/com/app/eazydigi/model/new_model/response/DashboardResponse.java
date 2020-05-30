package com.app.eazydigi.model.new_model.response;

import java.util.List;

public class DashboardResponse {

    /**
     * status : 0
     * message : Success!!
     * data : {"totalStudent":561,"othersVehicle":3,"upcomingCamps":["Camp  on 20 May : 2020-05-23 : 22:00:00"],"totalVan":1,"absentStud":0,"totalBus":3,"todaysCollection":0,"totalTeachers":2,"notices":["",""],"presentStud":30,"totalStaffs":0,"totalExpense":0,"circulars":["",""]}
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
         * totalStudent : 561
         * othersVehicle : 3
         * upcomingCamps : ["Camp  on 20 May : 2020-05-23 : 22:00:00"]
         * totalVan : 1
         * absentStud : 0
         * totalBus : 3
         * todaysCollection : 0.0
         * totalTeachers : 2
         * notices : ["",""]
         * presentStud : 30
         * totalStaffs : 0
         * totalExpense : 0.0
         * circulars : ["",""]
         */

        private int totalStudent;
        private int othersVehicle;
        private int totalVan;
        private int absentStud;
        private int totalBus;
        private double todaysCollection;
        private int totalTeachers;
        private int presentStud;
        private int totalStaffs;
        private double totalExpense;
        private List<String> upcomingCamps;
        private List<String> notices;
        private List<String> circulars;

        public int getTotalStudent() {
            return totalStudent;
        }

        public void setTotalStudent(int totalStudent) {
            this.totalStudent = totalStudent;
        }

        public int getOthersVehicle() {
            return othersVehicle;
        }

        public void setOthersVehicle(int othersVehicle) {
            this.othersVehicle = othersVehicle;
        }

        public int getTotalVan() {
            return totalVan;
        }

        public void setTotalVan(int totalVan) {
            this.totalVan = totalVan;
        }

        public int getAbsentStud() {
            return absentStud;
        }

        public void setAbsentStud(int absentStud) {
            this.absentStud = absentStud;
        }

        public int getTotalBus() {
            return totalBus;
        }

        public void setTotalBus(int totalBus) {
            this.totalBus = totalBus;
        }

        public double getTodaysCollection() {
            return todaysCollection;
        }

        public void setTodaysCollection(double todaysCollection) {
            this.todaysCollection = todaysCollection;
        }

        public int getTotalTeachers() {
            return totalTeachers;
        }

        public void setTotalTeachers(int totalTeachers) {
            this.totalTeachers = totalTeachers;
        }

        public int getPresentStud() {
            return presentStud;
        }

        public void setPresentStud(int presentStud) {
            this.presentStud = presentStud;
        }

        public int getTotalStaffs() {
            return totalStaffs;
        }

        public void setTotalStaffs(int totalStaffs) {
            this.totalStaffs = totalStaffs;
        }

        public double getTotalExpense() {
            return totalExpense;
        }

        public void setTotalExpense(double totalExpense) {
            this.totalExpense = totalExpense;
        }

        public List<String> getUpcomingCamps() {
            return upcomingCamps;
        }

        public void setUpcomingCamps(List<String> upcomingCamps) {
            this.upcomingCamps = upcomingCamps;
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
