package com.mailsentinel.ui.screen.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import com.mailsentinel.core.service.VendorAdapter
import com.mailsentinel.core.utils.EncryptedPrefs
import com.mailsentinel.domain.repository.OcrRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class SettingsUiState(
    val offlineOcrEnabled: Boolean = true,
    val cloudOcrEnabled: Boolean = false,
    val modelDownloaded: Boolean = false,
    val apiKey: String = "",
    val aiModel: String = "gpt-4o",
    val keepAliveLevel: Int = 1
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val ocrRepository: OcrRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState(
        modelDownloaded = ocrRepository.isOfflineModelDownloaded(),
        apiKey = EncryptedPrefs.getApiKey(context, "cloud_ai_key") ?: ""
    ))
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun toggleOfflineOcr(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(offlineOcrEnabled = enabled)
    }

    fun toggleCloudOcr(enabled: Boolean) {
        _uiState.value = _uiState.value.copy(cloudOcrEnabled = enabled)
    }

    fun updateApiKey(key: String) {
        _uiState.value = _uiState.value.copy(apiKey = key)
        EncryptedPrefs.saveApiKey(context, "cloud_ai_key", key)
    }

    fun selectAiModel(model: String) {
        _uiState.value = _uiState.value.copy(aiModel = model)
    }

    fun setKeepAliveLevel(level: Int) {
        _uiState.value = _uiState.value.copy(keepAliveLevel = level)
    }

    fun openAutoStartSettings() {
        VendorAdapter.openAutoStartSettings(context)
    }

    fun requestBatteryOptimization() {
        VendorAdapter.requestIgnoreBatteryOptimization(context)
    }
}
