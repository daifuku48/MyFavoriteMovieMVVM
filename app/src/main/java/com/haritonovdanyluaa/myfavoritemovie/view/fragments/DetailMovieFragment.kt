package com.haritonovdanyluaa.myfavoritemovie.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.haritonovdanyluaa.myfavoritemovie.databinding.FragmentDetailMovieBinding
import com.haritonovdanyluaa.myfavoritemovie.view.ViewModel.DetailViewModel
import com.haritonovdanyluaa.myfavoritemovie.view.ViewModel.DetailViewModelFactory
import com.haritonovdanyluaa.myfavoritemovie.view.ViewModel.MainViewModel
import com.haritonovdanyluaa.myfavoritemovie.view.ViewModel.MainViewModelFactory
import com.haritonovdanyluaa.myfavoritemovie.view.activity.MainActivity

class DetailMovieFragment : Fragment() {
    private var _binding : FragmentDetailMovieBinding? = null
    private lateinit var vm: DetailViewModel
    private val binding
        get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailMovieBinding.inflate(layoutInflater, container, false)
        vm = ViewModelProvider(this, DetailViewModelFactory(requireActivity().application))[DetailViewModel::class.java]
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = requireActivity().intent
        val title = intent.getStringExtra("title")
        vm.getDetailMovieFromApi(title!!)
        vm.detailMovie.observe(viewLifecycleOwner){
            binding?.detailName?.text = vm.detailMovie.value?.title
            binding?.detailActors?.text = vm.detailMovie.value?.actors
            binding?.detailGenre?.text = vm.detailMovie.value?.genre
            binding?.detailPlot?.text  = vm.detailMovie.value?.plot
            binding?.detailYear?.text = vm.detailMovie.value?.year
            Glide.with(requireContext())
                .load(vm.detailMovie.value?.poster)
                .skipMemoryCache(true)
                .centerCrop()
                .into(binding?.detailImage!!)
        }
        binding?.cancelButton?.setOnClickListener {
            val intentCancel = Intent(requireContext(), MainActivity::class.java)
            startActivity(intentCancel)
        }

        binding?.confirmButton?.setOnClickListener {
            vm.insertMovie()
            val intentCancel = Intent(requireContext(), MainActivity::class.java)
            startActivity(intentCancel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}