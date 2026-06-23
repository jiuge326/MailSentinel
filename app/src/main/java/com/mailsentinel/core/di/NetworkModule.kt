package com.mailsentinel.core.di

import com.mailsentinel.core.network.ImapClient
import com.mailsentinel.core.network.SmtpClient
import com.mailsentinel.core.network.ConnectionStateManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    
    @Provides
    @Singleton
    fun provideImapClient(): ImapClient = ImapClient()
    
    @Provides
    @Singleton
    fun provideSmtpClient(): SmtpClient = SmtpClient()
    
    @Provides
    @Singleton
    fun provideConnectionStateManager(): ConnectionStateManager = ConnectionStateManager()
}
