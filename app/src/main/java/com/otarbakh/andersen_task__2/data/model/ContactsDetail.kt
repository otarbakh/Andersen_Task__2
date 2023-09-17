package com.otarbakh.andersen_task__2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.otarbakh.andersen_task__2.common.Constants.tableName


@Entity(tableName = tableName)

data class ContactsDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val image:Int? = null,
    val letterInCircle:String? = null
)

