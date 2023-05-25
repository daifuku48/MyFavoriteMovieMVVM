package com.haritonovdanyluaa.myfavoritemovie.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haritonovdanyluaa.myfavoritemovie.R
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data.Movie
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.MovieEntity


class RecyclerAdapterFavorites(var list : List<MovieEntity>,
                      var context: Context
)
    : RecyclerView.Adapter<RecyclerAdapterFavorites.ViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterFavorites.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_recycler_item, parent, false)
        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterFavorites.ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.year.text = list[position].year
        Glide.with(context)
            .load(list[position].poster)
            .skipMemoryCache(true)
            .centerCrop()
            .into(holder.image)
    }

    override fun getItemCount() = list.size
}