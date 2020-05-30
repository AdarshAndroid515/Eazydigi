package com.app.eazydigi.model.new_model.response;

import java.util.List;

public class HomeworkDashboardStudentResponse {

    /**
     * status : 1
     * message : Success!!
     * data : {"needToReworkHomework":2,"pending":4,"myOverDueHm":2,"myHomeworkDetailList":[{"description":"asdasdasd12","status":1,"firstName":null,"lastName":null,"header":"dasdasd12","rollNumber":null,"studId":573,"dueDate":"2020-04-24","subject":"1","schoolId":null,"homeworkId":5,"teacherFName":"anand","teacherLName":"sinha","admissionNum":null,"classNum":null,"section":null},{"description":"read chapter 1 of bio","status":2,"firstName":null,"lastName":null,"header":"Biology","rollNumber":null,"studId":573,"dueDate":"2020-05-18","subject":"2","schoolId":null,"homeworkId":17,"teacherFName":"anand","teacherLName":"sinha","admissionNum":null,"classNum":null,"section":null},{"description":"Chapter 1","status":2,"firstName":null,"lastName":null,"header":"Chapter 1","rollNumber":null,"studId":573,"dueDate":"2020-05-26","subject":"2","schoolId":null,"homeworkId":19,"teacherFName":"anand","teacherLName":"sinha","admissionNum":null,"classNum":null,"section":null},{"description":"grammer","status":2,"firstName":null,"lastName":null,"header":"learn englishh","rollNumber":null,"studId":573,"dueDate":"2020-05-31","subject":"1","schoolId":null,"homeworkId":20,"teacherFName":"anand","teacherLName":"sinha","admissionNum":null,"classNum":null,"section":null},{"description":"question and answer","status":2,"firstName":null,"lastName":null,"header":"learn chapter 1","rollNumber":null,"studId":573,"dueDate":"2020-05-28","subject":"3","schoolId":null,"homeworkId":21,"teacherFName":"anand","teacherLName":"sinha","admissionNum":null,"classNum":null,"section":null},{"description":"lesson 1 ","status":7,"firstName":null,"lastName":null,"header":"lesson 1 ","rollNumber":null,"studId":573,"dueDate":"2020-05-05","subject":"2","schoolId":null,"homeworkId":16,"teacherFName":"anand","teacherLName":"sinha","admissionNum":null,"classNum":null,"section":null},{"description":"Finish Algebra","status":7,"firstName":null,"lastName":null,"header":"Finish Algebra","rollNumber":null,"studId":573,"dueDate":"2020-05-24","subject":"4","schoolId":null,"homeworkId":18,"teacherFName":"anand","teacherLName":"sinha","admissionNum":null,"classNum":null,"section":null}],"allMyHomework":7,"completedHomework":1}
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
         * needToReworkHomework : 2
         * pending : 4
         * myOverDueHm : 2
         * myHomeworkDetailList : [{"description":"asdasdasd12","status":1,"firstName":null,"lastName":null,"header":"dasdasd12","rollNumber":null,"studId":573,"dueDate":"2020-04-24","subject":"1","schoolId":null,"homeworkId":5,"teacherFName":"anand","teacherLName":"sinha","admissionNum":null,"classNum":null,"section":null},{"description":"read chapter 1 of bio","status":2,"firstName":null,"lastName":null,"header":"Biology","rollNumber":null,"studId":573,"dueDate":"2020-05-18","subject":"2","schoolId":null,"homeworkId":17,"teacherFName":"anand","teacherLName":"sinha","admissionNum":null,"classNum":null,"section":null},{"description":"Chapter 1","status":2,"firstName":null,"lastName":null,"header":"Chapter 1","rollNumber":null,"studId":573,"dueDate":"2020-05-26","subject":"2","schoolId":null,"homeworkId":19,"teacherFName":"anand","teacherLName":"sinha","admissionNum":null,"classNum":null,"section":null},{"description":"grammer","status":2,"firstName":null,"lastName":null,"header":"learn englishh","rollNumber":null,"studId":573,"dueDate":"2020-05-31","subject":"1","schoolId":null,"homeworkId":20,"teacherFName":"anand","teacherLName":"sinha","admissionNum":null,"classNum":null,"section":null},{"description":"question and answer","status":2,"firstName":null,"lastName":null,"header":"learn chapter 1","rollNumber":null,"studId":573,"dueDate":"2020-05-28","subject":"3","schoolId":null,"homeworkId":21,"teacherFName":"anand","teacherLName":"sinha","admissionNum":null,"classNum":null,"section":null},{"description":"lesson 1 ","status":7,"firstName":null,"lastName":null,"header":"lesson 1 ","rollNumber":null,"studId":573,"dueDate":"2020-05-05","subject":"2","schoolId":null,"homeworkId":16,"teacherFName":"anand","teacherLName":"sinha","admissionNum":null,"classNum":null,"section":null},{"description":"Finish Algebra","status":7,"firstName":null,"lastName":null,"header":"Finish Algebra","rollNumber":null,"studId":573,"dueDate":"2020-05-24","subject":"4","schoolId":null,"homeworkId":18,"teacherFName":"anand","teacherLName":"sinha","admissionNum":null,"classNum":null,"section":null}]
         * allMyHomework : 7
         * completedHomework : 1
         */

