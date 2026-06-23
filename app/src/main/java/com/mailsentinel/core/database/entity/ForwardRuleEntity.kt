package com.mailsentinel.core.database.entity

import androidx.room.*

@Entity(
    tableName = "forward_rules",
    indices = [Index(value = ["account_id"])],
    foreignKeys = [ForeignKey(
        entity = AccountEntity::class,
        parentColumns = ["id"],
        childColumns = ["account_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ForwardRuleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "account_id")
    val accountId: Long,
    val name: String,
    @ColumnInfo(name = "regex_pattern")
    val regexPattern: String? = null,
    @ColumnInfo(name = "regex_target")
    val regexTarget: String = "body",  // subject/body/from/any
    @ColumnInfo(name = "js_script")
    val jsScript: String? = null,
    @ColumnInfo(name = "target_address")
    val targetAddress: String,
    @ColumnInfo(name = "include_ocr")
    val includeOcr: Boolean = false,
    @ColumnInfo(name = "is_active")
    val isActive: Boolean = true,
    val priority: Int = 0,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
