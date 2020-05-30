package com.app.eazydigi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.app.eazydigi.R;
import com.app.eazydigi.adapter.FragmentViewPagerAdapter;
import com.app.eazydigi.fragment.CircularFragment;
import com.app.eazydigi.fragment.NoticesFragment;
import com.app.eazydigi.util.PreferenceUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticesCircularsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    NoticesFragment noticesFragment;
    CircularFragment circularFragment;

    int userRoleId;
    public static final int RESULT_ACTIVITY_CREATE_NOTICE = 123;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices_circulars);
        ButterKnife.bind(this);

        initToolbar();

        initViews();

        userRoleId = PreferenceUtils.getUserRoleIdFromDefaults(context);

        if (userRoleId == 2) {
            fab.show();
        } else {
            fab.hide();
        }
    }

    @OnClick({R.id.fab})
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {

            case R.id.fab:
                startActivityForResult(new Intent(this, CreateNoticeCircularActivity.class), RESULT_ACTIVITY_CREATE_NOTICE);
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_ACTIVITY_CREATE_NOTICE) {
            if (resultCode == RESULT_OK) {
                noticesFragment.getNoticeBoards();
                circularFragment.getCircularBoards();
            }
        }
    }

    public void initViews() {

        noticesFragment = new NoticesFragment();
        circularFragment = new CircularFragment();

        setupViewPager(viewpager);
        tabLayout.setupWithViewPager(viewpager);

    }

    public void initToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getString(R.string.notices_circulars));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {

        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(noticesFragment, getResources().getString(R.string.notices));
        adapter.addFragment(circularFragment, getResources().getString(R.string.circulars));
        viewPager.setAdapter(adapter);
    }

}