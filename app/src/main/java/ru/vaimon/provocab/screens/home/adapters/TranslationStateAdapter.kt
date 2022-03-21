package ru.vaimon.provocab.screens.home.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.vaimon.provocab.models.Translation
import ru.vaimon.provocab.screens.home.fragments.ExampleFragment
import ru.vaimon.provocab.screens.home.fragments.ExplanationFragment

class TranslationStateAdapter(fm: FragmentManager, lc: Lifecycle) : FragmentStateAdapter(fm, lc) {
    private val exampleFragment = ExampleFragment()
    private val explanationFragment = ExplanationFragment()
    var currentWord: Translation? = null

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return explanationFragment
            1 -> return exampleFragment
            else -> throw IllegalArgumentException("There are only 2 tabs available")
        }
    }

    fun updateValues(newData: Translation){
        currentWord = newData
        exampleFragment.updateValues(newData.examples.toList())
        explanationFragment.updateValues(newData.translations, newData.cambridgeDefinitions)
    }
}