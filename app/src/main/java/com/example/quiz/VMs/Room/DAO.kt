package com.example.quiz.VMs.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quiz.VMs.Room.lsRoom.UserInfo

@Dao
interface QuizDAO{

    //CREATE  USER
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertUser(userInfo: UserInfo)

    @Query("SELECT * From UserInfo WHERE name =:username ")
    suspend fun GetSpecificUserInfo(username :String):List<UserInfo>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertQuestion(roomModel: RoomModel)


    @Query("SELECT * From RoomModel")
    suspend fun GetAllQuestions():List<RoomModel>




}
