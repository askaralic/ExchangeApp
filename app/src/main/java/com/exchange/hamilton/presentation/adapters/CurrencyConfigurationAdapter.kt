package com.exchange.hamilton.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exchange.hamilton.data.model.AvailableCurrencies
import com.exchange.hamilton.databinding.ListRowCurrencyConfigurationItemBinding


class CurrencyConfigurationAdapter(private val dataSet: ArrayList<AvailableCurrencies>) :
    RecyclerView.Adapter<CurrencyConfigurationAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it, position)
        }
    }

    private fun getItem(position: Int): AvailableCurrencies {
        return dataSet[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListRowCurrencyConfigurationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(private val binding: ListRowCurrencyConfigurationItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currencyResponse: AvailableCurrencies, position: Int) {
            binding.currency = currencyResponse;
            currencyResponse.shouldEnable?.let { binding.checkboxEnable.setChecked(it) };
            binding.checkboxEnable.setOnCheckedChangeListener { buttonView, isChecked ->
                currencyResponse.shouldEnable = isChecked
            }
        }

    }


    private class DiffCallback : DiffUtil.ItemCallback<AvailableCurrencies>() {
        override fun areItemsTheSame(oldItem: AvailableCurrencies, newItem: AvailableCurrencies): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AvailableCurrencies, newItem: AvailableCurrencies): Boolean {
            return oldItem == newItem
        }
    }
}