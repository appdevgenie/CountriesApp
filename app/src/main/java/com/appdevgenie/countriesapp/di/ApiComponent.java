package com.appdevgenie.countriesapp.di;

import com.appdevgenie.countriesapp.model.CountriesService;
import com.appdevgenie.countriesapp.viewmodel.ListViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(CountriesService service);

    void inject(ListViewModel listViewModel);
}
