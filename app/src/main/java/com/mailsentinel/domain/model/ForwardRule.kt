package com.mailsentinel.domain.model

data class ForwardRule(
    val id: Long = 0,
    val accountId: Long,
    val name: String,
    val regexPattern: String? = null,
    val regexTarget: RegexTarget = RegexTarget.BODY,
    val jsScript: String? = null,
    val targetAddress: String,
    val includeOcr: Boolean = false,
    val isActive: Boolean = true,
    val priority: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)

enum class RegexTarget {
    SUBJECT,
    BODY,
    FROM,
    ANY
}
