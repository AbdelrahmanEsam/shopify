package com.example.shopify.settings.presenation.address.map

import androidx.lifecycle.ViewModel
import com.example.shopify.utils.hiltanotations.Dispatcher
import com.example.shopify.utils.hiltanotations.Dispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AddressViewModel  @Inject constructor(
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
): ViewModel() {

    private val _state: MutableStateFlow<AddressState> =
        MutableStateFlow(AddressState())
    val state = _state.asStateFlow()


    private val _snackBarFlow: MutableSharedFlow<Int> =
        MutableSharedFlow()
    val snackBarFlow = _snackBarFlow.asSharedFlow()


    fun onEvent(intent: AddressIntent)
    {
        when(intent){
            AddressIntent.MapLoaded -> _state.update { it.copy(loading = false) }
            is AddressIntent.NewLatLong ->{


                _state.update { it.copy(latitude = intent.latitude.toString(), longitude = intent.longitude.toString()) }
            }
        }
    }


}