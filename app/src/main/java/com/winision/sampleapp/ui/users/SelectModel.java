package com.winision.sampleapp.ui.users;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.winision.sampleapp.R;


public class SelectModel extends Fragment {

    private ListView modelListview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_model, container, false);


        modelListview = view.findViewById(R.id.modelList);

        return view;
    }

}
