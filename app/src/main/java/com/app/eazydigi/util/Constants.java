package com.app.eazydigi.util;

public class Constants {

    public static final String[] listClassTypes = {"Nursery", "LKG", "UKG", "Class-I", "Class-II", "Class-III", "Class-IV", "Class-V", "Class-VI", "Class-VII", "Class-VIII", "Class-IX", "Class-X", "Class-XI", "Class-XII"};

    public static class PrefKeys {

        public static final String KEY_USERINFO = "user_info";

        public static final String KEY_SCHOOLID = "school_id";
        public static final String KEY_USERID = "user_id";
        public static final String KEY_USER_ROLEID = "user_roleid";

    }

    public static class UserRoles {

        public static final int ROLE_ADMIN = 2;
        public static final int ROLE_TEACHER = 4;
        public static final int ROLE_STUDENT = 6;

    }

    /*MODULES*/
    public static final String TYPE_MODULE_USERS = "Users";
    public static final String TYPE_MODULE_APPLICANTS = "Applicants";
    public static final String TYPE_MODULE_FEE = "Fee";
    public static final String TYPE_MODULE_LEDGER = "Ledger";
    public static final String TYPE_MODULE_MESSENGER = "Messenger";
    public static final String TYPE_MODULE_TRANSPORT = "Transport";
    public static final String TYPE_MODULE_STUDENT_ID_CARD = "Student ID Card";
    public static final String TYPE_MODULE_NOTICE_BOARD = "Notice Board";
    public static final String TYPE_MODULE_LEAVE_REQUEST = "Leave Request";

    public static final String TYPE_MODULE_MARK_ATTENDANCE = "Mark Attendance";
    public static final String TYPE_MODULE_REPORTS = "Reports";
    public static final String TYPE_MODULE_ACADEMIC_REPORTS = "Academic Reports";
    public static final String TYPE_MODULE_HOMEWORK = "Homework";

    public static final String TYPE_MODULE_STUDENT_DETAILS = "My Details";

    public static final String[] listAdminModules = {TYPE_MODULE_USERS, TYPE_MODULE_APPLICANTS, TYPE_MODULE_LEDGER, TYPE_MODULE_NOTICE_BOARD, TYPE_MODULE_MESSENGER, TYPE_MODULE_TRANSPORT, TYPE_MODULE_STUDENT_ID_CARD, TYPE_MODULE_REPORTS, TYPE_MODULE_ACADEMIC_REPORTS,TYPE_MODULE_LEAVE_REQUEST};

    public static final String[] listStudentModules = {TYPE_MODULE_ACADEMIC_REPORTS, TYPE_MODULE_STUDENT_DETAILS, TYPE_MODULE_NOTICE_BOARD,TYPE_MODULE_HOMEWORK,TYPE_MODULE_LEAVE_REQUEST};

    public static final String[] listTeacherModules = {TYPE_MODULE_MARK_ATTENDANCE, TYPE_MODULE_MESSENGER, TYPE_MODULE_REPORTS, TYPE_MODULE_ACADEMIC_REPORTS, TYPE_MODULE_NOTICE_BOARD, TYPE_MODULE_HOMEWORK};

  /*  public static final String[] listAdminModules = {TYPE_MODULE_NOTICE_BOARD, TYPE_MODULE_ACADEMIC_REPORTS,TYPE_MODULE_FEE};

    public static final String[] listStudentModules = {TYPE_MODULE_ACADEMIC_REPORTS, TYPE_MODULE_NOTICE_BOARD,TYPE_MODULE_HOMEWORK};

    public static final String[] listTeacherModules = {TYPE_MODULE_MARK_ATTENDANCE,TYPE_MODULE_ACADEMIC_REPORTS, TYPE_MODULE_NOTICE_BOARD, TYPE_MODULE_HOMEWORK};*/
}