package com.denizzz.countrieskotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denizzz.countrieskotlin.model.Country

//her view in kendi ViewModel i olmalı
class FeedViewModel: ViewModel() {
    val countries= MutableLiveData<List<Country>>()
    val countryError=MutableLiveData<List<Boolean>>()
    val countryLoading=MutableLiveData<List<Boolean>>()
    
    
}