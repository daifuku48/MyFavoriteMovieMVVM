package com.haritonovdanyluaa.myfavoritemovie.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.haritonovdanyluaa.myfavoritemovie.databinding.FragmentFavoritesBinding
import com.haritonovdanyluaa.myfavoritemovie.view.ViewModel.DetailViewModel
import com.haritonovdanyluaa.myfavoritemovie.view.ViewModel.MainViewModel
import com.haritonovdanyluaa.myfavoritemovie.view.ViewModel.MainViewModelFactory
import com.haritonovdanyluaa.myfavoritemovie.view.activity.DetailActivity
import com.haritonovdanyluaa.myfavoritemovie.view.adapters.RecyclerAdapter
import com.haritonovdanyluaa.myfavoritemovie.view.adapters.RecyclerAdapterFavorites

class FavoritesFragment : Fragment() {
    private var _binding : FragmentFavoritesBinding? = null
    private val binding get() = _binding
    private lateinit var vm: MainViewModel
    var mainViewModel : MainViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this, MainViewModelFactory(requireActivity().application))[MainViewModel::class.java]
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.layoutManager = linearLayoutManager
        vm.getAllMovies()
        vm.movies.observe(viewLifecycleOwner){ items ->
            if (items != null)
            {
                val adapter = RecyclerAdapterFavorites(items, requireContext())
                adapter.setOnItemClickListener(object : RecyclerAdapterFavorites.OnItemClickListener{
                    override fun onItemClick(position: Int) {
                        val intent = Intent(requireContext(), DetailActivity::class.java)
                        intent.putExtra("title", items[position].title)
                        intent.putExtra("deleteButton", true)
                        startActivity(intent)
                    }
                })
                binding?.recyclerView?.adapter = adapter
            }
        }

    }

}