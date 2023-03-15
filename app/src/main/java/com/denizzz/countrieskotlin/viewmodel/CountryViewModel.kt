package com.denizzz.countrieskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denizzz.countrieskotlin.model.Country
import com.denizzz.countrieskotlin.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application): BaseViewModel(application) {

    val countryLiveData= MutableLiveData<Country>()

    fun getDataFromRoom(uuid :Int){
        //val country=Country("Turkey","Asia","Ankara","TL","Turkish","www.sss")
        //countryLiveData.value=country
        launch {
            val dao=CountryDatabase(getApplication()).countryDao()
            val country=dao.getCountry(uuid)

            countryLiveData.value=country
        }

    }
}