package com.nganga.robert.furryfriends.core.util

import com.nganga.robert.furryfriends.core.util.Constants.CAT_BASE_IMAGE_URL

object Extensions {

    fun String.getImageUrl(): String{
        return "$CAT_BASE_IMAGE_URL$this.jpg"
    }
}