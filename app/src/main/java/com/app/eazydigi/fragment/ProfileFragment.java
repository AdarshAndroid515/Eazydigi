package com.app.eazydigi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.eazydigi.R;
import com.app.eazydigi.activity.FeePaymentDetailActivity;
import com.app.eazydigi.model.old_model.LoginResponseInfo;
import com.app.eazydigi.model.old_model.ProfileDetailInfo;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.PreferenceUtils;
import com.app.eazydigi.util.Utils;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends BaseFragment {

    LoginResponseInfo loginResponseInfo;

    @BindView(R.id.et_fname)
    EditText etFirstName;

    @BindView(R.id.et_lname)
    EditText etLastName;

    @BindView(R.id.et_mname)
    EditText etMiddleName;

    @BindView(R.id.et_father_name)
    EditText etFatherName;

    @BindView(R.id.et_mother_name)
    EditText etMotherName;

    @BindView(R.id.et_contact)
    EditText etContactNumber;

    @BindView(R.id.et_email)
    EditText etemail;

    @BindView(R.id.et_dob)
    EditText etdob;

    @BindView(R.id.rg_gender)
    RadioGroup rgGender;

    @BindView(R.id.rb_male)
    RadioButton rbMale;

    @BindView(R.id.rb_female)
    RadioButton rbFemale;

    @BindView(R.id.rb_other)
    RadioButton rbOther;

    @BindView(R.id.et_about_us)
    EditText etAboutUs;

    @BindView(R.id.et_parm_address)
    EditText etParmAddress;

    @BindView(R.id.et_current_address)
    EditText etCurrentAddress;

    @BindView(R.id.et_school_name)
    EditText etSchoolName;

    @BindView(R.id.et_adhar_number)
    EditText etAdharNumber;

    @BindView(R.id.et_parent_occupation)
    EditText etParentOccupation;

    @BindView(R.id.et_account_name)
    EditText etAccountName;

    @BindView(R.id.et_account_number)
    EditText etAccountNumber;

    @BindView(R.id.et_bank_ifsc)
    EditText etBankIFSC;

    @BindView(R.id.tv_fee_payment_details)
    TextView tvFeePaymentDetails;

    @BindView(R.id.et_contactLabel)
    TextView tvContactLabel;

    private static final String TAG = "ProfileFragment";
    private LoginResponseInfo loginResponseInfoD;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);

        loginResponseInfo = PreferenceUtils.getUserInfoFromDefaults(getActivity());

        initViews();

        //getProfileDetail();

        loginResponseInfoD=MyApplication.getAppInstance().getLoginResponseInfo();

        return view;

    }

    private void initViews() {

        etFirstName.setEnabled(false);
        etLastName.setEnabled(false);
        etMiddleName.setEnabled(false);
        etFatherName.setEnabled(false);
        etMotherName.setEnabled(false);
        etContactNumber.setEnabled(false);
        etemail.setEnabled(false);
        etdob.setEnabled(false);
        etAboutUs.setEnabled(false);
        etParmAddress.setEnabled(false);
        etCurrentAddress.setEnabled(false);
        etSchoolName.setEnabled(false);
        etAdharNumber.setEnabled(false);
        etParentOccupation.setEnabled(false);
        etAccountName.setEnabled(false);
        etAccountNumber.setEnabled(false);
        etBankIFSC.setEnabled(false);

//        if (loginResponseInfo.getUserRoleId() == Constants.UserRoles.ROLE_STUDENT) {
//            tvFeePaymentDetails.setVisibility(View.VISIBLE);
//        } else {
//            tvFeePaymentDetails.setVisibility(View.GONE);
//        }

        try {
            JWTUtils jwtUtils=new JWTUtils();
            String Token=MyApplication.getAppInstance().sharedPref().getString(MyApplication.getAppInstance().sharedPref().ACCESS_TOKEN);
            String returnString=jwtUtils.decoded(Token);
            JSONObject jsonObject = new JSONObject(returnString);
            //JSONArray jsonArray=jsonObject.getJSONObject("loginUser").getJSONArray("roles");
            JSONObject jsonObjectUser=jsonObject.getJSONObject("loginUser");
            String FName=jsonObjectUser.getString("firstName");
            //String MName=jsonObjectUser.getString("middleName");
            String LName=jsonObjectUser.getString("lastName");
            String Emails=jsonObjectUser.getString("emailId");

            //String Role=jsonArray.getJSONObject(0).getString("name");
            String SchooName=jsonObject.getString("schoolName");

            etFirstName.setText(FName);
            //etMiddleName.setText(MName);
            etLastName.setText(LName);
            etemail.setText(Emails);
            etSchoolName.setText(SchooName);

            try {
                String Phone= String.valueOf(jsonObjectUser.getLong("phone"));
                etContactNumber.setText(Phone);
            }catch (Exception e){
                etContactNumber.setVisibility(View.GONE);
                tvContactLabel.setVisibility(View.GONE);
                e.printStackTrace();
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public class JWTUtils {

        public String decoded(String JWTEncoded) throws Exception {
            String returnString = "";
            try {
                String[] split = JWTEncoded.split("\\.");
                Log.v("JWT_DECODED", "Header: " + getJson(split[0]));
                Log.v("JWT_DECODED", "Body: " + getJson(split[1]));

                returnString=getJson(split[1]);
            } catch (UnsupportedEncodingException e) {
                //Error
            }
            return returnString;
        }

        private  String getJson(String strEncoded) throws UnsupportedEncodingException{
            byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
            return new String(decodedBytes, "UTF-8");
        }
    }


    @OnClick({R.id.tv_fee_payment_details})
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.tv_fee_payment_details:
                startActivity(new Intent(getActivity(), FeePaymentDetailActivity.class));
                break;

            default:
                break;

        }
    }

    public void getProfileDetail() {
        Log.e(TAG, "@@@@@@@@getProfileDetail: ");

        if (!Utils.isConnectedToInternet(getActivity())) {
            return;
        }
        MyApplication.getAppInstance().getAPIInterface().getProfileDetail(
                MyApplication.getAppInstance().getLoginResponseInfo().getSchoolId(), Utils.getAuthorization(),
                MyApplication.getAppInstance().getLoginResponseInfo().getUserId()).enqueue(new Callback<ProfileDetailInfo>() {
            @Override
            public void onResponse(Call<ProfileDetailInfo> call, Response<ProfileDetailInfo> response) {
                Log.e(TAG, "@@@@@@onResponse: " + response.body());

                if (response.isSuccessful()) {
                    ProfileDetailInfo profileDetailInfo = response.body();
                    setData(profileDetailInfo);
                }


            }

            @Override
            public void onFailure(Call<ProfileDetailInfo> call, Throwable t) {
                Log.e(TAG, "@@@@@@onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });


    }


    public void setData(ProfileDetailInfo profileDetailInfo) {
        String firstName = profileDetailInfo.getFirstName();
        String middleName = profileDetailInfo.getMiddleName();
        String lastName = profileDetailInfo.getLastName();
        String fatherName = (String) profileDetailInfo.getFatherName();
        String motherName = (String) profileDetailInfo.getMotherName();
        String contact = profileDetailInfo.getMobile();
        String email = (String) profileDetailInfo.getEmail();
        String dob = Utils.convertToDateString(profileDetailInfo.getDob(), "yyyy-MM-dd'T'HH:mm:ss", "dd MMM, yyyy");

        Integer gender = profileDetailInfo.getSex();
        String aboutUs = (String) profileDetailInfo.getAboutMe();
        String parmAddress = (String) profileDetailInfo.getParmanentAddress();
        String currentAddress = (String) profileDetailInfo.getCurrentAddress();
        String prevSchoolName = (String) profileDetailInfo.getPreviousSchoolName();
        String adharNo = (String) profileDetailInfo.getAadharNo();
        String parentOccupation = profileDetailInfo.getParentOccupation();
        String accNumber = (String) profileDetailInfo.getBankAccountNo();
        String roleName = profileDetailInfo.getRoleName();
        String bankIFSC = (String) profileDetailInfo.getBankIFSC();


        if (firstName != null && firstName.length() > 0) {
            etFirstName.setText("" + profileDetailInfo.getFirstName());
        }
        if (middleName != null && middleName.length() > 0) {
            etLastName.setText("" + profileDetailInfo.getLastName());
        }
        if (lastName != null && lastName.length() > 0) {
            etMiddleName.setText("" + profileDetailInfo.getMiddleName());
        }
        if (fatherName != null && fatherName.length() > 0) {
            etFatherName.setText("" + profileDetailInfo.getFatherName());
        }
        if (motherName != null && motherName.length() > 0) {
            etMotherName.setText("" + profileDetailInfo.getMotherName());
        }
        if (contact != null && contact.length() > 0) {
            etContactNumber.setText("" + profileDetailInfo.getMobile());
        }
        if (email != null && email.length() > 0) {
            etemail.setText("" + profileDetailInfo.getEmail());
        }

        if (dob != null && dob.length() > 0) {
            etdob.setText("" + Utils.convertToDateString(profileDetailInfo.getDob(), "yyyy-MM-dd'T'HH:mm:ss", "dd MMM, yyyy"));
        }

        if (gender == null || gender == 1) {
            rbMale.setChecked(true);
            rbFemale.setEnabled(false);
            rbOther.setEnabled(false);
        } else if (gender == 2) {
            rbFemale.setChecked(true);
            rbMale.setEnabled(false);
            rbOther.setEnabled(false);
        } else {
            rbOther.setChecked(true);
            rbMale.setEnabled(false);
            rbFemale.setEnabled(false);
        }

        if (aboutUs != null && aboutUs.length() > 0) {
            etAboutUs.setText("" + profileDetailInfo.getAboutMe());
        }
        if (parmAddress != null && parmAddress.length() > 0) {
            etParmAddress.setText("" + profileDetailInfo.getParmanentAddress());
        }
        if (currentAddress != null && currentAddress.length() > 0) {
            etCurrentAddress.setText("" + profileDetailInfo.getCurrentAddress());
        }
        if (prevSchoolName != null && prevSchoolName.length() > 0) {
            etSchoolName.setText("" + profileDetailInfo.getPreviousSchoolName());
        }
        if (adharNo != null && adharNo.length() > 0) {
            etAdharNumber.setText("" + profileDetailInfo.getAadharNo());
        }
        if (parentOccupation != null && parentOccupation.length() > 0) {
            etParentOccupation.setText("" + profileDetailInfo.getParentOccupation());
        }
        if (roleName != null && roleName.length() > 0) {
            etAccountName.setText("" + profileDetailInfo.getRoleName());
        }
        if (accNumber != null && accNumber.length() > 0) {
            etAccountNumber.setText("" + profileDetailInfo.getBankAccountNo());
        }
        if (bankIFSC != null && bankIFSC.length() > 0) {
            etBankIFSC.setText("" + profileDetailInfo.getBankIFSC());
        }
    }

}
