package com.winision.sampleapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class BackPress {

    private Fragment parentFragment;

    public BackPress(Fragment parentFragment) {
        this.parentFragment = parentFragment;
    }
/*
    @Override
    public boolean onBackPressed() {

        if(parentFragment == null) {
            return false;
        }

        int childCount = parentFragment.getChildFragmentManager()
                .getBackStackEntryCount();

        if(childCount == 0) {
            return false;
        }else {
            FragmentManager childFragmentManager = parentFragment.getChildFragmentManager();
            OnBackPressedListener childFragment = (OnBackPressedListener) childFragmentManager
                    .getFragments().get(0);

            if(!childFragment.onBackPressed()) {
                childFragmentManager.popBackStackImmediate();
            }

        }

        return true;
    }*/
}
