package com.mailsentinel.domain.repository

import com.mailsentinel.domain.model.*

interface ForwardRepository {
    suspend fun addRule(rule: ForwardRule): Long
    suspend fun updateRule(rule: ForwardRule)
    suspend fun deleteRule(ruleId: Long)
    suspend fun getActiveRules(accountId: Long): List<ForwardRule>
    suspend fun testRule(rule: ForwardRule, sampleText: String): Boolean
    suspend fun applyRules(message: MailMessage, accountId: Long): List<ForwardRule>
}
