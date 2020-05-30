package com.app.eazydigi.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.eazydigi.R;
import com.app.eazydigi.model.old_model.LoginResponseInfo;
import com.app.eazydigi.model.old_model.SchoolInfo;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.PreferenceUtils;
import com.app.eazydigi.util.Utils;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SchoolProfileFragment extends BaseFragment {

    LoginResponseInfo loginResponseInfo;

    @BindView(R.id.et_school_short_name)
    EditText etSchoolShortName;

    @BindView(R.id.et_school_full_name)
    EditText etSchoolFullName;

    @BindView(R.id.et_contact)
    EditText etContactNumber;

    @BindView(R.id.et_email)
    EditText etemail;

    @BindView(R.id.et_address)
    EditText etAddress;

    @BindView(R.id.et_fax_number)
    EditText etFaxnumber;

    @BindView(R.id.et_affiliation_number)
    EditText etAffiliationNumber;

    @BindView(R.id.et_school_number)
    EditText etSchoolNumber;

    @BindView(R.id.et_account_holder_name)
    EditText etAccountHolderName;

    @BindView(R.id.et_account_number)
    EditText etAccountNumber;

    @BindView(R.id.et_bank_ifsc)
    EditText etBankIFSC;

    @BindView(R.id.iv_school_logo)
    ImageView ivProfile;

    private static final String TAG = "SchoolProfileFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_school_profile, container, false);
        ButterKnife.bind(this, view);

        loginResponseInfo = PreferenceUtils.getUserInfoFromDefaults(getActivity());
        Log.e(TAG, "@@@@@@@loginresponseinfoonCreateView: " + loginResponseInfo.getUserId());


        initViews();

        getSchoolProfileDetail();

        return view;

    }

    private void initViews() {
        etSchoolShortName.setEnabled(false);
        etSchoolFullName.setEnabled(false);
        etContactNumber.setEnabled(false);
        etemail.setEnabled(false);
        etAddress.setEnabled(false);
        etFaxnumber.setEnabled(false);
        etAffiliationNumber.setEnabled(false);
        etSchoolNumber.setEnabled(false);
        etAccountHolderName.setEnabled(false);
        etAccountNumber.setEnabled(false);
        etBankIFSC.setEnabled(false);

    }

    public void getSchoolProfileDetail() {
        Log.e(TAG, "@@@@@@@@getProfileDetail: ");

        if (!Utils.isConnectedToInternet(getActivity())) {
            return;
        }
        MyApplication.getAppInstance().getAPIInterface().loadSchoolData(MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(),
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId()).enqueue(new Callback<SchoolInfo>() {
            @Override
            public void onResponse(Call<SchoolInfo> call, Response<SchoolInfo> response) {
                Log.e(TAG, "@@@@@@onResponse: " + response.body());

                if (response.isSuccessful()) {
                    SchoolInfo schoolInfo = response.body();
                    setData(schoolInfo);
                }

            }

            @Override
            public void onFailure(Call<SchoolInfo> call, Throwable t) {
                Log.e(TAG, "@@@@@@onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });


    }


    public void setData(SchoolInfo schoolInfo) {
        String schoolShortName = schoolInfo.getSchoolName();
        String schoolFullNme = schoolInfo.getSchoolFullName();
        String contact = schoolInfo.getContactNumber();
        String email = schoolInfo.getEmail();
        String faxNumber = schoolInfo.getFax();
        String address = schoolInfo.getAddress();
        String affiliationNo = (String) schoolInfo.getAffiliationNo();
        String schoolNo = (String) schoolInfo.getSchoolNo();
//        String accountHolderName = schoolInfo();
//        String accNumber = (String) schoolInfo.getBankAccountNo();
//        String bankIFSC = (String) schoolInfo();


        if (schoolShortName != null && schoolShortName.length() > 0) {
            etSchoolShortName.setText("" + schoolInfo.getSchoolName());
        }
        if (schoolFullNme != null && schoolFullNme.length() > 0) {
            etSchoolFullName.setText("" + schoolInfo.getSchoolFullName());
        }
        if (faxNumber != null && faxNumber.length() > 0) {
            etFaxnumber.setText("" + schoolInfo.getFax());
        }
        if (address != null && address.length() > 0) {
            etAddress.setText("" + schoolInfo.getAddress());
        }
        if (affiliationNo != null && affiliationNo.length() > 0) {
            etAffiliationNumber.setText("" + schoolInfo.getAffiliationNo());
        }
        if (contact != null && contact.length() > 0) {
            etContactNumber.setText("" + schoolInfo.getContactNumber());
        }
        if (email != null && email.length() > 0) {
            etemail.setText("" + schoolInfo.getEmail());
        }
        if (schoolNo != null && schoolNo.length() > 0) {
            etSchoolNumber.setText("" + schoolInfo.getSchoolNo());
        }
//        if (accountHolderName!= null && accountHolderName.length() > 0) {
//            etAccountHolderName.setText("" + );
//        }
//        if (accNumber != null && accNumber.length() > 0) {
//            etAccountNumber.setText("" + schoolInfo.getEmail());
//        }
//        if (bankIFSC != null && bankIFSC.length() > 0) {
//            etBankIFSC.setText("" + );
//        }

        if (schoolInfo.getLogo() != null) {
            Glide.with(getActivity())
                    .load(schoolInfo.getLogo())
                    .placeholder(R.drawable.ic_school)
                    .into(ivProfile);
        }

    }

}

