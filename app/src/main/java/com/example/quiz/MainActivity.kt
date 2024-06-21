package com.example.quiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instagram.lsPages.SignupComp
import com.example.quiz.VMs.Room.MainViewModel
import com.example.quiz.VMs.Room.QuizVM
import com.example.quiz.VMs.Room.Repository
import com.example.quiz.VMs.Room.db
import com.example.quiz.lsPages.LoginComp
import com.example.quiz.quizPage.HomePage
import com.example.quiz.quizPage.QuestionComp
import com.example.quiz.ui.theme.QuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navStateBig = rememberNavController()
                    val owner = this
                    val contex = LocalContext.current
                    val db = db.getInstance(contex)
                    val repo = Repository(db)
                    val viewModel = MainViewModel(repo)
                    val model = QuizVM(viewModel,this , navController = navStateBig )


                    NavHost(navController = navStateBig , startDestination = "quizPage" ){
                        composable("signupPage"){
                            SignupComp(navStateBig , model)
                        }
                        composable("loginPage"){
                            LoginComp(navStateBig , model)
                        }
                        composable("quizPage"){
                            HomePage(navStateBig , model)
                        }
                        composable(route = "questionPage"){
                            BackHandler(true) {
                                // do nothing
                            }
                            QuestionComp(navController = navStateBig , model =model, mainViewModel =viewModel , owner = owner)
                        }
                        composable(route = "showScorePage"){
                            BackHandler(true) {
                                // do nothing
                            }
                            //ScoreComp(navState,model)
                        }

                    }
                }
            }
        }
    }
}

