package ru.vaimon.provocab.screens.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.vaimon.provocab.databinding.FragmentExplanationBinding
import ru.vaimon.provocab.models.CambridgeDefinition
import ru.vaimon.provocab.screens.home.fragments.adapters.CambridgeRecyclerViewAdapter
import ru.vaimon.provocab.screens.home.fragments.adapters.TranslationRecyclerViewAdapter

class ExplanationFragment : Fragment() {
    lateinit var binding: FragmentExplanationBinding
    private val translationAdapter = TranslationRecyclerViewAdapter()
    private val cambridgeAdapter = CambridgeRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExplanationBinding.inflate(inflater,container,false)
        setupUI()
        return binding.root
    }

    private fun setupUI() {
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() {
        binding.rvCambridge.adapter = cambridgeAdapter
        binding.rvTranslation.adapter = translationAdapter
    }

    fun updateValues(translationValues: List<String>, cambridgeValues: List<CambridgeDefinition>){
        translationAdapter.values = translationValues
        translationAdapter.notifyDataSetChanged()
        cambridgeAdapter.values = cambridgeValues
        cambridgeAdapter.notifyDataSetChanged()
    }
}