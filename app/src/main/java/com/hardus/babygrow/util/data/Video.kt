package com.hardus.babygrow.util.data

data class Video(
    val judul_video:String,
    val url_video:String,
    val duration_video:String? = null,
    val description_video:String? = null
){
    constructor() : this("", "")
}
