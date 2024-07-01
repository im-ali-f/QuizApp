package com.example.quiz.VMs.Room

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.quiz.VMs.Room.lsRoom.UserInfo
import kotlin.random.Random

class QuizVM(
    private val mainViewModel: MainViewModel,
    private val owner: LifecycleOwner,
    private val navController: NavController
) : ViewModel() {

    //login
    val loginEnteredUsername = mutableStateOf("")
    val loginEnteredPassword = mutableStateOf("")
    val passwordVisible = mutableStateOf(false)

    //sign up
    val signupEnteredFirstname = mutableStateOf("")
    val signupEnteredLastName = mutableStateOf("")
    val signupEnteredUsername = mutableStateOf("")
    val signupEnteredPassword = mutableStateOf("")
    val signupPasswordVisible = mutableStateOf(false)
    val signupAdminRole = mutableStateOf("member")

    val canContinueSignup = mutableStateOf(false)
    val canContinueLogin = mutableStateOf(false)


     val loggedInUser = mutableStateOf(
         UserInfo(
             role = "",
             id = 0,
             password = "",
             name = "",
             firstname = "",
             lastname = ""
         )
     )


    //for test
    /*
    val loggedInUser = mutableStateOf(
        UserInfo(
            id = 0,
            //role = "member",
            role = "admin",
            password = "123",
            name = "ali",
            firstname = "ali",
            lastname = "farhad"
        )
    )

     */


    fun LoginFunctionallity() {
        checkToContinue2()
        if (canContinueLogin.value) {
            mainViewModel.GetUserInfo(loginEnteredUsername.value)
            mainViewModel.GetUserInfoResponse.observe(owner, Observer { response ->
                if(response.isEmpty()){
                    Log.d("ROOM", "loginFunctionallity: user not exist ")


                }
                else{
                    Log.d("ROOM", "loginFunctionallity: user exist ")
                    if(response[0].password == loginEnteredPassword.value){
                        loggedInUser.value = response[0]
                        navController.navigate("quizPage")
                    }
                }
                mainViewModel.GetUserInfoResponse = MutableLiveData()
            })

        }
    }

    fun SignupFunctionallity() {
        checkToContinue()
        if (canContinueSignup.value) {
            mainViewModel.GetUserInfo(signupEnteredUsername.value)
            mainViewModel.GetUserInfoResponse.observe(owner, Observer { response ->
                if(response.isEmpty()){
                    val bodyToSend = UserInfo(
                        id = 0,
                        password = signupEnteredPassword.value,
                        name = signupEnteredUsername.value,
                        firstname = signupEnteredFirstname.value,
                        lastname = signupEnteredLastName.value,
                        role = signupAdminRole.value
                    )
                    mainViewModel.InsertUser(bodyToSend)
                    navController.navigate("loginPage")
                    Log.d("ROOM", "SignupFunctionallity: user created ")
                }
                else{
                    Log.d("ROOM", "SignupFunctionallity: user exist ")
                }
                mainViewModel.GetUserInfoResponse = MutableLiveData()
            })

        }
    }

    fun checkToContinue() {
        if(signupEnteredFirstname.value  != ""&&signupEnteredLastName.value  != ""&&signupEnteredUsername.value  != ""&&signupEnteredPassword.value  != ""){
            canContinueSignup.value = true
        }
        else{
            canContinueSignup.value = false

        }

    }

    fun checkToContinue2() {
        if(loginEnteredPassword.value != "" && loginEnteredUsername.value != ""){
            canContinueLogin.value = true
        }
        else{
            canContinueLogin.value = false

        }

    }



    //admin

    val adminText = mutableStateOf("")
    val adminAns1 = mutableStateOf("")
    val adminAns2 = mutableStateOf("")
    val adminAns3 = mutableStateOf("")
    val adminAns4 = mutableStateOf("")
    val adminCorrectAns = mutableStateOf("")


    fun InsertQuestion() {
        if (
            adminText.value != "" &&
            adminAns1.value != "" &&
            adminAns2.value != "" &&
            adminAns3.value != "" &&
            adminAns4.value != "" &&
            adminCorrectAns.value != ""
        ) {

            val bodyToSend = RoomModel(
                id = 0,
                text = adminText.value,
                correctAnswer = adminCorrectAns.value,
                answer1 = adminAns1.value,
                answer2 = adminAns2.value,
                answer3 = adminAns3.value,
                answer4 = adminAns4.value
            )
            mainViewModel.InsertQuestion(bodyToSend)



            adminText.value = ""
            adminAns1.value = ""
            adminAns2.value = ""
            adminAns3.value = ""
            adminAns4.value = ""
            adminCorrectAns.value = ""




        }


    }

    fun addDefaultQuestion(){

            var bodyToSend = RoomModel(
                id = 0,
                text = "soal shomare 11111",
                correctAnswer = "${Random.nextInt(1, 5)}",
                answer1 = "javab soal 1",
                answer2 = "javabbbb \n testttt",
                answer3 = "gozine  3",
                answer4 = "javab 444444"
            )
            mainViewModel.InsertQuestion(bodyToSend)

         bodyToSend = RoomModel(0, "What is the capital of France?", "1", "Paris", "London", "Berlin", "Madrid")
        mainViewModel.InsertQuestion(bodyToSend)
         bodyToSend = RoomModel(0, "Which planet is known as the Red Planet?", "3", "Earth", "Jupiter", "Mars", "Venus")
        mainViewModel.InsertQuestion(bodyToSend)
         bodyToSend = RoomModel(0, "Who wrote 'To Kill a Mockingbird'?", "2", "J.K. Rowling", "Harper Lee", "Mark Twain", "Ernest Hemingway")
        mainViewModel.InsertQuestion(bodyToSend)
        bodyToSend =RoomModel(0, "What is the largest mammal?", "4", "Elephant", "Giraffe", "Whale Shark", "Blue Whale")
        mainViewModel.InsertQuestion(bodyToSend)
        bodyToSend =RoomModel(0, "Which element has the chemical symbol 'O'?", "2", "Gold", "Oxygen", "Iron", "Silver")
        mainViewModel.InsertQuestion(bodyToSend)
        bodyToSend =RoomModel(0, "What is the smallest prime number?", "1", "2", "3", "1", "5")
        mainViewModel.InsertQuestion(bodyToSend)
        bodyToSend =RoomModel(0, "Who painted the Mona Lisa?", "3", "Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet")
        mainViewModel.InsertQuestion(bodyToSend)
        bodyToSend =RoomModel(0, "What is the longest river in the world?", "4", "Amazon", "Yangtze", "Mississippi", "Nile")
        mainViewModel.InsertQuestion(bodyToSend)
        bodyToSend =RoomModel(0, "Which country is the largest by area?", "1", "Russia", "Canada", "China", "United States")
        mainViewModel.InsertQuestion(bodyToSend)
        bodyToSend =RoomModel(0, "Who developed the theory of relativity?", "4", "Isaac Newton", "Galileo Galilei", "Nikola Tesla", "Albert Einstein")
        mainViewModel.InsertQuestion(bodyToSend)
        bodyToSend =RoomModel(0, "What is the hardest natural substance on Earth?", "1", "Diamond", "Gold", "Iron", "Platinum")
        mainViewModel.InsertQuestion(bodyToSend)
        bodyToSend =RoomModel(0, "What is the main ingredient in traditional Japanese miso soup?", "3", "Chicken", "Beef", "Soybean paste", "Fish")
        mainViewModel.InsertQuestion(bodyToSend)
        bodyToSend =RoomModel(0, "Which country gifted the Statue of Liberty to the United States?", "4", "Germany", "Spain", "Italy", "France")
        mainViewModel.InsertQuestion(bodyToSend)
        bodyToSend =RoomModel(0, "What is the largest ocean on Earth?", "1", "Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean")
        mainViewModel.InsertQuestion(bodyToSend)
        bodyToSend =RoomModel(0, "In what year did the Titanic sink?", "2", "1905", "1912", "1920", "1898")
        mainViewModel.InsertQuestion(bodyToSend)




    }

    //answar
    val correctAns = mutableStateOf(0)

    fun LogoutFunctionallity() {
        navController.navigate("loginPage")
        loggedInUser.value = UserInfo(
            id = 0,
            role = "",
            password = "",
            name = "",
            firstname = "",
            lastname = ""
        )
        loginEnteredPassword.value = ""
        loginEnteredUsername.value = ""
        signupEnteredFirstname.value = ""
        signupEnteredLastName.value = ""
        signupEnteredUsername.value = ""
        signupEnteredPassword.value = ""
        signupAdminRole.value = "member"
        canContinueSignup.value = false
        canContinueLogin.value = false


    }


}

