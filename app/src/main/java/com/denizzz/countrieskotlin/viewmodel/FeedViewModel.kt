package com.denizzz.countrieskotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denizzz.countrieskotlin.model.Country
import com.denizzz.countrieskotlin.service.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

//her view in kendi ViewModel i olmalı
class FeedViewModel: ViewModel() {

    private val countryApiService=CountryAPIService()
    private val disposable=CompositeDisposable()

    val countries= MutableLiveData<List<Country>>()
    val countryError=MutableLiveData<Boolean>()
    val countryLoading=MutableLiveData<Boolean>()

 fun refreshData(){

//    getDataFromAPI()

     val country=Country("Turkey","Asia","Ankara","TL","Turkish","www.sss")
     val country1=Country("Turkey1","Asia1","Ankara","TL","Turkish","www.sss")
     val country2=Country("Turkey2","Asia2","Ankara","TL","Turkish","www.sss")


     val  countryList= arrayListOf<Country>(country,country1,country2)
     countries.value=countryList
     countryError.value= false
     countryLoading.value=false


 }

    /*private fun getDataFromAPI(){
        countryLoading.value=true
        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Country>>(){

                    override fun onSuccess(t: List<Country>) {
                        countries.value=t
                        countryLoading.value=true
                        countryError.value=false
                    }

                    override fun onError(e: Throwable) {
                        countryError.value=true
                        countryLoading.value=false
                        e.printStackTrace()

                    }

                })

        )

    }


     */


}