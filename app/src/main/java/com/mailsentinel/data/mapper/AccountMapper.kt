package com.mailsentinel.data.mapper

import com.mailsentinel.core.database.entity.AccountEntity
import com.mailsentinel.domain.model.Account
import com.mailsentinel.domain.model.ConnectionState

object AccountMapper : EntityMapper<AccountEntity, Account> {
    override fun mapFromEntity(entity: AccountEntity): Account {
        return Account(
            id = entity.id,
            emailAddress = entity.emailAddress,
            displayName = entity.displayName,
            imapHost = entity.imapHost,
            imapPort = entity.imapPort,
            smtpHost = entity.smtpHost,
            smtpPort = entity.smtpPort,
            useSsl = entity.useSsl,
            tokenType = entity.tokenType ?: "password",
            lastSyncTime = entity.lastSyncTime,
            isActive = entity.isActive,
            connectionState = when (entity.connectionState) {
                "connected" -> ConnectionState.CONNECTED
                "connecting" -> ConnectionState.CONNECTING
                "error" -> ConnectionState.ERROR
                else -> ConnectionState.DISCONNECTED
            },
            lastError = entity.lastError,
            createdAt = entity.createdAt
        )
    }

    override fun mapToDomain(domain: Account): AccountEntity {
        return AccountEntity(
            id = domain.id,
            emailAddress = domain.emailAddress,
            displayName = domain.displayName,
            imapHost = domain.imapHost,
            imapPort = domain.imapPort,
            smtpHost = domain.smtpHost,
            smtpPort = domain.smtpPort,
            useSsl = domain.useSsl,
            tokenType = domain.tokenType,
            connectionState = when (domain.connectionState) {
                ConnectionState.CONNECTED -> "connected"
                ConnectionState.CONNECTING -> "connecting"
                ConnectionState.ERROR -> "error"
                ConnectionState.DISCONNECTED -> "disconnected"
            },
            lastError = domain.lastError,
            isActive = domain.isActive,
            lastSyncTime = domain.lastSyncTime,
            createdAt = domain.createdAt
        )
    }
}
