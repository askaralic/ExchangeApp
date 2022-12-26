package com.exchange.hamilton.presentation.fragments

import android.os.Bundle
import android.os.CountDownTimer
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
import com.exchange.hamilton.databinding.FragmentConvertBinding
import com.exchange.hamilton.presentation.viewmodels.HomeViewModel
import javax.inject.Inject


class ConvertFragment : DaggerFragment(R.layout.fragment_convert) {

    private lateinit var binding: FragmentConvertBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentConvertBinding.bind(view)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.approvalBottomSheet.setupWithNavController( binding.root.findNavController())
        startTimer()
        bindViewWithValues();
        initEventsWithValues()

        setFragmentResultListener(124.toString()) { key, bundle ->
           if(bundle.getInt("action")!! == 1){
               findNavController().popBackStack()
               findNavController().navigate(R.id.actionApprovalToConfirmation)
           }
        }
    }

    private fun initEventsWithValues() {
        binding.btnConvert.setOnClickListener{
            findNavController().navigate(R.id.actionConvertToApproval)
        }

    }

    private fun startTimer() {
        object : CountDownTimer(viewModel.approvalDuration * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.txtTimer.setText("${(millisUntilFinished / 1000)} Second Left")
            }
            override fun onFinish() {
                binding.btnConvert.isEnabled = false
                binding.txtTimer.setText("Timeout.Please try again !")
            }
        }.start()
    }

    private fun bindViewWithValues() {
        binding.txtFinalAmount.setText(
            String.format("%.2f", viewModel.convertAmount(viewModel.destinationCurrencyAmount)).toDouble()
                .toString() + " " + viewModel.destinationCurrency)
    }

    fun showMessage(message:String){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }


}