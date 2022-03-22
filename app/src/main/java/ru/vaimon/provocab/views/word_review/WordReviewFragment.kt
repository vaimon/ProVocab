package ru.vaimon.provocab.views.word_review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ru.vaimon.provocab.R
import ru.vaimon.provocab.databinding.FragmentWordReviewBinding
import ru.vaimon.provocab.models.Translation
import ru.vaimon.provocab.screens.home.MainContract
import ru.vaimon.provocab.screens.home.MainPresenter
import ru.vaimon.provocab.views.word_review.adapters.TranslationStateAdapter

class WordReviewFragment(var data: Translation) : Fragment(), WordReviewContract.View {
    private lateinit var binding: FragmentWordReviewBinding
    private lateinit var translationAdapter: TranslationStateAdapter

    private val mPresenter: WordReviewContract.Presenter by lazy {
        WordReviewPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWordReviewBinding.inflate(inflater,container,false)
        setupListeners()
        setupAdapters()
        setupUI()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        mPresenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        mPresenter.detachView()
    }

    private fun setupListeners() {
        binding.btnSave.setOnClickListener {
            if(binding.btnSave.isChecked){
                mPresenter.saveWord(translationAdapter.currentWord ?: throw IllegalArgumentException("We don't have the word? Impossible."))
            } else{
                mPresenter.deleteWord(translationAdapter.currentWord ?: throw IllegalArgumentException("We don't have the word? Impossible."))
            }
            //binding.btnSave.isChecked = !binding.btnSave.isChecked
        }
    }

    private fun setupAdapters() {
        translationAdapter =
            TranslationStateAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = translationAdapter
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = context?.getString(R.string.meanings)
                }
                1 -> {
                    tab.text = context?.getString(R.string.examples)
                }
            }
        }.attach()
    }

    private fun setupUI() {
        binding.tvWord.text = data.word
        binding.tvPronunciation.text = data.pronunciation
        translationAdapter.updateValues(data)
        binding.btnSave.isChecked = mPresenter.checkWordPresence(data.word)
    }

    fun updateData(newData: Translation){
        data = newData
        setupUI()
    }

}