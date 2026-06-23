package com.mailsentinel.domain.usecase

import com.mailsentinel.domain.model.Account
import com.mailsentinel.domain.repository.MailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListenNewMailUseCase @Inject constructor(
    private val mailRepository: MailRepository
) {
    operator fun invoke(accounts: List<Account>): Flow<Unit> {
        // 启动 IMAP IDLE 监听，返回新邮件 Flow
        // 实际实现在 Repository 层
        return kotlinx.coroutines.flow.flowOf(Unit)  // placeholder
    }
}
