package com.exchange.hamilton.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import com.exchange.hamilton.R
import com.exchange.hamilton.databinding.FragmentApprovalBinding
import com.exchange.hamilton.presentation.viewmodels.HomeViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject


class ApprovalFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory
    }



    private lateinit var binding: FragmentApprovalBinding
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_approval, container, false)
        binding = FragmentApprovalBinding.bind(view)

        bindViewWithValue()
        initEvents();
        return binding.root
    }

    private fun bindViewWithValue() {
        binding.txtApprovalMessage.setText("Your are about to get "+String.format("%.2f",  viewModel.convertAmount(viewModel.amountToConvert ?: 0f))+" "+ (viewModel.selectedDestinationCurrency?.CurrencyName
            ?: "") +" for "+ (viewModel.amountToConvert
            ?: 0) + " "+ viewModel.selectedSourceCurrency?.CurrencyName +". Do you approve this transaction")
    }

    private fun initEvents() {
        binding.btnApprove.setOnClickListener{
            dismiss()
            setFragmentResult(124.toString(), bundleOf("action" to 1))

        }
        binding.btnCancel.setOnClickListener{
            dismiss()
            setFragmentResult(124.toString(), bundleOf("action" to 2))
        }
    }


}