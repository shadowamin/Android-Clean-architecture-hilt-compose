package com.hannibalprojects.sampleproject.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey val id: Int,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val avatar: String?
)