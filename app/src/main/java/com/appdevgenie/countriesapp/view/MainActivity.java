package com.appdevgenie.countriesapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appdevgenie.countriesapp.R;
import com.appdevgenie.countriesapp.model.CountryModel;
import com.appdevgenie.countriesapp.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.countriesList)
    RecyclerView countriesList;

    @BindView(R.id.list_error)
    TextView listError;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;

    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    private ListViewModel listViewModel;
    private CountryListAdapter adapter = new CountryListAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);
        listViewModel.refresh();

        countriesList.setLayoutManager(new LinearLayoutManager(this));
        countriesList.setAdapter(adapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listViewModel.refresh();
                refreshLayout.setRefreshing(false);
            }
        });

        observerViewModel();
    }

    private void observerViewModel() {

        listViewModel.countries.observe(this, new Observer<List<CountryModel>>() {
            @Override
            public void onChanged(List<CountryModel> countryModels) {
                if(countryModels != null){
                    countriesList.setVisibility(View.VISIBLE);
                    adapter.updateCountries(countryModels);
                }
            }
        });

        listViewModel.countryLoadError.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isError) {
                if(isError != null){
                    listError.setVisibility(isError ? View.VISIBLE : View.GONE);
                }
            }
        });

        listViewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if(isLoading != null){
                    loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                    if(isLoading){
                        listError.setVisibility(View.GONE);
                        countriesList.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
}