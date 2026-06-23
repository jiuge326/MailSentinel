package com.mailsentinel.ui.screen.status

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mailsentinel.domain.model.Account
import com.mailsentinel.domain.model.ConnectionError
import com.mailsentinel.domain.model.ErrorType
import com.mailsentinel.domain.model.ConnectionState
import com.mailsentinel.domain.repository.MailRepository
import com.mailsentinel.core.database.dao.AccountDao
import com.mailsentinel.core.utils.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AccountStatusUiState(
    val accounts: List<Account> = emptyList(),
    val errors: Map<Long, ConnectionError> = emptyMap(),
    val isRefreshing: Boolean = false
)

@HiltViewModel
class AccountStatusViewModel @Inject constructor(
    private val mailRepository: MailRepository,
    private val accountDao: AccountDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountStatusUiState())
    val uiState: StateFlow<AccountStatusUiState> = _uiState.asStateFlow()

    init {
        loadAccounts()
    }

    /**
     * 刷新：重新从数据库加载账户列表，并对状态异常的账户进行连接测试
     */
    fun refresh() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isRefreshing = true)
            try {
                val accounts = mailRepository.getAllAccounts()
                val errors = mutableMapOf<Long, ConnectionError>()

                for (account in accounts) {
                    // 对所有账户都测试一次连接，更新状态
                    try {
                        val password = accountDao.getById(account.id)?.password ?: ""
                        if (password.isBlank()) {
                            errors[account.id] = ConnectionError(
                                type = ErrorType.AUTH_FAILED,
                                message = "密码/授权码未保存，请重新配置",
                                solution = "请删除账户后重新添加，并确保在保存前先测试连接",
                                actionLabel = "去配置"
                            )
                            // 更新数据库状态为 error
                            accountDao.updateConnectionState(account.id, "error", "密码未保存")
                            continue
                        }

                        val result = mailRepository.testConnection(account, password)
                        if (result.isSuccess) {
                            // 连接成功，更新数据库状态
                            accountDao.updateConnectionState(account.id, "connected")
                        } else {
                            val exception = Exception(result.exceptionOrNull()?.message ?: "连接失败")
                            errors[account.id] = NetworkUtils.diagnoseConnectionError(exception)
                            accountDao.updateConnectionState(account.id, "error", exception.message)
                        }
                    } catch (e: Exception) {
                        errors[account.id] = NetworkUtils.diagnoseConnectionError(e)
                        accountDao.updateConnectionState(account.id, "error", e.message)
                    }
                }

                if (accounts.isEmpty()) {
                    errors[0L] = ConnectionError(
                        type = ErrorType.ACCOUNT_NOT_CONFIGURED,
                        message = "账户未配置",
                        solution = "请先添加邮箱账户",
                        actionLabel = "去配置"
                    )
                }

                _uiState.value = _uiState.value.copy(accounts = accounts, errors = errors)
            } catch (e: Exception) {
                // 加载失败，至少保留已有的 accounts 列表
                val current = _uiState.value.accounts
                val errors = mutableMapOf<Long, ConnectionError>()
                errors[0L] = ConnectionError(
                    type = ErrorType.UNKNOWN,
                    message = "刷新失败: ${e.message}",
                    solution = "请检查网络后重试"
                )
                _uiState.value = _uiState.value.copy(accounts = current, errors = errors)
            } finally {
                _uiState.value = _uiState.value.copy(isRefreshing = false)
            }
        }
    }

    /**
     * 对单个账户重试连接
     */
    fun retryConnection(accountId: Long) {
        viewModelScope.launch {
            try {
                val account = mailRepository.getAccountById(accountId)
                if (account == null) {
                    loadAccounts()
                    return@launch
                }

                val password = accountDao.getById(accountId)?.password ?: ""
                if (password.isBlank()) {
                    // 密码为空，直接返回错误
                    refresh()
                    return@launch
                }

                val result = mailRepository.testConnection(account, password)
                // 无论成功失败，重新加载状态
                refresh()
            } catch (_: Exception) {
                refresh()
            }
        }
    }

    private fun loadAccounts() {
        viewModelScope.launch {
            try {
                val accounts = mailRepository.getAllAccounts()
                _uiState.value = _uiState.value.copy(accounts = accounts)
            } catch (_: Exception) {
                // 加载失败，保留当前状态
            }
        }
    }
}
