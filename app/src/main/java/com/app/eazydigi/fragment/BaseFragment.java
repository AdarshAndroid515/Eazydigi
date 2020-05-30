package com.app.eazydigi.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.eazydigi.R;
import com.app.eazydigi.util.ProgressHUD;

public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ProgressHUD showPD(String message) {
        try {
            ProgressHUD pd = ProgressHUD.show(getActivity(), (message != null) ? message : getString(R.string.please_wait), true, false);
            return pd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void dismissPD(ProgressHUD pd) {

        try {
            if (pd != null && pd.isShowing()) {
                pd.dismiss();
                pd = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
