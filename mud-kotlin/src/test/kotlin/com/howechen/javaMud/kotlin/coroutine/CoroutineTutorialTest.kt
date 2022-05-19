package com.howechen.javaMud.kotlin.coroutine

import kotlinx.coroutines.*
import kotlin.test.Test


class CoroutineTutorialTest {
    @Test
    fun givenCoroutine_whenHit_thenReturnHelloWorld() = runBlocking { // this: CoroutineScope
        launch { // launch a new coroutine and continue
            delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
            println("World!") // print after delay
        }
        println("Hello") // main coroutine continues while a previous one is delayed
    }

    @Test
    fun givenCoroutineWithMultipleLaunchs_whenHit_thenReturnHelloWorld() =
        runBlocking { // this: CoroutineScope
            launch { // launch a new coroutine and continue
                for (i in 0..5) {
                    multipleLaunches(i)
                }
            }
            println("Hello") // main coroutine continues while a previous one is delayed
        }

    @Test
    fun givenMultipleFunctionsInOneScope_whenRun_thenSouldReturnCorrectly() = runBlocking {
        doMultipleTasks()
        println("Done")
    }

    @Test
    fun givenLargeRepeatTimesOfCoroutines_whenExecute_thenShouldSucceed() = runBlocking {
        repeat(100_000) {
            launch {
                delay(5000L)
                print(".")
            }
        }
    }

    @Test
    fun givenThreeAsync_whenExecute_thenShouldSucceed() = runBlocking {
        val deferreds: List<Deferred<Int>> = (1..3).map {
            async {
                delay(1000L * it)
                println("Loading $it")
                it
            }
        }
        val sum = deferreds.awaitAll().sum()
        println("$sum")
    }

    @Test
    fun givenThreeAsyncWithTimeoutAndGetSumOutside_whenExecute_thenShouldSucceed() = runBlocking {
        var sum = 0
        try {
            withTimeout(5000) {
                val deferreds: List<Deferred<Int>> = (1..3).map {
                    async {
                        delay(1000L * it)
                        println("Loading $it")
                        it
                    }
                }
                sum = deferreds.awaitAll().sum()
            }
        } catch (e: TimeoutCancellationException) {
            println(e.message)
        } finally {
            println("$sum")
        }

    }

    private suspend fun doMultipleTasks() = coroutineScope { // this: CoroutineScope
        launch {
            delay(2000L)
            println("World 2")
        }
        launch {
            delay(1000L)
            println("World 1")
        }
        println("Hello")
    }

    private suspend fun multipleLaunches(i: Int) {
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World! [${i}]") // print after delay
    }
}
