package com.app.eazydigi.util;

import android.app.Application;

import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.model.old_model.LoginResponseInfo;

import io.reactivex.disposables.CompositeDisposable;

public class MyApplication extends Application {

    private static MyApplication appInstance;

    private static APIInterface apiInterface;

    private static LoginResponseInfo loginResponseInfo;

    private CompositeDisposable disposable;

    private SharedPref sharedPref;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;

        //apiInterface = AppApi.initialize();

        apiInterface = ApiClient.getClient(this).create(APIInterface.class);

        sharedPref = new SharedPref(this);
        disposable = new CompositeDisposable();

    }

    public static MyApplication getAppInstance() {
        return appInstance;
    }

    public APIInterface getAPIInterface() {
        return apiInterface;
    }

    public LoginResponseInfo getLoginResponseInfo() {

        if (loginResponseInfo == null) {
            loginResponseInfo = PreferenceUtils.getUserInfoFromDefaults(this);
        }
        return loginResponseInfo;
    }

    public static void clearLoginResponseInfo() {
        loginResponseInfo = null;
    }

    public SharedPref sharedPref() {
        return sharedPref;
    }

    public CompositeDisposable getDisposable() {
        return disposable;
    }
}
