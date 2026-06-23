package com.mailsentinel.domain.repository

import com.mailsentinel.domain.model.*

/**
 * 正则匹配结果：规则命中时附带匹配到的具体文本
 */
data class RuleMatchResult(
    val rule: ForwardRule,
    /** 完整匹配文本 */
    val matchedText: String,
    /** 捕获组内容（如验证码、金额等） */
    val captureGroups: List<String> = emptyList()
) {
    /** 获取最有价值的内容（优先捕获组第1个，其次完整匹配） */
    val displayText: String get() = captureGroups.firstOrNull() ?: matchedText
}

interface ForwardRepository {
    suspend fun addRule(rule: ForwardRule): Long
    suspend fun updateRule(rule: ForwardRule)
    suspend fun deleteRule(ruleId: Long)
    suspend fun getActiveRules(accountId: Long): List<ForwardRule>
    suspend fun testRule(rule: ForwardRule, sampleText: String): Boolean
    suspend fun applyRules(message: MailMessage, accountId: Long): List<ForwardRule>
    /** 应用规则并返回带匹配文本的结果 */
    suspend fun applyRulesWithResult(message: MailMessage, accountId: Long): List<RuleMatchResult>
}
