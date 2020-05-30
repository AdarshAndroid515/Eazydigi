package com.app.eazydigi.model.new_model;

import android.content.Context;

public class EventModel {
    public Object requestObject;
    public Object responseObject;
    public String notificationName;
    public Context context;
    public EventModel(Object requestObject, Object responseObject, String notificationName, Context context ) {
        this.requestObject = requestObject;
        this.responseObject = responseObject;
        this.notificationName = notificationName;
        this.context = context;

    }
}
