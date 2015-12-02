package io.github.importre.adkr.api.recent

import io.github.importre.adkr.api.common.Result

public class Recent : Result() {

    // ["videos", "comments", "caption", "likes", "link", "user", "created_time", "images",
    // "type", "users_in_photo", "filter", "tags", "id", "location"]
    val images = Images()
}