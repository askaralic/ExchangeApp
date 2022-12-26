package com.exchange.hamilton.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.android.support.DaggerFragment
import com.exchange.hamilton.R
import com.exchange.hamilton.databinding.FragmentConfirmationBinding
import com.exchange.hamilton.presentation.viewmodels.HomeViewModel
import javax.inject.Inject


class ConfirmFragment : DaggerFragment(R.layout.fragment_confirmation) {

    private lateinit var binding: FragmentConfirmationBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConfirmationBinding.bind(view)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        bindViewWithValues();
        initEventsWithValues()

    }

    private fun initEventsWithValues() {
        binding.btnDone.setOnClickListener{
            findNavController().clearBackStack(R.id.homeFragment)
            findNavController().navigate(R.id.actionConfirmationToHome)
        }
    }


    private fun bindViewWithValues() {
        binding.txtSuccessMessage.setText("Great now you have " + String.format("%.2f", viewModel.convertAmount(viewModel.amountToConvert ?: 0f))+" "+(viewModel.selectedDestinationCurrency?.CurrencyName ?:"") + " in your account.")
        binding.txtConversionRate.setText("Your conversion rate was 1/" + (viewModel.getConversionRate() ?: 0f))
    }

    fun showMessage(message:String){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


}