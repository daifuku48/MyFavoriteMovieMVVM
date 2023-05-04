package com.haritonovdanyluaa.myfavoritemovie.view.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.Repository

class MainViewModelFactory(var application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(application) as T
    }

    private val getAppRepository by lazy(LazyThreadSafetyMode.NONE) {
        Repository(application = application)
    }

}