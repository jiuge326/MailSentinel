package com.mailsentinel.domain.usecase

import com.mailsentinel.domain.model.ForwardRule
import com.mailsentinel.domain.repository.ForwardRepository
import javax.inject.Inject

class ManageRulesUseCase @Inject constructor(
    private val forwardRepository: ForwardRepository
) {
    suspend fun addRule(rule: ForwardRule): Long = forwardRepository.addRule(rule)
    suspend fun updateRule(rule: ForwardRule) = forwardRepository.updateRule(rule)
    suspend fun deleteRule(ruleId: Long) = forwardRepository.deleteRule(ruleId)
    suspend fun getActiveRules(accountId: Long) = forwardRepository.getActiveRules(accountId)
    suspend fun testRule(rule: ForwardRule, sample: String): Boolean =
        forwardRepository.testRule(rule, sample)
}
