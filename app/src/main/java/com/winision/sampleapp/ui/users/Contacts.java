package com.winision.sampleapp.ui.users;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.winision.sampleapp.R;
import com.winision.sampleapp.ViewPagerAdapterContacts;

public class Contacts extends Fragment {

    ViewPagerAdapterContacts viewPagerAdapterContacts;
    UsersFragment users_fragment;
    Calls_Fragment calls_fragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ActionBar toolbar;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Fragment fragment;

        switch (item.getItemId()) {
            case R.id.navigation_users:
                toolbar.setTitle("Users");
                fragment = new Contacts();
                loadFragment(fragment);
                return true;
            case R.id.navigation_calls:
                toolbar.setTitle("Calls");
                Toast.makeText(getContext(), "Calls Tab selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapterContacts = new ViewPagerAdapterContacts(getChildFragmentManager());

        users_fragment = new UsersFragment();
        calls_fragment = new Calls_Fragment();

        viewPagerAdapterContacts.addfragment(users_fragment, "XR-Users");
        viewPagerAdapterContacts.addfragment(calls_fragment, "Calls");

        viewPager.setAdapter(viewPagerAdapterContacts);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager()
                .beginTransaction();
        transaction.addToBackStack(null);
        transaction.commit();
    }


}