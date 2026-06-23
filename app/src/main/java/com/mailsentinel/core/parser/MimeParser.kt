package com.mailsentinel.core.parser

import com.mailsentinel.domain.model.ParsedBody
import jakarta.mail.BodyPart
import jakarta.mail.Multipart
import jakarta.mail.Part
import jakarta.mail.internet.MimeMessage
import com.mailsentinel.core.parser.EmailBodyParser

class MimeParser(private val bodyParser: EmailBodyParser = EmailBodyParser()) {
    
    fun parseMessage(mimeMessage: MimeMessage): Pair<ParsedBody?, List<AttachmentInfo>> = try {
        val parsedBody = when {
            mimeMessage.isMimeType("text/html") -> {
                val html = mimeMessage.content as? String ?: ""
                bodyParser.parseHtmlBody(html)
            }
            mimeMessage.isMimeType("text/plain") -> {
                val text = mimeMessage.content as? String ?: ""
                bodyParser.parseTextBody(text)
            }
            mimeMessage.isMimeType("multipart/*") -> {
                parseMultipart(mimeMessage.content as Multipart)
            }
            else -> {
                ParsedBody(plainText = "[不支持的邮件格式]")
            }
        }
        
        // 提取附件信息（简化）
        val attachments = mutableListOf<AttachmentInfo>()
        extractAttachments(mimeMessage, attachments)
        
        Pair(parsedBody, attachments)
    } catch (e: Exception) {
        Pair(ParsedBody(plainText = "[解析失败: ${e.message}]"), emptyList())
    }
    
    private fun parseMultipart(multipart: Multipart): ParsedBody {
        // 优先找 HTML，回退纯文本
        for (i in 0 until multipart.count) {
            val bodyPart = multipart.getBodyPart(i)
            if (bodyPart.isMimeType("text/html")) {
                val html = bodyPart.content as? String ?: ""
                return bodyParser.parseHtmlBody(html)
            }
        }
        for (i in 0 until multipart.count) {
            val bodyPart = multipart.getBodyPart(i)
            if (bodyPart.isMimeType("text/plain")) {
                val text = bodyPart.content as? String ?: ""
                return bodyParser.parseTextBody(text)
            }
        }
        return ParsedBody(plainText = "[无法解析的邮件内容]")
    }
    
    private fun extractAttachments(part: Part, attachments: MutableList<AttachmentInfo>) {
        if (part.isMimeType("multipart/*")) {
            val mp = part.content as Multipart
            for (i in 0 until mp.count) {
                extractAttachments(mp.getBodyPart(i), attachments)
            }
        } else {
            val disposition = part.disposition
            if (disposition != null && (disposition.equals(Part.ATTACHMENT, true) || disposition.equals(Part.INLINE, true))) {
                attachments.add(
                    AttachmentInfo(
                        filename = part.fileName ?: "unknown",
                        contentType = part.contentType ?: "application/octet-stream",
                        size = part.size
                    )
                )
            }
        }
    }
}

data class AttachmentInfo(
    val filename: String,
    val contentType: String,
    val size: Int
)
