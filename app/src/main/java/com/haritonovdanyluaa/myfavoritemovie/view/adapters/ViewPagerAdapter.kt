package com.haritonovdanyluaa.myfavoritemovie.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.haritonovdanyluaa.myfavoritemovie.view.fragments.FavoritesFragment
import com.haritonovdanyluaa.myfavoritemovie.view.fragments.SearchFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            SearchFragment()
        } else FavoritesFragment()
    }

}