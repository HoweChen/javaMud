package com.howechen.javaMud.kotlin.coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

val delayExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
val runnable = Runnable { run { println(Thread.currentThread().name) } }
fun executorTest() {
    Executors.newCachedThreadPool().run {
        repeat(100) {
            println("haha")
            delayExecutor.schedule(runnable, 10, TimeUnit.SECONDS)
//            TimeUnit.MILLISECONDS.sleep(666)
            println("again")
        }
        shutdown()
        awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS)
    }
    delayExecutor.shutdown()
    delayExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS)
}

fun ktLaunchTest() = runBlocking {
    repeat(100) {
        launch {
            println("haha")
            delay(10_000)
            println("again")
        }
    }
}

fun trackTime(method: () -> Unit) = println(measureTimeMillis(method))

fun main() {
    trackTime { executorTest() } // 746
    trackTime { ktLaunchTest() } // 1055
}