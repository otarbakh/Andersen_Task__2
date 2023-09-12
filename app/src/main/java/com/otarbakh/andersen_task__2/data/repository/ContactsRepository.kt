package com.otarbakh.andersen_task__2.data.repository

import com.otarbakh.andersen_task__2.data.ContactsDetail
import dagger.Provides

import kotlinx.coroutines.flow.Flow

interface ContactsRepository {

    suspend fun getContacts() : Flow<List<ContactsDetail>>

    suspend fun deleteContact(contact: ContactsDetail)

    suspend fun insertContact(contact: ContactsDetail)

    suspend fun  upsert(contact: ContactsDetail)

    suspend fun deleteAll()


}