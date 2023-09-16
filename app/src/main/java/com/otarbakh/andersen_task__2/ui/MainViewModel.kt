package com.otarbakh.andersen_task__2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otarbakh.andersen_task__2.data.model.ContactsDetail
import com.otarbakh.andersen_task__2.domain.ContactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val contactsRepository: ContactsRepository,
) : ViewModel() {

    private val phoneNumberFlow = MutableSharedFlow<ContactsDetail>()
    private val _state = MutableStateFlow<List<ContactsDetail>>(emptyList())
    val state = _state.asSharedFlow()

    suspend fun setPhoneNumber(contactsDetail: ContactsDetail) {

        phoneNumberFlow.emit(contactsDetail)
    }

    fun getPhoneNumber(): Flow<ContactsDetail> {
        return phoneNumberFlow
    }

    suspend fun getContactsFromViewModel() {
        viewModelScope.launch {
            contactsRepository.getContacts().collectLatest {
                _state.value = it
            }
        }

    }

    fun updateContact(contact: ContactsDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            contactsRepository.insertContact(contact)
        }
    }

    fun insert(contact: ContactsDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            contactsRepository.insertContact(contact)
        }
    }

    fun upsertContact(contact: ContactsDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            contactsRepository.upsert(contact)
        }
    }
    fun delete(contact: ContactsDetail) {
        viewModelScope.launch(Dispatchers.IO) {
            contactsRepository.deleteContact(contact)
        }
    }
}