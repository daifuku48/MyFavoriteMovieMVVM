package com.haritonovdanyluaa.myfavoritemovie.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.haritonovdanyluaa.myfavoritemovie.R
import com.haritonovdanyluaa.myfavoritemovie.databinding.ActivityMainBinding
import com.haritonovdanyluaa.myfavoritemovie.view.adapters.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var navController : NavController
    private var binding : ActivityMainBinding? = null
    private var viewPagerAdapter: ViewPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.tabLayout?.addTab(binding?.tabLayout?.newTab()!!.setText("Search"))
        binding?.tabLayout?.addTab(binding?.tabLayout?.newTab()!!.setText("Favorites"))

        val fragmentManager = supportFragmentManager
        viewPagerAdapter = ViewPagerAdapter(fragmentManager, lifecycle)
        binding?.viewPager?.adapter = viewPagerAdapter

        binding?.tabLayout?.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding?.viewPager?.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })


        binding?.viewPager?.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding?.tabLayout?.selectTab(binding?.tabLayout?.getTabAt(position))
                super.onPageSelected(position)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        viewPagerAdapter = null
    }
}