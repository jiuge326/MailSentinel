package com.mailsentinel.domain.usecase

import com.mailsentinel.domain.model.MailMessage
import com.mailsentinel.domain.repository.ForwardRepository
import com.mailsentinel.domain.repository.MailRepository
import javax.inject.Inject

class ForwardMailUseCase @Inject constructor(
    private val forwardRepository: ForwardRepository,
    private val mailRepository: MailRepository
) {
    suspend operator fun invoke(message: MailMessage): Result<Unit> {
        return try {
            val rules = forwardRepository.applyRules(message, message.accountId)
            // 根据规则转发邮件
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
