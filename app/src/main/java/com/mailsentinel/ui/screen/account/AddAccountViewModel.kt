package com.mailsentinel.ui.screen.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mailsentinel.domain.model.Account
import com.mailsentinel.domain.model.ConnectionState
import com.mailsentinel.domain.repository.MailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddAccountUiState(
    val displayName: String = "",
    val emailAddress: String = "",
    val password: String = "",
    val imapHost: String = "",
    val imapPort: String = "993",
    val smtpHost: String = "",
    val smtpPort: String = "465",
    val useSsl: Boolean = true,
    val isTesting: Boolean = false,
    val isSaving: Boolean = false,
    val connectionTested: Boolean = false,
    val testResult: String? = null,
    val saved: Boolean = false
)

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val mailRepository: MailRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddAccountUiState())
    val uiState: StateFlow<AddAccountUiState> = _uiState.asStateFlow()

    fun updateField(field: String, value: String) {
        val current = _uiState.value
        _uiState.value = when (field) {
            "displayName" -> current.copy(displayName = value)
            "emailAddress" -> current.copy(emailAddress = value)
            "password" -> current.copy(password = value)
            "imapHost" -> current.copy(imapHost = value)
            "imapPort" -> current.copy(imapPort = value)
            "smtpHost" -> current.copy(smtpHost = value)
            "smtpPort" -> current.copy(smtpPort = value)
            "useSsl" -> current.copy(useSsl = value == "true")
            else -> current
        }
    }

    fun applyQuickConfig(provider: String) {
        val configs = mapOf(
            "gmail" to mapOf("imapHost" to "imap.gmail.com", "imapPort" to "993", "smtpHost" to "smtp.gmail.com", "smtpPort" to "465"),
            "outlook" to mapOf("imapHost" to "outlook.office365.com", "imapPort" to "993", "smtpHost" to "smtp.office365.com", "smtpPort" to "587"),
            "qq" to mapOf("imapHost" to "imap.qq.com", "imapPort" to "993", "smtpHost" to "smtp.qq.com", "smtpPort" to "465"),
            "163" to mapOf("imapHost" to "imap.163.com", "imapPort" to "993", "smtpHost" to "smtp.163.com", "smtpPort" to "465")
        )
        val config = configs[provider] ?: return
        val current = _uiState.value
        _uiState.value = current.copy(
            imapHost = config["imapHost"] ?: "",
            imapPort = config["imapPort"] ?: "993",
            smtpHost = config["smtpHost"] ?: "",
            smtpPort = config["smtpPort"] ?: "465"
        )
    }

    fun testConnection() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isTesting = true, testResult = null)
            try {
                val account = buildAccount()
                val result = mailRepository.testConnection(account)
                _uiState.value = _uiState.value.copy(
                    isTesting = false,
                    connectionTested = result.isSuccess,
                    testResult = if (result.isSuccess) "连接成功" else result.exceptionOrNull()?.message ?: "连接失败"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isTesting = false,
                    connectionTested = false,
                    testResult = e.message ?: "测试失败"
                )
            }
        }
    }

    fun saveAccount() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isSaving = true)
            try {
                val account = buildAccount()
                mailRepository.addAccount(account)
                _uiState.value = _uiState.value.copy(isSaving = false, saved = true)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isSaving = false)
            }
        }
    }

    private fun buildAccount(): Account {
        return Account(
            emailAddress = _uiState.value.emailAddress,
            displayName = _uiState.value.displayName.ifBlank { _uiState.value.emailAddress },
            imapHost = _uiState.value.imapHost,
            imapPort = _uiState.value.imapPort.toIntOrNull() ?: 993,
            smtpHost = _uiState.value.smtpHost,
            smtpPort = _uiState.value.smtpPort.toIntOrNull() ?: 465,
            useSsl = _uiState.value.useSsl,
            tokenType = "password",
            connectionState = ConnectionState.DISCONNECTED
        )
    }
}
