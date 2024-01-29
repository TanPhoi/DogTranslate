package com.example.dogtranslate.ui.training.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogtranslate.data.remote.model.DataTraining
import com.example.dogtranslate.databinding.ItemTrainingBinding
import com.example.dogtranslate.util.setOnSingClickListener

class TrainingAdapter(val trainingList: ArrayList<DataTraining>) :
    RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>() {
    inner class TrainingViewHolder(val binding: ItemTrainingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(training: DataTraining) {
            binding.trainingData = training
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val binding =
            ItemTrainingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrainingViewHolder(binding)
    }

    var onItemClick: ((DataTraining) -> Unit)? = null
    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val data = trainingList[position]
        holder.bind(data)

        holder.itemView.setOnSingClickListener {
            onItemClick?.invoke(data)
        }
    }

    override fun getItemCount(): Int = if (trainingList.isNotEmpty()) trainingList.size else 0
}