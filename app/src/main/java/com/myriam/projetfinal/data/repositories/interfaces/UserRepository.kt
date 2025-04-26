package com.myriam.projetfinal.data.repositories.interfaces

import com.myriam.projetfinal.data.models.User

interface UserRepository {
    fun getUser(): User
}