package com.denizzz.countrieskotlin.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denizzz.countrieskotlin.model.Country
import com.denizzz.countrieskotlin.service.CountryAPIService
import com.denizzz.countrieskotlin.service.CountryDatabase
import com.denizzz.countrieskotlin.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

//her view in kendi ViewModel i olmalı
class FeedViewModel(application: Application): BaseViewModel(application) {

    private val countryApiService=CountryAPIService()
    private val disposable=CompositeDisposable()
    private var customPreferences=CustomSharedPreferences(getApplication())

    private var refreshTime =10 * 60 * 1000 * 1000 * 1000L


    val countries= MutableLiveData<List<Country>>()
    val countryError=MutableLiveData<Boolean>()
    val countryLoading=MutableLiveData<Boolean>()

 fun refreshData(){

     val updateTime=customPreferences.getTıme()
     if (updateTime != null && updateTime!=0L && System.nanoTime()-updateTime < refreshTime) {
         getDataFromSQlite()
     }else{
         getDataFromAPI()

     }
/*
     val country=Country("Turkey","Asia","Ankara","TL","Turkish","www.sss")
     val country1=Country("Turkey1","Asia1","Ankara","TL","Turkish","www.sss")
     val country2=Country("Turkey2","Asia2","Ankara","TL","Turkish","www.sss")


     val  countryList= arrayListOf<Country>(country,country1,country2)
     countries.value=countryList
     countryError.value= false
     countryLoading.value=false



 */
 }

    private fun getDataFromSQlite() {
        launch {
            val countries=CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(),"Countries from SQlite",Toast.LENGTH_LONG).show()
        }


    }

    private fun getDataFromAPI(){
        countryLoading.value=true
        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Country>>(){

                    override fun onSuccess(t: List<Country>) {
                       /* countries.value=t
                        countryLoading.value=false
                        countryError.value=false
                        println(t)
                        */
                        storeInSQlite(t)
                        Toast.makeText(getApplication(),"Countries from API",Toast.LENGTH_LONG).show()

                    }

                    override fun onError(e: Throwable) {
                        countryError.value=true
                        countryLoading.value=false
                        e.printStackTrace()

                    }

                })

        )
    }

    private fun showCountries( countryLst: List<Country>){
        countries.value=countryLst
        countryLoading.value=false
        countryError.value=false
        println(countryLst)
    }

    private fun storeInSQlite(list: List<Country>){
        //coroutine

        launch {
            val dao=CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong=dao.insertAll(*list.toTypedArray()) //list -> individual
            var i=0;
            while (i<list.size){
                list[i].uuid=listLong[i].toInt()
                i++
            }
            showCountries(list)

        }

        customPreferences.saveTime(System.nanoTime())

    }






}