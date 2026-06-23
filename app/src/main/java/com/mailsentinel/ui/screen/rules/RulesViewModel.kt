package com.mailsentinel.ui.screen.rules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mailsentinel.domain.model.ForwardRule
import com.mailsentinel.domain.repository.ForwardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RulesUiState(
    val rules: List<ForwardRule> = emptyList(),
    val showAddDialog: Boolean = false
)

@HiltViewModel
class RulesViewModel @Inject constructor(
    private val forwardRepository: ForwardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RulesUiState())
    val uiState: StateFlow<RulesUiState> = _uiState.asStateFlow()

    init {
        loadRules()
    }

    private fun loadRules() {
        viewModelScope.launch {
            try {
                val rules = forwardRepository.getActiveRules(0L)
                _uiState.value = _uiState.value.copy(rules = rules)
            } catch (_: Exception) {}
        }
    }

    fun showAddDialog() {
        _uiState.value = _uiState.value.copy(showAddDialog = true)
    }

    fun hideAddDialog() {
        _uiState.value = _uiState.value.copy(showAddDialog = false)
    }

    fun addRule(rule: ForwardRule) {
        viewModelScope.launch {
            try {
                forwardRepository.addRule(rule)
                hideAddDialog()
                loadRules()
            } catch (_: Exception) {}
        }
    }

    fun deleteRule(ruleId: Long) {
        viewModelScope.launch {
            try {
                forwardRepository.deleteRule(ruleId)
                loadRules()
            } catch (_: Exception) {}
        }
    }
}
