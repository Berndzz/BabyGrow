package com.hardus.babygrow.auth.presentation.sign_in

data class SignInState(
    val isLoading: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
