package com.catchman.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import kotlin.collections.ArrayList



data class CommonUser(
        val id: Long,
        val login: String,
        val avatar_url: String
)

data class UserInfo(
        val id: Long,
        val publickRepos: Long,
        val publicGists: Long,
        val followers: Long,
        val blog: String,
        val name: String,
        val avatarUrl: String
)

