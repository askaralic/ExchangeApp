package com.exchange.hamilton.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import com.exchange.hamilton.R
import com.exchange.hamilton.databinding.FragmentConfigurationBinding
import com.exchange.hamilton.presentation.viewmodels.HomeViewModel
import javax.inject.Inject


class ConfigurationFragment : DaggerFragment(R.layout.fragment_configuration) {

    private lateinit var binding: FragmentConfigurationBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConfigurationBinding.bind(view)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        bindViewWithValues();
        initEventsWithValues()

    }

    private fun initEventsWithValues() {
    }


    private fun bindViewWithValues() {
       binding.btnCurrencyConfiguration.setOnClickListener{
           findNavController().navigate(R.id.actionConfigurationToCurrencyList)
       }
        binding.btnDone.setOnClickListener{
           viewModel.approvalDuration = binding.numberPicker.text.toString().toLong()
           findNavController().popBackStack()
       }
    }

    fun showMessage(message:String){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


}