package com.mailsentinel.domain.model

data class ConnectionError(
    val type: ErrorType,
    val message: String,
    val solution: String,
    val actionLabel: String? = null  // 如"去配置"，为 null 则无操作按钮
)

enum class ErrorType {
    NETWORK_UNAVAILABLE,
    AUTH_FAILED,
    CERTIFICATE_ERROR,
    PORT_ERROR,
    TIMEOUT,
    ACCOUNT_NOT_CONFIGURED,
    UNKNOWN
}
