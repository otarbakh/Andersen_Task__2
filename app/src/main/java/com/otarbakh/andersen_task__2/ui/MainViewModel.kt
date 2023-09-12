package com.otarbakh.andersen_task__2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otarbakh.andersen_task__2.data.model.ContactsDetail
import com.otarbakh.andersen_task__2.domain.ContactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val contactsRepository: ContactsRepository,
) : ViewModel() {

    suspend fun getContacts(): Flow<List<ContactsDetail>> {
        return contactsRepository.getContacts()
    }


    fun updateContact(contact: ContactsDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            contactsRepository.insertContact(contact)
        }
    }
}