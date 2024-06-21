package com.example.quiz.VMs.Room

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.quiz.VMs.Room.lsRoom.UserInfo

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


    /* val loggedInUser = mutableStateOf(
         UserInfoResponseListItem(
             role = "",
             id = "",
             password = "",
             name = "",
             firstname = "",
             lastname = ""
         )
     )

     */
    //for test
    val loggedInUser = mutableStateOf(
        UserInfo(
            id = 0,
            role = "member",
            //role = "admin",
            password = "123",
            name = "ali",
            firstname = "ali",
            lastname = "farhad"
        )
    )


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

    //answar
    val correctAns = mutableStateOf(0)


}