package com.mailsentinel.core.database.dao

import androidx.room.*
import com.mailsentinel.core.database.entity.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Query("SELECT * FROM accounts WHERE is_active = 1 ORDER BY created_at DESC")
    fun getAllActive(): Flow<List<AccountEntity>>
    
    @Query("SELECT * FROM accounts ORDER BY created_at DESC")
    suspend fun getAll(): List<AccountEntity>
    
    @Query("SELECT * FROM accounts WHERE id = :id")
    suspend fun getById(id: Long): AccountEntity?
    
    @Query("SELECT * FROM accounts WHERE email_address = :email")
    suspend fun getByEmail(email: String): AccountEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: AccountEntity): Long
    
    @Update
    suspend fun update(account: AccountEntity)
    
    @Query("UPDATE accounts SET connection_state = :state, last_error = :error WHERE id = :id")
    suspend fun updateConnectionState(id: Long, state: String, error: String? = null)
    
    @Query("DELETE FROM accounts WHERE id = :id")
    suspend fun delete(id: Long)
    
    @Query("SELECT COUNT(*) FROM accounts")
    suspend fun count(): Int
}
