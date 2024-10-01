package com.timcoumu.mydirary.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DiaryDao {
    @Insert
    fun insertDirary(diary: Diary)
    @Update
    fun updateDirary(newdirary: Diary)
    @Query("select * from Diary")
    fun loadAllDiraries(): List<Diary>
    @Query("select * from Diary where title = :title")
    fun loadDiraryByTitle(title: String): List<Diary>
    @Delete
    fun deleteAllDiraries(diary: Diary)
    @Query("delete from Diary where title = :title")
    //通常会返回一个 Int 类型的值，表示数据库操作的结果,如果返回值为大于 0 的整数，则表示成功删除了实体；如果返回值为 0，则表示没有匹配的实体需要删除。
    fun deleteDirayByTitle(title: String): Int
    @Query("UPDATE Diary SET details = :newContent WHERE title = :title")
    fun updateDiraryContentByTitle(title: String, newContent: String)
}