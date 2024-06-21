package com.example.quiz.VMs.Room

import com.example.quiz.VMs.Room.lsRoom.UserInfo


class Repository(val db: db) {


    //room

    suspend fun InsertQuestion(RoomModel: RoomModel){
        db.QUIZDAO().InsertQuestion(RoomModel)
    }


    suspend fun GetAllQuestions()= db.QUIZDAO().GetAllQuestions()



    suspend fun GetUserInfo(username : String)= db.QUIZDAO().GetSpecificUserInfo(username)

    suspend fun InsertUser(userInfo: UserInfo){
        db.QUIZDAO().InsertUser(userInfo)
    }


}