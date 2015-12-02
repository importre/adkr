package io.github.importre.adkr.api.user

import com.google.gson.annotations.SerializedName

class Counts {

    val media: Int = 0

    val follows: Int = 0

    @SerializedName("followed_by")
    val followedBy: Int = 0
}

