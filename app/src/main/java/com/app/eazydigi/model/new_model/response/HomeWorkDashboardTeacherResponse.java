package com.app.eazydigi.model.new_model.response;

import java.util.List;

public class HomeWorkDashboardTeacherResponse {

    /**
     * status : 1
     * message : Success!!
     * data : {"allStudHomework":9,"pendingHomework":2,"studentList":[{"description":"learn constitution","status":1,"firstName":"AADARSH","lastName":"KUMAR","header":"learn civics","rollNumber":103,"studId":567,"dueDate":"2020-05-03","subject":"3","schoolId":null,"homeworkId":13,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010003","classNum":-1,"section":"1"},{"description":"learn constitution","status":7,"firstName":"AADITI","lastName":"KUMARI","header":"learn civics","rollNumber":104,"studId":568,"dueDate":"2020-05-03","subject":"3","schoolId":null,"homeworkId":14,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010004","classNum":2,"section":"2"},{"description":"learn constitution","status":2,"firstName":"AADITYA","lastName":"KUMAR","header":"learn civics","rollNumber":105,"studId":569,"dueDate":"2020-05-03","subject":"3","schoolId":null,"homeworkId":15,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010005","classNum":-2,"section":"2"},{"description":"asdasdasd12","status":1,"firstName":"AARAW","lastName":"TIWARI","header":"dasdasd12","rollNumber":109,"studId":573,"dueDate":"2020-04-24","subject":"1","schoolId":null,"homeworkId":5,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010009","classNum":1,"section":"1"},{"description":"lesson 1 ","status":7,"firstName":"AARAW","lastName":"TIWARI","header":"lesson 1 ","rollNumber":109,"studId":573,"dueDate":"2020-05-05","subject":"2","schoolId":null,"homeworkId":16,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010009","classNum":1,"section":"1"},{"description":"read chapter 1 of bio","status":2,"firstName":"AARAW","lastName":"TIWARI","header":"Biology","rollNumber":109,"studId":573,"dueDate":"2020-05-18","subject":"2","schoolId":null,"homeworkId":17,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010009","classNum":1,"section":"1"},{"description":"Finish Algebra","status":7,"firstName":"AARAW","lastName":"TIWARI","header":"Finish Algebra","rollNumber":109,"studId":573,"dueDate":"2020-05-24","subject":"4","schoolId":null,"homeworkId":18,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010009","classNum":1,"section":"1"},{"description":"dfsdf","status":1,"firstName":"ADITYA","lastName":"KUMAR","header":"asfasf","rollNumber":141,"studId":605,"dueDate":"2020-04-25","subject":"2","schoolId":null,"homeworkId":6,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010041","classNum":1,"section":"1"},{"description":"asdasd","status":1,"firstName":"AFRINA","lastName":"KHATOON","header":"holiday hw","rollNumber":147,"studId":611,"dueDate":"2020-04-23","subject":"2","schoolId":null,"homeworkId":11,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010047","classNum":1,"section":"1"}],"needToRework":3,"overDueHomework":0,"completed":4}
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
         * allStudHomework : 9
         * pendingHomework : 2
         * studentList : [{"description":"learn constitution","status":1,"firstName":"AADARSH","lastName":"KUMAR","header":"learn civics","rollNumber":103,"studId":567,"dueDate":"2020-05-03","subject":"3","schoolId":null,"homeworkId":13,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010003","classNum":-1,"section":"1"},{"description":"learn constitution","status":7,"firstName":"AADITI","lastName":"KUMARI","header":"learn civics","rollNumber":104,"studId":568,"dueDate":"2020-05-03","subject":"3","schoolId":null,"homeworkId":14,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010004","classNum":2,"section":"2"},{"description":"learn constitution","status":2,"firstName":"AADITYA","lastName":"KUMAR","header":"learn civics","rollNumber":105,"studId":569,"dueDate":"2020-05-03","subject":"3","schoolId":null,"homeworkId":15,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010005","classNum":-2,"section":"2"},{"description":"asdasdasd12","status":1,"firstName":"AARAW","lastName":"TIWARI","header":"dasdasd12","rollNumber":109,"studId":573,"dueDate":"2020-04-24","subject":"1","schoolId":null,"homeworkId":5,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010009","classNum":1,"section":"1"},{"description":"lesson 1 ","status":7,"firstName":"AARAW","lastName":"TIWARI","header":"lesson 1 ","rollNumber":109,"studId":573,"dueDate":"2020-05-05","subject":"2","schoolId":null,"homeworkId":16,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010009","classNum":1,"section":"1"},{"description":"read chapter 1 of bio","status":2,"firstName":"AARAW","lastName":"TIWARI","header":"Biology","rollNumber":109,"studId":573,"dueDate":"2020-05-18","subject":"2","schoolId":null,"homeworkId":17,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010009","classNum":1,"section":"1"},{"description":"Finish Algebra","status":7,"firstName":"AARAW","lastName":"TIWARI","header":"Finish Algebra","rollNumber":109,"studId":573,"dueDate":"2020-05-24","subject":"4","schoolId":null,"homeworkId":18,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010009","classNum":1,"section":"1"},{"description":"dfsdf","status":1,"firstName":"ADITYA","lastName":"KUMAR","header":"asfasf","rollNumber":141,"studId":605,"dueDate":"2020-04-25","subject":"2","schoolId":null,"homeworkId":6,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010041","classNum":1,"section":"1"},{"description":"asdasd","status":1,"firstName":"AFRINA","lastName":"KHATOON","header":"holiday hw","rollNumber":147,"studId":611,"dueDate":"2020-04-23","subject":"2","schoolId":null,"homeworkId":11,"teacherFName":null,"teacherLName":null,"admissionNum":"stud010047","classNum":1,"section":"1"}]
         * needToRework : 3
         * overDueHomework : 0
         * completed : 4
         */

