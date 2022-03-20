package ru.vaimon.provocab.screens.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.vaimon.provocab.databinding.FragmentExampleBinding
import ru.vaimon.provocab.models.Example
import ru.vaimon.provocab.screens.home.fragments.adapters.ExampleRecyclerViewAdapter

class ExampleFragment : Fragment() {
    lateinit var binding: FragmentExampleBinding
    private val exampleAdapter = ExampleRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExampleBinding.inflate(inflater,container,false)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() {
        binding.rvExamples.adapter = exampleAdapter
    }

    fun updateValues(newValues: List<Example>){
        exampleAdapter.values = newValues
        exampleAdapter.notifyDataSetChanged()
    }
}