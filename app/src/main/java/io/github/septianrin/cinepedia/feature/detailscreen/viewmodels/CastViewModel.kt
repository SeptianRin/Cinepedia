package io.github.septianrin.cinepedia.feature.detailscreen.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.septianrin.cinepedia.feature.detailscreen.models.Cast
import kotlinx.coroutines.launch
import javax.inject.Inject

class CastViewModel(): ViewModel() {

    private var listCast : List<Cast> = mutableListOf()

    fun setData(newData: List<Cast>){
        Log.e( "setData: ", "$newData")
        listCast = newData
        Log.e( "setData: ", "$listCast")
    }

    fun getData() = listCast

}