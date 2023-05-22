package com.haritonovdanyluaa.myfavoritemovie.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.haritonovdanyluaa.myfavoritemovie.R
import com.haritonovdanyluaa.myfavoritemovie.databinding.FragmentFavoritesBinding
import com.haritonovdanyluaa.myfavoritemovie.databinding.FragmentSearchBinding
import com.haritonovdanyluaa.myfavoritemovie.view.ViewModel.MainViewModel
import com.haritonovdanyluaa.myfavoritemovie.view.ViewModel.MainViewModelFactory
import com.haritonovdanyluaa.myfavoritemovie.view.adapters.RecyclerAdapter
import org.koin.dsl.koinApplication

class SearchFragment : Fragment() {
    private var _binding : FragmentSearchBinding? = null
    private val binding get() = _binding
    private lateinit var vm: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this, MainViewModelFactory(requireActivity().application))[MainViewModel::class.java]
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireActivity())

        vm.movieList.observe(viewLifecycleOwner){ items ->
            val adapter = RecyclerAdapter(items.Search, requireContext())
            adapter.setOnItemClickListener(object : RecyclerAdapter.OnItemClickListener{
                override fun onItemClick(position: Int) {

                        items.Search[position].Title
                }
            })
            binding?.recyclerView?.adapter = adapter
        }

        binding?.recyclerView?.layoutManager = layoutManager
        binding?.searchImg?.setOnClickListener {
            vm.getMovieFromApi(binding?.searchEditText?.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}