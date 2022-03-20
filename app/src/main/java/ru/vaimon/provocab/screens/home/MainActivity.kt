package ru.vaimon.provocab.screens.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import ru.vaimon.provocab.R
import ru.vaimon.provocab.databinding.ActivityMainBinding
import ru.vaimon.provocab.models.Translation
import ru.vaimon.provocab.screens.home.adapters.TranslationStateAdapter

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var translationAdapter: TranslationStateAdapter
    private val mPresenter: MainContract.Presenter by lazy {
        MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
        setupAdapters()
        setupUI()
    }

    override fun onStart() {
        super.onStart()
        mPresenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        mPresenter.detachView()
    }

    private fun setupAdapters() {
        translationAdapter =
            TranslationStateAdapter(supportFragmentManager, lifecycle)
        binding.fragTranslation.viewPager.adapter = translationAdapter
    }

    private fun setupUI() {
//        binding.fragTranslation.tabLayout.apply {
//            addTab(this.newTab().setText(applicationContext.getString(R.string.meanings)))
//            addTab(this.newTab().setText(applicationContext.getString(R.string.examples)))
//            addOnTabSelectedListener
//        }
        TabLayoutMediator(
            binding.fragTranslation.tabLayout, binding.fragTranslation.viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = applicationContext.getString(R.string.meanings)
                }
                1 -> {
                    tab.text = applicationContext.getString(R.string.examples)
                }
            }
        }.attach()

    }

    private fun setupListeners() {
        binding.btnSearch.setOnClickListener {
            mPresenter.startWordSearch(binding.etWord.text.toString())
        }

        binding.etWord.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter.startWordSearch(binding.etWord.text.toString())
                val inputMethodManager =
                    applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.etWord.windowToken, 0)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    override fun showLoadingState() {
        TODO("Not yet implemented")
    }

    override fun showTranslation(translation: Translation) {
        //binding.tvResult.text = "${translation.word} ${translation.transcription} => [${translation.translations.joinToString(", ")}]\nCambridge: ${translation.cambridgeDefinitions.joinToString(separator = "\n")}\n\n${translation.examples.joinToString("\n")}"
        translationAdapter.updateValues(translation)
        binding.fragTranslation.tvWord.text = translation.word
        binding.fragTranslation.tvPronunciation.text = translation.pronunciation
        binding.fragTranslation.root.visibility = View.VISIBLE
    }

    override fun showError(reason: String) {

    }
}