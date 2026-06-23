package com.mailsentinel.core.regex

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
}
