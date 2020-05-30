package com.app.eazydigi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.eazydigi.model.old_model.HWStatusInfo;
import com.app.eazydigi.model.old_model.StudentClassInfo;
import com.app.eazydigi.model.old_model.StudentSectionInfo;
import com.app.eazydigi.model.old_model.SubjectInfo;

import java.util.ArrayList;
import java.util.List;

public class CustomSpinnerArrayAdapter extends ArrayAdapter<String> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private List<StudentSectionInfo> listSection = new ArrayList<>();
    private List<StudentClassInfo> listClass = new ArrayList<>();
    private List<HWStatusInfo> listHwStatus = new ArrayList<>();
    private List<SubjectInfo> listSubject = new ArrayList<>();

    private final int mValue;


    private final int mResource;
    private static final String TAG = "CustomSpinnerArrayAdapt";

    public CustomSpinnerArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objectsClass, int value) {
        super(context, resource, 0, objectsClass);
        mValue = value;
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        if (mValue == 1) {
            listClass = objectsClass;
        } else if (mValue == 2) {
            listHwStatus = objectsClass;
        } else if (mValue == 3) {
            listSubject = objectsClass;
        }else if(mValue==4){
            listSection=objectsClass;
        }


    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {

        final View view = mInflater.inflate(mResource, parent, false);

        TextView tvClassSection = (TextView) view.findViewById(android.R.id.text1);
        if (mValue == 1) {
            StudentClassInfo studentClassInfo = listClass.get(position);
            tvClassSection.setText("" + studentClassInfo.getValue());
        } else if(mValue==2){
            HWStatusInfo statusInfo = listHwStatus.get(position);
            tvClassSection.setText("" + statusInfo.getStatus());
        }else if(mValue==3){
            SubjectInfo subjectInfo = listSubject.get(position);
            tvClassSection.setText("" + subjectInfo.getValue());
        }else if(mValue==4){
            StudentSectionInfo studentSectionInfo=listSection.get(position);
            tvClassSection.setText("" + studentSectionInfo.getValue());
        }

        return view;
    }
}