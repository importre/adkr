package io.github.importre.adkr.api.recent

import com.google.gson.annotations.SerializedName

public class Images {

    @SerializedName("low_resolution")
    val low: Image = Image()

    val thumbnail: Image = Image()

    @SerializedName("standard_resolution")
    val standard: Image = Image()
}

