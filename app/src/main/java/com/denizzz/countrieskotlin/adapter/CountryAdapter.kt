package com.denizzz.countrieskotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.denizzz.countrieskotlin.R
import com.denizzz.countrieskotlin.model.Country

class CountryAdapter(val countryList:ArrayList<Country>):RecyclerView.Adapter <CountryAdapter.CountryViewHolder>() {

   // val countryName = view.findViewById<TextView>(R.id.country_name)
   // val countryName=v
    //val messageButton = view.findViewById<Button>(R.id.message_button)

    class CountryViewHolder (var view: View): RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        //layout ile adapter bağlanır
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.item_country_row,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
     //item_country_row  item lara ulasılıabılır
        var country_name=holder.view.findViewById<TextView>(R.id.country_name_r)
        var country_region=holder.view.findViewById<TextView>(R.id.country_region_r)

        country_name.text=countryList[position].countryName
        country_region.text=countryList[position].countryRegion
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList:List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}