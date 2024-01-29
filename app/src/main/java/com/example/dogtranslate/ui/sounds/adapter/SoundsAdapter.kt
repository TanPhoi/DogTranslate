package com.example.dogtranslate.ui.sounds.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogtranslate.data.local.model.Dog
import com.example.dogtranslate.databinding.ItemDogBinding
import com.example.dogtranslate.util.setOnSingClickListener

class SoundsAdapter(private val dogList: ArrayList<Dog>) :
    RecyclerView.Adapter<SoundsAdapter.SoundsViewHolder>() {
    inner class SoundsViewHolder(val binding: ItemDogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dog: Dog) {
            binding.dog = dog
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundsViewHolder {
        val binding = ItemDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SoundsViewHolder(binding)
    }

    var itemOnClick: ((Dog) -> Unit)? = null
    override fun onBindViewHolder(holder: SoundsViewHolder, position: Int) {
        val dog = dogList[position]
        holder.bind(dog)

        dog.srcDog.let {
            holder.binding.imgDog.setImageResource(it!!)
        }

        holder.itemView.setOnSingClickListener {
            itemOnClick?.invoke(dog)
        }
    }

    override fun getItemCount(): Int = if (dogList.isNotEmpty()) dogList.size else 0

}