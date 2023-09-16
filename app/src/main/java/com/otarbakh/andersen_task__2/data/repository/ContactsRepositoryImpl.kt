package com.otarbakh.andersen_task__2.data.repository


import com.otarbakh.andersen_task__2.data.database.UserDao
import com.otarbakh.andersen_task__2.data.model.ContactsDetail
import com.otarbakh.andersen_task__2.domain.ContactsRepository
import kotlinx.coroutines.flow.Flow


import javax.inject.Inject

class ContactsRepositoryImpl @Inject constructor(

    private val contactsDao: UserDao,

    ) : ContactsRepository {

    override suspend fun getContacts(): Flow<List<ContactsDetail>> {
        return contactsDao.getAll()
    }


    override suspend fun insertContact(contact: ContactsDetail) {
        contactsDao.insert(contact)
    }

    override suspend fun upsert(contact: ContactsDetail) {
        contactsDao.upsert(contact)
    }

    override suspend fun deleteContact(contact: ContactsDetail) {
        contactsDao.delete(contact)
    }

    override suspend fun deleteAll() {
        contactsDao.deleteAll()
    }

}
