package de.ostfale.sbkotlin.sbkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SbKotlinApplication

fun main(args: Array<String>) {
    runApplication<SbKotlinApplication>(*args)
}
