package com.denizzz.countrieskotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denizzz.countrieskotlin.model.Country

//her view in kendi ViewModel i olmalı
class FeedViewModel: ViewModel() {
    val countries= MutableLiveData<List<Country>>()
    val countryError=MutableLiveData<Boolean>()
    val countryLoading=MutableLiveData<Boolean>()

 fun refreshData(){
     val country=Country("Turkey","Asia","Ankara","TL","Turkish","www.sss")
     val country1=Country("Turkey1","Asia1","Ankara","TL","Turkish","www.sss")
     val country2=Country("Turkey2","Asia2","Ankara","TL","Turkish","www.sss")


     val  countryList= arrayListOf<Country>(country,country1,country2)
     countries.value=countryList
     countryError.value= false
     countryLoading.value=false


 }



}