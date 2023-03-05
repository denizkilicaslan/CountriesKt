package com.denizzz.countrieskotlin.service

import com.denizzz.countrieskotlin.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {

    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //BASEURL  https://raw.githubusercontent.com/
    // retrofit olusturulur


    private val BASEURL="https://raw.githubusercontent.com/"
    private val api=Retrofit.Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountryAPI::class.java)

    fun getData() : Single<List<Country>>{
        return api.getCountries()
        //CountryAPI interface member
    }




}