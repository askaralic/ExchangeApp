package com.exchange.hamilton.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exchange.hamilton.data.model.AvailableCurrencies
import com.exchange.hamilton.databinding.ListRowCurrencyItemBinding


class CurrencyAdapter(private val dataSet: ArrayList<AvailableCurrencies>,val clickListener: (AvailableCurrencies)-> Unit) :
    RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let {
            holder.bind(it, position)
        }
    }

    private fun getItem(position: Int): AvailableCurrencies {
        return dataSet[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListRowCurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(private val binding: ListRowCurrencyItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currencyResponse: AvailableCurrencies, position: Int) {
            binding.currency = currencyResponse;
            binding.root.setOnClickListener{
                clickListener(currencyResponse)
            }
        }

    }

}