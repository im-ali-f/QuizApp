package com.example.quiz.lsPages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quiz.R
import com.example.quiz.VMs.Room.QuizVM
import com.example.quiz.ui.theme.borderColor
import com.example.quiz.ui.theme.mainBlueColor
import com.example.quiz.ui.theme.mainSeperatorColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginComp(navController: NavController, model: QuizVM) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        //main column
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // logo
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Q  U  I  Z",
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontSize = 25.sp,
                    fontWeight = FontWeight(600)
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            //inputs
            val interactionSource = remember { MutableInteractionSource() }
            BasicTextField(
                value = model.loginEnteredUsername.value,
                onValueChange = { new ->
                    model.loginEnteredUsername.value = new
                },
                cursorBrush = Brush.horizontalGradient(listOf(MaterialTheme.colorScheme.onTertiary , mainBlueColor)),
                singleLine = true,
                maxLines = 1,
                textStyle = TextStyle(fontSize = 17.sp, lineHeight = 30.sp, fontWeight = FontWeight(500) , color = MaterialTheme.colorScheme.tertiary),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .border(width = 0.5.dp, color = borderColor, shape = RoundedCornerShape(5.dp))

                ,
                interactionSource = interactionSource,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Default
                ),
            ) { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,


                    ),
                    // value = model.enteredChat.value,
                    value = model.loginEnteredUsername.value,
                    singleLine = true,
                    innerTextField = innerTextField,
                    enabled = true,
                    placeholder = {
                        Text(
                            text = "Username",
                            fontWeight = FontWeight(500),
                            fontSize = 14.sp,
                            color =  MaterialTheme.colorScheme.onTertiary

                        )
                    },
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource,
                    contentPadding = PaddingValues(top = 14.dp, bottom = 14.dp, end = 15.dp, start = 15.dp), // this is how you can remove the padding
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            val interactionSource2 = remember { MutableInteractionSource() }
            BasicTextField(
                value = model.loginEnteredPassword.value,
                onValueChange = { new ->
                    model.loginEnteredPassword.value = new
                },
                cursorBrush = Brush.horizontalGradient(listOf(MaterialTheme.colorScheme.onTertiary , mainBlueColor)),
                singleLine = true,
                maxLines = 1,
                textStyle = TextStyle(fontSize = 17.sp, lineHeight = 30.sp, fontWeight = FontWeight(500), color = MaterialTheme.colorScheme.tertiary),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .border(width = 0.5.dp, color = borderColor, shape = RoundedCornerShape(5.dp)),
                interactionSource = interactionSource2,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Default
                ),
                visualTransformation = if (model.passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            ) { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                    ),
                    value = model.loginEnteredPassword.value,
                    singleLine = true,
                    innerTextField = innerTextField,
                    enabled = true,
                    placeholder = {
                        Text(
                            text = "Password",
                            fontWeight = FontWeight(500),
                            fontSize = 14.sp,
                            color =  MaterialTheme.colorScheme.onTertiary
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            model.passwordVisible.value = !model.passwordVisible.value
                        }) {
                            Icon(
                                painter = painterResource(id = if (model.passwordVisible.value) R.drawable.eye_slash else R.drawable.eye),
                                modifier = Modifier.size(19.dp),
                                contentDescription = "Toggle password visibility",
                                tint = MaterialTheme.colorScheme.surfaceTint
                            )
                        }
                    },
                    visualTransformation = if (model.passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                    interactionSource = interactionSource2,
                    contentPadding = PaddingValues(top = 14.dp, bottom = 14.dp, end = 15.dp, start = 15.dp),
                )
            }
            Spacer(modifier = Modifier.height(15.dp))


            Button(
                onClick = {
                    model.LoginFunctionallity()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(mainBlueColor),
                contentPadding = PaddingValues(0.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "Log in",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600)
                )
            }

            Spacer(modifier = Modifier.height(35.dp))


            Box(modifier = Modifier.fillMaxWidth()){
                Box(modifier = Modifier
                    .fillMaxWidth(0.37f)
                    .height(0.7.dp)
                    .align(Alignment.CenterStart)
                    .background(mainSeperatorColor))
                Box(Modifier.align(Alignment.Center)){
                    Text(
                        textAlign = TextAlign.Center,
                        text = "OR",
                        color = MaterialTheme.colorScheme.onTertiary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600)
                    )
                }
                Box(modifier = Modifier
                    .fillMaxWidth(0.37f)
                    .height(0.7.dp)
                    .align(Alignment.CenterEnd)
                    .background(mainSeperatorColor))
            }
            Spacer(modifier = Modifier.height(40.dp))

            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Don't have an account",
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onTertiary
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { navController.navigate("signupPage")}
                        .padding(5.dp)
                    ,
                    text = "Sign up",
                    fontWeight = FontWeight(400),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = mainBlueColor
                )
            }
            //end of column
        }

        //bottom Row
        Column (
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        , horizontalAlignment = Alignment.CenterHorizontally){
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(0.7.dp)
                .background(mainSeperatorColor)
            )
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = "Quiz App",
                fontWeight = FontWeight(400),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onTertiary

            )
            Spacer(modifier = Modifier.height(25.dp))

        }
    }
}
