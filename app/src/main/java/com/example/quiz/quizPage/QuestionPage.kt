package com.example.quiz.quizPage


import android.os.Bundle

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quiz.VMs.Room.MainViewModel
import com.example.quiz.VMs.Room.QuizVM
import com.example.quiz.VMs.Room.RoomModel
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun QuestionComp(
    navController: NavController,
    model: QuizVM,
    mainViewModel: MainViewModel,
    owner: LifecycleOwner
) {
    var showPopUp by remember { mutableStateOf(false) }
    val questionList = remember { mutableStateOf(emptyList<RoomModel>()) }
    mainViewModel.GetallQuestions()
    LaunchedEffect(Unit) {
        showPopUp = true
        delay(1000)
        showPopUp = false
    }

    mainViewModel.GetAllQuestionsResponse.observe(owner, Observer { response ->
        Log.d("TAG", "GetAllQuestionsFunctionallity:started ")
        val listToSend = mutableListOf<RoomModel>()
        response.forEach { model ->
            listToSend.add(model)
        }
        questionList.value = listToSend
        mainViewModel.GetAllQuestionsResponse = MutableLiveData()
    })

    if (questionList.value.size >= 10) {
        val randomElements = remember { questionList.value.shuffled(Random(System.currentTimeMillis())).take(10) }
        var questionCounter by remember { mutableStateOf(0) }
        var selectedBTN by remember { mutableStateOf(0) }
        var answeredCorrectly by remember { mutableStateOf(false) }

        Log.d("TAG $questionCounter", "QuestionComp: started")

        if (showPopUp) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF121212)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        } else {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                val correctBrush = Brush.linearGradient(
                    listOf(Color(0xFF5DC039), Color(0xFFE6E1E0))
                )
                val wrongBrush = Brush.linearGradient(
                    listOf(Color(0xFF94284B), Color(0xFFE6E1E0))
                )
                val unselectedBrush = Brush.linearGradient(
                    listOf(Color(0xFF737FA3), Color(0xFFE6E1E0))
                )

                var scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .background(Color(0xFF2C2C2C))
                        .padding(24.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Question ${questionCounter + 1}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.End,
                        color = Color.White
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(Color(0xFF3C3C3C))
                    ) {
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = randomElements[questionCounter].text,
                            fontWeight = FontWeight.Medium,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        listOf(
                            randomElements[questionCounter].answer1 to "1",
                            randomElements[questionCounter].answer2 to "2",
                            randomElements[questionCounter].answer3 to "3",
                            randomElements[questionCounter].answer4 to "4"
                        ).forEachIndexed { index, (answer, id) ->
                            val brush = when {
                                selectedBTN == 0 -> unselectedBrush
                                randomElements[questionCounter].correctAnswer == id -> correctBrush
                                selectedBTN == index + 1 -> wrongBrush
                                else -> unselectedBrush
                            }
                            Button(
                                onClick = {
                                    if (selectedBTN == 0) {
                                        selectedBTN = index + 1
                                        answeredCorrectly = randomElements[questionCounter].correctAnswer == id
                                        if (answeredCorrectly) {
                                            model.correctAns.value += 1
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp)),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent
                                ),
                                shape = RoundedCornerShape(16.dp),
                                contentPadding = PaddingValues(0.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(min=60.dp)
                                        .background(brush),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "گزینه ${index + 1}: $answer",
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }

                    Button(
                        onClick = {
                            if (questionCounter < 9) {
                                questionCounter += 1
                            } else {
                                showPopUp = true
                                navController.navigate("showScorePage")
                            }
                            selectedBTN = 0
                            answeredCorrectly = false
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
                            text = "Next",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
    else {
        Log.d("TAG", "QuestionComp: Not enough questions available!")
    }
}




