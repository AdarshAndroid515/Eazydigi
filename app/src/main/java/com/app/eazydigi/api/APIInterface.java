package com.app.eazydigi.api;

import com.app.eazydigi.model.new_model.UpdateNoticeResponse;
import com.app.eazydigi.model.new_model.request.AssignHomeworkRequest;
import com.app.eazydigi.model.new_model.request.CreateNewLeaveRequestModel;
import com.app.eazydigi.model.new_model.request.CreateNoticeCircularRequest;
import com.app.eazydigi.model.new_model.request.LoginRequest;
import com.app.eazydigi.model.new_model.response.AssignHomeworkResponse;
import com.app.eazydigi.model.new_model.response.AttendanceListResponse;
import com.app.eazydigi.model.new_model.response.AttendanceStudentResponse;
import com.app.eazydigi.model.new_model.response.CreateNoticeCircularResponse;
import com.app.eazydigi.model.new_model.response.CreatenewLeaveResponse;
import com.app.eazydigi.model.new_model.response.DashboardResponse;
import com.app.eazydigi.model.new_model.response.GetAllNoticesResponse;
import com.app.eazydigi.model.new_model.response.HomeWorkDashboardTeacherResponse;
import com.app.eazydigi.model.new_model.response.HomeworkDashboardStudentResponse;
import com.app.eazydigi.model.new_model.response.LeaveRequestListStudentResponse;
import com.app.eazydigi.model.new_model.response.LoginResponse;
import com.app.eazydigi.model.new_model.response.MarkAttendanceListResponse;
import com.app.eazydigi.model.new_model.response.SaveAttendanceResponse;
import com.app.eazydigi.model.new_model.response.StudentDashboardResponse;
import com.app.eazydigi.model.new_model.response.StudentListResponse;
import com.app.eazydigi.model.new_model.response.TeacherDashboardResponse;
import com.app.eazydigi.model.new_model.response.TeacherDetailResponse;
import com.app.eazydigi.model.new_model.response.UpdateHomeworkStudentResponse;
import com.app.eazydigi.model.new_model.response.UpdateHomeworkTeacherResponse;
import com.app.eazydigi.model.new_model.response.UpdateLeaveAdminResponse;
import com.app.eazydigi.model.old_model.AttendanceDetailForStudentInfo;
import com.app.eazydigi.model.old_model.AttendanceDetailsByClass;
import com.app.eazydigi.model.old_model.AttendanceSheetByDateInfo;
import com.app.eazydigi.model.old_model.CircularInfo;
import com.app.eazydigi.model.old_model.CollectFeeDetailnfo;
import com.app.eazydigi.model.old_model.ExpenseDetail;
import com.app.eazydigi.model.old_model.FeePaymentDetailInfo;
import com.app.eazydigi.model.old_model.FilteredStudentInfo;
import com.app.eazydigi.model.old_model.HWStatusInfo;
import com.app.eazydigi.model.old_model.NoticeInfo;
import com.app.eazydigi.model.old_model.ProfileDetailInfo;
import com.app.eazydigi.model.old_model.SchoolInfo;
import com.app.eazydigi.model.old_model.StudentAttendanceReportByMonthInfo;
import com.app.eazydigi.model.old_model.StudentClassInfo;
import com.app.eazydigi.model.old_model.StudentFeeDetail;
import com.app.eazydigi.model.old_model.StudentHWAssignmentByStatusInfo;
import com.app.eazydigi.model.old_model.StudentHWAssignmentStateInfo;
import com.app.eazydigi.model.old_model.StudentPendingFeeDetails;
import com.app.eazydigi.model.old_model.StudentSectionInfo;
import com.app.eazydigi.model.old_model.SubjectInfo;
import com.app.eazydigi.model.old_model.TeacherHWAssignmentByStatusInfo;
import com.app.eazydigi.model.old_model.TeacherHWAssignmentStateInfo;
import com.app.eazydigi.model.old_model.TotalStudentsByClassInfo;
import com.app.eazydigi.model.old_model.TotalUsersDataInfo;
import com.app.eazydigi.model.old_model.VehicleDetail;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface APIInterface {

//    @FormUrlEncoded
//    @POST("UserAccount/AuthenticateUser")
//    Call<LoginResponseInfo> authenticate(@Header("schoolID") String schoolID, @Field("userName") String username, @Field("password") String password);


    /*********************          New  Api               **********************/

    @Headers({"Content-Type: application/json"})
    @POST("login")
    Single<LoginResponse> authenticate(@Body LoginRequest loginRequest);

    @Headers({"Content-Type: application/json"})
    @GET("api/sa/school/school-admin/dashboard")
    Single<DashboardResponse> getDashboard();

    @Headers({"Content-Type: application/json"})
    @GET("api/sa/school/teacher/dashboard")
    Single<TeacherDashboardResponse> getDashboardTeacher();

    @Headers({"Content-Type: application/json"})
    @GET("api/teacher/teacher-detail")
    Single<TeacherDetailResponse> getTeacherDetail();

    @Headers({"Content-Type: application/json"})
    @GET("api/sa/school/student/dashboard")
    Single<StudentDashboardResponse> getDashboardStudent();

    @Headers({"Content-Type: application/json"})
    @GET("api/communication/notices")
    Single<GetAllNoticesResponse> getNoticeList();

    @Headers({"Content-Type: application/json"})
    @POST("api/communication/notice")
    Single<CreateNoticeCircularResponse> createNotice(@Body CreateNoticeCircularRequest createNoticeCircularRequest);

    @Headers({"Content-Type: application/json"})
    @POST("api/communication/notice")
    Single<UpdateNoticeResponse> updateNotice(@Body GetAllNoticesResponse.DataBean dataBean);

    @Headers({"Content-Type: application/json"})
    @GET("api/sa/school/stud-attendance/student/attendances")
    Single<AttendanceListResponse> getAttendanceList1(@Query("class") String classX, @Query("sec") String sec, @Query("attDate") String attDate);

    @Headers({"Content-Type: application/json"})
    @GET("api/sa/school/stud-attendance/student/attendances")
    Single<MarkAttendanceListResponse> getMarkAttendanceList(@Query("class") String classX, @Query("sec") String sec, @Query("attDate") String attDate);

    @Headers({"Content-Type: application/json"})
    @POST("api/sa/school/stud-attendance/student/attendance")
    Single<SaveAttendanceResponse> getSaveAttendanceList(@Body List<MarkAttendanceListResponse.DataBean> dataBeanList);

    @Headers({"Content-Type: application/json"})
    @GET("api/sa/school/teacher/homework/dashboard")
    Single<HomeWorkDashboardTeacherResponse> getHomeWorkDashboardTeacher(@Query("status") String status);

    @Headers({"Content-Type: application/json"})
    @POST("api/sa/school/teacher/assign-homework")
    Single<AssignHomeworkResponse> getAssignHomework(@Body AssignHomeworkRequest assignHomeworkRequest);

    @Headers({"Content-Type: application/json"})
    @GET("api/user/students")
    Single<StudentListResponse> getStudentList(@Query("classId") String classId, @Query("rollNo") String rollNo,
                                               @Query("admissionNo") String admissionNo, @Query("studName") String studName,
                                               @Query("section") String section);

    @Headers({"Content-Type: application/json"})
    @GET("api/sa/school/student/homework/dashboard")
    Single<HomeworkDashboardStudentResponse> getHomeWorkDashboardStudent(@Query("status") String status);

    @Headers({"Content-Type: application/json"})
    @PUT("api/sa/school/student/homework")
    Single<UpdateHomeworkStudentResponse> getUpdateHomeworkStudent(@Body HomeworkDashboardStudentResponse.DataBean.MyHomeworkDetailListBean  myHomeworkDetailListBean);

    @Headers({"Content-Type: application/json"})
    @PUT("api/sa/school/teacher/homework-detail")
    Single<UpdateHomeworkTeacherResponse> getUpdateHomeworkTeacher(@Body HomeWorkDashboardTeacherResponse.DataBean.StudentListBean  myHomeworkDetailListBean);

    @Headers({"Content-Type: application/json"})
    @GET("api/sa/school/stud-attendance/student/attendance-report")
    Single<AttendanceStudentResponse> getAttendanceStudentByDate(@Query("attDate") String attDate);

    @Headers({"Content-Type: application/json"})
    @GET("api/sa/school/leave/requests")
    Single<LeaveRequestListStudentResponse> getLeaveListStudent(@Query("studId") String studId, @Query("flag") String flag);

    @Headers({"Content-Type: application/json"})
    @POST("api/sa/school/leave/request")
    Single<CreatenewLeaveResponse> createNewLeaveRequest(@Body CreateNewLeaveRequestModel createNewLeaveRequest);

    @Headers({"Content-Type: application/json"})
    @PUT("api/sa/school/leave/request")
    Single<UpdateLeaveAdminResponse> updateLeaveRequest(@Body LeaveRequestListStudentResponse.DataBean dataBean);


    /*********************          Old Api               **********************/

    @Headers({"Content-Type: application/json"})
    @POST("UserAccount/RegisterUser")
    Call<ResponseBody> register(@Header("schoolID") String schoolID, @Body JsonObject jsonObject);

    @GET("UserAccount/CheckEmailRegistered")
    Call<Object> checkEmailRegistered(@Header("schoolID") String schoolID, @Query("email") String email);

    @GET("UserAccount/RestorePasswordByEmail")
    Call<Object> restorePasswordByEmail(@Header("schoolID") String schoolID, @Query("email") String email);

    @GET("Common/loadSchoolData")
    Call<SchoolInfo> loadSchoolData(@Header("schoolID") int schoolID, @Query("schoolId") int schoolData);

    @GET("Dashbord/GetTotalUsersData")
    Call<TotalUsersDataInfo> getTotalUsersData(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolData);

    @GET("Dashbord/GetTotalPaidFeeForStudent")
    Call<ResponseBody> getTotalPaidFeeForStudent(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolId, @Query("userId") int userId, @Query("session") int session);

    @GET("Attendance/GetAttendanceDetailForStudent")
    Call<AttendanceDetailForStudentInfo> getAttendanceDetailForStudent(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolId, @Query("userId") int userId, @Query("sessionId") int session);

    @POST("Board/NoticeBoardLists")
    Call<List<NoticeInfo>> getNoticeBoardLists(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Body JsonObject jsonObject);

    @GET("Board/GetCircularBoard")
    Call<List<CircularInfo>> getCircularBoard(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolIDString);

    @GET("Dashbord/GetTodaysTotalFeeCollection")
    Call<List<StudentFeeDetail>> getTodaysTotalFeeCollection(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolIDString, @Query("feeCollectionDate") String feeCollectionDate);

    @GET("Dashbord/GetVehicleDetails")
    Call<List<VehicleDetail>> getVehicleDetails(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolIDString);

    @GET("Dashbord/GetLedgerExpenseTrendByDepartment")
    Call<List<ExpenseDetail>> getLedgerExpenseTrendByDepartment(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolIDString, @Query("paidDate") String paidDate);

    @GET("Dashbord/GetTotalStudentsByClass")
    Call<TotalStudentsByClassInfo> getTotalStudentsByClass(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolData, @Query("classID") int classID, @Query("sectionID") int sectionID);

    @GET("Dashbord/GetTodaysAttendanceData")
    Call<List<AttendanceDetailsByClass>> getTodaysAttendanceData(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolData, @Query("classID") int classID, @Query("sectionID") int sectionID, @Query("attendanceDate") String paidDate);

    @GET("Dashbord/GetPendingFeeForSingleStudent")
    Call<StudentPendingFeeDetails> getPendingFeeForSingleStudent(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolData, @Query("userId") int userId);

    @GET("UserAccount/ProfileDetail")
    Call<ProfileDetailInfo> getProfileDetail(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("userId") int usrId);

    @POST("Board/ManageBoard")
    Call<NoticeInfo> manageBoard(@Header("schoolId") int schoolId, @Header("Authorization") String authorization, @Body JsonObject jsonObject);

    @GET("Attendance/GetAttendanceSheetByDate")
    Call<AttendanceSheetByDateInfo> getAttendanceDetailByDate(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolId, @Query("claasId") int claasId, @Query("sectionId") int sectionId, @Query("attendanceDate") String attendanceDate);

    @POST("Attendance/UpdateAttendanceSheet")
    Call<ResponseBody> updateAttendanceSheet(@Header("schoolId") int schoolId, @Header("Authorization") String authorization, @Query("attendanceDate") String attendanceDate, @Query("userID") int userId, @Body JsonObject jsonObject);

    @GET("Attendance/GetAttendanceByMonthForStudent")
    Call<List<StudentAttendanceReportByMonthInfo>> getAttendanceByMonthForStudent(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolId, @Query("userId") int userId, @Query("monthId") int monthId);

    @GET("Common/Classes")
    Call<List<StudentClassInfo>> getStudentClass(@Header("schoolID") int schoolID, @Header("Authorization") String authorization);

    @GET("Common/Sections")
    Call<List<StudentSectionInfo>> getStudentSection(@Header("schoolID") int schoolID, @Header("Authorization") String authorization);


    /* HOMEWORK */

    @GET("HomeworkAndRemarks/GetTeacherHwAssignmentState")
    Call<List<TeacherHWAssignmentStateInfo>> getTeacherHwAssignmentState(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("teacherId") int teacherId, @Query("schoolId") int schoolId);

    @GET("HomeworkAndRemarks/GetTeacherHwAssignmentsByStatus")
    Call<List<TeacherHWAssignmentByStatusInfo>> getTeacherHwAssignmentsByStatus(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolId, @Query("assignedBy") int assignedBy, @Query("statusId") int statusId);

    @GET("HomeworkAndRemarks/GetAllHomeworkStatus")
    Call<List<HWStatusInfo>> getAllHomeworkStatus(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolId);

    @GET("Common/Subjects")
    Call<List<SubjectInfo>> getSubjects(@Header("schoolID") int schoolID, @Header("Authorization") String authorization);

    @POST("HomeworkAndRemarks/UpdateHomeworkStatus")
    Call<ResponseBody> updateHomeworkStatus(@Header("schoolId") int schoolID, @Header("Authorization") String authorization,@Query("assignmnetId") int assignmnetId, @Query("schoolId") int schoolId, @Body JsonObject jsonObject);

    @GET("UserAccount/GetFilteredStudents")
    Call<List<FilteredStudentInfo>> getFilteredStudents(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("pageIndex") int pageIndex,@Query("pageSize") int pageSize, @Query("className") int className, @Query("section") int section, @Header("roll") int roll, @Query("admissionId") int admissionId, @Query("studName") String studName, @Query("schoolId") int schoolId, @Query("isActive") int isActive);

    @POST("HomeworkAndRemarks/AssignHomework")
    Call<ResponseBody> assignHomework(@Header("schoolId") int schoolID, @Header("Authorization") String authorization,@Body JsonObject jsonObject);

    @GET("HomeworkAndRemarks/GetStudentHwAssignmentState")
    Call<List<StudentHWAssignmentStateInfo>> getStudentHwAssignmentState(@Header("schoolID") int schoolID, @Header("Authorization") String authorization,
                                                                         @Query("studentId") int studentId, @Query("schoolId") int schoolId);


    @GET("HomeworkAndRemarks/GetHwAssignmentsToStudentByStatus")
    Call<List<StudentHWAssignmentByStatusInfo>> getHwAssignmentsToStudentByStatus(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolId, @Query("assignedTo") int assignedTo, @Query("statusId") int statusId);

    @POST("HomeworkAndRemarks/UpdateHomeworkStatus")
    Call<ResponseBody> updateHomeworkStatusByStudent(@Header("schoolId") int schoolID, @Header("Authorization") String authorization,
                                                      @Query("assignmnetId") int assignmnetId, @Query("schoolId") int schoolId, @Body JsonObject jsonObject);

    @POST("Payment/PaymentHistory")
    Call<FeePaymentDetailInfo> getFeePaymentHistory(@Header("schoolId") int schoolID, @Header("Authorization") String authorization, @Body JsonObject jsonObject);

    @GET("UserAccount/GetFilteredStudents")
    Call<List<FilteredStudentInfo>> getFilteredStudents(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("pageIndex") int pageIndex,@Query("pageSize") int pageSize, @Query("className") String className, @Query("section") int section, @Query("roll") String roll, @Query("admissionId") String admissionId, @Query("studName") String studName, @Query("schoolId") int schoolId, @Query("isActive") String isActive);

    @GET("Common/UsersAutoCompletePaymentUsers")
    Call<List<CollectFeeDetailnfo>> getCollctFeeDetail(@Header("schoolID") int schoolID, @Header("Authorization") String authorization, @Query("schoolId") int schoolId, @Query("userRoleId") int userRoleId, @Query("userSearchString") String userSearchString);


}

