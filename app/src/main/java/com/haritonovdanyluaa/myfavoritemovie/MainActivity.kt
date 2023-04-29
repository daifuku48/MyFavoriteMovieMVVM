package com.haritonovdanyluaa.myfavoritemovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.haritonovdanyluaa.myfavoritemovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    private var viewPagerAdapter: ViewPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding?.tabLayout?.addTab(binding?.tabLayout?.newTab()!!.setText("Favorites"))
        binding?.tabLayout?.addTab(binding?.tabLayout?.newTab()!!.setText("Movies"))

        val fragmentManager = supportFragmentManager
        viewPagerAdapter = ViewPagerAdapter(fragmentManager, lifecycle)
        binding?.viewPager?.adapter = viewPagerAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        viewPagerAdapter = null
    }
}