package com.example.android.vidmeclient.ui.activities;

import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.vidmeclient.AppVidMe;
import com.example.android.vidmeclient.R;
import com.example.android.vidmeclient.di.component.AppComponent;
import com.example.android.vidmeclient.model.entities.AuthResponse;
import com.example.android.vidmeclient.ui.fragments.FeaturedFragment;
import com.example.android.vidmeclient.ui.fragments.NewFragment;
import com.example.android.vidmeclient.ui.fragments.RootFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LaunchActivity extends AppCompatActivity {

    private Menu menu;
    private RootFragment feedState;
    private boolean userLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        loadUserProfileIfExists();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


    }

    private void loadUserProfileIfExists() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String user = sharedPreferences.getString("user", null);
        userLogged = user != null;
        if (userLogged) {
            getApp().setUserProfile(new Gson().fromJson(user, AuthResponse.class));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        this.menu = menu;

        MenuItem item = menu.findItem(R.id.action_sign_out);
        item.setVisible(userLogged);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_sign_out) {
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            sharedPreferences.edit().clear().apply();
            getApp().setUserProfile(null);
            item.setVisible(false);
            feedState.notifyUpdates();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());

        adapter.addFragment(new FeaturedFragment(), getResources().getString(R.string.featured_title));
        adapter.addFragment(new NewFragment(), getResources().getString(R.string.new_title));
        feedState = new RootFragment();
        adapter.addFragment(feedState, getResources().getString(R.string.feed_title));

        viewPager.setAdapter(adapter);
    }

    public void showLogOutMenuItem(boolean action) {
        showText(menu == null ? "null" : "not null");
        if (menu != null) {
            MenuItem item = menu.findItem(R.id.action_sign_out);
            item.setVisible(action);
        }
    }

    public AppComponent getAppComponent() {
        return getApp().appComponent();
    }

    private AppVidMe getApp() {
        return (AppVidMe) getApplication();
    }

    private void showText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
