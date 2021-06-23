package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.databinding.ItemAsteroidBinding

interface OnClickAsteroid {
    fun onClickAsteroid(selectedAsteroid: Asteroid)
}

//TODO Futuramente usar DiffUtil, sugestão dada pelo Revisor
class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var asteroidList = listOf<Asteroid>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var listener: OnClickAsteroid? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MainViewHolder.from(parent)
    }

    override fun getItemCount(): Int = asteroidList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainViewHolder -> {
                holder.bind(asteroidList[position], listener)
            }
        }
    }

    fun setListener(listener: OnClickAsteroid) {
        this.listener = listener
    }

    class MainViewHolder private constructor(private val binding: ItemAsteroidBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Asteroid, listener: OnClickAsteroid?) {
            binding.asteroid = item
            binding.onClickAsteroidListener = listener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MainViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAsteroidBinding.inflate(layoutInflater, parent, false)

                return MainViewHolder(binding)
            }
        }
    }
}