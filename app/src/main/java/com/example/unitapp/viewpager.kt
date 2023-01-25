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
                    tab.text = "weight"
                    tab.setIcon(R.drawable.ic_baseline_fitness_center_24)
                }
                1->{
                    tab.text = "temperature"
                    tab.setIcon(R.drawable.ic_baseline_thermostat_24)
                }
                2->{
                    tab.text = "distance"
                    tab.setIcon(R.drawable.ic_baseline_run)
                }
                3->{
                    tab.text = "currency"
                    tab.setIcon(R.drawable.ic_baseline_currency_exchange_24)
                }
            }
        }.attach()


        return view
    }
}