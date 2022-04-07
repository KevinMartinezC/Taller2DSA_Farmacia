package com.udb.taller2DSA.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.udb.taller2DSA.R
import com.udb.taller2DSA.databinding.FragmentRecordBinding
import com.udb.taller2DSA.viewmodels.record.RecordViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

class RecordFragment : Fragment() {

    lateinit var viewModel: RecordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel { parametersOf() }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRecordBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_record, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.orderList.adapter = RecordAdapter()
        return binding.root
    }
}