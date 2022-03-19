package ru.vaimon.provocab.screens.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import ru.vaimon.provocab.databinding.ActivityMainBinding
import ru.vaimon.provocab.models.CambridgeDefinition
import ru.vaimon.provocab.models.Translation

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding
    private val mPresenter: MainContract.Presenter by lazy {
        MainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
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
        binding.btnSearch.setOnClickListener {
            mPresenter.startWordSearch(binding.etWord.text.toString())
        }

        binding.etWord.setOnEditorActionListener { _, i, _ ->
            if(i == EditorInfo.IME_ACTION_SEARCH){
                mPresenter.startWordSearch(binding.etWord.text.toString())
                val inputMethodManager = applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.etWord.windowToken,0)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    override fun showLoadingState() {
        TODO("Not yet implemented")
    }

    override fun showTranslation(translation: Translation){
        binding.tvResult.text = "${translation.word} ${translation.transcription} => [${translation.translations.joinToString(", ")}]\nCambridge: ${translation.cambridgeDefinitions.joinToString(separator = "\n")}\n\n${translation.examples.joinToString("\n")}"
    }

    override fun showError(reason: String) {
        binding.tvResult.text = reason
    }
}