        private int needToReworkHomework;
        private int pending;
        private int myOverDueHm;
        private int allMyHomework;
        private int completedHomework;
        private List<MyHomeworkDetailListBean> myHomeworkDetailList;

        public int getNeedToReworkHomework() {
            return needToReworkHomework;
        }

        public void setNeedToReworkHomework(int needToReworkHomework) {
            this.needToReworkHomework = needToReworkHomework;
        }

        public int getPending() {
            return pending;
        }

        public void setPending(int pending) {
            this.pending = pending;
        }

        public int getMyOverDueHm() {
            return myOverDueHm;
        }

        public void setMyOverDueHm(int myOverDueHm) {
            this.myOverDueHm = myOverDueHm;
        }

        public int getAllMyHomework() {
            return allMyHomework;
        }

        public void setAllMyHomework(int allMyHomework) {
            this.allMyHomework = allMyHomework;
        }

        public int getCompletedHomework() {
            return completedHomework;
        }

        public void setCompletedHomework(int completedHomework) {
            this.completedHomework = completedHomework;
        }

        public List<MyHomeworkDetailListBean> getMyHomeworkDetailList() {
            return myHomeworkDetailList;
        }

        public void setMyHomeworkDetailList(List<MyHomeworkDetailListBean> myHomeworkDetailList) {
            this.myHomeworkDetailList = myHomeworkDetailList;
        }

        public static class MyHomeworkDetailListBean {
            /**
             * description : asdasdasd12
             * status : 1
             * firstName : null
             * lastName : null
             * header : dasdasd12
             * rollNumber : null
             * studId : 573
             * dueDate : 2020-04-24
             * subject : 1
             * schoolId : null
             * homeworkId : 5
             * teacherFName : anand
             * teacherLName : sinha
             * admissionNum : null
             * classNum : null
             * section : null
             */

            private String description;
            private int status;
            private Object firstName;
            private Object lastName;
            private String header;
            private Object rollNumber;
            private int studId;
            private String dueDate;
            private String subject;
            private Object schoolId;
            private int homeworkId;
            private String teacherFName;
            private String teacherLName;
            private Object admissionNum;
            private Object classNum;
            private Object section;

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

            public Object getFirstName() {
                return firstName;
            }

            public void setFirstName(Object firstName) {
                this.firstName = firstName;
            }

            public Object getLastName() {
                return lastName;
            }

            public void setLastName(Object lastName) {
                this.lastName = lastName;
            }

            public String getHeader() {
                return header;
            }

            public void setHeader(String header) {
                this.header = header;
            }

            public Object getRollNumber() {
                return rollNumber;
            }

            public void setRollNumber(Object rollNumber) {
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

            public String getTeacherFName() {
                return teacherFName;
            }

            public void setTeacherFName(String teacherFName) {
                this.teacherFName = teacherFName;
            }

            public String getTeacherLName() {
                return teacherLName;
            }

            public void setTeacherLName(String teacherLName) {
                this.teacherLName = teacherLName;
            }

            public Object getAdmissionNum() {
                return admissionNum;
            }

            public void setAdmissionNum(Object admissionNum) {
                this.admissionNum = admissionNum;
            }

            public Object getClassNum() {
                return classNum;
            }

            public void setClassNum(Object classNum) {
                this.classNum = classNum;
            }

            public Object getSection() {
                return section;
            }

            public void setSection(Object section) {
                this.section = section;
            }
        }
    }
}
