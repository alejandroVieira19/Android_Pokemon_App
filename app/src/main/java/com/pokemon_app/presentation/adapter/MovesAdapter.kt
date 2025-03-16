package com.pokemon_app.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovesAdapter : RecyclerView.Adapter<MovesAdapter.MoveViewHolder>() {
    private val movesList = mutableListOf<String>()

    fun submitList(newList: List<String>) {
        movesList.clear()
        movesList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return MoveViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        holder.bind(movesList[position])
    }

    override fun getItemCount() = movesList.size

    class MoveViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(move: String) {
            (view as TextView).text = move
        }
    }
}
