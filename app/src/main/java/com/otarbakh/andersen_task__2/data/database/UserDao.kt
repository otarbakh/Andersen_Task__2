package com.otarbakh.andersen_task__2.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.otarbakh.andersen_task__2.data.ContactsDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM UserContact")
    fun getAll(): Flow<List<ContactsDetail>>

    @Insert
    fun insertAll(vararg teams: ContactsDetail)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: ContactsDetail)

    @Delete
    fun delete(contact: ContactsDetail)

    @Query("DELETE FROM UserContact")
    fun deleteAll()

    @Update
    fun upsert(contact: ContactsDetail)
}