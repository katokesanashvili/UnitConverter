package com.example.unitapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class viewpager: Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_vpagerwnav, container, false)
        val viewpager = view.findViewById<ViewPager2>(R.id.pager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab)
        val viewpageradapter = fragmentManager?.let { viewpageradapter(it, lifecycle) }

        viewpager.adapter = viewpageradapter

        TabLayoutMediator(tabLayout, viewpager){tab, position->
            when(position){
                0->{
                    tab.text = "Kg"
                    tab.setIcon(R.drawable.ic_baseline_weight)
                }
                1->{
                    tab.text = "°C"
                    tab.setIcon(R.drawable.ic_baseline_temp)
                }
                2->{
                    tab.text = "Km"
                    tab.setIcon(R.drawable.ic_baseline_run)
                }
                3->{
                    tab.text = "Dollar"
                    tab.setIcon(R.drawable.ic_baseline_dollar)
                }
            }
        }.attach()


        return view
    }
}