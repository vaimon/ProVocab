package ru.vaimon.provocab.screens.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.vaimon.provocab.R
import ru.vaimon.provocab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUI()
    }

    private fun setupUI() {

    }
}