package ru.vaimon.provocab.screens.home.fragments.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.vaimon.provocab.R
import ru.vaimon.provocab.databinding.ItemExampleBinding
import ru.vaimon.provocab.models.Example

class ExampleRecyclerViewAdapter(
    var values: List<Example> = listOf()
) : RecyclerView.Adapter<ExampleRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemExampleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.example.text = item.englishExample
        holder.translation.text = item.translation
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemExampleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val example = binding.tvSentence
        val translation = binding.tvTranslation
    }

}