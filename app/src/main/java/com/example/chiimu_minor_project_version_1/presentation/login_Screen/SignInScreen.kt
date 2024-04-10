package com.example.chiimu_minor_project_version_1.presentation.login_Screen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.chiimu_minor_project_version_1.R
import com.example.chiimu_minor_project_version_1.ui.theme.LightBlue
import com.example.chiimu_minor_project_version_1.utils.Constant.ServerClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel()
) {

    val googleSignInState = viewModel.googleState.value

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.googleSignIn(credentials)
            } catch (it: ApiException) {
                print(it)
            }
        }








    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.signInState.collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login Account", // Add this line for the heading
            color = Color.Black,
            fontSize = 40.sp, // Adjust font size as needed
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 40.dp) // Add some padding for spacing
        )

        Text(text = "Enter Your credentials to Login",
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            color = Color.Gray,
            fontFamily= FontFamily.Default
        )


        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            colors = TextFieldDefaults.textFieldColors(
                containerColor =  LightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = LightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                email = it

            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = {
                Text(text = "Email",fontFamily= FontFamily.Default)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            colors = TextFieldDefaults.textFieldColors(
                containerColor =  LightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = LightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                password = it
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = {
                Text(text = "password")
            }
        )

        Button(
            onClick = {
                scope.launch {
                    viewModel.loginUser(email, password)
                }


            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 30.dp, end = 30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(
                text = "Sign In",
                color = Color.White,
                modifier = Modifier
                    .padding(7.dp)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            if (state.value?.isLoading == true) {
                CircularProgressIndicator()
            }
        }
        Text(
            modifier = Modifier
                .padding(15.dp)
                .clickable {
                    navController.navigate("Sign_Up_Screen")
                },
            text = "Already Have an account? sign In",
            fontWeight = FontWeight.Bold, color = Color.Black, //fontFamily = RegularFont
        )
        Text( text = "Or connect with", modifier = Modifier.padding(top = 40.dp), fontWeight = FontWeight.Medium, color = Color.Gray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp), horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken(ServerClient)
                    .build()
                val googleSignInClient = GoogleSignIn.getClient(context,gso)
                launcher.launch(googleSignInClient.signInIntent)

            }) {
                Icon(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = "Google Icon", tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.width(20.dp))


            IconButton(onClick = {

            }) {
                Icon(
                    modifier = Modifier.size(52.dp),
                    painter = painterResource(id = R.drawable.ic_facebook),
                    contentDescription = "Google Icon", tint = Color.Unspecified
                )
            }

        }
    }

    LaunchedEffect(key1 = state.value?.isSuccess) {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true) {
                val success = state.value?.isSuccess
                Toast.makeText(context, "$success", Toast.LENGTH_LONG).show()
                navController.navigate("Home_Screen"){
                    popUpTo("Sign_In_Screen") { inclusive = true }
                }


            }
        }
    }
    LaunchedEffect(key1 = state.value?.isError) {
        scope.launch {
            if (state.value?.isError?.isNotBlank() == true) {
                val error = state.value?.isError
                Toast.makeText(context, "$error", Toast.LENGTH_LONG).show()
            }
        }
    }
    LaunchedEffect(key1 = googleSignInState.success) {
        scope.launch {
            if (googleSignInState.success != null) {
                Toast.makeText(context, "Sign In Success", Toast.LENGTH_LONG).show()
                navController.navigate("Home_Screen"){
                    popUpTo("Sign_In_Screen") { inclusive = true }
                }


            }
        }
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        if (googleSignInState.loading){
            CircularProgressIndicator()
        }
    }

}
