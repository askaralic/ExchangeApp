package com.exchange.hamilton.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import com.exchange.hamilton.R
import com.exchange.hamilton.data.model.AvailableCurrencies
import com.exchange.hamilton.databinding.FragmentCurrencyConfigurationBinding
import com.exchange.hamilton.presentation.adapters.CurrencyConfigurationAdapter
import com.exchange.hamilton.presentation.viewmodels.HomeViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class CurrencyConfigurationFragment : BottomSheetDialogFragment() {

    var dataSet: ArrayList<AvailableCurrencies> = ArrayList()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }



    private lateinit var binding: FragmentCurrencyConfigurationBinding
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_currency_configuration, container, false)
        binding = FragmentCurrencyConfigurationBinding.bind(view)
        initEventWithValues()
        setAdapter()
        getCurrencyList()
        return binding.root
    }

    private fun initEventWithValues() {
        binding.btnSave.setOnClickListener{
            saveCurrencyList()
            dismiss()
        }
    }


    private var adapter: CurrencyConfigurationAdapter? =null;


    private  fun setAdapter() {
        if(adapter == null){
            binding.progress.visibility= View.VISIBLE
            adapter = CurrencyConfigurationAdapter(dataSet)
            binding.progress.visibility= View.GONE
        }
        binding.listViewCurrencyList.adapter=adapter
        adapter?.notifyDataSetChanged()
    }

    private fun getCurrencyList() {
        job?.cancel()
        job = lifecycleScope.launch {
            dataSet.clear()
            binding.progress.visibility = View.VISIBLE
            viewModel.getCurrency()?.let { dataSet.addAll(it.availableCurrencies) }
            setAdapter()
            binding.progress.visibility = View.GONE
        }
    }
    private fun saveCurrencyList() {
        job?.cancel()
        job = lifecycleScope.launch {
           var currency = viewModel.getCurrency()
            if (currency != null) {
                currency.availableCurrencies = dataSet
            }
            viewModel.currencyList  =  currency
        }
    }
}