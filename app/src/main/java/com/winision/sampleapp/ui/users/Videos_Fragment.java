package com.winision.sampleapp.ui.users;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winision.sampleapp.ApiInterface;
import com.winision.sampleapp.R;
import com.winision.sampleapp.VideoClient;
import com.winision.sampleapp.VideoModal;

import java.util.List;

import retrofit2.Call;


public class Videos_Fragment extends Fragment {


    public Videos_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_videos_, container, false);

        ApiInterface apiClient = VideoClient.getNotesClient().create(ApiInterface.class);
        Call<List<VideoModal>> call = apiClient.getVideoData();


        return view;
    }

}
