package com.denizzz.countrieskotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denizzz.countrieskotlin.model.Country

class CountryViewModel: ViewModel() {

    val countryLiveData= MutableLiveData<Country>()

    fun getDataFromRoom(){
        val country=Country("Turkey","Asia","Ankara","TL","Turkish","www.sss")
        countryLiveData.value=country
    }
}