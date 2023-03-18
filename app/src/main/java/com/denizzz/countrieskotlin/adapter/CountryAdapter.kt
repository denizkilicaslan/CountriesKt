package com.denizzz.countrieskotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IntegerRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.denizzz.countrieskotlin.R
import com.denizzz.countrieskotlin.databinding.ItemCountryRowBinding
import com.denizzz.countrieskotlin.model.Country
import com.denizzz.countrieskotlin.util.downloadFromUrl
import com.denizzz.countrieskotlin.util.placeholderProgressBar
import com.denizzz.countrieskotlin.view.FeedFragmentDirections

class CountryAdapter(val countryList:ArrayList<Country>):RecyclerView.Adapter <CountryAdapter.CountryViewHolder>() ,CountryClickListener {

    class CountryViewHolder (var view: ItemCountryRowBinding): RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        //layout ile adapter bağlanır
        val inflater=LayoutInflater.from(parent.context)
        //val view=inflater.inflate(R.layout.item_country_row,parent,false)
        val view=DataBindingUtil.inflate<ItemCountryRowBinding>(inflater,R.layout.item_country_row,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
     //item_country_row  item lara ulasılıabılır
        holder.view.country=countryList[position]
        holder.view.listener=this



     /* old vers

       var country_name=holder.view.findViewById<TextView>(R.id.country_name_r)
        var country_region=holder.view.findViewById<TextView>(R.id.country_region_r)

        country_name.text=countryList[position].countryName
        country_region.text=countryList[position].countryRegion


        holder.view.setOnClickListener {
            val actions=FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            //actions.countryUuid
            Navigation.findNavController(it).navigate(actions)
        }
        var country_img=holder.view.findViewById<ImageView>(R.id.imageView)
        country_img.downloadFromUrl(countryList[position].imageUrl, placeholderProgressBar(holder.view.context))
      */


    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList:List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        //super.onCountryClicked(v)
       var uuid=v.findViewById<TextView>(R.id.countryUuidText_r)
        var x=Integer.parseInt(uuid.toString())
        val actions=FeedFragmentDirections.actionFeedFragmentToCountryFragment(x)
        //actions.countryUuid
        Navigation.findNavController(v).navigate(actions)
    }
}