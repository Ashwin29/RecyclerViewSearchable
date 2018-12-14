package com.winision.sampleapp.ui.users;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.winision.sampleapp.ApiInterface;
import com.winision.sampleapp.Adapters.NotesAdapter;
import com.winision.sampleapp.Clients.NotesClient;
import com.winision.sampleapp.Modals.NotesModal;
import com.winision.sampleapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Notes_Fragment extends Fragment {

    private SearchView searchView;
    private NotesAdapter notesAdapter;

    public Notes_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_notes_, container, false);

        ApiInterface apiClient = NotesClient.getNotesClient().create(ApiInterface.class);
        Call<List<NotesModal>> call = apiClient.getNotesData();

        final RecyclerView recyclerView = view.findViewById(R.id.listView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

        notesAdapter = new NotesAdapter(this.getActivity());
        recyclerView.setAdapter(notesAdapter);

        call.enqueue(new Callback<List<NotesModal>>() {
            @Override
            public void onResponse(Call<List<NotesModal>> call, Response<List<NotesModal>> response) {
                List<NotesModal> data = response.body();
                notesAdapter.addNotesData(data);
            }

            @Override
            public void onFailure(Call<List<NotesModal>> call, Throwable t) {

            }
        });

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);

        // setHasOptionsMenu(true);


        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        //new SearchView(((MainActivity) mContext).getSupportActionBar().getThemedContext());
        searchView = (SearchView) menu.findItem(R.id.search)
                .getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                notesAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                notesAdapter.getFilter().filter(query);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
