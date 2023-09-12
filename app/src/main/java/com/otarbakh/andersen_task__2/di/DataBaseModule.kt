package com.otarbakh.andersen_task__2.di

import android.content.Context
import androidx.room.Room
import com.otarbakh.andersen_task__2.common.Constants.dataBaseName
import com.otarbakh.andersen_task__2.data.database.DataBase
import com.otarbakh.andersen_task__2.data.database.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides

    fun provideDb(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(
            context,
            DataBase::class.java, dataBaseName,
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: DataBase): UserDao {
        return db.userDao
    }

}