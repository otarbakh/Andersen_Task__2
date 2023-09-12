package com.otarbakh.andersen_task__2.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.otarbakh.andersen_task__2.common.Constants.tableName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = tableName)

data class ContactsDetail(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val surname: String,
    val phoneNumber: String
) : Parcelable

