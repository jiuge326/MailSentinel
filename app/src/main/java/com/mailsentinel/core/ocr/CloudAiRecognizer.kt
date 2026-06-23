package com.mailsentinel.core.ocr

import com.mailsentinel.domain.model.OcrResult
import com.mailsentinel.domain.model.OcrSourceType
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class CloudAiRecognizer {

    private val httpClient = OkHttpClient()

    /**
     * 使用 GPT-4o 视觉识别图片内容
     * API Key 从参数传入，不硬编码
     */
    suspend fun recognizeWithGpt4o(
        imageBase64: String,
        apiKey: String
    ): OcrResult = try {
        val requestBody = JSONObject().apply {
            put("model", "gpt-4o")
            put("messages", org.json.JSONArray().apply {
                put(JSONObject().apply {
                    put("role", "user")
                    put("content", org.json.JSONArray().apply {
                        put(JSONObject().apply {
                            put("type", "text")
                            put("text", "请识别图片中的文字，直接输出识别结果，不要添加任何解释。")
                        })
                        put(JSONObject().apply {
                            put("type", "image_url")
                            put("image_url", JSONObject().apply {
                                put("url", "data:image/jpeg;base64,$imageBase64")
                            })
                        })
                    })
                })
            })
            put("max_tokens", 1000)
        }

        val request = Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .addHeader("Authorization", "Bearer $apiKey")
            .addHeader("Content-Type", "application/json")
            .post(RequestBody.create("application/json".toMediaTypeOrNull(), requestBody.toString()))
            .build()

        suspendCancellableCoroutine { continuation ->
            httpClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    continuation.resume(
                        OcrResult(
                            recognizedText = "[云端识别失败: ${e.message}]",
                            sourceType = OcrSourceType.CLOUD
                        )
                    )
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = try { response.body?.string() ?: "" } catch (e: Exception) { "" }
                    val recognizedText = try {
                        val json = JSONObject(body)
                        val content = json.getJSONArray("choices")
                            .getJSONObject(0)
                            .getJSONObject("message")
                            .getString("content")
                        content
                    } catch (e: Exception) {
                        "[解析云端响应失败: ${e.message}]"
                    }

                    continuation.resume(
                        OcrResult(
                            recognizedText = recognizedText,
                            language = "zh",
                            confidence = 0.95f,
                            sourceType = OcrSourceType.CLOUD
                        )
                    )
                }
            })
        }
    } catch (e: Exception) {
        OcrResult(
            recognizedText = "[云端识别异常: ${e.message}]",
            sourceType = OcrSourceType.CLOUD
        )
    }
}
