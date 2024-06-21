package com.example.quiz.VMs.Room.lsRoom

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserInfo")
data class UserInfo(
    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val name: String,
    val password: String,
    val role: String,
    val firstname :String,
    val lastname :String,
)