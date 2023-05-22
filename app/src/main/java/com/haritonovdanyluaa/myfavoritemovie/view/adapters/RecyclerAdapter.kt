package com.haritonovdanyluaa.myfavoritemovie.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haritonovdanyluaa.myfavoritemovie.R
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data.Movie

class RecyclerAdapter(var list : List<Movie>,
                      var context: Context
)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private lateinit var mListener : OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

     class ViewHolder(view: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(view){
         val image: ImageView
         val title: TextView
         val year: TextView

         init {
             title = view.findViewById(R.id.titleRecyclerView)
             image = view.findViewById(R.id.imageRecyclerView)
             year = view.findViewById(R.id.year_recycler_view)
             view.setOnClickListener {
                 listener.onItemClick(adapterPosition)
             }
         }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_recycler_item, parent, false)
        return ViewHolder(view, mListener)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title.text = list[position].Title
        viewHolder.year.text = list[position].Year
        Glide.with(context)
            .load(list[position].Poster)
            .skipMemoryCache(true)
            .centerCrop()
            .into(viewHolder.image)
    }

}