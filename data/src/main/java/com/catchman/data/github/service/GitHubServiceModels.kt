package com.catchman.data.github.service

import com.google.gson.annotations.SerializedName


data class CommonUser(
        val id: Long,
        val login: String,
        @SerializedName("avatar_url")
        val avatarUrl: String
)

data class UserInfo(
        val id: Long,
        @SerializedName("public_repos")
        val publickRepos: Long,
        @SerializedName("public_gists")
        val publicGists: Long,
        val followers: Long,
        val blog: String,
        val name: String,
        @SerializedName("avatar_url")
        val avatarUrl: String
)
