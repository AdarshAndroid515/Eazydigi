package com.app.eazydigi.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.eazydigi.R;
import com.app.eazydigi.api.APIInterface;
import com.app.eazydigi.api.ApiClient;
import com.app.eazydigi.fragment.HomeFragment;
import com.app.eazydigi.fragment.ProfileFragment;
import com.app.eazydigi.fragment.SchoolProfileFragment;
import com.app.eazydigi.model.new_model.response.DashboardResponse;
import com.app.eazydigi.util.AlertMessages;
import com.app.eazydigi.util.MyApplication;
import com.app.eazydigi.util.PreferenceUtils;
import com.app.eazydigi.util.ProgressHUD;
import com.app.eazydigi.util.Utils;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DashboardActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.iv_school_logo)
    ImageView ivSchoolLogo;

    @BindView(R.id.tv_school_name)
    TextView tvSchoolName;

    @BindView(R.id.tv_home)
    TextView tvHome;

    @BindView(R.id.tv_profile)
    TextView tvProfile;

    @BindView(R.id.tv_school_profile)
    TextView tvSchoolProfile;

    @BindView(R.id.tv_logout)
    TextView tvLogout;

    int userRoleId;


    private static final String TAG = "DashboardActivity";
    private boolean doubleBackToExitPressedOnce = false;
    private static int SPLASH_TIME_OUT = 5000;

    @Override
    protected void onResume() {
        super.onResume();
        // .... other stuff in my onResume ....

        this.doubleBackToExitPressedOnce = false;

    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        try {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Click Again to Exit", Toast.LENGTH_SHORT).show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                doubleBackToExitPressedOnce = false;
            }
        }, SPLASH_TIME_OUT);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        initToolbar();

        initDrawer();

        tvHome.performClick();

        setSchoolData();

        if (Utils.isConnectedToInternet(context)) {
          //  getDashboardInfo();
        } else {
            Toast.makeText(context, getString(R.string.alert_check_connection), Toast.LENGTH_SHORT).show();
        }

    }

    private void setSchoolData() {

        if (MyApplication.getAppInstance().getLoginResponseInfo().getSchoolName()!=null)
            tvSchoolName.setText("" + MyApplication.getAppInstance().getLoginResponseInfo().getSchoolName());

        try {
            if (MyApplication.getAppInstance().getLoginResponseInfo().getSchoolLogo()!=null)
                Glide.with(this)
                        .load(ApiClient.BASE_URL+"api/sa/school/sm/fileDownload/"+MyApplication.getAppInstance().getLoginResponseInfo().getSchoolLogo())
                        .into(ivSchoolLogo);
        }catch (Exception e){
            e.printStackTrace();
        }

        userRoleId = PreferenceUtils.getUserRoleIdFromDefaults(context);

        /*
        if (userRoleId == Constants.UserRoles.ROLE_ADMIN) {
            tvSchoolProfile.setVisibility(View.VISIBLE);
        } else {
            tvSchoolProfile.setVisibility(View.GONE);
        }

         */

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }


    @OnClick({R.id.tv_home, R.id.tv_profile, R.id.tv_school_profile, R.id.tv_logout})
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.tv_home:
                activateMenu(view.getId());
                replaceFragment(new HomeFragment());
                drawerLayout.closeDrawers();
                break;
            case R.id.tv_profile:
                activateMenu(view.getId());
                replaceFragment(new ProfileFragment());
                drawerLayout.closeDrawers();
                break;
            case R.id.tv_school_profile:
                activateMenu(view.getId());
                replaceFragment(new SchoolProfileFragment());
                drawerLayout.closeDrawers();
                break;
            case R.id.tv_logout:
                AlertMessages.showMessage(context, getString(R.string.confirmation), getString(R.string.are_you_sure_logout), getString(R.string.yes), getString(R.string.cancel), null, new AlertMessages.AlertDialogCallback() {
                    @Override
                    public void clickedButtonText(String s) {
                        if (s.equals(getString(R.string.yes))) {
                            MyApplication.getAppInstance().sharedPref().setString(MyApplication.getAppInstance().sharedPref().ACCESS_TOKEN,null);
                            MyApplication.getAppInstance().clearLoginResponseInfo();
                            PreferenceUtils.clearUserDefaults(context);
                            startActivity(new Intent(context, LoginActivity.class));
                            finishAffinity();
                        }
                    }
                });
                break;
            default:
                break;

        }

    }

    public void activateMenu(int id) {

        tvHome.setBackground(null);
        tvProfile.setBackground(null);
        tvSchoolProfile.setBackground(null);
        tvLogout.setBackground(null);

        tvHome.setTextColor(Color.WHITE);
        tvProfile.setTextColor(Color.WHITE);
        tvSchoolProfile.setTextColor(Color.WHITE);
        tvLogout.setTextColor(Color.WHITE);

        Typeface typeface = ResourcesCompat.getFont(context, R.font.googlesans_regular);
        tvHome.setTypeface(typeface);
        tvProfile.setTypeface(typeface);
        tvSchoolProfile.setTypeface(typeface);
        tvLogout.setTypeface(typeface);

        TextView textView = findViewById(id);
        textView.setBackgroundResource(R.drawable.bg_sidemenu_item);
        textView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        textView.setTypeface(ResourcesCompat.getFont(context, R.font.googlesans_bold));

        Log.e(TAG, "@@@@@@@@@@gettext " + textView.getText());
        setTitle("" + textView.getText());

    }

    private void initDrawer() {

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                supportInvalidateOptionsMenu();

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

                drawerLayout.bringChildToFront(drawerView);
                drawerLayout.requestLayout();
            }

        };

        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_action_menu);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

    }

    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    public void getDashboardInfo(){
        ProgressHUD pd = showPD(getString(R.string.signing_in));

        APIInterface apiService = ApiClient.getClient(this).create(APIInterface.class);
        MyApplication.getAppInstance().getDisposable().add(apiService.getDashboard()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DashboardResponse>() {
                    @Override
                    public void onSuccess(DashboardResponse dashboardResponse) {
                        dismissPD(pd);
                        try {

                           // EventBus.getDefault().post(new EventModel(null,dashboardResponse,"DASHBOARD_RESPONSE",null));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissPD(pd);
                        Log.v("error",e.getMessage());
                    }
                }));
    }
}
