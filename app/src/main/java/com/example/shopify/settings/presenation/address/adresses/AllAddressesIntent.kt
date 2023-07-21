package com.example.shopify.settings.presenation.address.adresses

sealed interface AllAddressesIntent{


    object GetAllAddresses :  AllAddressesIntent

    data class DeleteAddress(val position : Int) : AllAddressesIntent

}