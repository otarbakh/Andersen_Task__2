package com.otarbakh.andersen_task__2

data class ContactsDetail(
    val name:String,
    val surname:String,
    val phoneNumber:String
)

val contactsList = listOf<ContactsDetail>(
    ContactsDetail("Guga","Beriashvili", "555 55 55 44"),
    ContactsDetail("Vaja","Sanadiradze", "595 11 12 33"),
    ContactsDetail("Giorgi","Dzocenidze", "595 49 49 49"),
    ContactsDetail("Luka","Razikashvili", "595 00 00 00"),
    ContactsDetail("Ilia","Chavchavadze", "595 22 22 44"),
)