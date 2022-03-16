package ru.vaimon.provocab.screens.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.vaimon.provocab.databinding.ActivityMainBinding
import ru.vaimon.provocab.models.CambridgeDefinition

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
    }

    override fun showLoadingState() {
        TODO("Not yet implemented")
    }

    override fun showTranslation(cambridgeDefinitions: List<CambridgeDefinition>){
        binding.tvResult.text = cambridgeDefinitions.joinToString(separator = "\n")
    }

    override fun showError(reason: String) {
        binding.tvResult.text = reason
    }
}