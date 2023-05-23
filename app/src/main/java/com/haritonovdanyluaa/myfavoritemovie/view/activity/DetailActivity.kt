package com.haritonovdanyluaa.myfavoritemovie.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.haritonovdanyluaa.myfavoritemovie.databinding.ActivityDetailBinding
import com.haritonovdanyluaa.myfavoritemovie.databinding.ActivityMainBinding
import com.haritonovdanyluaa.myfavoritemovie.view.adapters.ViewPagerDetailAdapter

class DetailActivity : AppCompatActivity() {
    private lateinit var navController : NavController
    private var binding : ActivityDetailBinding? = null
    private var viewPagerAdapter: ViewPagerDetailAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val fragmentManager = supportFragmentManager
        viewPagerAdapter = ViewPagerDetailAdapter(fragmentManager, lifecycle)
        binding?.viewPager2?.adapter = viewPagerAdapter

    }
}