        private int allStudHomework;
        private int pendingHomework;
        private int needToRework;
        private int overDueHomework;
        private int completed;
        private List<StudentListBean> studentList;

        public int getAllStudHomework() {
            return allStudHomework;
        }

        public void setAllStudHomework(int allStudHomework) {
            this.allStudHomework = allStudHomework;
        }

        public int getPendingHomework() {
            return pendingHomework;
        }

        public void setPendingHomework(int pendingHomework) {
            this.pendingHomework = pendingHomework;
        }

        public int getNeedToRework() {
            return needToRework;
        }

        public void setNeedToRework(int needToRework) {
            this.needToRework = needToRework;
        }

        public int getOverDueHomework() {
            return overDueHomework;
        }

        public void setOverDueHomework(int overDueHomework) {
            this.overDueHomework = overDueHomework;
        }

        public int getCompleted() {
            return completed;
        }

        public void setCompleted(int completed) {
            this.completed = completed;
        }

        public List<StudentListBean> getStudentList() {
            return studentList;
        }

        public void setStudentList(List<StudentListBean> studentList) {
            this.studentList = studentList;
        }

        public static class StudentListBean {
            /**
             * description : learn constitution
             * status : 1
             * firstName : AADARSH
             * lastName : KUMAR
             * header : learn civics
             * rollNumber : 103
             * studId : 567
             * dueDate : 2020-05-03
             * subject : 3
             * schoolId : null
             * homeworkId : 13
             * teacherFName : null
             * teacherLName : null
             * admissionNum : stud010003
             * classNum : -1
             * section : 1
             */

            private String description;
            private int status;
            private String firstName;
            private String lastName;
            private String header;
            private int rollNumber;
            private int studId;
            private String dueDate;
            private String subject;
            private Object schoolId;
            private int homeworkId;
            private Object teacherFName;
            private Object teacherLName;
            private String admissionNum;
            private int classNum;
            private String section;

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

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }

            public int getRollNumber() {
                return rollNumber;
            }

            public void setRollNumber(int rollNumber) {
                this.rollNumber = rollNumber;
            }

            public int getStudId() {
                return studId;
            }

            public void setStudId(int studId) {
                this.studId = studId;
            }

            public String getDueDate() {
                return dueDate;
            }

            public void setDueDate(String dueDate) {
                this.dueDate = dueDate;
            }

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public Object getSchoolId() {
                return schoolId;
            }

            public void setSchoolId(Object schoolId) {
                this.schoolId = schoolId;
            }

            public int getHomeworkId() {
                return homeworkId;
            }

            public void setHomeworkId(int homeworkId) {
                this.homeworkId = homeworkId;
            }

            public Object getTeacherFName() {
                return teacherFName;
            }

            public void setTeacherFName(Object teacherFName) {
                this.teacherFName = teacherFName;
            }

            public Object getTeacherLName() {
                return teacherLName;
            }

            public void setTeacherLName(Object teacherLName) {
                this.teacherLName = teacherLName;
            }

            public String getAdmissionNum() {
                return admissionNum;
            }

            public void setAdmissionNum(String admissionNum) {
                this.admissionNum = admissionNum;
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
}
