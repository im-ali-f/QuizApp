package com.example.quiz.VMs.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quiz.VMs.Room.QuizDAO
import com.example.quiz.VMs.Room.lsRoom.UserInfo


@Database(
    entities = [RoomModel::class , UserInfo::class],
    version = 6,
    exportSchema = true
)
abstract class db : RoomDatabase() {

    //inja interface ha moarefi shan
    abstract fun QUIZDAO(): QuizDAO

    // end moarefi
    companion object {
        @Volatile
        private var INSTANCE: db? = null

        fun getInstance(context: Context): db {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        db::class.java,
                        "localDB"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}