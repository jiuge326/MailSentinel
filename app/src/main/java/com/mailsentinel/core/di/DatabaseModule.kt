package com.mailsentinel.core.di

import com.mailsentinel.core.database.MailSentinelDatabase
import com.mailsentinel.core.database.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideAccountDao(db: MailSentinelDatabase): AccountDao = db.accountDao()
    
    @Provides
    @Singleton
    fun provideFolderDao(db: MailSentinelDatabase): FolderDao = db.folderDao()
    
    @Provides
    @Singleton
    fun provideMessageDao(db: MailSentinelDatabase): MessageDao = db.messageDao()
    
    @Provides
    @Singleton
    fun provideAttachmentDao(db: MailSentinelDatabase): AttachmentDao = db.attachmentDao()
    
    @Provides
    @Singleton
    fun provideOcrResultDao(db: MailSentinelDatabase): OcrResultDao = db.ocrResultDao()
    
    @Provides
    @Singleton
    fun provideForwardRuleDao(db: MailSentinelDatabase): ForwardRuleDao = db.forwardRuleDao()
}
