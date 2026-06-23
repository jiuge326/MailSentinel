package com.mailsentinel.core.di

import com.mailsentinel.core.database.dao.*
import com.mailsentinel.core.network.ImapClient
import com.mailsentinel.core.network.SmtpClient
import com.mailsentinel.core.ocr.OcrManager
import com.mailsentinel.core.parser.EmailBodyParser
import com.mailsentinel.core.parser.MimeParser
import com.mailsentinel.core.regex.RegexMatcher
import com.mailsentinel.data.repository.MailRepositoryImpl
import com.mailsentinel.data.repository.OcrRepositoryImpl
import com.mailsentinel.data.repository.ForwardRepositoryImpl
import com.mailsentinel.domain.repository.MailRepository
import com.mailsentinel.domain.repository.OcrRepository
import com.mailsentinel.domain.repository.ForwardRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindMailRepository(impl: MailRepositoryImpl): MailRepository
    
    @Binds
    @Singleton
    abstract fun bindOcrRepository(impl: OcrRepositoryImpl): OcrRepository
    
    @Binds
    @Singleton
    abstract fun bindForwardRepository(impl: ForwardRepositoryImpl): ForwardRepository
    
    companion object {
        @Provides
        @Singleton
        fun provideEmailBodyParser(): EmailBodyParser = EmailBodyParser()
        
        @Provides
        @Singleton
        fun provideMimeParser(): MimeParser = MimeParser()
        
        @Provides
        @Singleton
        fun provideRegexMatcher(): RegexMatcher = RegexMatcher()
    }
}
