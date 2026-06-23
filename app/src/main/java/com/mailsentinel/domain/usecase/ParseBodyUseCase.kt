package com.mailsentinel.domain.usecase

import com.mailsentinel.domain.model.ParsedBody
import javax.inject.Inject

// 注意：EmailBodyParser 位于 core 模块，domain 层不应直接依赖。
// 此处仅按用户要求保留签名，实际使用时需通过依赖注入传入 Parser 接口。
class ParseBodyUseCase @Inject constructor(
    private val parser: com.mailsentinel.core.parser.EmailBodyParser =
        com.mailsentinel.core.parser.EmailBodyParser()
) {
    operator fun invoke(html: String): ParsedBody {
        return try {
            parser.parseHtmlBody(html)
        } catch (e: Exception) {
            ParsedBody(plainText = "[解析失败: ${e.message}]")
        }
    }
}
