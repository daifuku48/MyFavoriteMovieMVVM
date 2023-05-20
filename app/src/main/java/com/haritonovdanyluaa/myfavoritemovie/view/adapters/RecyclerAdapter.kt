package com.haritonovdanyluaa.myfavoritemovie.view.adapters

import android.content.Context
import android.media.session.PlaybackState.CustomAction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.haritonovdanyluaa.myfavoritemovie.R
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data.SearchData

class RecyclerAdapter(var list : LiveData<SearchData>,
                      var liveCycleOwner: LifecycleOwner,
                      var context: Context
)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {



     class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
         val image: ImageView
         val title: TextView
         val year: TextView

         init {
             title = view.findViewById(R.id.titleRecyclerView)
             image = view.findViewById(R.id.imageRecyclerView)
             year = view.findViewById(R.id.year_recycler_view)
         }
     }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.value?.Search?.size ?: 3

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d("image", list.value?.Search?.get(position)?.Poster.toString())
        viewHolder.title.text = list.value?.Search?.get(position)?.Title
        viewHolder.year.text = list.value?.Search?.get(position)?.Year
        Glide.with(context)
            .load(list.value?.Search?.get(position)?.Poster.toString())
            .skipMemoryCache(true)
            .centerCrop()
            .into(viewHolder.image)
    }

}