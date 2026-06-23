package com.mailsentinel.domain.model

data class ParsedBody(
    val plainText: String,
    val preview: String? = null,
    val inlineImages: List<String> = emptyList(),
    val links: List<Link> = emptyList(),
    val tables: List<String> = emptyList()  // 简化的表格表示
)

data class Link(
    val text: String,
    val url: String
)
