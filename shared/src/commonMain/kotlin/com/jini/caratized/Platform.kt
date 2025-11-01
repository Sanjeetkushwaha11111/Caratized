package com.jini.caratized

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform