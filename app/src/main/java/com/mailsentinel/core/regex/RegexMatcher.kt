package com.mailsentinel.core.regex

/**
 * 正则匹配结果，包含命中的完整匹配文本和所有捕获组
 */
data class MatchResult(
    /** 完整匹配的文本（等价于 group(0)） */
    val matchedText: String,
    /** 所有捕获组（group(1), group(2), ...），空列表表示无捕获组 */
    val captureGroups: List<String> = emptyList()
)

class RegexMatcher {

    fun matches(pattern: String, target: String, ignoreCase: Boolean = false): Boolean = try {
        val regex = if (ignoreCase) {
            Regex(pattern, RegexOption.IGNORE_CASE)
        } else {
            Regex(pattern)
        }
        regex.containsMatchIn(target)
    } catch (e: Exception) {
        false
    }

    fun findMatches(pattern: String, target: String, ignoreCase: Boolean = false): List<String> = try {
        val regex = if (ignoreCase) {
            Regex(pattern, RegexOption.IGNORE_CASE)
        } else {
            Regex(pattern)
        }
        regex.findAll(target).map { it.value }.toList()
    } catch (e: Exception) {
        emptyList()
    }

    /**
     * 查找第一个匹配并返回 MatchResult（含捕获组）
     * 用于提取验证码、订单号等具体内容
     */
    fun findFirstMatch(pattern: String, target: String, ignoreCase: Boolean = false): MatchResult? = try {
        val regex = if (ignoreCase) {
            Regex(pattern, RegexOption.IGNORE_CASE)
        } else {
            Regex(pattern)
        }
        regex.find(target)?.let { match ->
            MatchResult(
                matchedText = match.value,
                captureGroups = match.groupValues.drop(1)  // drop(0)=完整匹配，剩余为捕获组
            )
        }
    } catch (_: Exception) { null }

    /**
     * 查找所有匹配并返回每个匹配的 MatchResult 列表
     */
    fun findAllMatches(pattern: String, target: String, ignoreCase: Boolean = false): List<MatchResult> = try {
        val regex = if (ignoreCase) {
            Regex(pattern, RegexOption.IGNORE_CASE)
        } else {
            Regex(pattern)
        }
        regex.findAll(target).map { match ->
            MatchResult(
                matchedText = match.value,
                captureGroups = match.groupValues.drop(1)
            )
        }.toList()
    } catch (_: Exception) { emptyList() }
}
