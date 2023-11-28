package com.example.projet_prog_mobile.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Query("SELECT * FROM user LIMIT 1")
    fun getUserEntity(): User

    @Update
    fun updateUserEntity(vararg user: User)

    @Insert
    fun createUserEntity(vararg user: User)

}