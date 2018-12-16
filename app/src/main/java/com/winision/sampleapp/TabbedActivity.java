package com.winision.sampleapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.winision.sampleapp.ui.users.Contacts;
import com.winision.sampleapp.ui.users.KnowledgeBank;
import com.winision.sampleapp.ui.users.Profile_Fragment;

public class TabbedActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ActionBar toolBar;
    private Contacts contacts;
    private static final String FRAGEMENT_HOME = "HOME";
    private static final String OTHER_FRAGMENT = "OTHERS";


    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_contacts:
                    toolBar.setTitle("Contacts");
                    loadFragment(new Contacts(), FRAGEMENT_HOME);
                    return true;
                case R.id.navigation_profile:
                    toolBar.setTitle("Profile");
                    loadFragment(new Profile_Fragment(), OTHER_FRAGMENT);
                    return true;
                case R.id.navigation_kb:
                    toolBar.setTitle("Knowledge Bank");
                    loadFragment(new KnowledgeBank(), OTHER_FRAGMENT);
                    return true;
                default:
                    return false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolBar = getSupportActionBar();

        toolBar.setDisplayUseLogoEnabled(true);
        toolBar.setDisplayShowTitleEnabled(true);
        toolBar.setLogo(R.drawable.logo);

        toolBar.setDisplayShowHomeEnabled(true);


        mTextMessage = findViewById(R.id.message);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void loadFragment(Fragment fragment, String name) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);

        final int count = fragmentManager.getBackStackEntryCount();

        if (name.equals(OTHER_FRAGMENT)) {
            fragmentTransaction.addToBackStack(name);
        }

        fragmentTransaction.commit();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {

                if (fragmentManager.getBackStackEntryCount() <= count) {
                    fragmentManager.popBackStack(OTHER_FRAGMENT, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    fragmentManager.removeOnBackStackChangedListener(this);

                    navigation.getMenu().getItem(0).setChecked(true);
                    toolBar.setTitle("Contacts");
                }
            }
        });

    }
}
