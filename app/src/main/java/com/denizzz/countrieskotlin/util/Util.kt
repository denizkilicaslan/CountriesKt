package com.denizzz.countrieskotlin.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.denizzz.countrieskotlin.R


//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

/*
fun String.myExxtension( myParameter:String){
    println(myParameter)
    }
 */

fun ImageView.downloadFromUrl( url:String?,progressDrawable:CircularProgressDrawable){

    val options=RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}


fun placeholderProgressBar(context:Context,):CircularProgressDrawable{
  return CircularProgressDrawable(context).apply {
      centerRadius=40f
      strokeWidth=8f
      start()
  }
}