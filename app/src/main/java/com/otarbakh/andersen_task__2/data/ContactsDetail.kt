package com.otarbakh.andersen_task__2.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "UserContact")

data class ContactsDetail(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name:String,
    val surname:String,
    val phoneNumber:String
):Parcelable

val contactsList = listOf <ContactsDetail>(
    ContactsDetail(1,"Guga","Beriashvili", "555 55 55 44"),
    ContactsDetail(2,"Vaja","Sanadiradze", "595 11 12 33"),
    ContactsDetail(3,"Giorgi","Dzocenidze", "595 49 49 49"),
    ContactsDetail(4,"Luka","Razikashvili", "595 00 00 00"),
    ContactsDetail(5,"Ilia","Chavchavadze", "595 22 22 44"),
)
