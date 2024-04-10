package com.example.chiimu_minor_project_version_1.presentation.login_Screen

import com.google.firebase.auth.AuthResult

data class GoogleSignInState(
    val success: AuthResult? = null,
    val loading: Boolean = false,
    val error: String = ""
)
