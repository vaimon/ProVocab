package ru.vaimon.provocab.screens.home.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vaimon.provocab.databinding.ItemCambridgeBinding
import ru.vaimon.provocab.models.CambridgeDefinition

class CambridgeRecyclerViewAdapter (var values: List<CambridgeDefinition> = listOf()) :
    RecyclerView.Adapter<CambridgeRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCambridgeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.example.text = item.example
        holder.sentence.text = item.meaning
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemCambridgeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val example = binding.tvExample
        val sentence = binding.tvSentence
    }
}