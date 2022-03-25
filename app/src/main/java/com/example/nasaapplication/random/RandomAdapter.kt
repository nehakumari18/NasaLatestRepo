package com.example.nasaapplication.random

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nasaapplication.R
import com.example.nasaapplication.databinding.AdapterApodItemBinding
import com.example.nasaapplication.model.Apod

class RandomAdapter(
        private var onAdd: (Apod) -> Unit
) : ListAdapter<Apod, RandomAdapter.ApodItemsViewHolder>(ApodRandomDiffCallback) {

    class ApodItemsViewHolder(
            private val binding: AdapterApodItemBinding,
            private var onAdd: (Apod) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(apod: Apod) {
            binding.apply {
                tvTitleItem.text = apod.title
                tvContentItem.text = apod.explanation
                tvDateItem.text = apod.date
                imgApodItem.load(apod.url) {
                    placeholder(R.drawable.no_image_available)
                }
                btnDetail.setOnClickListener {
                    if (extendedLinearLayout.visibility == View.GONE)
                        extendedLinearLayout.visibility = View.VISIBLE
                    else
                        extendedLinearLayout.visibility = View.GONE
                }
                btnAddFavorites.setOnClickListener {
                    onAdd(apod)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodItemsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ApodItemsViewHolder(
                AdapterApodItemBinding.inflate(layoutInflater, parent, false),
                onAdd
        )
    }

    override fun onBindViewHolder(holder: ApodItemsViewHolder, position: Int) {
        val apodItem = getItem(position)
        holder.bind(apodItem)
    }

    companion object ApodRandomDiffCallback : DiffUtil.ItemCallback<Apod>() {
        override fun areItemsTheSame(oldItem: Apod, newItem: Apod): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Apod, newItem: Apod): Boolean {
            return oldItem.date == newItem.date
        }
    }
}