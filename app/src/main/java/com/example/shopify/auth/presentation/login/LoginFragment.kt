package com.example.shopify.auth.presentation.login

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.shopify.AuthNavGraphDirections
import com.example.shopify.R
import com.example.shopify.auth.presentation.CustomerViewModel
import com.example.shopify.auth.presentation.getValue
import com.example.shopify.databinding.FragmentLoginBinding
import com.example.shopify.utils.snackBarObserver
import com.example.shopify.utils.ui.visibleIf
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment(private val firebaseAuth: FirebaseAuth) : Fragment() {

    lateinit var binding: FragmentLoginBinding

    lateinit var navController: NavController

    private val viewModel: LoginViewModel by viewModels()

    private val customerViewModel: CustomerViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        binding.signInBtn.setOnClickListener { login() }

        binding.googleBtnView.googleBtn.setOnClickListener {
            signInWithGoogle()
        }

        binding.navToRegisterBtn.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.forgetPasswordBtn.setOnClickListener {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToPasswordRecoveryFragment())
        }

        binding.guestBtn.setOnClickListener {
            navController.navigate(AuthNavGraphDirections.actionToHomeFragmentAsGuest())
            firebaseAuth.signOut()
        }
        listenToLoginStatus()

        requireActivity().snackBarObserver(viewModel.snackBarFlow)
    }


    private val authResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {

                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    setCurrentUser(account.idToken)
                } catch (e: ApiException) {
                    e.printStackTrace()
                }

            }
        }

    private fun setCurrentUser(idToken: String?) {
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        lifecycleScope.launch {
            var userName = ""
            firebaseAuth.apply {
                signInWithCredential(firebaseCredential)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            currentUser?.let { user ->
                                customerViewModel.createCustomerAccount(user)
                                userName = user.displayName.toString()
                            }

                            navController.navigate(AuthNavGraphDirections.actionLoginFragmentToHomeFragment())
                        }
                    }
            }
        }
    }


    private fun signInWithGoogle() {
        val googleSignInOptions = GoogleSignInOptions.Builder()
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val client = GoogleSignIn.getClient(requireContext(), googleSignInOptions)
        authResultLauncher.launch(client.signInIntent)
    }

    private fun login() {
        val email = binding.emailField.emailEt.getValue()
        val password = binding.passwordField.passwordEt.getValue()
        viewModel.login(email, password)
    }

    private fun listenToLoginStatus() {
        lifecycleScope.launch(Dispatchers.IO) {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.loginState.collectLatest { state ->
                    withContext(Dispatchers.Main) {

                        binding.loginProgressBar visibleIf state.loading

                        if (state.success == true) {
                            firebaseAuth.currentUser?.apply {
                                Timber.i("email: $email")
                                Timber.i("Name: $displayName")
                                Timber.i("phone: $phoneNumber")
                                Timber.i("photoUrl: $photoUrl")
                            }
                            navController.navigate(AuthNavGraphDirections.actionLoginFragmentToHomeFragment())
                        }
                        if (state.unVerified == true) showDialog()
                    }
                }
            }
        }
    }


    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("Email not verified, Check Your Inbox")
            .show()
    }
}