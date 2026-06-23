package com.mailsentinel.core.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object EncryptedPrefs {

    private const val FILE_NAME = "mailsentinel_encrypted_prefs"

    private fun getMasterKey(context: Context): MasterKey = try {
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    } catch (e: Exception) {
        // 如果加密失败，使用简单方案
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    private fun getPrefs(context: Context) = try {
        EncryptedSharedPreferences.create(
            context,
            FILE_NAME,
            getMasterKey(context),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    } catch (e: Exception) {
        // 回退到普通 SharedPreferences（如果设备不支持加密）
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    fun saveApiKey(context: Context, keyName: String, apiKey: String) = try {
        getPrefs(context).edit().putString(keyName, apiKey).apply()
    } catch (_: Exception) {}

    fun getApiKey(context: Context, keyName: String): String? = try {
        getPrefs(context).getString(keyName, null)
    } catch (_: Exception) { null }

    fun removeApiKey(context: Context, keyName: String) = try {
        getPrefs(context).edit().remove(keyName).apply()
    } catch (_: Exception) {}
}
