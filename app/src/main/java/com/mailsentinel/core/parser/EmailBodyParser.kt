package com.mailsentinel.core.parser

import com.mailsentinel.domain.model.Link
import com.mailsentinel.domain.model.ParsedBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class EmailBodyParser {
    
    fun parseHtmlBody(html: String): ParsedBody = try {
        val doc: Document = Jsoup.parse(html)
        
        // 清除危险元素
        doc.select("script, iframe, style, object, embed").remove()
        
        val plainText = doc.body().text()
        
        // 提取链接
        val links = doc.select("a[href]").map { element ->
            Link(
                text = element.text().take(100),
                url = element.attr("href")
            )
        }
        
        // 提取内嵌图片 CID
        val inlineImages = doc.select("img[src^=cid:]").map { img ->
            img.attr("src").removePrefix("cid:")
        }
        
        // 智能摘要
        val preview = doc.body().select("p, div, li, h1, h2, h3")
            .take(5)
            .joinToString("\n") { it.text().take(200) }
            .take(500)
        
        // 提取表格（简化）
        val tables = doc.select("table").map { table ->
            table.text().take(500)
        }
        
        ParsedBody(
            plainText = plainText,
            preview = preview.ifBlank { plainText.take(200) },
            inlineImages = inlineImages,
            links = links,
            tables = tables
        )
    } catch (e: Exception) {
        ParsedBody(plainText = "[解析失败: ${e.message}]")
    }
    
    fun parseTextBody(text: String): ParsedBody {
        return ParsedBody(
            plainText = text,
            preview = text.take(200)
        )
    }
}
