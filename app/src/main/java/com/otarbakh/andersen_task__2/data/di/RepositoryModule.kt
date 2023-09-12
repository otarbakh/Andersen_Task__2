package com.otarbakh.andersen_task__2.data.di

import com.otarbakh.andersen_task__2.data.repository.ContactsRepository
import com.otarbakh.andersen_task__2.data.repository.ContactsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSummaryRepository(
        contactsRepository: ContactsRepositoryImpl
    ): ContactsRepository
}


