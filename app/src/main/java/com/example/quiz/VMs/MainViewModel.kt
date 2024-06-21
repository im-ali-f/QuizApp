package com.example.quiz.VMs.Room

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quiz.VMs.Room.lsRoom.UserInfo
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {


    //room

    fun InsertQuestion(RoomModel: RoomModel){
        viewModelScope.launch {
            repository.InsertQuestion(RoomModel)
        }
    }

    


    var GetAllQuestionsResponse: MutableLiveData<List<RoomModel>> = MutableLiveData()
    fun GetallQuestions() {
        viewModelScope.launch {
            val response: List<RoomModel> = repository.GetAllQuestions()
            GetAllQuestionsResponse.value = response
        }
    }


    fun InsertUser(userInfo: UserInfo){
        viewModelScope.launch {
            repository.InsertUser(userInfo)
        }
    }


    var GetUserInfoResponse: MutableLiveData<List<UserInfo>> = MutableLiveData()
    fun GetUserInfo(username:String) {
        viewModelScope.launch {
            val response: List<UserInfo> = repository.GetUserInfo(username = username)
            GetUserInfoResponse.value = response
        }
    }


}