package com.jansellopez.eemjoy.core.di

import android.content.Context
import androidx.room.Room
import com.jansellopez.eemjoy.data.database.ClientDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ClientDatabase::class.java,"client_database").build()

    @Singleton
    @Provides
    fun provideClientsDao(db: ClientDatabase) = db.getClientsDao()
}