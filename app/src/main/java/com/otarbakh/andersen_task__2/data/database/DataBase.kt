package com.otarbakh.andersen_task__2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.otarbakh.andersen_task__2.data.model.ContactsDetail


@Database(entities = [ContactsDetail::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract val userDao: UserDao

}