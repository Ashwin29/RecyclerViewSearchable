package com.winision.sampleapp.ui.users;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.winision.sampleapp.Clients.Client;
import com.winision.sampleapp.Adapters.DataAdapter;
import com.winision.sampleapp.Modals.Modal;
import com.winision.sampleapp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersFragment extends Fragment {

    private SearchView searchView;
    private DataAdapter dataAdapter;

    public static UsersFragment newInstance() {
        return new UsersFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.users_fragment, container, false);
        ApiInterface apiClient = Client.getClient().create(ApiInterface.class);
        Call<List<Modal>> call = apiClient.getUserData();


        final RecyclerView recyclerView = view.findViewById(R.id.listView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        dataAdapter = new DataAdapter(this.getActivity());
        recyclerView.setAdapter(dataAdapter);

        call.enqueue(new Callback<List<Modal>>() {
            @Override
            public void onResponse(Call<List<Modal>> call, Response<List<Modal>> response) {
                List<Modal> data = response.body();
                dataAdapter.addData(data);
            }

            @Override
            public void onFailure(Call<List<Modal>> call, Throwable t) {

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
                dataAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                dataAdapter.getFilter().filter(query);
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