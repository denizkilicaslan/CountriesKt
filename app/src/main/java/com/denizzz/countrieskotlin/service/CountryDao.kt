package com.denizzz.countrieskotlin.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.denizzz.countrieskotlin.model.Country

@Dao
interface CountryDao {

    //Data Access Obj
    // Insert -> INSERT INTO
    // suspend -> coroutine, pause & resume
    // vararg->multiple country objs
    // List<Long> -> Primary Keys

    @Insert
    suspend fun insertAll(vararg countries:Country):List<Long>

    @Query("SELECT * FROM country")
    suspend fun getAllCountries():List<Country>

    @Query("SELECT * FROM country WHERE uuid=:countryId")
    suspend fun getCountry(countryId:Int):Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()

}