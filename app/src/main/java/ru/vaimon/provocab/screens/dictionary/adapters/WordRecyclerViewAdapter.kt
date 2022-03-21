package ru.vaimon.provocab.screens.dictionary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmResults
import ru.vaimon.provocab.databinding.ItemWordBinding
import ru.vaimon.provocab.models.Translation

class WordRecyclerViewAdapter(
    private val mListener: OnItemInteractionListener? = null
) : RecyclerView.Adapter<WordRecyclerViewAdapter.ViewHolder>() {
    var observableResults: RealmResults<Translation>? = null

    private val values: List<Translation>
        get() {
            return observableResults?.toList() ?: listOf()
        }

    private val mOnClickListener = View.OnClickListener {
        val item = it.tag as Translation
        mListener?.onItemInteraction(item)
    }

    fun setResults(newValues: RealmResults<Translation>){
        observableResults = newValues
        notifyDataSetChanged()
        observableResults?.addChangeListener { t, changeSet ->
            changeSet.changes.forEach {
                notifyItemChanged(it)
            }
            changeSet.deletions.forEach {
                notifyItemRemoved(it)
            }
            changeSet.insertions.forEach {
                notifyItemInserted(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.word.text = item.word

        mListener?.also {
            with(holder.itemView) {
                tag = item
                setOnClickListener(mOnClickListener)
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemWordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val word = binding.tvWord
    }

    interface OnItemInteractionListener {
        fun onItemInteraction(item: Translation)
    }

}