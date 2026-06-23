package com.mailsentinel.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.mailsentinel.domain.model.ConnectionError
import com.mailsentinel.domain.model.ErrorType

object NetworkUtils {

    fun isNetworkAvailable(context: Context): Boolean = try {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val caps = cm.getNetworkCapabilities(network) ?: return false
        caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } catch (_: Exception) { false }

    fun diagnoseConnectionError(exception: Exception): ConnectionError {
        val message = exception.message ?: ""
        val className = exception.javaClass.simpleName ?: ""
        
        return when {
            // 认证失败 - QQ邮箱最常见的问题
            className.contains("AuthenticationFailed", true) ||
            message.contains("AuthenticationFailed", true) ||
            message.contains("AUTHENTICATE FAILED", true) ||
            message.contains("auth", true) ||
            message.contains("login", true) ||
            message.contains("密码错误", true) ||
            message.contains("Invalid credentials", true) ->
                ConnectionError(
                    type = ErrorType.AUTH_FAILED,
                    message = "认证失败：邮箱拒绝登录",
                    solution = "① QQ/163邮箱必须使用「授权码」而非密码\n② 请到邮箱设置中开启IMAP并生成授权码\n③ 确认邮箱地址拼写无误",
                    actionLabel = "去配置"
                )
            
            // SSL/证书错误
            className.contains("SSLException", true) ||
            className.contains("SSLHandshakeException", true) ||
            message.contains("SSLException", true) ||
            message.contains("cert", true) ||
            message.contains("SSL", true) ||
            message.contains("handshake", true) ->
                ConnectionError(
                    type = ErrorType.CERTIFICATE_ERROR,
                    message = "SSL证书错误：无法建立安全连接",
                    solution = "① 检查SSL是否开启（QQ/163邮箱必须开启SSL）\n② 确认端口正确：IMAP用993，SMTP用465\n③ 如果用自签名证书，需信任该证书"
                )
            
            // 连接超时
            className.contains("SocketTimeoutException", true) ||
            message.contains("SocketTimeoutException", true) ||
            message.contains("timeout", true) ||
            message.contains("Timed out", true) ->
                ConnectionError(
                    type = ErrorType.TIMEOUT,
                    message = "连接超时：服务器未响应",
                    solution = "① 检查网络是否正常连接\n② 确认IMAP服务器地址和端口无误\n③ 尝试切换WiFi/移动数据"
                )
            
            // 端口/连接被拒绝
            className.contains("ConnectException", true) ||
            message.contains("Connection refused", true) ||
            message.contains("ConnectException", true) ||
            message.contains("Connection timed out", true) ->
                ConnectionError(
                    type = ErrorType.PORT_ERROR,
                    message = "无法连接到服务器",
                    solution = "① 确认IMAP服务器地址正确（QQ: imap.qq.com, 163: imap.163.com）\n② 确认端口正确（SSL端口993，非SSL端口143）\n③ 检查网络防火墙是否阻止了连接"
                )
            
            // 网络不可达
            message.contains("Network unreachable", true) ||
            message.contains("No route to host", true) ||
            message.contains("UnknownHostException", true) ->
                ConnectionError(
                    type = ErrorType.NETWORK_UNAVAILABLE,
                    message = "网络不可达：无法解析服务器地址",
                    solution = "① 检查网络是否连接\n② 确认服务器地址拼写正确\n③ 尝试重启网络连接"
                )
            
            // Folder/协议错误
            message.contains("FolderNotFoundException", true) ||
            message.contains("NOOP failed", true) ||
            message.contains("protocol", true) ->
                ConnectionError(
                    type = ErrorType.UNKNOWN,
                    message = "协议错误：$message",
                    solution = "① 确认邮箱已开启IMAP服务\n② QQ邮箱请到设置→账户→POP3/IMAP/SMTP中开启\n③ 163邮箱请在设置中开启IMAP"
                )
            
            // 未知错误 - 显示原始信息
            else ->
                ConnectionError(
                    type = ErrorType.UNKNOWN,
                    message = "连接失败：${message.ifBlank { "未知错误" }}",
                    solution = "请检查以下内容：\n① 邮箱地址和密码/授权码是否正确\n② IMAP服务器和端口配置\n③ 网络是否正常\n④ 邮箱是否已开启IMAP服务"
                )
        }
    }
}
