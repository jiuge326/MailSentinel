package com.mailsentinel.core.network

import com.mailsentinel.domain.model.Account
import jakarta.mail.*
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import java.util.Properties

class SmtpClient {
    
    fun sendMail(
        account: Account,
        password: String,
        to: List<String>,
        subject: String,
        body: String,
        isHtml: Boolean = false
    ): Result<Unit> = try {
        val props = Properties().apply {
            put("mail.smtp.host", account.smtpHost)
            put("mail.smtp.port", account.smtpPort.toString())
            put("mail.smtp.auth", "true")
            if (account.useSsl) {
                put("mail.smtp.ssl.enable", "true")
                put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            }
            put("mail.smtp.timeout", "30000")
        }
        
        val session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(account.emailAddress, password)
            }
        })
        
        val message = MimeMessage(session).apply {
            setFrom(InternetAddress(account.emailAddress, account.displayName))
            setRecipients(Message.RecipientType.TO, to.map { InternetAddress(it) }.toTypedArray())
            this.subject = subject
            if (isHtml) {
                setContent(body, "text/html; charset=utf-8")
            } else {
                setText(body, "utf-8")
            }
        }
        
        Transport.send(message)
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
