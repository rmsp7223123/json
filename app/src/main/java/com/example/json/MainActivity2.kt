package com.example.json

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.json.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding : ActivityMain2Binding;
    private lateinit var newsNavHostFragment : NavHostFragment;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater);
        setContentView(binding.root);
        newsNavHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment;

        binding.bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController());
    }
}