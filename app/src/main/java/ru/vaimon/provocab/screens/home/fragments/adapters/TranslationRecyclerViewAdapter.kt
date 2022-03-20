package ru.vaimon.provocab.screens.home.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vaimon.provocab.databinding.ItemTransalationBinding

class TranslationRecyclerViewAdapter(var values: List<String> = listOf()) :
    RecyclerView.Adapter<TranslationRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTransalationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.translation.text = item
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemTransalationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val translation = binding.tvTranslation
    }
}