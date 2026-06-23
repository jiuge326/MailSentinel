package com.mailsentinel.domain.model

/**
 * 规则动作类型
 * FORWARD: 转发邮件到目标地址
 * COPY: 复制匹配内容到剪贴板（通过通知）
 * NOTIFY: 仅推送通知提醒
 */
enum class RuleAction {
    FORWARD,
    COPY,
    NOTIFY
}

/**
 * 正则表达式匹配目标
 */
enum class RegexTarget {
    SUBJECT,
    BODY,
    FROM,
    ANY
}

data class ForwardRule(
    val id: Long = 0,
    val accountId: Long,
    val name: String,
    val regexPattern: String? = null,
    val regexTarget: RegexTarget = RegexTarget.BODY,
    val jsScript: String? = null,
    val targetAddress: String = "",
    val includeOcr: Boolean = false,
    val actionType: RuleAction = RuleAction.FORWARD,  // 新增：动作类型
    val isActive: Boolean = true,
    val priority: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
