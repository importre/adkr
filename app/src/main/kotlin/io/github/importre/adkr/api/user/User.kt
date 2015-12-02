package io.github.importre.adkr.api.user

import com.google.gson.annotations.SerializedName

class User {

    val id: String = "self"

    @SerializedName("username")
    val userName: String = ""

    @SerializedName("full_name")
    val fullName: String = ""

    @SerializedName("profile_picture")
    val profilePicture: String = ""

    val bio: String = ""

    val website: String = ""

    val counts: Counts = Counts()
}