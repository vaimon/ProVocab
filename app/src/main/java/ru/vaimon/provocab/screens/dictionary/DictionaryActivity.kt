package ru.vaimon.provocab.screens.dictionary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.realm.RealmResults
import ru.vaimon.provocab.databinding.ActivityDictionaryBinding
import ru.vaimon.provocab.models.Translation
import ru.vaimon.provocab.screens.dictionary.adapters.WordRecyclerViewAdapter

class DictionaryActivity : AppCompatActivity(), DictionaryContract.View {
    private val mPresenter: DictionaryContract.Presenter by lazy {
        DictionaryPresenter()
    }

    private lateinit var binding: ActivityDictionaryBinding
    private lateinit var wordAdapter: WordRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDictionaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapters()
        setupUI()
    }

    override fun onStart() {
        super.onStart()
        mPresenter.attachView(this)
        mPresenter.loadDictionary()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.detachView()
    }

    private fun setupAdapters() {
        wordAdapter =
            WordRecyclerViewAdapter(object : WordRecyclerViewAdapter.OnItemInteractionListener {
                override fun onItemInteraction(item: Translation) {

                }
            })
    }

    private fun setupUI() {
        binding.rvWords.adapter = wordAdapter
    }

    override fun setupValues(newValues: RealmResults<Translation>) {
        wordAdapter.setResults(newValues)
    }
}