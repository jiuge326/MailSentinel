package com.mailsentinel.core.database.dao

import androidx.room.*
import com.mailsentinel.core.database.entity.ForwardRuleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ForwardRuleDao {
    @Query("SELECT * FROM forward_rules WHERE account_id = :accountId AND is_active = 1 ORDER BY priority DESC")
    suspend fun getActiveByAccount(accountId: Long): List<ForwardRuleEntity>
    
    @Query("SELECT * FROM forward_rules ORDER BY created_at DESC")
    fun getAll(): Flow<List<ForwardRuleEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rule: ForwardRuleEntity): Long
    
    @Update
    suspend fun update(rule: ForwardRuleEntity)
    
    @Query("DELETE FROM forward_rules WHERE id = :id")
    suspend fun delete(id: Long)
}
