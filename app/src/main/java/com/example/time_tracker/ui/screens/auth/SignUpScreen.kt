package com.example.time_tracker.ui.screens.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

import com.stevdzasan.onetap.GoogleUser
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.getUserFromTokenId
import com.stevdzasan.onetap.rememberOneTapSignInState

/**
 * @author bybuss
 */

@Composable
fun SignUpScreen() {
    val state = rememberOneTapSignInState()
    var user by remember { mutableStateOf<GoogleUser?>(null) }

    OneTapSignInWithGoogle(
        state = state,
        clientId = "378974376277-kn556bho5fdjlfk5ujfaiv61bbjj7lqt.apps.googleusercontent.com",
        rememberAccount = false,
        onTokenIdReceived = { tokenId ->
            user = getUserFromTokenId(tokenId = tokenId)
            Log.d("SignUp", "Received user: $user")
        },
        onDialogDismissed = {
            Log.d("SignUp", "Dialog dismissed: $it")
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { state.open() }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (state.opened) {
                        CircularProgressIndicator(
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Sign in")
                }
            }
        }

        Log.d("MainActivity", "Rendering user info: $user")

        user?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Sub: ${it.sub}")
                Text(text = "Full Name: ${it.fullName}")
                Text(text = "Email: ${it.email}")
                Text(text = "Email Verified: ${it.emailVerified}")
                Text(text = "Given Name: ${it.givenName}")
                Text(text = "Family Name: ${it.familyName}")
                Text(text = "Issued At: ${it.issuedAt}")
                Text(text = "Expiration Time: ${it.expirationTime}")
                Text(text = "Locale: ${it.locale}")
                it.picture?.let { pictureUrl ->
                    Image(
                        painter = rememberAsyncImagePainter(pictureUrl),
                        contentDescription = null,
                        modifier = Modifier.size(96.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DefaultTextField(icon: ImageVector, label: String) {
    var textValue by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        label = { Text(text = label) },
        value = textValue,
        onValueChange = {textValue = it },
        keyboardOptions = KeyboardOptions.Default,
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null)
        }
    )
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}