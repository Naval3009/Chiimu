package com.example.chiimu_minor_project_version_1.data

import com.example.chiimu_minor_project_version_1.utils.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun LoginUser(email:String,password:String) : Flow<Resource<AuthResult>>

    fun RegisterUser(email:String,password:String) : Flow<Resource<AuthResult>>


    fun GoogleSigIn(credential: AuthCredential) :  Flow<Resource<AuthResult>>
}