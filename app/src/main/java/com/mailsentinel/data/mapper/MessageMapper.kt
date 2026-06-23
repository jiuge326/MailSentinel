package com.mailsentinel.data.mapper

import com.mailsentinel.core.database.entity.MessageEntity
import com.mailsentinel.domain.model.MailMessage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MessageMapper : EntityMapper<MessageEntity, MailMessage> {
    private val gson = Gson()

    override fun mapFromEntity(entity: MessageEntity): MailMessage {
        return MailMessage(
            id = entity.id,
            accountId = entity.accountId,
            folderId = entity.folderId,
            uid = entity.uid,
            messageIdHeader = entity.messageIdHeader,
            subject = entity.subject,
            fromAddress = entity.fromAddress,
            fromName = entity.fromName,
            toAddresses = try {
                gson.fromJson(entity.toAddresses, object : TypeToken<List<String>>() {}.type) ?: emptyList()
            } catch (_: Exception) { emptyList() },
            ccAddresses = try {
                entity.ccAddresses?.let { gson.fromJson(it, object : TypeToken<List<String>>() {}.type) }
            } catch (_: Exception) { null },
            receivedDate = entity.receivedDate,
            sentDate = entity.sentDate,
            isRead = entity.isRead,
            isStarred = entity.isStarred,
            hasAttachments = entity.hasAttachments,
            previewText = entity.previewText,
            bodyHtml = entity.bodyHtml,
            bodyText = entity.bodyText,
            size = entity.size,
            flags = try {
                entity.flags?.split(",") ?: emptyList()
            } catch (_: Exception) { emptyList() }
        )
    }

    override fun mapToDomain(domain: MailMessage): MessageEntity {
        return MessageEntity(
            id = domain.id,
            accountId = domain.accountId,
            folderId = domain.folderId,
            uid = domain.uid,
            messageIdHeader = domain.messageIdHeader,
            subject = domain.subject,
            fromAddress = domain.fromAddress,
            fromName = domain.fromName,
            toAddresses = gson.toJson(domain.toAddresses),
            ccAddresses = domain.ccAddresses?.let { gson.toJson(it) },
            receivedDate = domain.receivedDate,
            sentDate = domain.sentDate,
            isRead = domain.isRead,
            isStarred = domain.isStarred,
            hasAttachments = domain.hasAttachments,
            previewText = domain.previewText,
            bodyHtml = domain.bodyHtml,
            bodyText = domain.bodyText,
            size = domain.size,
            flags = domain.flags.joinToString(",")
        )
    }
}
