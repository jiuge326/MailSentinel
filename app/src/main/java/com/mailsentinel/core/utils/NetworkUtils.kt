package com.mailsentinel.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object NetworkUtils {

    fun isNetworkAvailable(context: Context): Boolean = try {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val caps = cm.getNetworkCapabilities(network) ?: return false
        caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } catch (_: Exception) { false }

    fun diagnoseConnectionError(exception: Exception): com.mailsentinel.domain.model.ConnectionError {
        val message = exception.message ?: ""
        return when {
            message.contains("AuthenticationFailedException", true) ||
            message.contains("auth", true) || message.contains("login", true) ->
                com.mailsentinel.domain.model.ConnectionError(
                    type = com.mailsentinel.domain.model.ErrorType.AUTH_FAILED,
                    message = "认证失败",
                    solution = "请检查密码或授权码是否正确，部分邮箱需要使用授权码而非密码",
                    actionLabel = "去配置"
                )
            message.contains("SSLException", true) || message.contains("cert", true) ->
                com.mailsentinel.domain.model.ConnectionError(
                    type = com.mailsentinel.domain.model.ErrorType.CERTIFICATE_ERROR,
                    message = "证书错误",
                    solution = "请检查服务器是否使用自签名证书，可以在账户设置中开启信任所有证书"
                )
            message.contains("ConnectException", true) || message.contains("port", true) ->
                com.mailsentinel.domain.model.ConnectionError(
                    type = com.mailsentinel.domain.model.ErrorType.PORT_ERROR,
                    message = "连接失败",
                    solution = "请检查 IMAP/SMTP 服务器地址和端口是否正确"
                )
            message.contains("SocketTimeoutException", true) || message.contains("timeout", true) ->
                com.mailsentinel.domain.model.ConnectionError(
                    type = com.mailsentinel.domain.model.ErrorType.TIMEOUT,
                    message = "连接超时",
                    solution = "请检查网络连接是否正常，或尝试增加超时时间"
                )
            else ->
                com.mailsentinel.domain.model.ConnectionError(
                    type = com.mailsentinel.domain.model.ErrorType.UNKNOWN,
                    message = "连接错误: $message",
                    solution = "请检查网络和账户配置"
                )
        }
    }
}
