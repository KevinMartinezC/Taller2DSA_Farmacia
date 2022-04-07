package com.udb.taller2DSA.viewmodels.record

import androidx.lifecycle.*
import com.udb.taller2DSA.repository.HomeRepository

class RecordViewModel(repository: HomeRepository) : ViewModel() {

    val orders = repository.orders
}