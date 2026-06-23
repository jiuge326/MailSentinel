package com.mailsentinel.domain.usecase

import com.mailsentinel.domain.model.Account
import com.mailsentinel.domain.repository.MailRepository
import javax.inject.Inject

class SyncMailUseCase @Inject constructor(
    private val mailRepository: MailRepository
) {
    suspend operator fun invoke(account: Account): Result<Unit> {
        return try {
            mailRepository.syncMessages(account.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
