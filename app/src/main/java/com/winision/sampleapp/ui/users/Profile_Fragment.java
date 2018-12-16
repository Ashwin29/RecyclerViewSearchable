package com.winision.sampleapp.ui.users;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.winision.sampleapp.ChangeProfilePic;
import com.winision.sampleapp.R;
import com.winision.sampleapp.ui.users.Password_Fragment;

public class Profile_Fragment extends Fragment {

    private TextView passwordTxt;
    private FloatingActionButton editProfilePic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_, container, false);

        passwordTxt = view.findViewById(R.id.passwordTxt);
        editProfilePic = view.findViewById(R.id.editProfilePic);


        passwordTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapPasswordFragment();
            }
        });


        editProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPicActivity();
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

    private void openPicActivity() {
        startActivity(new Intent(getContext(), ChangeProfilePic.class));
    }



}
