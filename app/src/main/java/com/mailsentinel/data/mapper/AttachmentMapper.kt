package com.mailsentinel.data.mapper

import com.mailsentinel.core.database.entity.AttachmentEntity
import com.mailsentinel.domain.model.Attachment

object AttachmentMapper : EntityMapper<AttachmentEntity, Attachment> {
    override fun mapFromEntity(entity: AttachmentEntity): Attachment {
        return Attachment(
            id = entity.id,
            messageId = entity.messageId,
            filename = entity.filename,
            contentType = entity.contentType,
            size = entity.size,
            localPath = entity.localPath,
            isDownloaded = entity.isDownloaded,
            ocrResult = entity.ocrResult,
            ocrProcessed = entity.ocrProcessed
        )
    }

    override fun mapToDomain(domain: Attachment): AttachmentEntity {
        return AttachmentEntity(
            id = domain.id,
            messageId = domain.messageId,
            filename = domain.filename,
            contentType = domain.contentType,
            size = domain.size,
            localPath = domain.localPath,
            isDownloaded = domain.isDownloaded,
            ocrResult = domain.ocrResult,
            ocrProcessed = domain.ocrProcessed
        )
    }
}
