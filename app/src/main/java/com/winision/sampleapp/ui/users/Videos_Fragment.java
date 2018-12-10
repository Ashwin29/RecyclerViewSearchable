package com.winision.sampleapp.ui.users;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.winision.sampleapp.ApiInterface;
import com.winision.sampleapp.R;
import com.winision.sampleapp.Adapters.VideoAdapter;
import com.winision.sampleapp.Clients.VideoClient;
import com.winision.sampleapp.Modals.VideoModal;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Videos_Fragment extends Fragment {


    private VideoAdapter videoAdapter;
    private SearchView searchView;
    private FloatingActionButton selectionFab;
    private View view;
    private int spanCount = 2;


    public Videos_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_videos_, container, false);

        selectionFab = view.findViewById(R.id.selectionFab);
        selectionFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialogFragment bottomSheetDialog = new VideoFilter();
                bottomSheetDialog.show(getFragmentManager(), bottomSheetDialog.getTag());
            }
        });

        ApiInterface apiClient = VideoClient.getVideosClient().create(ApiInterface.class);
        Call<List<VideoModal>> call = apiClient.getVideoData();

        final RecyclerView recyclerView = view.findViewById(R.id.listView);

/*
        view = getActivity().getWindow().getDecorView();
        int orientation = getResources().getConfiguration().orientation;
        if (Configuration.ORIENTATION_LANDSCAPE == orientation) {
           spanCount = 3;
        } else if(Configuration.ORIENTATION_PORTRAIT == orientation) {
            spanCount = 2;
        }
*/

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), spanCount, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        videoAdapter = new VideoAdapter(this.getActivity());
        recyclerView.setAdapter(videoAdapter);

        call.enqueue(new Callback<List<VideoModal>>() {
            @Override
            public void onResponse(Call<List<VideoModal>> call, Response<List<VideoModal>> response) {
                List<VideoModal> data = response.body();
                videoAdapter.addVideodata(data);
            }

            @Override
            public void onFailure(Call<List<VideoModal>> call, Throwable t) {

            }
        });

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);

        // setHasOptionsMenu(true);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                videoAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                videoAdapter.getFilter().filter(query);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

}
