package com.example.shopify.utils.navigation

import android.location.Geocoder
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.shopify.settings.presenation.address.map.AddressFragment
import com.example.shopify.utils.connectivity.ConnectivityObserver
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class FragmentFactory  @Inject constructor(
    private val connectivityObserver: ConnectivityObserver,
    private val englishGeoCoder: Geocoder,
    private val firebaseAuth: FirebaseAuth,
    private val firebaseStorage: FirebaseStorage,
    private val firebaseFirestore: FirebaseFirestore

    ): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){

            AddressFragment::class.java.name ->{
                AddressFragment(englishGeoCoder)
            }
            else -> return super.instantiate(classLoader, className)
        }
    }


}