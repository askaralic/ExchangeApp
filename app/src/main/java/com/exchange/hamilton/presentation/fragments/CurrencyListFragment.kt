package com.exchange.hamilton.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import com.exchange.hamilton.R
import com.exchange.hamilton.data.model.AvailableCurrencies
import com.exchange.hamilton.databinding.FragmentCurrencyListBinding
import com.exchange.hamilton.presentation.adapters.CurrencyAdapter
import com.exchange.hamilton.presentation.viewmodels.HomeViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


class CurrencyListFragment : BottomSheetDialogFragment() {

    var dataSet: ArrayList<AvailableCurrencies> = ArrayList()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }



    private lateinit var binding: FragmentCurrencyListBinding
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_currency_list, container, false)
        binding = FragmentCurrencyListBinding.bind(view)
        initEventWithValues()
        setAdapter()
        getCurrencyList()
        return binding.root
    }

    private fun initEventWithValues() {

    }


    private var adapter: CurrencyAdapter? =null;


    private  fun setAdapter() {
        if(adapter == null){
            binding.progress.visibility= View.VISIBLE
            adapter = CurrencyAdapter(dataSet,{
                setFragmentResult(123.toString(), bundleOf("amount" to it.CurrencyAmount,"currency" to it.CurrencyName))
                Toast.makeText(activity, it.CurrencyName, Toast.LENGTH_SHORT).show()
                dismiss()
            })
            binding.progress.visibility= View.GONE
        }
        binding.listViewCurrencyList.adapter=adapter
        adapter?.notifyDataSetChanged()
    }

    private fun getCurrencyList() {
        job?.cancel()
        job = lifecycleScope.launch {
            dataSet.clear()
            viewModel.getCurrency()?.let {
                var filteredList = it.availableCurrencies.filter { x -> x.shouldEnable == true }
                dataSet.addAll(filteredList)
                setAdapter()
            } }

    }

}