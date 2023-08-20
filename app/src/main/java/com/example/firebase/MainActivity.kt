package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.firebase.Fragment.HomeFragment
import com.example.firebase.Fragment.UserFragment
import com.example.firebase.adapter.viewPagerAdapter
import com.example.firebase.databinding.ActivityLoginBinding
import com.example.firebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTab()
    }

    private fun setupTab(){
        val adapter = viewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(UserFragment(), "User")

        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.home)
        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.person)

    }
}