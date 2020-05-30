package com.app.eazydigi.listener;

import com.app.eazydigi.model.new_model.response.GetAllNoticesResponse;

public interface OnNoticeStatusChangeListener {

    public void onStatusChangeListener(int position,GetAllNoticesResponse.DataBean dataBean);
}
