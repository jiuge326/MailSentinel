package com.mailsentinel.core.database.dao

import androidx.room.*
import com.mailsentinel.core.database.entity.FolderEntity

@Dao
interface FolderDao {
    @Query("SELECT * FROM folders WHERE account_id = :accountId")
    suspend fun getByAccount(accountId: Long): List<FolderEntity>
    
    @Query("SELECT * FROM folders WHERE account_id = :accountId AND full_name = :fullName")
    suspend fun getByFullName(accountId: Long, fullName: String): FolderEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(folders: List<FolderEntity>)
    
    @Update
    suspend fun update(folder: FolderEntity)
    
    @Query("DELETE FROM folders WHERE account_id = :accountId")
    suspend fun deleteByAccount(accountId: Long)
}
