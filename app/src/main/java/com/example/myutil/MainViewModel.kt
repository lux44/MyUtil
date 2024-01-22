package com.example.myutil

import androidx.lifecycle.ViewModel
import com.example.myutil.data.remote.api.CommonService
import com.example.myutil.repositories.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val commonService: CommonService
): ViewModel() {

}