package com.example.chiimu_minor_project_version_1.presentation.login_Screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chiimu_minor_project_version_1.data.AuthRepository
import com.example.chiimu_minor_project_version_1.utils.Resource
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel()  {

    val _signInState= Channel<SignInState>()
    val signInState= _signInState.receiveAsFlow()

    val _googleState= mutableStateOf(GoogleSignInState())
    val googleState: State<GoogleSignInState> = _googleState


    fun googleSignIn(credential: AuthCredential)= viewModelScope.launch {
        repository.GoogleSigIn(credential).collect{
                result->
            when (result) {
                is Resource.Success -> {
                    _googleState.value = GoogleSignInState(success = result.data)
                }
                is Resource.Loading -> {
                    _googleState.value = GoogleSignInState(loading = true)
                }
                is Resource.Error -> {
                    _googleState.value = GoogleSignInState(error = result.message!!)
                }
            }

        }
    }



    fun loginUser(email:String,passoword:String)=viewModelScope.launch {
        repository.LoginUser(email,passoword).collect{
                result->

            when(result){
                is Resource.Success -> {
                    _signInState.send(SignInState(isSuccess = "Sign In Success "))
                }
                is Resource.Loading -> {
                    _signInState.send(SignInState(isLoading = true))
                }
                is Resource.Error -> {

                    _signInState.send(SignInState(isError = result.message))
                }

            }
        }
    }


}