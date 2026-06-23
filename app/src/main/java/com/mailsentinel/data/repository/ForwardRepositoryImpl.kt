package com.mailsentinel.data.repository

import com.mailsentinel.core.database.dao.ForwardRuleDao
import com.mailsentinel.core.regex.RegexMatcher
import com.mailsentinel.domain.model.*
import com.mailsentinel.domain.repository.ForwardRepository
import javax.inject.Inject

class ForwardRepositoryImpl @Inject constructor(
    private val forwardRuleDao: ForwardRuleDao,
    private val regexMatcher: RegexMatcher
) : ForwardRepository {

    override suspend fun addRule(rule: ForwardRule): Long = try {
        val entity = com.mailsentinel.core.database.entity.ForwardRuleEntity(
            accountId = rule.accountId,
            name = rule.name,
            regexPattern = rule.regexPattern,
            regexTarget = rule.regexTarget.name.lowercase(),
            jsScript = rule.jsScript,
            targetAddress = rule.targetAddress,
            includeOcr = rule.includeOcr,
            isActive = rule.isActive,
            priority = rule.priority,
            createdAt = rule.createdAt
        )
        forwardRuleDao.insert(entity)
    } catch (_: Exception) { -1L }

    override suspend fun updateRule(rule: ForwardRule) = try {
        val entity = com.mailsentinel.core.database.entity.ForwardRuleEntity(
            id = rule.id,
            accountId = rule.accountId,
            name = rule.name,
            regexPattern = rule.regexPattern,
            regexTarget = rule.regexTarget.name.lowercase(),
            jsScript = rule.jsScript,
            targetAddress = rule.targetAddress,
            includeOcr = rule.includeOcr,
            isActive = rule.isActive,
            priority = rule.priority
        )
        forwardRuleDao.update(entity)
    } catch (_: Exception) {}

    override suspend fun deleteRule(ruleId: Long) = try {
        forwardRuleDao.delete(ruleId)
    } catch (_: Exception) {}

    override suspend fun getActiveRules(accountId: Long): List<ForwardRule> {
        return try {
            forwardRuleDao.getActiveByAccount(accountId).map { entity ->
                ForwardRule(
                    id = entity.id,
                    accountId = entity.accountId,
                    name = entity.name,
                    regexPattern = entity.regexPattern,
                    regexTarget = RegexTarget.valueOf(entity.regexTarget.uppercase()),
                    jsScript = entity.jsScript,
                    targetAddress = entity.targetAddress,
                    includeOcr = entity.includeOcr,
                    isActive = entity.isActive,
                    priority = entity.priority,
                    createdAt = entity.createdAt
                )
            }
        } catch (_: Exception) { emptyList() }
    }

    override suspend fun testRule(rule: ForwardRule, sampleText: String): Boolean {
        return try {
            if (rule.regexPattern == null) return false
            regexMatcher.matches(rule.regexPattern, sampleText)
        } catch (_: Exception) { false }
    }

    override suspend fun applyRules(message: MailMessage, accountId: Long): List<ForwardRule> = try {
        val rules = getActiveRules(accountId)
        val matchedRules = mutableListOf<ForwardRule>()

        for (rule in rules) {
            val targetText = when (rule.regexTarget) {
                RegexTarget.SUBJECT -> message.subject ?: ""
                RegexTarget.BODY -> message.bodyText ?: message.previewText ?: ""
                RegexTarget.FROM -> message.fromAddress
                RegexTarget.ANY -> "${message.subject ?: ""} ${message.bodyText ?: ""} ${message.fromAddress}"
            }

            if (rule.regexPattern != null && regexMatcher.matches(rule.regexPattern, targetText)) {
                matchedRules.add(rule)
            }
        }

        matchedRules
    } catch (_: Exception) { emptyList() }
}
