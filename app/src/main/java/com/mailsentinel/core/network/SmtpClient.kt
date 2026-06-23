package com.mailsentinel.core.network

import com.mailsentinel.domain.model.Account
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
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
            put("mail.smtp.auth.mechanisms", "PLAIN")
            put("mail.smtp.sasl.enable", "false")
            if (account.useSsl) {
                put("mail.smtp.ssl.enable", "true")
                put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
                put("mail.smtp.socketFactory.fallback", "false")
                put("mail.smtp.socketFactory.port", account.smtpPort.toString())
                put("mail.smtp.ssl.trust", "*")
            }
            put("mail.smtp.timeout", "20000")
            put("mail.smtp.connectiontimeout", "20000")
            put("mail.smtp.debug", "true")
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
