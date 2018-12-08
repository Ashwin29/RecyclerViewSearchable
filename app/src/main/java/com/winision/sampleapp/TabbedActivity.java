package com.winision.sampleapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.winision.sampleapp.ui.users.Contacts;
import com.winision.sampleapp.ui.users.KnowledgeBank;

public class TabbedActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ActionBar toolBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_contacts:
                    toolBar.setTitle("Contacts");
                    fragment = new Contacts();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    toolBar.setTitle("Profile");
                    fragment = new Profile_Fragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_kb:
                    toolBar.setTitle("Knowledge Bank");
                    fragment = new KnowledgeBank();
                    loadFragment(fragment);
                    return true;
                default:
                    toolBar.setTitle("Contacts");
                    fragment = new Contacts();
                    loadFragment(fragment);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        toolBar = getSupportActionBar();

        toolBar.setTitle("XR Assist");

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

}
