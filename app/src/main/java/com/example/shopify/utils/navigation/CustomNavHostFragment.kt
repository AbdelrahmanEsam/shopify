package com.example.shopify.utils.navigation

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import com.example.shopify.utils.navigation.FragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CustomNavHostFragment : NavHostFragment(){
    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = fragmentFactory
    }
}