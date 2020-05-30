package com.app.eazydigi.model.new_model.request;

public class CreateNoticeCircularRequest {

    /**
     * category : 1
     * title : demo notice
     * description : demo notice
     * startDate : 2020-05-19
     */

    private int category;
    private String title;
    private String description;
    private String startDate;

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
}
