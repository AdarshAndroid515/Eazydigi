package com.app.eazydigi.model.new_model.request;

import java.util.List;

public class AssignHomeworkRequest {

    /**
     * subject : 2
     * dueDate : 2020-05-18T18:30:00.000Z
     * header : Biology
     * description : read chapter 1 of bio
     * studIdList : [573]
     */

    private String subject;
    private String dueDate;
    private String header;
    private String description;
    private List<Integer> studIdList;

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

    public List<Integer> getStudIdList() {
        return studIdList;
    }

    public void setStudIdList(List<Integer> studIdList) {
        this.studIdList = studIdList;
    }
}
