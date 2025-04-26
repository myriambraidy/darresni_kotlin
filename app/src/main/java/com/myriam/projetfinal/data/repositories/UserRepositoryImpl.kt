package com.myriam.projetfinal.data.repositories

import com.myriam.projetfinal.data.models.User
import com.myriam.projetfinal.data.repositories.interfaces.UserRepository

class UserRepositoryImpl: UserRepository {
    private val _user: User = User("myriam")

    override fun getUser(): User {
        return this._user
    }
}