package com.example.projet_prog_mobile.data.local.user

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM user LIMIT 1")
    fun getUserEntity(): User
}