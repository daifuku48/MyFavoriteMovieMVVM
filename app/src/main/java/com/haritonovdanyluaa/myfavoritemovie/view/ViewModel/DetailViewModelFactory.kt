package com.haritonovdanyluaa.myfavoritemovie.view.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.Repository


class DetailViewModelFactory(var application: Application) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(application) as T
    }

    private val getAppRepository by lazy(LazyThreadSafetyMode.NONE) {
        Repository(application = application)
    }

}