package ru.vaimon.provocab.screens.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ru.vaimon.provocab.R
import ru.vaimon.provocab.databinding.ActivityMainBinding
import ru.vaimon.provocab.models.Translation
import ru.vaimon.provocab.screens.dictionary.DictionaryActivity
import ru.vaimon.provocab.views.word_review.WordReviewFragment

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding

    private val mPresenter: MainContract.Presenter by lazy {
        MainPresenter()
    }

    private var wordReviewFragment: WordReviewFragment? = null

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
    }

    private fun setupUI() {

    }

    private fun hideKeyboard() {
        val inputMethodManager =
            applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.etWord.windowToken, 0)
    }

    private fun startDictionaryActivity() {
        startActivity(Intent(this, DictionaryActivity::class.java))
    }

    private fun setupListeners() {
        binding.btnSearch.setOnClickListener {
            hideKeyboard()
            mPresenter.startWordSearch(binding.etWord.text.toString())
        }

        binding.btnDictionary.setOnClickListener {
            startDictionaryActivity()
        }

        binding.etWord.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                mPresenter.startWordSearch(binding.etWord.text.toString())
                hideKeyboard()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    override fun showLoadingState() {
        binding.progressIndicator.visibility = View.VISIBLE
        binding.fragTranslation.visibility = View.GONE
    }

    override fun showTranslation(translation: Translation) {
        wordReviewFragment?.apply {
            this.updateData(translation)
        } ?: run {
            wordReviewFragment = WordReviewFragment(translation)
            supportFragmentManager.beginTransaction()
                .add(R.id.fragTranslation, wordReviewFragment!!)
                .commit()
        }
        binding.fragTranslation.visibility = View.VISIBLE
        binding.progressIndicator.visibility = View.GONE
    }

    override fun showError(reason: String) {
        binding.progressIndicator.visibility = View.GONE
        Snackbar.make(
            binding.root,
            "Произошла ошибка: $reason",
            BaseTransientBottomBar.LENGTH_SHORT
        ).show()
    }
}