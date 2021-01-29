package com.appdevgenie.countriesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.appdevgenie.countriesapp.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>> countries = new MutableLiveData<>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public void refresh(){
        fetchCountries();
    }

    private void fetchCountries(){

        // dummy data
        CountryModel country1 = new CountryModel("test1", "test1", "");
        CountryModel country2 = new CountryModel("test2", "test2", "");
        CountryModel country3 = new CountryModel("test3", "test3", "");
        CountryModel country4 = new CountryModel("test4", "test4", "");
        CountryModel country5 = new CountryModel("test5", "test5", "");
        CountryModel country6 = new CountryModel("test6", "test6", "");

        List<CountryModel> countryModelList = new ArrayList<>();
        countryModelList.add(country1);
        countryModelList.add(country2);
        countryModelList.add(country3);
        countryModelList.add(country4);
        countryModelList.add(country5);
        countryModelList.add(country6);

        countries.setValue(countryModelList);
        countryLoadError.setValue(false);
        loading.setValue(false);
    }
}
