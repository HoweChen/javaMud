package com.howechen.mudspringboot.hybrid

import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class KotlinBean {

    fun test(): String {
        return Random.nextLong().toString()
    }
}