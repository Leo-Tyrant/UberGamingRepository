package com.example.ubergaming2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.IntentSender
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts

import com.example.ubergaming2.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.material.snackbar.Snackbar

class Login : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private var appClient: SignInClient? = null
    private var signUpRequest: BeginSignInRequest? = null
    private var signInRequest: BeginSignInRequest? = null

    private val loginResult =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            try {
                val credential = appClient?.getSignInCredentialFromIntent(result.data)
                val idToken = credential?.googleIdToken
                when {
                    idToken != null -> {
                        // Got an ID token from Google. Use it to authenticate
                        // with your backend.
                        val msg = "idToken: $idToken"
                        Snackbar.make(binding.root, msg, Snackbar.LENGTH_INDEFINITE).show()
                        Log.d("Login", msg)
                    }
                    else -> {
                        // Shouldn't happen.
                        Log.d("Login", "No ID token!")
                        Snackbar.make(binding.root, "No ID token!", Snackbar.LENGTH_INDEFINITE)
                            .show()
                    }
                }
            } catch (e: ApiException) {
                when (e.statusCode) {
                    CommonStatusCodes.CANCELED -> {
                        Log.d("Login", "One-tap dialog was closed.")
                        // Don't re-prompt the user.
                        Snackbar.make(
                            binding.root,
                            "One-tap dialog was closed.",
                            Snackbar.LENGTH_INDEFINITE
                        ).show()
                    }
                    CommonStatusCodes.NETWORK_ERROR -> {
                        Log.d("Login", "One-tap encountered a network error.")
                        // Try again or just ignore.
                        Snackbar.make(
                            binding.root,
                            "Login encountered a network error.",
                            Snackbar.LENGTH_INDEFINITE
                        ).show()
                    }
                    else -> {
                        Log.d(
                            "Login", "Couldn't get credential from result." +
                                    " (${e.localizedMessage})"
                        )
                        Snackbar.make(
                            binding.root, "Couldn't get credential from result.\" +\n" +
                                    " (${e.localizedMessage})", Snackbar.LENGTH_INDEFINITE
                        ).show()
                    }
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appClient = Identity.getSignInClient(this)
        signUpRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(BuildConfig.WEB_CLIENT_ID)
                    // Show all accounts on the device.
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(BuildConfig.WEB_CLIENT_ID)
                    // Show all accounts on the device.
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            )
            .build()

        binding.btnSignIn.setOnClickListener {
            displaySignIn()
        }
    }

    private fun displaySignIn(){
        appClient?.beginSignIn(signInRequest!!)
            ?.addOnSuccessListener(this) { result ->
                try {
                    val ib = IntentSenderRequest.Builder(result.pendingIntent.intentSender).build()
                    loginResult.launch(ib)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e("btn click", "Couldn't start One Tap UI: ${e.localizedMessage}")
                }
            }
            ?.addOnFailureListener(this) { e ->
                // No Google Accounts found. Just continue presenting the signed-out UI.
                displaySignUp()
                Log.d("btn click", e.localizedMessage!!)
            }
    }

    private fun displaySignUp() {
        appClient?.beginSignIn(signUpRequest!!)
            ?.addOnSuccessListener(this) { result ->
                try {
                    val ib = IntentSenderRequest.Builder(result.pendingIntent.intentSender).build()
                    loginResult.launch(ib)
                } catch (e: IntentSender.SendIntentException) {
                    Log.e("btn click", "Couldn't start Login  UI: ${e.localizedMessage}")
                }
            }
            ?.addOnFailureListener(this) { e ->
                // No Google Accounts found. Just continue presenting the signed-out UI.
                displaySignUp()
                Log.d("btn click", e.localizedMessage!!)
            }
    }
}