package com.app.eazydigi.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.app.eazydigi.R;
import com.app.eazydigi.listener.OnWriteNoteListener;
import com.app.eazydigi.model.old_model.AttendanceDetailForStudentInfo;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNoteBottomDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    @BindView(R.id.et_note)
    EditText etNote;

    @BindView(R.id.btn_cancel)
    Button btnCancel;

    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @BindView(R.id.tv_student_name)
    TextView tvStudentName;

    static AttendanceDetailForStudentInfo attendanceStudentInfo;
    public String Reason="";
    public static final int RESULT_ACTIVITY_ATTENDANCE = 123;

    private static final String TAG = "AddNoteBottomDialogFrag";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    static OnWriteNoteListener onWriteNoteListener;

    public static AddNoteBottomDialogFragment newInstance(AttendanceDetailForStudentInfo info, OnWriteNoteListener listener) {
        Log.e(TAG, "@@@@@@newInstance: " );
        attendanceStudentInfo = info;
        onWriteNoteListener = listener;
        AddNoteBottomDialogFragment addNoteBottomDialogFragment = new AddNoteBottomDialogFragment();
        return addNoteBottomDialogFragment;
    }

    public static AddNoteBottomDialogFragment newInstance(String Reason, OnWriteNoteListener listener) {
        Log.e(TAG, "@@@@@@newInstance: " );
        Reason = Reason;
        onWriteNoteListener = listener;
        AddNoteBottomDialogFragment addNoteBottomDialogFragment = new AddNoteBottomDialogFragment();
        return addNoteBottomDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_write_note, container, false);
        ButterKnife.bind(this, view);
        tvStudentName.setText("" + attendanceStudentInfo.getStudentName());

        return view;

    }


    @OnClick({R.id.btn_cancel, R.id.btn_submit})
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.btn_submit:
                String note = etNote.getText().toString();
                addNote(note);
                dismiss();
                if (onWriteNoteListener != null) {
                    Log.e(TAG, "@@@@@@@notelisteneronClick: " );
                    onWriteNoteListener.onWriteNote(attendanceStudentInfo);
                }
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;

        }

    }

    public void addNote(String note) {
        Log.e(TAG, "@@@@@@@@addNote: " + note);
        attendanceStudentInfo.getAttendanceList().get(0).setAttendanceStatusReason(note);
    }


}