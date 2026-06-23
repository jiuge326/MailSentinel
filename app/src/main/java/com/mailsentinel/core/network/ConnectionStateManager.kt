package com.mailsentinel.core.network

import com.mailsentinel.domain.model.ConnectionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConnectionStateManager {
    private val _connectionStates = MutableStateFlow<Map<Long, ConnectionState>>(emptyMap())
    val connectionStates: StateFlow<Map<Long, ConnectionState>> = _connectionStates.asStateFlow()
    
    fun updateState(accountId: Long, state: ConnectionState) {
        val current = _connectionStates.value.toMutableMap()
        current[accountId] = state
        _connectionStates.value = current
    }
    
    fun getState(accountId: Long): ConnectionState {
        return _connectionStates.value[accountId] ?: ConnectionState.DISCONNECTED
    }
}
