package com.example.projet_prog_mobile.data.local.user

import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    private val userDao: UserDao
) {
    fun getUserEntity(): User {
        return userDao.getUserEntity()
    }

    fun createUserEntity(user:User){
        userDao.createUserEntity(user)
    }
}