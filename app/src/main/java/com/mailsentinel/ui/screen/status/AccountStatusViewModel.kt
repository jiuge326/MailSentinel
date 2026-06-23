package com.mailsentinel.ui.screen.status

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mailsentinel.domain.model.Account
import com.mailsentinel.domain.model.ConnectionError
import com.mailsentinel.domain.model.ErrorType
import com.mailsentinel.domain.model.ConnectionState
import com.mailsentinel.domain.repository.MailRepository
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
    private val mailRepository: MailRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AccountStatusUiState())
    val uiState: StateFlow<AccountStatusUiState> = _uiState.asStateFlow()

    init {
        loadAccounts()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isRefreshing = true)
            loadAccounts()
            _uiState.value = _uiState.value.copy(isRefreshing = false)
        }
    }

    private fun loadAccounts() {
        viewModelScope.launch {
            try {
                val accounts = mailRepository.getAllAccounts()
                val errors = mutableMapOf<Long, ConnectionError>()

                for (account in accounts) {
                    if (account.connectionState == ConnectionState.ERROR) {
                        try {
                            val testResult = mailRepository.testConnection(account)
                            if (testResult.isFailure) {
                                errors[account.id] = NetworkUtils.diagnoseConnectionError(
                                    Exception(testResult.exceptionOrNull()?.message ?: "未知错误")
                                )
                            }
                        } catch (e: Exception) {
                            errors[account.id] = NetworkUtils.diagnoseConnectionError(e)
                        }
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
            } catch (_: Exception) {}
        }
    }

    fun retryConnection(accountId: Long) {
        viewModelScope.launch {
            try {
                val account = mailRepository.getAccountById(accountId)
                if (account != null) {
                    mailRepository.testConnection(account)
                }
                loadAccounts()
            } catch (_: Exception) {}
        }
    }
}
