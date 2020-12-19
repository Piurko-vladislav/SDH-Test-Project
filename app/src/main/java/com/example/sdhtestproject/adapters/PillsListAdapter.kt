package com.example.sdhtestproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sdhtestproject.databinding.PillRecyclerItemBinding
import com.example.sdhtestproject.models.Results
import com.google.android.material.card.MaterialCardView
import java.util.ArrayList

class PillsListAdapter(var listener: PillsAdapterListener) :
    RecyclerView.Adapter<PillsListAdapter.ViewHolder>() {
    private var pillsList: List<Results> = ArrayList<Results>()

    fun setData(list: List<Results>) {
        pillsList = list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = PillRecyclerItemBinding.inflate(layoutInflater, parent, false)

        val itemView = binding.root

        return ViewHolder(itemView, binding, listener)
    }

    override fun getItemCount(): Int {
        return pillsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(pillsList)
    }


    class ViewHolder(itemView: View, binding: PillRecyclerItemBinding, private val listener: PillsAdapterListener) :
        RecyclerView.ViewHolder(itemView) {

        var cardView: MaterialCardView = binding.pillCardView
        var tradeLabel: TextView = binding.pillTradeLabel
        var manufacturerName: TextView = binding.pillManufacturerName

        fun bind(pillsList: List<Results>) {
            tradeLabel.setText(pillsList.get(adapterPosition).trade_label?.name)
            manufacturerName.setText(pillsList.get(adapterPosition).manufacturer?.name)

            itemView.setOnClickListener {
                listener.onPillClicked(cardView, pillsList.get(adapterPosition))
            }
        }
    }

    interface PillsAdapterListener {
        fun onPillClicked(cardView: View, results: Results)
    }

}