package com.winision.sampleapp.ui.users;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
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

import com.winision.sampleapp.R;
import com.winision.sampleapp.ViewPagerAdapterContacts;


public class VideoFilter extends BottomSheetDialogFragment {


    ViewPagerAdapterContacts adapter;
    SelectProduct selectProduct;
    SelectModel selectModel;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_close:
                    return true;
                case R.id.navigation_filter:
                    Toast.makeText(getActivity(), "Filter Triggered", Toast.LENGTH_LONG).show();
                    return true;
                default:
                    return false;
            }
        }
    };
    private BottomSheetBehavior.BottomSheetCallback
            mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_filter, container, false);

        viewPager = view.findViewById(R.id.viewPagerFilter);
        viewPager.setOffscreenPageLimit(2);

        setUpPager(viewPager);

        tabLayout = view.findViewById(R.id.tabVideoFilter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        switch (item.getItemId()) {
            case R.id.navigation_products:
                loadFragment(new SelectProduct());
                return true;
            case R.id.navigation_model:
                loadFragment(new SelectModel());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_video_filter, null);
        dialog.setContentView(contentView);
    }


    private void setUpPager(ViewPager viewPager) {

        adapter = new ViewPagerAdapterContacts(getChildFragmentManager());

        selectProduct = new SelectProduct();
        selectModel = new SelectModel();

        adapter.addfragment(selectProduct, "SELECT PRODUCT");
        adapter.addfragment(selectModel, "SELECT MODEL");

        viewPager.setAdapter(adapter);

    }


    private void loadFragment(Fragment fragment) {
        final FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);

        fragmentTransaction.commit();

    }

}
