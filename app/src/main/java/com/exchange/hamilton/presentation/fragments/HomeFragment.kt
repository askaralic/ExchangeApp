package com.exchange.hamilton.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.android.support.DaggerFragment
import com.exchange.hamilton.R
import com.exchange.hamilton.data.model.AvailableCurrencies
import com.exchange.hamilton.databinding.FragmentHomeBinding
import com.exchange.hamilton.presentation.viewmodels.HomeViewModel
import com.exchange.hamilton.utils.hideSoftKeyboard
import javax.inject.Inject


class HomeFragment : DaggerFragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.currencyBottomSheet.setupWithNavController( binding.root.findNavController())
        binding.layoutFromCurrency.setOnClickListener{
            viewModel.operation = 1
            findNavController().navigate(R.id.actionHomeToCurrencyList)
        }

        binding.layoutToCurrency.setOnClickListener{
            viewModel.operation = 2
            findNavController().navigate(R.id.actionHomeToCurrencyList)
        }
        binding.btnCalculate.setOnClickListener{
            activity?.hideSoftKeyboard()
            if(viewModel.selectedSourceCurrency == null){
                showMessage("Please choose the source currency")
            }else if(viewModel.selectedDestinationCurrency == null){
                showMessage("Please choose the destination currency")
            }else if(binding.edtAmountToConvert.text.isNullOrEmpty()){
                showMessage("Please enter the amount to convert")
            }else {
                try {
                    viewModel.amountToConvert = binding.edtAmountToConvert.text.toString().toFloat()
                }catch (_:Exception){

                }
                findNavController().navigate(R.id.actionHomeToConvertFragment)
            }
        }
        binding.btnConfiguration.setOnClickListener{
            findNavController().navigate(R.id.actionHomeToConfigurationFragment)
        }
        setFragmentResultListener(123.toString()) { key, bundle ->
            if(viewModel.operation ==1){
                val item = AvailableCurrencies()
                item.CurrencyAmount = bundle.getFloat("amount")
                item.CurrencyName = bundle.getString("currency")!!
                viewModel.selectedSourceCurrency = item
                binding.txtSourceCurrency.setText(item.CurrencyName)
            }else{
                val item = AvailableCurrencies()
                item.CurrencyAmount = bundle.getFloat("amount")
                item.CurrencyName = bundle.getString("currency")!!
                viewModel.selectedDestinationCurrency = item
                binding.txtDestinationCurrency.setText(item.CurrencyName)
            }
            //Toast.makeText(activity, "received " + viewModel.sourceCurrency + " amount"+viewModel.sourceCurrencyAmount, Toast.LENGTH_SHORT).show()
        }
    }

    fun showMessage(message:String){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


}