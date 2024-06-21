package com.example.quiz.quizPage


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quiz.VMs.Room.MainViewModel
import com.example.quiz.VMs.Room.QuizVM





@Composable
fun HomePage(
    navController: NavController,
    model: QuizVM,

) {
    val navControllerInner = rememberNavController()
    NavHost(navController = navControllerInner, startDestination = "homePage") {
        composable("homePage") {
            if (model.loggedInUser.value.role == "member") {
                val navStateInner = rememberNavController()
                NavHost(navController = navStateInner, startDestination = "homePage") {
                    composable("homePage") {
                        Column(Modifier.fillMaxSize()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(0, 0, 40, 40))
                                    .padding(end = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .padding(start = 5.dp, end = 5.dp)
                                ) {
                                    Column(Modifier.padding(top = 10.dp)) {
                                        Text(
                                            text = "${model.loggedInUser.value.firstname} ${model.loggedInUser.value.lastname}",
                                            fontWeight = FontWeight(600),
                                            fontSize = 16.sp,
                                            textAlign = TextAlign.Center,
                                            color = MaterialTheme.colorScheme.onTertiary
                                        )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Box(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(10.dp)
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .height(1.dp)
                                                    .background(MaterialTheme.colorScheme.onTertiary)
                                                    .fillMaxWidth()
                                            )
                                        }
                                    }
                                }
                            }
                            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                                val scrollState = rememberScrollState()
                                Column(
                                    Modifier
                                        .fillMaxSize()
                                        .padding(20.dp)
                                        .verticalScroll(scrollState),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    val brushBTN = Brush.linearGradient(
                                        listOf(
                                            Color(0xFF330885),
                                            Color(0xFFDD3416)
                                        )
                                    )
                                    Button(
                                        onClick = {
                                            model.checkToContinue()
                                            navController.navigate("questionPage")
                                        },
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .fillMaxWidth(0.5f)
                                            .height(60.dp)
                                            .clip(RoundedCornerShape(12.dp)),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFF1E88E5)
                                        ),
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text(
                                            modifier = Modifier.offset(y = -5.dp),
                                            textAlign = TextAlign.Center,
                                            text = " Start Quiz",
                                            color = Color.White,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight(600)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                Column(
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(0, 0, 40, 40))
                            .padding(end = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .padding(start = 5.dp, end = 5.dp)
                        ) {
                            Column(Modifier.padding(top = 10.dp)) {
                                Text(
                                    text = "${model.loggedInUser.value.firstname} ${model.loggedInUser.value.lastname}",
                                    fontWeight = FontWeight(600),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onTertiary
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .height(1.dp)
                                            .background(MaterialTheme.colorScheme.onTertiary)
                                            .fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        val scrollState = rememberScrollState()
                        Column(
                            Modifier
                                .fillMaxSize()
                                .verticalScroll(scrollState)
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "متن سوال را وارد کنید",
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            TextField(
                                shape = RoundedCornerShape(5.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp),
                                textStyle = TextStyle(
                                    fontWeight = FontWeight(500),
                                    fontSize = 15.sp
                                ),
                                colors = TextFieldDefaults.colors(
                                    focusedTextColor = MaterialTheme.colorScheme.onTertiary,
                                    unfocusedTextColor = MaterialTheme.colorScheme.onTertiary,
                                    focusedIndicatorColor = Color.Transparent,
                                ),
                                placeholder = {
                                    Text(
                                        text = "متن سوال",
                                        color = MaterialTheme.colorScheme.onTertiary,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight(500)
                                    )
                                },
                                value = model.adminText.value,
                                onValueChange = { new -> model.adminText.value = new }
                            )
                            Spacer(modifier = Modifier.height(40.dp))
                            listOf(
                                "گزینه شماره 1" to model.adminAns1,
                                "گزینه شماره 2" to model.adminAns2,
                                "گزینه شماره 3" to model.adminAns3,
                                "گزینه شماره 4" to model.adminAns4
                            ).forEachIndexed { index, (label, state) ->
                                Text(
                                    text = label,
                                    fontWeight = FontWeight(600),
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onTertiary
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                TextField(
                                    shape = RoundedCornerShape(5.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(0.dp),
                                    textStyle = TextStyle(
                                        fontWeight = FontWeight(500),
                                        fontSize = 15.sp
                                    ),
                                    colors = TextFieldDefaults.colors(
                                        focusedTextColor = MaterialTheme.colorScheme.onTertiary,
                                        unfocusedTextColor = MaterialTheme.colorScheme.onTertiary,
                                        focusedIndicatorColor = Color.Transparent,
                                    ),
                                    placeholder = {
                                        Text(
                                            text = "گزینه ${index + 1}",
                                            color = MaterialTheme.colorScheme.onTertiary,
                                            fontSize = 17.sp,
                                            fontWeight = FontWeight(500)
                                        )
                                    },
                                    value = state.value,
                                    onValueChange = { new -> state.value = new }
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "Correct",
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .clickable { model.adminCorrectAns.value = (index + 1).toString() }
                                        .padding(5.dp)
                                        .background(if (model.adminCorrectAns.value == (index + 1).toString()) Color.Green else Color.Transparent)
                                        .padding(5.dp),
                                    fontWeight = FontWeight(600),
                                    fontSize = 17.sp,
                                    textAlign = TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onTertiary
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                            Spacer(modifier = Modifier.height(25.dp))
                            Button(
                                onClick = {
                                    model.InsertQuestion()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(MaterialTheme.colorScheme.background),
                                contentPadding = PaddingValues(0.dp),
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Green
                                )
                            ) {
                                Text(
                                    textAlign = TextAlign.Center,
                                    text = "Add",
                                    color = MaterialTheme.colorScheme.onTertiary,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight(600)
                                )
                            }
                        }
                    }
                }
            }
        }
        composable("specificPage") {
            //SpecificPDF(filename = selectedPDF.value , navControllerInner)
        }
    }
}

