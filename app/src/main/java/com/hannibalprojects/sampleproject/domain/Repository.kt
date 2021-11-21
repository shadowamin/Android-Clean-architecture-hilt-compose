package com.hannibalprojects.sampleproject.domain

import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getUsers(): Flow<List<User>>

    fun getUser(id : Int): User

    suspend fun refreshUsers(): Boolean
}