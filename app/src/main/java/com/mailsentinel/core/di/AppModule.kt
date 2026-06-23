package com.mailsentinel.core.di

import android.content.Context
import androidx.room.Room
import com.mailsentinel.core.database.MailSentinelDatabase
import com.mailsentinel.core.ocr.OcrManager
import com.mailsentinel.core.service.KeepAliveManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MailSentinelDatabase {
        return Room.databaseBuilder(
            context,
            MailSentinelDatabase::class.java,
            "mailsentinel.db"
        ).build()
    }
    
    @Provides
    @Singleton
    fun provideOcrManager(@ApplicationContext context: Context): OcrManager {
        return OcrManager(context)
    }
    
    @Provides
    @Singleton
    fun provideKeepAliveManager(@ApplicationContext context: Context): KeepAliveManager {
        return KeepAliveManager(context)
    }
}
