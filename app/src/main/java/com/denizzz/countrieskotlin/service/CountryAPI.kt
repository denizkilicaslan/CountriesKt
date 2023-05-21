package com.denizzz.countrieskotlin.service

import com.denizzz.countrieskotlin.model.Country
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface CountryAPI {

    //GET POST
    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //BASEURL  https://raw.githubusercontent.com/
    //EXT     atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    //fun getCountries():Call<> retrofit
    fun getCountries():Single<List<Country>>
    //nasıl bir call yapılacağı call observable
    //https://bugfender.com/blog/data-flows-in-rxjava2-observable-flowable-single-maybe-completable/
}
