package com.winision.sampleapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.winision.sampleapp.ui.users.Password_Fragment;

public class Profile_Fragment extends Fragment {

    private TextView passwordTxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);

        passwordTxt = view.findViewById(R.id.passwordTxt);

        passwordTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapPasswordFragment();
            }
        });

        return view;
    }


    private void swapPasswordFragment() {
        Password_Fragment password_fragment = new Password_Fragment();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.frameLayout, password_fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
