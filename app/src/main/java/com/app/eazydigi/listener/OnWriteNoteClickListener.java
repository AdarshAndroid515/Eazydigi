package com.app.eazydigi.listener;

import com.app.eazydigi.model.new_model.response.MarkAttendanceListResponse;

public interface OnWriteNoteClickListener {
    public void onWriteNoteClick2(int position,String Reason, MarkAttendanceListResponse.DataBean dataBean);
}
