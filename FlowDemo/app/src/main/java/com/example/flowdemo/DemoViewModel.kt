package com.example.flowdemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DemoViewModel : ViewModel() {

    val myFlow: Flow<Int> = flow {}

}