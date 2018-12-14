package com.winision.sampleapp.ui.users;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.winision.sampleapp.OnBackPressedListener;
import com.winision.sampleapp.R;
import com.winision.sampleapp.ViewPagerAdapterContacts;

public class Contacts extends Fragment {

    ViewPagerAdapterContacts viewPagerAdapterContacts;
    UsersFragment users_fragment;
    Calls_Fragment calls_fragment;
    private TabLayout tabLayout;
    private static final String PAGER_HOME = "Calls";
    private ActionBar toolbar;
    public ViewPager viewPager;
    private FloatingActionButton backCheck;
    private static final String PAGER_OTHER = "others";


    public Contacts() {
        // Required empty public constructor
    }

    public static Contacts newInstance(String param1, String param2) {
        Contacts fragment = new Contacts();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);

        viewPager = view.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);

        toolbar = getActivity().getActionBar();

        setupViewPager(viewPager);

        backCheck = view.findViewById(R.id.backCheck);

        backCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        tabLayout = view.findViewById(R.id.tablayoutContacts);

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        return view;
    }



    private void setupViewPager(ViewPager viewPager) {

        viewPagerAdapterContacts = new ViewPagerAdapterContacts(getChildFragmentManager());

        users_fragment = new UsersFragment();
        calls_fragment = new Calls_Fragment();

        viewPagerAdapterContacts.addfragment(users_fragment, "XR-Users");
        viewPagerAdapterContacts.addfragment(calls_fragment, "Calls");


        viewPager.setAdapter(viewPagerAdapterContacts);

    }

    private void back() {
        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, false);
        }
    }

}
