package com.denizzz.countrieskotlin.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.denizzz.countrieskotlin.model.Country


@Database(entities= arrayOf(Country::class), version =1)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao():CountryDao


    //Singleton
    companion object{

        @Volatile private var instance:CountryDatabase?=null
        private val lock=Any()

        // sync cunku farklı threadlerden ulasıldıgında tek bı yerden ulasım saglansın
        operator fun invoke(context: Context)= instance ?: synchronized(lock){
            instance?: makeDatabase(context).also {
                instance=it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,CountryDatabase::class.java,"countrydatabase"
        ).build()




    }

}